package com.martinez.aaron.jaxrxMessenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.martinez.aaron.jaxrxMessenger.database.DatabaseClass;
import com.martinez.aaron.jaxrxMessenger.model.Message;

public class MessageService {
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1L, "Hello World", "Aaron"));
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
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		return messages.put(message.getId(), message);
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
