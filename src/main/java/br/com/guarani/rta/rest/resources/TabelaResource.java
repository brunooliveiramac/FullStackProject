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
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.guarani.rta.dao.tabela.TabelaDAO;
import br.com.guarani.rta.entidade.Tabela;
import br.com.guarani.rta.entidade.Tabelas;


@Component
@Path("/tabelas")
public class TabelaResource
{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TabelaDAO tabelaDao;

	@Autowired
	private ObjectMapper mapper;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@JsonIgnore
	public String list() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		List<Tabela> tabelas = this.tabelaDao.findAll();
		return mapper.writeValueAsString(tabelas) ; 
	}
	
	@Path("/{idprojeto}") 
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String listTableProjeto(@PathParam("idprojeto") int idprojeto) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.FAIL_ON_EMPTY_BEANS,false); 

		List<Tabela> tabelas = this.tabelaDao.getTableByVersion(idprojeto);
		return mapper.writeValueAsString(tabelas) ; 
	}
	
	@Path("/{versao}/{idprojeto}") 
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String listTableByVersion(@PathParam("versao") int versao, @PathParam("idprojeto") int idprojeto) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		List<Tabela> tabelas = this.tabelaDao.getTableByVersion(versao, idprojeto);
		return mapper.writeValueAsString(tabelas) ; 
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/tabela/{versao}/{id}")
	public String porId(@PathParam("versao") int versao, @PathParam("id") int id) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		List<Tabela> tabelas = this.tabelaDao.getTableByVersionIdTab(versao, id);
		if (tabelas == null) {
			throw new WebApplicationException(404);
		} 	
		return mapper.writeValueAsString(tabelas) ; 
	}
	
	//Alterações
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/tabela/geral/{versaoOrigem}/{versaoDestino}/{idprojeto}")
	public String comparerGeral(@PathParam("versaoOrigem") int versaoOrigem, @PathParam("versaoDestino") int versaoDestino, @PathParam("idprojeto") int idprojeto) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		List<Tabela> tabelas = this.tabelaDao.getTablesByVersionIdBetween(versaoOrigem, versaoDestino, idprojeto);
		if (tabelas == null) {
			throw new WebApplicationException(404);
		} 	 
		return mapper.writeValueAsString(tabelas) ; 
	}
	
	//Origem
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/tabela/geral/origem/{versaoOrigem}/{versaoDestino}/{idprojeto}")
	public String getTablesWithChange(@PathParam("versaoOrigem") int versaoOrigem, @PathParam("versaoDestino") int versaoDestino, @PathParam("idprojeto") int idprojeto) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.FAIL_ON_EMPTY_BEANS,false); 

		List<Tabela> tabelas = this.tabelaDao.getTablesWithChange(versaoOrigem, versaoDestino, idprojeto);
		if (tabelas == null) {
			throw new WebApplicationException(404);
		} 	 
		return mapper.writeValueAsString(tabelas) ; 
	}
 
} 