package com.matheus.testwebcoket.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class RedisConfiguration {

//  @Bean
//  @Primary
//  public ReactiveRedisConnectionFactory lettuceConnectionFactory() {
//
//    LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
//        .useSsl().and()
//        .commandTimeout(Duration.ofSeconds(2))
//        .shutdownTimeout(Duration.ZERO)
//        .build();
//
//    return new LettuceConnectionFactory(new RedisStandaloneConfiguration("0.0.0.0", 6379), clientConfig);
//  }

  @Bean
  public ReactiveRedisMessageListenerContainer reactiveRedisMessageListenerContainer(
      ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
    return new ReactiveRedisMessageListenerContainer(reactiveRedisConnectionFactory);
  }

  @Bean
  public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(
      ReactiveRedisConnectionFactory factory) {
    return new ReactiveRedisTemplate<>(factory, RedisSerializationContext.string());
  }
}
