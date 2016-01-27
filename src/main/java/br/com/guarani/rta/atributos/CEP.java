package br.com.guarani.rta.atributos;

import br.com.guarani.rta.entidade.Campo;
import br.com.guarani.rta.validador.Atributos;
 
public class CEP implements Atributos{

	private Atributos proximo;

	@Override
	public boolean validaAtributo(Campo campo, String dadosEntrada) {
		if(campo.getAtributos().getId() == 1){
				if(dadosEntrada.matches("\\d{5}-\\d{3}")){
					return true;
				}else{
					return false;
				}
	}else{
		return proximo.validaAtributo(campo, dadosEntrada);
	  }
	}
	
	@Override
	public void setProximo(Atributos proximo) {
		this.proximo = proximo;
	}

}
