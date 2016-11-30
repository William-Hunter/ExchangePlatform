/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : exchange

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-11-29 11:54:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `ids` varchar(45) NOT NULL,
  `sender` varchar(45) NOT NULL,
  `receiver` varchar(45) NOT NULL,
  `context` varchar(100) NOT NULL,
  `aim` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ids`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for dealrecord
-- ----------------------------
DROP TABLE IF EXISTS `dealrecord`;
CREATE TABLE `dealrecord` (
  `ids` varchar(45) NOT NULL,
  `sender` varchar(45) NOT NULL,
  `receiver` varchar(45) NOT NULL,
  `itemId` int(10) unsigned NOT NULL,
  `itemName` varchar(45) NOT NULL,
  PRIMARY KEY (`ids`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dealrecord
-- ----------------------------

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `ids` varchar(45) NOT NULL,
  `itemName` varchar(45) NOT NULL,
  `owner` varchar(45) NOT NULL,
  `buyPrice` int(10) unsigned NOT NULL,
  `description` varchar(100) NOT NULL,
  `pictureLink` varchar(45) NOT NULL,
  PRIMARY KEY (`ids`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `email` varchar(45) NOT NULL,
  `ids` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `statu` tinyint(1) unsigned NOT NULL,
  `authorize` varchar(45) NOT NULL,
  `joinTime` date NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`ids`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
