package br.com.guarani.rta.rest.resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;

import javax.annotation.security.PermitAll;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.StreamingOutput;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.jersey.core.spi.factory.ResponseBuilderHeaders;

@Component
@Path("/files")
public class DownloadResource {
	
	  
	
	  
	  
	   @Path("/hello")
	   @GET
	   @Produces(MediaType.TEXT_HTML)
	   public String returnVersion() {
		System.out.println("Hello");
	    return "Hello User!";
	   }
	  
	    
	    @GET
	    @Path("/word/{filename}")
	    @Produces("application/msword")
	    public Response downloadFileWord(@PathParam("filename")  String fileName, @Context ServletContext ct){
	    	File file = new File(ct.getRealPath("/download/layout/"+fileName));	         
	        Response response = null;

	    	if(file.exists()){
	    		ResponseBuilder builder = Response.ok((Object) file);
	    		builder.header("Content-Disposition", "attachment; filename="+file.getName());
		        return builder.build();
	    	}else{
	    		response = Response.status(404).
	    	              entity("FILE NOT FOUND: " + fileName).
	    	              build();
	    	}
			return response;	    	
	    } 
	     
	    
	    @GET
	    @Path("/word/apk/{filename}")
	    @Produces("application/msword")
	    public Response downloadFileApk(@PathParam("filename")  String fileName, @Context ServletContext ct){
	      	File file = new File(ct.getRealPath("/download/apk/"+fileName));

	      	ResponseBuilder response = Response.ok((Object) file);
	        response.header("Content-Disposition", "attachment; filename="+file.getName());
	        return response.build();
	    } 
	     
	     
				

}
