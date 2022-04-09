package com.github.gw.gateway.admin.gateway.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.gw.common.core.constant.CacheConstants;
import com.github.gw.common.core.constant.CommonConstants;
import com.github.gw.common.core.utils.JacksonUtils;
import com.github.gw.common.gateway.support.DynamicRouteInitEvent;
import com.github.gw.common.gateway.vo.RouteDefinitionVo;
import com.github.gw.common.model.gateway.domain.GatewayRouteConf;
import com.github.gw.gateway.admin.gateway.mapper.GatewayRouteConfMapper;
import com.github.gw.gateway.admin.gateway.service.GatewayRouteConfService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * 网关路由
 *
 * @author li7hai26@gmail.com
 * @date 2018年11月06日10:27:55
 */
@Slf4j
@AllArgsConstructor
@Service("gatewayRouteConfService")
public class GatewayRouteConfServiceImpl extends ServiceImpl<GatewayRouteConfMapper, GatewayRouteConf>
        implements GatewayRouteConfService {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 新增或者更新路由信息
     *
     * @param route 路由信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(GatewayRouteConf route) {
        // 清空Redis 缓存
        Long result = redisTemplate.opsForHash().delete(CacheConstants.ROUTE_KEY, route.getRouteId());
        log.info("清空网关路由条数:{} ", result);
        try {
            route.setDelFlag(CommonConstants.STATUS_NORMAL);
            super.saveOrUpdate(route);

            RouteDefinitionVo routeVo = new RouteDefinitionVo();
            routeVo.setId(route.getRouteId());
            routeVo.setRouteId(route.getRouteId());
            routeVo.setRouteName(route.getRouteName());
            routeVo.setOrder(route.getOrder());
            if (StrUtil.isNotBlank(route.getPredicates())) {
                routeVo.setPredicates(JacksonUtils.readValue(route.getPredicates(), new TypeReference<List<PredicateDefinition>>() {
                }));
            }
            if (StrUtil.isNotBlank(route.getFilters())) {
                routeVo.setFilters(JacksonUtils.readValue(route.getFilters(), new TypeReference<List<FilterDefinition>>() {
                }));
            }
            if (StrUtil.isNotBlank(route.getUri())) {
                routeVo.setUri(URI.create(route.getUri()));
            }
            if (StrUtil.isNotBlank(route.getMetadata())) {
                routeVo.setMetadata(JacksonUtils.readValue(route.getMetadata(), new TypeReference<Map<String, Object>>() {
                }));
            }
            // 插入生效路由
            redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionVo.class));
            redisTemplate.opsForHash().put(CacheConstants.ROUTE_KEY, routeVo.getId(), routeVo);
            log.info("更新网关路由结束");
            // 通知网关重置路由
            redisTemplate.convertAndSend(CacheConstants.ROUTE_JVM_RELOAD_TOPIC, "路由信息,网关缓存更新");
        } catch (Exception e) {
            log.error("路由配置解析失败", e);
            // 回滚路由，重新加载即可
            SpringUtil.publishEvent(new DynamicRouteInitEvent(this));
            // 抛出异常
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * 删除路由信息
     *
     * @param routeId 路由id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoute(String routeId) {
        // 清空Redis 缓存
        String[] strRouteIdArray = routeId.split(",");
        Long result = redisTemplate.opsForHash().delete(CacheConstants.ROUTE_KEY, strRouteIdArray);
        log.info("清空网关路由条数:{} ", result);
        try {
            this.remove(Wrappers.<GatewayRouteConf>update().lambda().in(GatewayRouteConf::getRouteId, strRouteIdArray));
            log.info("更新网关路由结束");
            // 通知网关重置路由
            redisTemplate.convertAndSend(CacheConstants.ROUTE_JVM_RELOAD_TOPIC, "路由信息,网关缓存更新");
        } catch (Exception e) {
            log.error("路由配置解析失败", e);
            // 回滚路由，重新加载即可
            SpringUtil.publishEvent(new DynamicRouteInitEvent(this));
            // 抛出异常
            throw new RuntimeException(e);
        }
    }
}
