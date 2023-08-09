package com.cao.article.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cao.article.common.Result;
import com.cao.article.entity.Article;
import com.cao.article.entity.Issue;
import com.cao.article.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: IssueController
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/6 19:05
 */
@Controller
public class IssueController extends BaseController{


    @GetMapping("/issue-add")
    public String toIssueAdd(){
        return "issue-add";
    }

    @GetMapping("/showIssue/{issueName}")
    public String showIssue(@PathVariable(name="issueName") String name){
        List<Article> list = articleService.list(new QueryWrapper<Article>().eq("issue_name", name));
        List<String> collect = list.stream().map(Article::getName).collect(Collectors.toList());
        req.setAttribute("list",list);
        httpSession.setAttribute("issueName",name);
        return "issuePage";
    }

    @ResponseBody
    @PostMapping("/issue/add")
    public Result add(String issueName){
        if (!StrUtil.isNotBlank(issueName)||"".equals(issueName)){
            return Result.fail("论文名称不能为空！");
        }
        User currentUser = super.getCurrentUser();
        List<Issue> name = issueService.list(new QueryWrapper<Issue>().eq("name", issueName));
        if (name.size()!=0){
            return Result.fail("已存在该论文");
        }
        Issue issue = new Issue();
        Date date = new Date(System.currentTimeMillis());
        issue.setName(issueName);
        issue.setCreateTime(date);
        issue.setAuthor(currentUser.getUsername());
        issue.setModifiedTime(date);
        boolean save = issueService.save(issue);
        if (save){
            return Result.success();
        }else{
            return Result.fail("添加失败");
        }

    }

    @ResponseBody
    @GetMapping("/issue/delete/{issueId}")
    public Result deleteIssue(@PathVariable int issueId){
        Issue issue = issueService.getOne(new QueryWrapper<Issue>().eq("id", issueId));
        String issueName = issue.getName();
        boolean remove = articleService.removeByIds(articleService.list(new QueryWrapper<Article>().eq("issue_name", issueName))
                .stream().map(Article::getId).collect(Collectors.toList()));
        if (!remove){
            return Result.fail("文章删除失败！请重试");
        }
        boolean b = issueService.removeById(issueId);
        if (b){
            return Result.success();
        }else{
            return Result.fail("论文删除失败！请重试");
        }
    }
}
