package com.cao.article.config;

import com.cao.article.service.impl.UserSecurityDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * ClassName: MySercurityConfig
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/5 21:30
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //自定义登陆页面
                .loginPage("/login")
                //如果URL为loginPage,则用SpringSecurity中自带的过滤器去处理该请求
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/index")
                .and()
                //请求授权
                .authorizeRequests()
                //在访问我们的URL时，我们是不需要省份认证，可以立即访问
                .antMatchers("/","/login","/register","/doRegister","/doLogin").permitAll()
                //所有请求都被拦截，都需认证
                .anyRequest().authenticated()
                .and()
                // 请求头允许X-ContentType-Options
                .headers().contentTypeOptions().disable()
                .and()
                // 请求头允许X-Frame-Options, 否则所有iframe将失效
//                .headers().frameOptions().disable()
//                .and()
                // 注销, 回到首页
                .logout().logoutSuccessUrl("/")
                //SpringSecurity保护机制
                .and()
                .csrf().disable();
    }




    @Autowired
    private UserSecurityDetailService userSecurityDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 认证
        auth.userDetailsService(userSecurityDetailService).passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        // 使用BCrypt加密密码
        return new BCryptPasswordEncoder();
    }
}
