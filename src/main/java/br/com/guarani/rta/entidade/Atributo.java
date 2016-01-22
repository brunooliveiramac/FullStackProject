package br.com.guarani.rta.entidade;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

@Entity
@Table(name ="atributo")
public class Atributo  implements br.com.guarani.rta.entidade.Entity {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private TipoAtributo tipo;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	} 

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoAtributo getTipo() {
		return tipo;
	}

	public void setTipo(TipoAtributo tipo) {
		this.tipo = tipo;
	}
	
	
	

}
