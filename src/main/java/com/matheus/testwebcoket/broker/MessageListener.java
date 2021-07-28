package com.matheus.testwebcoket.broker;

import com.matheus.testwebcoket.subscriber.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;


@Controller
public class MessageListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class.getName());

  private Subscriber subscriber;

  public MessageListener(Subscriber subscriber) {
    this.subscriber = subscriber;
  }

  @MessageMapping("/ws")
  public void read(String topic) {
    LOGGER.info("Listening to topic {}", topic);

    subscriber.listener(topic);
  }
}
