package com.cao.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cao.article.entity.Issue;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * ClassName: IssueMapper
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/4 23:21
 */
@Repository
public interface IssueMapper extends BaseMapper<Issue> {
}
