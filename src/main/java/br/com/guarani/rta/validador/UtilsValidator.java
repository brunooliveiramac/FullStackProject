package br.com.guarani.rta.validador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import br.com.guarani.rta.entidade.Campo;
import br.com.guarani.rta.entidade.LinhaErro;
import br.com.guarani.rta.entidade.Registros;
import br.com.guarani.rta.entidade.RelatorioErros;
import br.com.guarani.rta.entidade.TabelasErros;


/**
 * @author Bruno - PC
 * I work with static variable because they are a good choice for utils class. I have only one instance of my 
 * variables and methods, so I use less memory of my server.
 *
 */
 
public class UtilsValidator{
	
	public static int telefone_mask = 0;
	public static int data_mask = 0;
	public static int null_erros = 0;
	public static int formato_embalagem = 0;
	
	
	
	public static Registros registro;	
	public static List<Registros> registros;
	
	public UtilsValidator(){
		registro = new Registros();
		registros = new ArrayList<>();
	}
	
	
	public boolean verifyIsNull(Campo campo, String part, String nulo){
		if(nulo.contains("Não")){
				if(part != null && !part.isEmpty()) {
					return true;
				}else{ 
					registro = new Registros(campo.getNomef(), " Nulo.", "Não Nulo.", " Campo não pode ser nulo.");
				    registros.add(registro);
				    null_erros ++;
				 return false;
				}	
			}
		 else
			return false;
	}
	
	public  boolean verifyIsString(String part){
		if(part.contains("\\s*[a-zA-Z]+")) return true;
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
	
	public  boolean verificaTamanho(String basename, Campo campo, Integer tamBd, Integer tamCarga, String part) throws IOException{
		if(part.isEmpty() || part.equals(null) || tamCarga == 0){
			return true;
		}
		if(tamBd >= tamCarga) {
			return true;
		}  
		else
			registro = new Registros(campo.getNomef(), part, " Tam Max:"+tamBd.toString() , " Tamanho do campo incorreto.");
		    registros.add(registro);	
		    return false;
	}
	
	public static   boolean isTelefone(String numeroTelefone, String camponome) {
		if(numeroTelefone.equals(null) || numeroTelefone.isEmpty()){
			return true;
		}
		 if(numeroTelefone.matches("\\s*(.((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4})*\\s*") ||
	                numeroTelefone.matches("\\s*(.((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4})*\\s*"))
	        	return true;
        else
        	registro = new Registros(camponome, numeroTelefone, " (XX) XXXX-XXXX / (XX) XXXXX-XXXX", " Formato Telefone inválido");
        	registros.add(registro);
        	telefone_mask ++;
        return false;
    }
	
	
	public static  boolean isCep(String cep, String campo){
		if(cep.matches("^\\s*(\\d{5}-\\d{3})\\s*$")){
			return true;
		}
		else{
			registro = new Registros(campo, cep, " XX.XXX-XXX", " Formato CEP inválido");
        	registros.add(registro);
			return false;
		}
	}
	
	
	
	public static  boolean isCnpj(String cnpj, String campo){
		if(cnpj.matches("^\\s*([0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}[/]?[0-9]{4}[-]?[0-9]{2})\\s*$")){
		 	return true;
		} 
		else{
			registro = new Registros(campo, cnpj, " XX.XXX.XXX/YYYY-ZZ", " Formato CNPJ inválido");
        	registros.add(registro);
			return false;
		}
	}
	
	
	public static boolean isDate(String date, String campo){
		if(date.matches("^((19|20)\\d\\d)/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])$")){
			return true;
		}else{
			registro = new Registros(campo, date, " YYYY/MM/DD", " Formato Data inválido");
        	registros.add(registro);
        	data_mask ++;
			return false;
		}
	}
	
	
	
	public static boolean isCpf(String cpf, String campo){
		if(cpf.matches("^\\s*([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}[-]?[0-9]{2})\\s*$")){
		 	return true;
		}
		else{
			registro = new Registros(campo, cpf, " XXX.XXX.XXX-XX", " Formato CPF inválido");
        	registros.add(registro);
			return false;
		}
	}
	
	@SuppressWarnings("static-access")
	public static boolean isEmbalagem(String embalagem, String campo){
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
			} else{
				registro = new Registros(campo, embalagem, " D,Q,QM,QMI,@", "Dados embalagem incorretos");
	        	registros.add(registro);
	        	formato_embalagem ++;
				return false;
			}
	} 
	
