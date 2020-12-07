drop database if exists shiro;
create database shiro;
use shiro;
drop table if exists user;
drop table if exists role;
drop table if exists permission;
drop table if exists user_role;
drop table if exists role_permission;

create table user(
id bigint auto_increment,
name varchar(100),
password varchar(100),
constraint pk_user primary key(id)
)charset=UTF8 engine=InnoDB;
create table role(
id bigint auto_increment,
name varchar(100),
constraint pk_role primary key(id)
)charset=UTF8 engine=InnoDB;
create table permission(
id bigint auto_increment,
name varchar(100),
constraint pk_permission primary key(id)
)charset=UTF8 engine=InnoDB;
create table user_role(
uid bigint,
rid bigint,
constraint pk_user_role primary key(uid,rid)
)charset=UTF8 engine=InnoDB;
create table role_permission(
	rid bigint,
	pid bigint,
	constraint pk_role_permission primary key(rid,pid)
)charset=UTF8 engine=InnoDB;


-- insert data
/* */
INSERT INTO `permission` VALUES (1,'addCode');
INSERT INTO `permission` VALUES (2,'deleteCode');
INSERT INTO `permission` VALUES (3,'editCode');
INSERT INTO `permission` VALUES (4,'updateCode');
INSERT INTO `permission` VALUES (5,'listCode');
INSERT INTO `permission` VALUES (6,'addFeatures');
INSERT INTO `permission` VALUES (7,'deleteFeatures');
INSERT INTO `permission` VALUES (8,'editFeatures');
INSERT INTO `permission` VALUES (9,'updateFeatures');
INSERT INTO `permission` VALUES (10,'listFeatures');
INSERT INTO `role` VALUES (1,'admin');
INSERT INTO `role` VALUES (2,'CodeManager');
INSERT INTO `role` VALUES (3,'featuresManager');
INSERT INTO `role_permission` VALUES (1,1);
INSERT INTO `role_permission` VALUES (1,2);
INSERT INTO `role_permission` VALUES (1,3);
INSERT INTO `role_permission` VALUES (1,4);
INSERT INTO `role_permission` VALUES (1,5);
INSERT INTO `role_permission` VALUES (1,6);
INSERT INTO `role_permission` VALUES (1,7);
INSERT INTO `role_permission` VALUES (1,8);
INSERT INTO `role_permission` VALUES (1,9);
INSERT INTO `role_permission` VALUES (1,10);
INSERT INTO `role_permission` VALUES (2,1);
INSERT INTO `role_permission` VALUES (2,2);
INSERT INTO `role_permission` VALUES (2,3);
INSERT INTO `role_permission` VALUES (2,4);
INSERT INTO `role_permission` VALUES (2,5);
INSERT INTO `role_permission` VALUES (3,6);
INSERT INTO `role_permission` VALUES (3,7);
INSERT INTO `role_permission` VALUES (3,8);
INSERT INTO `role_permission` VALUES (3,9);
INSERT INTO `role_permission` VALUES (3,10);
INSERT INTO `user` VALUES (1,'gaki','3354');
INSERT INTO `user` VALUES (2,'ohaha','9527');
INSERT INTO `user_role` VALUES (1,1);
INSERT INTO `user_role` VALUES (2,2);

alter table user add (salt varchar(100))