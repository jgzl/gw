package com.github.jgzl.gw.common.redis.template;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class RedisObjectMapper {

    private RedisObjectMapper(){}

    public static ObjectMapper getInstance() {
        ObjectMapper objectMapper = new ObjectMapper();
        //设置序列化的域(属性,方法etc)以及修饰范围,Any包括private,public 默认是public的
        //ALL所有方位,ANY所有修饰符
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //指定输入的类型,redis反序列化需要类型,否则会被转化成LinkedHashMap
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL);
        //如果java.time包下Json报错,添加如下两行代码
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static Jackson2JsonRedisSerializer<Object> getJackson2JsonRedisSerializer(){
        // 使用 JSON 序列化方式（库是 Jackson ），序列化 VALUE 。
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(RedisObjectMapper.getInstance());
        return jackson2JsonRedisSerializer;
    }
}
