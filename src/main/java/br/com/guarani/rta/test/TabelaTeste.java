package br.com.guarani.rta.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.guarani.rta.dao.projeto.ProjetoDAO;
import br.com.guarani.rta.dao.projeto.ProjetoDAOimpl;
import br.com.guarani.rta.dao.tabela.TabelaDAO;
import br.com.guarani.rta.dao.tabela.TabelaDAOimpl;
import br.com.guarani.rta.entidade.Projeto;
import br.com.guarani.rta.entidade.Tabela;
import junit.framework.Assert;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/context.xml" })
public class TabelaTeste {
	
	@Autowired
	private TabelaDAO tabelaDao;

	
	
/*	public void tabelaLista(){
		List<Tabela> list = tabelaDao.findAll();
		System.out.println("LISTAGEM: " + list);
	}
	

	@SuppressWarnings("deprecation")
	public void tabelaList(){
		List<Tabela> list = tabelaDao.getTableByVersion(4, 2);
		System.out.println("TABELA: " + list);

	}
	
	public void tabelaListProjeto(){
		List<Tabela> list = tabelaDao.getTableByVersion(1);
		System.out.println("TABELA: " + list);

	}*/
	
	@Test
	public void tabelaListComparer(){
		List<Tabela> list = tabelaDao.getTableByVersion(1);
		System.out.println("TABELA: " + list);
		System.out.println("TABELA: " + list);
		System.out.println("TABELA: " + list);
		System.out.println("TABELA: " + list);
		System.out.println("TABELA: " + list);

	}
	
	
	
}
 