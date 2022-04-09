package com.github.gw.common.gateway.configuration;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.github.gw.common.core.constant.CacheConstants;
import com.github.gw.common.gateway.support.AccessConfCacheHolder;
import com.github.gw.common.model.gateway.vo.GatewayAccessConfVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.List;

/**
 * @author li7hai26@gmail.com
 * @date 2021/11/5
 * <p>
 * 动态路由配置类
 */
@Slf4j
@Configuration
public class DynamicAccessConfAutoConfiguration {

    public DynamicAccessConfAutoConfiguration(RedisMessageListenerContainer container) {
        container.addMessageListener((message, bytes) -> {
            log.warn("接收到重新加载网关访问事件");
            AccessConfCacheHolder.removeList();
            RedisTemplate redisTemplate = SpringUtil.getBean(RedisTemplate.class);
            redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(GatewayAccessConfVo.class));
            List<GatewayAccessConfVo> values = redisTemplate.<String,GatewayAccessConfVo>opsForHash().values(CacheConstants.ACCESS_CONF_KEY);
            if (CollUtil.isEmpty(values)) {
                values = ListUtil.empty();
            }
            AccessConfCacheHolder.setList(values);
        }, new ChannelTopic(CacheConstants.ACCESS_CONF_JVM_RELOAD_TOPIC));
    }

}
