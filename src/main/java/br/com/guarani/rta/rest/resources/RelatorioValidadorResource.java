package br.com.guarani.rta.rest.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import br.com.guarani.rta.dao.campo.CampoDAO;
import br.com.guarani.rta.entidade.Campo;
import br.com.guarani.rta.entidade.Projeto;
import br.com.guarani.rta.entidade.Projetos;
import br.com.guarani.rta.entidade.RelatorioErros;
import br.com.guarani.rta.entidade.TabelasErros;
import br.com.guarani.rta.validador.TesteFile;
import br.com.guarani.rta.validador.UtilsValidator;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

@Component
@Path("/relatorios")
public class RelatorioValidadorResource {
	
	private String folderUser = null;
	
	@Autowired
	private CampoDAO campodao;
	
	@Autowired
	private TesteFile relatorios;
	 
	@GET
	@Consumes("text/html")
	@Produces(MediaType.APPLICATION_JSON)
	public String listErros(@Context ServletContext ctx) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();

	    File folder = new File(ctx.getRealPath("/arquivos/" + folderUser));
        	 
		List<TabelasErros> tabErros = new ArrayList<TabelasErros>();
		
		tabErros =	relatorios.listaErros(folder);
		
		folderUser = null;
				
		return mapper.writeValueAsString(tabErros);
	} 
	   
		@ResponseStatus(value=HttpStatus.OK)
		@POST
		@Produces(MediaType.MULTIPART_FORM_DATA)
		@Consumes(MediaType.MULTIPART_FORM_DATA)
	    @Path("/upload")
	    public void listValid(@Context HttpServletRequest req, @Context ServletContext ct) throws IOException, FileUploadException {
					
			if(folderUser == null){		
				folderUser = RandomStringUtils.randomAlphabetic(10);			
			    File folder = new File(ct.getRealPath("/arquivos/" + folderUser));
			    folder.mkdir();
			}
			
			
			ServletFileUpload fileUpload = new ServletFileUpload();
			FileItemIterator iterator = fileUpload.getItemIterator(req);
			
			while (iterator.hasNext()) {	
				
				
                   FileItemStream item = iterator.next();
                   
                   String nameItemExtension = item.getName();
                   
					if(isZip(nameItemExtension)){
						 
						zipToFiles(item);
						
					}else{
		              
						streamToFile(req, item, "arquivos/" + folderUser);   				   

					}
       		 }
	    } 

 
	
		
		private boolean isZip(String name) {
			String extension = name.substring(name.lastIndexOf(".") + 1, name.length());
				if(extension.equals("rar")){
					return true;
				}
			return false;
		}
		
		
		
		
		
		
		private void zipToFiles(FileItemStream item){
		    try {
				
		    	InputStream inputStream = item.openStream();
		    	
		    	File file = new File("zip");
		    	
		    	try (FileOutputStream out = new FileOutputStream(file)) {
			        IOUtils.copy(inputStream, out);
			        inputStream.close();
			    }
		    	
		    	FileInputStream input = new FileInputStream(file);

		        ZipInputStream zipStream = new ZipInputStream(input);
		        
		        ZipEntry entry;
		       
		        while ((entry = zipStream.getNextEntry()) != null)
		        {
		            while (zipStream.available() > 0)
		            	zipStream.read();
		            	System.out.println(entry.toString());
		        }
		        				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		

		private void unZip(String zip, String folderStract){
	         try {
				ZipFile zipFile = new ZipFile(zip);
				zipFile.extractAll(folderStract);
			} catch (ZipException e) {
				e.printStackTrace();
			}
		 }
	
			
		private File streamToFile (HttpServletRequest req, FileItemStream item, String baseFolder) throws IOException {            
			
			String realPath  = req.getSession().getServletContext().getRealPath("/" + baseFolder);
		    File file = new File(realPath + "/" + item.getName());
		   
		    InputStream inputStream = item.openStream();
		    

			    try (FileOutputStream out = new FileOutputStream(file)) {
			        IOUtils.copy(inputStream, out);
			        inputStream.close();
			    }
			    
		    return file;            
		}
		
		
		



}
