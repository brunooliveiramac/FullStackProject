package br.com.guarani.rta.entidade;


import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Layouts implements Serializable {
		
		
	    private static final long serialVersionUID = 1L;
	
	
		List<Layout> layouts;
		
		public List<Layout> getLayout() {
			return layouts;
		}
		
		public void setLayout(List<Layout> layouts) {
			this.layouts = layouts;
		}
		
		
}	
