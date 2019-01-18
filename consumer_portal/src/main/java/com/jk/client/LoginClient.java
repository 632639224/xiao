package com.jk.client;

        import com.jk.bean.User;
        import org.springframework.cloud.netflix.feign.FeignClient;
        import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient("plogin")
public interface LoginClient{

    @RequestMapping("goToLogin")
    User login(User user);
}