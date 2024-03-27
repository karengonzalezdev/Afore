package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.ws.beans.ConciliacionBean;

public class ConciliacionConsultarBeanResponse {
	List<ConciliacionBean> lista;
	
	public ConciliacionConsultarBeanResponse()
	{
		
	}
	public ConciliacionConsultarBeanResponse(List<ConciliacionBean> lista)
	{
		super();
		this.lista = lista;
	}

	@XmlElementWrapper(name = "conciliaciones")
	@XmlElement(name = "conciliacion")
	public List<ConciliacionBean> getLista() {
		return lista;
	}

	public void setLista(List<ConciliacionBean> lista) {
		this.lista = lista;
	}
	
}
