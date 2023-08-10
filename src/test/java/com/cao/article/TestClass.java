package com.cao.article;

import com.cao.article.common.IssueRank;
import com.cao.article.entity.Issue;
import com.cao.article.entity.User;
import com.cao.article.mapper.UserMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.sql.*;
import java.util.List;

/**
 * ClassName: TestClass
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/4 20:49
 */
//@SpringBootTest
public class TestClass {

    @Autowired
    UserMapper userMapper;


    @Test
    public void test() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost/articleutil?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        Date date = new Date(System.currentTimeMillis());
        PreparedStatement statement = connection.prepareStatement("insert into user value(?,?,?,?,?,?)");
        statement.setInt(1,1);
        statement.setString(2,"cjm");
        statement.setString(3,"123");
        statement.setObject(4,date);
        statement.setObject(5,date);
        statement.setObject(6,date);
        int i = statement.executeUpdate();


        if (i==1) {
            System.out.println(1);
        }

    }
    @Test
    public void test1(){
        System.out.println(userMapper);

    }



    @Test
    public void test2(){

    }
}
