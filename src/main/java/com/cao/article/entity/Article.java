package com.cao.article.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.Date;

/**
 * ClassName: Article
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/4 20:39
 */
@Data
@Accessors(chain = true)
public class Article{

    @TableId(type = IdType.AUTO)
    private int id;

    private String name;

    private String issueName;

    private int historyPageNum;

    private int totalPageNum;

    private Date uploadTime;

    private Date lastViewTime;

    private int viewCount;

}
