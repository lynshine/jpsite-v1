/*
Navicat MySQL Data Transfer

Source Server         : 192.168.3.152
Source Server Version : 50722
Source Host           : 192.168.3.152:3306
Source Database       : jp_admin

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-11-13 10:57:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) NOT NULL COMMENT '菜单名',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '菜单路径',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` timestamp NOT NULL  ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('tangyan', 'COw9h0Am4Ea/0R2no2qP/A==', '1CjLa0jd79XeiioUi74JXw==', '2018-11-12 20:43:09');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '角色名',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` timestamp NOT NULL  ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `menu_id` int(1) NOT NULL COMMENT '菜单ID',
  `role_id` int(1) NOT NULL COMMENT '角色ID',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` timestamp NOT NULL  ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `login_name` varchar(255) NOT NULL DEFAULT '' COMMENT '登录名',
  `login_password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` timestamp NOT NULL  ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2439 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('232', '李白5', '', '', '2018-11-06 14:59:16', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2336', '李白0', '', '', '2018-11-06 15:19:11', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2337', '李白0', '', '', '2018-11-06 15:19:11', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2338', '李白1', '', '', '2018-11-06 15:19:11', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2339', '李白2', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2340', '李白3', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2341', '李白3', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2342', '李白5', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2343', '李白6', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2344', '李白7', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2345', '李白8', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2346', '李白8', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2347', '李白10', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2348', '李白11', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2349', '李白12', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2350', '李白13', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2351', '李白13', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2352', '李白15', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2353', '李白16', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2354', '李白17', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2355', '李白18', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2356', '李白18', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2357', '李白20', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2358', '李白21', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2359', '李白22', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2360', '李白23', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2361', '李白23', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2362', '李白25', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2363', '李白26', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2364', '李白27', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2365', '李白28', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2366', '李白28', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2367', '李白30', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2368', '李白31', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2369', '李白32', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2370', '李白33', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2371', '李白33', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2372', '李白35', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2373', '李白36', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2374', '李白37', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2375', '李白38', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2376', '李白38', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2377', '李白40', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2378', '李白41', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2379', '李白42', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2380', '李白43', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2381', '李白43', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2382', '李白45', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2383', '李白46', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2384', '李白47', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2385', '李白48', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2386', '李白48', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2387', '李白50', '', '', '2018-11-06 15:19:12', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2388', '李白51', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2389', '李白52', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2390', '李白53', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2391', '李白53', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2392', '李白55', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2393', '李白56', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2394', '李白57', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2395', '李白58', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2396', '李白58', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2397', '李白60', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2398', '李白61', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2399', '李白62', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2400', '李白63', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2401', '李白63', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2402', '李白65', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2403', '李白66', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2404', '李白67', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2405', '李白68', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2406', '李白68', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2407', '李白70', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2408', '李白71', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2409', '李白72', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2410', '李白73', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2411', '李白74', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2412', '李白75', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2413', '李白75', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2414', '李白76', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2415', '李白77', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2416', '李白78', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2417', '李白79', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2418', '李白80', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2419', '李白81', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2420', '李白82', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2421', '李白83', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2422', '李白83', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2423', '李白85', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2424', '李白85', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2425', '李白86', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2426', '李白88', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2427', '李白89', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2428', '李白90', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2429', '李白91', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2430', '李白92', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2431', '李白93', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2432', '李白93', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2433', '李白95', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2434', '李白95', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2435', '李白97', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2436', '李白98', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2437', '李白99', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');
INSERT INTO `user` VALUES ('2438', '李白100', '', '', '2018-11-06 15:19:13', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` timestamp NOT NULL  ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
