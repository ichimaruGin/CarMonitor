  drop database if exists `car_monitor_db`;
create database `car_monitor_db` character set utf8 collate utf8_unicode_ci;
create user `car_db_user`@`localhost` IDENTIFIED by 'password';
grant all on `car_monitor_db`.* to `car_db_user`@`localhost`;
use `car_monitor_db`;
drop table if exists `cars`;
create table `cars` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `reg_number` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `driver_name` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `driver_phone` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `terminal_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `department` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `division` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `branch` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `map_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into cars values ('68f230af-9e45-4a49-b8cd-018aa710356e', '浙AC687R', '福克斯','张三', '1367500000', 'd3f8d7e0-784f-41cb-8f15-b57c528971f9', '武警浙江总队', '杭州中队', '西湖支队', null, null, null);

drop table if exists `terminals`;
create table `terminals` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `terminal_id` char(5) COLLATE utf8_unicode_ci NOT NULL,
  `alcohol_limit` int(11) DEFAULT NULL,
  `water_temp_limit` int(11) DEFAULT NULL,
  `speed_limit` int(11) DEFAULT NULL,
  `tired_drive_limit_minutes` int(11) DEFAULT NULL,
  `sampling_interval_seconds` int(11) DEFAULT NULL,
  `sampling_data_upload_seconds` int(11) DEFAULT NULL,
  `tl_wake_up_interval_seconds` int(11) DEFAULT NULL,
  `idle_status_upload_seconds` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into `terminals` values('a4119d8d-9300-437d-b097-1a922a60c81e','00001',150, 200, 200, 30, 5, 5, 200, 100,   '2013-07-12 12:12:32', '2013-07-12 12:12:32');

drop table if exists `maps`;
create table `map` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

drop table if exists `terminal_events`;
create table `terminal_events` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `terminal_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `type` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `process_flag` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `process_message` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

drop table `terminal_event_attributes`;
create table `terminal_event_attributes` (
 `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
 `attr_code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
 `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
 `created_at` datetime DEFAULT NULL,
 `updated_at` datetime DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into `terminal_event_attributes` values ('e27eed4f-459a-434f-aec6-b0517a2b209f','CAR_WATER_TEMP_PARAM', '水温参数', '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminal_event_attributes` values ('025d0728-fabf-4995-b70b-dbb2335fe181', 'CAR_SPEED', '车速', '2013-07-12 12:12:32', '2013-07-12 12:12:32');

drop table if exists `terminal_event_attr_char`;
create table `terminal_event_attr_char` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `event_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `attr_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `attr_value` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

drop table if exists `terminal_event_attr_long`;
create table `terminal_event_attr_long` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `event_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `attr_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `attr_value` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

