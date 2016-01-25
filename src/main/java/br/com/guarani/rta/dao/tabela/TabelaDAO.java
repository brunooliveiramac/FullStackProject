package br.com.guarani.rta.dao.tabela;

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
import br.com.guarani.rta.dao.Dao;
import br.com.guarani.rta.entidade.Projeto;
import br.com.guarani.rta.entidade.Tabela;




@Component
public interface TabelaDAO extends Dao<Tabela, Long> {
	
	public List<Tabela> getTableByVersion(int idprojeto);

	public List<Tabela> getTableByVersion(int versao, int idprojeto);

	public List<Tabela> getTableByVersionIdTab(int versao, int id);

	public List<Tabela> getTablesByVersionIdBetween(int versaoOrigem, int versaoDestino, int idprojeto);
	
	public List<Tabela> getTablesWithChange(int versaoOrigem, int versaoDestino, int idprojeto);
	
	public List<String> getTablesName();

}
