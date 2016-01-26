package br.com.guarani.rta.validador;

import br.com.guarani.rta.entidade.Campo;

public interface Atributos {

		boolean validaAtributo(Campo campo, String dadosEntrada);
		
		void setProximo(Atributos atributos);
}
