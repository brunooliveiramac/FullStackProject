package br.com.guarani.rta.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD) 
@Entity
@Table(name ="campo")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)
public class Campo implements br.com.guarani.rta.entidade.Entity, Comparable<Campo>{


	private static final long serialVersionUID = 1L;
	
	private int id;
	private String cprimaria;
	private int tam;
	private String ativo;
	private String nulo;
	private String comentarios;
	private String nome;
	private String tipo;
	private String nomef;
	private String deci;
	private Integer chave;

	private Tabela tabela;
	private Projeto projeto;
	private Layout layout; 
	
	private Atributo atributos;
	
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable = false, length = 80)
	public String getCprimaria() {
		return cprimaria;
	}
	public void setCprimaria(String cprimaria) {
		this.cprimaria = cprimaria;
	}
	
	@Column(nullable = false, length = 80)
	public int getTam() {
		return tam;
	}
	public void setTam(int tam) {
		this.tam = tam;
	}
	
	
	@Column(nullable = false, length = 80)
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	@Column(nullable = false, length = 80)
	public String getNulo() {
		return nulo;
	}
	public void setNulo(String nulo) {
		this.nulo = nulo;
	}
	
	
	@Column(nullable = false, length = 80)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Column(nullable = false, length = 80)
	public String getNomef() {
		return nomef;
	}

	public void setNomef(String nomef) {
		this.nomef = nomef;
	}

	@Column(nullable = false, length = 80)
	public String getDeci() {
		return deci;
	}

	public void setDeci(String deci) {
		this.deci = deci;
	}

	@Column(nullable = false, length = 80)
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "tabela_nomef", referencedColumnName= "nomef", nullable = false)
	public Tabela getTabela() {
		return tabela;
	}
	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}
	
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "projeto_id", nullable = false)
	@JsonIgnore
	public Projeto getProjeto() {
		return projeto;
	}
	
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "layout_gua_lay_num_ver", referencedColumnName= "gua_lay_num_ver", nullable = false)
	@JsonIgnore
	public Layout getLayout() {
		return layout;
	}
	
	public void setLayout(Layout layout) {
		this.layout = layout;
	}
	
	/*@ManyToOne
	@JoinColumn(name = "campo_id", referencedColumnName="id", nullable = true)
	public Campo getCampo() {
		return campo;
	}
	
	public void setCampo(Campo campo) {
		this.campo = campo;
	}*/


	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
 
	public Integer getChave() {
		return chave;
	}
	
	public void setChave(Integer chave) {
		this.chave = chave;
	}
	
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "atributo_id",referencedColumnName= "id", nullable = true)
	public Atributo getAtributos() {
		return atributos;
	}
	 
	public void setAtributos(Atributo atributos) {
		this.atributos = atributos;
	}
	
	@Override
	public String toString() {
		return nome+ " "+ativo+" " +nulo+" "+tam+" "+chave;
	}

	@Override 
	public int compareTo(Campo campo) {
		if(this.chave > campo.chave){
			return -1;
		}else
			
		return 1;
	}
	

}
