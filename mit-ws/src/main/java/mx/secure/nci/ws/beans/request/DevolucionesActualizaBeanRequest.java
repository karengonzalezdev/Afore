package mx.secure.nci.ws.beans.request;

import mx.secure.nci.business.util.UtilValidate;
import mx.secure.nci.business.vo.DevolucionesVO;
import mx.secure.nci.business.vo.ValidacionesVO;

public class DevolucionesActualizaBeanRequest {

	private String folio;
	private String usuAct;
	private String banderaProc;
	
	
	public ValidacionesVO validarRequest() {
		
		if( this.folio == null && this.usuAct == null && this.banderaProc == null) {
			return new ValidacionesVO( Boolean.FALSE ,  "Por lo menos debe ingresar un criterio." );
		}
		
		if( this.folio == null ) {
			return new ValidacionesVO( Boolean.FALSE ,  "folio requerido" );
		}	
		if( this.usuAct == null ) {
			return new ValidacionesVO( Boolean.FALSE ,  "usuario de actualizacion requerido" );
		}			
		if( this.banderaProc == null ) {
			return new ValidacionesVO( Boolean.FALSE ,  "bandera requerida" );
		}else {
			if( !UtilValidate.validaFormatoStringF4( this.banderaProc , 1) ) {
				return new ValidacionesVO( Boolean.FALSE , "bandera requerida, validar formato NUMERICO MAX 1" );
			}			
		}
		
		return new ValidacionesVO(Boolean.TRUE , "Validaci�n exitosa.",
				new DevolucionesVO(folio,usuAct,new Integer(banderaProc)));
	}
	
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getUsuAct() {
		return usuAct;
	}
	public void setUsuAct(String usuAct) {
		this.usuAct = usuAct;
	}
	public String getBanderaProc() {
		return banderaProc;
	}
	public void setBanderaProc(String banderaProc) {
		this.banderaProc = banderaProc;
	}

	
}
