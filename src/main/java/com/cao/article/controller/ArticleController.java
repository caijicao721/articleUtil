package com.cao.article.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cao.article.common.Result;
import com.cao.article.entity.Article;
import com.cao.article.entity.Issue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * ClassName: ArticleController
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/6 21:55
 */
@Controller
public class ArticleController extends BaseController{


    @ResponseBody
    @PostMapping("/setCurrentPage")
    public Result setPage(int currentPage){

        String name = (String)httpSession.getAttribute("articleName");
        String issueName = (String)httpSession.getAttribute("issueName");
        Article article = articleService.getOne(
                new QueryWrapper<Article>().eq("name", name)
                        .eq("issue_name",issueName)
        );
        article.setHistoryPageNum(currentPage);
        boolean b = articleService.updateById(article);
        //logger.info(String.valueOf(currentPage));
        if (b){
            return Result.success();
        }else{
            return Result.fail("更新失败");
        }
    }

    @ResponseBody
    @PostMapping("/setTotalPage")
    public Result setTotalPage(int totalPage){
        logger.info("总页数" + totalPage);
        String name = (String)httpSession.getAttribute("articleName");
        String issueName = (String)httpSession.getAttribute("issueName");
        Article article = articleService.getOne(
                new QueryWrapper<Article>().eq("name", name)
                        .eq("issue_name",issueName)
        );
        article.setTotalPageNum(totalPage);
        boolean b = articleService.updateById(article);
        logger.info(String.valueOf(totalPage));
        if (b){
            return Result.success();
        }else{
            return Result.fail("更新失败");
        }
    }

    @PostMapping({"/upload"})
    public String upload(@RequestParam("file") MultipartFile[] files) throws IOException, InterruptedException {

        long s = System.currentTimeMillis();
        List<Article> list = new ArrayList<>();
        Date date = new Date(s);
        ExecutorService executorService = new ThreadPoolExecutor(5,10,100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
        int size = files.length;
        int count = 0;
        String issueName = (String)httpSession.getAttribute("issueName");
        List<Article> issuelist = articleService.list(new QueryWrapper<Article>().eq("issue_name", issueName));
        Set<String> collect = issuelist.stream().map(Article::getName).collect(Collectors.toSet());
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (MultipartFile file:files){
            if (collect.contains(file.getOriginalFilename())|| "".equals(file.getOriginalFilename())
                || !StrUtil.isNotBlank(file.getOriginalFilename())){
                countDownLatch.countDown();
                continue;
            }
            String filePath = constParam.getUploadDir();
            Article article = new Article();
            article.setName(file.getOriginalFilename());
            article.setUploadTime(date);
            article.setIssueName(issueName);
            article.setHistoryPageNum(1);
            article.setViewCount(0);
            list.add(article);
            count++;
            logger.info(article.toString());
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
        if (count!=0){
            boolean b = articleService.saveBatch(list);
            Issue issue = issueService.getOne(new QueryWrapper<Issue>().eq("name", issueName));
            issue.setArticleCount(issue.getArticleCount()+count);
            boolean save = issueService.updateById(issue);
            logger.info(String.valueOf(save));
            if (!b||!save){
                return "error";
            }
        }
        logger.info("消耗时间为"+(e-s)+"ms");
        List<Article> issueList = articleService.list(new QueryWrapper<Article>().eq("issue_name", issueName));
        req.setAttribute("list",issueList);
        logger.info(String.valueOf(issueList));
        return "issuePage";

    }


    @GetMapping("/showPDF/{pdfURL}")
    public String showPdf(@PathVariable(name="pdfURL") String name){
        req.setAttribute("name",name);
        httpSession.setAttribute("articleName",name);
        String issueName = (String)httpSession.getAttribute("issueName");
        Article article = articleService.getOne(
                new QueryWrapper<Article>().eq("name", name)
                        .eq("issue_name",issueName)
        );
        Date date = new Date(System.currentTimeMillis());
        article.setLastViewTime(date);
        article.setViewCount(article.getViewCount()+1);
        articleService.updateById(article);
        req.setAttribute("history",article.getHistoryPageNum());
        req.setAttribute("currentIssueName",issueName);
        logger.info("当前issue"+issueName);
        return "show";
    }

    @ResponseBody
    @GetMapping("/article/delete/{id}")
    public Result deleteArticle(@PathVariable int id){
        List<Article> list = articleService.list(new QueryWrapper<Article>().eq("id", id));
        if (list.size()==0){
            return Result.fail("该文章不存在");
        }
        boolean b = articleService.removeById(id);
        if (b){
            return Result.success();
        }else{
            return Result.fail("删除失败，请重试！");
        }
    }
}
