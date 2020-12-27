package com.amity.account.web.validate;

import com.amity.account.common.consts.ErrorCodeConstant;
import com.amity.account.common.utils.I18nMessageUtil;
import com.amity.account.pojo.form.RegisterForm;
import com.amity.common.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by Amity on 2020/12/23 14:47
 */
@Component
public class RegisterValidator {

    public void mobileRegisterValidate(RegisterForm registerForm){
        if(StringUtils.isBlank(registerForm.getMobile())) {
            throw new ValidationException(I18nMessageUtil.getMessage(ErrorCodeConstant.AccountRegisterError.MOBILE_REQUIRED), ErrorCodeConstant.AccountRegisterError.MOBILE_REQUIRED);
        }

        if(StringUtils.isBlank(registerForm.getPassword())) {
            throw new ValidationException(I18nMessageUtil.getMessage(ErrorCodeConstant.AccountRegisterError.PASSWORD_REQUIRED), ErrorCodeConstant.AccountRegisterError.PASSWORD_REQUIRED);
        }
    }
}
