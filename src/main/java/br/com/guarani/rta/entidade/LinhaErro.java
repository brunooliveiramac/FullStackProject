package br.com.guarani.rta.entidade;

import java.util.List;

public class LinhaErro {
	
	private Integer num_linha;

	private List<Registros> erros;

	
	
	
	public LinhaErro(Integer num_linha, List<Registros> erros) {
		this.num_linha = num_linha;
		this.erros = erros;
	}

	public LinhaErro() {
		// TODO Auto-generated constructor stub
	}

	public Integer getNum_linha() {
		return num_linha;
	}

	public void setNum_linha(Integer num_linha) {
		this.num_linha = num_linha;
	}

	public List<Registros> getErros() {
		return erros;
	}

	public void setErros(List<Registros> erros) {
		this.erros = erros;
	}
	
	
	

}
