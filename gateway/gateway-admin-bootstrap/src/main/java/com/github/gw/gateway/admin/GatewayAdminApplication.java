package com.github.gw.gateway.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 获取验证码地址 http://localhost:8000/captcha/get
 * 验证验证码地址 http://localhost:8000/captcha/check
 * @author li7hai26@gmail.com
 * @date 2021/10/8
 */
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayAdminApplication.class, args);
    }

}