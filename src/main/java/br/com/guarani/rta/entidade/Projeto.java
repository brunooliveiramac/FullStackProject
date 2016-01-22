package br.com.guarani.rta.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.guarani.rta.JsonViews;

 
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity 
@Table(name ="projeto")
public class Projeto implements br.com.guarani.rta.entidade.Entity{
	
	
	@Id
	@GeneratedValue
	private int gua_pro_id;
	
	private String gua_pro_nome;
	
	@JsonView(JsonViews.Admin.class)
	@Column(nullable = false, length = 12)
	public int getGua_pro_id() {
		return gua_pro_id;
	}
	
	public void setGua_pro_id(int gua_pro_id) {
		this.gua_pro_id = gua_pro_id;
	} 
	
	@JsonView(JsonViews.Admin.class)
	public String getGua_pro_nome() {
		return gua_pro_nome;
	}
	public void setGua_pro_nome(String gua_pro_nome) {
		this.gua_pro_nome = gua_pro_nome;
	}
	
	
	@Override
	public String toString() {
		return gua_pro_nome;
	}
	

}
