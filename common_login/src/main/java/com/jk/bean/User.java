package com.jk.bean;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;

    private String userAccount;

    private String userPassWord;

    private String userName;

    private Date createTime;

    private String remPwd;

    private Integer role = 1;
}
