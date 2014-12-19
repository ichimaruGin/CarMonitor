set charset "utf8";
drop database if exists `car_monitor_db`;
create database `car_monitor_db` character set utf8 collate utf8_unicode_ci;
grant all privileges on `car_monitor_db`.* to `car_db_user`@`localhost` identified by 'password';
flush privileges; 
use `car_monitor_db`;
drop table if exists `departments`;
create table `departments` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `parent_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `long_name` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` TIMESTAMP,
  `updated_at` TIMESTAMP,
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
  `created_at` TIMESTAMP,
  `updated_at` TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into cars values ('68f230af-9e45-4a49-b8cd-018aa710356e', '浙AC687R', '福克斯','张三', '1367500000', '3dc0189a-5453-431e-8ba8-032cf6532c68', 'a4119d8d-9300-437d-b097-1a922a60c81e', null, null, null);

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
  `created_at` TIMESTAMP,
  `updated_at` TIMESTAMP,
  UNIQUE KEY `terminal_id_uk` (`terminal_id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into `terminals` values('a4119d8d-9300-437d-b097-1a922a60c81e','00001',150, 200, 200, 30, 5, 5, 200, 100,   '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminals` values('e522cd5f-23e0-4d5f-8187-bfaa381c9026','00002',150, 200, 200, 30, 5, 5, 200, 100,   '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminals` values('5a9cb92c-048d-4cb2-bd6c-fef07a8637f8','00003',150, 200, 200, 30, 5, 5, 200, 100,   '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminals` values('8f7509f6-f509-4531-8eaa-a7032a89626f','00004',150, 200, 200, 30, 5, 5, 200, 100,   '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminals` values('bbdc3cf3-7f1a-4e87-9ca7-4d52173375b8','00005',150, 200, 200, 30, 5, 5, 200, 100,   '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminals` values('ce5ee8cf-d958-4859-ad39-c069db1dc4b3','00006',150, 200, 200, 30, 5, 5, 200, 100,   '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminals` values('033cc190-9e94-4675-8df3-692e7406dce9','00007',150, 200, 200, 30, 5, 5, 200, 100,   '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminals` values('e3e57ee7-37be-4bb6-9398-e0016dca2e9b','00008',150, 200, 200, 30, 5, 5, 200, 100,   '2013-07-12 12:12:32', '2013-07-12 12:12:32');

drop table if exists `maps`;
create table `map` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` TIMESTAMP,
  `updated_at` TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


drop table if exists `terminal_events`;
create table `terminal_events` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `terminal_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `type` char(6) COLLATE utf8_unicode_ci NOT NULL,
  `process_flag` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `process_message` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `raw_message` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` TIMESTAMP,
  `updated_at` TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into `terminal_events` values ('42958bb8-a94f-4cb4-8408-07e7c4a82875','a4119d8d-9300-437d-b097-1a922a60c81e', 'CAT718',null, null, 'Stored by script', '2013-07-29 01:25:00','2013-07-29 01:25:00');

drop table if exists `terminal_event_attributes`;
create table `terminal_event_attributes` (
 `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
 `type` char(6) COLLATE utf8_unicode_ci NOT NULL,
 `attr_code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
 `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
 `created_at` TIMESTAMP,
 `updated_at` TIMESTAMP,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into `terminal_event_attributes` values ('e27eed4f-459a-434f-aec6-b0517a2b209f','CAT718', 'CAR_WATER_TEMP_PARAM', '水温参数', '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminal_event_attributes` values ('025d0728-fabf-4995-b70b-dbb2335fe181','CAT718', 'CAR_SPEED_PARAM', '车速', '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminal_event_attributes` values ('531cb3fe-893d-4b31-bca1-c153910123d0','CAT718','CAR_LATITUDE', '纬度', '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminal_event_attributes` values ('7812283c-6adb-450c-a612-6f984ca6f9dc', 'CAT718','CAR_LONGITUDE', '经度', '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminal_event_attributes` values ('f4128212-3331-4987-99de-40c659c9a7c0', 'CAT718','CAR_RPM_PARAM', '转速', '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminal_event_attributes` values ('cc5b3433-6ab9-4982-83c8-2ac87722beed', 'CAT718','CAR_OIL_PARAM', '燃油', '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminal_event_attributes` values ('60af8671-edd1-409e-bca7-63c32c282af0', 'CAT718','TIRED_DRIVE_STATE', '疲劳驾驶', '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminal_event_attributes` values ('4cf1b782-f34a-4ff3-be5b-e4ff8b1450a8', 'CAT718','DRUNK_DRIVE', '酒驾', '2013-07-12 12:12:32', '2013-07-12 12:12:32');

insert into `terminal_event_attributes` values ('8162955e-397b-4481-812c-03ca72382ebe', 'CATOBD','CAR_OBD_ERR', '故障码', '2013-07-12 12:12:32', '2013-07-12 12:12:32');

drop table if exists `terminal_event_attr_char`;
create table `terminal_event_attr_char` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `event_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `attr_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `attr_value` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` TIMESTAMP,
  `updated_at` TIMESTAMP,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into `terminal_event_attr_char` values ('375e6cd7-473f-4564-af78-6d1c8ab85712', '42958bb8-a94f-4cb4-8408-07e7c4a82875','531cb3fe-893d-4b31-bca1-c153910123d0', '129.4', '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminal_event_attr_char` values ('db3d9fdf-8632-4f88-97ad-933b2f9f85e5', '42958bb8-a94f-4cb4-8408-07e7c4a82875','7812283c-6adb-450c-a612-6f984ca6f9dc', '119.3', '2013-07-12 12:12:32', '2013-07-12 12:12:32');


drop table if exists `terminal_event_attr_long`;
create table `terminal_event_attr_long` (
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `event_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `attr_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `attr_value` int(11) DEFAULT NULL,
  `created_at` TIMESTAMP,
  `updated_at` TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

drop table if exists `terminal_exceptions`;
create table `terminal_exceptions`(
  `id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `terminal_id` char(36) COLLATE utf8_unicode_ci NOT NULL,
  `code` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `long_value` int(11) DEFAULT NULL,
  `char_value` varchar(255) DEFAULT NULL,
  `process_flag` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` TIMESTAMP,
  `updated_at` TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into `terminal_event_attr_long` values ('2f96401a-6b6d-4f3d-b4be-566f261e5935', '42958bb8-a94f-4cb4-8408-07e7c4a82875','e27eed4f-459a-434f-aec6-b0517a2b209f',89, '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminal_event_attr_long` values ('48c95b48-fb38-41be-8cda-f890610b733f', '42958bb8-a94f-4cb4-8408-07e7c4a82875','025d0728-fabf-4995-b70b-dbb2335fe181',119, '2013-07-12 12:12:32', '2013-07-12 12:12:32');
insert into `terminal_event_attr_long` values ('3c4edaf9-f5d4-4911-aae8-d4d57736b2f5', '42958bb8-a94f-4cb4-8408-07e7c4a82875','f4128212-3331-4987-99de-40c659c9a7c0',2500, '2013-07-12 12:12:32', '2013-07-12 12:12:32');
