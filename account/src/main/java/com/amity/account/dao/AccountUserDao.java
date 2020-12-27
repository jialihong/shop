package com.amity.account.dao;

import com.amity.account.pojo.po.AccountUserPO;
import org.springframework.stereotype.Repository;

/**
 * Created by Amity on 2020/12/23 11:16
 */
@Repository
public interface AccountUserDao {

    void insert(AccountUserPO userPO);
}
