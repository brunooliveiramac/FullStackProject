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

import org.apache.commons.collections.CollectionUtils;
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
import br.com.guarani.rta.dao.tabela.TabelaDAO;
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
	private TabelaDAO tabelaDAO;
	
	@Autowired
    List<Campo> campos = null;
	
	
	
	public List<String> isCorrectTableName(File file)
	{
		
	
		
	List<String> filesWorongName = null;
	filesWorongName = new ArrayList<>();

	List<String> listaNomeTabelaBd = tabelaDAO.getTablesName();
	
	for(File f  :  file.listFiles())
	{	
		
    	String basename = FilenameUtils.getBaseName(f.toString());		
		
    	if(listaNomeTabelaBd.contains(basename))
		{
		
		}else
		
			filesWorongName.add(basename);
		}
		return filesWorongName;
	}
	

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
        Integer linha = 1;
        List<String> incorrect = null;
        
		TabelaErro tabelasErros = null;
		
	    LinhaErro linhaErro = null;
		
		UtilsValidator utils = null;
			
		 loop:
		 for (File fileEntry : folder.listFiles())
		 {
			  
		        if (fileEntry.isFile()) 
		        {		        	
		        	linha = 1;
		        	
		        	String basename = FilenameUtils.getBaseName(fileEntry.toString());
		        	
		        	tabelasErros = new TabelaErro(basename);
		        	
		        	incorrect = isCorrectTableName(folder);
        	    	
		        	if(incorrect.size() != 0)
        	    	{
        	    		tabelasErros.setTabelas_nome_incorreto(incorrect);
        	    		tabelasErros.setNome_tabela(null);
		        		tabelaErros.add(tabelasErros);
        	            break loop;
        	    	} 

		        	campos = campodao.porNomeTabela(basename);
		        	List<String> dados;
		        	BufferedReader in = null;
		        	
		        	try 
		        	{
		        		in = new BufferedReader(new FileReader(fileEntry));
		        	    String read = null;
		        	  
		        	    while ((read = in.readLine()) != null)
		        	    {
		        	    if(read == null) break;
		        	    	
				        	utils = new UtilsValidator();
		        	        
		        	        dados = UtilsValidator.checaCaractere(read, "|",200);	
		        	         					        	        
		        	        looplinha:
		        	        for(i = 0; i<= dados.size(); i++)
		        	        {
		        	        Iterator<Campo> it = campos.iterator();
		        	    	
		    	    	    		while (it.hasNext())
		    	    	    		{		
		    	    	    			
				    	    	    			 if(dados.size() != campos.size() && dados.size() != 0)
								        	        {
				    	    	    				 	if(dados.size() < campos.size()){
				    	    	    				 		linhaErro = new LinhaErro(linha ++, " com indice incorreto. Faltando dados na linha.");
										        	        tabelasErros.getLinhas().add(linhaErro);
										        	        break looplinha;	
				    	    	    				 	}else{
				    	    	    				 		linhaErro = new LinhaErro(linha ++, " com indice incorreto. Dados acima do esperado.");
										        	        tabelasErros.getLinhas().add(linhaErro);
										        	        break looplinha;	
				    	    	    				 	}
								        	        	
								        	        }
				    	    	    			 
				    	    	    			  if(dados.size() == 0)
								        	        {	
								        	        	linhaErro = new LinhaErro(linha ++, " em branco.");
									        	        tabelasErros.getLinhas().add(linhaErro);
								        	        	break looplinha;
								        	        }
				    	    	    				
		    	    	    			
		    	    	    			    Campo campo = it.next();     
						         			cprimaria = campo.getCprimaria();
						         			tam = campo.getTam();
						         			ativo = campo.getAtivo();
											nulo = campo.getNulo();
	 			
						         			carga = dados.get(i).length();
						         			
						         			utils.verificaTamanho(basename, campo, tam, carga, dados.get(i));
						         			
						         			utils.validaAtributos(campo, dados.get(i));
						         			
						         			utils.verifyIsNull(campo, dados.get(i), nulo);
						         			
						         			i++;
		    	    	    		}
		    	    	    		
		        	           }
					        	        
					        if(utils.getRegistros().size() == 0)
					        {
					        //linha com indice incorreto ou em branco, inf já adicionada. (Pula).
					        }else
					        {
					        	linhaErro = new LinhaErro(linha ++, utils.getRegistros());
			        	        tabelasErros.getLinhas().add(linhaErro);
			        	        
					        }
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
