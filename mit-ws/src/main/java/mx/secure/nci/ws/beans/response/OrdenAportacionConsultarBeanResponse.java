package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.ws.beans.OrdenAportacionBean;

public class OrdenAportacionConsultarBeanResponse 
{
	
	
	
	public OrdenAportacionConsultarBeanResponse(
			List<OrdenAportacionBean> ordenesAportacion) {
		super();
		this.ordenesAportacion = ordenesAportacion;
	}
	
	public OrdenAportacionConsultarBeanResponse(){}
	

	private List<OrdenAportacionBean> ordenesAportacion;

	@XmlElementWrapper(name="ordenesAportacion")
	@XmlElement(name="ordenAportacion")
	public List<OrdenAportacionBean> getOrdenesAportacion() {
		return ordenesAportacion;
	}

	public void setOrdenesAportacion(List<OrdenAportacionBean> ordenesAportacion) {
		this.ordenesAportacion = ordenesAportacion;
	}

}
