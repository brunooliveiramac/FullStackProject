package br.com.guarani.rta.validador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.guarani.rta.dao.campo.CampoDAO;
import br.com.guarani.rta.entidade.Campo;
import br.com.guarani.rta.entidade.TabelasErros;
import br.com.guarani.rta.validador.UtilsValidator;

@Component
public class TesteFile {
	
	
	@Autowired
	private CampoDAO campodao;
	
	@Autowired
    List<Campo> campos = null;

    @Test
	public List<TabelasErros> listaErros (File folder) throws IOException{

    	List<TabelasErros> tabelasDeErros = new ArrayList<TabelasErros>();
    	
		int carga;
		String nome;
		String nome_fisico;
		String cprimaria;
		int tam;
		String ativo;
		String nulo;
		String decimal;
		boolean isdata;
		boolean istelefone;
		boolean iscep;
		boolean iscnpj;
		
		int i;
		
		TabelasErros tabelasErros = null;
	    
		UtilsValidator utils = null;
			
		 for (final File fileEntry : folder.listFiles()) {
			 
		        if (fileEntry.isFile()) {		        	

		        	String basename = FilenameUtils.getBaseName(fileEntry.toString());
		        
		        	campos = campodao.porNomeTabela(basename);
		        	
		        	List<String> dados;
		        	 
		        	BufferedReader in = null;
		        	try {
		        	    in = new BufferedReader(new FileReader(fileEntry));
		        	    String read = null;
		        	  
		        	    while ((read = in.readLine()) != null) {
		        	    	
		        	    	tabelasErros = new TabelasErros();
				    	    
				        	utils = new UtilsValidator(tabelasErros);
		        	        
		        	        dados = UtilsValidator.checaCaractere(read, "|",200);	
		        	        
		        	        //Comparar quantidade de campos com o do banco
		        	        
		        	        if(dados.size() == campos.size()){
		        				System.out.println("preencha todos os campos");
		        	        	break;
		        	        }
		        	        
		        	        if(dados.size() == 0){
		        				System.out.println("Linha em Branco");
		        	        	break;
		        	        }
		        	        
		        	        System.out.println(dados.toString());
		        	        
		        	        for(i = 0; i<= dados.size(); i++){
		        	        	
		        	    	Iterator<Campo> it = campos.iterator();
		        	    	
		    	    	    		while (it.hasNext()){
		    	    	    			
		    	    	    			    Campo campo = it.next();     
						         			cprimaria = campo.getCprimaria();
						         			tam = campo.getTam();
						         			ativo = campo.getAtivo();
											nulo = campo.getNulo();
	 			
						         			carga = dados.get(i).length();
						         			
						         			utils.verificaTamanho(basename, campo.getNomef(), tam, carga, dados.get(i));
						         			
						         			utils.validaAtributos(campo, dados.get(i));
						         			
						         			utils.verifyIsNull(dados.get(i), nulo);
						         		i++;
		    	    	    		}
		        	           }
					            
		        	        tabelasDeErros.add(utils.getTabelasErro());
		        	    } 
		        	}catch(Exception e){
		        		e.printStackTrace();
		        	}	        	
		        	finally{
		        		in.close();
		        	}
		       }
		  }
		return tabelasDeErros;
	}    
}
