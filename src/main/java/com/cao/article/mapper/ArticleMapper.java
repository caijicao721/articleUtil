package com.cao.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cao.article.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * ClassName: ArticleMapper
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/4 23:21
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {
}
