/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : zero

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 15/09/2018 15:00:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址ID',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址名',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户联系方式',
  `buildingName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建筑名',
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 14, NULL, '6363', NULL, NULL);
INSERT INTO `address` VALUES (2, 14, NULL, '1215', NULL, NULL);

-- ----------------------------
-- Table structure for admininfo
-- ----------------------------
DROP TABLE IF EXISTS `admininfo`;
CREATE TABLE `admininfo`  (
  `Aid` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `Aname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员名',
  `Apwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员密码',
  `Alevel` int(10) NOT NULL COMMENT '管理员权限等级',
  `ArealName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员真实姓名',
  `APhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员联系方式',
  PRIMARY KEY (`Aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admininfo
-- ----------------------------
INSERT INTO `admininfo` VALUES (1, 'admin', 'athelp', 0, '张延孔', '18856308801');

-- ----------------------------
-- Table structure for goodsinfo
-- ----------------------------
DROP TABLE IF EXISTS `goodsinfo`;
CREATE TABLE `goodsinfo`  (
  `Gid` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `Gname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `Gprice` double NULL DEFAULT NULL COMMENT '原价格',
  `Gamount` int(11) NULL DEFAULT NULL COMMENT '库存',
  `Gdate` datetime(0) NOT NULL COMMENT '日期',
  `Gimgurl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `Glook` int(11) NOT NULL,
  `Gintro` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '介绍',
  `Gbrief` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介',
  `Gstate` int(11) NULL DEFAULT NULL COMMENT '状态',
  `Gsale` int(255) NULL DEFAULT NULL COMMENT '销量',
  `Gdiscount` double NULL DEFAULT NULL COMMENT '折扣',
  `Gtype` int(11) NULL DEFAULT NULL COMMENT '类别',
  `Gunit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`Gid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodsinfo
-- ----------------------------
INSERT INTO `goodsinfo` VALUES (1, NULL, NULL, NULL, '2018-08-25 20:24:37', 'C:/', 1, '2233', '3344', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `goodsinfo` VALUES (3, '水果', 20, 4, '2018-09-08 07:16:40', '123', 1, '嗯，只是水果而已', '水果大法好啊', NULL, NULL, 0.95, 2, '斤');
INSERT INTO `goodsinfo` VALUES (4, '水果', 0, 0, '2018-09-08 07:23:14', '567', 1, '嗯，只是水果而已', '水果大法好啊', NULL, NULL, 0, 2, '斤');
INSERT INTO `goodsinfo` VALUES (5, '水鬼', 20, 4, '2018-09-08 07:23:48', '123', 1, '嗯，只是水果而已', '水果大法好啊', NULL, NULL, 0.95, 2, '斤');
INSERT INTO `goodsinfo` VALUES (6, '正式的水果', 20, 4, '2018-09-09 01:06:05', '123', 1, '嗯，只是水果而已', '水果大法好啊\r\n', NULL, NULL, 0.95, 2, '斤');
INSERT INTO `goodsinfo` VALUES (7, 'fruit', 20, 4, '2018-09-09 02:24:36', '/Upload/fruit.jpg', 1, '嗯，只是水果而已', '水果大法好啊', NULL, NULL, 0.95, 2, '斤');
INSERT INTO `goodsinfo` VALUES (8, '完整的水果', 20, 4, '2018-09-09 03:03:30', '/Upload/1536462208105.jpg', 1, '嗯，只是水果而已', '水果大法好啊', NULL, NULL, 0.95, 2, '斤');
INSERT INTO `goodsinfo` VALUES (9, '尝试干吧水果', 70, 8, '2018-09-09 05:50:34', '/Upload/1536472220826.jpg', 1, '嗯，只是水果而已', '水果大法好啊', NULL, NULL, 0.85, 3, '斤');
INSERT INTO `goodsinfo` VALUES (10, '芒果', 3, 4, '2018-09-15 02:04:19', '/Upload/1536977042497.jpg', 1, '二外额外群若', '为期 ', NULL, NULL, 0.2, 2, '4');

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype`  (
  `Tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类别ID',
  `Tname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `Tpic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品照',
  `Tintro` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品介绍',
  `Tbrief` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品简介',
  PRIMARY KEY (`Tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES (4, NULL, NULL, NULL, NULL);
INSERT INTO `goodstype` VALUES (5, NULL, 'C:\\Users\\winte\\Pictures\\2018-03-20-0.jpg', '', '');
INSERT INTO `goodstype` VALUES (6, '123', 'C:\\Users\\winte\\Pictures\\2018-03-20-0.jpg', '', '');
INSERT INTO `goodstype` VALUES (7, '太空水果', NULL, '非本地产', '来自外太空');
INSERT INTO `goodstype` VALUES (8, 'æ°é²çæ°´æ', '/Upload/1536471729038.jpg', 'å¯ï¼åªæ¯æ°´æèå·²', 'æ°´æå¤§æ³å¥½å');
INSERT INTO `goodstype` VALUES (9, '正常水果', '/Upload/1536471889116.jpg', '嗯，只是水果而已', '水果大法好啊');

-- ----------------------------
-- Table structure for ordergoods
-- ----------------------------
DROP TABLE IF EXISTS `ordergoods`;
CREATE TABLE `ordergoods`  (
  `OGid` int(11) NOT NULL DEFAULT 0 COMMENT '订单商品集ID',
  `Oid` int(11) NULL DEFAULT NULL COMMENT '订单ID',
  `Uid` int(11) NULL DEFAULT NULL COMMENT '收件人 用户名',
  `Gid` int(11) NULL DEFAULT NULL COMMENT '库存商品集ID',
  `OGamount` int(11) NULL DEFAULT NULL COMMENT '订单商品数量',
  `OGtotalprice` double NULL DEFAULT NULL COMMENT '订单商品总价',
  `OGDate` datetime(0) NULL DEFAULT NULL COMMENT '商品下单时间',
  PRIMARY KEY (`OGid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo`  (
  `Oid` int(11) NOT NULL DEFAULT 0 COMMENT '订单ID',
  `Odate` datetime(0) NULL DEFAULT NULL COMMENT '下单时间',
  `Aid` int(11) NOT NULL COMMENT '寄件地址',
  `Ostate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `Orecname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人 名',
  `Orecadr` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件地址',
  `Orectel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人联系方式',
  `Uid` int(11) NULL DEFAULT NULL COMMENT '收件人 用户名',
  `Ototalprice` double NULL DEFAULT NULL COMMENT '总额',
  `Obrief` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介',
  `Ointro` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '介绍',
  PRIMARY KEY (`Oid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `Uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `Uname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `Upwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `Uemail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `Urealname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户真实姓名',
  `Uphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户联系方式',
  `Utype` int(11) NULL DEFAULT NULL COMMENT '用户类型',
  `Ucread` int(11) NULL DEFAULT NULL,
  `Umoney` double NULL DEFAULT NULL COMMENT '用户余额',
  `Udate` datetime(0) NULL DEFAULT NULL COMMENT '用户注册时间',
  PRIMARY KEY (`Uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, 'kp', '7894555', 'auska123456@qq.com', 'kunpeng', '12341111', 1, NULL, 2233, '2018-08-25 00:57:13');
INSERT INTO `userinfo` VALUES (2, '鲲鹏', '45678', '123456789@qq.com', '苏回', '124679123', 2, NULL, 9999.9, '2018-09-14 02:14:03');

SET FOREIGN_KEY_CHECKS = 1;
