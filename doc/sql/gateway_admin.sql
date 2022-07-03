create database `gateway_admin` default character set utf8mb4 collate utf8mb4_general_ci;
USE `gateway_admin`;

/*
 Navicat Premium Data Transfer

 Source Server         : common
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : e538e60da76b.c.methodot.com:31810
 Source Schema         : gateway_admin

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 03/07/2022 22:00:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gateway_access_conf
-- ----------------------------
DROP TABLE IF EXISTS `gateway_access_conf`;
CREATE TABLE `gateway_access_conf` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `api_key` varchar(64) DEFAULT NULL COMMENT 'api访问key',
  `api_secret` varchar(64) DEFAULT NULL COMMENT 'api访问密钥',
  `system` varchar(64) DEFAULT NULL COMMENT '访问系统',
  `status` char(1) DEFAULT '1' COMMENT '状态(是否启用) 0:启用,1:禁用',
  `remark` longtext COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_idx1_username` (`api_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='网关访问配置表';

-- ----------------------------
-- Records of gateway_access_conf
-- ----------------------------
BEGIN;
INSERT INTO `gateway_access_conf` (`id`, `api_key`, `api_secret`, `system`, `status`, `remark`, `create_time`, `update_time`, `del_flag`) VALUES (1, 'metersphere-key', 'metersphere-secret', 'metersphere', '0', 'metersphere备注', '2022-01-14 17:01:09', '2022-06-04 09:27:31', '1');
INSERT INTO `gateway_access_conf` (`id`, `api_key`, `api_secret`, `system`, `status`, `remark`, `create_time`, `update_time`, `del_flag`) VALUES (2, 'test', 'test', 'test', '1', 'test', '2022-01-14 17:09:21', '2022-06-04 09:27:27', '1');
INSERT INTO `gateway_access_conf` (`id`, `api_key`, `api_secret`, `system`, `status`, `remark`, `create_time`, `update_time`, `del_flag`) VALUES (3, 'testApiKey', 'testApiSecret', 'testApi', '0', '测试', '2022-06-04 09:27:53', NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for gateway_log
-- ----------------------------
DROP TABLE IF EXISTS `gateway_log`;
CREATE TABLE `gateway_log` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `source_service` varchar(64) DEFAULT NULL COMMENT '请求来源服务',
  `api_key` varchar(64) DEFAULT NULL COMMENT 'api访问key',
  `api_secret` varchar(64) DEFAULT NULL COMMENT 'api访问secret',
  `environment` varchar(64) DEFAULT NULL COMMENT '请求来源环境',
  `target_service` varchar(64) DEFAULT NULL COMMENT '请求目标服务',
  `schema` varchar(10) DEFAULT NULL COMMENT '协议',
  `request_path` longtext,
  `request_path_and_query` longtext,
  `request_method` varchar(10) DEFAULT NULL,
  `request_header` longtext,
  `request_body` longtext,
  `request_source_ip` varchar(128) DEFAULT NULL,
  `response_body` longtext,
  `http_status` varchar(10) DEFAULT NULL,
  `execute_time` bigint(64) DEFAULT NULL,
  `request_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '请求时间',
  `response_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '响应时间',
  `error_msg` longtext,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of gateway_log
-- ----------------------------
BEGIN;
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('0901f634-1faa-428e-abd8-a4f966965d34', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=22\"]}', NULL, NULL, NULL, '200', 199, '2022-07-03 13:56:43', '2022-07-03 13:56:43', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('2e852711-d85b-4815-9e33-b815636ba03c', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=14\"]}', NULL, NULL, NULL, '200', 22, '2022-07-03 13:56:43', '2022-07-03 13:56:43', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('30e5afa2-2bcc-4d7e-8b96-dc0681045162', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=16\"]}', NULL, NULL, NULL, '200', 72, '2022-07-03 13:56:49', '2022-07-03 13:56:49', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('42238ccb-5a04-448c-b193-eff38f6d8f45', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=13\"]}', NULL, NULL, NULL, '200', 45, '2022-07-03 13:57:03', '2022-07-03 13:57:03', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('42551e57-1172-4c29-b5a8-d33093bb0bce', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=14\"]}', NULL, NULL, NULL, '200', 37, '2022-07-03 13:57:01', '2022-07-03 13:57:01', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('4601d8bb-0dea-4785-8781-50e9b4c73202', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=17\"]}', NULL, NULL, NULL, '200', 61, '2022-07-03 13:57:05', '2022-07-03 13:57:05', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('5841fcaa-19cb-466c-ab0d-54bf38cb19bc', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=12\"]}', NULL, NULL, NULL, '200', 35, '2022-07-03 13:56:52', '2022-07-03 13:56:52', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('8600e5f0-d990-4d46-9844-fe9a8771b302', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=45\"]}', NULL, NULL, NULL, '200', 48, '2022-07-03 13:56:47', '2022-07-03 13:56:47', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('8d331ed8-569d-4007-9d2c-b460d96ec042', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=22\"]}', NULL, NULL, NULL, '200', 117, '2022-07-03 13:56:43', '2022-07-03 13:56:43', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('9a574120-e0ce-4d71-9e17-710ed02430f4', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=31\"]}', NULL, NULL, NULL, '200', 38, '2022-07-03 13:57:06', '2022-07-03 13:57:06', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('a058b0b7-e9eb-42d1-aac4-22dbb358b800', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BDSVRTM=16; BD_HOME=1\"]}', NULL, NULL, NULL, '200', 1678, '2022-07-03 13:55:13', '2022-07-03 13:55:15', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('ae97d4bb-798c-48ad-b083-a11dba4c3d11', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=0\"]}', NULL, NULL, NULL, '200', 51, '2022-07-03 13:56:45', '2022-07-03 13:56:45', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('b1b60e82-6bb9-408f-8eef-d4807d308002', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=12\"]}', NULL, NULL, NULL, '200', 45, '2022-07-03 13:57:02', '2022-07-03 13:57:02', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('bf90e360-a2f1-42d3-9468-89bf5c9e2a2e', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=25\"]}', NULL, NULL, NULL, '200', 62, '2022-07-03 13:56:46', '2022-07-03 13:56:46', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('e098ed5c-950d-41e4-bda4-4285cffbda1d', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=12\"]}', NULL, NULL, NULL, '200', 41, '2022-07-03 13:56:59', '2022-07-03 13:56:59', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('e647047b-eb59-4e1a-a530-8f94fedf6f1a', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=20\"]}', NULL, NULL, NULL, '200', 50, '2022-07-03 13:57:04', '2022-07-03 13:57:04', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('f31a53e9-31fb-4cfb-a077-c3d5abdedcc5', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=45\"]}', NULL, NULL, NULL, '200', 51, '2022-07-03 13:56:51', '2022-07-03 13:56:51', NULL);
INSERT INTO `gateway_log` (`id`, `source_service`, `api_key`, `api_secret`, `environment`, `target_service`, `schema`, `request_path`, `request_path_and_query`, `request_method`, `request_header`, `request_body`, `request_source_ip`, `response_body`, `http_status`, `execute_time`, `request_time`, `response_time`, `error_msg`) VALUES ('fa39fac8-d492-4036-b048-dc68eb43ecd6', 'testApi', 'testApiKey', 'testApiSecret', 'qa', 'baidu', NULL, '/api/baidu', '/api/baidu?x-business-api-key=testApiKey&x-business-api-secret=testApiSecret&x-business-api-system=testApi&x-business-api-env=qa', 'GET', '{\"Host\":[\"localhost:7000\"],\"Connection\":[\"keep-alive\"],\"Cache-Control\":[\"max-age=0\"],\"sec-ch-ua\":[\"\\\" Not A;Brand\\\";v=\\\"99\\\", \\\"Chromium\\\";v=\\\"101\\\", \\\"Microsoft Edge\\\";v=\\\"101\\\"\"],\"sec-ch-ua-mobile\":[\"?0\"],\"sec-ch-ua-platform\":[\"\\\"macOS\\\"\"],\"DNT\":[\"1\"],\"Upgrade-Insecure-Requests\":[\"1\"],\"User-Agent\":[\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 Edg/101.0.1210.32\"],\"Accept\":[\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"],\"Sec-Fetch-Site\":[\"none\"],\"Sec-Fetch-Mode\":[\"navigate\"],\"Sec-Fetch-User\":[\"?1\"],\"Sec-Fetch-Dest\":[\"document\"],\"Accept-Encoding\":[\"gzip, deflate, br\"],\"Accept-Language\":[\"zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\"],\"Cookie\":[\"BD_UPN=123253; BD_HOME=1; BDSVRTM=22\"]}', NULL, NULL, NULL, '200', 110, '2022-07-03 13:56:43', '2022-07-03 13:56:43', NULL);
COMMIT;

-- ----------------------------
-- Table structure for gateway_route_conf
-- ----------------------------
DROP TABLE IF EXISTS `gateway_route_conf`;
CREATE TABLE `gateway_route_conf` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `route_name` varchar(30) DEFAULT NULL COMMENT '路由名称',
  `route_id` varchar(30) DEFAULT NULL COMMENT '路由id',
  `predicates` json DEFAULT NULL COMMENT '断言',
  `filters` json DEFAULT NULL COMMENT '过滤器',
  `uri` longtext COMMENT 'url',
  `order` int(2) DEFAULT '0' COMMENT '排序',
  `metadata` json DEFAULT NULL COMMENT '路由元信息',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='网关路由配置表';

-- ----------------------------
-- Records of gateway_route_conf
-- ----------------------------
BEGIN;
INSERT INTO `gateway_route_conf` (`id`, `route_name`, `route_id`, `predicates`, `filters`, `uri`, `order`, `metadata`, `create_time`, `update_time`, `del_flag`) VALUES (1, '监控管理', 'monitor', '[{\"args\": {\"_genkey_0\": \"/monitor/**\"}, \"name\": \"Path\"}]', '[]', 'lb://monitor', 0, '{\"version\": \"1\"}', '2021-12-27 10:43:25', '2022-01-06 10:40:46', '0');
INSERT INTO `gateway_route_conf` (`id`, `route_name`, `route_id`, `predicates`, `filters`, `uri`, `order`, `metadata`, `create_time`, `update_time`, `del_flag`) VALUES (2, '百度', 'baidu', '[{\"args\": {\"_genkey_0\": \"/baidu/**\"}, \"name\": \"Path\"}]', '[]', 'https://www.baidu.com', 0, '{\"version\": \"1\"}', '2022-06-03 16:39:53', '2022-07-03 13:55:04', '0');
COMMIT;

-- ----------------------------
-- Table structure for magic_api_backup
-- ----------------------------
DROP TABLE IF EXISTS `magic_api_backup`;
CREATE TABLE `magic_api_backup` (
  `id` varchar(32) NOT NULL COMMENT '原对象ID',
  `create_date` bigint(13) NOT NULL COMMENT '备份时间',
  `tag` varchar(32) DEFAULT NULL COMMENT '标签',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `name` varchar(64) DEFAULT NULL COMMENT '原名称',
  `content` mediumtext COMMENT '备份内容',
  `create_by` varchar(64) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`,`create_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of magic_api_backup
-- ----------------------------
BEGIN;
INSERT INTO `magic_api_backup` (`id`, `create_date`, `tag`, `type`, `name`, `content`, `create_by`) VALUES ('0ff52bca18bd48c98502ece1329eeac0', 1651505339211, NULL, 'api', '获取全部网关路由信息', '{\r\n  \"properties\" : { },\r\n  \"id\" : \"0ff52bca18bd48c98502ece1329eeac0\",\r\n  \"script\" : \"return \'Hello magic-api\'\",\r\n  \"groupId\" : \"968a9a7ee3c04483a1c6a6c23f8c445d\",\r\n  \"name\" : \"获取全部网关路由信息\",\r\n  \"createTime\" : 1651505339190,\r\n  \"updateTime\" : null,\r\n  \"lock\" : null,\r\n  \"createBy\" : null,\r\n  \"updateBy\" : null,\r\n  \"path\" : \"/routes\",\r\n  \"method\" : \"GET\",\r\n  \"parameters\" : [ ],\r\n  \"options\" : [ ],\r\n  \"requestBody\" : null,\r\n  \"headers\" : [ ],\r\n  \"paths\" : [ ],\r\n  \"responseBody\" : null,\r\n  \"description\" : null,\r\n  \"requestBodyDefinition\" : null,\r\n  \"responseBodyDefinition\" : null\r\n}', NULL);
INSERT INTO `magic_api_backup` (`id`, `create_date`, `tag`, `type`, `name`, `content`, `create_by`) VALUES ('0ff52bca18bd48c98502ece1329eeac0', 1651505411818, NULL, 'api', '获取全部网关路由信息', '{\r\n  \"properties\" : { },\r\n  \"id\" : \"0ff52bca18bd48c98502ece1329eeac0\",\r\n  \"script\" : \"db.table(\\\"gateway_route_conf\\\").count()\",\r\n  \"groupId\" : \"968a9a7ee3c04483a1c6a6c23f8c445d\",\r\n  \"name\" : \"获取全部网关路由信息\",\r\n  \"createTime\" : null,\r\n  \"updateTime\" : 1651505411797,\r\n  \"lock\" : null,\r\n  \"createBy\" : null,\r\n  \"updateBy\" : null,\r\n  \"path\" : \"/routes\",\r\n  \"method\" : \"GET\",\r\n  \"parameters\" : [ ],\r\n  \"options\" : [ ],\r\n  \"requestBody\" : null,\r\n  \"headers\" : [ ],\r\n  \"paths\" : [ ],\r\n  \"responseBody\" : null,\r\n  \"description\" : null,\r\n  \"requestBodyDefinition\" : null,\r\n  \"responseBodyDefinition\" : null\r\n}', NULL);
INSERT INTO `magic_api_backup` (`id`, `create_date`, `tag`, `type`, `name`, `content`, `create_by`) VALUES ('0ff52bca18bd48c98502ece1329eeac0', 1651505432267, NULL, 'api', '获取全部网关路由信息', '{\r\n  \"properties\" : { },\r\n  \"id\" : \"0ff52bca18bd48c98502ece1329eeac0\",\r\n  \"script\" : \"db.table(\\\"gateway_route_conf\\\").page()\",\r\n  \"groupId\" : \"968a9a7ee3c04483a1c6a6c23f8c445d\",\r\n  \"name\" : \"获取全部网关路由信息\",\r\n  \"createTime\" : null,\r\n  \"updateTime\" : 1651505432248,\r\n  \"lock\" : null,\r\n  \"createBy\" : null,\r\n  \"updateBy\" : null,\r\n  \"path\" : \"/routes\",\r\n  \"method\" : \"GET\",\r\n  \"parameters\" : [ ],\r\n  \"options\" : [ ],\r\n  \"requestBody\" : null,\r\n  \"headers\" : [ ],\r\n  \"paths\" : [ ],\r\n  \"responseBody\" : \"{\\n    \\\"code\\\": 1,\\n    \\\"message\\\": \\\"success\\\",\\n    \\\"data\\\": 1,\\n    \\\"timestamp\\\": 1651505412773,\\n    \\\"executeTime\\\": 26\\n}\",\r\n  \"description\" : null,\r\n  \"requestBodyDefinition\" : null,\r\n  \"responseBodyDefinition\" : {\r\n    \"name\" : \"\",\r\n    \"value\" : \"\",\r\n    \"description\" : \"\",\r\n    \"required\" : false,\r\n    \"dataType\" : \"Object\",\r\n    \"type\" : null,\r\n    \"defaultValue\" : null,\r\n    \"validateType\" : \"\",\r\n    \"error\" : \"\",\r\n    \"expression\" : \"\",\r\n    \"children\" : [ {\r\n      \"name\" : \"code\",\r\n      \"value\" : \"1\",\r\n      \"description\" : \"\",\r\n      \"required\" : false,\r\n      \"dataType\" : \"Integer\",\r\n      \"type\" : null,\r\n      \"defaultValue\" : null,\r\n      \"validateType\" : \"\",\r\n      \"error\" : \"\",\r\n      \"expression\" : \"\",\r\n      \"children\" : [ ]\r\n    }, {\r\n      \"name\" : \"message\",\r\n      \"value\" : \"success\",\r\n      \"description\" : \"\",\r\n      \"required\" : false,\r\n      \"dataType\" : \"String\",\r\n      \"type\" : null,\r\n      \"defaultValue\" : null,\r\n      \"validateType\" : \"\",\r\n      \"error\" : \"\",\r\n      \"expression\" : \"\",\r\n      \"children\" : [ ]\r\n    }, {\r\n      \"name\" : \"data\",\r\n      \"value\" : \"1\",\r\n      \"description\" : \"\",\r\n      \"required\" : false,\r\n      \"dataType\" : \"Integer\",\r\n      \"type\" : null,\r\n      \"defaultValue\" : null,\r\n      \"validateType\" : \"\",\r\n      \"error\" : \"\",\r\n      \"expression\" : \"\",\r\n      \"children\" : [ ]\r\n    }, {\r\n      \"name\" : \"timestamp\",\r\n      \"value\" : \"1651505412773\",\r\n      \"description\" : \"\",\r\n      \"required\" : false,\r\n      \"dataType\" : \"Long\",\r\n      \"type\" : null,\r\n      \"defaultValue\" : null,\r\n      \"validateType\" : \"\",\r\n      \"error\" : \"\",\r\n      \"expression\" : \"\",\r\n      \"children\" : [ ]\r\n    }, {\r\n      \"name\" : \"executeTime\",\r\n      \"value\" : \"26\",\r\n      \"description\" : \"\",\r\n      \"required\" : false,\r\n      \"dataType\" : \"Integer\",\r\n      \"type\" : null,\r\n      \"defaultValue\" : null,\r\n      \"validateType\" : \"\",\r\n      \"error\" : \"\",\r\n      \"expression\" : \"\",\r\n      \"children\" : [ ]\r\n    } ]\r\n  }\r\n}', NULL);
INSERT INTO `magic_api_backup` (`id`, `create_date`, `tag`, `type`, `name`, `content`, `create_by`) VALUES ('62ee0d323ae946b8a9fcd08a11389fc1', 1651505239995, NULL, 'api', '测试', '{\r\n  \"properties\" : { },\r\n  \"id\" : \"62ee0d323ae946b8a9fcd08a11389fc1\",\r\n  \"script\" : \"return \'Hello magic-api\'\",\r\n  \"groupId\" : \"968a9a7ee3c04483a1c6a6c23f8c445d\",\r\n  \"name\" : \"测试\",\r\n  \"createTime\" : 1651505239976,\r\n  \"updateTime\" : null,\r\n  \"lock\" : null,\r\n  \"createBy\" : null,\r\n  \"updateBy\" : null,\r\n  \"path\" : \"/test\",\r\n  \"method\" : \"GET\",\r\n  \"parameters\" : [ ],\r\n  \"options\" : [ ],\r\n  \"requestBody\" : null,\r\n  \"headers\" : [ ],\r\n  \"paths\" : [ ],\r\n  \"responseBody\" : null,\r\n  \"description\" : null,\r\n  \"requestBodyDefinition\" : null,\r\n  \"responseBodyDefinition\" : null\r\n}', NULL);
INSERT INTO `magic_api_backup` (`id`, `create_date`, `tag`, `type`, `name`, `content`, `create_by`) VALUES ('968a9a7ee3c04483a1c6a6c23f8c445d', 1651505223813, NULL, 'api-group', '网管管理服务', '{\r\n  \"properties\" : { },\r\n  \"id\" : \"968a9a7ee3c04483a1c6a6c23f8c445d\",\r\n  \"name\" : \"网管管理服务\",\r\n  \"type\" : \"api\",\r\n  \"parentId\" : \"0\",\r\n  \"path\" : \"/magic/api\",\r\n  \"createTime\" : 1651505223795,\r\n  \"updateTime\" : null,\r\n  \"createBy\" : null,\r\n  \"updateBy\" : null,\r\n  \"paths\" : [ ],\r\n  \"options\" : [ ]\r\n}', NULL);
INSERT INTO `magic_api_backup` (`id`, `create_date`, `tag`, `type`, `name`, `content`, `create_by`) VALUES ('968a9a7ee3c04483a1c6a6c23f8c445d', 1651505331365, NULL, 'api-group', '网管管理服务', '{\r\n  \"properties\" : { },\r\n  \"id\" : \"968a9a7ee3c04483a1c6a6c23f8c445d\",\r\n  \"name\" : \"网管管理服务\",\r\n  \"type\" : \"api\",\r\n  \"parentId\" : \"0\",\r\n  \"path\" : \"/magic/api/gateway/admin\",\r\n  \"createTime\" : null,\r\n  \"updateTime\" : 1651505331355,\r\n  \"createBy\" : null,\r\n  \"updateBy\" : null,\r\n  \"paths\" : [ ],\r\n  \"options\" : [ ]\r\n}', NULL);
COMMIT;

-- ----------------------------
-- Table structure for magic_api_file
-- ----------------------------
DROP TABLE IF EXISTS `magic_api_file`;
CREATE TABLE `magic_api_file` (
  `file_path` varchar(512) NOT NULL,
  `file_content` mediumtext,
  PRIMARY KEY (`file_path`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of magic_api_file
-- ----------------------------
BEGIN;
INSERT INTO `magic_api_file` (`file_path`, `file_content`) VALUES ('magic-api/api/', 'this is directory');
INSERT INTO `magic_api_file` (`file_path`, `file_content`) VALUES ('magic-api/api/网管管理服务/', 'this is directory');
INSERT INTO `magic_api_file` (`file_path`, `file_content`) VALUES ('magic-api/api/网管管理服务/group.json', '{\r\n  \"properties\" : { },\r\n  \"id\" : \"968a9a7ee3c04483a1c6a6c23f8c445d\",\r\n  \"name\" : \"网管管理服务\",\r\n  \"type\" : \"api\",\r\n  \"parentId\" : \"0\",\r\n  \"path\" : \"/magic/api/gateway/admin\",\r\n  \"createTime\" : null,\r\n  \"updateTime\" : 1651505331355,\r\n  \"createBy\" : null,\r\n  \"updateBy\" : null,\r\n  \"paths\" : [ ],\r\n  \"options\" : [ ]\r\n}');
INSERT INTO `magic_api_file` (`file_path`, `file_content`) VALUES ('magic-api/api/网管管理服务/测试.ms', '{\r\n  \"properties\" : { },\r\n  \"id\" : \"62ee0d323ae946b8a9fcd08a11389fc1\",\r\n  \"script\" : null,\r\n  \"groupId\" : \"968a9a7ee3c04483a1c6a6c23f8c445d\",\r\n  \"name\" : \"测试\",\r\n  \"createTime\" : 1651505239976,\r\n  \"updateTime\" : null,\r\n  \"lock\" : null,\r\n  \"createBy\" : null,\r\n  \"updateBy\" : null,\r\n  \"path\" : \"/test\",\r\n  \"method\" : \"GET\",\r\n  \"parameters\" : [ ],\r\n  \"options\" : [ ],\r\n  \"requestBody\" : null,\r\n  \"headers\" : [ ],\r\n  \"paths\" : [ ],\r\n  \"responseBody\" : null,\r\n  \"description\" : null,\r\n  \"requestBodyDefinition\" : null,\r\n  \"responseBodyDefinition\" : null\r\n}\r\n================================\r\nreturn \'Hello magic-api\'');
INSERT INTO `magic_api_file` (`file_path`, `file_content`) VALUES ('magic-api/api/网管管理服务/获取全部网关路由信息.ms', '{\r\n  \"properties\" : { },\r\n  \"id\" : \"0ff52bca18bd48c98502ece1329eeac0\",\r\n  \"script\" : null,\r\n  \"groupId\" : \"968a9a7ee3c04483a1c6a6c23f8c445d\",\r\n  \"name\" : \"获取全部网关路由信息\",\r\n  \"createTime\" : null,\r\n  \"updateTime\" : 1651505432248,\r\n  \"lock\" : null,\r\n  \"createBy\" : null,\r\n  \"updateBy\" : null,\r\n  \"path\" : \"/routes\",\r\n  \"method\" : \"GET\",\r\n  \"parameters\" : [ ],\r\n  \"options\" : [ ],\r\n  \"requestBody\" : null,\r\n  \"headers\" : [ ],\r\n  \"paths\" : [ ],\r\n  \"responseBody\" : \"{\\n    \\\"code\\\": 1,\\n    \\\"message\\\": \\\"success\\\",\\n    \\\"data\\\": 1,\\n    \\\"timestamp\\\": 1651505412773,\\n    \\\"executeTime\\\": 26\\n}\",\r\n  \"description\" : null,\r\n  \"requestBodyDefinition\" : null,\r\n  \"responseBodyDefinition\" : {\r\n    \"name\" : \"\",\r\n    \"value\" : \"\",\r\n    \"description\" : \"\",\r\n    \"required\" : false,\r\n    \"dataType\" : \"Object\",\r\n    \"type\" : null,\r\n    \"defaultValue\" : null,\r\n    \"validateType\" : \"\",\r\n    \"error\" : \"\",\r\n    \"expression\" : \"\",\r\n    \"children\" : [ {\r\n      \"name\" : \"code\",\r\n      \"value\" : \"1\",\r\n      \"description\" : \"\",\r\n      \"required\" : false,\r\n      \"dataType\" : \"Integer\",\r\n      \"type\" : null,\r\n      \"defaultValue\" : null,\r\n      \"validateType\" : \"\",\r\n      \"error\" : \"\",\r\n      \"expression\" : \"\",\r\n      \"children\" : [ ]\r\n    }, {\r\n      \"name\" : \"message\",\r\n      \"value\" : \"success\",\r\n      \"description\" : \"\",\r\n      \"required\" : false,\r\n      \"dataType\" : \"String\",\r\n      \"type\" : null,\r\n      \"defaultValue\" : null,\r\n      \"validateType\" : \"\",\r\n      \"error\" : \"\",\r\n      \"expression\" : \"\",\r\n      \"children\" : [ ]\r\n    }, {\r\n      \"name\" : \"data\",\r\n      \"value\" : \"1\",\r\n      \"description\" : \"\",\r\n      \"required\" : false,\r\n      \"dataType\" : \"Integer\",\r\n      \"type\" : null,\r\n      \"defaultValue\" : null,\r\n      \"validateType\" : \"\",\r\n      \"error\" : \"\",\r\n      \"expression\" : \"\",\r\n      \"children\" : [ ]\r\n    }, {\r\n      \"name\" : \"timestamp\",\r\n      \"value\" : \"1651505412773\",\r\n      \"description\" : \"\",\r\n      \"required\" : false,\r\n      \"dataType\" : \"Long\",\r\n      \"type\" : null,\r\n      \"defaultValue\" : null,\r\n      \"validateType\" : \"\",\r\n      \"error\" : \"\",\r\n      \"expression\" : \"\",\r\n      \"children\" : [ ]\r\n    }, {\r\n      \"name\" : \"executeTime\",\r\n      \"value\" : \"26\",\r\n      \"description\" : \"\",\r\n      \"required\" : false,\r\n      \"dataType\" : \"Integer\",\r\n      \"type\" : null,\r\n      \"defaultValue\" : null,\r\n      \"validateType\" : \"\",\r\n      \"error\" : \"\",\r\n      \"expression\" : \"\",\r\n      \"children\" : [ ]\r\n    } ]\r\n  }\r\n}\r\n================================\r\ndb.table(\"gateway_route_conf\").page()');
INSERT INTO `magic_api_file` (`file_path`, `file_content`) VALUES ('magic-api/datasource/', 'this is directory');
INSERT INTO `magic_api_file` (`file_path`, `file_content`) VALUES ('magic-api/function/', 'this is directory');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `parent_id` int(20) DEFAULT NULL COMMENT '父部门id',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` (`dept_id`, `name`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `del_flag`) VALUES (1, '山东', 1, -1, 1, '2018-01-22 19:00:23', '2021-12-31 14:33:51', '0');
INSERT INTO `sys_dept` (`dept_id`, `name`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `del_flag`) VALUES (2, '沙县国际', 2, -1, 1, '2018-01-22 19:00:38', '2021-12-31 14:33:51', '0');
INSERT INTO `sys_dept` (`dept_id`, `name`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `del_flag`) VALUES (3, '潍坊', 3, 1, 1, '2018-01-22 19:00:44', '2021-12-31 14:33:51', '0');
INSERT INTO `sys_dept` (`dept_id`, `name`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `del_flag`) VALUES (4, '高新', 4, 3, 1, '2018-01-22 19:00:52', '2021-12-31 14:33:51', '0');
INSERT INTO `sys_dept` (`dept_id`, `name`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `del_flag`) VALUES (5, '院校', 5, 4, 1, '2018-01-22 19:00:57', '2021-12-31 14:33:51', '0');
INSERT INTO `sys_dept` (`dept_id`, `name`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `del_flag`) VALUES (6, '潍院', 6, 5, 1, '2018-01-22 19:01:06', '2021-12-31 14:33:51', '1');
INSERT INTO `sys_dept` (`dept_id`, `name`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `del_flag`) VALUES (7, '山东沙县', 7, 2, 1, '2018-01-22 19:01:57', '2021-12-31 14:33:51', '0');
INSERT INTO `sys_dept` (`dept_id`, `name`, `sort`, `parent_id`, `status`, `create_time`, `update_time`, `del_flag`) VALUES (8, '潍坊沙县', 8, 7, 1, '2018-01-22 19:02:03', '2021-12-31 14:33:51', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_relation`;
CREATE TABLE `sys_dept_relation` (
  `ancestor` int(11) NOT NULL COMMENT '祖先节点',
  `descendant` int(11) NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor`,`descendant`) USING BTREE,
  KEY `idx1` (`ancestor`) USING BTREE,
  KEY `idx2` (`descendant`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='部门关系表';

-- ----------------------------
-- Records of sys_dept_relation
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (1, 1);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (1, 3);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (1, 4);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (1, 5);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (2, 2);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (2, 7);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (2, 8);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (2, 11);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (2, 12);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (3, 3);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (3, 4);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (3, 5);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (4, 4);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (4, 5);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (5, 5);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (7, 7);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (7, 8);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (7, 11);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (7, 12);
INSERT INTO `sys_dept_relation` (`ancestor`, `descendant`) VALUES (8, 8);
COMMIT;

-- ----------------------------
-- Table structure for sys_error_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_error_code`;
CREATE TABLE `sys_error_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '错误码编号',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '错误码类型',
  `application_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '应用名',
  `code` int(11) NOT NULL DEFAULT '0' COMMENT '错误码编码',
  `message` varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '错误码错误提示',
  `memo` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '备注',
  `creator` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1535525176886648834 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='错误码表';

-- ----------------------------
-- Records of sys_error_code
-- ----------------------------
BEGIN;
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820177905451010, 1, 'gateway-admin-server', 1002000000, '登录失败，账号密码不正确', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820177913839618, 1, 'gateway-admin-server', 1002000001, '登录失败，账号被禁用', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820177913839619, 1, 'gateway-admin-server', 1002000002, '登录失败', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820177913839620, 1, 'gateway-admin-server', 1002000003, '验证码不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820177980948481, 1, 'gateway-admin-server', 1002000004, '验证码不正确', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820177980948482, 1, 'gateway-admin-server', 1002000005, '未绑定账号，需要进行绑定', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820177980948483, 1, 'gateway-admin-server', 1002000006, 'Token 已经过期', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820177980948484, 1, 'gateway-admin-server', 1002001000, '已经存在该名字的菜单', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820177980948485, 1, 'gateway-admin-server', 1002001001, '父菜单不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178043863042, 1, 'gateway-admin-server', 1002001002, '不能设置自己为父菜单', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178043863043, 1, 'gateway-admin-server', 1002001003, '菜单不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178043863044, 1, 'gateway-admin-server', 1002001004, '存在子菜单，无法删除', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178043863045, 1, 'gateway-admin-server', 1002001005, '父菜单的类型必须是目录或者菜单', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178043863046, 1, 'gateway-admin-server', 1002002000, '角色不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178115166209, 1, 'gateway-admin-server', 1002002001, '已经存在名为【{}】的角色', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178115166210, 1, 'gateway-admin-server', 1002002002, '已经存在编码为【{}】的角色', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178115166211, 1, 'gateway-admin-server', 1002002003, '不能操作类型为系统内置的角色', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178115166212, 1, 'gateway-admin-server', 1002002004, '名字为【{}】的角色已被禁用', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178173886465, 1, 'gateway-admin-server', 1002002005, '编码【{}】不能使用', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178173886466, 1, 'gateway-admin-server', 1002003000, '用户账号已经存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178173886467, 1, 'gateway-admin-server', 1002003001, '手机号已经存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178173886468, 1, 'gateway-admin-server', 1002003002, '邮箱已经存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178173886469, 1, 'gateway-admin-server', 1002003003, '用户不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178240995330, 1, 'gateway-admin-server', 1002003004, '导入用户数据不能为空！', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178240995331, 1, 'gateway-admin-server', 1002003005, '用户密码校验失败', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178240995332, 1, 'gateway-admin-server', 1002003006, '名字为【{}】的用户已被禁用', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178240995333, 1, 'gateway-admin-server', 1002003008, '创建用户失败，原因：超过租户最大租户配额({})！', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178240995334, 1, 'gateway-admin-server', 1002004000, '已经存在该名字的部门', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178303909890, 1, 'gateway-admin-server', 1002004001, '父级部门不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178303909891, 1, 'gateway-admin-server', 1002004002, '当前部门不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178303909892, 1, 'gateway-admin-server', 1002004003, '存在子部门，无法删除', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178303909893, 1, 'gateway-admin-server', 1002004004, '不能设置自己为父部门', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178303909894, 1, 'gateway-admin-server', 1002004005, '部门中存在员工，无法删除', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178371018754, 1, 'gateway-admin-server', 1002004006, '部门不处于开启状态，不允许选择', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178371018755, 1, 'gateway-admin-server', 1002004007, '不能设置自己的子部门为父部门', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178371018756, 1, 'gateway-admin-server', 1002005000, '当前岗位不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178371018757, 1, 'gateway-admin-server', 1002005001, '岗位({}) 不处于开启状态，不允许选择', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178371018758, 1, 'gateway-admin-server', 1002005002, '已经存在该名字的岗位', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178433933313, 1, 'gateway-admin-server', 1002005003, '已经存在该标识的岗位', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178433933314, 1, 'gateway-admin-server', 1002006001, '当前字典类型不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178433933315, 1, 'gateway-admin-server', 1002006002, '字典类型不处于开启状态，不允许选择', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178433933316, 1, 'gateway-admin-server', 1002006003, '已经存在该名字的字典类型', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178501042178, 1, 'gateway-admin-server', 1002006004, '已经存在该类型的字典类型', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178501042179, 1, 'gateway-admin-server', 1002006005, '无法删除，该字典类型还有字典数据', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178501042180, 1, 'gateway-admin-server', 1002007001, '当前字典数据不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178501042181, 1, 'gateway-admin-server', 1002007002, '字典数据({})不处于开启状态，不允许选择', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178501042182, 1, 'gateway-admin-server', 1002007003, '已经存在该值的字典数据', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178568151041, 1, 'gateway-admin-server', 1002008001, '当前通知公告不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178568151042, 1, 'gateway-admin-server', 1002011000, '短信渠道不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178568151043, 1, 'gateway-admin-server', 1002011001, '短信渠道不处于开启状态，不允许选择', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178568151044, 1, 'gateway-admin-server', 1002011002, '无法删除，该短信渠道还有短信模板', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178568151045, 1, 'gateway-admin-server', 1002012000, '短信模板不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178643648513, 1, 'gateway-admin-server', 1002012001, '已经存在编码为【{}】的短信模板', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178647842817, 1, 'gateway-admin-server', 1002013000, '手机号不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178647842818, 1, 'gateway-admin-server', 1002013001, '模板参数({})缺失', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178647842819, 1, 'gateway-admin-server', 1002013002, '短信模板不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178647842820, 1, 'gateway-admin-server', 1002014000, '验证码不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178693980161, 1, 'gateway-admin-server', 1002014001, '验证码已过期', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178693980162, 1, 'gateway-admin-server', 1002014002, '验证码已使用', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178693980163, 1, 'gateway-admin-server', 1002014003, '验证码不正确', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178693980164, 1, 'gateway-admin-server', 1002014004, '超过每日短信发送数量', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178693980165, 1, 'gateway-admin-server', 1002014005, '短信发送过于频率', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178756894721, 1, 'gateway-admin-server', 1002014006, '手机号已被使用', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178756894722, 1, 'gateway-admin-server', 1002014007, '验证码未被使用', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178756894723, 1, 'gateway-admin-server', 1002015000, '租户不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178756894724, 1, 'gateway-admin-server', 1002015001, '名字为【{}】的租户已被禁用', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178756894725, 1, 'gateway-admin-server', 1002015002, '名字为【{}】的租户已过期', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178824003585, 1, 'gateway-admin-server', 1002015003, '系统租户不能进行修改、删除等操作！', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178824003586, 1, 'gateway-admin-server', 1002016000, '租户套餐不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178824003587, 1, 'gateway-admin-server', 1002016001, '租户正在使用该套餐，请给租户重新设置套餐后再尝试删除', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178824003588, 1, 'gateway-admin-server', 1002016002, '名字为【{}】的租户套餐已被禁用', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178824003589, 1, 'gateway-admin-server', 1002017000, '错误码不存在', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178824003590, 1, 'gateway-admin-server', 1002017001, '已经存在编码为【{}】的错误码', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178824003591, 1, 'gateway-admin-server', 1002018000, '社交授权失败，原因是：{}', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178886918146, 1, 'gateway-admin-server', 1002018001, '社交解绑失败，非当前用户绑定', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1512820178886918147, 1, 'gateway-admin-server', 1002018002, '社交授权失败，找不到对应的用户', '', '-1', '2022-04-09 23:50:12', '-1', '2022-04-09 23:50:12', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1523331144308879362, 1, 'gateway-admin-server', 1002020001, '网关请求来源apiKey不合法', '', '-1', '2022-05-08 23:57:02', '-1', '2022-06-11 15:31:45', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1523331144694755329, 1, 'gateway-admin-server', 1002020002, '网关请求来源apiSecret不合法', '', '-1', '2022-05-08 23:57:02', '-1', '2022-06-11 15:31:45', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1523331145026105345, 1, 'gateway-admin-server', 1002020003, '网关请求来源system不合法', '', '-1', '2022-05-08 23:57:02', '-1', '2022-06-11 15:31:45', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1535525176282669058, 1, 'gateway-admin-server', 1002020004, '网关请求来源apiKey/apiSecret已禁用', '', '-1', '2022-06-11 15:31:45', '-1', '2022-06-11 15:31:45', b'0');
INSERT INTO `sys_error_code` (`id`, `type`, `application_name`, `code`, `message`, `memo`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES (1535525176886648833, 1, 'gateway-admin-server', 1002020005, '网关请求来源apiKey/apiSecret/system不允许为空', '', '-1', '2022-06-11 15:31:46', '-1', '2022-06-11 15:31:46', b'0');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(32) DEFAULT NULL COMMENT '菜单名字',
  `permission` varchar(32) DEFAULT NULL COMMENT '权限标志',
  `path` varchar(128) DEFAULT NULL COMMENT '前端路由标识路径',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT '1' COMMENT '排序值',
  `keep_alive` bit(1) DEFAULT b'0' COMMENT '是否缓存',
  `type` char(1) DEFAULT '0' COMMENT '菜单类型 （0菜单 1按钮）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3101 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1000, '系统管理', NULL, '/system', -1, 'SettingIcon', 0, b'0', '0', '2018-09-28 08:29:53', '2021-12-27 15:13:30', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1100, '用户管理', NULL, '/system/user', 1000, 'MenuIcon', 1, b'0', '0', '2017-11-02 22:24:37', '2021-12-28 20:15:53', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1101, '用户新增', 'sys_user_add', NULL, 1100, NULL, 1, b'0', '1', '2017-11-08 09:52:09', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1102, '用户修改', 'sys_user_edit', NULL, 1100, NULL, 1, b'0', '1', '2017-11-08 09:52:48', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1103, '用户删除', 'sys_user_del', NULL, 1100, NULL, 1, b'0', '1', '2017-11-08 09:54:01', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1104, '导入导出', 'sys_user_export', NULL, 1100, NULL, 1, b'0', '1', '2017-11-08 09:54:01', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1200, '菜单管理', NULL, '/system/menu', 1000, 'MenuIcon', 2, b'0', '0', '2017-11-08 09:57:27', '2021-12-28 20:14:22', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1201, '菜单新增', 'sys_menu_add', NULL, 1200, NULL, 1, b'0', '1', '2017-11-08 10:15:53', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1202, '菜单修改', 'sys_menu_edit', NULL, 1200, NULL, 1, b'0', '1', '2017-11-08 10:16:23', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1203, '菜单删除', 'sys_menu_del', NULL, 1200, NULL, 1, b'0', '1', '2017-11-08 10:16:43', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1300, '角色管理', NULL, '/system/role', 1000, 'MenuIcon', 3, b'0', '0', '2017-11-08 10:13:37', '2021-12-28 20:14:19', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1301, '角色新增', 'sys_role_add', NULL, 1300, NULL, 1, b'0', '1', '2017-11-08 10:14:18', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1302, '角色修改', 'sys_role_edit', NULL, 1300, NULL, 1, b'0', '1', '2017-11-08 10:14:41', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1303, '角色删除', 'sys_role_del', NULL, 1300, NULL, 1, b'0', '1', '2017-11-08 10:14:59', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1304, '分配权限', 'sys_role_perm', NULL, 1300, NULL, 1, b'0', '1', '2018-04-20 07:22:55', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1400, '部门管理', NULL, '/system/department', 1000, 'MenuIcon', 4, b'0', '0', '2018-01-20 13:17:19', '2021-12-28 20:14:16', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1401, '部门新增', 'sys_dept_add', NULL, 1400, NULL, 1, b'0', '1', '2018-01-20 14:56:16', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1402, '部门修改', 'sys_dept_edit', NULL, 1400, NULL, 1, b'0', '1', '2018-01-20 14:56:59', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (1403, '部门删除', 'sys_dept_del', NULL, 1400, NULL, 1, b'0', '1', '2018-01-20 14:57:28', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2000, '网关管理', NULL, '/gateway', -1, 'OperationIcon', 1, b'0', '0', '2018-09-04 05:58:41', '2021-12-31 16:17:42', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2100, '网关日志', NULL, '/gateway/log', 2000, 'MenuIcon', 1, b'0', '0', '2018-09-04 05:58:41', '2021-12-28 20:14:25', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2101, '网关日志新增', 'gateway_log_add', NULL, 2100, NULL, 1, b'0', '1', '2018-01-20 14:56:16', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2102, '网关日志修改', 'gateway_log_edit', NULL, 2100, NULL, 1, b'0', '1', '2018-01-20 14:56:59', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2103, '网关日志删除', 'gateway_log_del', NULL, 2100, NULL, 1, b'0', '1', '2018-01-20 14:57:28', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2200, '网关路由', NULL, '/gateway/route', 2000, 'MenuIcon', 2, b'0', '0', '2018-09-04 05:58:41', '2021-12-28 20:14:28', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2201, '网关路由新增', 'gateway_route_add', NULL, 2200, NULL, 1, b'0', '1', '2018-01-20 14:56:16', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2202, '网关路由修改', 'gateway_route_edit', NULL, 2200, NULL, 1, b'0', '1', '2018-01-20 14:56:59', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2203, '网关路由删除', 'gateway_route_del', NULL, 2200, NULL, 1, b'0', '1', '2018-01-20 14:57:28', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2204, '网关路由拷贝', 'gateway_route_copy', NULL, 2200, NULL, 1, b'0', '1', '2018-01-20 14:57:28', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2300, '网关访问', NULL, '/gateway/access', 2000, 'MenuIcon', 3, b'0', '0', '2018-09-04 05:58:41', '2021-12-28 20:14:28', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2301, '网关访问新增', 'gateway_access_add', NULL, 2300, NULL, 1, b'0', '1', '2018-01-20 14:56:16', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2302, '网关访问修改', 'gateway_access_edit', NULL, 2300, NULL, 1, b'0', '1', '2018-01-20 14:56:59', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2303, '网关访问删除', 'gateway_access_del', NULL, 2300, NULL, 1, b'0', '1', '2018-01-20 14:57:28', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2304, '网关访问状态启用禁用', 'gateway_access_status', NULL, 2300, NULL, 1, b'0', '1', '2018-01-20 14:57:28', '2021-05-25 03:12:55', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (3000, '项目信息', NULL, '/project', -1, 'MapLocationIcon', 2, b'0', '0', '2018-09-04 05:58:41', '2021-12-31 16:17:42', '0');
INSERT INTO `sys_menu` (`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (3100, '项目依赖', NULL, '/project/information', 3000, 'MenuIcon', 1, b'0', '0', '2018-09-04 05:58:41', '2021-12-28 20:14:25', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(64) DEFAULT NULL COMMENT '角色code',
  `role_desc` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `ds_type` char(1) DEFAULT '2' COMMENT '数据权限类型',
  `ds_scope` varchar(255) DEFAULT NULL COMMENT '数据权限范围',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`role_id`) USING BTREE,
  KEY `role_idx1_role_code` (`role_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_code`, `role_desc`, `ds_type`, `ds_scope`, `create_time`, `update_time`, `del_flag`) VALUES (1, '超级管理员', 'ROLE_admin', '超级管理员', '0', '2', '2017-10-29 15:45:51', '2021-12-31 14:19:46', '0');
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_code`, `role_desc`, `ds_type`, `ds_scope`, `create_time`, `update_time`, `del_flag`) VALUES (2, '普通用户', 'ROLE_normal', '普通用户', '0', '2', '2021-12-31 14:19:20', '2021-12-31 14:20:27', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色菜单表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1000);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1100);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1101);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1102);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1103);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1104);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1200);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1201);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1202);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1203);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1300);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1301);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1302);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1303);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1304);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1400);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1401);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1402);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1403);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2000);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2100);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2101);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2102);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2103);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2200);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2201);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2202);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2203);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2204);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2300);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2301);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2302);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2303);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2304);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 3000);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 3100);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2000);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2100);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2101);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2102);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2103);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2200);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2201);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2202);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2203);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2300);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2301);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2302);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2303);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 3000);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 3100);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名称',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '别名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '加密盐',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(64) DEFAULT NULL COMMENT '上次登录IP',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `lock_flag` tinyint(1) DEFAULT '0' COMMENT '是否锁定',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`user_id`) USING BTREE,
  KEY `user_idx1_username` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`user_id`, `user_name`, `nick_name`, `password`, `salt`, `gender`, `email`, `mobile`, `avatar`, `last_login_time`, `last_login_ip`, `dept_id`, `lock_flag`, `create_time`, `update_time`, `del_flag`) VALUES (1, 'admin', 'admin-nickname', '$2a$10$IVzj1Wd.ZQdOIWdb1htQjexU94uoNeuk1crlQ9ExVupPi0Iy1uv.C', '', 1, 'li7hai26@gmail.com', '17034642888', NULL, '2022-01-06 15:47:04', '127.0.0.1', 1, 0, '2018-04-20 07:15:18', '2022-01-06 18:13:43', '0');
INSERT INTO `sys_user` (`user_id`, `user_name`, `nick_name`, `password`, `salt`, `gender`, `email`, `mobile`, `avatar`, `last_login_time`, `last_login_ip`, `dept_id`, `lock_flag`, `create_time`, `update_time`, `del_flag`) VALUES (2, 'editor', 'editor-nickname', '$2a$10$IVzj1Wd.ZQdOIWdb1htQjexU94uoNeuk1crlQ9ExVupPi0Iy1uv.C', '', 0, 'li7hai26@outlook.com', '17034642888', NULL, '2022-01-06 15:47:08', '127.0.0.1', 5, 0, '2021-12-31 16:53:14', '2022-01-06 18:40:33', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (2, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
