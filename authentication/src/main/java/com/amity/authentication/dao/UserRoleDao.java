package com.amity.authentication.dao;

import com.amity.authentication.pojo.po.RolePO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Amity on 2021/1/5 12:00
 */
@Repository
public interface UserRoleDao {

    List<RolePO> searchByUserId(Long userId);
}
