package br.com.guarani.rta.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity 
@Table(name ="tabela")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)
public class Tabela implements br.com.guarani.rta.entidade.Entity{
	

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String cprimaria;
	private String tam;
	private String ativo;
	private String nulo;
	private String comentarios;
	private String nome;
	private String nomef;
	private String deci;
	
	private Layout layout;
	private Projeto projeto;
	private List<Campo> children = new ArrayList<Campo>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(nullable = true, length = 80)
	public String getAtivo() {
		return ativo;
	}
	
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	
		

	@Column(nullable = true, length = 80)
	public String getCprimaria() {
		return cprimaria;
	}

	public void setCprimaria(String cprimaria) {
		this.cprimaria = cprimaria;
	}

	@Column(nullable = true, length = 80)
	public String getTam() {
		return tam;
	}

	public void setTam(String tam) {
		this.tam = tam;
	}

	

	
	@Column(nullable = true, length = 80)
	public String getNulo() {
		return nulo;
	}

	public void setNulo(String nulo) {
		this.nulo = nulo;
	}

	
	@Column(nullable = true, length = 80)
	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	
	
	@Column(name = "nome", nullable = true, length = 80)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Column(nullable = true, length = 80)
	public String getNomef() {
		return nomef;
	}

	public void setNomef(String nomef) {
		this.nomef = nomef;
	}

	
	@Column(nullable = true, length = 80)
	public String getDeci() {
		return deci;
	}

	public void setDeci(String deci) {
		this.deci = deci;
	}

	@ManyToOne
	@JoinColumn(name = "projeto_id", nullable = false)
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	@JsonManagedReference
	@OneToMany(mappedBy = "tabela", targetEntity = Campo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	public List<Campo> getChildren() {
		return children;
	}
	
	public void setChildren(List<Campo> children) {
		this.children = children;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "layout_gua_lay_num_ver", referencedColumnName= "gua_lay_num_ver", nullable = false)
	public Layout getLayout() {
		return layout;
	}

	
	public void setLayout(Layout layout) {
		this.layout = layout;
	}
	
	
	@Override
	public String toString() {
		return nome;
	}
	

}
