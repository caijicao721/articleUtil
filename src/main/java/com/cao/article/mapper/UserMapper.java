package com.cao.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cao.article.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * ClassName: UserMapper
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/4 23:22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findByName(String name);
}
