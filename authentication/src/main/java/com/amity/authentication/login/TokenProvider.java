package com.amity.authentication.login;

import com.amity.authentication.common.StringConstant;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

/**
 * Created by Amity on 2021/1/6 11:23
 */
public class TokenProvider {
    private final String username;

    public TokenProvider(String username) {
        this.username = username;
    }

    /**
     * 生成token
     */
    public String createToken() {
        return DigestUtils.md5DigestAsHex((username + LocalDateTime.now().toString()).getBytes());
    }

    /**
     * 验证token
     */
    public Boolean validateToken(String authToken, UserDetails userDetails) {
        //check token

        return Boolean.TRUE;
    }

    /**
     * 从token中获取用户名
     * @param authToken token
     * @return 登录名
     */
    public String getUsernameFromToken(String authToken) {
        // ...

        return "username";
    }

}
