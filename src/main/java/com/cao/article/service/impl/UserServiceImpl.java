package com.cao.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cao.article.mapper.IssueMapper;
import com.cao.article.mapper.UserMapper;
import com.cao.article.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cao.article.entity.User;

import java.sql.Date;

/**
 * ClassName: UserServiceImpl
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/4 23:26
 */
@Service()
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {


    @Autowired
    UserMapper userMapper;

    public void test1(){
        System.out.println(userMapper);
        Date date = new Date(System.currentTimeMillis());
        User cjm = new User();
        cjm.setId(1);
        cjm.setUsername("cjm");
        cjm.setPassword("123");
        cjm.setCreateTime(date);
        cjm.setModifiedTime(date);
        cjm.setLastLoginTime(date);
        int insert = userMapper.insert(cjm);
        System.out.println(insert);
    }
}
