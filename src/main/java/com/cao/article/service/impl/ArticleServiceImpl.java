package com.cao.article.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cao.article.entity.Article;
import com.cao.article.mapper.ArticleMapper;
import com.cao.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: ArticleServiceImpl
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/5 13:50
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

}
