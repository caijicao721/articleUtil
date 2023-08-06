package com.cao.article.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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

    public int id;

    public String uploadUser;

    public int historyPageNum;

    public int totalPageNum;

    public Date uploadTime;

    public Date lastViewTime;

    public int viewCount;

}
