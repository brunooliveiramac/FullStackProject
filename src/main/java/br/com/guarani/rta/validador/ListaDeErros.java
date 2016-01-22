package br.com.guarani.rta.validador;

import java.util.ArrayList;
import java.util.List;

import br.com.guarani.rta.entidade.TabelasErros;

public class ListaDeErros {
	
	
	public ListaDeErros() {
		// TODO Auto-generated constructor stub
	}
	
	public List<TabelasErros> erros = new ArrayList<>();
	
	public List<TabelasErros> getErros() {
		return erros;
	}
	
	public void setErros(List<TabelasErros> erros) {
		this.erros = erros;
	}

	@Override
	public String toString() {
		return "ListaDeErros [erros=" + erros + "]";
	}
	
	
	
}
