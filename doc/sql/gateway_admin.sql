create database `gateway_admin` default character set utf8mb4 collate utf8mb4_general_ci;
USE `gateway_admin`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gateway_access_conf
-- ----------------------------
DROP TABLE IF EXISTS `gateway_access_conf`;
CREATE TABLE `gateway_access_conf`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `api_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'api访问key',
  `api_secret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'api访问密钥',
  `system` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态(是否启用) 0:启用,1:禁用',
  `remark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_idx1_username`(`api_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '网关访问配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gateway_access_conf
-- ----------------------------
INSERT INTO `gateway_access_conf` VALUES (1, 'metersphere-key', 'metersphere-secret', 'metersphere', '0', 'metersphere备注', '2022-01-14 17:01:09', '2022-01-14 17:09:55', '0');
INSERT INTO `gateway_access_conf` VALUES (2, 'test', 'test', 'test', '1', 'test', '2022-01-14 17:09:21', '2022-01-14 17:09:37', '0');

-- ----------------------------
-- Table structure for gateway_route_conf
-- ----------------------------
DROP TABLE IF EXISTS `gateway_route_conf`;
CREATE TABLE `gateway_route_conf`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `route_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由名称',
  `route_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由id',
  `predicates` json NULL COMMENT '断言',
  `filters` json NULL COMMENT '过滤器',
  `uri` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'url',
  `order` int(2) NULL DEFAULT 0 COMMENT '排序',
  `metadata` json NULL COMMENT '路由元信息',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '网关路由配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gateway_route_conf
-- ----------------------------
BEGIN;
INSERT INTO `gateway_route_conf` VALUES (1, '监控管理', 'monitor', '[{\"args\": {\"_genkey_0\": \"/monitor/**\"}, \"name\": \"Path\"}]', '[]', 'lb://monitor', 0, '{\"version\": \"1\"}', '2021-12-27 10:43:25', '2022-01-06 10:40:46', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `parent_id` int(20) NULL DEFAULT NULL COMMENT '父部门id',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '山东', 1, -1, 1, '2018-01-22 19:00:23', '2021-12-31 14:33:51', '0');
INSERT INTO `sys_dept` VALUES (2, '沙县国际', 2, -1, 1, '2018-01-22 19:00:38', '2021-12-31 14:33:51', '0');
INSERT INTO `sys_dept` VALUES (3, '潍坊', 3, 1, 1, '2018-01-22 19:00:44', '2021-12-31 14:33:51', '0');
INSERT INTO `sys_dept` VALUES (4, '高新', 4, 3, 1, '2018-01-22 19:00:52', '2021-12-31 14:33:51', '0');
INSERT INTO `sys_dept` VALUES (5, '院校', 5, 4, 1, '2018-01-22 19:00:57', '2021-12-31 14:33:51', '0');
INSERT INTO `sys_dept` VALUES (6, '潍院', 6, 5, 1, '2018-01-22 19:01:06', '2021-12-31 14:33:51', '1');
INSERT INTO `sys_dept` VALUES (7, '山东沙县', 7, 2, 1, '2018-01-22 19:01:57', '2021-12-31 14:33:51', '0');
INSERT INTO `sys_dept` VALUES (8, '潍坊沙县', 8, 7, 1, '2018-01-22 19:02:03', '2021-12-31 14:33:51', '0');

-- ----------------------------
-- Table structure for sys_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_relation`;
CREATE TABLE `sys_dept_relation`  (
  `ancestor` int(11) NOT NULL COMMENT '祖先节点',
  `descendant` int(11) NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor`, `descendant`) USING BTREE,
  INDEX `idx1`(`ancestor`) USING BTREE,
  INDEX `idx2`(`descendant`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept_relation
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept_relation` VALUES (1, 1);
INSERT INTO `sys_dept_relation` VALUES (1, 3);
INSERT INTO `sys_dept_relation` VALUES (1, 4);
INSERT INTO `sys_dept_relation` VALUES (1, 5);
INSERT INTO `sys_dept_relation` VALUES (2, 2);
INSERT INTO `sys_dept_relation` VALUES (2, 7);
INSERT INTO `sys_dept_relation` VALUES (2, 8);
INSERT INTO `sys_dept_relation` VALUES (2, 11);
INSERT INTO `sys_dept_relation` VALUES (2, 12);
INSERT INTO `sys_dept_relation` VALUES (3, 3);
INSERT INTO `sys_dept_relation` VALUES (3, 4);
INSERT INTO `sys_dept_relation` VALUES (3, 5);
INSERT INTO `sys_dept_relation` VALUES (4, 4);
INSERT INTO `sys_dept_relation` VALUES (4, 5);
INSERT INTO `sys_dept_relation` VALUES (5, 5);
INSERT INTO `sys_dept_relation` VALUES (7, 7);
INSERT INTO `sys_dept_relation` VALUES (7, 8);
INSERT INTO `sys_dept_relation` VALUES (7, 11);
INSERT INTO `sys_dept_relation` VALUES (7, 12);
INSERT INTO `sys_dept_relation` VALUES (8, 8);
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名字',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标志',
  `path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前端路由标识路径',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` int(11) NULL DEFAULT 1 COMMENT '排序值',
  `keep_alive` bit(1) NULL DEFAULT b'0' COMMENT '是否缓存',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单类型 （0菜单 1按钮）',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2202 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1000, '系统管理', NULL, '/system', -1, 'SettingIcon', 0, b'0', '0', '2018-09-28 08:29:53', '2021-12-27 15:13:30', '0');
INSERT INTO `sys_menu` VALUES (1100, '用户管理', NULL, '/system/user', 1000, 'MenuIcon', 1, b'0', '0', '2017-11-02 22:24:37', '2021-12-28 20:15:53', '0');
INSERT INTO `sys_menu` VALUES (1101, '用户新增', 'sys_user_add', NULL, 1100, NULL, 1, b'0', '1', '2017-11-08 09:52:09', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (1102, '用户修改', 'sys_user_edit', NULL, 1100, NULL, 1, b'0', '1', '2017-11-08 09:52:48', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (1103, '用户删除', 'sys_user_del', NULL, 1100, NULL, 1, b'0', '1', '2017-11-08 09:54:01', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (1104, '导入导出', 'sys_user_export', NULL, 1100, NULL, 1, b'0', '1', '2017-11-08 09:54:01', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (1200, '菜单管理', NULL, '/system/menu', 1000, 'MenuIcon', 2, b'0', '0', '2017-11-08 09:57:27', '2021-12-28 20:14:22', '0');
INSERT INTO `sys_menu` VALUES (1201, '菜单新增', 'sys_menu_add', NULL, 1200, NULL, 1, b'0', '1', '2017-11-08 10:15:53', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (1202, '菜单修改', 'sys_menu_edit', NULL, 1200, NULL, 1, b'0', '1', '2017-11-08 10:16:23', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (1203, '菜单删除', 'sys_menu_del', NULL, 1200, NULL, 1, b'0', '1', '2017-11-08 10:16:43', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (1300, '角色管理', NULL, '/system/role', 1000, 'MenuIcon', 3, b'0', '0', '2017-11-08 10:13:37', '2021-12-28 20:14:19', '0');
INSERT INTO `sys_menu` VALUES (1301, '角色新增', 'sys_role_add', NULL, 1300, NULL, 1, b'0', '1', '2017-11-08 10:14:18', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (1302, '角色修改', 'sys_role_edit', NULL, 1300, NULL, 1, b'0', '1', '2017-11-08 10:14:41', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (1303, '角色删除', 'sys_role_del', NULL, 1300, NULL, 1, b'0', '1', '2017-11-08 10:14:59', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (1304, '分配权限', 'sys_role_perm', NULL, 1300, NULL, 1, b'0', '1', '2018-04-20 07:22:55', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (1400, '部门管理', NULL, '/system/department', 1000, 'MenuIcon', 4, b'0', '0', '2018-01-20 13:17:19', '2021-12-28 20:14:16', '0');
INSERT INTO `sys_menu` VALUES (1401, '部门新增', 'sys_dept_add', NULL, 1400, NULL, 1, b'0', '1', '2018-01-20 14:56:16', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (1402, '部门修改', 'sys_dept_edit', NULL, 1400, NULL, 1, b'0', '1', '2018-01-20 14:56:59', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (1403, '部门删除', 'sys_dept_del', NULL, 1400, NULL, 1, b'0', '1', '2018-01-20 14:57:28', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (2000, '网关管理', NULL, '/gateway', -1, 'MapLocationIcon', 1, b'0', '0', '2018-09-04 05:58:41', '2021-12-31 16:17:42', '0');
INSERT INTO `sys_menu` VALUES (2100, '网关日志', NULL, '/gateway/log', 2000, 'MenuIcon', 1, b'0', '0', '2018-09-04 05:58:41', '2021-12-28 20:14:25', '0');
INSERT INTO `sys_menu` VALUES (2101, '网关日志新增', 'gateway_log_add', NULL, 2100, NULL, 1, b'0', '1', '2018-01-20 14:56:16', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (2102, '网关日志修改', 'gateway_log_edit', NULL, 2100, NULL, 1, b'0', '1', '2018-01-20 14:56:59', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (2103, '网关日志删除', 'gateway_log_del', NULL, 2100, NULL, 1, b'0', '1', '2018-01-20 14:57:28', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (2200, '网关路由', NULL, '/gateway/route', 2000, 'MenuIcon', 2, b'0', '0', '2018-09-04 05:58:41', '2021-12-28 20:14:28', '0');
INSERT INTO `sys_menu` VALUES (2201, '网关路由新增', 'gateway_route_add', NULL, 2200, NULL, 1, b'0', '1', '2018-01-20 14:56:16', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (2202, '网关路由修改', 'gateway_route_edit', NULL, 2200, NULL, 1, b'0', '1', '2018-01-20 14:56:59', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (2203, '网关路由删除', 'gateway_route_del', NULL, 2200, NULL, 1, b'0', '1', '2018-01-20 14:57:28', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (2204, '网关路由拷贝', 'gateway_route_copy', NULL, 2200, NULL, 1, b'0', '1', '2018-01-20 14:57:28', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (2300, '网关访问', NULL, '/gateway/access', 2000, 'MenuIcon', 3, b'0', '0', '2018-09-04 05:58:41', '2021-12-28 20:14:28', '0');
INSERT INTO `sys_menu` VALUES (2301, '网关访问新增', 'gateway_access_add', NULL, 2300, NULL, 1, b'0', '1', '2018-01-20 14:56:16', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (2302, '网关访问修改', 'gateway_access_edit', NULL, 2300, NULL, 1, b'0', '1', '2018-01-20 14:56:59', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (2303, '网关访问删除', 'gateway_access_del', NULL, 2300, NULL, 1, b'0', '1', '2018-01-20 14:57:28', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` VALUES (2304, '网关访问状态启用禁用', 'gateway_access_status', NULL, 2300, NULL, 1, b'0', '1', '2018-01-20 14:57:28', '2021-05-25 03:12:55', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色code',
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `ds_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '2' COMMENT '数据权限类型',
  `ds_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据权限范围',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`role_id`) USING BTREE,
  INDEX `role_idx1_role_code`(`role_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'ROLE_admin', '超级管理员', '0', '2', '2017-10-29 15:45:51', '2021-12-31 14:19:46', '0');
INSERT INTO `sys_role` VALUES (2, '普通用户', 'ROLE_normal', '普通用户', '0', '2', '2021-12-31 14:19:20', '2021-12-31 14:20:27', '0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 1000);
INSERT INTO `sys_role_menu` VALUES (1, 1100);
INSERT INTO `sys_role_menu` VALUES (1, 1101);
INSERT INTO `sys_role_menu` VALUES (1, 1102);
INSERT INTO `sys_role_menu` VALUES (1, 1103);
INSERT INTO `sys_role_menu` VALUES (1, 1104);
INSERT INTO `sys_role_menu` VALUES (1, 1200);
INSERT INTO `sys_role_menu` VALUES (1, 1201);
INSERT INTO `sys_role_menu` VALUES (1, 1202);
INSERT INTO `sys_role_menu` VALUES (1, 1203);
INSERT INTO `sys_role_menu` VALUES (1, 1300);
INSERT INTO `sys_role_menu` VALUES (1, 1301);
INSERT INTO `sys_role_menu` VALUES (1, 1302);
INSERT INTO `sys_role_menu` VALUES (1, 1303);
INSERT INTO `sys_role_menu` VALUES (1, 1304);
INSERT INTO `sys_role_menu` VALUES (1, 1400);
INSERT INTO `sys_role_menu` VALUES (1, 1401);
INSERT INTO `sys_role_menu` VALUES (1, 1402);
INSERT INTO `sys_role_menu` VALUES (1, 1403);
INSERT INTO `sys_role_menu` VALUES (1, 2000);
INSERT INTO `sys_role_menu` VALUES (1, 2100);
INSERT INTO `sys_role_menu` VALUES (1, 2101);
INSERT INTO `sys_role_menu` VALUES (1, 2102);
INSERT INTO `sys_role_menu` VALUES (1, 2103);
INSERT INTO `sys_role_menu` VALUES (1, 2200);
INSERT INTO `sys_role_menu` VALUES (1, 2201);
INSERT INTO `sys_role_menu` VALUES (1, 2202);
INSERT INTO `sys_role_menu` VALUES (1, 2203);
INSERT INTO `sys_role_menu` VALUES (1, 2204);
INSERT INTO `sys_role_menu` VALUES (1, 2300);
INSERT INTO `sys_role_menu` VALUES (1, 2301);
INSERT INTO `sys_role_menu` VALUES (1, 2302);
INSERT INTO `sys_role_menu` VALUES (1, 2303);
INSERT INTO `sys_role_menu` VALUES (1, 2304);
INSERT INTO `sys_role_menu` VALUES (2, 2000);
INSERT INTO `sys_role_menu` VALUES (2, 2100);
INSERT INTO `sys_role_menu` VALUES (2, 2101);
INSERT INTO `sys_role_menu` VALUES (2, 2102);
INSERT INTO `sys_role_menu` VALUES (2, 2103);
INSERT INTO `sys_role_menu` VALUES (2, 2200);
INSERT INTO `sys_role_menu` VALUES (2, 2201);
INSERT INTO `sys_role_menu` VALUES (2, 2202);
INSERT INTO `sys_role_menu` VALUES (2, 2203);
INSERT INTO `sys_role_menu` VALUES (2, 2300);
INSERT INTO `sys_role_menu` VALUES (2, 2301);
INSERT INTO `sys_role_menu` VALUES (2, 2302);
INSERT INTO `sys_role_menu` VALUES (2, 2303);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '别名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '加密盐',
  `gender` tinyint(1) NULL DEFAULT NULL COMMENT '性别',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上次登录IP',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门ID',
  `lock_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否锁定',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_idx1_username`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin-nickname', '$2a$10$IVzj1Wd.ZQdOIWdb1htQjexU94uoNeuk1crlQ9ExVupPi0Iy1uv.C', '', 1, 'li7hai26@gmail.com', '17034642888', NULL, '2022-01-06 15:47:04', '127.0.0.1', 1, 0, '2018-04-20 07:15:18', '2022-01-06 18:13:43', '0');
INSERT INTO `sys_user` VALUES (2, 'editor', 'editor-nickname', '$2a$10$IVzj1Wd.ZQdOIWdb1htQjexU94uoNeuk1crlQ9ExVupPi0Iy1uv.C', '', 0, 'li7hai26@outlook.com', '17034642888', NULL, '2022-01-06 15:47:08', '127.0.0.1', 5, 0, '2021-12-31 16:53:14', '2022-01-06 18:40:33', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1, 2);
INSERT INTO `sys_user_role` VALUES (2, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;