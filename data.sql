/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.3.128
 Source Server Type    : MySQL
 Source Server Version : 50554
 Source Host           : 192.168.3.128:3306
 Source Schema         : springboot_admin

 Target Server Type    : MySQL
 Target Server Version : 50554
 File Encoding         : 65001

 Date: 18/11/2019 13:56:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `source_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `source_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_hide` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_resource
-- ----------------------------
INSERT INTO `tb_resource` VALUES (1, '用户管理', 'system:user:index', 1, '/admin/user/index', 2, 1, NULL, 0, '用户管理', '2019-11-10 13:56:51', '2019-11-10 13:59:01', 0);
INSERT INTO `tb_resource` VALUES (2, '用户编辑', 'system:user:edit', 2, '/admin/user/edit*', 3, 1, NULL, 0, '用户编辑', '2019-11-10 13:56:51', '2019-11-10 16:26:42', 1);
INSERT INTO `tb_resource` VALUES (3, '用户添加', 'system:user:add', 2, '/admin/user/add', 3, 2, NULL, 0, '用户添加', '2019-11-10 16:48:48', '2019-11-10 16:49:26', 1);
INSERT INTO `tb_resource` VALUES (4, '用户删除', 'system:user:deleteBatch', 2, '/admin/user/deleteBatch', 3, 3, NULL, 0, '用户删除', '2019-11-10 16:48:48', '2019-11-10 14:11:41', 1);
INSERT INTO `tb_resource` VALUES (5, '角色分配', 'system:user:grant', 2, '/admin/user/grant/**', 3, 4, NULL, 0, '角色分配', '2019-11-10 16:48:48', '2019-11-10 14:11:51', 1);
INSERT INTO `tb_resource` VALUES (6, '角色管理', 'system:role:index', 1, '/admin/role/index', 2, 2, NULL, 0, '角色管理', '2019-11-10 16:45:10', '2019-11-10 16:46:52', 0);
INSERT INTO `tb_resource` VALUES (7, '角色编辑', 'system:role:edit', 2, '/admin/role/edit*', 3, 1, NULL, 0, '角色编辑', '2019-11-10 16:47:02', '2019-11-10 10:24:06', 6);
INSERT INTO `tb_resource` VALUES (8, '角色添加', 'system:role:add', 2, '/admin/role/add', 3, 2, NULL, 0, '角色添加', '2019-11-10 16:47:23', '2019-11-10 16:49:16', 6);
INSERT INTO `tb_resource` VALUES (9, '角色删除', 'system:role:deleteBatch', 2, '/admin/role/deleteBatch', 3, 3, NULL, 0, '角色删除', '2019-11-10 16:47:23', '2019-11-10 14:12:03', 6);
INSERT INTO `tb_resource` VALUES (10, '资源分配', 'system:role:grant', 2, '/admin/role/grant/**', 3, 4, NULL, 0, '资源分配', '2019-11-10 16:47:23', '2019-11-10 14:12:11', 6);
INSERT INTO `tb_resource` VALUES (11, '资源管理', 'system:resource:index', 1, '/admin/resource/index', 2, 3, NULL, 0, '资源管理', '2019-11-10 11:21:12', '2019-11-10 11:21:42', 0);
INSERT INTO `tb_resource` VALUES (12, '资源编辑', 'system:resource:edit', 2, '/admin/resource/edit*', 3, 1, NULL, 0, '资源编辑', '2019-11-10 11:21:52', '2019-11-10 11:22:36', 11);
INSERT INTO `tb_resource` VALUES (13, '资源添加', 'system:resource:add', 2, '/admin/resource/add', 3, 2, NULL, 0, '资源添加', '2019-11-10 11:21:54', '2019-11-10 11:22:39', 11);
INSERT INTO `tb_resource` VALUES (14, '资源删除', 'system:resource:deleteBatch', 2, '/admin/resource/deleteBatch', 3, 3, NULL, 0, '资源删除', '2019-11-10 11:21:54', '2019-11-10 14:12:31', 11);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, 'administrator', 'administrator', 0, '超级管理员', '2019-11-10 17:25:30', '2019-11-10 17:26:25');
INSERT INTO `tb_role` VALUES (3, 'root', 'root', 0, '无', '2019-11-11 21:56:34', '2019-11-11 21:56:34');

-- ----------------------------
-- Table structure for tb_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_resource`;
CREATE TABLE `tb_role_resource`  (
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`, `resource_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_role_resource
-- ----------------------------
INSERT INTO `tb_role_resource` VALUES (1, 1);
INSERT INTO `tb_role_resource` VALUES (1, 2);
INSERT INTO `tb_role_resource` VALUES (1, 3);
INSERT INTO `tb_role_resource` VALUES (1, 4);
INSERT INTO `tb_role_resource` VALUES (1, 5);
INSERT INTO `tb_role_resource` VALUES (1, 6);
INSERT INTO `tb_role_resource` VALUES (1, 7);
INSERT INTO `tb_role_resource` VALUES (1, 8);
INSERT INTO `tb_role_resource` VALUES (1, 9);
INSERT INTO `tb_role_resource` VALUES (1, 10);
INSERT INTO `tb_role_resource` VALUES (1, 11);
INSERT INTO `tb_role_resource` VALUES (1, 12);
INSERT INTO `tb_role_resource` VALUES (1, 13);
INSERT INTO `tb_role_resource` VALUES (1, 14);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  `birthday` datetime NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delete_status` int(11) NULL DEFAULT NULL,
  `locked` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', 'admin', '3931MUEQD1939MQMLM4AISPVNE', 1, '1995-12-13 00:00:00', '17710372161', 'caixiaohuichn@163.com', '北京', 0, 0, '超级管理员', '2019-11-10 17:26:41', '2019-11-18 12:49:49');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES (1, 1);

SET FOREIGN_KEY_CHECKS = 1;
