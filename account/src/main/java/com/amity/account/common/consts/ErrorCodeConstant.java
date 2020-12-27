package com.amity.account.common.consts;

/**
 * Created by Amity on 2020/12/23 16:30
 */
public class ErrorCodeConstant {

    /**
     * 公共参数：101 + 1：客户端 |2：服务端 +后四位：自定义
     */
    public static final String ACCOUNT_THROWABLE_ERROR = "10120000";

    /**
     * 公共参数 1012：0100-0199
     */
    public static class AccountCommonError {

    }

    /**
     * 注册   1012：0200-0299
     */
    public static class AccountRegisterError {

        public static final String MOBILE_REQUIRED = "10120200";

        public static final String PASSWORD_REQUIRED = "10120201";
    }
}
