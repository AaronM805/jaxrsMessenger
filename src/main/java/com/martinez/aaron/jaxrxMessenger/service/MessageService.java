package com.martinez.aaron.jaxrxMessenger.service;

import java.util.ArrayList;
import java.util.List;

import com.martinez.aaron.jaxrxMessenger.model.Message;

public class MessageService {

	public List<Message> getAllMessages() {
		Message m1 = new Message(1L, "Hello World", "Aaron M");
		Message m2 = new Message(1L, "Andrea is Ready", "Drizzle");
		
		List<Message> messages = new ArrayList<>();
		messages.add(m1);
		messages.add(m2);
		
		return messages;
	}
}
