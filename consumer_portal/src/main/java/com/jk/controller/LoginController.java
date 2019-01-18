package com.jk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jk.bean.User;
import com.jk.client.LoginClient;
import com.jk.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Controller
@RequestMapping("user")
public class LoginController {

    @Autowired
    LoginClient lc;

    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("view")
    public String toView(String viewname){
        return viewname;
    }

    /**
     *登录+记住密码
     */
    @ResponseBody
    @RequestMapping("login")
    public String login(User user, HttpSession session, HttpServletResponse response) throws UnsupportedEncodingException {

        User userFormProvider = lc.login(user);
        //判断用户名密码是否正确
        if(userFormProvider == null){
            return "1";
        }
        //如果正确   将用户信息存放到session中
        session.setAttribute("user",userFormProvider);
        //判断前台有没有勾选记住密码
        //如果勾选了
        if(user.getRemPwd()!=null){


            //第一种方法给session中存放数据   通过字符窜拼接
        /*    String userMessage = userFormProvider.getUserAccount()+"#"+userFormProvider.getUserPassWord();*/
            //第二种方法给session中存放数据    通过json与对象之间的转换
            String userAndPwd = JSONObject.toJSONString(userFormProvider);
            //将json对象进行编码
            String userMessage = URLEncoder.encode(userAndPwd, "UTF-8");
            // 将 账号 密码存放到cookie中
           Cookie cookie = new Cookie(Constant.userMsg,userMessage);

            //设置在根目录就可以访问
            cookie.setPath("/");
            //设置过期时间    单位：秒
            cookie.setMaxAge(604800);
            //将自定义的cookie放到响应头  存放在浏览器内存中
            response.addCookie(cookie);
        }else{
            //如果没有勾选记住密码   则清空cookie
            Cookie cookie = new Cookie(Constant.userMsg,null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "0";
    }

    /**
     *回显密码框
     */
    @ResponseBody
    @RequestMapping("getCookie")
    public User getCookie(HttpServletRequest request) throws UnsupportedEncodingException {
        //获取到所有的cookie
        Cookie[] cookies = request.getCookies();

      /*  String cookieValue = "";*/
        User userobj = null;

        //判断cookie不为空
        if(cookies != null){
            //遍历cookie
            for (Cookie cookie : cookies) {
                //如果cookie的名称与你存入的cookie名称相同
                if(cookie.getName().equals(Constant.userMsg)){
                    //将json对象进行解码   获取到cookie的value值
                    String user = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    //将json转换成对象
                    userobj = JSON.parseObject(user, User.class);
                    /*cookieValue = cookie.getValue();*/
                }
            }
        }
       /* return cookieValue;*/

        return userobj;
    }
}
