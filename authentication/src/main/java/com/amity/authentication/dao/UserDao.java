package com.amity.authentication.dao;

import com.amity.authentication.pojo.AccountPO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    AccountPO getUserByUsername(String username);
}
