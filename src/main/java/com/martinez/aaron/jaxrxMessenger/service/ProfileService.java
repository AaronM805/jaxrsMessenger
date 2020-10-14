package com.martinez.aaron.jaxrxMessenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.martinez.aaron.jaxrxMessenger.database.DatabaseClass;
import com.martinez.aaron.jaxrxMessenger.model.Profile;

public class ProfileService {
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("aaron", new Profile(1L, "aaron", "Aaron", "Martinez"));
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
		return profiles.get(profileName);
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
