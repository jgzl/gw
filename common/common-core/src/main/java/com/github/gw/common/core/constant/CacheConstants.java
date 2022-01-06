package com.github.gw.common.core.constant;

/**
 * 缓存的key 常量
 *
 * @author li7hai26@gmail.com
 */
public class CacheConstants {

    /**
     * 权限缓存前缀
     */
    public final static String SYS_PREFIX = "gw:";

    /**
     * 缓存有效期，默认720（分钟）
     */
    public final static long EXPIRATION = 720;

    /**
     * 缓存刷新时间，默认120（分钟）
     */
    public final static long REFRESH_TIME = 120;

    /**
     * 权限缓存前缀
     */
    public final static String LOGIN_TOKEN_KEY = SYS_PREFIX+"login_tokens:";

    /**
     * 验证码缓存key
     */
    public final static String DEFAULT_CODE_KEY = SYS_PREFIX+"gateway:validateCode:";

    /**
     * 路由存放
     */
    public final static String ROUTE_KEY = SYS_PREFIX+"gateway_route_key";

    /**
     * 内存reload 时间
     */
    public final static String ROUTE_JVM_RELOAD_TOPIC = SYS_PREFIX+"gateway_jvm_route_reload_topic";

    /**
     * redis 重新加载 路由信息
     */
    public final static String ROUTE_REDIS_RELOAD_TOPIC = SYS_PREFIX+"redis_route_reload_topic";

    /**
     * 菜单信息缓存
     */
    public final static String MENU_DETAILS = SYS_PREFIX+"menu_details";

    /**
     * 用户信息缓存
     */
    public final static String USER_DETAILS = SYS_PREFIX+"user_details";

    /**
     * 角色信息缓存
     */
    public final static String ROLE_DETAILS = SYS_PREFIX+"role_details";
}
