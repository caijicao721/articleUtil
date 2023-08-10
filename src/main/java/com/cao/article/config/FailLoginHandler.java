package com.cao.article.config;

import com.cao.article.common.MyLogger;
import org.slf4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * ClassName: FailLoginHandler
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/9 21:35
 */
@Component
public class FailLoginHandler implements AuthenticationFailureHandler {

    protected Logger logger = MyLogger.getLogger();

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletRequest.getSession().setAttribute("message","用户名或密码错误！");
        logger.info("登录失败");
        logger.info(e.getMessage());
        httpServletResponse.sendRedirect("/login");
    }
}
