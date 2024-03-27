package mx.secure.nci.ws.beans.request;

import javax.xml.bind.annotation.XmlElement;

import mx.secure.nci.ws.beans.PreSolicitudDomiciliacionBean;


public class PreSolicitudDomiciliacionInsertarBeanRequest 
{
	private PreSolicitudDomiciliacionBean preSolicitudDomiciliacion;

		
	@XmlElement(name = "preSolicitud")
	public PreSolicitudDomiciliacionBean getPreSolicitudDomiciliacion() 
	{
		return preSolicitudDomiciliacion;
	}

	public void setPreSolicitudDomiciliacion(PreSolicitudDomiciliacionBean preSolicitudDomiciliacion) 
	{
		this.preSolicitudDomiciliacion = preSolicitudDomiciliacion;
	}

}
