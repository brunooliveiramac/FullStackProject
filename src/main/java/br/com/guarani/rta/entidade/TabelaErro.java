package br.com.guarani.rta.entidade;

import java.util.List;

public class TabelaErro {
	
	private String nome;
	private String conteudo;
	private String comentario;

	private List<LinhaErro> children;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public List<LinhaErro> getChildren() {
		return children;
	}

	public void setChildren(List<LinhaErro> children) {
		this.children = children;
	}

	
	
}
