package com.matheus.testwebcoket.publisher;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FakePublisher {

  private Publisher publisher;

  public FakePublisher(Publisher publisher) {
    this.publisher = publisher;
  }

  @Scheduled(fixedDelay = 30000)
  public void publish() {
    publisher.send("matheus");
  }
}