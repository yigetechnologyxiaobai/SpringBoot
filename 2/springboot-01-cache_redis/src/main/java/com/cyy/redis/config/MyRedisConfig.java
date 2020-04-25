package com.cyy.redis.config;

import com.cyy.redis.pojo.User;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, User > empRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, User> template = new RedisTemplate<Object, User>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<User>(User.class);
        template.setDefaultSerializer(serializer);
        return template;
    }
    
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(User.class);
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer);
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    }

}
