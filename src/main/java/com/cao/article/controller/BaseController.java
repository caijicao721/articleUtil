package com.cao.article.controller;

import com.cao.article.common.ConstParam;
import com.cao.article.service.ArticleService;
import com.cao.article.service.IssueService;
import com.cao.article.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
    HttpServletResponse resp;

    Logger logger =  LoggerFactory.getLogger(BaseController.class);

    @Autowired
    ArticleService articleService;

    @Autowired
    IssueService issueService;

    @Autowired
    UserService userService;

}
