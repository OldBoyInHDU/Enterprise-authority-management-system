package com.ruki.eams.service.impl;

import com.ruki.eams.dao.IUserDao;
import com.ruki.eams.domain.Role;
import com.ruki.eams.domain.UserInfo;
import com.ruki.eams.service.IUserService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将包含用户信息的userInfo对象封装成UserDetails，明文密码需要在密码前加上前缀
        //User user = new User(userInfo.getUsername(), "{noop}"+userInfo.getPassword(), getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(), "{noop}"+userInfo.getPassword(), userInfo.getStatus() == 1?true:false,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    //获得用户权限集合的方法，集合中装的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for(Role role : roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        return list;
    }

}
