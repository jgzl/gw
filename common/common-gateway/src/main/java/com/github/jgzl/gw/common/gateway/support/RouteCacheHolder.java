package com.github.jgzl.gw.common.gateway.support;

import com.github.jgzl.gw.common.gateway.vo.RouteDefinitionVo;
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
public class RouteCacheHolder {

    private final Map<String, RouteDefinitionVo> cache = new ConcurrentHashMap<>(100000);

    /**
     * 获取缓存的全部对象
     *
     * @return routeList
     */
    public List<RouteDefinitionVo> getRouteList() {
        List<RouteDefinitionVo> routeList = new ArrayList<>();
        cache.forEach((route,vo) -> routeList.add(vo));
        return routeList;
    }

    /**
     * 更新缓存
     *
     * @param routeList 缓存列表
     */
    public void setRouteList(List<RouteDefinitionVo> routeList) {
        routeList.forEach(route -> cache.put(route.getId(), route));
    }

    /**
     * 清空缓存
     */
    public void removeRouteList() {
        cache.clear();
    }

}
