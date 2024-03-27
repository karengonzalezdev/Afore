package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.ws.beans.MovBancoBean;

public class MovBancoConsultarBeanResponse {
	List<MovBancoBean> lista;
	
	public MovBancoConsultarBeanResponse()
	{}

	public MovBancoConsultarBeanResponse(List<MovBancoBean> lista)
	{
		super();
		this.lista = lista;
	}
	
	@XmlElementWrapper(name = "movBancos")
	@XmlElement(name = "movBanco")
	public List<MovBancoBean> getLista() {
		return lista;
	}

	public void setLista(List<MovBancoBean> lista) {
		this.lista = lista;
	}
	
}
