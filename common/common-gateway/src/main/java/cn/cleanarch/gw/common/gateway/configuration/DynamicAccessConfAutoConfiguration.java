package cn.cleanarch.gw.common.gateway.configuration;

import cn.cleanarch.gw.common.core.constant.CacheConstants;
import cn.cleanarch.gw.common.gateway.support.GatewayAccessConfCacheHolder;
import cn.cleanarch.gw.common.model.gateway.vo.GatewayAccessConfVo;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

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
            GatewayAccessConfCacheHolder.removeList();
            RedisTemplate<String,Object> redisTemplate = SpringUtil.getBean(new TypeReference<RedisTemplate<String,Object>>() {});
            List<GatewayAccessConfVo> values = redisTemplate.<String,GatewayAccessConfVo>opsForHash().values(CacheConstants.ACCESS_CONF_KEY);
            if (CollUtil.isEmpty(values)) {
                values = ListUtil.empty();
            }
            GatewayAccessConfCacheHolder.setList(values);
        }, new ChannelTopic(CacheConstants.ACCESS_CONF_JVM_RELOAD_TOPIC));
    }

}
