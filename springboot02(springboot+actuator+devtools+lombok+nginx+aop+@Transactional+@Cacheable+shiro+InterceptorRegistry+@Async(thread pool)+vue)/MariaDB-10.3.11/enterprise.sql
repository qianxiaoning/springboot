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
INSERT INTO `logs` VALUES ('26', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=0, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '146', '192.168.0.200', '2020-05-02 21:14:15');
INSERT INTO `logs` VALUES ('27', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=1, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '135', '192.168.0.200', '2020-05-02 21:15:37');
INSERT INTO `logs` VALUES ('28', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=16, username=null, password=null, salt=null, email=null, mobile=null, valid=0, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '42', '192.168.0.200', '2020-05-02 21:16:53');
INSERT INTO `logs` VALUES ('29', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=0, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '141', '192.168.0.200', '2020-05-02 21:17:24');
INSERT INTO `logs` VALUES ('30', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=1, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '227', '192.168.0.200', '2020-05-02 21:18:46');
INSERT INTO `logs` VALUES ('31', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=0, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '128', '192.168.0.200', '2020-05-02 21:22:04');
INSERT INTO `logs` VALUES ('32', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=1, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '138', '192.168.0.200', '2020-05-02 21:26:30');
INSERT INTO `logs` VALUES ('33', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[15,0]', '1810', '192.168.0.200', '2020-05-02 21:33:34');
INSERT INTO `logs` VALUES ('34', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[15,admin,1]', '135', '192.168.0.200', '2020-05-02 21:44:51');
INSERT INTO `logs` VALUES ('35', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=15,username=admin,valid=0]', '130', '192.168.0.200', '2020-05-02 21:46:22');
INSERT INTO `logs` VALUES ('36', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=15,username=admin,valid=1]', '49', '192.168.0.200', '2020-05-03 14:45:35');
INSERT INTO `logs` VALUES ('37', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=15,username=admin,valid=0]', '400', '127.0.0.1', '2020-05-03 15:22:45');
INSERT INTO `logs` VALUES ('38', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.deleteObject', '[159]', '52', '127.0.0.1', '2020-05-04 15:18:43');
INSERT INTO `logs` VALUES ('39', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.deleteObject', '[158]', '5', '127.0.0.1', '2020-05-04 15:46:43');
INSERT INTO `logs` VALUES ('40', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.deleteObject', '[157]', '8', '127.0.0.1', '2020-05-04 15:50:15');
INSERT INTO `logs` VALUES ('41', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.deleteObject', '[156]', '9', '127.0.0.1', '2020-05-04 15:55:35');
INSERT INTO `logs` VALUES ('42', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '11', '127.0.0.1', '2020-05-04 18:15:28');
INSERT INTO `logs` VALUES ('43', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '127.0.0.1', '2020-05-04 18:15:31');
INSERT INTO `logs` VALUES ('44', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-04 18:42:37');
INSERT INTO `logs` VALUES ('45', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '55', '127.0.0.1', '2020-05-04 20:19:25');
INSERT INTO `logs` VALUES ('46', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '11', '127.0.0.1', '2020-05-04 20:30:23');
INSERT INTO `logs` VALUES ('47', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '127.0.0.1', '2020-05-04 20:30:38');
INSERT INTO `logs` VALUES ('48', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '10', '127.0.0.1', '2020-05-04 21:28:37');
INSERT INTO `logs` VALUES ('49', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-04 21:28:38');
INSERT INTO `logs` VALUES ('50', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-04 21:28:41');
INSERT INTO `logs` VALUES ('51', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-04 21:28:43');
INSERT INTO `logs` VALUES ('52', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-04 21:28:45');
INSERT INTO `logs` VALUES ('53', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-04 21:28:49');
INSERT INTO `logs` VALUES ('54', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '12', '127.0.0.1', '2020-05-04 21:37:30');
INSERT INTO `logs` VALUES ('55', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-04 21:37:31');
INSERT INTO `logs` VALUES ('56', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '18', '127.0.0.1', '2020-05-05 20:43:54');
INSERT INTO `logs` VALUES ('57', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '127.0.0.1', '2020-05-05 20:44:10');
INSERT INTO `logs` VALUES ('58', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '127.0.0.1', '2020-05-05 20:57:12');
INSERT INTO `logs` VALUES ('59', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-05 21:14:14');
INSERT INTO `logs` VALUES ('60', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '127.0.0.1', '2020-05-05 21:58:29');
INSERT INTO `logs` VALUES ('61', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '127.0.0.1', '2020-05-06 15:36:21');
INSERT INTO `logs` VALUES ('62', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-06 15:36:22');
INSERT INTO `logs` VALUES ('63', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '13', '0:0:0:0:0:0:0:1', '2020-05-06 18:31:37');
INSERT INTO `logs` VALUES ('64', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-06 18:31:41');
INSERT INTO `logs` VALUES ('65', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '0:0:0:0:0:0:0:1', '2020-05-06 18:41:01');
INSERT INTO `logs` VALUES ('66', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-06 18:41:02');
INSERT INTO `logs` VALUES ('67', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '127.0.0.1', '2020-05-06 20:44:37');
INSERT INTO `logs` VALUES ('68', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-06 20:44:37');
INSERT INTO `logs` VALUES ('69', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-06 20:47:27');
INSERT INTO `logs` VALUES ('70', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-06 20:58:01');
INSERT INTO `logs` VALUES ('71', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '14', '0:0:0:0:0:0:0:1', '2020-05-08 15:09:48');
INSERT INTO `logs` VALUES ('72', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '127.0.0.1', '2020-05-08 15:09:50');
INSERT INTO `logs` VALUES ('73', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-08 15:11:25');
INSERT INTO `logs` VALUES ('74', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '127.0.0.1', '2020-05-08 15:12:09');
INSERT INTO `logs` VALUES ('75', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-08 15:13:18');
INSERT INTO `logs` VALUES ('76', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-08 15:15:18');
INSERT INTO `logs` VALUES ('77', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-08 15:17:47');
INSERT INTO `logs` VALUES ('78', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-08 15:18:34');
INSERT INTO `logs` VALUES ('79', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '0:0:0:0:0:0:0:1', '2020-05-08 15:19:19');
INSERT INTO `logs` VALUES ('80', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-08 15:19:21');
INSERT INTO `logs` VALUES ('81', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '0:0:0:0:0:0:0:1', '2020-05-08 15:20:02');
INSERT INTO `logs` VALUES ('82', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '0:0:0:0:0:0:0:1', '2020-05-08 16:41:02');
INSERT INTO `logs` VALUES ('83', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-08 16:42:46');
INSERT INTO `logs` VALUES ('84', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '13', '127.0.0.1', '2020-05-09 14:20:07');
INSERT INTO `logs` VALUES ('85', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '0:0:0:0:0:0:0:1', '2020-05-09 20:50:00');
INSERT INTO `logs` VALUES ('86', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '0:0:0:0:0:0:0:1', '2020-05-09 20:50:01');
INSERT INTO `logs` VALUES ('87', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '0:0:0:0:0:0:0:1', '2020-05-09 21:28:25');
INSERT INTO `logs` VALUES ('88', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '127.0.0.1', '2020-05-09 21:29:42');
INSERT INTO `logs` VALUES ('89', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '127.0.0.1', '2020-05-09 21:36:51');
INSERT INTO `logs` VALUES ('90', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '15', '0:0:0:0:0:0:0:1', '2020-05-09 21:38:09');
INSERT INTO `logs` VALUES ('91', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '127.0.0.1', '2020-05-09 21:40:59');
INSERT INTO `logs` VALUES ('92', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '10', '127.0.0.1', '2020-05-09 21:52:58');
INSERT INTO `logs` VALUES ('93', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-09 21:54:50');
INSERT INTO `logs` VALUES ('94', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-09 21:59:49');
INSERT INTO `logs` VALUES ('95', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-09 22:00:18');
INSERT INTO `logs` VALUES ('96', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '127.0.0.1', '2020-05-09 22:14:02');
INSERT INTO `logs` VALUES ('97', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-09 22:24:24');
INSERT INTO `logs` VALUES ('98', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '127.0.0.1', '2020-05-09 23:08:04');
INSERT INTO `logs` VALUES ('99', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-09 23:08:07');
INSERT INTO `logs` VALUES ('100', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-09 23:08:52');
INSERT INTO `logs` VALUES ('101', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-09 23:09:04');
INSERT INTO `logs` VALUES ('102', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '127.0.0.1', '2020-05-09 23:30:36');
INSERT INTO `logs` VALUES ('103', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '127.0.0.1', '2020-05-09 23:30:45');
INSERT INTO `logs` VALUES ('104', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '127.0.0.1', '2020-05-09 23:30:50');
INSERT INTO `logs` VALUES ('105', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '0:0:0:0:0:0:0:1', '2020-05-09 23:32:30');
INSERT INTO `logs` VALUES ('106', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '0:0:0:0:0:0:0:1', '2020-05-10 11:23:59');
INSERT INTO `logs` VALUES ('107', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '12', '0:0:0:0:0:0:0:1', '2020-05-10 11:29:19');
INSERT INTO `logs` VALUES ('108', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '0:0:0:0:0:0:0:1', '2020-05-10 11:29:20');
INSERT INTO `logs` VALUES ('109', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '10', '127.0.0.1', '2020-05-10 11:37:01');
INSERT INTO `logs` VALUES ('110', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-10 11:39:14');
INSERT INTO `logs` VALUES ('111', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-10 11:39:15');
INSERT INTO `logs` VALUES ('112', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-10 11:39:17');
INSERT INTO `logs` VALUES ('114', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-10 11:39:21');
INSERT INTO `logs` VALUES ('115', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '0:0:0:0:0:0:0:1', '2020-05-10 11:48:37');
INSERT INTO `logs` VALUES ('116', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '0:0:0:0:0:0:0:1', '2020-05-10 11:51:04');
INSERT INTO `logs` VALUES ('117', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=16,username=admin,valid=1]', '59', '0:0:0:0:0:0:0:1', '2020-05-10 11:51:30');
INSERT INTO `logs` VALUES ('118', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '10', '127.0.0.1', '2020-05-10 12:01:55');
INSERT INTO `logs` VALUES ('119', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '12', '127.0.0.1', '2020-05-10 19:47:46');
INSERT INTO `logs` VALUES ('120', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-10 19:48:48');
INSERT INTO `logs` VALUES ('121', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=0]', '130', '0:0:0:0:0:0:0:1', '2020-05-10 19:49:07');
INSERT INTO `logs` VALUES ('122', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=0]', '1', '0:0:0:0:0:0:0:1', '2020-05-10 19:49:28');
INSERT INTO `logs` VALUES ('123', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=0]', '2', '0:0:0:0:0:0:0:1', '2020-05-10 19:50:11');
INSERT INTO `logs` VALUES ('124', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=0]', '0', '0:0:0:0:0:0:0:1', '2020-05-10 19:52:36');
INSERT INTO `logs` VALUES ('125', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '127.0.0.1', '2020-05-10 19:55:43');
INSERT INTO `logs` VALUES ('126', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=0]', '1', '127.0.0.1', '2020-05-10 19:56:39');
INSERT INTO `logs` VALUES ('127', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '0:0:0:0:0:0:0:1', '2020-05-10 19:58:52');
INSERT INTO `logs` VALUES ('128', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=1]', '132', '127.0.0.1', '2020-05-10 19:59:14');
INSERT INTO `logs` VALUES ('129', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-10 19:59:29');
INSERT INTO `logs` VALUES ('130', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-10 19:59:35');
INSERT INTO `logs` VALUES ('131', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-10 19:59:45');
INSERT INTO `logs` VALUES ('132', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '127.0.0.1', '2020-05-10 20:04:03');
INSERT INTO `logs` VALUES ('133', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=0]', '137', '127.0.0.1', '2020-05-10 20:04:15');
INSERT INTO `logs` VALUES ('134', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '0:0:0:0:0:0:0:1', '2020-05-10 20:05:08');
INSERT INTO `logs` VALUES ('135', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=1]', '133', '127.0.0.1', '2020-05-10 20:05:16');
INSERT INTO `logs` VALUES ('136', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=0]', '146', '0:0:0:0:0:0:0:1', '2020-05-10 20:05:42');
INSERT INTO `logs` VALUES ('138', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-10 20:08:16');
INSERT INTO `logs` VALUES ('140', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '39', '0:0:0:0:0:0:0:1', '2020-05-10 20:09:10');
INSERT INTO `logs` VALUES ('141', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=0]', '131', '127.0.0.1', '2020-05-10 20:09:21');
INSERT INTO `logs` VALUES ('142', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '11', '0:0:0:0:0:0:0:1', '2020-05-10 20:10:05');
INSERT INTO `logs` VALUES ('143', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=1]', '131', '127.0.0.1', '2020-05-10 20:10:34');
INSERT INTO `logs` VALUES ('144', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-10 20:10:45');
INSERT INTO `logs` VALUES ('145', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=1]', '0', '0:0:0:0:0:0:0:1', '2020-05-10 20:10:48');
INSERT INTO `logs` VALUES ('146', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=1]', '1', '0:0:0:0:0:0:0:1', '2020-05-10 20:10:51');
INSERT INTO `logs` VALUES ('147', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-10 20:10:56');
INSERT INTO `logs` VALUES ('148', 'admin', '修改用户', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateUserDeptRoleIdsById', '[UserDeptRoleIds(depts=null, roleIds=[45])]', '179', '0:0:0:0:0:0:0:1', '2020-05-10 20:11:07');
INSERT INTO `logs` VALUES ('149', 'admin', '修改用户', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateUserDeptRoleIdsById', '[UserDeptRoleIds(depts=null, roleIds=[45])]', '251', '127.0.0.1', '2020-05-10 20:12:07');
INSERT INTO `logs` VALUES ('150', 'admin', '修改用户', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateUserDeptRoleIdsById', '[UserDeptRoleIds(depts=null, roleIds=[45])]', '227', '127.0.0.1', '2020-05-10 20:12:13');
INSERT INTO `logs` VALUES ('151', 'admin', '修改用户', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateUserDeptRoleIdsById', '[UserDeptRoleIds(depts=null, roleIds=[45])]', '210', '127.0.0.1', '2020-05-10 20:12:19');
INSERT INTO `logs` VALUES ('152', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-10 20:12:34');
INSERT INTO `logs` VALUES ('153', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '12', '0:0:0:0:0:0:0:1', '2020-05-10 20:12:51');
INSERT INTO `logs` VALUES ('154', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-10 20:12:54');
INSERT INTO `logs` VALUES ('155', 'admin', '修改用户', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateUserDeptRoleIdsById', '[UserDeptRoleIds(depts=null, roleIds=[45])]', '259', '127.0.0.1', '2020-05-10 20:13:01');
INSERT INTO `logs` VALUES ('156', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '0:0:0:0:0:0:0:1', '2020-05-10 20:13:17');
INSERT INTO `logs` VALUES ('157', 'admin', '修改用户', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateUserDeptRoleIdsById', '[UserDeptRoleIds(depts=null, roleIds=[45])]', '184', '127.0.0.1', '2020-05-10 20:13:27');
INSERT INTO `logs` VALUES ('158', 'admin', '修改用户', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateUserDeptRoleIdsById', '[UserDeptRoleIds(depts=null, roleIds=[45])]', '140', '0:0:0:0:0:0:0:1', '2020-05-10 20:26:49');
INSERT INTO `logs` VALUES ('159', 'admin', '修改用户', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateUserDeptRoleIdsById', '[UserDeptRoleIds(depts=null, roleIds=[45])]', '148', '0:0:0:0:0:0:0:1', '2020-05-10 20:26:59');
INSERT INTO `logs` VALUES ('160', 'admin', '修改用户', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateUserDeptRoleIdsById', '[UserDeptRoleIds(depts=null, roleIds=[45])]', '242', '0:0:0:0:0:0:0:1', '2020-05-10 20:27:11');
INSERT INTO `logs` VALUES ('161', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-10 20:29:37');
INSERT INTO `logs` VALUES ('162', 'admin', '修改用户', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateUserDeptRoleIdsById', '[UserDeptRoleIds(depts=null, roleIds=[45])]', '281', '0:0:0:0:0:0:0:1', '2020-05-10 20:29:46');
INSERT INTO `logs` VALUES ('163', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '0:0:0:0:0:0:0:1', '2020-05-10 20:31:23');
INSERT INTO `logs` VALUES ('164', 'admin', '修改用户', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateUserDeptRoleIdsById', '[UserDeptRoleIds(depts=null, roleIds=[45])]', '239', '0:0:0:0:0:0:0:1', '2020-05-10 20:31:31');
INSERT INTO `logs` VALUES ('165', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '127.0.0.1', '2020-05-10 20:31:51');
INSERT INTO `logs` VALUES ('166', 'admin', '修改用户', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateUserDeptRoleIdsById', '[UserDeptRoleIds(depts=null, roleIds=[45])]', '246', '0:0:0:0:0:0:0:1', '2020-05-10 20:31:59');
INSERT INTO `logs` VALUES ('167', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-10 20:32:03');
INSERT INTO `logs` VALUES ('168', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '14', '127.0.0.1', '2020-05-10 20:33:30');
INSERT INTO `logs` VALUES ('169', 'admin', '修改用户', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateUserDeptRoleIdsById', '[UserDeptRoleIds(depts=null, roleIds=[45])]', '412', '0:0:0:0:0:0:0:1', '2020-05-10 20:33:37');
INSERT INTO `logs` VALUES ('170', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-10 20:34:08');
INSERT INTO `logs` VALUES ('171', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=0]', '40', '0:0:0:0:0:0:0:1', '2020-05-10 20:34:11');
INSERT INTO `logs` VALUES ('172', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=1]', '48', '0:0:0:0:0:0:0:1', '2020-05-10 20:34:12');
INSERT INTO `logs` VALUES ('173', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=0]', '133', '127.0.0.1', '2020-05-10 20:51:13');
INSERT INTO `logs` VALUES ('174', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=1]', '123', '0:0:0:0:0:0:0:1', '2020-05-10 20:51:28');
INSERT INTO `logs` VALUES ('175', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '12', '0:0:0:0:0:0:0:1', '2020-05-12 17:20:38');
INSERT INTO `logs` VALUES ('176', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '0:0:0:0:0:0:0:1', '2020-05-12 17:20:40');
INSERT INTO `logs` VALUES ('177', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '0:0:0:0:0:0:0:1', '2020-05-12 17:20:40');
INSERT INTO `logs` VALUES ('178', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-12 17:22:59');
INSERT INTO `logs` VALUES ('179', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '0:0:0:0:0:0:0:1', '2020-05-12 17:23:00');
INSERT INTO `logs` VALUES ('180', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '10', '0:0:0:0:0:0:0:1', '2020-05-12 17:51:54');
INSERT INTO `logs` VALUES ('181', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '0:0:0:0:0:0:0:1', '2020-05-12 17:51:55');
INSERT INTO `logs` VALUES ('182', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '0:0:0:0:0:0:0:1', '2020-05-12 20:38:13');
INSERT INTO `logs` VALUES ('183', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '57', '0:0:0:0:0:0:0:1', '2020-05-12 20:43:57');
INSERT INTO `logs` VALUES ('184', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-12 20:44:43');
INSERT INTO `logs` VALUES ('185', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '127.0.0.1', '2020-05-12 20:47:47');
INSERT INTO `logs` VALUES ('186', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-12 20:47:57');
INSERT INTO `logs` VALUES ('187', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-12 20:47:58');
INSERT INTO `logs` VALUES ('188', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '127.0.0.1', '2020-05-13 13:10:56');
INSERT INTO `logs` VALUES ('189', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '15', '0:0:0:0:0:0:0:1', '2020-05-15 13:19:13');
INSERT INTO `logs` VALUES ('190', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-15 13:19:14');
INSERT INTO `logs` VALUES ('191', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '0:0:0:0:0:0:0:1', '2020-05-15 13:19:20');
INSERT INTO `logs` VALUES ('196', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-15 13:23:09');
INSERT INTO `logs` VALUES ('197', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '127.0.0.1', '2020-05-15 21:04:58');
INSERT INTO `logs` VALUES ('198', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-15 21:05:11');
INSERT INTO `logs` VALUES ('199', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '0:0:0:0:0:0:0:1', '2020-05-15 21:05:12');
INSERT INTO `logs` VALUES ('200', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '127.0.0.1', '2020-05-15 21:05:15');
INSERT INTO `logs` VALUES ('201', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-15 21:05:53');
INSERT INTO `logs` VALUES ('202', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-15 21:06:03');
INSERT INTO `logs` VALUES ('203', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-15 21:06:10');
INSERT INTO `logs` VALUES ('204', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-15 21:06:11');
INSERT INTO `logs` VALUES ('205', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-15 21:06:13');
INSERT INTO `logs` VALUES ('206', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-15 21:06:31');
INSERT INTO `logs` VALUES ('207', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-15 21:06:48');
INSERT INTO `logs` VALUES ('208', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '127.0.0.1', '2020-05-15 21:06:49');
INSERT INTO `logs` VALUES ('209', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-15 21:06:50');
INSERT INTO `logs` VALUES ('210', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-15 21:06:56');
INSERT INTO `logs` VALUES ('211', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '0:0:0:0:0:0:0:1', '2020-05-15 21:55:01');
INSERT INTO `logs` VALUES ('212', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-15 21:55:07');
INSERT INTO `logs` VALUES ('213', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-15 21:55:08');
INSERT INTO `logs` VALUES ('214', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-15 21:55:17');
INSERT INTO `logs` VALUES ('215', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-15 21:55:19');
INSERT INTO `logs` VALUES ('216', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-15 21:55:20');
INSERT INTO `logs` VALUES ('217', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-15 21:55:20');
INSERT INTO `logs` VALUES ('218', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-15 21:55:22');
INSERT INTO `logs` VALUES ('219', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-15 21:55:23');
INSERT INTO `logs` VALUES ('220', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-15 21:55:25');
INSERT INTO `logs` VALUES ('221', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-15 21:56:03');
INSERT INTO `logs` VALUES ('222', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-15 21:56:04');
INSERT INTO `logs` VALUES ('223', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-15 21:56:06');
INSERT INTO `logs` VALUES ('224', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-15 21:56:31');
INSERT INTO `logs` VALUES ('225', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-15 21:56:32');
INSERT INTO `logs` VALUES ('226', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-15 21:56:37');
INSERT INTO `logs` VALUES ('227', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-15 21:56:38');
INSERT INTO `logs` VALUES ('228', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-15 21:56:46');
INSERT INTO `logs` VALUES ('229', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-15 21:56:50');
INSERT INTO `logs` VALUES ('230', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-15 22:05:11');
INSERT INTO `logs` VALUES ('231', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-15 22:05:12');
INSERT INTO `logs` VALUES ('232', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-15 22:05:17');
INSERT INTO `logs` VALUES ('233', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-15 22:05:18');
INSERT INTO `logs` VALUES ('234', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-15 22:06:38');
INSERT INTO `logs` VALUES ('235', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-15 22:06:39');
INSERT INTO `logs` VALUES ('236', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '52', '0:0:0:0:0:0:0:1', '2020-05-15 22:12:58');
INSERT INTO `logs` VALUES ('237', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '127.0.0.1', '2020-05-15 22:13:00');
INSERT INTO `logs` VALUES ('238', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '127.0.0.1', '2020-05-15 22:13:01');
INSERT INTO `logs` VALUES ('239', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '127.0.0.1', '2020-05-15 22:13:02');
INSERT INTO `logs` VALUES ('240', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '0:0:0:0:0:0:0:1', '2020-05-15 22:13:08');
INSERT INTO `logs` VALUES ('241', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-15 22:13:09');
INSERT INTO `logs` VALUES ('242', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-15 22:13:11');
INSERT INTO `logs` VALUES ('243', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-15 22:13:14');
INSERT INTO `logs` VALUES ('244', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-15 22:13:16');
INSERT INTO `logs` VALUES ('245', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '127.0.0.1', '2020-05-15 22:23:08');
INSERT INTO `logs` VALUES ('246', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '127.0.0.1', '2020-05-15 22:23:10');
INSERT INTO `logs` VALUES ('247', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '0:0:0:0:0:0:0:1', '2020-05-15 22:23:24');
INSERT INTO `logs` VALUES ('248', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '0:0:0:0:0:0:0:1', '2020-05-15 22:23:26');
INSERT INTO `logs` VALUES ('249', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-15 22:23:26');
INSERT INTO `logs` VALUES ('250', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '127.0.0.1', '2020-05-15 22:23:35');
INSERT INTO `logs` VALUES ('251', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-15 22:23:36');
INSERT INTO `logs` VALUES ('252', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-15 22:23:38');
INSERT INTO `logs` VALUES ('253', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '0:0:0:0:0:0:0:1', '2020-05-15 22:28:35');
INSERT INTO `logs` VALUES ('254', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-15 22:28:36');
INSERT INTO `logs` VALUES ('255', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-15 22:28:37');
INSERT INTO `logs` VALUES ('256', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-15 22:28:40');
INSERT INTO `logs` VALUES ('257', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-15 22:28:41');
INSERT INTO `logs` VALUES ('258', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-15 22:28:42');
INSERT INTO `logs` VALUES ('259', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-15 22:28:43');
INSERT INTO `logs` VALUES ('260', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '0:0:0:0:0:0:0:1', '2020-05-15 22:28:44');
INSERT INTO `logs` VALUES ('261', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-15 22:28:51');
INSERT INTO `logs` VALUES ('262', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-15 22:28:52');
INSERT INTO `logs` VALUES ('263', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '127.0.0.1', '2020-05-15 22:39:46');
INSERT INTO `logs` VALUES ('264', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-15 22:39:49');
INSERT INTO `logs` VALUES ('265', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '127.0.0.1', '2020-05-16 15:50:31');
INSERT INTO `logs` VALUES ('266', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '127.0.0.1', '2020-05-16 15:50:45');
INSERT INTO `logs` VALUES ('267', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '0:0:0:0:0:0:0:1', '2020-05-16 15:51:17');
INSERT INTO `logs` VALUES ('268', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '0:0:0:0:0:0:0:1', '2020-05-16 15:51:18');
INSERT INTO `logs` VALUES ('269', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-16 15:51:21');
INSERT INTO `logs` VALUES ('270', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-16 15:59:30');
INSERT INTO `logs` VALUES ('271', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-16 15:59:31');
INSERT INTO `logs` VALUES ('272', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-16 16:04:58');
INSERT INTO `logs` VALUES ('273', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-16 16:04:58');
INSERT INTO `logs` VALUES ('274', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-16 16:05:00');
INSERT INTO `logs` VALUES ('275', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-16 16:05:00');
INSERT INTO `logs` VALUES ('276', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-16 16:05:01');
INSERT INTO `logs` VALUES ('277', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-16 16:05:26');
INSERT INTO `logs` VALUES ('278', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-16 16:05:27');
INSERT INTO `logs` VALUES ('279', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-16 16:05:28');
INSERT INTO `logs` VALUES ('280', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-16 16:05:40');
INSERT INTO `logs` VALUES ('281', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-16 16:06:11');
INSERT INTO `logs` VALUES ('282', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-16 16:06:12');
INSERT INTO `logs` VALUES ('283', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '15', '127.0.0.1', '2020-05-16 16:10:33');
INSERT INTO `logs` VALUES ('284', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-16 16:13:00');
INSERT INTO `logs` VALUES ('285', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '0:0:0:0:0:0:0:1', '2020-05-16 16:19:45');
INSERT INTO `logs` VALUES ('286', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-16 16:21:19');
INSERT INTO `logs` VALUES ('287', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-16 16:23:10');
INSERT INTO `logs` VALUES ('288', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-16 16:23:15');
INSERT INTO `logs` VALUES ('289', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '127.0.0.1', '2020-05-16 17:36:25');
INSERT INTO `logs` VALUES ('290', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '0:0:0:0:0:0:0:1', '2020-05-16 17:36:37');
INSERT INTO `logs` VALUES ('291', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '0:0:0:0:0:0:0:1', '2020-05-16 17:36:39');
INSERT INTO `logs` VALUES ('292', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-16 17:36:41');
INSERT INTO `logs` VALUES ('293', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-16 17:41:52');
INSERT INTO `logs` VALUES ('294', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '127.0.0.1', '2020-05-16 17:58:26');
INSERT INTO `logs` VALUES ('295', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-16 17:58:26');
INSERT INTO `logs` VALUES ('296', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-16 17:58:44');
INSERT INTO `logs` VALUES ('297', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-16 17:58:55');
INSERT INTO `logs` VALUES ('298', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-16 18:04:03');
INSERT INTO `logs` VALUES ('299', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-16 18:05:19');
INSERT INTO `logs` VALUES ('300', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-16 18:05:22');
INSERT INTO `logs` VALUES ('301', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-16 18:06:19');
INSERT INTO `logs` VALUES ('302', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-16 18:06:25');
INSERT INTO `logs` VALUES ('303', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-16 18:06:47');
INSERT INTO `logs` VALUES ('304', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-16 18:15:21');
INSERT INTO `logs` VALUES ('305', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '12', '127.0.0.1', '2020-05-16 19:40:10');
INSERT INTO `logs` VALUES ('306', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-16 19:40:11');
INSERT INTO `logs` VALUES ('307', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-16 19:40:14');
INSERT INTO `logs` VALUES ('308', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '127.0.0.1', '2020-05-16 22:22:41');
INSERT INTO `logs` VALUES ('309', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-16 22:22:49');
INSERT INTO `logs` VALUES ('310', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-16 22:22:54');
INSERT INTO `logs` VALUES ('311', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-16 22:23:00');
INSERT INTO `logs` VALUES ('312', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-16 22:23:04');
INSERT INTO `logs` VALUES ('313', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-16 22:23:05');
INSERT INTO `logs` VALUES ('314', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-16 22:24:27');
INSERT INTO `logs` VALUES ('315', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-16 22:24:30');
INSERT INTO `logs` VALUES ('316', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-16 22:35:24');
INSERT INTO `logs` VALUES ('317', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-16 22:35:24');
INSERT INTO `logs` VALUES ('318', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '13', '127.0.0.1', '2020-05-16 22:49:30');
INSERT INTO `logs` VALUES ('319', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '127.0.0.1', '2020-05-16 22:50:48');
INSERT INTO `logs` VALUES ('320', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '0:0:0:0:0:0:0:1', '2020-05-17 13:58:59');
INSERT INTO `logs` VALUES ('321', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '59', '127.0.0.1', '2020-05-17 14:30:50');
INSERT INTO `logs` VALUES ('322', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '0:0:0:0:0:0:0:1', '2020-05-17 17:35:33');
INSERT INTO `logs` VALUES ('323', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-17 17:53:56');
INSERT INTO `logs` VALUES ('324', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '127.0.0.1', '2020-05-17 17:53:59');
INSERT INTO `logs` VALUES ('325', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '17', '192.168.0.107', '2020-05-17 17:55:12');
INSERT INTO `logs` VALUES ('326', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-17 17:55:18');
INSERT INTO `logs` VALUES ('327', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '15', '192.168.0.107', '2020-05-17 18:08:27');
INSERT INTO `logs` VALUES ('328', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '192.168.0.107', '2020-05-17 18:08:32');
INSERT INTO `logs` VALUES ('329', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '12', '192.168.0.107', '2020-05-17 18:10:30');
INSERT INTO `logs` VALUES ('330', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '192.168.0.107', '2020-05-17 21:56:56');
INSERT INTO `logs` VALUES ('331', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '192.168.0.107', '2020-05-17 21:57:01');
INSERT INTO `logs` VALUES ('332', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '10', '192.168.0.107', '2020-05-17 21:57:18');
INSERT INTO `logs` VALUES ('333', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '192.168.0.107', '2020-05-17 21:57:19');
INSERT INTO `logs` VALUES ('334', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '192.168.0.107', '2020-05-17 21:57:20');
INSERT INTO `logs` VALUES ('335', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '11', '192.168.0.107', '2020-05-17 21:57:21');
INSERT INTO `logs` VALUES ('336', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '192.168.0.107', '2020-05-17 21:57:22');
INSERT INTO `logs` VALUES ('337', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '192.168.0.107', '2020-05-17 21:57:22');
INSERT INTO `logs` VALUES ('338', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '192.168.0.107', '2020-05-17 21:57:23');
INSERT INTO `logs` VALUES ('339', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '192.168.0.107', '2020-05-17 21:57:23');
INSERT INTO `logs` VALUES ('340', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '192.168.0.107', '2020-05-17 21:57:24');
INSERT INTO `logs` VALUES ('341', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '192.168.0.107', '2020-05-17 21:57:24');
INSERT INTO `logs` VALUES ('342', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '192.168.0.107', '2020-05-17 21:57:59');
INSERT INTO `logs` VALUES ('343', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 21:58:02');
INSERT INTO `logs` VALUES ('344', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 21:58:03');
INSERT INTO `logs` VALUES ('345', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 21:58:04');
INSERT INTO `logs` VALUES ('346', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-17 21:58:05');
INSERT INTO `logs` VALUES ('347', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 21:58:06');
INSERT INTO `logs` VALUES ('348', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '192.168.0.107', '2020-05-17 21:58:07');
INSERT INTO `logs` VALUES ('349', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-17 21:58:08');
INSERT INTO `logs` VALUES ('350', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-17 21:58:09');
INSERT INTO `logs` VALUES ('351', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 21:58:10');
INSERT INTO `logs` VALUES ('352', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 21:58:10');
INSERT INTO `logs` VALUES ('353', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 21:58:33');
INSERT INTO `logs` VALUES ('354', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-17 21:58:34');
INSERT INTO `logs` VALUES ('355', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 21:58:38');
INSERT INTO `logs` VALUES ('356', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 21:58:39');
INSERT INTO `logs` VALUES ('357', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '192.168.0.107', '2020-05-17 21:58:39');
INSERT INTO `logs` VALUES ('358', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 21:58:40');
INSERT INTO `logs` VALUES ('359', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 21:58:41');
INSERT INTO `logs` VALUES ('360', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 22:00:16');
INSERT INTO `logs` VALUES ('361', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-17 22:01:05');
INSERT INTO `logs` VALUES ('362', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 22:01:05');
INSERT INTO `logs` VALUES ('363', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '14', '192.168.0.107', '2020-05-17 22:02:33');
INSERT INTO `logs` VALUES ('364', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '192.168.0.107', '2020-05-17 22:02:47');
INSERT INTO `logs` VALUES ('365', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '12', '192.168.0.107', '2020-05-17 22:02:49');
INSERT INTO `logs` VALUES ('366', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '192.168.0.107', '2020-05-17 22:07:59');
INSERT INTO `logs` VALUES ('367', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-17 22:08:00');
INSERT INTO `logs` VALUES ('368', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-17 22:08:01');
INSERT INTO `logs` VALUES ('369', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-17 22:08:02');
INSERT INTO `logs` VALUES ('370', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '192.168.0.107', '2020-05-17 22:08:07');
INSERT INTO `logs` VALUES ('371', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 22:08:08');
INSERT INTO `logs` VALUES ('372', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 22:08:10');
INSERT INTO `logs` VALUES ('373', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '192.168.0.107', '2020-05-17 22:09:32');
INSERT INTO `logs` VALUES ('374', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '192.168.0.107', '2020-05-17 22:10:42');
INSERT INTO `logs` VALUES ('375', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 22:10:43');
INSERT INTO `logs` VALUES ('376', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 22:10:46');
INSERT INTO `logs` VALUES ('377', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 22:11:28');
INSERT INTO `logs` VALUES ('378', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-17 22:16:12');
INSERT INTO `logs` VALUES ('379', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 22:16:13');
INSERT INTO `logs` VALUES ('380', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-17 22:16:42');
INSERT INTO `logs` VALUES ('381', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-17 22:16:42');
INSERT INTO `logs` VALUES ('382', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-17 22:16:47');
INSERT INTO `logs` VALUES ('383', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '192.168.0.107', '2020-05-17 22:18:38');
INSERT INTO `logs` VALUES ('384', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-17 22:18:39');
INSERT INTO `logs` VALUES ('385', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '192.168.0.107', '2020-05-17 22:18:40');
INSERT INTO `logs` VALUES ('386', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '10', '192.168.0.107', '2020-05-17 22:18:57');
INSERT INTO `logs` VALUES ('387', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '192.168.0.107', '2020-05-17 22:19:01');
INSERT INTO `logs` VALUES ('388', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-17 22:21:02');
INSERT INTO `logs` VALUES ('389', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '192.168.0.107', '2020-05-17 22:29:13');
INSERT INTO `logs` VALUES ('390', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 22:29:13');
INSERT INTO `logs` VALUES ('391', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 22:29:18');
INSERT INTO `logs` VALUES ('392', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '14', '192.168.0.107', '2020-05-17 22:33:06');
INSERT INTO `logs` VALUES ('393', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '192.168.0.107', '2020-05-17 22:33:11');
INSERT INTO `logs` VALUES ('394', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '192.168.0.107', '2020-05-17 22:44:18');
INSERT INTO `logs` VALUES ('395', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-17 22:45:09');
INSERT INTO `logs` VALUES ('396', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-17 22:45:20');
INSERT INTO `logs` VALUES ('397', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 22:45:23');
INSERT INTO `logs` VALUES ('398', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '192.168.0.107', '2020-05-17 22:49:45');
INSERT INTO `logs` VALUES ('399', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '192.168.0.107', '2020-05-17 22:52:35');
INSERT INTO `logs` VALUES ('400', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 22:52:35');
INSERT INTO `logs` VALUES ('401', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 22:52:38');
INSERT INTO `logs` VALUES ('403', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 22:52:39');
INSERT INTO `logs` VALUES ('405', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '192.168.0.107', '2020-05-17 22:57:37');
INSERT INTO `logs` VALUES ('406', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-17 22:57:40');
INSERT INTO `logs` VALUES ('407', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-17 22:57:40');
INSERT INTO `logs` VALUES ('408', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '192.168.0.107', '2020-05-17 22:57:50');
INSERT INTO `logs` VALUES ('409', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 22:57:57');
INSERT INTO `logs` VALUES ('410', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 22:57:59');
INSERT INTO `logs` VALUES ('411', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 22:58:00');
INSERT INTO `logs` VALUES ('412', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 22:58:01');
INSERT INTO `logs` VALUES ('413', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-17 22:58:34');
INSERT INTO `logs` VALUES ('414', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 22:58:36');
INSERT INTO `logs` VALUES ('415', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 22:58:38');
INSERT INTO `logs` VALUES ('416', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-17 22:58:48');
INSERT INTO `logs` VALUES ('417', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-17 22:58:55');
INSERT INTO `logs` VALUES ('418', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-17 22:58:56');
INSERT INTO `logs` VALUES ('419', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-17 22:58:57');
INSERT INTO `logs` VALUES ('420', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 23:01:23');
INSERT INTO `logs` VALUES ('421', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 23:01:32');
INSERT INTO `logs` VALUES ('422', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 23:02:00');
INSERT INTO `logs` VALUES ('423', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-17 23:02:14');
INSERT INTO `logs` VALUES ('424', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 23:02:26');
INSERT INTO `logs` VALUES ('425', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 23:04:14');
INSERT INTO `logs` VALUES ('426', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '192.168.0.107', '2020-05-17 23:04:36');
INSERT INTO `logs` VALUES ('427', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-17 23:04:38');
INSERT INTO `logs` VALUES ('428', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 23:05:09');
INSERT INTO `logs` VALUES ('429', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 23:05:20');
INSERT INTO `logs` VALUES ('430', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-17 23:05:28');
INSERT INTO `logs` VALUES ('431', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 23:05:29');
INSERT INTO `logs` VALUES ('432', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-17 23:06:57');
INSERT INTO `logs` VALUES ('433', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-17 23:07:02');
INSERT INTO `logs` VALUES ('434', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '14', '192.168.0.107', '2020-05-18 13:37:22');
INSERT INTO `logs` VALUES ('435', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-18 13:38:23');
INSERT INTO `logs` VALUES ('436', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-18 13:38:35');
INSERT INTO `logs` VALUES ('437', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '192.168.0.107', '2020-05-18 13:38:37');
INSERT INTO `logs` VALUES ('438', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '192.168.0.107', '2020-05-18 13:38:39');
INSERT INTO `logs` VALUES ('439', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '12', '192.168.0.107', '2020-05-18 13:39:58');
INSERT INTO `logs` VALUES ('440', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-18 13:40:59');
INSERT INTO `logs` VALUES ('441', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-18 13:41:04');
INSERT INTO `logs` VALUES ('442', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-18 13:42:01');
INSERT INTO `logs` VALUES ('443', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-18 13:42:03');
INSERT INTO `logs` VALUES ('444', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-18 13:42:06');
INSERT INTO `logs` VALUES ('445', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-18 13:42:07');
INSERT INTO `logs` VALUES ('446', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-18 13:42:30');
INSERT INTO `logs` VALUES ('447', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-18 13:42:34');
INSERT INTO `logs` VALUES ('448', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-18 13:42:35');
INSERT INTO `logs` VALUES ('449', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-18 13:45:44');
INSERT INTO `logs` VALUES ('450', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-18 13:45:48');
INSERT INTO `logs` VALUES ('451', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-18 13:45:49');
INSERT INTO `logs` VALUES ('452', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '127.0.0.1', '2020-05-18 13:45:49');
INSERT INTO `logs` VALUES ('453', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-18 13:45:52');
INSERT INTO `logs` VALUES ('454', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-18 13:45:54');
INSERT INTO `logs` VALUES ('455', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-18 13:49:16');
INSERT INTO `logs` VALUES ('456', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '127.0.0.1', '2020-05-18 13:50:01');
INSERT INTO `logs` VALUES ('457', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-18 13:50:04');
INSERT INTO `logs` VALUES ('458', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-18 13:50:05');
INSERT INTO `logs` VALUES ('459', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-18 13:50:06');
INSERT INTO `logs` VALUES ('460', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '18', '127.0.0.1', '2020-05-18 13:50:54');
INSERT INTO `logs` VALUES ('461', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-18 13:51:14');
INSERT INTO `logs` VALUES ('462', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '127.0.0.1', '2020-05-18 13:51:18');
INSERT INTO `logs` VALUES ('463', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-18 13:51:19');
INSERT INTO `logs` VALUES ('464', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '127.0.0.1', '2020-05-18 13:53:06');
INSERT INTO `logs` VALUES ('465', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-18 13:53:07');
INSERT INTO `logs` VALUES ('466', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '127.0.0.1', '2020-05-18 13:53:45');
INSERT INTO `logs` VALUES ('467', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-18 13:53:45');
INSERT INTO `logs` VALUES ('468', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '127.0.0.1', '2020-05-18 13:53:46');
INSERT INTO `logs` VALUES ('469', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-18 13:53:47');
INSERT INTO `logs` VALUES ('470', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-18 13:53:47');
INSERT INTO `logs` VALUES ('471', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-18 13:53:48');
INSERT INTO `logs` VALUES ('472', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-18 13:53:50');
INSERT INTO `logs` VALUES ('473', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '0:0:0:0:0:0:0:1', '2020-05-18 13:53:54');
INSERT INTO `logs` VALUES ('474', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-18 13:54:09');
INSERT INTO `logs` VALUES ('475', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-18 13:54:09');
INSERT INTO `logs` VALUES ('476', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '12', '127.0.0.1', '2020-05-18 13:57:31');
INSERT INTO `logs` VALUES ('477', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '0:0:0:0:0:0:0:1', '2020-05-18 13:57:34');
INSERT INTO `logs` VALUES ('478', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '127.0.0.1', '2020-05-18 13:58:37');
INSERT INTO `logs` VALUES ('479', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-18 13:58:41');
INSERT INTO `logs` VALUES ('480', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-18 13:59:54');
INSERT INTO `logs` VALUES ('481', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-18 14:00:10');
INSERT INTO `logs` VALUES ('482', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-18 14:00:12');
INSERT INTO `logs` VALUES ('483', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-18 14:00:21');
INSERT INTO `logs` VALUES ('484', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '0:0:0:0:0:0:0:1', '2020-05-18 14:01:02');
INSERT INTO `logs` VALUES ('485', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-18 14:01:03');
INSERT INTO `logs` VALUES ('486', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '16', '0:0:0:0:0:0:0:1', '2020-05-18 17:30:03');
INSERT INTO `logs` VALUES ('487', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-18 17:30:06');
INSERT INTO `logs` VALUES ('488', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '127.0.0.1', '2020-05-18 17:30:07');
INSERT INTO `logs` VALUES ('489', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '127.0.0.1', '2020-05-18 17:53:08');
INSERT INTO `logs` VALUES ('490', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '0:0:0:0:0:0:0:1', '2020-05-18 17:53:16');
INSERT INTO `logs` VALUES ('491', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '0:0:0:0:0:0:0:1', '2020-05-18 17:53:21');
INSERT INTO `logs` VALUES ('492', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '0:0:0:0:0:0:0:1', '2020-05-18 17:53:22');
INSERT INTO `logs` VALUES ('493', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '127.0.0.1', '2020-05-18 18:01:05');
INSERT INTO `logs` VALUES ('494', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '0:0:0:0:0:0:0:1', '2020-05-18 18:01:26');
INSERT INTO `logs` VALUES ('495', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '127.0.0.1', '2020-05-18 18:05:34');
INSERT INTO `logs` VALUES ('496', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '18', '127.0.0.1', '2020-05-18 18:13:28');
INSERT INTO `logs` VALUES ('497', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '127.0.0.1', '2020-05-18 18:18:15');
INSERT INTO `logs` VALUES ('498', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '15', '0:0:0:0:0:0:0:1', '2020-05-20 20:47:06');
INSERT INTO `logs` VALUES ('499', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '0:0:0:0:0:0:0:1', '2020-05-20 22:51:20');
INSERT INTO `logs` VALUES ('500', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '0:0:0:0:0:0:0:1', '2020-05-21 16:13:46');
INSERT INTO `logs` VALUES ('501', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '127.0.0.1', '2020-05-21 22:03:19');
INSERT INTO `logs` VALUES ('502', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '127.0.0.1', '2020-05-21 22:03:21');
INSERT INTO `logs` VALUES ('503', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '127.0.0.1', '2020-05-21 22:03:23');
INSERT INTO `logs` VALUES ('504', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-21 22:03:25');
INSERT INTO `logs` VALUES ('505', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '0:0:0:0:0:0:0:1', '2020-05-21 22:03:26');
INSERT INTO `logs` VALUES ('506', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-21 22:03:27');
INSERT INTO `logs` VALUES ('507', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-21 22:03:34');
INSERT INTO `logs` VALUES ('508', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-21 22:03:34');
INSERT INTO `logs` VALUES ('509', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-21 22:03:42');
INSERT INTO `logs` VALUES ('510', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '127.0.0.1', '2020-05-21 22:03:49');
INSERT INTO `logs` VALUES ('511', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-21 22:04:07');
INSERT INTO `logs` VALUES ('512', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-21 22:05:33');
INSERT INTO `logs` VALUES ('513', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-21 22:05:34');
INSERT INTO `logs` VALUES ('514', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-21 22:05:35');
INSERT INTO `logs` VALUES ('515', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-21 22:05:36');
INSERT INTO `logs` VALUES ('516', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-21 22:05:36');
INSERT INTO `logs` VALUES ('517', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-21 22:05:37');
INSERT INTO `logs` VALUES ('518', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-21 22:05:38');
INSERT INTO `logs` VALUES ('519', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-21 22:05:39');
INSERT INTO `logs` VALUES ('520', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '127.0.0.1', '2020-05-21 22:05:58');
INSERT INTO `logs` VALUES ('521', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-21 22:06:00');
INSERT INTO `logs` VALUES ('522', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-21 22:06:01');
INSERT INTO `logs` VALUES ('523', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-21 22:06:03');
INSERT INTO `logs` VALUES ('524', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-21 22:06:04');
INSERT INTO `logs` VALUES ('525', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '0:0:0:0:0:0:0:1', '2020-05-21 22:06:05');
INSERT INTO `logs` VALUES ('526', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '0:0:0:0:0:0:0:1', '2020-05-21 22:06:08');
INSERT INTO `logs` VALUES ('527', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-21 22:24:03');
INSERT INTO `logs` VALUES ('528', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-21 22:28:21');
INSERT INTO `logs` VALUES ('529', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '11', '192.168.0.107', '2020-05-24 15:35:42');
INSERT INTO `logs` VALUES ('530', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,valid=0]', '81', '192.168.0.107', '2020-05-24 15:54:12');
INSERT INTO `logs` VALUES ('531', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '12', '192.168.0.107', '2020-05-24 15:59:06');
INSERT INTO `logs` VALUES ('532', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '192.168.0.107', '2020-05-24 15:59:07');
INSERT INTO `logs` VALUES ('533', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '192.168.0.107', '2020-05-24 15:59:12');
INSERT INTO `logs` VALUES ('534', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-24 15:59:37');
INSERT INTO `logs` VALUES ('535', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-24 15:59:38');
INSERT INTO `logs` VALUES ('536', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-24 15:59:49');
INSERT INTO `logs` VALUES ('537', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-24 16:00:41');
INSERT INTO `logs` VALUES ('538', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-24 16:00:43');
INSERT INTO `logs` VALUES ('539', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-24 16:09:49');
INSERT INTO `logs` VALUES ('540', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-24 16:10:27');
INSERT INTO `logs` VALUES ('541', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-24 16:10:28');
INSERT INTO `logs` VALUES ('542', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '192.168.0.107', '2020-05-24 16:11:01');
INSERT INTO `logs` VALUES ('543', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-24 16:11:02');
INSERT INTO `logs` VALUES ('544', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-24 16:11:12');
INSERT INTO `logs` VALUES ('545', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-24 16:11:21');
INSERT INTO `logs` VALUES ('546', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-24 16:11:26');
INSERT INTO `logs` VALUES ('547', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-24 16:15:42');
INSERT INTO `logs` VALUES ('548', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-24 16:15:44');
INSERT INTO `logs` VALUES ('549', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=admin,valid=1]', '130', '192.168.0.107', '2020-05-24 16:15:59');
INSERT INTO `logs` VALUES ('550', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=26,username=admin,valid=0]', '1766', '192.168.0.107', '2020-05-24 16:20:31');
INSERT INTO `logs` VALUES ('551', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-24 16:21:56');
INSERT INTO `logs` VALUES ('552', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=26,username=admin,valid=1]', '217', '192.168.0.107', '2020-05-24 16:22:07');
INSERT INTO `logs` VALUES ('553', 'admin', '修改用户', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateUserDeptRoleIdsById', '[UserDeptRoleIds(depts=null, roleIds=[1])]', '200', '192.168.0.107', '2020-05-24 16:22:15');
INSERT INTO `logs` VALUES ('554', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '1', '192.168.0.107', '2020-05-24 16:22:23');
INSERT INTO `logs` VALUES ('555', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,username=qxn,valid=0]', '143', '192.168.0.107', '2020-05-24 16:22:43');
INSERT INTO `logs` VALUES ('556', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '11', '192.168.0.107', '2020-05-24 16:28:26');
INSERT INTO `logs` VALUES ('557', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '11', '192.168.0.107', '2020-05-24 16:31:15');
INSERT INTO `logs` VALUES ('558', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-24 16:31:17');
INSERT INTO `logs` VALUES ('559', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '192.168.0.107', '2020-05-24 16:31:19');
INSERT INTO `logs` VALUES ('560', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,valid=1,modifiedUser=qxn]', '1800', '192.168.0.107', '2020-05-24 16:44:49');
INSERT INTO `logs` VALUES ('561', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-24 16:47:10');
INSERT INTO `logs` VALUES ('562', 'admin', '修改用户', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateUserDeptRoleIdsById', '[UserDeptRoleIds(depts=null, roleIds=[1, 52])]', '229', '192.168.0.107', '2020-05-24 16:47:19');
INSERT INTO `logs` VALUES ('563', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '16', '192.168.0.107', '2020-05-24 17:29:49');
INSERT INTO `logs` VALUES ('564', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '192.168.0.107', '2020-05-24 17:29:50');
INSERT INTO `logs` VALUES ('565', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,valid=0,modifiedUser=qxn]', '132', '192.168.0.107', '2020-05-24 17:33:51');
INSERT INTO `logs` VALUES ('566', 'qxn', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=25,valid=1,modifiedUser=qxn]', '131', '192.168.0.107', '2020-05-24 17:39:45');
INSERT INTO `logs` VALUES ('567', 'qxn', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '11', '192.168.0.107', '2020-05-24 20:20:52');
INSERT INTO `logs` VALUES ('568', 'qxn', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '13', '192.168.0.107', '2020-05-24 20:24:28');
INSERT INTO `logs` VALUES ('569', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '192.168.0.107', '2020-05-24 20:50:36');
INSERT INTO `logs` VALUES ('570', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '12', '192.168.0.107', '2020-05-24 20:50:38');
INSERT INTO `logs` VALUES ('571', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '192.168.0.107', '2020-05-24 20:50:41');
INSERT INTO `logs` VALUES ('572', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '192.168.0.107', '2020-05-24 20:50:46');
INSERT INTO `logs` VALUES ('573', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '192.168.0.107', '2020-05-24 20:50:52');
INSERT INTO `logs` VALUES ('574', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-24 20:50:52');
INSERT INTO `logs` VALUES ('575', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-24 20:51:02');
INSERT INTO `logs` VALUES ('576', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-24 20:51:03');
INSERT INTO `logs` VALUES ('577', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-24 20:51:04');
INSERT INTO `logs` VALUES ('578', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '192.168.0.107', '2020-05-25 11:01:47');
INSERT INTO `logs` VALUES ('579', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '8', '192.168.0.107', '2020-05-25 11:01:50');
INSERT INTO `logs` VALUES ('580', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-25 11:02:41');
INSERT INTO `logs` VALUES ('581', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-25 11:03:20');
INSERT INTO `logs` VALUES ('582', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '21', '192.168.0.107', '2020-05-25 14:58:52');
INSERT INTO `logs` VALUES ('584', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-25 16:08:17');
INSERT INTO `logs` VALUES ('585', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '192.168.0.107', '2020-05-25 16:08:37');
INSERT INTO `logs` VALUES ('586', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=30,valid=1,modifiedUser=admin]', '149', '192.168.0.107', '2020-05-25 16:08:41');
INSERT INTO `logs` VALUES ('587', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.0.107', '2020-05-25 16:09:06');
INSERT INTO `logs` VALUES ('588', 'admin', '禁用启用', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[id=30,valid=0,modifiedUser=admin]', '130', '192.168.0.107', '2020-05-25 16:09:10');
INSERT INTO `logs` VALUES ('589', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-25 16:11:51');
INSERT INTO `logs` VALUES ('590', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '18', '192.168.0.107', '2020-05-25 16:22:33');
INSERT INTO `logs` VALUES ('591', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-25 16:22:44');
INSERT INTO `logs` VALUES ('592', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-25 16:23:41');
INSERT INTO `logs` VALUES ('593', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.0.107', '2020-05-25 16:54:46');
INSERT INTO `logs` VALUES ('594', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-25 17:03:32');
INSERT INTO `logs` VALUES ('595', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '11', '192.168.0.107', '2020-05-25 17:05:47');
INSERT INTO `logs` VALUES ('596', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.0.107', '2020-05-25 17:16:36');
INSERT INTO `logs` VALUES ('597', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '13', '192.168.35.184', '2020-05-26 10:15:07');
INSERT INTO `logs` VALUES ('598', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '192.168.35.184', '2020-05-26 10:18:10');
INSERT INTO `logs` VALUES ('599', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '192.168.35.184', '2020-05-26 10:18:32');
INSERT INTO `logs` VALUES ('600', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '192.168.35.184', '2020-05-26 10:20:59');
INSERT INTO `logs` VALUES ('601', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.35.184', '2020-05-26 10:21:05');
INSERT INTO `logs` VALUES ('602', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.35.184', '2020-05-26 10:23:29');
INSERT INTO `logs` VALUES ('603', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.35.184', '2020-05-26 10:29:16');
INSERT INTO `logs` VALUES ('605', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '5', '192.168.35.184', '2020-05-26 10:31:08');
INSERT INTO `logs` VALUES ('606', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '9', '192.168.35.184', '2020-05-26 10:32:00');
INSERT INTO `logs` VALUES ('607', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '13', '192.168.35.184', '2020-05-26 10:32:33');
INSERT INTO `logs` VALUES ('608', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '6', '192.168.35.184', '2020-05-26 10:33:44');
INSERT INTO `logs` VALUES ('609', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '12', '192.168.35.184', '2020-05-26 10:36:45');
INSERT INTO `logs` VALUES ('610', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.35.184', '2020-05-26 10:37:33');
INSERT INTO `logs` VALUES ('611', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '13', '192.168.35.184', '2020-05-26 10:42:11');
INSERT INTO `logs` VALUES ('612', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.35.184', '2020-05-26 10:43:41');
INSERT INTO `logs` VALUES ('613', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '7', '192.168.35.184', '2020-05-26 10:46:19');
INSERT INTO `logs` VALUES ('614', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.35.184', '2020-05-26 10:48:14');
INSERT INTO `logs` VALUES ('615', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '2', '192.168.35.184', '2020-05-26 10:50:34');
INSERT INTO `logs` VALUES ('616', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.35.184', '2020-05-26 10:50:38');
INSERT INTO `logs` VALUES ('617', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.35.184', '2020-05-26 10:51:11');
INSERT INTO `logs` VALUES ('618', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.35.184', '2020-05-26 10:51:11');
INSERT INTO `logs` VALUES ('619', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.35.184', '2020-05-26 10:58:55');
INSERT INTO `logs` VALUES ('620', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '3', '192.168.35.184', '2020-05-26 11:03:54');
INSERT INTO `logs` VALUES ('621', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '16', '192.168.35.184', '2020-05-26 11:28:03');
INSERT INTO `logs` VALUES ('622', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '11', '192.168.35.184', '2020-05-26 11:28:59');
INSERT INTO `logs` VALUES ('623', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '12', '192.168.35.184', '2020-05-26 14:51:23');
INSERT INTO `logs` VALUES ('624', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '4', '192.168.35.184', '2020-05-26 15:13:01');
INSERT INTO `logs` VALUES ('625', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '10', '192.168.35.184', '2020-05-26 15:52:16');
INSERT INTO `logs` VALUES ('626', 'admin', '查询菜单', 'com.qxn.pj.sys.service.impl.MenusServiceImpl.findObjects', '[]', '12', '192.168.35.184', '2020-05-26 15:52:28');

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
