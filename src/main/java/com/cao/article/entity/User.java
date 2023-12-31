package com.cao.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * ClassName: User
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/4 20:10
 */
@Data
@Accessors(chain = true)
public class User {

    @TableId(type = IdType.AUTO)
    private int id;

    private String username;

    private String password;

    private Date createTime;

    private Date modifiedTime;

    private Date lastLoginTime;
}
