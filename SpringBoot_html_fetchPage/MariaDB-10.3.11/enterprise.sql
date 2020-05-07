/*
Navicat MySQL Data Transfer

Source Server         : qxn
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : enterprise

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-05-06 15:07:39
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
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES ('12', 'eee', 'eee', 'eee', 'eee', '1', '22', '2020-04-14 16:00:01');
INSERT INTO `logs` VALUES ('13', 'admin', 'operation', null, null, '118', '192.168.0.200', '2020-05-01 17:11:40');
INSERT INTO `logs` VALUES ('14', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImplupdateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=0, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '1', '192.168.0.200', '2020-05-02 19:55:23');
INSERT INTO `logs` VALUES ('15', 'admin', 'operation', 'com.qxn.pj.sys.service.impl.UsersServiceImpl.updateValidByUserId', '[Users(id=15, username=null, password=null, salt=null, email=null, mobile=null, valid=1, deptId=null, createdTime=null, modifiedTime=null, createdUser=null, modifiedUser=null)]', '140', '192.168.0.200', '2020-05-02 20:01:21');
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

