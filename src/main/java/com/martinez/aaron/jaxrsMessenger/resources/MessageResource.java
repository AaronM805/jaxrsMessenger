 package com.martinez.aaron.jaxrsMessenger.resources;

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

import com.martinez.aaron.jaxrsMessenger.model.Message;
import com.martinez.aaron.jaxrsMessenger.resources.beans.MessageFilterBean;
import com.martinez.aaron.jaxrsMessenger.service.MessageService;

import javax.ws.rs.core.UriInfo;

@Path("/messages")
// If every method consumes/produces the same media type, we can just annotate the whole class.
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
//@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
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
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		/*
		 * since message resource doesn't catch the exception, it gets thrown further to the Tomcat Servlet container and the servlet container has default behavior
		 * for exceptions that get bubbled up.
		 * 
		 * What we want is for the framework to catch this and return a JSON response so that it doesn't go all of the way up to the Tomcat Servlet container.
		 */
		Message message = service.getMessage(messageId);
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		// TODO: implement comments resource
//		message.addLink(getUriForComments(uriInfo, message), "comments");
		return message;
	}
	
	/**
	 * Method handles HTTP GET request. The returned object will be sent to the class as "text/plain" media type.
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJsonMessages(@QueryParam("year") int year, @QueryParam("start") int start,
									 @QueryParam("size") int size) {
		System.out.println("JSON content type");
		if(year > 0) {
			return service.getAllMessagesForYear(year);
		}
		
		if(start > 0 && size > 0) {
			return service.getAllMessagesPaginated(start - 1, size);
		}
		
		return service.getAllMessages();
	}
	
	 @GET
	 @Produces(MediaType.TEXT_XML)
	// This is an alternative to above method
	public List<Message> getXmlMessages(@BeanParam MessageFilterBean filterBean) {
		 System.out.println("XML content type");
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
	
	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String url = uriInfo.getBaseUriBuilder()
							.path(ProfileResource.class)
							.path(message.getAuthor())
							.build()
							.toString();
		return url;
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String url = uriInfo.getBaseUriBuilder()
							.path(MessageResource.class)
							.path(Long.toString(message.getId()))
							.build()
							.toString();
		return url;
	}
	
	private String getUriForComments(UriInfo uriInfo, Message message) {
		
		String url = uriInfo.getBaseUriBuilder()
							.path(MessageResource.class)
							.path(MessageResource.class, "getCommentResource")
							.path(CommentResource.class)
							.resolveTemplate("messageId", message.getId())
							.build()
							.toString();
		return url;
	}
}