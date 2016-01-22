package br.com.guarani.rta.entidade;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name="user_projeto")
public class UserProjeto implements Entity{

	private static final long serialVersionUID = 1L;
	
	private User user;
	private Projeto projeto;
	private int id;
	
	@Id 
	@GeneratedValue
	@Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "username", referencedColumnName= "name", nullable = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name = "projetoid",  referencedColumnName= "gua_pro_id", nullable = false)
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	

	
	
	
	
	
	
}
