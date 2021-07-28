package com.matheus.testwebcoket.publisher;

import java.time.Instant;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

  private ReactiveRedisTemplate reactiveRedisTemplate;

  public Publisher(ReactiveRedisTemplate reactiveRedisTemplate) {
    this.reactiveRedisTemplate = reactiveRedisTemplate;
  }

  public void send(String message, String topic) {
    reactiveRedisTemplate
        .convertAndSend(topic, "Message is: " + message)
        .subscribe();
  }
}