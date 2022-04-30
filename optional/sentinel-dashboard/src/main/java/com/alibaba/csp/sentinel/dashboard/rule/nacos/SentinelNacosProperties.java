package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lihaifeng
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "sentinel.nacos")
public class SentinelNacosProperties {

	/**
	 * nacos discovery server address.
	 */
	private String serverAddr;

	/**
	 * the nacos authentication username.
	 */
	private String username;

	/**
	 * the nacos authentication password.
	 */
	private String password;

	/**
	 * namespace, separation registry of different environments.
	 */
	private String namespace;

	private String group;
}
