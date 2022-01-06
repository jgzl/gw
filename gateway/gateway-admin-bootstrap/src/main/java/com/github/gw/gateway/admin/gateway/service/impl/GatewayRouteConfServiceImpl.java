package com.github.gw.gateway.admin.gateway.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.gw.common.core.constant.CacheConstants;
import com.github.gw.common.core.constant.CommonConstants;
import com.github.gw.common.core.utils.JacksonUtil;
import com.github.gw.common.gateway.support.DynamicRouteInitEvent;
import com.github.gw.common.gateway.vo.RouteDefinitionVo;
import com.github.gw.gateway.admin.gateway.domain.GatewayRouteConf;
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
import java.util.stream.Collectors;

/**
 * @author li7hai26@gmail.com
 * @date 2018年11月06日10:27:55
 * <p>
 * 动态路由处理类
 */
@Slf4j
@AllArgsConstructor
@Service("gatewayRouteConfService")
public class GatewayRouteConfServiceImpl extends ServiceImpl<GatewayRouteConfMapper, GatewayRouteConf>
		implements GatewayRouteConfService {

	private final RedisTemplate redisTemplate;

	/**
	 * 新增路由信息
	 * @param route 路由信息
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void createRoute(GatewayRouteConf route) {
		List<GatewayRouteConf> routeConfList = this.list();
		// 清空Redis 缓存
		Boolean result = redisTemplate.delete(CacheConstants.ROUTE_KEY);
		log.info("清空网关路由 {} ", result);

		try {
			routeConfList.add(route);
			// 遍历修改的routes，保存到Redis
			List<RouteDefinitionVo> routeDefinitionVoList = routeConfList.stream().map(sysRouteConf -> {
				RouteDefinitionVo routeVo = new RouteDefinitionVo();
				routeVo.setId(sysRouteConf.getRouteId());
				routeVo.setRouteId(sysRouteConf.getRouteId());
				routeVo.setRouteName(sysRouteConf.getRouteName());
				routeVo.setOrder(sysRouteConf.getOrder());
				if (StrUtil.isNotBlank(sysRouteConf.getPredicates())) {
					routeVo.setPredicates(JacksonUtil.readValue(sysRouteConf.getPredicates(), new TypeReference<List<PredicateDefinition>>() {}));
				}
				if (StrUtil.isNotBlank(sysRouteConf.getFilters())) {
					routeVo.setFilters(JacksonUtil.readValue(sysRouteConf.getFilters(), new TypeReference<List<FilterDefinition>>() {}));
				}
				if (StrUtil.isNotBlank(sysRouteConf.getUri())) {
					routeVo.setUri(URI.create(sysRouteConf.getUri()));
				}
				if (StrUtil.isNotBlank(sysRouteConf.getMetadata())) {
					routeVo.setMetadata(JacksonUtil.readValue(sysRouteConf.getMetadata(), new TypeReference<Map<String, Object>>() {}));
				}
				return routeVo;
			}).collect(Collectors.toList());

			// 逻辑删除全部
			GatewayRouteConf condition = new GatewayRouteConf();
			condition.setDelFlag(CommonConstants.STATUS_NORMAL);
			this.remove(new UpdateWrapper<>(condition));

			// 插入生效路由
			routeConfList = routeDefinitionVoList.stream().map(vo -> {
				redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionVo.class));
				redisTemplate.opsForHash().put(CacheConstants.ROUTE_KEY, vo.getId(), vo);
				GatewayRouteConf routeConf = new GatewayRouteConf();
				routeConf.setRouteId(vo.getRouteId());
				routeConf.setRouteName(vo.getRouteName());
				routeConf.setOrder(vo.getOrder());
				if (CollUtil.isNotEmpty(vo.getPredicates())) {
					routeConf.setPredicates(JacksonUtil.writeValueAsString(vo.getPredicates()));
				}
				if (CollUtil.isNotEmpty(vo.getFilters())) {
					routeConf.setFilters(JacksonUtil.writeValueAsString(vo.getFilters()));
				}
				routeConf.setUri(vo.getUri().toString());
				routeConf.setMetadata(JacksonUtil.writeValueAsString(vo.getMetadata()));
				routeConf.setDelFlag(CommonConstants.STATUS_NORMAL);
				return routeConf;
			}).collect(Collectors.toList());
			this.saveBatch(routeConfList);
			log.info("更新网关路由结束");

			// 通知网关重置路由
			redisTemplate.convertAndSend(CacheConstants.ROUTE_JVM_RELOAD_TOPIC, "路由信息,网关缓存更新");
		}catch (Exception e) {
			log.error("路由配置解析失败", e);
			// 回滚路由，重新加载即可
			SpringUtil.publishEvent(new DynamicRouteInitEvent(this));
			// 抛出异常
			throw new RuntimeException(e);
		}
	}

	/**
	 * 更新路由信息
	 * @param route 路由信息
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateRoute(GatewayRouteConf route) {
		List<GatewayRouteConf> routeConfList = this.list();
		// 清空Redis 缓存
		Boolean result = redisTemplate.delete(CacheConstants.ROUTE_KEY);
		log.info("清空网关路由 {} ", result);

		try {
			// 遍历修改的routes，保存到Redis
			List<RouteDefinitionVo> routeDefinitionVoList = routeConfList.stream().map(sysRouteConf -> {
				RouteDefinitionVo routeVo = new RouteDefinitionVo();
				if (sysRouteConf.getRouteId().equals(route.getRouteId())) {
					routeVo.setId(route.getRouteId());
					routeVo.setRouteId(route.getRouteId());
					routeVo.setRouteName(route.getRouteName());
					routeVo.setOrder(route.getOrder());
					if (StrUtil.isNotBlank(route.getPredicates())) {
						routeVo.setPredicates(JacksonUtil.readValue(route.getPredicates(), new TypeReference<List<PredicateDefinition>>() {}));
					}
					if (StrUtil.isNotBlank(route.getFilters())) {
						routeVo.setFilters(JacksonUtil.readValue(route.getFilters(), new TypeReference<List<FilterDefinition>>() {}));
					}
					if (StrUtil.isNotBlank(route.getUri())) {
						routeVo.setUri(URI.create(route.getUri()));
					}
					if (StrUtil.isNotBlank(route.getMetadata())) {
						routeVo.setMetadata(JacksonUtil.readValue(route.getMetadata(), new TypeReference<Map<String, Object>>() {}));
					}
				}else {
					routeVo.setId(sysRouteConf.getRouteId());
					routeVo.setRouteId(sysRouteConf.getRouteId());
					routeVo.setRouteName(sysRouteConf.getRouteName());
					routeVo.setOrder(sysRouteConf.getOrder());
					if (StrUtil.isNotBlank(sysRouteConf.getPredicates())) {
						routeVo.setPredicates(JacksonUtil.readValue(sysRouteConf.getPredicates(), new TypeReference<List<PredicateDefinition>>() {}));
					}
					if (StrUtil.isNotBlank(sysRouteConf.getFilters())) {
						routeVo.setFilters(JacksonUtil.readValue(sysRouteConf.getFilters(), new TypeReference<List<FilterDefinition>>() {}));
					}
					if (StrUtil.isNotBlank(sysRouteConf.getUri())) {
						routeVo.setUri(URI.create(sysRouteConf.getUri()));
					}
					if (StrUtil.isNotBlank(sysRouteConf.getMetadata())) {
						routeVo.setMetadata(JacksonUtil.readValue(sysRouteConf.getMetadata(), new TypeReference<Map<String, Object>>() {}));
					}
				}
				return routeVo;
			}).collect(Collectors.toList());

			// 逻辑删除全部
			GatewayRouteConf condition = new GatewayRouteConf();
			condition.setDelFlag(CommonConstants.STATUS_NORMAL);
			this.remove(new UpdateWrapper<>(condition));

			// 插入生效路由
			routeConfList = routeDefinitionVoList.stream().map(vo -> {
				redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionVo.class));
				redisTemplate.opsForHash().put(CacheConstants.ROUTE_KEY, vo.getId(), vo);
				GatewayRouteConf routeConf = new GatewayRouteConf();
				routeConf.setRouteId(vo.getRouteId());
				routeConf.setRouteName(vo.getRouteName());
				routeConf.setOrder(vo.getOrder());
				if (CollUtil.isNotEmpty(vo.getPredicates())) {
					routeConf.setPredicates(JacksonUtil.writeValueAsString(vo.getPredicates()));
				}
				if (CollUtil.isNotEmpty(vo.getFilters())) {
					routeConf.setFilters(JacksonUtil.writeValueAsString(vo.getFilters()));
				}
				routeConf.setUri(vo.getUri().toString());
				routeConf.setMetadata(JacksonUtil.writeValueAsString(vo.getMetadata()));
				routeConf.setDelFlag(CommonConstants.STATUS_NORMAL);
				return routeConf;
			}).collect(Collectors.toList());
			this.saveBatch(routeConfList);
			log.info("更新网关路由结束");

			// 通知网关重置路由
			redisTemplate.convertAndSend(CacheConstants.ROUTE_JVM_RELOAD_TOPIC, "路由信息,网关缓存更新");
		}catch (Exception e) {
			log.error("路由配置解析失败", e);
			// 回滚路由，重新加载即可
			SpringUtil.publishEvent(new DynamicRouteInitEvent(this));
			// 抛出异常
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除路由信息
	 * @param routeId 路由id
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteRoute(String routeId) {
		List<GatewayRouteConf> routeConfList = this.list();
		// 清空Redis 缓存
		Boolean result = redisTemplate.delete(CacheConstants.ROUTE_KEY);
		log.info("清空网关路由 {} ", result);

		try {
			// 遍历修改的routes，保存到Redis
			List<RouteDefinitionVo> routeDefinitionVoList = routeConfList.stream().map(sysRouteConf -> {
				RouteDefinitionVo routeVo = new RouteDefinitionVo();
				routeVo.setId(sysRouteConf.getRouteId());
				routeVo.setRouteId(sysRouteConf.getRouteId());
				routeVo.setRouteName(sysRouteConf.getRouteName());
				routeVo.setOrder(sysRouteConf.getOrder());
				if (StrUtil.isNotBlank(sysRouteConf.getPredicates())) {
					routeVo.setPredicates(JacksonUtil.readValue(sysRouteConf.getPredicates(), new TypeReference<List<PredicateDefinition>>() {}));
				}
				if (StrUtil.isNotBlank(sysRouteConf.getFilters())) {
					routeVo.setFilters(JacksonUtil.readValue(sysRouteConf.getFilters(), new TypeReference<List<FilterDefinition>>() {}));
				}
				if (StrUtil.isNotBlank(sysRouteConf.getUri())) {
					routeVo.setUri(URI.create(sysRouteConf.getUri()));
				}
				if (StrUtil.isNotBlank(sysRouteConf.getMetadata())) {
					routeVo.setMetadata(JacksonUtil.readValue(sysRouteConf.getMetadata(), new TypeReference<Map<String, Object>>() {}));
				}
				return routeVo;
			}).collect(Collectors.toList());

			routeDefinitionVoList = routeDefinitionVoList.stream().filter(sysRouteConf -> !sysRouteConf.getRouteId().equals(routeId)).collect(Collectors.toList());

			// 逻辑删除全部
			GatewayRouteConf condition = new GatewayRouteConf();
			condition.setDelFlag(CommonConstants.STATUS_NORMAL);
			this.remove(new UpdateWrapper<>(condition));

			// 插入生效路由
			routeConfList = routeDefinitionVoList.stream().map(vo -> {
				redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionVo.class));
				redisTemplate.opsForHash().put(CacheConstants.ROUTE_KEY, vo.getId(), vo);
				GatewayRouteConf routeConf = new GatewayRouteConf();
				routeConf.setRouteId(vo.getRouteId());
				routeConf.setRouteName(vo.getRouteName());
				routeConf.setOrder(vo.getOrder());
				if (CollUtil.isNotEmpty(vo.getPredicates())) {
					routeConf.setPredicates(JacksonUtil.writeValueAsString(vo.getPredicates()));
				}
				if (CollUtil.isNotEmpty(vo.getFilters())) {
					routeConf.setFilters(JacksonUtil.writeValueAsString(vo.getFilters()));
				}
				routeConf.setUri(vo.getUri().toString());
				routeConf.setMetadata(JacksonUtil.writeValueAsString(vo.getMetadata()));
				routeConf.setDelFlag(CommonConstants.STATUS_NORMAL);
				return routeConf;
			}).collect(Collectors.toList());
			this.saveBatch(routeConfList);
			log.info("更新网关路由结束");

			// 通知网关重置路由
			redisTemplate.convertAndSend(CacheConstants.ROUTE_JVM_RELOAD_TOPIC, "路由信息,网关缓存更新");
		}catch (Exception e) {
			log.error("路由配置解析失败", e);
			// 回滚路由，重新加载即可
			SpringUtil.publishEvent(new DynamicRouteInitEvent(this));
			// 抛出异常
			throw new RuntimeException(e);
		}
	}
}
