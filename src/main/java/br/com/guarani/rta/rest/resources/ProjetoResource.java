package br.com.guarani.rta.rest.resources;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import br.com.guarani.rta.dao.projeto.ProjetoDAO;
import br.com.guarani.rta.entidade.Projeto;
import br.com.guarani.rta.entidade.Projetos;


@Component
@Path("/projetos")
public class ProjetoResource {
	
	@Autowired
	private ProjetoDAO projetoDao;

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Projetos getProjetos() throws SQLException {
		List<Projeto> projeto = projetoDao.allProjects();
		Projetos projetos = new Projetos();
		projetos.setProjetos(projeto);
		return projetos;
	} 

	 
	
		/*when Angular is expecting JSON representing an array, like:
		[ {"some": "object", "with": "properties"}, {"another": "object", "with": "stuff"} ]*/
	
	
	@Path("/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Projeto> getProjetosUsuario(@PathParam("username") String username) throws SQLException {
		List<Projeto> projetos = projetoDao.projetosUsuario(username);
		return projetos;
	}
 

	@Path("/{username}/{idprojeto}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Projeto listaProjetoPorId(@PathParam("idprojeto") int projeto) {
		Projeto pro = projetoDao.porId(projeto);
		return pro;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvaProjeto(Projeto projeto) {
		URI uri = null;
		try {
			projetoDao.insert(projeto);
			uri = URI.create("/projeto/" + projeto.getGua_pro_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.created(uri).build();
	}

	@Path("/{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeProjeto(@PathParam("id") int id) {
		projetoDao.deletaProjeto(id);
		return Response.ok().build();
	}

	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editaProjeto(Projeto projeto, @PathParam("id") long id) {
		projetoDao.update(projeto);
		System.out.println(projeto.toString());
		return Response.ok().build();
	}
	
	
	
	private boolean isAdmin(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
			return false;
		}
		
		UserDetails userDetails = (UserDetails) principal;

		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			if (authority.toString().equals("admin")) {
				return true;
			}
		}

		return false;
	}
}
