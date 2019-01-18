package com.jk.service.impl;

import com.jk.bean.User;
import com.jk.mapper.LoginMapper;
import com.jk.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    public User findUserByUserAccountAndUserPwd(User user) {
       User userFormDb = loginMapper.findUserByUserAccountAndUserPwd(user);
       return userFormDb;
    }
}
