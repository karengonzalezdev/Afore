package mx.secure.nci.ws.beans.request;

import mx.secure.nci.business.util.UtilValidate;
import mx.secure.nci.business.vo.ValidacionesVO;

public class ArchivosDomiBitacoraBeanRequest {
	private String folio;
	
	public ArchivosDomiBitacoraBeanRequest () {
		
	}
	
	public ArchivosDomiBitacoraBeanRequest (String folio) {
		this.folio = folio;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	public ValidacionesVO validarRequest() {
		
		if( !UtilValidate.validaFormatoIntegerF4(this.folio, 20) ) {
			return new ValidacionesVO( Boolean.FALSE, "Validar formato folio, LONG MAX 20" );
		}
		
		return new ValidacionesVO( Boolean.TRUE , "Validacion exitosa." );
	}

}
