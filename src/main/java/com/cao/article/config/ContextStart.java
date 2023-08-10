package com.cao.article.config;

import cn.hutool.core.comparator.CompareUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cao.article.common.ArticleRank;
import com.cao.article.common.IssueRank;
import com.cao.article.common.MyLogger;
import com.cao.article.entity.Article;
import com.cao.article.entity.Issue;
import com.cao.article.entity.User;
import com.cao.article.service.ArticleService;
import com.cao.article.service.IssueService;
import com.cao.article.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * ClassName: ContextStart
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/9 23:27
 */
@Component
public class ContextStart implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    ArticleRank articleRank;

    @Autowired
    IssueService issueService;

    @Autowired
    ArticleService articleService;

    @Autowired
    IssueRank issueRank;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Issue> issueList = issueService.list();
        for (Issue issue:issueList){
            List<Article> articleList = articleService.list(new QueryWrapper<Article>().eq("issue_name", issue.getName()));
            for (Article article:articleList){
                articleRank.addArticle(issue.getName(),article);
            }
        }
        MyLogger.getLogger().info("初始化文章排行榜成功！！！！！");

        List<User> userList = userService.list();
        for (User user:userList){
            List<Issue> list = issueService.list(new QueryWrapper<Issue>().eq("author", user.getUsername()));
            list.sort((a, b) -> CompareUtil.compare(b.getModifiedTime(), a.getModifiedTime()));
            for (Issue issue:list){
                issueRank.put(user.getUsername(),issue);
            }

        }
        MyLogger.getLogger().info("初始化论文排行榜成功！！！！！");
    }
}
