/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : exchange

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-11-25 19:41:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `CommentId` int(10) unsigned NOT NULL,
  `Sender` varchar(45) NOT NULL,
  `Receiver` varchar(45) NOT NULL,
  `Context` varchar(100) NOT NULL,
  `Aim` int(10) unsigned NOT NULL,
  PRIMARY KEY (`CommentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for dealrecord
-- ----------------------------
DROP TABLE IF EXISTS `dealrecord`;
CREATE TABLE `dealrecord` (
  `DealId` int(10) unsigned NOT NULL,
  `Sender` varchar(45) NOT NULL,
  `Receiver` varchar(45) NOT NULL,
  `ItemId` int(10) unsigned NOT NULL,
  `ItemName` varchar(45) NOT NULL,
  PRIMARY KEY (`DealId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dealrecord
-- ----------------------------

-- ----------------------------
-- Table structure for iteminfo
-- ----------------------------
DROP TABLE IF EXISTS `iteminfo`;
CREATE TABLE `iteminfo` (
  `ItemId` int(10) unsigned NOT NULL,
  `ItemName` varchar(45) NOT NULL,
  `Owner` varchar(45) NOT NULL,
  `BuyPrice` int(10) unsigned NOT NULL,
  `Description` varchar(100) NOT NULL,
  `PictureLink` varchar(45) NOT NULL,
  PRIMARY KEY (`ItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iteminfo
-- ----------------------------

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `Email` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Statu` tinyint(1) unsigned NOT NULL,
  `Authorize` varchar(45) NOT NULL,
  `JoinTime` date NOT NULL,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
