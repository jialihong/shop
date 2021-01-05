package com.amity.authentication.pojo.domain;

import java.util.List;

/**
 * Created by Amity on 2021/1/5 11:49
 */
public class UserRoleDO {
    private String mobile;

    private String password;

    private List<String> roleNameList;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoleNameList() {
        return roleNameList;
    }

    public void setRoleNameList(List<String> roleNameList) {
        this.roleNameList = roleNameList;
    }
}
