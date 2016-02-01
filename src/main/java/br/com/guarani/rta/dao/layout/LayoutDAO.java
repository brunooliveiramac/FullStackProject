package br.com.guarani.rta.dao.layout;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.guarani.rta.dao.Dao;
import br.com.guarani.rta.entidade.Layout;




@Component
public interface LayoutDAO extends Dao<Layout, Long> {
	
	public List<Layout> layoutByProject(Integer idProjeto);

	

}
