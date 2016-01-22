package br.com.guarani.rta.validador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import br.com.guarani.rta.entidade.Campo;
import br.com.guarani.rta.entidade.LinhaErro;
import br.com.guarani.rta.entidade.Registros;
import br.com.guarani.rta.entidade.RelatorioErros;
import br.com.guarani.rta.entidade.TabelasErros;


public class UtilsValidator {
	
	public TabelasErros tabelasErros;
	public LinhaErro linhaErro;
	public Registros registro;
	
	public List<LinhaErro> linhaErros;
	public List<Registros> registros;
	
	public UtilsValidator(){
		tabelasErros = new TabelasErros();
		linhaErro = new LinhaErro();
		registro = new Registros();
		
		linhaErros = new ArrayList<>();
		registros = new ArrayList<>();
		
	}
	
	
	public boolean verifyIsNull(String part, String nulo){
		if(nulo.contains("Não")){
				if(part != null && !part.isEmpty()) {
					return true;
				}else{ 
					System.out.println("Campo Obrigatório! Preencha!");
					return false;
				}	
			}
		 else
			return false;
	}
	
	public  boolean verifyIsString(String part){
		if(part.contains("[a-zA-Z]+")) return true;
		else
		return false;	
	}	
	
	
	public static  boolean isReal(String part){
		try {
			Double.parseDouble(part);
			return true;
		} catch (Exception e) {
			return false;
		}
	}	
	
	public  boolean verificaTamanho(String basename, Campo campo, Integer tamBd, int tamCarga, String part) throws IOException{
		if(tamBd <= tamCarga) {
			return true;
		} 
		else
			
		registro = new Registros(campo.getNomef(), part, "Valor esperado: "+tamBd.toString() , " Tamanho do campo incorreta");
		
		registros.add(registro);
		
		
		
		
		return false;
	}
	
	
	public static  boolean isTelefone(String numeroTelefone, String camponome) {
        if( numeroTelefone.matches(".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}") ||
                numeroTelefone.matches(".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}"))
        	return true;
        else
        
     
        
        return false;
    }
	
	
	public static  boolean isCep(String cep){
		if(cep.matches("\\d{5}-\\d{3}")){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
	public static  boolean isCnpj(String c){
		if(c.matches("^([0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}[/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}[-]?[0-9]{2})$")){
		 	return true;
		}
		else{
			return false;
		}
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
			return false;
		}
	}
	
	
	

	public int sizeListEnter(File file) throws IOException{
	    LineIterator iteratorline = FileUtils.lineIterator(file);
	    int count = 0;
	            while (iteratorline.hasNext()) {
	            		String[] line = iteratorline.nextLine().split("\\|");
			            for (int i = 0; i<line.length; i++) {
			            	count ++;
			            }
			            System.out.println(count);
	            }
		return count;
	}
	
	
	public static void validaAtributos(Campo campo, String part){
		int atributo;
		try {
			if(campo.getAtributos() == null){
			}else{
				atributo =	campo.getAtributos().getId();		
				if(atributo == 1){
					UtilsValidator.isTelefone(part, campo.getNomef());
				}
				if(atributo == 2){
					UtilsValidator.isCnpj(part);
				}
				if(atributo == 3){
					UtilsValidator.isCpf(part);
				}
				if(atributo == 4){
					UtilsValidator.isDate(part);
				}
				if(atributo == 5){
					UtilsValidator.isCep(part);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
	
	public static List<String> checaCaractere(String part, String caractere, Integer tamMax) {
		
		if(part.length() == 0){
			System.out.println("Linha em Branco");
		}
		
		String novalinha = "";
		
	    novalinha = novalinha + part;
	    
	    List<String> campos = new ArrayList<String>();
	    
	    Character lendo;   
	    int j = 0, i = 0;
	    int tam_string = novalinha.length();
	    int start = 0;

	    while(j < tam_string){
	    	lendo = novalinha.charAt(j);
	    	i++;
		        if (caractere.equalsIgnoreCase(lendo.toString())) {
		            campos.add(novalinha.substring(start, j));
		            start = j+1;
		            i = 0;
		        }
	        j++;
	    }
	   
	   return campos;
	}
	

}
