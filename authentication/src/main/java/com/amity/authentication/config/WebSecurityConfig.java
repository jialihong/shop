package com.amity.authentication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Amity on 2021/1/4 10:44
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //特定的资源只允许特定的角色访问
                .authorizeRequests()
                    .antMatchers("/product/**").hasRole("USER")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                //当需要用户登录时候，转到的登录页面   spring security默认提供了一个登录页面，以及登录控制器。
                .formLogin()
                .and()
                .httpBasic();
    }


    /**
     * 人员绑定角色
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                // 管理员，同事具有 ADMIN,USER权限，可以访问所有资源
                .withUser("admin1").password("{noop}admin1").roles("ADMIN","USER")
                .and()
                // 普通用户，只能访问 /product/**
                .withUser("user1").password("{noop}user1").roles("USER");
    }
}
