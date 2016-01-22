package br.com.guarani.rta.entidade;

import java.util.List;

public class TabelaErro {
	
	private String nome_tabela;

	private List<LinhaErro> linhas;

	
	
	public TabelaErro() {

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
