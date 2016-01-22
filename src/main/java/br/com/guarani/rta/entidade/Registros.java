package br.com.guarani.rta.entidade;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Registros implements Serializable{

	private static final long serialVersionUID = 1L;
	 
	private int id;
	private String campo_nome;
	private String conteudo_campo;
	private String valor_esperado;
	private String comentario_erro;
	
	public Registros() {
	}

	
	public Registros(int id, String campo_nome, String conteudo, String valor_esperado, String comentario_erro) {
		this.id = id;
		this.conteudo_campo = conteudo;
		this.campo_nome = campo_nome;
		this.valor_esperado = valor_esperado;
		this.comentario_erro = comentario_erro;
	}
	
	
	
	public Registros(String campo_nome, String conteudo_campo, String valor_esperado, String comentario_erro) {
		this.campo_nome = campo_nome;
		this.conteudo_campo = conteudo_campo;
		this.valor_esperado = valor_esperado;
		this.comentario_erro = comentario_erro;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCampo_nome() {
		return campo_nome;
	}
	public void setCampo_nome(String campo_nome) {
		this.campo_nome = campo_nome;
	}

	public String getComentario_erro() {
		return comentario_erro;
	}
	public void setComentario_erro(String comentario_erro) {
		this.comentario_erro = comentario_erro;
	}
	
		
	public String getConteudo_campo() {
		return conteudo_campo;
	}
	
	public void setConteudo_campo(String conteudo_campo) {
		this.conteudo_campo = conteudo_campo;
	}
	
	
	public String getValor_esperado() {
		return valor_esperado;
	}
	
	public void setValor_esperado(String valor_esperado) {
		this.valor_esperado = valor_esperado;
	}
	
	
	@Override
	public String toString() {
		return  campo_nome + comentario_erro; 
	}
}
