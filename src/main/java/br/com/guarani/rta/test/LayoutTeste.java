package br.com.guarani.rta.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.guarani.rta.dao.layout.LayoutDAO;
import br.com.guarani.rta.entidade.Layout;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/context.xml" })
public class LayoutTeste {
	
	@Autowired
	private LayoutDAO layoutDao;
	
	@Test
	public void testaLista(){
		List<Layout> list = layoutDao.layoutByProject(1);
		System.out.println("Lista:" + list.toString());
	}

}
