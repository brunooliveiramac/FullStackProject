package br.com.guarani.rta.dao.layout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.guarani.rta.commons.CampoSqlConstants;
import br.com.guarani.rta.commons.TabelaSqlConstants;
import br.com.guarani.rta.connection.DBConnection;
import br.com.guarani.rta.dao.JpaDao;
import br.com.guarani.rta.entidade.Campo;
import br.com.guarani.rta.entidade.Layout;
import br.com.guarani.rta.entidade.Projeto;
import br.com.guarani.rta.entidade.Tabela;

@Repository
public class LayoutDAOimpl extends JpaDao<Layout, Long> implements LayoutDAO {
	
	public LayoutDAOimpl() {
		super(Layout.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Layout> layoutByProject(Integer idProjeto){
		Query query = this.getEntityManager().createQuery("SELECT l from Layout l WHERE l.projeto.gua_pro_id =:idProjeto");
		return query.setParameter("idProjeto", idProjeto)
				.getResultList();
			 
	}  

}
