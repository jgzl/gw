package com.github.gw.gateway.admin.system.configuration;

import com.github.gw.common.core.constant.CacheConstants;
import com.github.gw.common.model.gateway.convert.AccessConvert;
import com.github.gw.gateway.admin.gateway.service.GatewayAccessConfService;
import com.github.gw.common.model.gateway.vo.GatewayAccessConfVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @author lihaifeng
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class SwarmDataCommandLineRunner implements CommandLineRunner{

    private final GatewayAccessConfService accessConfService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("开始预热缓存数据");
        redisTemplate.delete(CacheConstants.ACCESS_CONF_KEY);
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(GatewayAccessConfVo.class));
        accessConfService.list().forEach(item -> {
            GatewayAccessConfVo vo = AccessConvert.INSTANCE.convertDo2Vo(item);
            redisTemplate.opsForHash().put(CacheConstants.ACCESS_CONF_KEY,vo.getApiKey(),vo);
        });
        redisTemplate.convertAndSend(CacheConstants.ACCESS_CONF_JVM_RELOAD_TOPIC,"缓存更新");
        log.info("结束预热缓存数据");
    }
}
