package com.matheus.testwebcoket.publisher;

import com.matheus.testwebcoket.model.ChatMessage;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

  private final ReactiveStringRedisTemplate reactiveRedisTemplate;

  public Publisher(ReactiveStringRedisTemplate reactiveRedisTemplate) {
    this.reactiveRedisTemplate = reactiveRedisTemplate;
  }

  public void send(String topic, ChatMessage message) {
    reactiveRedisTemplate
        .convertAndSend(topic, message.getText())
        .subscribe();
  }
}