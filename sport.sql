-- sport.sys_user definition

CREATE TABLE `sys_user` (
                            `id` bigint(20) NOT NULL COMMENT '用户id',
                            `user_type` tinyint(1) NOT NULL COMMENT '0运动员1数据录入员2学校管理员3体育总局超管',
                            `user_name` varchar(20) NOT NULL,
                            `password` varchar(50) NOT NULL,
                            `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `sys_user` (`id`, `user_type`, `user_name`, `password`, `created_time`) VALUES ('1', '3', 'super_admin', 'admin', '2023-10-01 19:45:31');
INSERT INTO `sys_user` (`id`, `user_type`, `user_name`, `password`, `created_time`) VALUES ('2', '2', 'admin', 'admin', '2023-10-01 19:45:45');
INSERT INTO `sys_user` (`id`, `user_type`, `user_name`, `password`, `created_time`) VALUES ('3', '1', 'recorder', '123456', '2023-10-01 19:46:24');


-- sport.match_item definition

CREATE TABLE `match_item` (
                              `id` bigint(20) NOT NULL COMMENT '比赛id',
                              `match_name` varchar(50) NOT NULL COMMENT '比赛名称',
                              `apply_start_time` datetime NOT NULL COMMENT '报名开始时间',
                              `apply_end_time` datetime NOT NULL COMMENT '报名结束时间',
                              `rank_rule` tinyint(1) NOT NULL COMMENT '排名规则1升序2降序',
                              `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='赛事表';


-- sport.match_participation definition

CREATE TABLE `match_participation` (
                                       `id` bigint(20) NOT NULL COMMENT '报名ID',
                                       `user_id` bigint(20) NOT NULL COMMENT '用户id',
                                       `match_id` bigint(20) NOT NULL COMMENT '比赛id',
                                       `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='报名表';


-- sport.match_result definition

CREATE TABLE `match_result` (
                                `id` bigint(20) NOT NULL COMMENT '成绩ID',
                                `user_id` bigint(20) NOT NULL COMMENT '用户id',
                                `match_id` bigint(20) NOT NULL COMMENT '比赛id',
                                `score` decimal(10,2) NOT NULL COMMENT '成绩',
                                `round` bigint(20) NOT NULL COMMENT '轮次',
                                `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='成绩表';