package br.com.guarani.rta.validador;

import java.util.ArrayList;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import br.com.guarani.rta.entidade.Registros;


public class TestaValidacoes {

	
	
	public static boolean SN(String campo){
		String regex = "^(\\s*[NS]\\s*)$";
		if(campo.matches(regex)){
			return true;
		}else{
			System.out.println("Inválido");
			return false;
		}
	}
	
	private static boolean isPoliticaPrecos(String dados){
		String regex = "^\\s*([012])\\s*$";
		if(dados.matches(regex)){
			return true;
		}else{
			System.out.println("Inválido");

			return false;
		}
	}
	
	
	private static boolean isTipoComissao(String dados){
		String regex = "^\\s*([NSM])\\s*$";
		if(dados.matches(regex)){
			return true;
		}else{
			System.out.println("Inválido");

			return false;
		}
	}
	
	
	private static boolean isTrataLimitCred(String dados) {
		String regex = "^\\s*([0-8])\\s*$";
		if(dados.matches(regex)){
			return true;
		}else{
			System.out.println("Inválido");

			return false;
		}
	}
	
	
	private static boolean isEstadoSeparadoPorVirgula(String uf) {
		String regex = "^\\s*(((([a-zA-Z]){2})([;]){0,1})*)\\s*$";
		if (uf.matches(regex)) {
			return true;
		}else{
			System.out.println("Inválido");

	    	return false;
		}
	}
	
	
	public static boolean isFrete(String frete){
		String regex = "^(\\s*[CFS]\\s*)$";
		if(frete.matches(regex)){
			return true;
		}
		else
			System.out.println("Inválido");
			return false;		
	}
	
	
	public static boolean isEmbalagem(String embalagem){
		String regex = "^\\s*(([^\\s]{1,6})([;])([0-9]{1,3})([;])([0-9]{1,3})([;])([0-9]{1,3})([;])([@])\\s*)+$";
		/*  D,Q,QM,QMI,@
		  	D = Descrição (máximo 6 caracteres);
			Q = Quantidade;
			QM = Quantidade múltipla;
			QMI = Quantidade mínima;
			@ = Separador de embalagem
		 */    
		if(embalagem.matches(regex)){
			return true; 
		} 
		else
			System.out.println("Invalido");
		return false;
} 
	
	public static boolean isCpf(String cpf){
		if(cpf.matches("^\\s*([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}[-]?[0-9]{2})\\s*$")){
		 	return true;
		}
		else{
			System.out.println("Invalido");

			return false;
		}
	}
	
	public static   boolean isTelefone(String numeroTelefone) {
		if(numeroTelefone.equals(null) || numeroTelefone.isEmpty()){
			return true;
		}
        if(numeroTelefone.matches("\\s*(.((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4})*\\s*") ||
                numeroTelefone.matches("\\s*(.((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4})*\\s*"))
        	return true;
        else
        	
        	System.out.println("Invalido");
        return false;
    }
	
	public static boolean isCodigoPorVirgula(String codigo){
		String regex = "(([^~,]*)[;]{0,1})*";
		String lastIndex = null;
		lastIndex = codigo.substring(codigo.length() - 1); 

		System.out.println(lastIndex);

		if(lastIndex.equals(";")){
			System.out.println("Virgula");
			return false;
		}	
		if(codigo.matches(regex)){
			return true;
		}
		else{
			System.out.println("Incorreto");
			return false;
		}
	}
 
	
	  
	 
	public static void main(String[] args) {
		
		isCodigoPorVirgula("21112;12312asd;");
	}


}

