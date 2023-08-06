package com.cao.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * ClassName: com.cao.article.ArticleUtilApplication
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/4 12:38
 */
@MapperScan("com.cao.article.mapper")
@SpringBootApplication
public class ArticleUtilApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(ArticleUtilApplication.class, args);
        for (String name : ioc.getBeanDefinitionNames()) {
            //System.out.println(name);
        }
        System.out.println("http://localhost:8080");
    }
}
