package com.martinez.aaron.jaxrsMessenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.martinez.aaron.jaxrsMessenger.database.DatabaseClass;
import com.martinez.aaron.jaxrsMessenger.exception.DataNotFoundException;
import com.martinez.aaron.jaxrsMessenger.model.Message;

public class MessageService {
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1L, "Hello World", "aaron"));
		messages.put(2L,  new Message(2L, "Andre Baby", "Drizzle"));
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		
		for(Message message : messages.values()) {
			cal.setTime(message.getCreated());
			
			if(cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		List<Message> messageList = new ArrayList<>(messages.values());
		
		if(start + size > messageList.size()) {
			return new ArrayList<>();
		}
		
		return messageList.subList(start, start + size);
	}
	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		
		if(message == null) {
			/*
			 * We throw this exception to the resource.
			 */
			throw new DataNotFoundException("Message with id " + id + " not found");
		}
		
		return message;
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return messages.get(message.getId());
	}
	
	public Message updateMessage(Message message) {
		if(message.getId() <= 0) {
			return null;
		}
		Message m = messages.get(message.getId());
		messages.put(message.getId(), message);
		m = messages.get(message.getId());
		
		return m;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
