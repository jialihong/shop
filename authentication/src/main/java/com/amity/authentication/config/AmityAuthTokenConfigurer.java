package com.amity.authentication.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Amity on 2021/1/12 17:11
 */
//public class AmityAuthTokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        不可行的！！
//        AmityTokenAuthenticationFilter amityFilter = new AmityTokenAuthenticationFilter();
//        http.addFilterBefore(amityFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}
