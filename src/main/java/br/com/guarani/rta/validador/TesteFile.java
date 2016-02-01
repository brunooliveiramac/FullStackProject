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

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.guarani.rta.dao.campo.CampoDAO;
import br.com.guarani.rta.dao.tabela.TabelaDAO;
import br.com.guarani.rta.dao.tabela.TabelaDAOimpl;
import br.com.guarani.rta.entidade.CabecalhoErros;
import br.com.guarani.rta.entidade.Campo;
import br.com.guarani.rta.entidade.LinhaErro;
import br.com.guarani.rta.entidade.TabelaErro;
import br.com.guarani.rta.entidade.TabelasErros;
import br.com.guarani.rta.validador.UtilsValidator;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component 
public class TesteFile {
	
	private static int dados_acima = 0; 
	private static int dados_abaixo = 0; 

	
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
    		//correta
		}else
		
			filesWorongName.add(basename);
		}
		return filesWorongName;
	}
	


    @SuppressWarnings("static-access")
	@Test
	public CabecalhoErros listaErros (File folder) throws IOException
    {
    	CabecalhoErros cabecalhoErros =  new  CabecalhoErros();
    	List<TabelaErro> tabelaErros = new ArrayList<TabelaErro>();
    	List<LinhaErro> linhaErros = new ArrayList<LinhaErro>();
    	
		int carga;
		String nome;
		String nome_fisico;
		String cprimaria;
		Integer tam;
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
        	    	{	//Se há tabelas com nome incorreto, para o loop e retorna as mesmas para o usuario
        	    		cabecalhoErros.setNomes_tabelas_incorretas(incorrect); 
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
		        	        
		        	        dados = UtilsValidator.checaCaractere(read, "|",800);	
		        	         					        	        
		        	        looplinha:
		        	        for(i = 0; i<= dados.size(); i++)
		        	        {
		        	        Iterator<Campo> it = campos.iterator();
		        	    	
		    	    	    		while (it.hasNext())
		    	    	    		{		
				    	    	    			 if(dados.size() != campos.size() && dados.size() != 0)
								        	        {	//Se indice diferente (Maior ou Menor).
				    	    	    				 	if(dados.size() < campos.size()){
				    	    	    				 		linhaErro = new LinhaErro(linha ++, " com indice incorreto. Faltando dados na linha.");
										        	        tabelasErros.getLinhas().add(linhaErro);
										        	        dados_abaixo ++;
										        	        break looplinha;	
				    	    	    				 	}else{
				    	    	    				 		linhaErro = new LinhaErro(linha ++, " com indice incorreto. Dados acima do esperado.");
										        	        tabelasErros.getLinhas().add(linhaErro);
										        	        dados_acima ++;
										        	        break looplinha;	
				    	    	    				 	}
								        	        	
								        	        }
				    	    	    			 
				    	    	    			  if(dados.size() == 0)
								        	        {	//Se Linha em branco.
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
		        		cabecalhoErros.getList().add(tabelasErros);
		        	}
		         
		         	cabecalhoErros.setTelefone_mask_erros(utils.getTelefone_mask()); //getErros of static variables
		         	cabecalhoErros.setNull_erros(utils.getNull_erros());
		         	cabecalhoErros.setFormato_embalagem(utils.getFormato_embalagem());
		         	cabecalhoErros.setCodigo_virgula(utils.getCodigo_virgula());
		         	cabecalhoErros.setTipo_prod_cli(utils.getTipo_prod_cli());
		         	cabecalhoErros.setPrazo_min(utils.getPrazo_min_ent());
		         	cabecalhoErros.setPolitica_precos(utils.getPolitica_precos());
		         	cabecalhoErros.setTipo_comissao(utils.getTipo_comissao());
		         	cabecalhoErros.setLimite_credito(utils.getLimite_credito());
		         	cabecalhoErros.setTipo_pessoa(utils.getTipo_pessoa());
		         	cabecalhoErros.setEstado_virgulo(utils.getEstado_virgulo());
		         	cabecalhoErros.setSina(utils.getSina());
		         	cabecalhoErros.setFrete(utils.getFrete());
		         	cabecalhoErros.setCep(utils.getCep());
		         	cabecalhoErros.setFormato_cpf(utils.getFormato_cpf());
		         	cabecalhoErros.setFormato_cnpj(utils.getFormato_cnpj());
		         	cabecalhoErros.setDate_mask_erros(utils.getData_mask());
		         	cabecalhoErros.setDados_acima(dados_acima);
		         	cabecalhoErros.setDados_abaixo(dados_abaixo);
		  }		
     	 utils.setTelefone_mask(0); //setErros to 0 of static variables for the next interation
     	 utils.setNull_erros(0);
     	 utils.setFormato_embalagem(0);
     	 utils.setCodigo_virgula(0);
     	 utils.setTipo_prod_cli(0);
     	 utils.setPrazo_min_ent(0);
     	 utils.setPrazo_min_ent(0);
     	 utils.setPolitica_precos(0);
     	 utils.setTipo_pessoa(0);
     	 utils.setEstado_virgulo(0);
     	 utils.setSina(0);
     	 utils.setFrete(0);
     	 utils.setCep(0);
     	 utils.setFormato_cnpj(0);
     	 utils.setFormato_cpf(0);
     	 utils.setFormato_cnpj(0);
     	 utils.setData_mask(0); 
     	 dados_acima = 0;
     	 dados_abaixo = 0;
     	 
		
		 return cabecalhoErros;
	}

    
    public static int getDados_acima() {
		return dados_acima;
	}
    
    public static void setDados_acima(int dados_acima) {
		TesteFile.dados_acima = dados_acima;
	}
    
    public static int getDados_abaixo() {
		return dados_abaixo;
	}
    
    public static void setDados_abaixo(int dados_abaixo) {
		TesteFile.dados_abaixo = dados_abaixo;
	}
    
    
    
}
