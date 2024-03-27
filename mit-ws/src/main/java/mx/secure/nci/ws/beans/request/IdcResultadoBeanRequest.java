package mx.secure.nci.ws.beans.request;

import mx.secure.nci.business.vo.ResultadoIdcVO;
import mx.secure.nci.business.vo.ValidacionesVO;

public class IdcResultadoBeanRequest {
	private String folio;
	
	public ValidacionesVO validaRequest() {
		if (this.folio == null || this.folio.isEmpty()) {
			return new ValidacionesVO(Boolean.FALSE, "Se debe recibir un folio");
		}	

		return new ValidacionesVO(Boolean.TRUE, "Folio Valido", new ResultadoIdcVO(folio));
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	
}

