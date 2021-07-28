package com.matheus.testwebcoket.controller;

import com.matheus.testwebcoket.model.ChatMessage;
import com.matheus.testwebcoket.publisher.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublishController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PublishController.class);

	@Autowired
	private Publisher publisher;

	@GetMapping(value = {"/publish/{chatMessage}/{userId}"}, produces = MediaType.TEXT_PLAIN_VALUE)
	public void publish(@PathVariable final String chatMessage, @PathVariable final String userId) {
		LOGGER.info(">> publish() chatMessage {} userId {}", chatMessage, userId);

		ChatMessage message = new ChatMessage();
		message.setText(chatMessage);

		publisher.send(userId, message);

		LOGGER.info("<< publish()");
	}
}
