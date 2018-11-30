/*
 Navicat MySQL Data Transfer

 Source Server         : 192.168.3.7
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 192.168.3.7:3306
 Source Schema         : ApolloConfigDB

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 13/11/2018 00:23:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for App
-- ----------------------------
DROP TABLE IF EXISTS `App`;
CREATE TABLE `App` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `AppId` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'AppID',
  `Name` varchar(500) NOT NULL DEFAULT 'default' COMMENT '应用名',
  `OrgId` varchar(32) NOT NULL DEFAULT 'default' COMMENT '部门Id',
  `OrgName` varchar(64) NOT NULL DEFAULT 'default' COMMENT '部门名字',
  `OwnerName` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'ownerName',
  `OwnerEmail` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'ownerEmail',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `AppId` (`AppId`(191)),
  KEY `DataChange_LastTime` (`DataChange_LastTime`),
  KEY `IX_Name` (`Name`(191))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='应用表';

-- ----------------------------
-- Records of App
-- ----------------------------
BEGIN;
INSERT INTO `App` VALUES (1, 'jpsite_apollo_001', 'jpsite-server', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', b'0', 'apollo', '2018-11-11 16:35:31', 'apollo', '2018-11-11 16:35:31');
COMMIT;

-- ----------------------------
-- Table structure for AppNamespace
-- ----------------------------
DROP TABLE IF EXISTS `AppNamespace`;
CREATE TABLE `AppNamespace` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `Name` varchar(32) NOT NULL DEFAULT '' COMMENT 'namespace名字，注意，需要全局唯一',
  `AppId` varchar(32) NOT NULL DEFAULT '' COMMENT 'app id',
  `Format` varchar(32) NOT NULL DEFAULT 'properties' COMMENT 'namespace的format类型',
  `IsPublic` bit(1) NOT NULL DEFAULT b'0' COMMENT 'namespace是否为公共',
  `Comment` varchar(64) NOT NULL DEFAULT '' COMMENT '注释',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `IX_AppId` (`AppId`),
  KEY `Name_AppId` (`Name`,`AppId`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='应用namespace定义';

-- ----------------------------
-- Records of AppNamespace
-- ----------------------------
BEGIN;
INSERT INTO `AppNamespace` VALUES (1, 'application', 'jpsite_apollo_001', 'properties', b'0', 'default app namespace', b'0', 'apollo', '2018-11-11 16:35:32', 'apollo', '2018-11-11 16:35:32');
COMMIT;

-- ----------------------------
-- Table structure for Audit
-- ----------------------------
DROP TABLE IF EXISTS `Audit`;
CREATE TABLE `Audit` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `EntityName` varchar(50) NOT NULL DEFAULT 'default' COMMENT '表名',
  `EntityId` int(10) unsigned DEFAULT NULL COMMENT '记录ID',
  `OpName` varchar(50) NOT NULL DEFAULT 'default' COMMENT '操作类型',
  `Comment` varchar(500) DEFAULT NULL COMMENT '备注',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='日志审计表';

-- ----------------------------
-- Records of Audit
-- ----------------------------
BEGIN;
INSERT INTO `Audit` VALUES (1, 'App', 1, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 16:35:32', NULL, '2018-11-11 16:35:32');
INSERT INTO `Audit` VALUES (2, 'AppNamespace', 1, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 16:35:32', NULL, '2018-11-11 16:35:32');
INSERT INTO `Audit` VALUES (3, 'Cluster', 1, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 16:35:32', NULL, '2018-11-11 16:35:32');
INSERT INTO `Audit` VALUES (4, 'Namespace', 1, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 16:35:33', NULL, '2018-11-11 16:35:33');
INSERT INTO `Audit` VALUES (5, 'Item', 1, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 16:41:40', NULL, '2018-11-11 16:41:40');
INSERT INTO `Audit` VALUES (6, 'Release', 1, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 16:41:48', NULL, '2018-11-11 16:41:48');
INSERT INTO `Audit` VALUES (7, 'ReleaseHistory', 1, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 16:41:48', NULL, '2018-11-11 16:41:48');
INSERT INTO `Audit` VALUES (8, 'Item', 2, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 17:36:05', NULL, '2018-11-11 17:36:05');
INSERT INTO `Audit` VALUES (9, 'Release', 2, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 17:36:11', NULL, '2018-11-11 17:36:11');
INSERT INTO `Audit` VALUES (10, 'ReleaseHistory', 2, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 17:36:11', NULL, '2018-11-11 17:36:11');
INSERT INTO `Audit` VALUES (11, 'Item', 1, 'UPDATE', NULL, b'0', 'apollo', '2018-11-11 17:40:28', NULL, '2018-11-11 17:40:28');
INSERT INTO `Audit` VALUES (12, 'Item', 1, 'UPDATE', NULL, b'0', 'apollo', '2018-11-11 17:42:43', NULL, '2018-11-11 17:42:43');
INSERT INTO `Audit` VALUES (13, 'Release', 3, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 17:45:02', NULL, '2018-11-11 17:45:02');
INSERT INTO `Audit` VALUES (14, 'ReleaseHistory', 3, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 17:45:02', NULL, '2018-11-11 17:45:02');
INSERT INTO `Audit` VALUES (15, 'Item', 1, 'UPDATE', NULL, b'0', 'apollo', '2018-11-11 19:14:55', NULL, '2018-11-11 19:14:55');
INSERT INTO `Audit` VALUES (16, 'Release', 4, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 19:15:00', NULL, '2018-11-11 19:15:00');
INSERT INTO `Audit` VALUES (17, 'ReleaseHistory', 4, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 19:15:00', NULL, '2018-11-11 19:15:00');
INSERT INTO `Audit` VALUES (18, 'Item', 2, 'UPDATE', NULL, b'0', 'apollo', '2018-11-11 19:44:24', NULL, '2018-11-11 19:44:24');
INSERT INTO `Audit` VALUES (19, 'Item', 2, 'UPDATE', NULL, b'0', 'apollo', '2018-11-11 19:44:35', NULL, '2018-11-11 19:44:35');
INSERT INTO `Audit` VALUES (20, 'Release', 5, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 19:44:38', NULL, '2018-11-11 19:44:38');
INSERT INTO `Audit` VALUES (21, 'ReleaseHistory', 5, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 19:44:38', NULL, '2018-11-11 19:44:38');
INSERT INTO `Audit` VALUES (22, 'Item', 3, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 20:08:29', NULL, '2018-11-11 20:08:29');
INSERT INTO `Audit` VALUES (23, 'Release', 6, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 20:09:40', NULL, '2018-11-11 20:09:40');
INSERT INTO `Audit` VALUES (24, 'ReleaseHistory', 6, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 20:09:40', NULL, '2018-11-11 20:09:40');
INSERT INTO `Audit` VALUES (25, 'Item', 3, 'DELETE', NULL, b'0', 'apollo', '2018-11-11 20:10:33', NULL, '2018-11-11 20:10:33');
INSERT INTO `Audit` VALUES (26, 'Item', 4, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 20:10:40', NULL, '2018-11-11 20:10:40');
INSERT INTO `Audit` VALUES (27, 'Release', 7, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 20:10:44', NULL, '2018-11-11 20:10:44');
INSERT INTO `Audit` VALUES (28, 'ReleaseHistory', 7, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 20:10:44', NULL, '2018-11-11 20:10:44');
INSERT INTO `Audit` VALUES (29, 'Item', 4, 'UPDATE', NULL, b'0', 'apollo', '2018-11-11 20:15:16', NULL, '2018-11-11 20:15:16');
INSERT INTO `Audit` VALUES (30, 'Release', 8, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 20:15:23', NULL, '2018-11-11 20:15:23');
INSERT INTO `Audit` VALUES (31, 'ReleaseHistory', 8, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 20:15:23', NULL, '2018-11-11 20:15:23');
INSERT INTO `Audit` VALUES (32, 'Item', 4, 'UPDATE', NULL, b'0', 'apollo', '2018-11-11 20:17:55', NULL, '2018-11-11 20:17:55');
INSERT INTO `Audit` VALUES (33, 'Release', 9, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 20:17:59', NULL, '2018-11-11 20:17:59');
INSERT INTO `Audit` VALUES (34, 'ReleaseHistory', 9, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 20:17:59', NULL, '2018-11-11 20:17:59');
INSERT INTO `Audit` VALUES (35, 'Item', 4, 'UPDATE', NULL, b'0', 'apollo', '2018-11-11 22:26:43', NULL, '2018-11-11 22:26:43');
INSERT INTO `Audit` VALUES (36, 'Release', 10, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 22:26:47', NULL, '2018-11-11 22:26:47');
INSERT INTO `Audit` VALUES (37, 'ReleaseHistory', 10, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 22:26:47', NULL, '2018-11-11 22:26:47');
INSERT INTO `Audit` VALUES (38, 'Item', 1, 'UPDATE', NULL, b'0', 'apollo', '2018-11-11 23:46:35', NULL, '2018-11-11 23:46:35');
INSERT INTO `Audit` VALUES (39, 'Release', 11, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 23:46:39', NULL, '2018-11-11 23:46:39');
INSERT INTO `Audit` VALUES (40, 'ReleaseHistory', 11, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 23:46:39', NULL, '2018-11-11 23:46:39');
INSERT INTO `Audit` VALUES (41, 'Item', 4, 'UPDATE', NULL, b'0', 'apollo', '2018-11-11 23:47:59', NULL, '2018-11-11 23:47:59');
INSERT INTO `Audit` VALUES (42, 'Release', 12, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 23:48:01', NULL, '2018-11-11 23:48:01');
INSERT INTO `Audit` VALUES (43, 'ReleaseHistory', 12, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 23:48:01', NULL, '2018-11-11 23:48:01');
INSERT INTO `Audit` VALUES (44, 'Item', 4, 'UPDATE', NULL, b'0', 'apollo', '2018-11-11 23:55:40', NULL, '2018-11-11 23:55:40');
INSERT INTO `Audit` VALUES (45, 'Release', 13, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 23:55:59', NULL, '2018-11-11 23:55:59');
INSERT INTO `Audit` VALUES (46, 'ReleaseHistory', 13, 'INSERT', NULL, b'0', 'apollo', '2018-11-11 23:55:59', NULL, '2018-11-11 23:55:59');
INSERT INTO `Audit` VALUES (47, 'Item', 2, 'UPDATE', NULL, b'0', 'apollo', '2018-11-12 00:32:23', NULL, '2018-11-12 00:32:23');
INSERT INTO `Audit` VALUES (48, 'Release', 14, 'INSERT', NULL, b'0', 'apollo', '2018-11-12 00:32:26', NULL, '2018-11-12 00:32:26');
INSERT INTO `Audit` VALUES (49, 'ReleaseHistory', 14, 'INSERT', NULL, b'0', 'apollo', '2018-11-12 00:32:26', NULL, '2018-11-12 00:32:26');
INSERT INTO `Audit` VALUES (50, 'Item', 4, 'UPDATE', NULL, b'0', 'apollo', '2018-11-12 00:35:36', NULL, '2018-11-12 00:35:36');
INSERT INTO `Audit` VALUES (51, 'Release', 15, 'INSERT', NULL, b'0', 'apollo', '2018-11-12 00:35:53', NULL, '2018-11-12 00:35:53');
INSERT INTO `Audit` VALUES (52, 'ReleaseHistory', 15, 'INSERT', NULL, b'0', 'apollo', '2018-11-12 00:35:53', NULL, '2018-11-12 00:35:53');
COMMIT;

-- ----------------------------
-- Table structure for Cluster
-- ----------------------------
DROP TABLE IF EXISTS `Cluster`;
CREATE TABLE `Cluster` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `Name` varchar(32) NOT NULL DEFAULT '' COMMENT '集群名字',
  `AppId` varchar(32) NOT NULL DEFAULT '' COMMENT 'App id',
  `ParentClusterId` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '父cluster',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `IX_AppId_Name` (`AppId`,`Name`),
  KEY `IX_ParentClusterId` (`ParentClusterId`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='集群';

-- ----------------------------
-- Records of Cluster
-- ----------------------------
BEGIN;
INSERT INTO `Cluster` VALUES (1, 'default', 'jpsite_apollo_001', 0, b'0', 'apollo', '2018-11-11 16:35:32', 'apollo', '2018-11-11 16:35:32');
COMMIT;

-- ----------------------------
-- Table structure for Commit
-- ----------------------------
DROP TABLE IF EXISTS `Commit`;
CREATE TABLE `Commit` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ChangeSets` longtext NOT NULL COMMENT '修改变更集',
  `AppId` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'AppID',
  `ClusterName` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'ClusterName',
  `NamespaceName` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'namespaceName',
  `Comment` varchar(500) DEFAULT NULL COMMENT '备注',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`),
  KEY `AppId` (`AppId`(191)),
  KEY `ClusterName` (`ClusterName`(191)),
  KEY `NamespaceName` (`NamespaceName`(191))
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='commit 历史表';

-- ----------------------------
-- Records of Commit
-- ----------------------------
BEGIN;
INSERT INTO `Commit` VALUES (1, '{\"createItems\":[{\"namespaceId\":1,\"key\":\"myConf\",\"value\":\"我的配置\",\"lineNum\":1,\"id\":1,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 16:41:39\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 16:41:39\"}],\"updateItems\":[],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-11 16:41:40', 'apollo', '2018-11-11 16:41:40');
INSERT INTO `Commit` VALUES (2, '{\"createItems\":[{\"namespaceId\":1,\"key\":\"jpsiteConf\",\"value\":\"jpsiteConfjpsiteConf\",\"lineNum\":2,\"id\":2,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 17:36:05\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 17:36:05\"}],\"updateItems\":[],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-11 17:36:05', 'apollo', '2018-11-11 17:36:05');
INSERT INTO `Commit` VALUES (3, '{\"createItems\":[],\"updateItems\":[{\"oldItem\":{\"namespaceId\":1,\"key\":\"myConf\",\"value\":\"我的配置\",\"comment\":\"\",\"lineNum\":1,\"id\":1,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 16:41:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 17:40:28\"},\"newItem\":{\"namespaceId\":1,\"key\":\"myConf\",\"value\":\"我的配置j\",\"comment\":\"\",\"lineNum\":1,\"id\":1,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 16:41:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 17:42:42\"}}],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-11 17:42:43', 'apollo', '2018-11-11 17:42:43');
INSERT INTO `Commit` VALUES (4, '{\"createItems\":[],\"updateItems\":[{\"oldItem\":{\"namespaceId\":1,\"key\":\"myConf\",\"value\":\"我的配置j\",\"comment\":\"\",\"lineNum\":1,\"id\":1,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 16:41:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 17:42:43\"},\"newItem\":{\"namespaceId\":1,\"key\":\"myConf\",\"value\":\"我的配置jkml\",\"comment\":\"\",\"lineNum\":1,\"id\":1,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 16:41:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 19:14:55\"}}],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-11 19:14:55', 'apollo', '2018-11-11 19:14:55');
INSERT INTO `Commit` VALUES (5, '{\"createItems\":[],\"updateItems\":[{\"oldItem\":{\"namespaceId\":1,\"key\":\"jpsiteConf\",\"value\":\"jpsiteConfjpsiteConf\",\"lineNum\":2,\"id\":2,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 17:36:05\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 17:36:05\"},\"newItem\":{\"namespaceId\":1,\"key\":\"jpsiteConf\",\"value\":\"jpsiteConfjpsiteConf awe\",\"comment\":\"\",\"lineNum\":2,\"id\":2,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 17:36:05\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 19:44:24\"}}],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-11 19:44:24', 'apollo', '2018-11-11 19:44:24');
INSERT INTO `Commit` VALUES (6, '{\"createItems\":[],\"updateItems\":[{\"oldItem\":{\"namespaceId\":1,\"key\":\"jpsiteConf\",\"value\":\"jpsiteConfjpsiteConf awe\",\"comment\":\"\",\"lineNum\":2,\"id\":2,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 17:36:05\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 19:44:24\"},\"newItem\":{\"namespaceId\":1,\"key\":\"jpsiteConf\",\"value\":\"jpsiteCon\",\"comment\":\"\",\"lineNum\":2,\"id\":2,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 17:36:05\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 19:44:35\"}}],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-11 19:44:35', 'apollo', '2018-11-11 19:44:35');
INSERT INTO `Commit` VALUES (7, '{\"createItems\":[{\"namespaceId\":1,\"key\":\"apollo-com.mty.jpsite.server.job\",\"value\":\"apollo-com.mty.jpsite.server.job\",\"lineNum\":3,\"id\":3,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 20:08:29\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 20:08:29\"}],\"updateItems\":[],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-11 20:08:29', 'apollo', '2018-11-11 20:08:29');
INSERT INTO `Commit` VALUES (8, '{\"createItems\":[],\"updateItems\":[],\"deleteItems\":[{\"namespaceId\":1,\"key\":\"apollo-com.mty.jpsite.server.job\",\"value\":\"apollo-com.mty.jpsite.server.job\",\"lineNum\":3,\"id\":3,\"isDeleted\":true,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 20:08:29\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 20:10:33\"}]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-11 20:10:33', 'apollo', '2018-11-11 20:10:33');
INSERT INTO `Commit` VALUES (9, '{\"createItems\":[{\"namespaceId\":1,\"key\":\"apolloConfig\",\"value\":\"apolloConfig\",\"lineNum\":3,\"id\":4,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 20:10:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 20:10:40\"}],\"updateItems\":[],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-11 20:10:40', 'apollo', '2018-11-11 20:10:40');
INSERT INTO `Commit` VALUES (10, '{\"createItems\":[],\"updateItems\":[{\"oldItem\":{\"namespaceId\":1,\"key\":\"apolloConfig\",\"value\":\"apolloConfig\",\"lineNum\":3,\"id\":4,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 20:10:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 20:10:40\"},\"newItem\":{\"namespaceId\":1,\"key\":\"apolloConfig\",\"value\":\"joiwjfeow\",\"comment\":\"\",\"lineNum\":3,\"id\":4,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 20:10:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 20:15:15\"}}],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-11 20:15:16', 'apollo', '2018-11-11 20:15:16');
INSERT INTO `Commit` VALUES (11, '{\"createItems\":[],\"updateItems\":[{\"oldItem\":{\"namespaceId\":1,\"key\":\"apolloConfig\",\"value\":\"joiwjfeow\",\"comment\":\"\",\"lineNum\":3,\"id\":4,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 20:10:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 20:15:16\"},\"newItem\":{\"namespaceId\":1,\"key\":\"apolloConfig\",\"value\":\"324\",\"comment\":\"\",\"lineNum\":3,\"id\":4,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 20:10:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 20:17:54\"}}],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-11 20:17:55', 'apollo', '2018-11-11 20:17:55');
INSERT INTO `Commit` VALUES (12, '{\"createItems\":[],\"updateItems\":[{\"oldItem\":{\"namespaceId\":1,\"key\":\"apolloConfig\",\"value\":\"324\",\"comment\":\"\",\"lineNum\":3,\"id\":4,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 20:10:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 20:17:55\"},\"newItem\":{\"namespaceId\":1,\"key\":\"apolloConfig\",\"value\":\"324kk\",\"comment\":\"\",\"lineNum\":3,\"id\":4,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 20:10:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 22:26:42\"}}],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-11 22:26:43', 'apollo', '2018-11-11 22:26:43');
INSERT INTO `Commit` VALUES (13, '{\"createItems\":[],\"updateItems\":[{\"oldItem\":{\"namespaceId\":1,\"key\":\"myConf\",\"value\":\"我的配置jkml\",\"comment\":\"\",\"lineNum\":1,\"id\":1,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 16:41:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 19:14:55\"},\"newItem\":{\"namespaceId\":1,\"key\":\"myConf\",\"value\":\"我的配置jkm\",\"comment\":\"\",\"lineNum\":1,\"id\":1,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 16:41:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 23:46:35\"}}],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-11 23:46:35', 'apollo', '2018-11-11 23:46:35');
INSERT INTO `Commit` VALUES (14, '{\"createItems\":[],\"updateItems\":[{\"oldItem\":{\"namespaceId\":1,\"key\":\"apolloConfig\",\"value\":\"324kk\",\"comment\":\"\",\"lineNum\":3,\"id\":4,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 20:10:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 22:26:43\"},\"newItem\":{\"namespaceId\":1,\"key\":\"apolloConfig\",\"value\":\"324\",\"comment\":\"\",\"lineNum\":3,\"id\":4,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 20:10:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 23:47:58\"}}],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-11 23:47:59', 'apollo', '2018-11-11 23:47:59');
INSERT INTO `Commit` VALUES (15, '{\"createItems\":[],\"updateItems\":[{\"oldItem\":{\"namespaceId\":1,\"key\":\"apolloConfig\",\"value\":\"324\",\"comment\":\"\",\"lineNum\":3,\"id\":4,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 20:10:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 23:47:59\"},\"newItem\":{\"namespaceId\":1,\"key\":\"apolloConfig\",\"value\":\"324d\",\"comment\":\"\",\"lineNum\":3,\"id\":4,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 20:10:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 23:55:40\"}}],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-11 23:55:40', 'apollo', '2018-11-11 23:55:40');
INSERT INTO `Commit` VALUES (16, '{\"createItems\":[],\"updateItems\":[{\"oldItem\":{\"namespaceId\":1,\"key\":\"jpsiteConf\",\"value\":\"jpsiteCon\",\"comment\":\"\",\"lineNum\":2,\"id\":2,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 17:36:05\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 19:44:35\"},\"newItem\":{\"namespaceId\":1,\"key\":\"jpsiteConf\",\"value\":\"jpsiteCond\",\"comment\":\"\",\"lineNum\":2,\"id\":2,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 17:36:05\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-12 00:32:23\"}}],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-12 00:32:23', 'apollo', '2018-11-12 00:32:23');
INSERT INTO `Commit` VALUES (17, '{\"createItems\":[],\"updateItems\":[{\"oldItem\":{\"namespaceId\":1,\"key\":\"apolloConfig\",\"value\":\"324d\",\"comment\":\"\",\"lineNum\":3,\"id\":4,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 20:10:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-11 23:55:40\"},\"newItem\":{\"namespaceId\":1,\"key\":\"apolloConfig\",\"value\":\"324dd\",\"comment\":\"\",\"lineNum\":3,\"id\":4,\"isDeleted\":false,\"dataChangeCreatedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2018-11-11 20:10:40\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeLastModifiedTime\":\"2018-11-12 00:35:35\"}}],\"deleteItems\":[]}', 'jpsite_apollo_001', 'default', 'application', NULL, b'0', 'apollo', '2018-11-12 00:35:36', 'apollo', '2018-11-12 00:35:36');
COMMIT;

-- ----------------------------
-- Table structure for GrayReleaseRule
-- ----------------------------
DROP TABLE IF EXISTS `GrayReleaseRule`;
CREATE TABLE `GrayReleaseRule` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `AppId` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'AppID',
  `ClusterName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'Cluster Name',
  `NamespaceName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'Namespace Name',
  `BranchName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'branch name',
  `Rules` varchar(16000) DEFAULT '[]' COMMENT '灰度规则',
  `ReleaseId` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '灰度对应的release',
  `BranchStatus` tinyint(2) DEFAULT '1' COMMENT '灰度分支状态: 0:删除分支,1:正在使用的规则 2：全量发布',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`),
  KEY `IX_Namespace` (`AppId`,`ClusterName`,`NamespaceName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='灰度规则表';

-- ----------------------------
-- Table structure for Instance
-- ----------------------------
DROP TABLE IF EXISTS `Instance`;
CREATE TABLE `Instance` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `AppId` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'AppID',
  `ClusterName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'ClusterName',
  `DataCenter` varchar(64) NOT NULL DEFAULT 'default' COMMENT 'Data Center Name',
  `Ip` varchar(32) NOT NULL DEFAULT '' COMMENT 'instance ip',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `IX_UNIQUE_KEY` (`AppId`,`ClusterName`,`Ip`,`DataCenter`),
  KEY `IX_IP` (`Ip`),
  KEY `IX_DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='使用配置的应用实例';

-- ----------------------------
-- Records of Instance
-- ----------------------------
BEGIN;
INSERT INTO `Instance` VALUES (1, 'jpsite_apollo_001', 'default', '', '192.168.3.7', '2018-11-11 19:21:49', '2018-11-11 19:21:49');
COMMIT;

-- ----------------------------
-- Table structure for InstanceConfig
-- ----------------------------
DROP TABLE IF EXISTS `InstanceConfig`;
CREATE TABLE `InstanceConfig` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `InstanceId` int(11) unsigned DEFAULT NULL COMMENT 'Instance Id',
  `ConfigAppId` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'Config App Id',
  `ConfigClusterName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'Config Cluster Name',
  `ConfigNamespaceName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'Config Namespace Name',
  `ReleaseKey` varchar(64) NOT NULL DEFAULT '' COMMENT '发布的Key',
  `ReleaseDeliveryTime` timestamp NULL DEFAULT NULL COMMENT '配置获取时间',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `IX_UNIQUE_KEY` (`InstanceId`,`ConfigAppId`,`ConfigNamespaceName`),
  KEY `IX_ReleaseKey` (`ReleaseKey`),
  KEY `IX_DataChange_LastTime` (`DataChange_LastTime`),
  KEY `IX_Valid_Namespace` (`ConfigAppId`,`ConfigClusterName`,`ConfigNamespaceName`,`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='应用实例的配置信息';

-- ----------------------------
-- Records of InstanceConfig
-- ----------------------------
BEGIN;
INSERT INTO `InstanceConfig` VALUES (1, 1, 'jpsite_apollo_001', 'default', 'application', '20181112003553-5b81c9e77c3270ba', '2018-11-12 00:35:54', '2018-11-11 19:21:49', '2018-11-12 00:35:55');
COMMIT;

-- ----------------------------
-- Table structure for Item
-- ----------------------------
DROP TABLE IF EXISTS `Item`;
CREATE TABLE `Item` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `NamespaceId` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '集群NamespaceId',
  `Key` varchar(128) NOT NULL DEFAULT 'default' COMMENT '配置项Key',
  `Value` longtext NOT NULL COMMENT '配置项值',
  `Comment` varchar(1024) DEFAULT '' COMMENT '注释',
  `LineNum` int(10) unsigned DEFAULT '0' COMMENT '行号',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `IX_GroupId` (`NamespaceId`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='配置项目';

-- ----------------------------
-- Records of Item
-- ----------------------------
BEGIN;
INSERT INTO `Item` VALUES (1, 1, 'myConf', '我的配置jkm', '', 1, b'0', 'apollo', '2018-11-11 16:41:40', 'apollo', '2018-11-11 23:46:35');
INSERT INTO `Item` VALUES (2, 1, 'jpsiteConf', 'jpsiteCond', '', 2, b'0', 'apollo', '2018-11-11 17:36:05', 'apollo', '2018-11-12 00:32:23');
INSERT INTO `Item` VALUES (3, 1, 'apollo-com.mty.jpsite.server.job', 'apollo-com.mty.jpsite.server.job', NULL, 3, b'1', 'apollo', '2018-11-11 20:08:29', 'apollo', '2018-11-11 20:10:33');
INSERT INTO `Item` VALUES (4, 1, 'apolloConfig', '324dd', '', 3, b'0', 'apollo', '2018-11-11 20:10:40', 'apollo', '2018-11-12 00:35:36');
COMMIT;

-- ----------------------------
-- Table structure for Namespace
-- ----------------------------
DROP TABLE IF EXISTS `Namespace`;
CREATE TABLE `Namespace` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `AppId` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'AppID',
  `ClusterName` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'Cluster Name',
  `NamespaceName` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'Namespace Name',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `AppId_ClusterName_NamespaceName` (`AppId`(191),`ClusterName`(191),`NamespaceName`(191)),
  KEY `DataChange_LastTime` (`DataChange_LastTime`),
  KEY `IX_NamespaceName` (`NamespaceName`(191))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='命名空间';

-- ----------------------------
-- Records of Namespace
-- ----------------------------
BEGIN;
INSERT INTO `Namespace` VALUES (1, 'jpsite_apollo_001', 'default', 'application', b'0', 'apollo', '2018-11-11 16:35:33', 'apollo', '2018-11-11 16:35:33');
COMMIT;

-- ----------------------------
-- Table structure for NamespaceLock
-- ----------------------------
DROP TABLE IF EXISTS `NamespaceLock`;
CREATE TABLE `NamespaceLock` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `NamespaceId` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '集群NamespaceId',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT 'default' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `IsDeleted` bit(1) DEFAULT b'0' COMMENT '软删除',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `IX_NamespaceId` (`NamespaceId`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='namespace的编辑锁';

-- ----------------------------
-- Table structure for Release
-- ----------------------------
DROP TABLE IF EXISTS `Release`;
CREATE TABLE `Release` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `ReleaseKey` varchar(64) NOT NULL DEFAULT '' COMMENT '发布的Key',
  `Name` varchar(64) NOT NULL DEFAULT 'default' COMMENT '发布名字',
  `Comment` varchar(256) DEFAULT NULL COMMENT '发布说明',
  `AppId` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'AppID',
  `ClusterName` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'ClusterName',
  `NamespaceName` varchar(500) NOT NULL DEFAULT 'default' COMMENT 'namespaceName',
  `Configurations` longtext NOT NULL COMMENT '发布配置',
  `IsAbandoned` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否废弃',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `AppId_ClusterName_GroupName` (`AppId`(191),`ClusterName`(191),`NamespaceName`(191)),
  KEY `DataChange_LastTime` (`DataChange_LastTime`),
  KEY `IX_ReleaseKey` (`ReleaseKey`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='发布';

-- ----------------------------
-- Records of Release
-- ----------------------------
BEGIN;
INSERT INTO `Release` VALUES (1, '20181111164148-5b81c9e77c1f59ad', '20181111164146-release', '', 'jpsite_apollo_001', 'default', 'application', '{\"myConf\":\"我的配置\"}', b'0', b'0', 'apollo', '2018-11-11 16:41:48', 'apollo', '2018-11-11 16:41:48');
INSERT INTO `Release` VALUES (2, '20181111173610-5b81c9e77c1f59ae', '20181111173609-release', '', 'jpsite_apollo_001', 'default', 'application', '{\"jpsiteConf\":\"jpsiteConfjpsiteConf\",\"myConf\":\"我的配置\"}', b'0', b'0', 'apollo', '2018-11-11 17:36:11', 'apollo', '2018-11-11 17:36:11');
INSERT INTO `Release` VALUES (3, '20181111174501-5b81c9e77c1f59af', '20181111174500-release', '', 'jpsite_apollo_001', 'default', 'application', '{\"jpsiteConf\":\"jpsiteConfjpsiteConf\",\"myConf\":\"我的配置j\"}', b'0', b'0', 'apollo', '2018-11-11 17:45:02', 'apollo', '2018-11-11 17:45:02');
INSERT INTO `Release` VALUES (4, '20181111191459-5b81c9e77ced99e4', '20181111191457-release', '', 'jpsite_apollo_001', 'default', 'application', '{\"jpsiteConf\":\"jpsiteConfjpsiteConf\",\"myConf\":\"我的配置jkml\"}', b'0', b'0', 'apollo', '2018-11-11 19:15:00', 'apollo', '2018-11-11 19:15:00');
INSERT INTO `Release` VALUES (5, '20181111194438-5b81c9e77ced99e5', '20181111194436-release', '', 'jpsite_apollo_001', 'default', 'application', '{\"jpsiteConf\":\"jpsiteCon\",\"myConf\":\"我的配置jkml\"}', b'0', b'0', 'apollo', '2018-11-11 19:44:38', 'apollo', '2018-11-11 19:44:38');
INSERT INTO `Release` VALUES (6, '20181111200940-5b81c9e77c3270b1', '20181111200938-release', '', 'jpsite_apollo_001', 'default', 'application', '{\"jpsiteConf\":\"jpsiteCon\",\"myConf\":\"我的配置jkml\",\"apollo-com.mty.jpsite.server.job\":\"apollo-com.mty.jpsite.server.job\"}', b'0', b'0', 'apollo', '2018-11-11 20:09:40', 'apollo', '2018-11-11 20:09:40');
INSERT INTO `Release` VALUES (7, '20181111201043-5b81c9e77c3270b2', '20181111201042-release', '', 'jpsite_apollo_001', 'default', 'application', '{\"jpsiteConf\":\"jpsiteCon\",\"myConf\":\"我的配置jkml\",\"apolloConfig\":\"apolloConfig\"}', b'0', b'0', 'apollo', '2018-11-11 20:10:44', 'apollo', '2018-11-11 20:10:44');
INSERT INTO `Release` VALUES (8, '20181111201522-5b81c9e77c3270b3', '20181111201521-release', '', 'jpsite_apollo_001', 'default', 'application', '{\"jpsiteConf\":\"jpsiteCon\",\"myConf\":\"我的配置jkml\",\"apolloConfig\":\"joiwjfeow\"}', b'0', b'0', 'apollo', '2018-11-11 20:15:23', 'apollo', '2018-11-11 20:15:23');
INSERT INTO `Release` VALUES (9, '20181111201758-5b81c9e77c3270b4', '20181111201756-release', '', 'jpsite_apollo_001', 'default', 'application', '{\"jpsiteConf\":\"jpsiteCon\",\"myConf\":\"我的配置jkml\",\"apolloConfig\":\"324\"}', b'0', b'0', 'apollo', '2018-11-11 20:17:59', 'apollo', '2018-11-11 20:17:59');
INSERT INTO `Release` VALUES (10, '20181111222646-5b81c9e77c3270b5', '20181111222645-release', '', 'jpsite_apollo_001', 'default', 'application', '{\"jpsiteConf\":\"jpsiteCon\",\"myConf\":\"我的配置jkml\",\"apolloConfig\":\"324kk\"}', b'0', b'0', 'apollo', '2018-11-11 22:26:47', 'apollo', '2018-11-11 22:26:47');
INSERT INTO `Release` VALUES (11, '20181111234639-5b81c9e77c3270b6', '20181111234636-release', '', 'jpsite_apollo_001', 'default', 'application', '{\"jpsiteConf\":\"jpsiteCon\",\"myConf\":\"我的配置jkm\",\"apolloConfig\":\"324kk\"}', b'0', b'0', 'apollo', '2018-11-11 23:46:39', 'apollo', '2018-11-11 23:46:39');
INSERT INTO `Release` VALUES (12, '20181111234801-5b81c9e77c3270b7', '20181111234800-release', '', 'jpsite_apollo_001', 'default', 'application', '{\"jpsiteConf\":\"jpsiteCon\",\"myConf\":\"我的配置jkm\",\"apolloConfig\":\"324\"}', b'0', b'0', 'apollo', '2018-11-11 23:48:01', 'apollo', '2018-11-11 23:48:01');
INSERT INTO `Release` VALUES (13, '20181111235559-5b81c9e77c3270b8', '20181111235557-release', '', 'jpsite_apollo_001', 'default', 'application', '{\"jpsiteConf\":\"jpsiteCon\",\"myConf\":\"我的配置jkm\",\"apolloConfig\":\"324d\"}', b'0', b'0', 'apollo', '2018-11-11 23:55:59', 'apollo', '2018-11-11 23:55:59');
INSERT INTO `Release` VALUES (14, '20181112003226-5b81c9e77c3270b9', '20181112003224-release', '', 'jpsite_apollo_001', 'default', 'application', '{\"jpsiteConf\":\"jpsiteCond\",\"myConf\":\"我的配置jkm\",\"apolloConfig\":\"324d\"}', b'0', b'0', 'apollo', '2018-11-12 00:32:26', 'apollo', '2018-11-12 00:32:26');
INSERT INTO `Release` VALUES (15, '20181112003553-5b81c9e77c3270ba', '20181112003551-release', '', 'jpsite_apollo_001', 'default', 'application', '{\"jpsiteConf\":\"jpsiteCond\",\"myConf\":\"我的配置jkm\",\"apolloConfig\":\"324dd\"}', b'0', b'0', 'apollo', '2018-11-12 00:35:53', 'apollo', '2018-11-12 00:35:53');
COMMIT;

-- ----------------------------
-- Table structure for ReleaseHistory
-- ----------------------------
DROP TABLE IF EXISTS `ReleaseHistory`;
CREATE TABLE `ReleaseHistory` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `AppId` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'AppID',
  `ClusterName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'ClusterName',
  `NamespaceName` varchar(32) NOT NULL DEFAULT 'default' COMMENT 'namespaceName',
  `BranchName` varchar(32) NOT NULL DEFAULT 'default' COMMENT '发布分支名',
  `ReleaseId` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '关联的Release Id',
  `PreviousReleaseId` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '前一次发布的ReleaseId',
  `Operation` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '发布类型，0: 普通发布，1: 回滚，2: 灰度发布，3: 灰度规则更新，4: 灰度合并回主分支发布，5: 主分支发布灰度自动发布，6: 主分支回滚灰度自动发布，7: 放弃灰度',
  `OperationContext` longtext NOT NULL COMMENT '发布上下文信息',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `IX_Namespace` (`AppId`,`ClusterName`,`NamespaceName`,`BranchName`),
  KEY `IX_ReleaseId` (`ReleaseId`),
  KEY `IX_DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='发布历史';

-- ----------------------------
-- Records of ReleaseHistory
-- ----------------------------
BEGIN;
INSERT INTO `ReleaseHistory` VALUES (1, 'jpsite_apollo_001', 'default', 'application', 'default', 1, 0, 0, '{\"isEmergencyPublish\":false}', b'0', 'apollo', '2018-11-11 16:41:48', 'apollo', '2018-11-11 16:41:48');
INSERT INTO `ReleaseHistory` VALUES (2, 'jpsite_apollo_001', 'default', 'application', 'default', 2, 1, 0, '{\"isEmergencyPublish\":false}', b'0', 'apollo', '2018-11-11 17:36:11', 'apollo', '2018-11-11 17:36:11');
INSERT INTO `ReleaseHistory` VALUES (3, 'jpsite_apollo_001', 'default', 'application', 'default', 3, 2, 0, '{\"isEmergencyPublish\":false}', b'0', 'apollo', '2018-11-11 17:45:02', 'apollo', '2018-11-11 17:45:02');
INSERT INTO `ReleaseHistory` VALUES (4, 'jpsite_apollo_001', 'default', 'application', 'default', 4, 3, 0, '{\"isEmergencyPublish\":false}', b'0', 'apollo', '2018-11-11 19:15:00', 'apollo', '2018-11-11 19:15:00');
INSERT INTO `ReleaseHistory` VALUES (5, 'jpsite_apollo_001', 'default', 'application', 'default', 5, 4, 0, '{\"isEmergencyPublish\":false}', b'0', 'apollo', '2018-11-11 19:44:38', 'apollo', '2018-11-11 19:44:38');
INSERT INTO `ReleaseHistory` VALUES (6, 'jpsite_apollo_001', 'default', 'application', 'default', 6, 5, 0, '{\"isEmergencyPublish\":false}', b'0', 'apollo', '2018-11-11 20:09:40', 'apollo', '2018-11-11 20:09:40');
INSERT INTO `ReleaseHistory` VALUES (7, 'jpsite_apollo_001', 'default', 'application', 'default', 7, 6, 0, '{\"isEmergencyPublish\":false}', b'0', 'apollo', '2018-11-11 20:10:44', 'apollo', '2018-11-11 20:10:44');
INSERT INTO `ReleaseHistory` VALUES (8, 'jpsite_apollo_001', 'default', 'application', 'default', 8, 7, 0, '{\"isEmergencyPublish\":false}', b'0', 'apollo', '2018-11-11 20:15:23', 'apollo', '2018-11-11 20:15:23');
INSERT INTO `ReleaseHistory` VALUES (9, 'jpsite_apollo_001', 'default', 'application', 'default', 9, 8, 0, '{\"isEmergencyPublish\":false}', b'0', 'apollo', '2018-11-11 20:17:59', 'apollo', '2018-11-11 20:17:59');
INSERT INTO `ReleaseHistory` VALUES (10, 'jpsite_apollo_001', 'default', 'application', 'default', 10, 9, 0, '{\"isEmergencyPublish\":false}', b'0', 'apollo', '2018-11-11 22:26:47', 'apollo', '2018-11-11 22:26:47');
INSERT INTO `ReleaseHistory` VALUES (11, 'jpsite_apollo_001', 'default', 'application', 'default', 11, 10, 0, '{\"isEmergencyPublish\":false}', b'0', 'apollo', '2018-11-11 23:46:39', 'apollo', '2018-11-11 23:46:39');
INSERT INTO `ReleaseHistory` VALUES (12, 'jpsite_apollo_001', 'default', 'application', 'default', 12, 11, 0, '{\"isEmergencyPublish\":false}', b'0', 'apollo', '2018-11-11 23:48:01', 'apollo', '2018-11-11 23:48:01');
INSERT INTO `ReleaseHistory` VALUES (13, 'jpsite_apollo_001', 'default', 'application', 'default', 13, 12, 0, '{\"isEmergencyPublish\":false}', b'0', 'apollo', '2018-11-11 23:55:59', 'apollo', '2018-11-11 23:55:59');
INSERT INTO `ReleaseHistory` VALUES (14, 'jpsite_apollo_001', 'default', 'application', 'default', 14, 13, 0, '{\"isEmergencyPublish\":false}', b'0', 'apollo', '2018-11-12 00:32:26', 'apollo', '2018-11-12 00:32:26');
INSERT INTO `ReleaseHistory` VALUES (15, 'jpsite_apollo_001', 'default', 'application', 'default', 15, 14, 0, '{\"isEmergencyPublish\":false}', b'0', 'apollo', '2018-11-12 00:35:53', 'apollo', '2018-11-12 00:35:53');
COMMIT;

-- ----------------------------
-- Table structure for ReleaseMessage
-- ----------------------------
DROP TABLE IF EXISTS `ReleaseMessage`;
CREATE TABLE `ReleaseMessage` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `Message` varchar(1024) NOT NULL DEFAULT '' COMMENT '发布的消息内容',
  `DataChange_LastTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`),
  KEY `IX_Message` (`Message`(191))
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='发布消息';

-- ----------------------------
-- Records of ReleaseMessage
-- ----------------------------
BEGIN;
INSERT INTO `ReleaseMessage` VALUES (15, 'jpsite_apollo_001+default+application', '2018-11-12 00:35:53');
COMMIT;

-- ----------------------------
-- Table structure for ServerConfig
-- ----------------------------
DROP TABLE IF EXISTS `ServerConfig`;
CREATE TABLE `ServerConfig` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `Key` varchar(64) NOT NULL DEFAULT 'default' COMMENT '配置项Key',
  `Cluster` varchar(32) NOT NULL DEFAULT 'default' COMMENT '配置对应的集群，default为不针对特定的集群',
  `Value` varchar(2048) NOT NULL DEFAULT 'default' COMMENT '配置项值',
  `Comment` varchar(1024) DEFAULT '' COMMENT '注释',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '1: deleted, 0: normal',
  `DataChange_CreatedBy` varchar(32) NOT NULL DEFAULT 'default' COMMENT '创建人邮箱前缀',
  `DataChange_CreatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DataChange_LastModifiedBy` varchar(32) DEFAULT '' COMMENT '最后修改人邮箱前缀',
  `DataChange_LastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`),
  KEY `IX_Key` (`Key`),
  KEY `DataChange_LastTime` (`DataChange_LastTime`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='配置服务自身配置';

-- ----------------------------
-- Records of ServerConfig
-- ----------------------------
BEGIN;
INSERT INTO `ServerConfig` VALUES (1, 'eureka.service.url', 'default', 'http://localhost:8080/eureka/', 'Eureka服务Url，多个service以英文逗号分隔', b'0', 'default', '2018-11-11 07:14:03', '', '2018-11-11 07:14:03');
INSERT INTO `ServerConfig` VALUES (2, 'namespace.lock.switch', 'default', 'false', '一次发布只能有一个人修改开关', b'0', 'default', '2018-11-11 07:14:03', '', '2018-11-11 07:14:03');
INSERT INTO `ServerConfig` VALUES (3, 'item.key.length.limit', 'default', '128', 'item key 最大长度限制', b'0', 'default', '2018-11-11 07:14:03', '', '2018-11-11 07:14:03');
INSERT INTO `ServerConfig` VALUES (4, 'item.value.length.limit', 'default', '20000', 'item value最大长度限制', b'0', 'default', '2018-11-11 07:14:03', '', '2018-11-11 07:14:03');
INSERT INTO `ServerConfig` VALUES (5, 'config-service.cache.enabled', 'default', 'false', 'ConfigService是否开启缓存，开启后能提高性能，但是会增大内存消耗！', b'0', 'default', '2018-11-11 07:14:03', '', '2018-11-11 07:14:03');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
