package com.github.gw.common.gateway.configuration;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/17
 */
@Configuration
public class ElasticSearchConfiguration extends ElasticsearchConfigurationSupport {

    @Bean
    @Override
    public ElasticsearchCustomConversions elasticsearchCustomConversions() {
        List<Converter<?, ?>> converters = Lists.newArrayList();
        converters.add(DateToLocalDateTimeConverter.INSTANCE);
        converters.add(StringToLocalDateTimeConverter.INSTANCE);
        converters.add(LongToLocalDateTimeConverter.INSTANCE);
        return new ElasticsearchCustomConversions(converters);
    }

    @ReadingConverter
    enum LongToLocalDateTimeConverter implements Converter<Long, LocalDateTime> {

        INSTANCE;

        @Override
        public java.time.LocalDateTime convert(Long source) {
            return Instant.ofEpochMilli(source).atZone(ZoneId.systemDefault()).toLocalDateTime();
        }

    }

    @ReadingConverter
    enum StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

        INSTANCE;

        @Override
        public java.time.LocalDateTime convert(String source) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(source, df);
        }

    }

    @WritingConverter
    enum DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {

        INSTANCE;

        @Override
        public LocalDateTime convert(Date date) {
            Instant instant = date.toInstant();
            return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
    }
}
