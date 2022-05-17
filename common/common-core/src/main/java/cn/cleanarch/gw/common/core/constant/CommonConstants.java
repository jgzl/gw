package cn.cleanarch.gw.common.core.constant;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/16
 */
public class CommonConstants {

    /**
     * 删除
     */
    public final static String STATUS_DEL = "1";

    /**
     * 正常
     */
    public final static String STATUS_NORMAL = "0";

    /**
     * 锁定
     */
    public static final String STATUS_LOCK = "9";

    /**
     * 菜单树根节点
     */
    public static final Long TREE_ROOT_ID = -1L;

    /**
     * 系统标志前缀
     */
    public final static String CONFIGURATION_PREFIX = "infra";

    /**
     * 系统标志前缀
     */
    public final static String CACHE_PREFIX = CONFIGURATION_PREFIX +":";
}
