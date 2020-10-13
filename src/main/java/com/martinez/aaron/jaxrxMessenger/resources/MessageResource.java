package com.martinez.aaron.jaxrxMessenger.resources;

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

import com.martinez.aaron.jaxrxMessenger.model.Message;
import com.martinez.aaron.jaxrxMessenger.service.MessageService;

@Path("messages")
public class MessageResource {
	MessageService service = new MessageService();
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// Accept the Model type as argument to bind to the request body
	// Jersey will know that this is what you are expecting
	public Message addMessage(Message message) {
		return service.addMessage(message);
	}
	
	@GET
	// variable path
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	// Injects path param to parameter type.
	public Message getMessage(@PathParam("messageId") long messageId) {
		return service.getMessage(messageId);
	}
	
	/**
	 * Method handles HTTP GET request. The returned object will be sent to the class as "text/plain" media type.
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages() {
		return service.getAllMessages();
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message removeMessage(@PathParam("messageId") long messageId) {
		return service.removeMessage(messageId);
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return service.updateMessage(message);
	}
	
}