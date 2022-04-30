package com.github.gw.common.gateway.support;

import cn.hutool.core.collection.CollUtil;
import com.github.gw.common.core.constant.CacheConstants;
import com.github.gw.common.gateway.vo.RouteDefinitionVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author li7hai26@gmail.com
 * @date 2021/10/31
 * <p>
 * redis 保存路由信息，优先级比配置文件高
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisRouteDefinitionWriter implements RouteDefinitionRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(r -> {
            RouteDefinitionVo vo = new RouteDefinitionVo();
            BeanUtils.copyProperties(r, vo);
            log.info("保存路由信息{}", vo);
            redisTemplate.opsForHash().put(CacheConstants.ROUTE_KEY, r.getId(), vo);
            redisTemplate.convertAndSend(CacheConstants.ROUTE_JVM_RELOAD_TOPIC, "新增路由信息,网关缓存更新");
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            log.info("删除路由信息{}", id);
            redisTemplate.opsForHash().delete(CacheConstants.ROUTE_KEY, id);
            redisTemplate.convertAndSend(CacheConstants.ROUTE_JVM_RELOAD_TOPIC, "删除路由信息,网关缓存更新");
            return Mono.empty();
        });
    }

    /**
     * 动态路由入口
     * <p>
     * 1. 先从内存中获取 2. 为空加载Redis中数据 3. 更新内存
     *
     * @return
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinitionVo> routeList = RouteCacheHolder.getRouteList();
        if (CollUtil.isNotEmpty(routeList)) {
            log.debug("内存中路由定义条数： {}， {}", routeList.size(), routeList);
            return Flux.fromIterable(routeList);
        }
        List<RouteDefinitionVo> values = redisTemplate.<String, RouteDefinitionVo>opsForHash().values(CacheConstants.ROUTE_KEY);
        log.info("redis中路由定义条数： {}， {}", values.size(), values);
        RouteCacheHolder.setRouteList(values);
        return Flux.fromIterable(values);
    }

}