package br.com.guarani.rta.dao.campo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.guarani.rta.commons.CampoSqlConstants;
import br.com.guarani.rta.commons.ProjetoSqlConstants;
import br.com.guarani.rta.connection.DBConnection;
import br.com.guarani.rta.dao.Dao;
import br.com.guarani.rta.dao.JpaDao;
import br.com.guarani.rta.entidade.Atributo;
import br.com.guarani.rta.entidade.Campo;
import br.com.guarani.rta.entidade.Layout;
import br.com.guarani.rta.entidade.Projeto;
import br.com.guarani.rta.entidade.TipoAtributo;


public class CampoDAOimpl extends JpaDao<Campo, Long> implements CampoDAO {
	
	public CampoDAOimpl() {
		super(Campo.class);
	}

	@SuppressWarnings("unchecked")
	@Override 
	public List<Campo> campoByTable(int idtabela) {
		Query consult = null; 
		try {
		consult = this.getEntityManager().createQuery("SELECT distinct c FROM Campo c JOIN FETCH c.tabela t LEFT OUTER JOIN c.atributos a WHERE c.tabela.id = :idtabela ORDER BY c.chave ASC");
		} catch (Exception e) {
		e.printStackTrace();
		}
		return consult
				 .setParameter("idtabela", idtabela)
				 .getResultList();
	}
	
	
	@Override
	public List<Campo> byTable(int idprojeto) {
		ArrayList<Campo> lista = null;
		Connection con = null;
		try {
		    con = DBConnection.getConnection();
			PreparedStatement ppStm = con.prepareStatement(CampoSqlConstants.RETURN_CAMP_BY_TABLE);
			ppStm.setInt(1, idprojeto);

			ResultSet resultado = ppStm.executeQuery();
			lista = new ArrayList<Campo>();
			while (resultado.next()) {
				Campo c = new Campo();
				Atributo a = new Atributo();
				
				a.setDescricao(resultado.getString("a.descricao"));
				a.setTipo(TipoAtributo.valueOf(resultado.getString("a.tipo")));
		
				c.setAtivo(resultado.getString("c.ativo"));
				c.setCprimaria(resultado.getString("c.cprimaria"));
				c.setDeci(resultado.getString("c.deci"));
				c.setNulo(resultado.getString("c.nulo"));
				c.setNulo(resultado.getString("c.tam"));
				c.setAtributos(a);
				
				lista.add(c);
			}
			resultado.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Campo> porNomeTabela(String name) {
		Query consult = this.getEntityManager().createQuery("SELECT distinct c FROM Campo c JOIN FETCH c.tabela t LEFT OUTER JOIN c.atributos a WHERE c.tabela.nomef = :name ORDER BY c.chave ASC");
		return consult
				 .setParameter("name", name)
				 .getResultList();
	}
	
	
	
}
