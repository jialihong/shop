package com.amity.account.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 国际化工具类
 * Created by Amity on 2020/12/23 17:34
 */
@Component
public class I18nMessageUtil implements MessageSourceAware {

    private static MessageSourceAccessor messageSourceAccessor;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        I18nMessageUtil.messageSourceAccessor = new MessageSourceAccessor(messageSource);
    }

    /**
     * 获取错误信息文件中对应的报错信息
     * @param code 错误码（错误信息文件中code）
     * @param locale 地区信息
     * @param args 参数
     * @return 报错信息
     */
    public static String getMessage(String code, Locale locale, Object... args) {
        if(null == locale) {
            return messageSourceAccessor.getMessage(code, args);
        }
        return messageSourceAccessor.getMessage(code, args, locale);
    }

    /**
     * 获取错误信息文件中对应的报错信息 如果不传locale信息，则从当前request获取，如果还是没有，则使用默认locale
     * @param code 错误码（错误信息文件中code）
     * @param args locale 地区信息
     * @return 报错信息
     */
    public static String getMessage(String code, Object... args) {
        return messageSourceAccessor.getMessage(code, args);
    }

}
