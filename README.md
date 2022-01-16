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

## 部署步骤
1.安装mysql,nacos,redis,elasticsearch步骤如下所示

[安装mysql](https://www.yuque.com/yuque_lihaifeng/cloudnative/docker-mysql)

[安装nacos](https://www.yuque.com/yuque_lihaifeng/cloudnative/docker-nacos)

[安装redis](https://www.yuque.com/yuque_lihaifeng/cloudnative/docker-redis)

[安装elasticsearch](https://www.yuque.com/yuque_lihaifeng/cloudnative/docker-elasticsearch7)

2.build.sh 打包脚本(用法如下)
```shell
# sh build.sh 需要打包的profile
sh build.sh prod
```
3.run.sh 运行脚本(用法如下)
```shell
sh run.sh
```

## 计划完成功能
- [ ] 需要给接入方提供一个apiKey和apiSecret,管控非微服务接口及微服务层级接口
- [ ] 限流配置(漏桶,令牌桶,固定时间窗口,滑动时间窗口)
- [ ] 权限认证RBAC
- [ ] token(jwt,oauth2)
- [ ] 非微服接口文档生成
- [x] 网关管理平台(web)-日志记录查看,路由配置,用户管理,菜单管理,部门管理

## 生产发布(内网)
内网地址 
http://192.168.192.201
http://192.168.192.202

外网地址
http://152.70.108.210/
http://140.83.39.222/
http://155.248.183.128/

使用Zerotier进行内网发布,公网使用Oracle Cloud LB进行负载均衡发布

使用如下方式加入内网（linux）
```shell
curl -s https://install.zerotier.com | sudo bash
zerotier-cli join 83048a0632ecb930
```

## 接口文档(内网)
使用smart-doc + torna来管理接口文档

torna地址 http://192.168.192.201:7700,账号密码自行注册

内网直接访问[分享地址-网关核心模块](http://192.168.192.201:7700/#/share/7YX0lXxR)

内网直接访问[分享地址-网关管理模块](http://192.168.192.201:7700/#/share/rnzdb8jY)