package br.com.guarani.rta.test;

import java.util.ArrayList;
import java.util.List;
 
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.guarani.rta.dao.projeto.ProjetoDAO;
import br.com.guarani.rta.dao.projeto.ProjetoDAOimpl;
import br.com.guarani.rta.entidade.Projeto;

public class ProjetoTeste {
	
	@Autowired
	public ProjetoDAO dao;
	
	@Test
	public void projetoLista(){
		List<Projeto> list = dao.allProjects();
		System.out.println(list);
	}
}
