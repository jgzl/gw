package com.github.jgzl.gw.common.gateway.support;

import com.github.jgzl.gw.common.model.gateway.vo.GatewayAccessConfVo;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author li7hai26@gmail.com
 * @date 2019-08-16
 * <p>
 * 路由缓存工具类
 */
@UtilityClass
public class AccessConfCacheHolder {

    private final Map<String,GatewayAccessConfVo> cache = new ConcurrentHashMap<>(100000);

    /**
     * 获取缓存的全部对象
     *
     * @return routeList
     */
    public List<GatewayAccessConfVo> getList() {
        List<GatewayAccessConfVo> list = new ArrayList<>();
        cache.forEach((route,vo) -> list.add(vo));
        return list;
    }

    /**
     * 获取缓存的对象
     *
     */
    public GatewayAccessConfVo get(String key) {
        return cache.get(key);
    }

    /**
     * 更新缓存
     *
     * @param list 缓存列表
     */
    public void setList(List<GatewayAccessConfVo> list) {
        list.forEach(item -> cache.put(item.getApiKey(), item));
    }

    /**
     * 清空缓存
     */
    public void removeList() {
        cache.clear();
    }

}
