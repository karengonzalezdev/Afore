package mx.secure.nci.ws.beans.request;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.business.vo.ValidacionesVO;


public class DevolucionesEnvioCIFBeanRequest {

	private List<String> folios;
	
	
	public ValidacionesVO validarRequest() {
		
		if( this.folios == null ) {
			return new ValidacionesVO( Boolean.FALSE ,  "Por lo menos debe ingresar un criterio." );
		}
		
		return new ValidacionesVO(Boolean.TRUE , "Validaci�n exitosa.");
	}	

	@XmlElementWrapper(name="ListaFolios")
	@XmlElement(name = "folio")
	public List<String> getFolios() {
		return folios;
	}

	public void setFolios(List<String> folios) {
		this.folios = folios;
	}
	
	
}
