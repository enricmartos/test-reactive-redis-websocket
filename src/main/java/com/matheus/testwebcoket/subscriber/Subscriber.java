package com.matheus.testwebcoket.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.ReactiveSubscription.Message;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class Subscriber {

	private static final Logger LOGGER = LoggerFactory.getLogger(Subscriber.class.getName());

	private final ReactiveRedisMessageListenerContainer reactiveRedisMessageListenerContainer;
	private final SimpMessagingTemplate simpMessagingTemplate;

	public Subscriber(ReactiveRedisMessageListenerContainer reactiveRedisMessageListenerContainer,
					  SimpMessagingTemplate simpMessagingTemplate) {
		this.reactiveRedisMessageListenerContainer = reactiveRedisMessageListenerContainer;
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	public void listener(String topic) {
		Flux<Message<String, String>> messageFlux =
				reactiveRedisMessageListenerContainer.receive(ChannelTopic.of(topic));

		messageFlux
				.doOnNext(msg -> {
					LOGGER.info("Published new message {} to channel topic {}", msg, topic);
					simpMessagingTemplate.convertAndSend("/prefix/status/" + msg.getChannel(), msg.getMessage());
				})
				.subscribe();
	}
}