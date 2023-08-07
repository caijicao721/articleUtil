package com.cao.article.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cao.article.entity.Issue;
import com.cao.article.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: TestController
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/4 12:46
 */
@Controller
public class IndexController extends BaseController{


    @GetMapping("/edit")
    public String editUser(){
        return "edit";
    }

    @RequestMapping({"/index"})
    public String hello(){
        User currentUser = super.getCurrentUser();
        Date date = new Date(System.currentTimeMillis());
        currentUser.setLastLoginTime(date);
        userService.updateById(currentUser);
        List<Issue> list = issueService.list(new QueryWrapper<Issue>().eq("author",currentUser.username));
        List<String> collect = list.stream().map(Issue::getName).collect(Collectors.toList());
        req.setAttribute("issueList",collect);
        System.out.println(collect);
        return "index";
    }











}
