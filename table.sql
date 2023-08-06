CREATE DATABASE articleUtil;

USE articleUtil;

CREATE TABLE `user`(
                       `id` int  NOT NULL,
                       `username` VARCHAR(20)  NOT NULL,
                       `password`VARCHAR(20) NOT NULL,
                       `create_time` DATETIME NOT NULL,
                       `modified_time` DATETIME NOT NULL,
                       `last_login_time` DATETIME NOT NULL,
                       PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;


CREATE TABLE `issue`(
                        `id` int  NOT NULL,
                        `author` VARCHAR(20) NOT null,
                        `name` VARCHAR(30) NOT NULL,
                        `create_time` DATETIME NOT NULL,
                        `modified_time` DATETIME NOT NULL,
                        PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `article`(
                          `id` int  NOT NULL,
                          `upload_user` VARCHAR(20) NOT null,
                          `history_pagenum` INT NOT NULL,
                          `total_pagenum` INT NOT NULL,
                          `upload_time` DATETIME NOT NULL,
                          `last_view_time` DATETIME NOT NULL,
                          `view_count` INT NOT NULL,
                          PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;