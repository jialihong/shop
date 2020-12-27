package com.amity.account.web.controller;

import com.amity.account.pojo.form.RegisterForm;
import com.amity.account.service.AccountUserService;
import com.amity.account.web.validate.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/register")
@RestController
public class RegisterController {

    @Autowired
    private RegisterValidator registerValidator;

    @Autowired
    private AccountUserService accountUserService;

    @PostMapping("/mobileRegister")
    public Boolean mobileRegister(@RequestBody RegisterForm registerForm){
        registerValidator.mobileRegisterValidate(registerForm);
        accountUserService.registerUser(registerForm);
        return Boolean.TRUE;
    }
}
