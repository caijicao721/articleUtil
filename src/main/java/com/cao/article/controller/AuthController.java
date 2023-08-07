package com.cao.article.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.cao.article.common.Result;
import com.cao.article.entity.User;
import com.cao.article.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Wrapper;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ClassName: AuthController
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/6 12:58
 */
@Controller
public class AuthController extends BaseController{

    @GetMapping({"/","/login"})
    public String login(){
        return "login";
    }


//    @PostMapping("/doLogin")
//    public void doLogin(String username, String password){
//        User user = userService.findByName(username);
//        System.out.println(user);
//        System.out.println(username);
//        System.out.println(password);
//        if (user == null){
//            req.setAttribute("loginResult","用户不存在");
//        }else{
//            String password1 = user.getPassword();
//            if (!password1.equals(password)){
//                req.setAttribute("loginResult","密码不一致");
//            }
//        }
//    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @ResponseBody
    @PostMapping("/doRegister")
    public Result doRegister(String username,String password,String confirmPassword){
        System.out.println(username);
        System.out.println(password);
        System.out.println(confirmPassword);
        if ("".equals(username)||"".equals(password)||"".equals(confirmPassword)){
            return Result.fail("请输入正确的用户名与密码！");
        }
        if (username==null||password==null||confirmPassword==null){
            return Result.fail("请输入正确的用户名与密码！");
        }
        List<User> list = userService.list();
        for (User user:list){
            if (user.getUsername().equals(username)){
                return Result.fail("用户名已存在！");
            }
        }
        if (!password.equals(confirmPassword)){
            return Result.fail("密码输入不一致");
        }
        User user = new User();
        Date date = new Date(System.currentTimeMillis());
        user.setUsername(username);
        user.setPassword(password);
        user.setCreateTime(date);
        user.setModifiedTime(date);
        boolean save = userService.save(user);
        if (save){
            return Result.success();
        }else{
            return Result.fail("请重试！");
        }
    }


    @GetMapping("/logout")
    public String logout(){
        System.out.println("logout----------------------");
        return "redirect:/";
    }
}
