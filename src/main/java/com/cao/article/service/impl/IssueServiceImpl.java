package com.cao.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cao.article.entity.Issue;
import com.cao.article.mapper.IssueMapper;
import com.cao.article.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: IssueServiceImpl
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/5 13:44
 */
@Service
public class IssueServiceImpl extends ServiceImpl<IssueMapper, Issue> implements IssueService {

    @Autowired
    IssueMapper issueMapper;

}
