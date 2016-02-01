package br.com.guarani.rta.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity //Entidade JPA
@Table(name ="layout")
public class Layout implements br.com.guarani.rta.entidade.Entity {

	
	private static final long serialVersionUID = 1L;
	

	
	private Date gua_lay_data_lancamento;
	private String gua_lay_notas;
	private int gua_lay_num_ver;
	private Projeto projeto;
    private String layoutPath;

	
	 
	

	public Date getGua_lay_data_lancamento() {
		return gua_lay_data_lancamento;
	}
	public void setGua_lay_data_lancamento(Date gua_lay_data_lancamento) {
		this.gua_lay_data_lancamento = gua_lay_data_lancamento;
	}

	public String getGua_lay_notas() {
		return gua_lay_notas;
	}
	public void setGua_lay_notas(String gua_lay_notas) {
		this.gua_lay_notas = gua_lay_notas;
	}
	
	@Id
	@GeneratedValue
	public int getGua_lay_num_ver() {
		return gua_lay_num_ver;
	}
	public void setGua_lay_num_ver(int gua_lay_num_ver) {
		this.gua_lay_num_ver = gua_lay_num_ver;
	}
	
	@ManyToOne 
	@JoinColumn(name = "projeto_id", nullable = false)
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}


	public String getLayoutPath() {
		return layoutPath;
	}
	
	public void setLayoutPath(String layoutPath) {
		this.layoutPath = layoutPath;
	}
	@Override
	public String toString() {
		return "Layout [gua_lay_data_lancamento=" + gua_lay_data_lancamento + ", gua_lay_notas=" + gua_lay_notas
				+ ", gua_lay_num_ver=" + gua_lay_num_ver + ", projeto=" + projeto + ", layoutPath=" + layoutPath + "]";
	}
	
	
	
	
	
}
	
	
	

	
	
	
	
	