	public static boolean isFrete(String frete, String campo){
		String regex = "^(\\s*[CFS]\\s*)$";
		if(frete.matches(regex)){
			return true;
		}
		else
			registro = new Registros(campo, frete, "C - F - S", "Dados inválidos");
	    	registros.add(registro);
			return false;		
	}
	
	public static boolean SN(String sn, String campo){
		String regex = "^(\\s*[NS]\\s*)$";
		if(sn.matches(regex)){
			return true;
		}else{
			registro = new Registros(campo, sn, " S ou N, somente", "Dados inválidos");
	    	registros.add(registro);
			return false;
		}
	}
	 
	
	private static boolean isEstadoSeparadoPorVirgula(String uf, String campo) {
		String regex = "^\\s*(((([a-zA-Z]){2})([;]){0,1})*)\\s*$";
		if (uf.matches(regex)) {
			return true;
		}else{
			registro = new Registros(campo, uf,  " UF separados por vírgula", "Dados inválidos");
	    	registros.add(registro);
	    	return false;
		}
	}
	

	private static boolean isTipoPessoa(String tipo, String campo) {
		String regex = "^(\\s*[RCPFDI]\\s*)$";
		if(tipo.matches(regex)){
			return true;
		}else{
			registro = new Registros(campo, tipo,  " R - C - P - F - D - I", "Dados inválidos");
    		registros.add(registro);
    		return false;
		}
			
	}
	
	
	private static boolean isTrataLimitCred(String dados, String campo) {
		String regex = "^\\s*([0-8])\\s*$";
		if(dados.matches(regex)){
			return true;
		}else{
			registro = new Registros(campo, dados,  " 0, 1, 2, 3, 4, 5, 6, 7 ou 8", "Dados inválidos");
			registros.add(registro);
			return false;
		}
	}
	
	private static boolean isTipoComissao(String dados, String campo){
		String regex = "^\\s*([NSM])\\s*$";
		if(dados.matches(regex)){
			return true;
		}else{
			registro = new Registros(campo, dados,  " N- (Comissão Flexível), S - (Verba), M - (Margem C.)", "Dados inválidos");
			registros.add(registro);
			return false;
		}
	}
	
	private static boolean isPoliticaPrecos(String dados, String campo){
		String regex = "^\\s*([012])\\s*$";
		if(dados.matches(regex)){
			return true;
		}else{
			registro = new Registros(campo, dados,  " 0 - Libera, 1 - Trava, 2 - Ignora ", "Dados inválidos");
			registros.add(registro);
			return false;
		}
	}
	
	private static boolean isPraziMinimoEnt(String dados, String campo){
		String regex = "^(\\s*([9]{3})*\\s*\\s*([0]{1})*\\s*)$";
		if(dados.matches(regex)){
			return true;
		}else{
			registro = new Registros(campo, dados,  " 999 - Ilimitado, 0 - Desebilitado ", "Dados inválidos");
			registros.add(registro);
			return false;
		}
			
	}
	
	private static boolean isTipoProdCli(String dados, String campo){
		String regex = "^([FSGLBRC])$";
		if(dados.matches(regex)){
			return true;
		}else{
			registro = new Registros(campo, dados,  "Fornecedor, Segmento, Grupo, Linha, Subgrupo, Ramo, GrupoCliente", "Dados inválidos");
			registros.add(registro);
			return false;
		}

	}
	
