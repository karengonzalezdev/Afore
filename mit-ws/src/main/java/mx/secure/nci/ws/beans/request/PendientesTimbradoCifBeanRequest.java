package mx.secure.nci.ws.beans.request;

import mx.secure.nci.business.vo.PendientesTimbradoCifVO;
import mx.secure.nci.business.vo.ValidacionesVO;

public class PendientesTimbradoCifBeanRequest {

	private String folio;
	private String tipoDevolucion;

	public ValidacionesVO validarRequest() {
		if (this.folio == null || this.folio.isEmpty()) {
			return new ValidacionesVO(Boolean.FALSE, "Se debe ingresar un folio");
		}
		if (this.tipoDevolucion == null || this.tipoDevolucion.isEmpty()) {
			return new ValidacionesVO(Boolean.FALSE, "Se debe ingresar un tipo de devolucion");
		}
		return new ValidacionesVO(Boolean.TRUE, "Validacion exitosa.",
				new PendientesTimbradoCifVO(folio, tipoDevolucion));
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getTipoDevolucion() {
		return tipoDevolucion;
	}

	public void setTipoDevolucion(String tipoDevolucion) {
		this.tipoDevolucion = tipoDevolucion;
	}

}
