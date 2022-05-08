package com.github.jgzl.gw.gateway.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.ssssssss.magicapi.cluster.MagicClusterConfiguration;

/**
 *
 * @author li7hai26@gmail.com
 * @date 2021/10/8
 */
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication(exclude = {ElasticsearchRestClientAutoConfiguration.class, MagicClusterConfiguration.class})
public class GatewayAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayAdminApplication.class, args);
    }

}