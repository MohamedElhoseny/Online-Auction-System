/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : swf2_db

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-07-04 04:38:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Fname` varchar(20) DEFAULT NULL,
  `Lname` varchar(20) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Pass` varchar(30) DEFAULT NULL,
  `profilePic` varchar(100) DEFAULT NULL,
  `Gender` tinyint(1) DEFAULT NULL,
  `BirthDate` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `IP` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'Elhosany', 'ELAdmin', 'a', 'a', '../Resources/Images/admin.jpg', '0', '8/7/2001', '01097706982', '0');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Cat_Name` varchar(20) DEFAULT NULL,
  `Admin_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Admin_ID` (`Admin_ID`),
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`Admin_ID`) REFERENCES `admin` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'Laptops', '1');
INSERT INTO `category` VALUES ('2', 'TVs', '1');
INSERT INTO `category` VALUES ('3', 'Fashion', '1');
INSERT INTO `category` VALUES ('4', 'Electronics', '1');
INSERT INTO `category` VALUES ('5', 'Health', '1');
INSERT INTO `category` VALUES ('6', 'Smart Phones', '1');

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Item_name` varchar(30) DEFAULT NULL,
  `Details` varchar(500) DEFAULT NULL,
  `picture` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `Cat_ID` int(11) DEFAULT NULL,
  `session_ID` int(11) DEFAULT NULL,
  `seller_ID` int(11) DEFAULT NULL,
  `Admin_ID` int(11) DEFAULT NULL,
  `Accepted` int(11) NOT NULL DEFAULT '0',
  `Served` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `Cat_ID` (`Cat_ID`),
  KEY `session_ID` (`session_ID`),
  KEY `seller_ID` (`seller_ID`),
  KEY `Admin_ID` (`Admin_ID`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`Cat_ID`) REFERENCES `category` (`ID`),
  CONSTRAINT `item_ibfk_2` FOREIGN KEY (`session_ID`) REFERENCES `sessions` (`ID`),
  CONSTRAINT `item_ibfk_3` FOREIGN KEY (`seller_ID`) REFERENCES `systemuser` (`ID`),
  CONSTRAINT `item_ibfk_4` FOREIGN KEY (`Admin_ID`) REFERENCES `admin` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('30', 'ASUS X750 17-Inch', 'new product', '../Resources/Images/shop/Laptops/l1.jpg', '8500', '1', '66', '21', '1', '1', '1');
