package com.cao.article.controller;

import cn.hutool.core.bean.BeanUtil;
import com.cao.article.entity.Article;
import com.cao.article.entity.User;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * ClassName: TestController
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/4 12:46
 */
@Controller
public class TestController extends BaseController{


    @RequestMapping({"/index"})
    public String hello(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal!=null){
            Map<String, Object> map = BeanUtil.beanToMap(principal);
            String username =(String) map.get("username");
        }
        System.out.println(principal);
        req.setAttribute("pdfURL","upload/小林coding-图解redis数据结构-亮白风格-v1.0.pdf");
        List<User> list = userService.list();
        System.out.println(list);
        return "index";
    }


    @PostMapping({"/upload"})
    public String upload(@RequestParam("file") MultipartFile[] files) throws IOException, InterruptedException {

        long s = System.currentTimeMillis();
        List<Article> list = new ArrayList<>();
        Date date = new Date(s);

        ExecutorService executorService = new ThreadPoolExecutor(5,10,100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
        int size = files.length;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (MultipartFile file:files){
            String filePath = constParam.getUploadDir();
//            Article article = new Article();
//            article.setUploadTime(date);
//            article.setUploadUser();
//            article.setHistoryPageNum(1);
//            article.setLastViewTime();
//            article.setTotalPageNum();
//            article.setViewCount();
            executorService.execute(()->{
                File dest = new File(filePath + "\\"+ file.getOriginalFilename());
                if (!dest.getParentFile().exists()){
                    dest.getParentFile().mkdirs();
                }
                try {
                    file.transferTo(dest);

                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }

            });

        }
        countDownLatch.await();
        executorService.shutdown();
        long e = System.currentTimeMillis();
        req.setAttribute("list",list);
        logger.info("消耗时间为"+(e-s)+"ms");
        return "complete";
    }


    @GetMapping("/showPDF/{pdfid}")
    public String show(@PathVariable(name="pdfid") int id){
        System.out.println(id);
        req.setAttribute("name","1-s2.0-S2214157X2200627X-main.pdf");
        req.setAttribute("history",4);
        return "show";
    }


    @GetMapping("/test")
    public void test1(){
        Date date = new Date(System.currentTimeMillis());
        User cjm = new User();
        cjm.setId(1);
        cjm.setUsername("cjm");
        cjm.setPassword("123");
        cjm.setCreateTime(date);
        cjm.setModifiedTime(date);
        cjm.setLastLoginTime(date);
        boolean save = userService.save(cjm);
        if (save){
            System.out.println(1);
        }
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
