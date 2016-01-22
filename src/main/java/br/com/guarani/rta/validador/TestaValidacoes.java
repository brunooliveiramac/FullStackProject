package br.com.guarani.rta.validador;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestaValidacoes {
	
	
	public static boolean isTelefone(String numeroTelefone) {
        if( numeroTelefone.matches(".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}") ||
                numeroTelefone.matches(".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}"))
        	return true;
        else
        	System.out.println("Invalido!");
        	return false;
    }
	
	public static boolean isCep(String cep){
		if(cep.matches("\\d{5}-\\d{3}")){
			return true;
		}
		else{
			System.out.println("inválido");
			return false;
		}
		
	}
	
	public static boolean isCnpj(String c){
		if(c.matches("^([0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}[/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}[-]?[0-9]{2})$")){
		 	return true;
		}
		else{
			System.out.println("inválido");
			return false;
		}
	}
	

	private static boolean isZip(String name) {
		String extension = name.substring(name.lastIndexOf(".") + 1, name.length());
			if(extension.equals("zip")){
				System.out.println("É ZIP!");
				return true;
			}
		return false;
	}
	/*
	 * This will take care of valid formats and valid dates. It will not validate the correct days of the month i.e. leap year.
	 */
	
	public static boolean isDate(String date){
		if(date.matches("^((19|20)\\d\\d)/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])$")){
			return true;
		}else{
			System.out.println("Invalido");
			return false;
		}
	}
	
	
	public static boolean isCpf(String c){
		if(c.matches("^([0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}[/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}[-]?[0-9]{2})$")){
		 	return true;
		}
		else{
			System.out.println("inválido");
			return false;
		}
	}
	
	public static  boolean verifyIsNumber(String part){
		try {
			Double.parseDouble(part);
			return true;
		} catch (Exception e) {
			System.out.println("Não é numero!");
			return false;
		}
	}
	
	
	public static  boolean verifyIsNull(String part, String nulo){
		if(nulo.contains("Não")){
				if(part != null && !part.isEmpty()) {
					return true;
				}else{ 
					System.out.println("Campo Obrigatório!");
					return false;
				}	
			}
		 else
			return false;
	}
	
	
	
	public static void main(String[] args) {
	
			isZip("txt.zip");
		
	}
}
