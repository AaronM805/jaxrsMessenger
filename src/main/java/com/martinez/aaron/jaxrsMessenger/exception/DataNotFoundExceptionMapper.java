package com.martinez.aaron.jaxrsMessenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.martinez.aaron.jaxrsMessenger.model.ErrorMessage;

@Provider
/*
 * Registers this class in Jax-Rs
 */
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	/*
	 * This catches exception and will throw it back as a resource
	 */
	public Response toResponse(DataNotFoundException exception) {
		
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 404, "http://localhost:8080/jaxrsMessenger");
		// sends response to user
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
