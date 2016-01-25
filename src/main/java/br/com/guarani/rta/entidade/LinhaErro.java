package br.com.guarani.rta.entidade;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LinhaErro implements Serializable{
	

	private static final long serialVersionUID = 1L;

	private Integer num_linha;
	private String msg_linha;
	private List<Registros> erros;	
	
	public LinhaErro(Integer num_linha, List<Registros> erros) {
		this.num_linha = num_linha;
		this.erros = erros;
	}

	public LinhaErro() {
		// TODO Auto-generated constructor stub
	}


	public LinhaErro(Integer num_linha, String msg_linha) {
		this.num_linha = num_linha;
		this.msg_linha = msg_linha;
	}

	public Integer getNum_linha() {
		return num_linha;
	}

	public void setNum_linha(Integer num_linha) {
		this.num_linha = num_linha;
	}

	public List<Registros> getErros() {
		return erros;
	}

	public void setErros(List<Registros> erros) {
		this.erros = erros;
	}
	
	public String getMsg_linha() {
		return msg_linha;
	}
	
	public void setMsg_linha(String msg_linha) {
		this.msg_linha = msg_linha;
	}
	

}
