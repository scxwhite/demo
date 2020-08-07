




CREATE TABLE `three_cat_user`
(
    `id`           int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username`     varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
    `password`     varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
    `email`        varchar(64) NOT NULL DEFAULT '' COMMENT '邮箱地址',
    `phone`        varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
    `dep_id`       int(11)     NOT NULL DEFAULT '-1' COMMENT '部门id',
    `is_valid`     tinyint(2)  NOT NULL DEFAULT '0' COMMENT '是否有效',
    `gmt_create`   bigint(13)  NOT NULL DEFAULT '0' COMMENT '创建时间',
    `gmt_modified` bigint(13)  NOT NULL DEFAULT '0' COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;
