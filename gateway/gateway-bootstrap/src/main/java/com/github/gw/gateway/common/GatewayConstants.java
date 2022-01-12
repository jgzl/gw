package com.github.gw.gateway.common;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/20
 */
public class GatewayConstants {

    /**
     * 统一前缀
     */
    public static final String SYSTEM_PREFIX = "gateway";
    /**
     * 环境
     */
    public static final String X_BUSINESS_ENV = "x_business_env";
    /**
     * 访问key
     */
    public static final String X_BUSINESS_API_KEY = "x_business_api_key";
    /**
     * 访问secret
     */
    public static final String X_BUSINESS_API_SECRET = "x_business_api_secret";
    /**
     * 访问系统
     */
    public static final String X_BUSINESS_API_SYSTEM = "x_business_api_system";
    /**
     * 默认大小限制
     */
    public static final Integer DEFAULT_LIMIT = 20*1024*1024;
}
