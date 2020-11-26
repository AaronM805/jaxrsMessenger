package com.martinez.aaron.jaxrsMessenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.martinez.aaron.jaxrsMessenger.model.Profile;
import com.martinez.aaron.jaxrsMessenger.service.ProfileService;

@Path("profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
	ProfileService service = new ProfileService();
	
	@POST
	public Profile addProfile(Profile profile) {
		return service.addProfile(profile);
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName) {
		return service.getProfile(profileName);
	}
	
	@GET
	public List<Profile> getProfiles() {
		return service.getAllProfiles();
	}
	
	@DELETE
	@Path("{profileName}")
	public Profile removeProfile(@PathParam("profileName") String profileName) {
		return service.removeProfile(profileName);
	}
	
	@PUT
	@Path("{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
		profile.setProfileName(profileName);
		return service.updateProfile(profile);
	}

}
