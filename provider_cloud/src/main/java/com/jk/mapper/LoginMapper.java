package com.jk.mapper;

import com.jk.bean.User;

public interface LoginMapper {
    User findUserByUserAccountAndUserPwd(User user);
}
