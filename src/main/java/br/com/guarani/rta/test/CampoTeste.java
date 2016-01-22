package br.com.guarani.rta.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.guarani.rta.dao.campo.CampoDAO;
import br.com.guarani.rta.dao.campo.CampoDAOimpl;
import br.com.guarani.rta.entidade.Campo;
import junit.framework.Assert;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/context.xml" })
public class CampoTeste {
	
	@Autowired
	CampoDAO dao;
	
	@SuppressWarnings("deprecation")
	@Test
	public void campo(){
		List<Campo> list = new CampoDAOimpl().campoByTable(6);
		System.out.println("Campos: "+list.toString());	
		Assert.assertNotNull(list);
	}

}
