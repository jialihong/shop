package com.amity.authentication.config;

import com.amity.authentication.service.AmityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Created by Amity on 2021/1/4 10:44
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AmityTokenAuthenticationFilter amityTokenAuthenticationFilter;

    @Autowired
    private AmityUserDetailsService userDetailsService;

//    private AmityAuthTokenConfigurer securityConfigurerAdapter() {
//        return new AmityAuthTokenConfigurer();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //特定的资源只允许特定的角色访问
                .authorizeRequests()
                    .antMatchers("/product/**").hasRole("USER")
//                    .antMatchers("/admin/**").hasRole("admin")
//                    .antMatchers("/login/**").hasAnyRole("ANONYMOUS")
                .and()
                .authorizeRequests()
                    .antMatchers("/login/**").permitAll()
                //其他所有请求，都需要经过验证
                .anyRequest().authenticated()
                .and()
                //当需要用户登录时候，转到的登录页面   spring security默认提供了一个登录页面，以及登录控制器。
                .formLogin()
                .and()
                .addFilterBefore(amityTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        //无状态  结合token，sso登录
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * 人员绑定角色
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                // 管理员，同事具有 ADMIN,USER权限，可以访问所有资源
//                .withUser("admin1").password("{noop}admin1").roles("ADMIN","USER")
//                .and()
//                // 普通用户，只能访问 /product/**
//                .withUser("user1").password("{noop}user1").roles("USER");

//        auth.authenticationProvider(authenticationProvider());
        /**
         * 从数据库获取用户和角色
         */
        auth
                .userDetailsService(userDetailsService)   //设置自定义的userDetailsService
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * 解决UsernameNotFoundException不抛出的问题
     * @return
     */
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setHideUserNotFoundExceptions(false);
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
}
