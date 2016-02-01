package br.com.guarani.rta.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class CabecalhoErros {

	
	private Integer total_erros;
	private Integer telefone_mask_erros;
	private Integer date_mask_erros;
	private Integer null_erros;
	private Integer formato_embalagem;
	private Integer codigo_virgula;
	private Integer tipo_prod_cli;
	private Integer prazo_min;
	private Integer politica_precos;
	private Integer tipo_comissao;
	private Integer limite_credito;
	private Integer tipo_pessoa;
	private Integer estado_virgulo;
	private Integer sina;
	private Integer frete;
	private Integer cep;
	private Integer formato_cpf;
	private Integer formato_cnpj;
	private Integer dados_acima;
	private Integer dados_abaixo;
	
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
	
	
	public Integer getCodigo_virgula() {
		return codigo_virgula;
	}
	
	public void setCodigo_virgula(Integer codigo_virgula) {
		this.codigo_virgula = codigo_virgula;
	}
	
	
	public Integer getTipo_prod_cli() {
		return tipo_prod_cli;
	}
	
	public void setTipo_prod_cli(Integer tipo_prod_cli) {
		this.tipo_prod_cli = tipo_prod_cli;
	}
	
	public Integer getPrazo_min() {
		return prazo_min;
	}
	
	public void setPrazo_min(Integer prazo_min) {
		this.prazo_min = prazo_min;
	}
	
	public void setPolitica_precos(Integer politica_precos) {
		this.politica_precos = politica_precos;
	} 
	
	public void setTipo_comissao(Integer tipo_comissao) {
		this.tipo_comissao = tipo_comissao;
	}
	
	public void setLimite_credito(Integer limite_credito) {
		this.limite_credito = limite_credito;
	}
	

	public void setTipo_pessoa(Integer tipo_pessoa) {
		this.tipo_pessoa = tipo_pessoa;
	}
	
	public void setEstado_virgulo(Integer estado_virgulo) {
		this.estado_virgulo = estado_virgulo;
	}
	
	public void setSina(Integer sina) {
		this.sina = sina;
	}
	
	public void setFrete(Integer frete) {
		this.frete = frete;
	}
	
	public void setCep(Integer cep) {
		this.cep = cep;
	}
	
	public void setFormato_cpf(Integer formato_cpf) {
		this.formato_cpf = formato_cpf;
	}
	
	public void setFormato_cnpj(Integer formato_cnpj) {
		this.formato_cnpj = formato_cnpj;
	}
	
	public void setDados_acima(Integer dados_acima) {
		this.dados_acima = dados_acima;
	}

	
	public Integer getPolitica_precos() {
		return politica_precos;
	}


	public Integer getTipo_comissao() {
		return tipo_comissao;
	}


	public Integer getLimite_credito() {
		return limite_credito;
	}


	public Integer getTipo_pessoa() {
		return tipo_pessoa;
	}


	public Integer getEstado_virgulo() {
		return estado_virgulo;
	}


	public Integer getSina() {
		return sina;
	}


	public Integer getFrete() {
		return frete;
	}


	public Integer getCep() {
		return cep;
	}


	public Integer getFormato_cpf() {
		return formato_cpf;
	}


	public Integer getFormato_cnpj() {
		return formato_cnpj;
	}


	public Integer getDados_acima() {
		return dados_acima;
	}

	
	public Integer getDados_abaixo() {
		return dados_abaixo;
	}
	
	public void setDados_abaixo(Integer dados_abaixo) {
		this.dados_abaixo = dados_abaixo;
	}
	
	
	
	

	@Override
	public String toString() {
		return "CabecalhoErros [total_erros=" + total_erros + ", telefone_mask_erros=" + telefone_mask_erros
				+ ", date_mask_erros=" + date_mask_erros + ", list=" + list + "]";
	}

}
