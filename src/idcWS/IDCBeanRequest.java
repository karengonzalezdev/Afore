package idcWS;

import idcBusiness.ValidacionesVO;

public class IDCBeanRequest {

	private String folio;
	
	public ValidacionesVO validarRequest() {
		if(this.folio == null || this.folio.isEmpty()) {
			return new ValidacionesVO(Boolean.FALSE, "Por lo menos debe ingresar un folio.");
		}
		return new ValidacionesVO(Boolean.TRUE, "Validación exitosa."); 
	}
	
	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}
	
}
