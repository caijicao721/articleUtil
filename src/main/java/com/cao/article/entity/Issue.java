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
public class Issue {

    @TableId(type = IdType.AUTO)
    public int id;

    public String author;

    public String name;

    public Date createTime;

    public Date modifiedTime;

}
