package com.jk.controller;

import com.jk.bean.User;
import com.jk.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("goToLogin")
    public User goToLogin(@RequestBody User user){
        User userFromDb = loginService.findUserByUserAccountAndUserPwd(user);
        return userFromDb;
    }

}
