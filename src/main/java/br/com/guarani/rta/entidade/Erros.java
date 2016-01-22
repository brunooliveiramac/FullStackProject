package br.com.guarani.rta.entidade;

public class Erros {
	
	
	private String nome;
	private String conteudo;
	private String comentario;
	
	public Erros() {
		// TODO Auto-generated constructor stub
	}
	
	public Erros(String nome, String conteudo, String comentario) {
		super();
		this.nome = nome;
		this.conteudo = conteudo;
		this.comentario = comentario;
	}
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
	
	
	

}
