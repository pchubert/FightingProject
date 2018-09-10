/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50558
Source Host           : localhost:3306
Source Database       : zero

Target Server Type    : MYSQL
Target Server Version : 50558
File Encoding         : 65001

Date: 2018-09-08 09:53:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `buildingName` varchar(255) DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for admininfo
-- ----------------------------
DROP TABLE IF EXISTS `admininfo`;
CREATE TABLE `admininfo` (
  `Aid` int(11) NOT NULL AUTO_INCREMENT,
  `Aname` varchar(50) DEFAULT NULL,
  `Apwd` varchar(20) DEFAULT NULL,
  `Alevel` int(10) NOT NULL,
  `ArealName` varchar(255) DEFAULT NULL,
  `APhone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Aid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admininfo
-- ----------------------------
INSERT INTO `admininfo` VALUES ('1', 'admin', 'athelp', '0', '张延孔', '18856308801');

-- ----------------------------
-- Table structure for goodsinfo
-- ----------------------------
DROP TABLE IF EXISTS `goodsinfo`;
CREATE TABLE `goodsinfo` (
  `Gid` int(11) NOT NULL DEFAULT '0' COMMENT '序号',
  `Gname` varchar(100) DEFAULT NULL COMMENT '名称',
  `Gprice` double DEFAULT NULL COMMENT '原价格',
  `Gamount` int(11) DEFAULT NULL COMMENT '库存',
  `Gdate` datetime NOT NULL COMMENT '日期',
  `Gimgurl` varchar(100) NOT NULL COMMENT '图片',
  `Glook` int(11) NOT NULL,
  `Gintro` text NOT NULL COMMENT '介绍',
  `Gbrief` text NOT NULL COMMENT '简介',
  `Gstate` int(11) DEFAULT NULL COMMENT '状态',
  `Gsale` int(255) DEFAULT NULL COMMENT '销量',
  `Gdiscount` double DEFAULT NULL COMMENT '折扣',
  `Gtype` int(11) DEFAULT NULL COMMENT '类别',
  `Gunit` varchar(255) DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`Gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodsinfo
-- ----------------------------

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype` (
  `Tid` int(11) NOT NULL AUTO_INCREMENT,
  `Tname` varchar(255) DEFAULT NULL,
  `Tpic` varchar(255) DEFAULT NULL,
  `Tintro` text,
  `Tbrief` text,
  PRIMARY KEY (`Tid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES ('4', null, null, null, null);
INSERT INTO `goodstype` VALUES ('5', null, 'C:\\Users\\winte\\Pictures\\2018-03-20-0.jpg', '', '');
INSERT INTO `goodstype` VALUES ('6', '酒水', 'C:\\Users\\winte\\Pictures\\2018-03-20-0.jpg', '', '');

-- ----------------------------
-- Table structure for ordergoods
-- ----------------------------
DROP TABLE IF EXISTS `ordergoods`;
CREATE TABLE `ordergoods` (
  `OGid` int(11) NOT NULL DEFAULT '0',
  `Oid` int(11) DEFAULT NULL,
  `Uid` int(11) DEFAULT NULL,
  `Gid` int(11) DEFAULT NULL,
  `OGamount` int(11) DEFAULT NULL,
  `OGtotalprice` double DEFAULT NULL,
  `OGDate` datetime DEFAULT NULL,
  PRIMARY KEY (`OGid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ordergoods
-- ----------------------------

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `Oid` int(11) NOT NULL DEFAULT '0',
  `Odate` datetime DEFAULT NULL,
  `Aid` int(11) NOT NULL,
  `Ostate` varchar(20) DEFAULT NULL,
  `Orecname` varchar(50) DEFAULT NULL,
  `Orecadr` varchar(200) DEFAULT NULL,
  `Orectel` varchar(255) DEFAULT NULL,
  `Uid` int(11) DEFAULT NULL,
  `Ototalprice` double DEFAULT NULL,
  `Obrief` text NOT NULL,
  `Ointro` text,
  PRIMARY KEY (`Oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderinfo
-- ----------------------------

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `Uid` int(11) NOT NULL DEFAULT '0',
  `Uname` varchar(50) DEFAULT NULL,
  `Upwd` varchar(20) DEFAULT NULL,
  `Uemail` varchar(100) DEFAULT NULL,
  `Urealname` varchar(255) DEFAULT NULL,
  `Uphone` varchar(255) DEFAULT NULL,
  `Utype` int(11) DEFAULT NULL,
  `Ucread` int(11) DEFAULT NULL,
  `Umoney` double DEFAULT NULL,
  `Udate` datetime DEFAULT NULL,
  PRIMARY KEY (`Uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
