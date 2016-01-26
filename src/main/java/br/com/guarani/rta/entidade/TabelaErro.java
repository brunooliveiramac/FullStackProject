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
	
	
	private Integer cont_total_erros;
	private Integer cont_total_mask_telefone;
	
	
	
	private String msg;
	List<String> tabelas_nome_incorreto;
	private String nome_tabela;
	private String tabela_incorrect_name;
	private List<LinhaErro> linhas;
	
	public TabelaErro() {

	}
	
	
	
	
	public TabelaErro(String nome_tabela, String tabela_incorrect_name) {
		this.nome_tabela = nome_tabela;
		this.tabela_incorrect_name = tabela_incorrect_name;
	}




	public TabelaErro(String nome_tabela, String tabela_incorrect_name, List<LinhaErro> linhas) {
		this.nome_tabela = nome_tabela;
		this.tabela_incorrect_name = tabela_incorrect_name;
		this.linhas = linhas;
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
	
	
	public String getTabela_incorrect_name() {
		return tabela_incorrect_name;
	}
	
	public void setTabela_incorrect_name(String tabela_incorrect_name) {
		this.tabela_incorrect_name = tabela_incorrect_name;
	}
	

	public List<String> getTabelas_nome_incorreto() {
		return tabelas_nome_incorreto;
	}
	
	public void setTabelas_nome_incorreto(List<String> tabelas_nome_incorreto) {
		this.tabelas_nome_incorreto = tabelas_nome_incorreto;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Integer getCont_total_erros() {
		return cont_total_erros;
	}
	
	public void setCont_total_erros(Integer cont_total_erros) {
		this.cont_total_erros = cont_total_erros;
	}
	
	
	public Integer getCont_total_mask_telefone() {
		return cont_total_mask_telefone;
	}
	
	public void setCont_total_mask_telefone(Integer cont_total_mask_telefone) {
		this.cont_total_mask_telefone = cont_total_mask_telefone;
	}
	
	
}
