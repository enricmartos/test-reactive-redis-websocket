package com.matheus.testwebcoket.broker;

import com.matheus.testwebcoket.subscriber.Subscriber;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MessageListener {

  private Subscriber subscriber;

  public MessageListener(Subscriber subscriber) {
    this.subscriber = subscriber;
  }

  @MessageMapping("/ws")
  public void read(String topic) {
    System.out.println(topic);

    subscriber.listener(topic);
  }
}
