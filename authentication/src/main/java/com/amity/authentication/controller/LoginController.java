package com.amity.authentication.controller;

import com.amity.authentication.common.StringConstant;
import com.amity.authentication.redis.RedisUtils;
import com.amity.authentication.service.AmityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * Created by Amity on 2021/1/6 11:16
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AmityUserDetailsService userDetailsService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取token
     * @param username username
     * @param password password
     * @return 获取token
     */
    @GetMapping("/token")
    public String authorize(@RequestParam String username, @RequestParam String password) {
        //1、创建UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        //2、认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        //3、保存认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //4、加载UserDetails
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        //5、生成自定义的token
        String token = DigestUtils.md5DigestAsHex((username + LocalDateTime.now().toString()).getBytes());
        //保存到redis  key--token;value--username
        redisUtils.set(token, userDetails.getUsername(), 1000L);
        return StringConstant.Token_BEGIN + token;
    }

}
