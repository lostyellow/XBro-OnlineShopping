/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50133
Source Host           : localhost:3306
Source Database       : xbro

Target Server Type    : MYSQL
Target Server Version : 50133
File Encoding         : 65001

Date: 2023-10-25 14:49:04
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `drugs`
-- ----------------------------
DROP TABLE IF EXISTS `drugs`;
CREATE TABLE `drugs` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `seller_id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_description` text,
  `product_image` varchar(255) DEFAULT NULL,
  `product_price` float NOT NULL,
  `batch_number` varchar(255) DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `prescription_required` tinyint(1) DEFAULT NULL,
  `is_frozen` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `name` (`product_name`),
  KEY `ID` (`product_id`),
  KEY `drugs_ibfk_1` (`seller_id`),
  CONSTRAINT `drugs_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of drugs
-- ----------------------------

-- ----------------------------
-- Table structure for `order_details`
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details` (
  `transaction_id` int(11) NOT NULL,
  `appointment_time` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `buyer_name` varchar(255) DEFAULT NULL,
  `buyer_gender` varchar(255) DEFAULT NULL,
  `buyer_identification` varchar(255) DEFAULT NULL,
  `buyer_phone_number` varchar(255) DEFAULT NULL,
  `notes` text,
  PRIMARY KEY (`transaction_id`),
  CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`transaction_id`) REFERENCES `transactions` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of order_details
-- ----------------------------

-- ----------------------------
-- Table structure for `transactions`
-- ----------------------------
DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `transaction_status` varchar(255) NOT NULL,
  `transaction_time` datetime NOT NULL,
  `transaction_amount` float NOT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `transactions_ibfk_2` (`product_id`),
  CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `drugs` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of transactions
-- ----------------------------

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `id_card` varchar(255) NOT NULL,
  `sex` varchar(255) NOT NULL,
  `tele` varchar(255) NOT NULL,
  `e_mail` varchar(255) NOT NULL,
  `birth` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'hanyuyellow', '123456', 'Hanyu Fang', '330921200204056510', 'male', '13732521835', '2112190219@pop.zjgsu.edu.cn', '2023-10-23');
