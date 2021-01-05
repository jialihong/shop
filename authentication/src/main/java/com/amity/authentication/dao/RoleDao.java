package com.amity.authentication.dao;

import com.amity.authentication.pojo.po.RolePO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Amity on 2021/1/5 16:24
 */
@Repository
public interface RoleDao {

    List<RolePO> searchRoleListByUserId(Long userId);
}
