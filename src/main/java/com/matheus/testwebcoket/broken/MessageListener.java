package com.matheus.testwebcoket.broken;

import com.matheus.testwebcoket.subscriber.Subscriber;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

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
