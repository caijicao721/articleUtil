package com.cao.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: com.cao.article.ArticleUtilApplication
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/4 12:38
 */
@SpringBootApplication
public class ArticleUtilApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleUtilApplication.class,args);
        System.out.println("http://localhost:8080");
    }
}
