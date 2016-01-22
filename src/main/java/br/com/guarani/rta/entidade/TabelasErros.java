package br.com.guarani.rta.entidade;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TabelasErros implements Serializable {

	

		private static final long serialVersionUID = 1L;
		
		private String nome_tabela;
		
		private List<RelatorioErros> erros;

		public TabelasErros() {
		}

		public TabelasErros(String nome_tabela, List<RelatorioErros> erros) {
			this.nome_tabela = nome_tabela;
			this.erros = erros;
		}

		public List<RelatorioErros> getErros() {
			return erros;
		}
		
		public void setErros(List<RelatorioErros> erros) {
			this.erros = erros;
		}
		
			
		public String getNome_tabela() {
			return nome_tabela;
		}
		
		public void setNome_tabela(String nome_tabela) {
			this.nome_tabela = nome_tabela;
		}

		@Override
		public String toString() {
			return "TabelasErros [nome_tabela=" + nome_tabela + ", erros=" + erros + "]";
		}
		
		
		
		
}
