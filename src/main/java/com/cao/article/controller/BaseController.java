package com.cao.article.controller;

import com.cao.article.common.ConstParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


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


    Logger logger =  LoggerFactory.getLogger(BaseController.class);

}
