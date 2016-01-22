package br.com.guarani.rta.dao.campo;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.guarani.rta.dao.Dao;
import br.com.guarani.rta.entidade.Campo;

@Component
public interface CampoDAO extends Dao<Campo, Long> {

	
	public List<Campo> campoByTable(int idtabela);

	public List<Campo> byTable(int idprojeto);

	public List<Campo> porNomeTabela(String name);


}
