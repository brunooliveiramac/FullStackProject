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

@Repository
public class ProjetoDAOimpl implements ProjetoDAO {

	private Connection con;
	private Projeto p;
	 
	
	@Autowired
	public ProjetoDAOimpl(DataSource ds, Projeto p) {
		p = new Projeto();
		try {
			this.con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Projeto> allProjects() {
		p = new Projeto();
		ArrayList<Projeto> lista = null;
		try {
			con = DBConnection.getConnection();
			PreparedStatement ppStm = con.prepareStatement(ProjetoSqlConstants.RETURN_LIST_OF_ALL_PROJECTS);
			ResultSet resultado = ppStm.executeQuery();
			lista = new ArrayList<Projeto>();
			while (resultado.next()) {
				p = new Projeto();
				p.setGua_pro_id(resultado.getInt("gua_pro_id"));
				p.setGua_pro_nome(resultado.getString("gua_pro_nome"));
				lista.add(p);
			}
			resultado.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public Projeto porId(int id) {
			p = new Projeto();
		try {
			con = DBConnection.getConnection();
			PreparedStatement ppStm = con.prepareStatement(ProjetoSqlConstants.RETURN_PROJECT_BY_ID);
			ppStm.setInt(1, id);
			ResultSet resultado = ppStm.executeQuery();
			while (resultado.next()) {
				p.setGua_pro_id(resultado.getInt("gua_pro_id"));
				p.setGua_pro_nome(resultado.getString("gua_pro_nome"));
			}
			System.out.println(p);
			con.close();
			resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}

	@Override
	public void insert(Projeto projeto) {
		try {
			con = DBConnection.getConnection();
			PreparedStatement ppStm = con.prepareStatement(ProjetoSqlConstants.INSERT_PROJECT);
			ppStm.setString(1, projeto.getGua_pro_nome());
			ppStm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Projeto projeto) {
		try {
			con = DBConnection.getConnection();
			PreparedStatement ppStm = con.prepareStatement(ProjetoSqlConstants.UPDATE_PROJECT);
			ppStm.setString(1, projeto.getGua_pro_nome());
			ppStm.setLong(2, projeto.getGua_pro_id());
			ppStm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletaProjeto(int id) {
		try {
			con = DBConnection.getConnection();
			PreparedStatement ppStm = con.prepareStatement(ProjetoSqlConstants.DELETE_PROJECT);
			ppStm.setInt(1, id);
			ppStm.executeUpdate();
			ppStm.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Projeto> projetosUsuario(String username) {
		ArrayList<Projeto> lista = null;
		p = new Projeto();

		try {
			con = DBConnection.getConnection();
			PreparedStatement ppStm = con.prepareStatement(ProjetoSqlConstants.RETURN_LIST_USER_PROJECTS);
			ppStm.setString(1, username);
			ResultSet resultado = ppStm.executeQuery();
			lista = new ArrayList<Projeto>();
			while (resultado.next()) {
				p = new Projeto();
				p.setGua_pro_id(resultado.getInt("p.gua_pro_id"));
				p.setGua_pro_nome(resultado.getString("p.gua_pro_nome"));
				lista.add(p);
			}
			resultado.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	
}
