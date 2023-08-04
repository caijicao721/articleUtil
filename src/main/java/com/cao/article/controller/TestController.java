package com.cao.article.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    @RequestMapping({"","/","index"})
    public String hello(){
        req.setAttribute("pdfURL","upload/小林coding-图解redis数据结构-亮白风格-v1.0.pdf");
        return "index";
    }


    @PostMapping({"/upload"})
    public String upload(@RequestParam("file") MultipartFile[] files) throws IOException, InterruptedException {
        long s = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        int index = 0;
//        String filePath = constParam.getUploadDir();
//        for (MultipartFile file:files){
//            File dest = new File(filePath + "\\"+ file.getOriginalFilename());
//            if (!dest.getParentFile().exists()){
//                dest.getParentFile().mkdirs();
//            }
//            file.transferTo(dest);
//        }
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        int size = files.length;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (MultipartFile file:files){
            String filePath = constParam.getUploadDir();
            list.add(index++);

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







}
