package br.com.guarani.rta.dao.tabela;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.guarani.rta.commons.CampoSqlConstants;
import br.com.guarani.rta.commons.TabelaSqlConstants;
import br.com.guarani.rta.connection.DBConnection;
import br.com.guarani.rta.dao.JpaDao;
import br.com.guarani.rta.entidade.Campo;
import br.com.guarani.rta.entidade.Layout;
import br.com.guarani.rta.entidade.Projeto;
import br.com.guarani.rta.entidade.Tabela;

@Repository
public class TabelaDAOimpl extends JpaDao<Tabela, Long> implements TabelaDAO {
	
	public TabelaDAOimpl() {
		super(Tabela.class); 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tabela> getTableByVersion(int idprojeto) {
		 Query consult = this.getEntityManager().createQuery("SELECT distinct  t FROM Tabela t JOIN FETCH t.projeto p JOIN FETCH t.children c WHERE p.gua_pro_id = :idprojeto");
		 return consult
				 .setParameter("idprojeto", idprojeto)
				 .getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override  
	@Transactional
	public List<Tabela> getTableByVersion(int versao, int idprojeto) {
		 Query consult = this.getEntityManager().createQuery("SELECT distinct  t FROM Tabela t JOIN FETCH t.layout as versao JOIN FETCH t.projeto p JOIN FETCH t.children c WHERE versao.gua_lay_num_ver <= :versao AND c.layout.gua_lay_num_ver <= :versao AND p.gua_pro_id = :idprojeto");
		 return consult
				 .setParameter("versao", versao)
				 .setParameter("idprojeto", idprojeto)
				 .getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override   
	@Transactional
	public List<Tabela> getTableByVersionIdTab(int versao, int id) {
		Query consult = this.getEntityManager().createQuery("SELECT distinct t FROM Tabela t  JOIN FETCH t.layout as versao JOIN FETCH t.children c WHERE versao.gua_lay_num_ver <= :versao AND c.layout.gua_lay_num_ver <= :versao AND t.id = :id");
		consult.setParameter("versao", versao);
		consult.setParameter("id", id);
		List<Tabela> tabela = (List<Tabela>) consult.getResultList();
		return tabela;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tabela> getTablesByVersionIdBetween(int versaoOrigem, int versaoDestino, int idprojeto) {
		 Query consult = this.getEntityManager().createQuery("SELECT distinct  t FROM Tabela t JOIN FETCH t.projeto p JOIN FETCH t.layout as versao JOIN FETCH t.children c WHERE versao.gua_lay_num_ver BETWEEN :versaoOrigem AND :versaoDestino AND c.layout.gua_lay_num_ver BETWEEN :versaoOrigem AND :versaoDestino AND p.gua_pro_id = :idprojeto ");
		 return consult
				 .setParameter("versaoOrigem", versaoOrigem)
				 .setParameter("versaoDestino", versaoDestino)
				 .setParameter("idprojeto", idprojeto)
				 .getResultList();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Tabela> getTablesWithChange(int versaoOrigem, int versaoDestino, int idprojeto) {
		
		Query consult = this.getEntityManager().createQuery("SELECT distinct  t FROM Tabela t JOIN FETCH t.projeto p JOIN FETCH t.children c WHERE t.layout.gua_lay_num_ver <= :versaoDestino AND c.layout.gua_lay_num_ver NOT BETWEEN :versaoOrigem AND :versaoDestino AND c.layout.gua_lay_num_ver <= :versaoDestino  AND p.gua_pro_id = :idprojeto ");
		 return consult
				 .setParameter("versaoOrigem", versaoOrigem)
				 .setParameter("versaoDestino", versaoDestino)
				 .setParameter("idprojeto", idprojeto) 
				 .getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTablesName() {
		Query consult = this.getEntityManager().createQuery("SELECT t.nomef FROM Tabela t");
		 return consult.getResultList();
	}    

}
