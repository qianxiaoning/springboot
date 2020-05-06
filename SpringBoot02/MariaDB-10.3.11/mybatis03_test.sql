/*
Navicat MySQL Data Transfer

Source Server         : qxn
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : mybatis03_test

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-04-01 16:08:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `game_company`
-- ----------------------------
DROP TABLE IF EXISTS `game_company`;
CREATE TABLE `game_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `remark` varchar(100) NOT NULL,
  `createdTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_company
-- ----------------------------
INSERT INTO `game_company` VALUES ('1', '任天堂', 'switch', '2020-02-28 15:28:37');
INSERT INTO `game_company` VALUES ('2', '索尼', 'ps', '2020-02-28 15:28:53');
INSERT INTO `game_company` VALUES ('3', '微软', 'xbox', '2020-02-06 15:29:14');
INSERT INTO `game_company` VALUES ('4', 'w', 'w', '2020-02-28 15:29:40');
