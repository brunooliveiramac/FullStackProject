package br.com.guarani.rta.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TabelaErro implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome_tabela;

	private List<LinhaErro> linhas;

	public TabelaErro() {

	}
	
	
	
	public TabelaErro(String nome_tabela) {
		this.nome_tabela = nome_tabela;
		this.linhas = new ArrayList<LinhaErro>();
	}



	public TabelaErro(String nome_tabela, List<LinhaErro> linhas) {
	this.nome_tabela = nome_tabela;
	this.linhas = linhas;
	
	}



	public String getNome_tabela() {
		return nome_tabela;
	}

	public void setNome_tabela(String nome_tabela) {
		this.nome_tabela = nome_tabela;
	}

	public List<LinhaErro> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<LinhaErro> linhas) {
		this.linhas = linhas;
	}
	
	
	

	
	
}
