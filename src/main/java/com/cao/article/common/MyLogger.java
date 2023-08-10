package com.cao.article.common;

import com.cao.article.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * ClassName: MyLogger
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/9 16:49
 */
public class MyLogger {

    private static final Logger logger;

    static {
        logger =  LoggerFactory.getLogger(MyLogger.class);
    }

    MyLogger(){

    }

    public static Logger getLogger(){
        return logger;
    }

}
