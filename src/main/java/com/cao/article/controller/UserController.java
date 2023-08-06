package com.cao.article.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.cao.article.common.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: UserController
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/6 10:24
 */
@Controller
public class UserController extends BaseController{

    @ResponseBody
    @PostMapping("/doLogin")
    public Result doLogin(String username,String password){
        System.out.println(username);
        System.out.println(password);
        System.out.println("登录成功------------------------------------");
        return Result.success();
    }

}
