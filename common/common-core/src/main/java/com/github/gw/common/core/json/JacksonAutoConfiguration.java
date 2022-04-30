package com.github.gw.common.core.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gw.common.core.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class JacksonAutoConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return JacksonUtil.getObjectMapper();
    }

}
