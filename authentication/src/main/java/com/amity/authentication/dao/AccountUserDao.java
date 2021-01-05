package com.amity.authentication.dao;

import com.amity.authentication.pojo.po.AccountUserPO;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountUserDao {

    AccountUserPO getUserByUsername(String mobile);
}
