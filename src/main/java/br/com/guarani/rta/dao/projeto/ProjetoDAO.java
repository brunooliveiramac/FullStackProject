package br.com.guarani.rta.dao.projeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.guarani.rta.commons.ProjetoSqlConstants;
import br.com.guarani.rta.connection.DBConnection;
import br.com.guarani.rta.entidade.Projeto;

@Component
public interface ProjetoDAO {
	

	public List<Projeto> allProjects();

	public Projeto porId(int id);
	
	public void insert(Projeto projeto);

	public void update(Projeto projeto);
	
	public void deletaProjeto(int id);
	
	public List<Projeto> projetosUsuario(String username);


}
