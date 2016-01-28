package br.com.guarani.rta.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CabecalhoErros {
	
	private Integer total_erros;
	private Integer telefone_mask_erros;
	private Integer date_mask_erros;
	private Integer null_erros;
	private Integer formato_embalagem;
	
	private List<TabelaErro> list = new ArrayList<TabelaErro>();
	private List<String> nomes_tabelas_incorretas = new ArrayList<String>();

	
	public CabecalhoErros() {
		list = new ArrayList<TabelaErro>();
	}


	public Integer getTotal_erros() {
		return total_erros;
	}

	public void setTotal_erros(Integer total_erros) {
		this.total_erros = total_erros;
	}

	public Integer getTelefone_mask_erros() {
		return telefone_mask_erros;
	}

	public void setTelefone_mask_erros(Integer telefone_mask_erros) {
		this.telefone_mask_erros = telefone_mask_erros;
	}

	public Integer getDate_mask_erros() {
		return date_mask_erros;
	}

	public void setDate_mask_erros(Integer date_mask_erros) {
		this.date_mask_erros = date_mask_erros;
	}

	public List<TabelaErro> getList() {
		return list;
	}

	public void setList(List<TabelaErro> list) {
		this.list = list;
	}

	
	public Integer getNull_erros() {
		return null_erros;
	}
	
	public void setNull_erros(Integer null_erros) {
		this.null_erros = null_erros;
	}
	
	public List<String> getNomes_tabelas_incorretas() {
		return nomes_tabelas_incorretas;
	}
	
	public void setNomes_tabelas_incorretas(List<String> nomes_tabelas_incorretas) {
		this.nomes_tabelas_incorretas = nomes_tabelas_incorretas;
	}
	
	public Integer getFormato_embalagem() {
		return formato_embalagem;
	}
	
	public void setFormato_embalagem(Integer formato_embalagem) {
		this.formato_embalagem = formato_embalagem;
	}
	
	

	@Override
	public String toString() {
		return "CabecalhoErros [total_erros=" + total_erros + ", telefone_mask_erros=" + telefone_mask_erros
				+ ", date_mask_erros=" + date_mask_erros + ", list=" + list + "]";
	}

}
