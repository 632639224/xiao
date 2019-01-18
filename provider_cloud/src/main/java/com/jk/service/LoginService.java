package com.jk.service;

import com.jk.bean.User;

public interface LoginService {
    User findUserByUserAccountAndUserPwd(User user);
}
