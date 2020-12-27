package com.amity.account.service;

import com.amity.account.pojo.form.RegisterForm;

/**
 * Created by Amity on 2020/12/23 14:20
 */
public interface AccountUserService {

    Boolean registerUser(RegisterForm registerForm);
}
