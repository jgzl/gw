# 网关微服务平台
## 项目结构
gateway-admin-ui为前端,参考前端项目 [vue-admin-work-x](https://gitee.com/qingqingxuan/vue-admin-work-x)

## 平台简介

GW是一套基于SpringCloudGateway开发的网关管理平台，毫无保留给个人及企业免费使用。

* 前端采用Vue、Element UI。
* 后端采用Spring Boot、Spring Security、Redis & Jwt。
* 权限认证使用Jwt，支持多终端认证系统。
* 支持加载动态权限菜单，多方式轻松权限控制。
* 高效率开发，使用代码生成器可以一键生成前后端代码(待开发)。
* 提供了技术栈（[Vue3](https://v3.cn.vuejs.org) [Element Plus](https://element-plus.org/zh-CN) [Vite](https://cn.vitejs.dev)）
* 特别鸣谢：[vue-admin-work-x](https://gitee.com/qingqingxuan/vue-admin-work-x)。

## 项目启动
* 1.配置nacos连接，分为注册中心和配置中心
* 2.配置redis信息
* 3.配置mysql信息
* 4.配置elasticsearch信息

## 配置host
```shell
127.0.0.1  nacos.dev.cn
127.0.0.1  mysql.dev.cn
127.0.0.1  redis.dev.cn
127.0.0.1  es.dev.cn
```

## 访问信息
账号 admin
密码 123456

## 计划完成功能
-[ ] 需要给接入方提供一个apiKey和apiSecret,管控非微服务接口及微服务层级接口
-[ ] 限流配置(漏桶,令牌桶,固定时间窗口,滑动时间窗口)
-[ ] 权限认证RBAC
-[ ] token(jwt,oauth2)
-[ ] 非微服接口文档生成
-[x] 网关管理平台(web)-日志记录查看,路由配置,用户管理,菜单管理,部门管理