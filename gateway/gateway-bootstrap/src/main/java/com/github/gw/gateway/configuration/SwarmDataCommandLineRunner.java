package com.github.gw.gateway.configuration;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import com.github.gw.common.core.constant.CacheConstants;
import com.github.gw.common.gateway.support.AccessConfCacheHolder;
import com.github.gw.gateway.admin.gateway.vo.GatewayAccessConfVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.List;

/**
 * @author lihaifeng
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class SwarmDataCommandLineRunner implements CommandLineRunner{

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("开始预热缓存数据");
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(GatewayAccessConfVo.class));
        List<GatewayAccessConfVo> vos = redisTemplate.<String, GatewayAccessConfVo>opsForHash().values(CacheConstants.ACCESS_CONF_KEY);
        if (CollUtil.isEmpty(vos)) {
            vos = ListUtil.empty();
        }
        AccessConfCacheHolder.setList(vos);
        log.info("结束预热缓存数据");
    }
}
