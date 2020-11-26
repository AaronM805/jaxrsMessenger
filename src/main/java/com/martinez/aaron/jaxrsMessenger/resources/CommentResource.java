package com.martinez.aaron.jaxrsMessenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/") // annotation is optional for sub-resources
public class CommentResource {
	
	@GET
	public String test() {
		return "new sub-resource";
	}
	
	@GET
	@Path("/{commentId}")
	public String test2(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return "Comment Id: " + commentId + " Message Id: " + messageId;
	}
	
	/* TODO: Implement rest of the functionality
	 * Comment Resource
	 * GET all comments
	 * get comment
	 * add comment
	 * update comment
	 * delete comment
	 * 
	 * 
	 * Comment class
	 * 	id
	 * 	message
	 * 	created
	 * 	author
	 * 
	 * 
	 * add following to Message class
	 * 	- comments map
	 * 	- get comments // make it transient
	 * 	- set comments
	 * 
	 * Comment Service
	 *	- get all comments
	 *	- get comment
	 *	- add comment
	 *	- update comment
	 *	- delete comment
	 */
}
