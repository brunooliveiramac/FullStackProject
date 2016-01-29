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
	   
	
	/*  @GET
	  @Path("/download")
	  @Produces(MediaType.APPLICATION_OCTET_STREAM)
	  public Response downloadFilebyPath(@Context ServletContext ct) {
		  File folder = new File(ct.getRealPath("/download/layout/"));
		  System.out.println(folder.getAbsolutePath());
		  String fileName = "/Ola.docx";
		  return download(fileName, folder);
	  }
	  
	  private Response download(String fileName, File folder) {  
	    String fileLocation = folder.getAbsolutePath() + fileName;	    
	    Response response = null;
	    NumberFormat myFormat = NumberFormat.getInstance();
	      myFormat.setGroupingUsed(true);
	    File file = new File(folder.getAbsolutePath()  + fileName);
	    if (file.exists()) {
	     

	   
	      ResponseBuilder builder = Response.ok(file);
	      builder.header("Content-Disposition", "attachment; filename=" + file.getName());
	      response = builder.build();
	      
	     
	     long file_size = file.length();
         System.out.println(String.format("Inside downloadFile==> fileName: %s, fileSize: %s bytes", 
         fileName, myFormat.format(file_size)));
         
         
         
	    } else {
	    		  response = Response.status(404).
	              entity("FILE NOT FOUND: " + fileLocation).
	              type("text/plain").
	              build();
	    }
	    return response;
	  }  
	  
	  
	  
	  	@GET
	    @Path("/arquivo")
	    public Response downloadPdfFile(@Context ServletContext ct)
	    { final File folder = new File(ct.getRealPath("/download/layout/Ola.docx"));
	      	
	    	
	        StreamingOutput fileStream =  new StreamingOutput()
	        {
	            public void write(java.io.OutputStream output) throws IOException, WebApplicationException
	            {
	                try
	                {
	                	
	                    java.nio.file.Path path = Paths.get(folder.getAbsolutePath());
	                    byte[] data = Files.readAllBytes(path);
	                    output.write(data);
	                    output.flush();

	                }
	                catch (Exception e)
	                {
	                    throw new WebApplicationException();
	                }
	            }
	        };

	        
	        return Response
	                .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
	                .header("content-disposition","attachment; filename = myfile.dox")
	                .build();
	    }
	  	
	  	
	  	*/

	  /*  @GET
	    @Path("/get")
	    @Produces("application/msword")
        @PermitAll
	    public Response getFile(@Context ServletContext ct) {

	        File file = new File(ct.getRealPath("/download/layout/Ola.docx"));

 
	        ResponseBuilder response = Response.ok((Object) file);
	        response.header("Content-Disposition",
	            "attachment; filename=\"file_from_server.docx\"");
	        return response.build();

	    }*/
	    
	    
	    @GET
	    @Path("/word/{filename}")
	    @Produces("application/msword")
	    public Response downloadZippedFile(@PathParam("filename")  String fileName, @Context ServletContext ct){
	       
	    	File file = new File(ct.getRealPath("/download/layout/"+fileName));
	         
	        System.out.println(file.getAbsolutePath());
	  
	        ResponseBuilder response = Response.ok((Object) file);
	        response.header("Content-Disposition", "attachment; filename="+file.getName());
	        return response.build();
	    } 
	     
	    
	    
	    @RequestMapping(value = "/download/getfile", method = RequestMethod.GET, produces = "application/msword")
	    public void demo(HttpServletResponse response, @Context ServletContext ct) throws IOException {
	       
	        BufferedWriter writer = new BufferedWriter(response.getWriter());
	        try {
	        	
	        response.setHeader("Content-Disposition", "attachment; filename=\"Layout.docx\"");
	        File file = new File(ct.getRealPath("/download/layout/Layout.doc"));
		    writer.write(file.toString());
		   
	        } finally {
	        writer.flush();
	        writer.close();
	        
	        }
	    }
	     
	
				

}
