package com.martinez.aaron.jaxrxMessenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("annotations")
	// matrix params are passed after a ; (this replaces the query (?) value)
	// Header param will be used more to send metadata for request such as authentication token.
	// Can be used for security purposes.
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("customHeaderValue") String header,
											@CookieParam("name") String cookie) {
		return "Matrix param: " + matrixParam + "\tHeader Param: " + header + "\tCookie: " + cookie;
	}
	
	@GET
	@Path("context")
	// Unique annotation can only be annotated to a few specia types.
	// The first special type is UriInfo
	//	Jersey will inject an instance of UriInfo
	// If we don't know what we are looking for, this is a great alternative. Otherwise, use the top version
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		String path = uriInfo.getAbsolutePath().toString();
		String cookies = headers.getCookies().toString();
		return "Path: " + path + " Cookies: " + cookies;
	}
}