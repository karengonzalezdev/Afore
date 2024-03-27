package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.ws.beans.SolicitudDomiciliacionBean;


public class SolicitudCargosConsultarSumBeanResponse {
	
	
	
	public SolicitudCargosConsultarSumBeanResponse(){}
	
	public SolicitudCargosConsultarSumBeanResponse(
			List<SolicitudDomiciliacionBean> listaSolicitudDomiciliacion) {
		super();
		this.listaSolicitudDomiciliacion = listaSolicitudDomiciliacion;
	}

	private List<SolicitudDomiciliacionBean> listaSolicitudDomiciliacion;

	@XmlElementWrapper(name = "solicitudesDomiciliacion")
	@XmlElement(name = "solicitud")
	public List<SolicitudDomiciliacionBean> getListaSolicitudDomiciliacion() {
		return listaSolicitudDomiciliacion;
	}

	public void setListaSolicitudDomiciliacion(List<SolicitudDomiciliacionBean> listaSolicitudDomiciliacion) {
		this.listaSolicitudDomiciliacion = listaSolicitudDomiciliacion;
	}
	
}
