package mx.secure.nci.ws.beans.request;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.business.vo.ActualizaEstatusIdcVO;
import mx.secure.nci.business.vo.ValidacionesVO;

public class ActualizaEstatusIdcBeanRequest {

	private String folio;
	private List<String> clientesDescartados;
	
	
public ValidacionesVO validarRequest() {
		
		if( this.folio == null ) {
			return new ValidacionesVO( Boolean.FALSE ,  "folio requerido" );
		}
		if( this.clientesDescartados == null) {
			return new ValidacionesVO( Boolean.FALSE ,  "Lista de Curp requerida" );
		}
		if( this.folio.isEmpty()) {
			return new ValidacionesVO( Boolean.FALSE ,  "folio requerido" );
		}
		if(this.clientesDescartados.isEmpty()) {
			return new ValidacionesVO( Boolean.FALSE ,  "Lista de Curp requerida" );
		}
		
		return new ValidacionesVO(Boolean.TRUE , "Validaci�n exitosa.",
				new ActualizaEstatusIdcVO(folio,clientesDescartados));
	}

	@XmlElementWrapper(name = "clientesDescartados")
	@XmlElement(name = "clienteDescartadoIdc")
	public List<String> getClientesDescartados() {
		return clientesDescartados;
	}
	
	public void setClientesDescartados(List<String> clientesDescartados) {
		this.clientesDescartados = clientesDescartados;
	}
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}	
	
}
