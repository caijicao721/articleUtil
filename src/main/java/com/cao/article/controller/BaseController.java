package com.cao.article.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.cao.article.common.ArticleRank;
import com.cao.article.common.ConstParam;
import com.cao.article.common.IssueRank;
import com.cao.article.common.MyLogger;
import com.cao.article.entity.User;
import com.cao.article.service.ArticleService;
import com.cao.article.service.IssueService;
import com.cao.article.service.UserService;
import com.cao.article.service.impl.UserSecurityDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * ClassName: BaseController
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/4 12:46
 */
@Controller
public class BaseController {

    @Autowired
    ConstParam constParam;


    @Autowired
    HttpServletRequest req;

    @Autowired
    HttpSession httpSession;

    protected Logger logger = MyLogger.getLogger();

    @Autowired
    ArticleService articleService;

    @Autowired
    IssueService issueService;

    @Autowired
    UserService userService;

    @Autowired
    UserSecurityDetailService userSecurityDetailService;

    @Autowired
    ArticleRank articleRank;

    @Autowired
    IssueRank issueRank;

    public User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal!=null){
            Map<String,Object> map = BeanUtil.beanToMap(principal);
            String username =(String) map.get("username");
            if (StrUtil.isNotBlank(username)){
                return userService.findByName(username);
            }
        }
        return null;
    }

}
