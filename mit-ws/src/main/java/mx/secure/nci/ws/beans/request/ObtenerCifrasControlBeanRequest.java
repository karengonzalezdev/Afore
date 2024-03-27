package mx.secure.nci.ws.beans.request;

import mx.secure.nci.business.vo.ObtenerCifrasControlVO;
import mx.secure.nci.business.vo.ValidacionesVO;

public class ObtenerCifrasControlBeanRequest {

private String folio;
	
	public ValidacionesVO validarRequest() {
		if(this.folio == null || this.folio.isEmpty()) {
			return new ValidacionesVO(Boolean.FALSE, "Se debe ingresar un folio");
		}
		return new ValidacionesVO(Boolean.TRUE, "Validacion exitosa.", new ObtenerCifrasControlVO(folio));
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}
	
}
