package com.martinez.aaron.jaxrxMessenger.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.martinez.aaron.jaxrxMessenger.model.Message;
import com.martinez.aaron.jaxrxMessenger.service.MessageService;

@Path("messages")
public class MessageResource {
	MessageService service = new MessageService();
	
	/**
	 * Method handles HTTP GET request. The returned object will be sent to the class as "text/plain" media type.
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages() {
		return service.getAllMessages();
	}
	
	@GET
	// variable path
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_XML)
	// Injects path param to parameter type.
	public Message getMessage(@PathParam("messageId") long messageId) {
		return service.getMessage(messageId);
	}
}
