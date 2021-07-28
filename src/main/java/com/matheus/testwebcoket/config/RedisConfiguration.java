package com.matheus.testwebcoket.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;

@Configuration
public class RedisConfiguration {

  @Bean
  @Primary
  ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(RedisProperties redisProperties) {
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
    return new LettuceConnectionFactory(redisStandaloneConfiguration);
  }

  @Bean
  public ReactiveRedisMessageListenerContainer reactiveRedisMessageListenerContainer(
      ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
    return new ReactiveRedisMessageListenerContainer(reactiveRedisConnectionFactory);
  }

  @Bean
  ReactiveStringRedisTemplate template(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
    return new ReactiveStringRedisTemplate(reactiveRedisConnectionFactory);
  }

}
