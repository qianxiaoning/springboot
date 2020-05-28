/*
Navicat MySQL Data Transfer

Source Server         : qxn
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : enterprise

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-05-26 17:36:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `depts`
-- ----------------------------
DROP TABLE IF EXISTS `depts`;
CREATE TABLE `depts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `note` varchar(100) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `createdUser` varchar(20) DEFAULT NULL,
  `modifiedUser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of depts
-- ----------------------------
INSERT INTO `depts` VALUES ('2', 'dept-a', null, '1', 'dept-a ..', '2018-04-19 18:59:09', '2018-04-19 18:59:09', 'admin', 'admin');
INSERT INTO `depts` VALUES ('3', 'dept-b', null, '2', '1111', '2018-04-19 19:15:05', '2018-04-19 19:15:05', null, null);
INSERT INTO `depts` VALUES ('4', 'dept-aa', '2', '1', '', '2018-04-22 18:10:58', '2018-04-22 22:11:47', null, null);
INSERT INTO `depts` VALUES ('5', 'ss1', '2', '111', 'dd1', '2020-04-15 14:41:40', '2020-04-15 14:41:40', null, null);

-- ----------------------------
-- Table structure for `logs`
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `operation` varchar(30) DEFAULT NULL,
  `method` varchar(200) DEFAULT '',
  `params` varchar(5000) DEFAULT '',
  `operationTime` bigint(20) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=627 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES ('13', 'admin', 'operation', null, null, '118', '192.168.0.200', '2020-05-01 17:11:40');
INSERT INTO `logs` VALUES ('14', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImplupdateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=0, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '1', '192.168.0.200', '2020-05-02 19:55:23');
INSERT INTO `logs` VALUES ('16', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=0, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '142', '192.168.0.200', '2020-05-02 20:02:08');
INSERT INTO `logs` VALUES ('17', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=1, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '140', '192.168.0.200', '2020-05-02 20:02:42');
INSERT INTO `logs` VALUES ('18', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=0, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '131', '192.168.0.200', '2020-05-02 20:03:07');
INSERT INTO `logs` VALUES ('19', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[[serialVersionUID=1916229535898756044,,id=15,,username=null,,password=null,,salt=null,,email=null,,mobile=null,,valid=1,,deptId=null,,createdTime=null,,modifiedTime=null,,createdUser=null,,modifiedUser=null,]]', '136', '192.168.0.200', '2020-05-02 20:44:13');
INSERT INTO `logs` VALUES ('20', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=0, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '101', '192.168.0.200', '2020-05-02 20:50:18');
INSERT INTO `logs` VALUES ('21', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=1, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '156', '192.168.0.200', '2020-05-02 20:51:51');
INSERT INTO `logs` VALUES ('22', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=0, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '89', '192.168.0.200', '2020-05-02 20:54:37');
INSERT INTO `logs` VALUES ('23', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=1, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '131', '192.168.0.200', '2020-05-02 21:10:19');
INSERT INTO `logs` VALUES ('24', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=0, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '136', '192.168.0.200', '2020-05-02 21:11:45');
INSERT INTO `logs` VALUES ('25', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=1, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '136', '192.168.0.200', '2020-05-02 21:13:13');

-- ----------------------------
-- Table structure for `menus`
-- ----------------------------
DROP TABLE IF EXISTS `menus`;
CREATE TABLE `menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `note` varchar(100) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `permission` varchar(500) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `createdUser` varchar(20) DEFAULT NULL,
  `modifiedUser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menus
-- ----------------------------
INSERT INTO `menus` VALUES ('8', '系统管理', '请求路径', '1', '8', null, null, 'sys:list', '2017-07-12 15:15:59', '2017-07-21 11:16:00', 'admin', 'admin');
INSERT INTO `menus` VALUES ('24', '系统配置', '请求路径', '1', '24', null, '8', null, '2017-07-12 15:15:59', '2017-07-12 15:15:59', 'admin', 'admin');
INSERT INTO `menus` VALUES ('25', '日志管理', '请求路径', '1', '25', null, '8', null, '2017-07-12 15:15:59', '2017-07-12 15:15:59', 'admin', 'admin');
INSERT INTO `menus` VALUES ('45', '用户管理', ' ', '1', '45', null, '8', 'sys:user:view', '2017-07-12 15:15:59', '2017-07-21 17:36:01', 'admin', 'admin');
INSERT INTO `menus` VALUES ('46', '菜单管理', 'menu/listUI.do', '1', '46', null, '8', 'sys:menu:view', '2017-07-12 15:15:59', '2017-07-21 17:36:16', 'admin', 'admin');
INSERT INTO `menus` VALUES ('47', '角色管理', 'role/listUI.do', '1', '47', null, '8', 'sys:role:view', '2017-07-12 15:15:59', '2017-07-21 17:38:03', 'admin', 'admin');
INSERT INTO `menus` VALUES ('48', '组织管理', '请求路径', '1', '48', null, '8', 'sys:org:view', '2017-07-12 15:15:59', '2017-07-21 18:37:57', 'admin', 'admin');
INSERT INTO `menus` VALUES ('115', '查看', '', '2', '1', null, '46', 'sys:menu:view', '2017-07-13 16:33:41', '2017-07-21 11:09:05', null, null);
INSERT INTO `menus` VALUES ('116', '新增', '', '2', '2', null, '46', 'sys:menu:add', '2017-07-13 16:34:02', '2017-07-21 11:09:22', null, null);
INSERT INTO `menus` VALUES ('117', '修改', '', '2', '3', null, '46', 'sys:menu:update', '2017-07-13 16:34:25', '2017-07-21 11:09:45', null, null);
INSERT INTO `menus` VALUES ('118', '删除', '', '2', '4', null, '46', 'sys:menu:delete', '2017-07-13 16:34:46', '2017-07-21 11:10:12', null, null);
INSERT INTO `menus` VALUES ('119', '查看', '', '2', '1', null, '45', 'sys:user:view', '2017-07-13 16:35:05', '2017-07-21 11:12:46', null, null);
INSERT INTO `menus` VALUES ('120', '查看', '', '2', '1', null, '47', 'sys:role:view', '2017-07-13 16:35:26', '2017-07-21 11:13:43', null, null);
INSERT INTO `menus` VALUES ('126', '新增', '', '2', '2', null, '45', 'sys:user:add', '2017-07-21 11:11:34', '2017-07-21 11:11:34', null, null);
INSERT INTO `menus` VALUES ('127', '修改', '', '2', '3', null, '45', 'sys:user:update', '2017-07-21 11:11:56', '2017-07-21 11:11:56', null, null);
INSERT INTO `menus` VALUES ('128', '新增', '', '2', '2', null, '47', 'sys:role:add', '2017-07-21 11:14:24', '2017-07-21 11:14:24', null, null);
INSERT INTO `menus` VALUES ('129', '修改', '', '2', '3', null, '47', 'sys:role:update', '2017-07-21 11:14:48', '2017-07-21 11:14:48', null, null);
INSERT INTO `menus` VALUES ('130', '删除', '', '2', '4', null, '47', 'sys:role:delete', '2017-07-21 11:15:09', '2017-07-21 11:15:09', null, null);
INSERT INTO `menus` VALUES ('132', 'ww', 'ww', '2', '11', null, '8', 'ww', '2020-04-14 15:56:24', '2020-04-14 15:56:24', null, null);
INSERT INTO `menus` VALUES ('133', 'ee', 'ee', '2', '23', null, '8', 'ee', '2020-04-14 19:44:52', '2020-04-14 19:44:52', null, null);
INSERT INTO `menus` VALUES ('162', '禁用启用', 'updateValidByUserId', '2', '12', null, '45', 'sys:user:valid', '2020-05-08 16:42:46', '2020-05-08 16:42:46', null, null);
INSERT INTO `menus` VALUES ('163', '删除', 'log/doDeleteObject', '2', '11', null, '25', 'sys:log:delete', '2020-05-09 21:59:48', '2020-05-09 21:59:48', null, null);

-- ----------------------------
-- Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `createdUser` varchar(20) DEFAULT NULL,
  `modifiedUser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', '系统管理员', '系统管理员', '2017-07-13 17:44:11', '2020-05-09 22:09:07', 'admin', null);
INSERT INTO `roles` VALUES ('45', '运维经理', '运维经理..', '2018-04-22 20:51:43', '2018-04-22 20:51:43', null, null);
INSERT INTO `roles` VALUES ('46', 'aa', 'aa', '2020-04-15 21:42:01', '2020-04-15 21:42:03', null, null);
INSERT INTO `roles` VALUES ('50', 'q', 'q', '2020-04-20 17:25:55', '2020-04-20 17:25:55', null, null);
INSERT INTO `roles` VALUES ('51', 'w1', 'w1', '2020-04-20 21:49:43', '2020-04-21 14:07:08', null, null);
INSERT INTO `roles` VALUES ('52', 'r', 'r', '2020-04-20 22:18:00', '2020-05-24 15:59:29', null, null);

-- ----------------------------
-- Table structure for `roles_menus`
-- ----------------------------
DROP TABLE IF EXISTS `roles_menus`;
CREATE TABLE `roles_menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roles_id` int(11) DEFAULT NULL,
  `menus_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3391 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles_menus
-- ----------------------------
INSERT INTO `roles_menus` VALUES ('1250', '46', '8');
INSERT INTO `roles_menus` VALUES ('1251', '46', '24');
INSERT INTO `roles_menus` VALUES ('1252', '46', '25');
INSERT INTO `roles_menus` VALUES ('1253', '46', '45');
INSERT INTO `roles_menus` VALUES ('1254', '46', '119');
INSERT INTO `roles_menus` VALUES ('1255', '46', '126');
INSERT INTO `roles_menus` VALUES ('1256', '46', '127');
INSERT INTO `roles_menus` VALUES ('1257', '46', '46');
INSERT INTO `roles_menus` VALUES ('1258', '46', '115');
INSERT INTO `roles_menus` VALUES ('1259', '46', '116');
INSERT INTO `roles_menus` VALUES ('1260', '46', '117');
INSERT INTO `roles_menus` VALUES ('1261', '46', '118');
INSERT INTO `roles_menus` VALUES ('1262', '46', '47');
INSERT INTO `roles_menus` VALUES ('1263', '46', '120');
INSERT INTO `roles_menus` VALUES ('1264', '46', '128');
INSERT INTO `roles_menus` VALUES ('1265', '46', '129');
INSERT INTO `roles_menus` VALUES ('1266', '46', '130');
INSERT INTO `roles_menus` VALUES ('1267', '46', '48');
INSERT INTO `roles_menus` VALUES ('3368', '1', '8');
INSERT INTO `roles_menus` VALUES ('3369', '1', '24');
INSERT INTO `roles_menus` VALUES ('3370', '1', '25');
INSERT INTO `roles_menus` VALUES ('3371', '1', '45');
INSERT INTO `roles_menus` VALUES ('3372', '1', '119');
INSERT INTO `roles_menus` VALUES ('3373', '1', '126');
INSERT INTO `roles_menus` VALUES ('3374', '1', '127');
INSERT INTO `roles_menus` VALUES ('3375', '1', '46');
INSERT INTO `roles_menus` VALUES ('3376', '1', '115');
INSERT INTO `roles_menus` VALUES ('3377', '1', '116');
INSERT INTO `roles_menus` VALUES ('3378', '1', '117');
INSERT INTO `roles_menus` VALUES ('3379', '1', '118');
INSERT INTO `roles_menus` VALUES ('3380', '1', '47');
INSERT INTO `roles_menus` VALUES ('3381', '1', '120');
INSERT INTO `roles_menus` VALUES ('3382', '1', '128');
INSERT INTO `roles_menus` VALUES ('3383', '1', '129');
INSERT INTO `roles_menus` VALUES ('3384', '1', '130');
INSERT INTO `roles_menus` VALUES ('3385', '1', '48');
INSERT INTO `roles_menus` VALUES ('3386', '1', '163');
INSERT INTO `roles_menus` VALUES ('3387', '1', '162');
INSERT INTO `roles_menus` VALUES ('3388', '52', '162');
INSERT INTO `roles_menus` VALUES ('3389', '52', '8');
INSERT INTO `roles_menus` VALUES ('3390', '52', '45');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  `valid` tinyint(4) DEFAULT NULL COMMENT '0禁用 1启用 默认1',
  `deptId` int(11) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `createdUser` varchar(20) DEFAULT NULL,
  `modifiedUser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', '1232d1c04037827331320d0fb2277bd4', '482d929f-3163-44bf-b47d-4b497025cedb', 'admin@t.cn', '13624356789', '1', '3', null, '2020-05-24 20:44:37', null, 'admin');
INSERT INTO `users` VALUES ('2', 'zhangli', 'bdcf69375bdb532e50279b91eb00940d', '5e7cbd36-e897-4951-b42b-19809caf3caa', 'zhangli@t.cn', '13678909876', '0', '3', '2017-07-18 10:01:51', '2018-04-22 20:49:19', null, 'admin');
INSERT INTO `users` VALUES ('3', 'wangke', 'c5dc32ec66041aeddf432b3146bd2257', '5e3e1475-1ea9-4a6a-976e-b07545827139', 'wangke@t.cn', '18678900987', '1', '3', '2017-07-18 11:40:53', '2018-04-22 20:48:52', null, null);
INSERT INTO `users` VALUES ('4', 'zhangql', '+HBpqtPuj9KLBIpneR5X0A==', 'ed487fac-9952-45c9-acaa-21dab9c689cc', 'zhangql@t.cn', '13678909876', '1', '2', '2017-07-18 12:17:30', '2018-04-22 20:48:04', null, null);
INSERT INTO `users` VALUES ('5', 'fanwei', '1acab7425d6dfae670f26bd160518902', '34fbedb2-e135-4f8d-b595-24360edc348d', 'fanwei@t.cn', '13876545678', '1', '3', '2017-07-20 17:03:22', '2018-04-22 20:47:49', null, null);
INSERT INTO `users` VALUES ('6', 'wumei', '431ebdcccf3404787a144f9ba669a8e2', '8a14f46f-7a17-4dfe-85ab-08e63cb618ce', 'wumei@t.cn', '13567898765', '1', '2', '2017-07-21 10:57:40', '2018-04-22 20:46:49', null, null);
INSERT INTO `users` VALUES ('7', 'user-003', '689c673a0d8bda7ee795dd45a126ae96', '3faa1d2b-a99f-4ffb-9d29-0e71563258af', 't@t.com', '123', '1', '3', '2018-01-12 23:19:58', '2018-04-22 20:46:07', null, 'admin');
INSERT INTO `users` VALUES ('9', 'user-002', 'e10adc3949ba59abbe56e057f20f883e', null, 't@t.com', '123', '1', '3', '2018-01-12 23:20:31', '2018-04-22 20:45:55', null, null);
INSERT INTO `users` VALUES ('12', 'user-001', '5bf6593afd106aa544000d559f0c2241', '9528e727-2901-4746-8558-9010d9607da2', 't@t.com', '123', '1', '3', '2018-01-13 01:48:32', '2018-04-22 20:45:37', null, null);
INSERT INTO `users` VALUES ('13', 'user-c', '2630d8bd50c76abf001a9daceeae97e6', '30fff766-e245-4a93-9f5e-6eb2c2cec494', 't@t.com', '123456', '0', '3', '2018-01-13 02:01:56', '2018-04-22 20:43:58', null, 'admin');
INSERT INTO `users` VALUES ('15', 'user-b', '2ce586af95c6431112092f653659c85f', 'eaedbaee-d760-40e4-b71e-ccecf01b6187', 't@t.com', '123456', '0', '3', '2018-01-13 02:02:06', null, null, null);
INSERT INTO `users` VALUES ('16', 'user-a1', '11', '', '3@t.com11', '11', '1', '3', '2018-04-22 19:43:11', '2020-05-10 20:33:37', null, null);
INSERT INTO `users` VALUES ('21', 'a', 'a', null, '', '', '0', null, null, '2020-04-24 16:04:10', null, null);
INSERT INTO `users` VALUES ('22', 'qwe', '26b407e83726d73bf7e7217f2fcb831f', null, '1', '1', '1', '3', null, '2020-05-10 11:54:46', null, null);
INSERT INTO `users` VALUES ('23', 'r', '4b13c54fed55ac216995be19fc1bcb0a', null, '', '', '1', '3', null, '2020-05-10 11:57:10', null, null);
INSERT INTO `users` VALUES ('24', 'z', '5f2d0f0dfded66661bfe81729e66c62e', null, '', '', '1', '3', null, '2020-05-10 12:02:13', null, null);
INSERT INTO `users` VALUES ('25', 'qqw', '2ae24d2361ae5c47c3d5feeaecd01f6c', '5d96627c-0955-47e7-ac61-571bf727077c', '1', '1', '1', '3', '2020-05-10 19:48:15', '2020-05-24 17:39:44', null, 'qxn');
INSERT INTO `users` VALUES ('26', 'qxn', '9ea5f77c836ba1ae8f39495da4b9353d', '42877b42-8d56-4700-abc1-08c705ea4502', '', '', '1', '3', '2020-05-24 16:00:26', '2020-05-24 16:47:19', null, null);
INSERT INTO `users` VALUES ('27', 'qa', '679613e71b595ce4ab3d9db81d0efac0', 'e01de068-786d-41b6-b160-c6954087eb69', '1', '1', '1', '3', '2020-05-24 20:19:16', '2020-05-24 20:19:16', null, null);
INSERT INTO `users` VALUES ('28', 'qz', 'acd19e424c3092e27929c969816d6fcc', '51551686-4afa-4825-a7e7-fe44da385b80', '1', '1', '1', '3', '2020-05-24 20:21:06', '2020-05-24 20:21:06', null, null);
INSERT INTO `users` VALUES ('29', 'qx', 'a8886b29b2c294d4dac83c02702b4470', 'c546cb6c-1a82-4039-9f1c-51eab9b9aeef', '1', '1', '1', '3', '2020-05-24 20:25:11', '2020-05-24 20:25:11', null, null);
INSERT INTO `users` VALUES ('30', 'qc', 'cc5eb3b704d7168eaf7460327c5866e0', '8d2e6dcb-5ff2-4489-9185-9f7a3f7cf9d5', '1', '1', '0', '3', '2020-05-24 20:27:56', '2020-05-25 16:09:10', 'admin', 'admin');
INSERT INTO `users` VALUES ('31', 'qv', 'ea16280bb703ee2a65f2c02e9457af59', '7958b33e-7f86-4bb3-a338-f1ed5d0d75b4', '1', '1', '1', '3', '2020-05-24 20:29:35', '2020-05-24 20:29:35', 'admin', 'admin');

-- ----------------------------
-- Table structure for `users_roles`
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO `users_roles` VALUES ('65', '1', '1');
INSERT INTO `users_roles` VALUES ('73', '21', '45');
INSERT INTO `users_roles` VALUES ('74', '21', '1');
INSERT INTO `users_roles` VALUES ('77', '15', '51');
INSERT INTO `users_roles` VALUES ('78', '15', '52');
INSERT INTO `users_roles` VALUES ('79', '22', '45');
INSERT INTO `users_roles` VALUES ('80', '23', '45');
INSERT INTO `users_roles` VALUES ('81', '24', '45');
INSERT INTO `users_roles` VALUES ('91', '25', '45');
INSERT INTO `users_roles` VALUES ('96', '16', '45');
INSERT INTO `users_roles` VALUES ('100', '26', '1');
INSERT INTO `users_roles` VALUES ('101', '26', '52');
INSERT INTO `users_roles` VALUES ('102', '27', '1');
INSERT INTO `users_roles` VALUES ('103', '28', '1');
INSERT INTO `users_roles` VALUES ('104', '29', '1');
INSERT INTO `users_roles` VALUES ('105', '30', '1');
INSERT INTO `users_roles` VALUES ('106', '31', '1');
