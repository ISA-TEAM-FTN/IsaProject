DROP TABLE IF EXISTS `center_account`;

CREATE TABLE `center_account`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `date_created`   datetime   NOT NULL,
    `date_updated`   datetime DEFAULT NULL,
    `deleted`        bit(1)     NOT NULL,
    `name`           varchar(255),
    `description`    varchar(255),
    `average_rating` decimal(19, 4),
    `address`        varchar(255),
    `city`           varchar(255),
    `country`        varchar(255),
    `start_time`     time,
    `end_time`       time,
    PRIMARY KEY (id)
);

INSERT INTO `center_account`(date_created,date_updated,deleted,name, description, average_rating, address, city, country, start_time, `end_time`)
values ('2022-04-22 10:34:23', '2022-04-22 10:34:23	', false,'Center1', 'Description', 12.5, 'Address1', 'City1', 'Country1', '10:34:21', '18:34:23');

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`                bigint(20)   NOT NULL AUTO_INCREMENT,
    `date_created`      datetime     NOT NULL,
    `date_updated`      datetime     DEFAULT NULL,
    `deleted`           bit(1)       NOT NULL,
    `email`             varchar(255) NOT NULL,
    `first_name`        varchar(255) NOT NULL,
    `password`          varchar(255) NOT NULL,
    `address`           varchar(255) NOT NULL,
    `country`           varchar(255) NOT NULL,
    `city`              varchar(255) NOT NULL,
    `phone`             varchar(255) NOT NULL,
    `role`              varchar(255) DEFAULT NULL,
    `last_name`         varchar(255) NOT NULL,
    `first_login`       bit(1)       NOT NULL,
    `personal_id`       varchar(255),
    `gender`            varchar(255),
    `occupation`        varchar(255),
    `occupation_info`   varchar(255),
    `center_account_id` bigint(20),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`center_account_id`) REFERENCES center_account (id)
);

insert into `user`(date_created, date_updated, deleted, email, first_name, password, address, country, city, phone,
                   role, last_name, first_login, personal_id, gender, occupation, occupation_info, center_account_id)
VALUES ('2022-04-22 10:34:23', '2022-04-22 10:34:23	', false, 'nebojsa@gmail.com', 'Nebojsa', '$2a$12$uS5REK6ftoMpYcQdEyFA4et02OP22Fkvebbs5SHEeT.hM/hd5QRg2
', 'a', 'c', 'c', 'p', 'ADMIN_CENTER', 'Bogosavljev', false, '3213213', 'MALE', '', '', 1)