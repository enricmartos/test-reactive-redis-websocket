package com.matheus.testwebcoket.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatMessage implements Serializable {

	private String text;

}
