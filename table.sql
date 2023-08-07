CREATE DATABASE articleUtil;

USE articleUtil;

CREATE TABLE `user`(
                       `id` int  NOT NULL auto_increment,
                       `username` VARCHAR(20)  NOT NULL,
                       `password`VARCHAR(20) NOT NULL,
                       `create_time` DATETIME NOT NULL,
                       `modified_time` DATETIME NOT NULL,
                       `last_login_time` DATETIME DEFAULT NULL,
                       PRIMARY KEY(`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb3;


CREATE TABLE `issue`(
                        `id` int  NOT NULL auto_increment,
                        `author` VARCHAR(20) NOT NULL,
                        `name` VARCHAR(30) NOT NULL,
                        `create_time` DATETIME NOT NULL,
                        `modified_time` DATETIME NOT NULL,
                        PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `article`(
                          `id` int  NOT NULL auto_increment,
                          `name` VARCHAR(50) NOT NULL,
                          `issue_name` VARCHAR(50) NOT null,
                          `history_page_num` INT NOT NULL,
                          `total_page_num` INT DEFAULT NULL,
                          `upload_time` DATETIME NOT NULL,
                          `last_view_time` DATETIME DEFAULT NULL,
                          `view_count` INT DEFAULT NULL,
                          PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;