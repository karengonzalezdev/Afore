package mx.secure.nci.ws.beans.response;

import javax.xml.bind.annotation.XmlElement;

public class SolicitudReclasificacionGuardarBeanResponse {
	
	private String folio;

	public SolicitudReclasificacionGuardarBeanResponse() {}

	public SolicitudReclasificacionGuardarBeanResponse(String folio) {
		this.folio = folio;
	}
	
	@XmlElement(name="folio")
	public String getFolio() {
		return folio;
	}
	
	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	
}
