package com.cao.article.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.cao.article.common.Result;
import com.cao.article.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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
    @PostMapping("/user/edit")
    public Result edit(String username, String password, String confirmPassword){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal!=null){
            Map<String,Object> map = BeanUtil.beanToMap(principal);
            String name =(String) map.get("username");
            if (StrUtil.isNotBlank(name)){
                User user = userService.findByName(name);
                List<User> list = userService.list();
                Set<String> collect = list.stream().map(User::getUsername).collect(Collectors.toSet());
                if (collect.contains(username)){
                    return Result.fail("用户名已存在!");
                }
                if (!password.equals(confirmPassword) || "".equals(password)){
                    return Result.fail("密码输入错误!");
                }
                if ("".equals(username)){
                    return Result.fail("用户名不能为空!");
                }
                int id = user.getId();
                Date date = new Date(System.currentTimeMillis());
                User updateUser = new User();
                updateUser.setId(id)
                          .setUsername(username)
                          .setCreateTime(user.getCreateTime())
                          .setPassword(password)
                                  .setModifiedTime(date);
                boolean b = userService.updateById(updateUser);
                if (b){
                    return Result.success();
                }else{
                    return Result.fail("修改失败！");
                }
            }else{
                return Result.fail("登录过期，请重新登录！");
            }
        }
        else{
            return Result.fail("登录过期，请重新登录！");
        }
    }

}
