/*
Navicat MySQL Data Transfer

Source Server         : test2
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : test2

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-07-13 17:01:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户 ID',
  `customername` varchar(16) NOT NULL COMMENT '客户名',
  `cell_phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `create_time` timestamp NOT NULL DEFAULT '1970-01-02 00:00:01' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
