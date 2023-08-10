package com.cao.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * ClassName: Issue
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/4 20:36
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Issue {

    @TableId(type = IdType.AUTO)
    private int id;

    private String author;

    private String name;

    private Date createTime;

    private Date modifiedTime;

    private int articleCount;

}