	public static boolean isCodigoPorVirgula(String codigo, String campo){
		String regex = "(([^~,]*)[;]{0,1})*";
		String lastIndex = null;
		if(codigo == null || codigo.isEmpty()){
			return true;
		}
		
		lastIndex = codigo.substring(codigo.length() - 1); 
		if(lastIndex.equals(";")){
			registro = new Registros(campo, codigo,  "AA11;AA22", "Não deve haver virgula como ultimo caractere");
			registros.add(registro);
			return false;
		}	
		if(codigo.matches(regex)){
			return true;
		}
		else{
			registro = new Registros(campo, codigo,  "AA11;AA22", "Códigos devem ser sepados por vírgula");
			registros.add(registro);
			return false;
		}
	}
 
 
	public static void validaAtributos(Campo campo, String part){
		int atributo;
		try {
			if(campo.getAtributos() == null){
			}else{
				atributo =	campo.getAtributos().getId();		
				if(atributo == 1 && !part.isEmpty()){
					UtilsValidator.isTelefone(part, campo.getNomef());
				}
				if(atributo == 2 && !part.isEmpty()){
					UtilsValidator.isCnpj(part, campo.getNomef());
				}
				if(atributo == 3 && !part.isEmpty()){
					UtilsValidator.isCpf(part, campo.getNomef());
				}
				if(atributo == 4 && !part.isEmpty()){
					UtilsValidator.isDate(part, campo.getNomef());
				}
				if(atributo == 5 && !part.isEmpty()){ 
					UtilsValidator.isCep(part, campo.getNomef());
				}
				if(atributo == 6 && !part.isEmpty()){
					UtilsValidator.isEmbalagem(part, campo.getNomef());
					
				}if(atributo == 7 && !part.isEmpty()){
					UtilsValidator.isFrete(part, campo.getNomef());
					
				}if(atributo == 8 && !part.isEmpty()){
					UtilsValidator.SN(part, campo.getNomef());
					
				}if(atributo == 9 && !part.isEmpty()){
					UtilsValidator.isEstadoSeparadoPorVirgula(part, campo.getNomef());
					
				}if(atributo == 10 && !part.isEmpty()){
					UtilsValidator.isTipoPessoa(part, campo.getNomef());
				
				}if(atributo == 11 && !part.isEmpty()){
					UtilsValidator.isTrataLimitCred(part, campo.getNomef());
					
				}if(atributo == 12 && !part.isEmpty()){
					UtilsValidator.isTipoComissao(part, campo.getNomef());
					
				}if(atributo == 13 && !part.isEmpty()){
					UtilsValidator.isPoliticaPrecos(part, campo.getNomef());
					
				}if(atributo == 14 && !part.isEmpty()){
					UtilsValidator.isPraziMinimoEnt(part, campo.getNomef());
					
				}if(atributo == 15 && !part.isEmpty()){
					UtilsValidator.isTipoProdCli(part, campo.getNomef());
					
				}if(atributo == 16 && !part.isEmpty()){
					UtilsValidator.isCodigoPorVirgula(part, campo.getNomef());
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	





	public static List<String> checaCaractere(String part, String caractere, Integer tamMax) {

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


	public Registros getRegistro() {
		return registro;
	}


	public void setRegistro(Registros registro) {
		this.registro = registro;
	}


	public List<Registros> getRegistros() {
		return registros;
	}


	public void setRegistros(List<Registros> registros) {
		this.registros = registros;
	}
	
	public int getTelefone_mask() {
		return telefone_mask;
	}
	
	public static void setTelefone_mask(int telefone_mask) {
		UtilsValidator.telefone_mask = telefone_mask;
	}
	
	public static int getNull_erros() {
		return null_erros;
	}
	
	public static void setNull_erros(int null_erros) {
		UtilsValidator.null_erros = null_erros;
	}
	
	public static int getFormato_embalagem() {
		return formato_embalagem;
	}
	
	public static void setFormato_embalagem(int formato_embalagem) {
		UtilsValidator.formato_embalagem = formato_embalagem;
	}
	
	public static void setData_mask(int data_mask) {
		UtilsValidator.data_mask = data_mask;
	}
	
	

}
