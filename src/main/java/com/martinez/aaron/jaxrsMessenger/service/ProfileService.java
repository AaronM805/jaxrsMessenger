package com.martinez.aaron.jaxrsMessenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.martinez.aaron.jaxrsMessenger.database.DatabaseClass;
import com.martinez.aaron.jaxrsMessenger.model.ErrorMessage;
import com.martinez.aaron.jaxrsMessenger.model.Profile;

public class ProfileService {
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("aaron", new Profile(1L, "aaron", "Aaron", "Martinez"));
		profiles.put("Drizzle", new Profile(2L, "Drizzle", "Andrea", "Garibay"));
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public List<Profile> getAllProfiles() {
		return new ArrayList<>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		/*
		 * Not ideal since we are constructing a response in the business service, which leads to low cohesion and high coupling.
		 * It could be in the resource, but in opinion, it is better to create a new class.
		 * There are subclass exceptions that implement WebApplicationException that are more granular
		 */
		ErrorMessage errorMessage = new ErrorMessage("Not Found", 404, "http://localhost:8080/jaxrsMessenger");
		Response response = Response.status(Status.NOT_FOUND)
									.entity(errorMessage)
									.build();
		
		Profile profile = profiles.get(profileName);
		
		if(profile == null) {
			// we don't have to map this
			throw new WebApplicationException(response);
		}
		
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
	
	public Profile updateProfile(Profile profile) {
		if(profile.getProfileName().isEmpty()) {
			return null;
		}
		
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
}
