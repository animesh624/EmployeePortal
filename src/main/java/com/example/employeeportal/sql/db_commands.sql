CREATE TABLE `user_data_temp` (
                             `id` varchar(64) NOT NULL DEFAULT '',
                             `user_email` varchar(64) NOT NULL DEFAULT '',
                             `password` varchar(128) NOT NULL DEFAULT '',
                             `first_name` varchar(56) DEFAULT NULL,
                             `last_name` varchar(56) DEFAULT NULL,
                             `is_admin` int DEFAULT 0,
                             `date_created` datetime DEFAULT NULL,
                             `date_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `uk_username` (`user_name`),
                             KEY `idx_username` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `employee_data_temp` (
                             `id` varchar(64) NOT NULL DEFAULT '',
                             `user_email` varchar(64) NOT NULL DEFAULT '',
                             `first_name` varchar(56) DEFAULT NULL,
                             `last_name` varchar(56) DEFAULT NULL,
                              `full_name` varchar(124) DEFAULT NULL,
                             `emp_code` varchar(56) DEFAULT NULL,
                             `designation` varchar(56) DEFAULT NULL,
                             `pod` varchar(56) DEFAULT NULL,
                             `frequency` int DEFAULT 0,
                             `contact_number` varchar(56) DEFAULT NULL,
                             `date_created` datetime DEFAULT NULL,
                             `date_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `uk_username` (`user_name`),
                             UNIQUE KEY `uk_emp_code` (`emp_code`),
                             KEY `idx_username` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `employee_data_temp`
    ADD COLUMN `manager_email` varchar(128) NOT NULL;

ALTER TABLE `employee_data`
    ADD COLUMN `full_name` varchar(128) NOT NULL DEFAULT NULL;

ALTER TABLE `employee_data`
    DROP COLUMN `password`;

CREATE TABLE `ui_user_role_temp` (
                                     `id` varchar(48) NOT NULL,
                                     `employee_id` varchar(48) NOT NULL,
                                     `role_id` varchar(48) NOT NULL,
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ui_role_master_temp` (
                                  `id` varchar(48) NOT NULL,
                                  `name` varchar(64) NOT NULL,
                                  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `manager_reportee_temp` (
                                       `reportee_email` varchar(64) NOT NULL,
                                       `manager_email` varchar(64) NOT NULL,
                                       PRIMARY KEY (`reportee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `ui_role_master_temp`
VALUES ('R1','TOPUP');


INSERT INTO `ui_role_master_temp`
VALUES ('R2','KYC');


INSERT INTO `ui_role_master_temp`
VALUES ('R3','STARTER');


INSERT INTO `ui_role_master_temp`
VALUES ('R4','REPEAT');


INSERT INTO `ui_role_master_temp`
VALUES ('R5','BNPL');


INSERT INTO `ui_role_master_temp`
VALUES ('R6','NACH');


UPDATE employee_data_temp
SET user_email = CONCAT(SUBSTRING_INDEX(user_email, '.', 1), '@example.com')
WHERE user_email LIKE '%.%.%';


ALTER TABLE `employee_data_temp`
    ADD COLUMN `dob` varchar(50) NOT NULL;

CREATE TABLE `languages_temp` (
                                         `user_email` varchar(64) NOT NULL,
                                         `language` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `skills_temp` (
                               `user_email` varchar(64) NOT NULL,
                               `skill` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `interests_temp` (
                                  `id` varchar(50) NOT NULL,
                                  `user_email` varchar(64) NOT NULL,
                                  `interest` varchar(64) NOT NULL,
                                  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



ALTER TABLE `employee_data_temp`
    ADD COLUMN `profile_image_url` varchar(150);

ALTER TABLE `interests_temp`
    ADD COLUMN `id` varchar(50), PRIMARY KEY;



INSERT INTO `user_role_master_temp` (`role_id`, `name`)
VALUES
    ('R28', 'Instagram');


ALTER TABLE document_url_temp
    RENAME COLUMN role_id TO document_name;

ALTER table employee_data_temp
drop column manager_code;

ALTER table `user_data_temp` drop index uk_username;

ALTER TABLE user_data_temp
    MODIFY user_email VARCHAR(128) NOT NULL,
    ADD CONSTRAINT uc_user_email UNIQUE (user_email);


ALTER TABLE employee_data_temp
    ADD CONSTRAINT fk_user_email
        FOREIGN KEY (user_email)
            REFERENCES user_data_temp(user_email);



ALTER TABLE employee_data_temp
    ALTER COLUMN frequency SET DEFAULT 0;




