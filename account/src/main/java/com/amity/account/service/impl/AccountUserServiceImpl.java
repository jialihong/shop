package com.amity.account.service.impl;

import com.amity.account.dao.AccountUserDao;
import com.amity.account.pojo.form.RegisterForm;
import com.amity.account.pojo.po.AccountUserPO;
import com.amity.account.service.AccountUserService;
import com.amity.common.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Amity on 2020/12/23 14:22
 */
@Service
public class AccountUserServiceImpl implements AccountUserService {

    @Autowired
    private AccountUserDao accountUserDao;

    @Override
    public Boolean registerUser(RegisterForm registerForm) {
        AccountUserPO userPO = BeanMapper.map(registerForm, AccountUserPO.class);
        accountUserDao.insert(userPO);
        return Boolean.TRUE;
    }
}
