package cn.cleanarch.gw.common.core.json;

import cn.cleanarch.gw.common.core.utils.JacksonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
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
