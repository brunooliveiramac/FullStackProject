package br.com.guarani.rta.rest.resources;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;



import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.guarani.rta.dao.layout.LayoutDAO;
import br.com.guarani.rta.dao.tabela.TabelaDAO;
import br.com.guarani.rta.entidade.Layout;
import br.com.guarani.rta.entidade.Tabela;
import br.com.guarani.rta.entidade.Tabelas;


@Component
@Path("/layouts")
public class LayoutResource
{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LayoutDAO layoutDao;

	@Autowired
	private ObjectMapper mapper;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String list() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		List<Layout> layouts = this.layoutDao.findAll();
		return mapper.writeValueAsString(layouts) ; 
	}
	 
	
	@GET
	@Path("/projeto/{idprojeto}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listByProject(@PathParam("idprojeto") Integer idprojeto) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		List<Layout> layouts = this.layoutDao.layoutByProject(idprojeto);
		return mapper.writeValueAsString(layouts) ; 
	}

} 