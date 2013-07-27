set charset "utf8";
drop database if exists `car_monitor_db`;
create database `car_monitor_db` character set utf8 collate utf8_unicode_ci;
create user `car_db_user`@`localhost` IDENTIFIED by 'password';
grant all on `car_monitor_db`.* to `car_db_user`@`localhost`;
use `car_monitor_db`;
drop table if exists `departments`;
create table `departments` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `parent_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `long_name` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into departments values ('b58f6085-c8af-4592-9894-08d19c191345', 'b58f6085-c8af-4592-9894-08d19c191345', '武警总部','武警总部', null, null);
insert into departments values ('523a6c45-7e57-410d-8a38-09e0d5a3cc06', 'b58f6085-c8af-4592-9894-08d19c191345', '浙江总队','浙江总队', null, null);
insert into departments values ('ee900c15-7b04-4fda-a687-44e9f6ff96a2', 'b58f6085-c8af-4592-9894-08d19c191345', '贵州总队','贵州总队', null, null);
insert into departments values ('413725c9-3fdc-4f04-830d-ac7f4350db34', '523a6c45-7e57-410d-8a38-09e0d5a3cc06', '杭州支队','浙江总队杭州支队', null, null);
insert into departments values ('0156c73a-4f4c-401e-8ebe-169f7f9c0f75', '413725c9-3fdc-4f04-830d-ac7f4350db34', '西湖中队','浙江总队杭州支队西湖中队', null, null);
insert into departments values ('3dc0189a-5453-431e-8ba8-032cf6532c68', '413725c9-3fdc-4f04-830d-ac7f4350db34', '拱墅中队','浙江总队杭州支队拱墅中队', null, null);
insert into departments values ('dd55e56b-8ddb-43d7-8f7f-776afd1682e7', '413725c9-3fdc-4f04-830d-ac7f4350db34', '滨江中队','浙江总队杭州支队滨江中队', null, null);
insert into departments values ('38c867d0-3d57-452f-8297-a0d83804ae4c', '413725c9-3fdc-4f04-830d-ac7f4350db34', '下城中队','浙江总队杭州支队下城中队', null, null);

drop table if exists `cars`;
create table `cars` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `reg_number` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `driver_name` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `driver_phone` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `department_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `terminal_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `map_id` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into cars values ('68f230af-9e45-4a49-b8cd-018aa710356e', '浙AC687R', '福克斯','张三', '1367500000', '3dc0189a-5453-431e-8ba8-032cf6532c68', 'd3f8d7e0-784f-41cb-8f15-b57c528971f9', null, null, null);

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
  UNIQUE KEY `terminal_id_uk` (`terminal_id`),
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

