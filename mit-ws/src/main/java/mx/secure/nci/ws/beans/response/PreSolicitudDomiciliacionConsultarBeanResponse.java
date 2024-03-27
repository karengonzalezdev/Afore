package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.ws.beans.PreSolicitudDomiciliacionBean;


public class PreSolicitudDomiciliacionConsultarBeanResponse 
{
	public PreSolicitudDomiciliacionConsultarBeanResponse(){}
	
	public PreSolicitudDomiciliacionConsultarBeanResponse(List<PreSolicitudDomiciliacionBean> listaPreSolicitudDomiciliacion)
	{
		super();
		this.listaPreSolicitudDomiciliacion = listaPreSolicitudDomiciliacion;
	}
	
	private List<PreSolicitudDomiciliacionBean> listaPreSolicitudDomiciliacion;
	
	
	@XmlElementWrapper(name = "presolicitudesDomiciliacion")
	@XmlElement(name = "preSolicitud")
	public List<PreSolicitudDomiciliacionBean> getListaPreSolicitudDomiciliacion() {
		return listaPreSolicitudDomiciliacion;
	}

	public void setListaPreSolicitudDomiciliacion(List<PreSolicitudDomiciliacionBean> listaPreSolicitudDomiciliacion) {
		this.listaPreSolicitudDomiciliacion = listaPreSolicitudDomiciliacion;
	}
	
}
