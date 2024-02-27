CREATE TABLE `user_data` (
                             `id` varchar(64) NOT NULL DEFAULT '',
                             `username` varchar(64) NOT NULL DEFAULT '',
                             `password` varchar(128) NOT NULL DEFAULT '',
                             `first_name` varchar(56) DEFAULT NULL,
                             `last_name` varchar(56) DEFAULT NULL,
                             `date_created` datetime DEFAULT NULL,
                             `date_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `uk_username` (`username`),
                             KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `employee_data` (
                             `id` varchar(64) NOT NULL DEFAULT '',
                             `username` varchar(64) NOT NULL DEFAULT '',
                             `first_name` varchar(56) DEFAULT NULL,
                             `last_name` varchar(56) DEFAULT NULL,
                             `emp_code` varchar(56) DEFAULT NULL,
                             `designation` varchar(56) DEFAULT NULL,
                             `level` varchar(56) DEFAULT NULL,
                             `contact_number` varchar(56) DEFAULT NULL,
                             `date_created` datetime DEFAULT NULL,
                             `date_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `uk_username` (`username`),
                             UNIQUE KEY `uk_emp_code` (`emp_code`),
                             KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;