package mx.secure.nci.ws.beans.request;

import mx.secure.nci.business.util.UtilValidate;
import mx.secure.nci.business.vo.ValidacionesVO;

public class UltimosArchivosGeneradosBeanRequest {
	private String origenAportacion;
	private String cuentasIncluir;
	private String archivoGenerar;
	
	public UltimosArchivosGeneradosBeanRequest() {
		
	}
	
	public UltimosArchivosGeneradosBeanRequest( String origenAportacion
											  , String cuentasIncluir
											  , String archivoGenerar) {
		this.origenAportacion = origenAportacion;
		this.cuentasIncluir = cuentasIncluir;
		this.archivoGenerar = archivoGenerar;
	}

	public ValidacionesVO validarOrigenAportacion() {
		if( !UtilValidate.validaFormatoIntegerF4(this.origenAportacion, 5) ) {
			return new ValidacionesVO( Boolean.FALSE, "Validar formato origenAportacion, LONG MAX 5" );
		}
		return new ValidacionesVO( Boolean.TRUE , "Validacion exitosa." );
	}
	
	public ValidacionesVO validarCuentasIncluir() {
		if( !UtilValidate.validaFormatoIntegerF4(this.cuentasIncluir, 5) ) {
			return new ValidacionesVO( Boolean.FALSE, "Validar formato cuentasIncluir, LONG MAX 5" );
		}
		return new ValidacionesVO( Boolean.TRUE , "Validacion exitosa." );
	}
	public ValidacionesVO validarArchivoGenerar() {
		if( !UtilValidate.validaFormatoIntegerF4(this.archivoGenerar, 5) ) {
			return new ValidacionesVO( Boolean.FALSE, "Validar formato archivoGenerar, LONG MAX 5" );
		}
		return new ValidacionesVO( Boolean.TRUE , "Validacion exitosa." );
	}
	
	public String getOrigenAportacion() {
		return origenAportacion;
	}

	public void setOrigenAportacion(String origenAportacion) {
		this.origenAportacion = origenAportacion;
	}

	public String getCuentasIncluir() {
		return cuentasIncluir;
	}

	public void setCuentasIncluir(String cuentasIncluir) {
		this.cuentasIncluir = cuentasIncluir;
	}

	public String getArchivoGenerar() {
		return archivoGenerar;
	}

	public void setArchivoGenerar(String archivoGenerar) {
		this.archivoGenerar = archivoGenerar;
	}
	
	

}
