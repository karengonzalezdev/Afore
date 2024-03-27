package mx.secure.nci.ws.beans.request;

import mx.secure.nci.business.vo.DetalleExcepcionesIdcVO;
import mx.secure.nci.business.vo.ValidacionesVO;

public class DetalleExcepcionesIdcBeanRequest {

	private String folio;
	
	public ValidacionesVO validarRequest() {
		if(this.folio == null || this.folio.isEmpty()) {
			return new ValidacionesVO(Boolean.FALSE, "Se debe ingresar un folio");
		}
		return new ValidacionesVO(Boolean.TRUE, "Validación exitosa.", new DetalleExcepcionesIdcVO(folio));
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}
}
