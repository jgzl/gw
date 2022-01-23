package com.github.gw.common.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis Json序列化
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/24
 */
public class RedisSerializerInstance {

    private volatile static RedisSerializerInstance instance;

    private RedisSerializerInstance(){}

    public static RedisSerializerInstance getInstance(){
        if (instance == null){
            synchronized (RedisSerializerInstance.class){
                if (instance == null){
                    return new RedisSerializerInstance();
                }
            }
        }
        return instance;
    }

    public RedisSerializer<Object> defaultSerializer(){
        Jackson2JsonRedisSerializer redisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //解决查询缓存转换异常的问题
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        redisSerializer.setObjectMapper(objectMapper);
        return redisSerializer;
    }
}
