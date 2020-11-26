 package com.martinez.aaron.jaxrxMessenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.martinez.aaron.jaxrxMessenger.model.Message;
import com.martinez.aaron.jaxrxMessenger.resources.beans.MessageFilterBean;
import com.martinez.aaron.jaxrxMessenger.service.MessageService;

@Path("/messages")
// If every method consumes/produces the same media type, we can just annotate the whole class.
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	MessageService service = new MessageService();
	
	@POST
	// Accept the Model type as argument to bind to the request body
	// Jersey will know that this is what you are expecting
//	public Message addMessage(Message message) {
	// To send more information than just the message, we want to send a response
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = service.addMessage(message);
		// set all different values into the Builder pattern and then build the response
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build();
		return Response.created(uri) // sets status and location header of new resource. ALWAYS DO THIS
					   .entity(newMessage)
					   .build();
	}
	
	@GET
	// variable path
	@Path("/{messageId}")
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
	public List<Message> getMessages(@QueryParam("year") int year, @QueryParam("start") int start,
									 @QueryParam("size") int size) {
		if(year > 0) {
			return service.getAllMessagesForYear(year);
		}
		
		if(start > 0 && size > 0) {
			return service.getAllMessagesPaginated(start - 1, size);
		}
		
		return service.getAllMessages();
	}
	
//	@GET
	// This is an alternative to above method
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if(filterBean.getYear() > 0) {
			return service.getAllMessagesForYear(filterBean.getYear());
		}
		
		if(filterBean.getStart() > 0 && filterBean.getSize() > 0) {
			return service.getAllMessagesPaginated(filterBean.getStart() - 1, filterBean.getSize());
		}
		return service.getAllMessages();
		
	}
	
	@DELETE
	@Path("/{messageId}")
	public Message removeMessage(@PathParam("messageId") long messageId) {
		return service.removeMessage(messageId);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return service.updateMessage(message);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
}