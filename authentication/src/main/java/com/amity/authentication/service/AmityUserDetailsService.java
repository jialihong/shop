package com.amity.authentication.service;

import com.amity.authentication.dao.UserDao;
import com.amity.authentication.pojo.AccountPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component("userDetailsService")
public class AmityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1、查询用户
        AccountPO account = userDao.getUserByUsername(username);
        if(null == account) {
            //这里 找不到必须要抛异常
            throw new UsernameNotFoundException("User " + username + " was not found in db");
        }

        //2、设置角色
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>(16);
        account.getRoleList().forEach(role -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
            grantedAuthorities.add(grantedAuthority);
        });

        return new User(username, account.getPassword(), grantedAuthorities);
    }
}
