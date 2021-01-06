package com.amity.authentication.service;

import com.amity.authentication.dao.AccountUserDao;
import com.amity.authentication.dao.RoleDao;
import com.amity.authentication.dao.UserRoleDao;
import com.amity.authentication.pojo.po.AccountUserPO;
import com.amity.authentication.pojo.po.RolePO;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component("userDetailsService")
public class AmityUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountUserDao accountUserDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1、查询用户
        AccountUserPO account = accountUserDao.getUserByUsername(username);
        if(null == account) {
            //这里 找不到必须要抛异常
            throw new UsernameNotFoundException("User " + username + " was not found in db");
        }

        List<RolePO> rolePOS = roleDao.searchRoleListByUserId(account.getId());

        if(CollectionUtils.isEmpty(rolePOS)) {
            System.out.println("没有权限");
        }

        //2、设置角色
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>(16);
        rolePOS.forEach(role -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getRoleName());
            grantedAuthorities.add(grantedAuthority);
        });

        return new User(username, account.getPassword(), grantedAuthorities);
    }
}
