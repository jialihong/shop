package com.amity.authentication.login;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * Created by Amity on 2021/1/6 11:23
 */
public class TokenProvider {
    private final String secretKey;

    private final int tokenValidity;

    public TokenProvider(String secretKey, int tokenValidity) {
        this.secretKey = secretKey;
        this.tokenValidity = tokenValidity;
    }

    /**
     * 生成token
     */
    public Token createToken(UserDetails userDetails) {
        long expires = System.currentTimeMillis() + 1000L * tokenValidity;
        String token = computeSignature(userDetails, expires);
        return new Token(token, expires);
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

    /**
     * 生成token字符串
     * @param userDetails
     * @param expires
     * @return
     */
    public String computeSignature(UserDetails userDetails, long expires) {
        // 一些特有的信息组装 ,并结合某种加密活摘要算法   例如 something+"|"+something2+MD5(s);
        return secretKey + "." + DigestUtils.md5DigestAsHex((secretKey + userDetails.getUsername()).getBytes());
    }

}
