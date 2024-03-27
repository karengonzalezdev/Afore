package mx.secure.nci.ws.beans.request;

import mx.secure.nci.business.vo.ReporteDetalleIdcVO;
import mx.secure.nci.business.vo.ValidacionesVO;


public class DetalleIdcReporteBeanRequest {

	private String folio;
	
	
	public ValidacionesVO validarRequest() {
		
		if( this.folio == null || this.folio.isEmpty() ) {
			return new ValidacionesVO( Boolean.FALSE ,  "Por lo menos debe ingresar un folio." );
		}
		
		return new ValidacionesVO(Boolean.TRUE , "Validacion exitosa.", new ReporteDetalleIdcVO(folio));
	}	

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}
}
