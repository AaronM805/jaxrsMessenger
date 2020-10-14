package com.martinez.aaron.jaxrxMessenger.database;

import java.util.HashMap;
import java.util.Map;

import com.martinez.aaron.jaxrxMessenger.model.Message;
import com.martinez.aaron.jaxrxMessenger.model.Profile;

/*
 * This is not thread safe
 * 
 * TODO: make thread safe
 */
public class DatabaseClass {
	
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}

}
