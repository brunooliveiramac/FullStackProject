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
import br.com.guarani.rta.entidade.LinhaErro;
import br.com.guarani.rta.entidade.TabelaErro;
import br.com.guarani.rta.entidade.TabelasErros;
import br.com.guarani.rta.validador.UtilsValidator;

@Component
public class TesteFile{
	
	
	@Autowired
	private CampoDAO campodao;
	
	@Autowired
    List<Campo> campos = null;

    @SuppressWarnings("static-access")
	@Test
	public List<TabelaErro> listaErros (File folder) throws IOException
    {

    	List<TabelaErro> tabelaErros = new ArrayList<TabelaErro>();
    	
    	List<LinhaErro> linhaErros = new ArrayList<LinhaErro>();
    	
    	
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
        int linha = 1;

		TabelaErro tabelasErros = null;
		
	    LinhaErro linhaErro = null;
		
		UtilsValidator utils = null;
			
		 for (File fileEntry : folder.listFiles())
		 {
			  
		        if (fileEntry.isFile()) 
		        {		        	
		        	
		        	linha = 1;
		        	
		        	String basename = FilenameUtils.getBaseName(fileEntry.toString());
		        	
		        	tabelasErros = new TabelaErro(basename);

		        	campos = campodao.porNomeTabela(basename);
		        	
		        	List<String> dados;
		        	 
		        	BufferedReader in = null;
		        	
		        	try 
		        	{
		        	    
		        		in = new BufferedReader(new FileReader(fileEntry));
		        	    String read = null;
		        	  
		        	    while ((read = in.readLine()) != null)
		        	    {
				        	utils = new UtilsValidator();
		        	        
		        	        dados = UtilsValidator.checaCaractere(read, "|",200);	
		        	        
		        	        
		        	        if(dados.size() != campos.size())
		        	        {
		        				System.out.println("Preencha Todos os Campos");
		        	        	break;
		        	        }
		        	        
		        	        if(dados.size() == 0)
		        	        {
		        				System.out.println("Linha em Branco");
		        	        	break;
		        	        }
		        	        
		        	        System.out.println(dados.toString());
		        	        
		        	        for(i = 0; i<= dados.size(); i++)
		        	        {

		        	        Iterator<Campo> it = campos.iterator();
		        	    	
		    	    	    		while (it.hasNext())
		    	    	    		{
		    	    	    			    Campo campo = it.next();     
						         			cprimaria = campo.getCprimaria();
						         			tam = campo.getTam();
						         			ativo = campo.getAtivo();
											nulo = campo.getNulo();
	 			
						         			carga = dados.get(i).length();
						         			
						         			utils.verificaTamanho(basename, campo, tam, carga, dados.get(i));
						         			
						         			utils.validaAtributos(campo, dados.get(i));
						         			
						         			utils.verifyIsNull(dados.get(i), nulo);
						         			
						         			i++;
		    	    	    		}
		        	           }
		        	    	
							linhaErro = new LinhaErro(linha ++, utils.getRegistros());
		        	        tabelasErros.getLinhas().add(linhaErro);
		        	    } 
		        	  		        	   
		        	}
		        	catch(Exception e)
		        	{
		        		e.printStackTrace();
		        	}	        	
		        	finally
		        	{
		        		in.close();
		        	}		        	
			        tabelaErros.add(tabelasErros);
		        	}
		  }
		 return tabelaErros;
	}
}