INSERT INTO `item` VALUES ('31', 'acer y70 15-Inch', 'new product', '../Resources/Images/shop/Laptops/l2.jpg', '6500', '1', '67', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('32', 'HP Pavilion x360', 'new product', '../Resources/Images/shop/Laptops/l3.jpg', '10500', '1', '68', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('33', 'HP 17-x021 i5', 'new product', '../Resources/Images/shop/Laptops/l4.jpg', '5000', '1', '69', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('34', 'lenovo y5070  17-Inch', 'new product', '../Resources/Images/shop/Laptops/l5.jpg', '12500', '1', '70', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('35', 'ASUS i3 - x540 ', 'new product', '../Resources/Images/shop/Laptops/l6.jpg', '4700', '1', '71', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('36', 'acer 15-inch Red ', 'new product', '../Resources/Images/shop/Laptops/l7.jpg', '5000', '1', '72', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('37', 'HP ultra Touch i7', 'new product', '../Resources/Images/shop/Laptops/l8.jpg', '12400', '1', '73', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('38', 'Acer i5 Red z715', 'new product', '../Resources/Images/shop/Laptops/l9.jpg', '7500', '1', '74', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('39', 'Apple MacBook  i7 b', 'new product', '../Resources/Images/shop/Laptops/l10.jpg', '20500', '1', '75', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('40', 'LG Smart TV 55-inch', 'new product', '../Resources/Images/shop/TVs/1.jpg', '9800', '2', '76', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('41', 'Samsung LED 42-Inch', 'new product', '../Resources/Images/shop/TVs/4.jpg', '6000', '2', '77', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('42', 'LG Smart TV android', 'new product', '../Resources/Images/shop/TVs/2.jpg', '10500', '2', '78', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('43', 'LG LED 42-Inch silver', 'new product', '../Resources/Images/shop/TVs/3.jpg', '5000', '2', '79', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('44', 'Samsung UHD 49-Inch', 'new product', '../Resources/Images/shop/TVs/5.jpg', '11000', '2', '80', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('45', 'Toshiba 32-inch LED', 'new product', '../Resources/Images/shop/TVs/6.jpg', '4500', '2', '81', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('46', 'Toshiba LCD 32-inch', 'new product', '../Resources/Images/shop/TVs/7.jpg', '3200', '2', '82', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('47', 'Toshiba Android Smart', 'new product', '../Resources/Images/shop/TVs/8.jpg', '9000', '2', '83', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('48', 'Kogan 32-Inch LED', 'new product', '../Resources/Images/shop/TVs/9.jpg', '5000', '2', '84', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('49', 'Panasonic 55cm LED', 'new product', '../Resources/Images/shop/TVs/10.jpg', '7000', '2', '85', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('58', 'GYM Equip-1', 'new product', '../Resources/Images/shop/Health/3.png', '10500', '5', '86', '25', '1', '1', '0');
INSERT INTO `item` VALUES ('59', 'GYM Equip-2', 'new product', '../Resources/Images/shop/Health/2.png', '5000', '5', '87', '25', '1', '1', '0');
INSERT INTO `item` VALUES ('60', 'Fitness Ultra Speed', 'new product', '../Resources/Images/shop/Health/1.jpg', '5000', '5', '88', '21', '1', '1', '0');
INSERT INTO `item` VALUES ('62', 'Smart LG LED 43-inch', 'new product', '../Resources/Images/shop/TVs/2.jpg', '8700', '2', '90', '26', '1', '1', '0');
INSERT INTO `item` VALUES ('63', 'HP Z50 i3 15.5 inch', 'new product', '../Resources/Images/shop/Laptops/l3.jpg', '7500', '1', '91', '27', '1', '1', '0');
INSERT INTO `item` VALUES ('66', 'Fitness Equip', 'new product', '../Resources/Images/shop/Health/4.jpg', '8000', '5', '92', '26', '1', '1', '0');
INSERT INTO `item` VALUES ('67', 'GYM Equip 17-EQ', 'new product', '../Resources/Images/shop/Health/5.jpg', '15000', '5', '93', '26', '1', '1', '0');
INSERT INTO `item` VALUES ('68', 'GYM Equip 50cm', 'new product', '../Resources/Images/shop/Health/6.jpg', '4000', '5', '94', '26', '1', '1', '0');
INSERT INTO `item` VALUES ('69', 'Heavy GYM 17-G', 'new product', '../Resources/Images/shop/Health/7.jpg', '20500', '5', '95', '26', '1', '1', '0');
INSERT INTO `item` VALUES ('70', 'Fitness GYM Equip', 'new product', '../Resources/Images/shop/Health/8.jpg', '17000', '5', '96', '26', '1', '1', '0');
INSERT INTO `item` VALUES ('71', 'Multi Fitness Equip', 'new product', '../Resources/Images/shop/Health/9.jpg', '21500', '5', '97', '26', '1', '1', '0');
INSERT INTO `item` VALUES ('72', 'Super GYM Equip', 'new product', '../Resources/Images/shop/Health/10.jpg', '7500', '5', '98', '26', '1', '1', '0');
INSERT INTO `item` VALUES ('73', 'Dwight Instrument', 'new product', '../Resources/Images/shop/Electronics/1.jpg', '4000', '4', '99', '27', '1', '1', '0');
INSERT INTO `item` VALUES ('74', 'Electronic Calibration', 'new product', '../Resources/Images/shop/Electronics/2.jpg', '5000', '4', '100', '27', '1', '1', '0');
INSERT INTO `item` VALUES ('75', 'Power Quality Analyzer', 'new product', '../Resources/Images/shop/Electronics/3.jpg', '4000', '4', '101', '27', '1', '1', '0');
INSERT INTO `item` VALUES ('76', 'Megger MFT 1720', 'new product', '../Resources/Images/shop/Electronics/4.jpg', '3000', '4', '102', '27', '1', '1', '0');
INSERT INTO `item` VALUES ('77', 'Calibration Services ', 'new product', '../Resources/Images/shop/Electronics/5.jpg', '4000', '4', '103', '27', '1', '1', '0');
INSERT INTO `item` VALUES ('78', 'Test Equipment', 'new product', '../Resources/Images/shop/Electronics/6.jpg', '5000', '4', '105', '27', '1', '1', '0');
INSERT INTO `item` VALUES ('79', 'Black Addidas Shoes', 'new product', '../Resources/Images/shop/Fashion/1.jpg', '2200', '3', '109', '29', '1', '2', '0');
INSERT INTO `item` VALUES ('81', 'Black Addidas', 'new product', '../Resources/Images/shop/Fashion/1.jpg', '2200', '3', '104', '29', '1', '1', '0');
INSERT INTO `item` VALUES ('82', 'neo Addidas', 'new product', '../Resources/Images/shop/Fashion/2.jpg', '1500', '3', '106', '29', '1', '1', '0');
INSERT INTO `item` VALUES ('83', 'Simple Addidas White', 'new product', '../Resources/Images/shop/Fashion/3.jpg', '900', '3', '107', '29', '1', '1', '0');
INSERT INTO `item` VALUES ('84', 'Brown Watch Original', 'new product', '../Resources/Images/shop/Fashion/4.jpg', '1800', '3', '108', '29', '1', '1', '0');
INSERT INTO `item` VALUES ('85', 'Black Casio Watch', 'new product', '../Resources/Images/shop/Fashion/5.jpg', '2400', '3', '110', '29', '1', '1', '0');
INSERT INTO `item` VALUES ('86', 'DM Brown Model 17', 'new product', '../Resources/Images/shop/Fashion/6.jpg', '5000', '3', '111', '29', '1', '1', '0');
INSERT INTO `item` VALUES ('87', 'Simple Dress Gray', 'new product', '../Resources/Images/shop/Fashion/7.jpg', '2800', '3', '113', '29', '1', '1', '0');
INSERT INTO `item` VALUES ('88', 'Red Fashion Dress', 'new product', '../Resources/Images/shop/Fashion/8.jpg', '7000', '3', '109', '29', '1', '1', '0');
INSERT INTO `item` VALUES ('89', 'Addidas Black', 'new product', '../Resources/Images/shop/Fashion/1.jpg', '2500', '3', '112', '21', '1', '2', '0');
INSERT INTO `item` VALUES ('90', 'Smart tv', 'new product', '../Resources/Images/shop/TVs/2.jpg', '5000', '2', '114', '25', '1', '2', '0');
INSERT INTO `item` VALUES ('91', 'Smart tv', 'new product', '../Resources/Images/shop/TVs/1.jpg', '8500', '2', '114', '26', '1', '2', '0');
INSERT INTO `item` VALUES ('92', 'Smart Phone', 'new product', '../Resources/Images/shop/TVs/b7.jpg', '7500', '2', '114', '26', '1', '2', '0');
INSERT INTO `item` VALUES ('93', 'Black Addidas', 'new product', '../Resources/Images/shop/Fashion/1.jpg', '2400', '3', '112', '21', '1', '1', '0');

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notification
-- ----------------------------
INSERT INTO `notification` VALUES ('1', 'admin approve for your item');
INSERT INTO `notification` VALUES ('2', 'admin reject for your item');
INSERT INTO `notification` VALUES ('3', 'Your Participated Item \'s  Session was Started  , You Can Join Bid Session !');
INSERT INTO `notification` VALUES ('4', 'Your Participated Item \'s  Session was Ended , we will Notify you about result !');
INSERT INTO `notification` VALUES ('5', 'Configuration ! You Won The Last  Bid  Session , Best Wishes for next one !');
INSERT INTO `notification` VALUES ('6', 'Bad News !  You  Lose Your Last Bid Session , We Hope to You to Win Next Time ');
INSERT INTO `notification` VALUES ('7', 'Your Item Bid Session  Was Started  , we will notify You about any Updates');
INSERT INTO `notification` VALUES ('8', 'Your Item Bid Session Was Ended  , We will notify you for Result');
INSERT INTO `notification` VALUES ('9', 'Configuration For Selling Your Item in Our System , You Can Check Now for Results');

-- ----------------------------
-- Table structure for sessions
-- ----------------------------
DROP TABLE IF EXISTS `sessions`;
CREATE TABLE `sessions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Session_date` varchar(20) DEFAULT NULL,
  `Start_time` int(11) DEFAULT NULL,
  `End_time` int(11) DEFAULT NULL,
  `reserved` int(2) NOT NULL DEFAULT '0',
  `Winner_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Winner_ID` (`Winner_ID`),
  CONSTRAINT `sessions_ibfk_1` FOREIGN KEY (`Winner_ID`) REFERENCES `systemuser` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sessions
-- ----------------------------
INSERT INTO `sessions` VALUES ('66', '3-7-2017', '1', '2', '1', null);
INSERT INTO `sessions` VALUES ('67', '3-7-2017', '3', '4', '1', '36');
INSERT INTO `sessions` VALUES ('68', '3-7-2017', '5', '6', '1', null);
INSERT INTO `sessions` VALUES ('69', '3-7-2017', '7', '8', '1', null);
INSERT INTO `sessions` VALUES ('70', '3-7-2017', '9', '10', '1', null);
INSERT INTO `sessions` VALUES ('71', '3-7-2017', '11', '12', '1', null);
INSERT INTO `sessions` VALUES ('72', '3-7-2017', '13', '14', '1', null);
INSERT INTO `sessions` VALUES ('73', '3-7-2017', '15', '16', '1', null);
INSERT INTO `sessions` VALUES ('74', '3-7-2017', '17', '18', '1', null);
INSERT INTO `sessions` VALUES ('75', '3-7-2017', '19', '20', '1', null);
INSERT INTO `sessions` VALUES ('76', '3-7-2017', '21', '22', '1', null);
INSERT INTO `sessions` VALUES ('77', '3-7-2017', '23', '24', '1', null);
INSERT INTO `sessions` VALUES ('78', '4-7-2017', '1', '2', '1', null);
INSERT INTO `sessions` VALUES ('79', '4-7-2017', '3', '4', '1', null);
INSERT INTO `sessions` VALUES ('80', '4-7-2017', '5', '6', '1', null);
INSERT INTO `sessions` VALUES ('81', '4-7-2017', '7', '8', '1', null);
INSERT INTO `sessions` VALUES ('82', '4-7-2017', '9', '10', '1', null);
INSERT INTO `sessions` VALUES ('83', '4-7-2017', '11', '12', '1', null);
INSERT INTO `sessions` VALUES ('84', '4-7-2017', '13', '14', '1', null);
INSERT INTO `sessions` VALUES ('85', '4-7-2017', '15', '16', '1', null);
INSERT INTO `sessions` VALUES ('86', '4-7-2017', '17', '18', '1', null);
INSERT INTO `sessions` VALUES ('87', '4-7-2017', '19', '20', '1', null);
INSERT INTO `sessions` VALUES ('88', '4-7-2017', '21', '22', '1', null);
INSERT INTO `sessions` VALUES ('89', '4-7-2017', '23', '24', '1', null);
INSERT INTO `sessions` VALUES ('90', '5-7-2017', '1', '2', '1', null);
INSERT INTO `sessions` VALUES ('91', '5-7-2017', '3', '4', '1', null);
INSERT INTO `sessions` VALUES ('92', '5-7-2017', '5', '6', '1', null);
INSERT INTO `sessions` VALUES ('93', '5-7-2017', '7', '8', '1', null);
INSERT INTO `sessions` VALUES ('94', '5-7-2017', '9', '10', '1', null);
INSERT INTO `sessions` VALUES ('95', '5-7-2017', '11', '12', '1', null);
INSERT INTO `sessions` VALUES ('96', '5-7-2017', '13', '14', '1', null);
INSERT INTO `sessions` VALUES ('97', '5-7-2017', '15', '16', '1', null);
INSERT INTO `sessions` VALUES ('98', '5-7-2017', '17', '18', '1', null);
INSERT INTO `sessions` VALUES ('99', '5-7-2017', '19', '20', '1', null);
INSERT INTO `sessions` VALUES ('100', '5-7-2017', '21', '22', '1', null);
INSERT INTO `sessions` VALUES ('101', '5-7-2017', '23', '24', '1', null);
INSERT INTO `sessions` VALUES ('102', '6-7-2017', '1', '2', '1', null);
INSERT INTO `sessions` VALUES ('103', '6-7-2017', '3', '4', '1', null);
INSERT INTO `sessions` VALUES ('104', '6-7-2017', '5', '6', '1', null);
INSERT INTO `sessions` VALUES ('105', '6-7-2017', '7', '8', '1', null);
INSERT INTO `sessions` VALUES ('106', '6-7-2017', '9', '10', '1', null);
INSERT INTO `sessions` VALUES ('107', '6-7-2017', '11', '12', '1', null);
INSERT INTO `sessions` VALUES ('108', '6-7-2017', '13', '14', '1', null);
INSERT INTO `sessions` VALUES ('109', '6-7-2017', '15', '16', '1', null);
INSERT INTO `sessions` VALUES ('110', '6-7-2017', '17', '18', '1', null);
INSERT INTO `sessions` VALUES ('111', '6-7-2017', '19', '20', '1', null);
INSERT INTO `sessions` VALUES ('112', '6-7-2017', '21', '22', '1', null);
INSERT INTO `sessions` VALUES ('113', '6-7-2017', '23', '24', '1', null);
INSERT INTO `sessions` VALUES ('114', '7-7-2017', '1', '2', '0', null);
INSERT INTO `sessions` VALUES ('115', '7-7-2017', '23', '24', '0', null);

-- ----------------------------
-- Table structure for session_participants
-- ----------------------------
DROP TABLE IF EXISTS `session_participants`;
CREATE TABLE `session_participants` (
  `session_ID` int(11) NOT NULL,
  `bidder_ID` int(11) NOT NULL,
  `item_ID` int(11) NOT NULL,
  `price` double(50,0) DEFAULT '0',
  PRIMARY KEY (`session_ID`,`bidder_ID`,`item_ID`),
  KEY `Bidder_ID` (`bidder_ID`),
  KEY `session_participants_ibfk_3` (`item_ID`),
  CONSTRAINT `session_participants_ibfk_1` FOREIGN KEY (`session_ID`) REFERENCES `sessions` (`ID`),
  CONSTRAINT `session_participants_ibfk_2` FOREIGN KEY (`bidder_ID`) REFERENCES `systemuser` (`ID`),
  CONSTRAINT `session_participants_ibfk_3` FOREIGN KEY (`item_ID`) REFERENCES `item` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of session_participants
-- ----------------------------
INSERT INTO `session_participants` VALUES ('67', '22', '31', '9000');
INSERT INTO `session_participants` VALUES ('67', '24', '31', '8500');
INSERT INTO `session_participants` VALUES ('67', '28', '31', '0');
INSERT INTO `session_participants` VALUES ('67', '30', '31', '8000');
INSERT INTO `session_participants` VALUES ('67', '31', '31', '20000');
INSERT INTO `session_participants` VALUES ('67', '32', '31', '7000');
INSERT INTO `session_participants` VALUES ('67', '33', '31', '6800');
INSERT INTO `session_participants` VALUES ('67', '34', '31', '0');
INSERT INTO `session_participants` VALUES ('67', '35', '31', '0');
INSERT INTO `session_participants` VALUES ('67', '36', '31', '30000');
INSERT INTO `session_participants` VALUES ('68', '35', '32', '0');
INSERT INTO `session_participants` VALUES ('107', '36', '83', '0');
INSERT INTO `session_participants` VALUES ('112', '36', '93', '0');

-- ----------------------------
-- Table structure for systemuser
-- ----------------------------
DROP TABLE IF EXISTS `systemuser`;
CREATE TABLE `systemuser` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Fname` varchar(20) DEFAULT NULL,
  `Lname` varchar(20) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Pass` varchar(30) DEFAULT NULL,
  `profilePic` varchar(100) DEFAULT NULL,
  `Gender` int(1) DEFAULT NULL,
  `BirthDate` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `Authorization` int(1) DEFAULT NULL,
  `IP` varchar(50) DEFAULT NULL,
  `State` int(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemuser
-- ----------------------------
INSERT INTO `systemuser` VALUES ('1', 'Elhosany', 'ELAdmin', 'admin22@gmail.com', '123', '../Resources/Images/admin.jpg', '0', '8/7/2001', '01097706982', '0', '192.168.1.14', '1');
INSERT INTO `systemuser` VALUES ('21', 'Mohamed', 'Elseller', 's', 's', '../Resources/Images/ProfilePics/b3.jpg', '0', '8/7/2001', '01097706982', '1', '192.168.1.14', '0');
INSERT INTO `systemuser` VALUES ('22', 'Mohamed', 'Elbidder', 'b', 'b', '../Resources/Images/faces/3.png', '0', '8/7/2001', '01097706982', '2', '192.168.1.14', '0');
INSERT INTO `systemuser` VALUES ('24', 'Shady', 'Mohamed', 'b2@gmail.com', '123', '../Resources/Images/faces/2.png', '0', '7/7/2001', '222', '2', '192.168.1.14', '0');
INSERT INTO `systemuser` VALUES ('25', 's', '2', 's2', 's2', '../Resources/Images/faces/2.png', '0', '8/7/2001', '12', '1', '192.168.1.14', '0');
INSERT INTO `systemuser` VALUES ('26', 'Mohamed', 'Hasan', 'mseller@gmail.com', '123', '../Resources/Images/ProfilePics/b5.jpg', '0', '7/8/2001', '0106010416', '1', '192.168.1.14', '0');
INSERT INTO `systemuser` VALUES ('27', 'Mary', 'Zaki', 'maryseller@gmail.com', '123', '../Resources/Images/ProfilePics/seller.jpg', '0', '8/8/2001', '0123526315', '1', '192.168.1.14', '0');
INSERT INTO `systemuser` VALUES ('28', 'Mohamed', 'Saleem', 'b2@gmail.com', '123', '../Resources/Images/ProfilePics/b3.jpg', '0', '9/8/2001', '01112980694', '2', '192.168.1.14', '0');
INSERT INTO `systemuser` VALUES ('29', 'Nader', 'Saif', 's2@gmail.com', '123', '../Resources/Images/ProfilePics/s1.jpg', '0', '8/8/1999', '0112532502', '1', '192.168.1.14', '0');
INSERT INTO `systemuser` VALUES ('30', 'Saif ', 'Gamal', 'b3@gmail.com', '123', '../Resources/Images/ProfilePics/b3.jpg', '0', '9/5/2001', '01253555156', '2', '192.168.1.14', '0');
INSERT INTO `systemuser` VALUES ('31', 'Samy', 'Khaled', 'b4@gmail.com', '123', '../Resources/Images/ProfilePics/b5.jpg', '0', '8/8/1999', '01054848514', '2', '192.168.1.14', '0');
INSERT INTO `systemuser` VALUES ('32', 'Reda', 'Zaher', 'b5@gmail.com', '123', '../Resources/Images/ProfilePics/b6.jpg', '0', '8/7/1996', '01154512654', '2', '192.168.1.14', '0');
INSERT INTO `systemuser` VALUES ('33', 'Ahmed', 'Ali', 'b6@gmail.com', '123', '../Resources/Images/ProfilePics/bidder.jpg', '0', '7/7/2000', '01112536255', '2', '192.168.1.14', '0');
INSERT INTO `systemuser` VALUES ('34', 'Usama', 'Nabil', 'b7@gmail.com', '123', '../Resources/Images/ProfilePics/s1.jpg', '0', '9/5/2000', '01006010525', '2', '127.0.0.1', '0');
INSERT INTO `systemuser` VALUES ('35', 'New', 'Bidder', 'mbidder@gmail.com', '123', '../Resources/Images/ProfilePics/admin.jpg', '0', '6/6/1995', '01097706982', '2', '192.168.1.14', '0');
INSERT INTO `systemuser` VALUES ('36', 'Mohamed', 'Hasan', 'bidder2@gmail.com', '123', '../Resources/Images/ProfilePics/admin.jpg', '0', '8/8/1995', '01112980611', '2', '192.168.1.14', '0');

-- ----------------------------
-- Table structure for user_notification
-- ----------------------------
DROP TABLE IF EXISTS `user_notification`;
CREATE TABLE `user_notification` (
  `User_ID` int(11) NOT NULL,
  `notification_ID` int(11) NOT NULL,
  PRIMARY KEY (`User_ID`,`notification_ID`),
  KEY `notification_ID` (`notification_ID`),
  CONSTRAINT `user_notification_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `systemuser` (`ID`),
  CONSTRAINT `user_notification_ibfk_2` FOREIGN KEY (`notification_ID`) REFERENCES `notification` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_notification
-- ----------------------------
