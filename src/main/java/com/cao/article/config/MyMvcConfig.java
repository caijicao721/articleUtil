package com.cao.article.config;

import com.cao.article.common.ConstParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: MyMvcConfig
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/4 13:22
 */

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Autowired
    ConstParam constParam;






    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///"+constParam.getUploadDir()+"/");
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
}
