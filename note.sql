DROP DATABASE IF EXISTS shiro;
CREATE DATABASE shiro DEFAULT CHARACTER SET utf8;
USE shiro;
   
drop table if exists user;
drop table if exists role;
drop table if exists permission;
drop table if exists user_role;
drop table if exists role_permission;
   
create table user (
  id bigint auto_increment,
  name varchar(100),
  password varchar(100),
  constraint pk_users primary key(id)
) charset=utf8 ENGINE=InnoDB;
   
create table role (
  id bigint auto_increment,
  name varchar(100),
  constraint pk_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;
   
create table permission (
  id bigint auto_increment,
  name varchar(100),
  constraint pk_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;
   
create table user_role (
  id bigint auto_increment,
  uid bigint,
  rid bigint,
  constraint pk_users_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;
   
create table role_permission (
  id bigint auto_increment,
  rid bigint,
  pid bigint,
  constraint pk_roles_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;



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
INSERT INTO `role` VALUES (2,'codeManager');
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
INSERT INTO `user` VALUES (1,'Gaki','3354');
INSERT INTO `user` VALUES (2,'Tinker','9527');
INSERT INTO `user_role` VALUES (1,1);
INSERT INTO `user_role` VALUES (2,2);


# support ssm  shiro
alter table user  add salt varchar(100);
alter table role  add desc_ varchar(100);
alter table permission  add desc_ varchar(100);
alter table permission  add url varchar(100);

delete  from user;
delete  from role;
delete  from user_role;
delete from permission;
delete  from role_permission;


INSERT INTO `permission` VALUES (1,'addCode','增加代码','/addCode');
INSERT INTO `permission` VALUES (2,'deleteCode','删除代码','/deleteCode');
INSERT INTO `permission` VALUES (3,'editeCode','编辑代码','/editeCode');
INSERT INTO `permission` VALUES (4,'updateCode','修改代码','/updateCode');
INSERT INTO `permission` VALUES (5,'listCode','查看代码','/listCode');
INSERT INTO `permission` VALUES (6,'addFeatures','增加功能','/addFeatures');
INSERT INTO `permission` VALUES (7,'deleteFeatures','删除功能','/deleteFeatures');
INSERT INTO `permission` VALUES (8,'editeFeatures','编辑功能','/editeFeatures');
INSERT INTO `permission` VALUES (9,'updateFeatures','修改功能','/updateFeatures');
INSERT INTO `permission` VALUES (10,'listFeatures','查看功能','/listFeatures');
INSERT INTO `role` VALUES (1,'admin','超级管理员');
INSERT INTO `role` VALUES (2,'codeManager','代码管理员');
INSERT INTO `role` VALUES (3,'featuresManager','功能管理员');
INSERT INTO `role_permission` VALUES (1,1,1);
INSERT INTO `role_permission` VALUES (2,1,2);
INSERT INTO `role_permission` VALUES (3,1,3);
INSERT INTO `role_permission` VALUES (4,1,4);
INSERT INTO `role_permission` VALUES (5,1,5);
INSERT INTO `role_permission` VALUES (6,1,6);
INSERT INTO `role_permission` VALUES (7,1,7);
INSERT INTO `role_permission` VALUES (8,1,8);
INSERT INTO `role_permission` VALUES (9,1,9);
INSERT INTO `role_permission` VALUES (10,1,10);
INSERT INTO `role_permission` VALUES (11,2,1);
INSERT INTO `role_permission` VALUES (12,2,2);
INSERT INTO `role_permission` VALUES (13,2,3);
INSERT INTO `role_permission` VALUES (14,2,4);
INSERT INTO `role_permission` VALUES (15,2,5);
INSERT INTO `role_permission` VALUES (50,3,10);
INSERT INTO `role_permission` VALUES (51,3,9);
INSERT INTO `role_permission` VALUES (52,3,8);
INSERT INTO `role_permission` VALUES (53,3,7);
INSERT INTO `role_permission` VALUES (54,3,6);
INSERT INTO `role_permission` VALUES (55,3,1);
INSERT INTO `role_permission` VALUES (56,5,11);
INSERT INTO `user` VALUES (1,'Gaki','3cc0aac98669495260ecd7e92251b412','rg6582EyA2XzZPFF3Z8/4g==');
INSERT INTO `user` VALUES (2,'Tinker','d818695f322d64dd81815bd603036d10','IEIfnqzPQYTS/b6SaW5gxw==');
INSERT INTO `user_role` VALUES (43,2,2);
INSERT INTO `user_role` VALUES (45,1,1);