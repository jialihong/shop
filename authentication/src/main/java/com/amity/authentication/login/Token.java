package com.amity.authentication.login;

/**
 * Created by Amity on 2021/1/6 11:22
 */
public class Token {
    private String token;

    private Long expireTime;

    public Token() {
    }

    public Token(String token, Long expireTime) {
        this.token = token;
        this.expireTime = expireTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
