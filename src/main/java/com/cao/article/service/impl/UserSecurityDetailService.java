package com.cao.article.service.impl;

import com.cao.article.entity.User;
import com.cao.article.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: UserSecurityDetailService
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/6 12:24
 */
@Service
public class UserSecurityDetailService implements UserDetailsService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByName(username);
        //user=null;
        System.out.println(user+"查询了该用户");
        if (user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        // 角色集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 角色必须以`ROLE_`开头，数据库中没有，则在这里加
        authorities.add(new SimpleGrantedAuthority("ROLE_User"));
        // 数据库密码是明文, 需要加密进行比对
        return new org.springframework.security.core.userdetails.User(user.getUsername(), passwordEncoder.encode(user.getPassword()),authorities);
    }
}
