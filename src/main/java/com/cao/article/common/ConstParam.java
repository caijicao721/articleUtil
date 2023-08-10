package com.cao.article.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * ClassName: ConstParam
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/4 12:49
 */
@Component
public class ConstParam {

    @Value("${file.upload.dir}")
    private String uploadDir;

    public String getUploadDir(){
        return uploadDir;
    }

    public static final int CACHE_SIZE = 3;
}
