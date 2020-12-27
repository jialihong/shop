package com.amity.account.common.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import java.util.Set;

/**
 * Created by Amity on 2020/12/23 19:34
 */
@Configuration
public class I18nMessageConfiguration {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        //加载国际化信息文件
        Set<String> messagePackages = messageSource.getBasenameSet();
        //业务异常
        messagePackages.add("i18n.exceptionMessage");
        //校验异常
        messagePackages.add("i18n.validatorMessage");
        //描述性资源
        messagePackages.add("i18n.i18nMessage");
        messageSource.setBasenames(messagePackages.toArray(new String[3]));

        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

}
