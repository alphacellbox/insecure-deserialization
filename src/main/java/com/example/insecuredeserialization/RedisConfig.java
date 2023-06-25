package com.example.insecuredeserialization;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
@EnableRedisRepositories
@AllArgsConstructor
public class RedisConfig {
   RedisProperties rp;

    @Bean
    public RedisCustomConversions redisCustomConversions(OffsetDateTimeToBytesConverter offsetToBytes, BytesToOffsetDateTimeConverter bytesToOffset) {
        return new RedisCustomConversions(Arrays.asList(offsetToBytes, bytesToOffset));
    }

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//        configuration.setHostName(rp.getHost());
//        configuration.setPort(rp.getPort());
//        configuration.setPassword(rp.getPassword());
        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<Serializable, Serializable> template() {
        RedisTemplate<Serializable, Serializable> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        return template;
    }

    @Component
    @ReadingConverter
    public class BytesToOffsetDateTimeConverter implements Converter<byte[], OffsetDateTime> {
        @Override
        public OffsetDateTime convert(final byte[] source) {
            return OffsetDateTime.parse(new String(source), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }
    }

    @Component
    @WritingConverter
    public class OffsetDateTimeToBytesConverter implements Converter<OffsetDateTime, byte[]> {
        @Override
        public byte[] convert(final OffsetDateTime source) {
            return source.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME).getBytes();
        }
    }

}
