package mx.secure.nci.ws.beans.request;

import mx.secure.nci.business.util.UtilMethod;
import mx.secure.nci.business.util.UtilValidate;
import mx.secure.nci.business.vo.LoteControlAluxVO;
import mx.secure.nci.business.vo.ValidacionesVO;

public class DevolucionesListaEstatusBeanRequest {

	private String fechaPresentacion;
	private String fechaFinPresentacion;
	private String idArchivo;
	private String idEstatusLote;
	private String estatus;
	
	public ValidacionesVO validarRequest() {
		if( this.fechaPresentacion == null ) {
			return new ValidacionesVO( Boolean.FALSE ,  "fechaPresentacion requerida" );
		}
		if( this.fechaFinPresentacion == null ) {
			return new ValidacionesVO( Boolean.FALSE ,  "fechaFinPresentacion requerida" );
		}
		if( !UtilValidate.validaFormatoStringF4( this.idArchivo , 5) ) {
			return new ValidacionesVO( Boolean.FALSE , "idArchivo requerido, validar formato NUMERICO MAX 5" );
		}
		if( !UtilValidate.validaFormatoStringF4( this.idEstatusLote , 8) ) {
			return new ValidacionesVO( Boolean.FALSE , "idEstatusLote requerido, validar formato NUMERICO MAX 8" );
		}
		if( this.fechaPresentacion != null &&  this.fechaFinPresentacion != null) {
			if( !UtilValidate.validarFechaPorFormatoF4( this.fechaPresentacion , "dd/MM/yyyy") ) {
				return new ValidacionesVO( Boolean.FALSE , "fechaPresentacion, validar formato dd/MM/yyyy" );
			}
			if( !UtilValidate.validarFechaPorFormatoF4( this.fechaFinPresentacion , "dd/MM/yyyy") ) {
				return new ValidacionesVO( Boolean.FALSE , "fechaFinPresentacion, validar formato dd/MM/yyyy" );
			}			
		}
		return new ValidacionesVO( Boolean.TRUE , "Validaci�n exitosa.", 
				new LoteControlAluxVO(new Integer(idArchivo), new Integer(idEstatusLote), UtilMethod.getDateToString(fechaPresentacion, "dd/MM/yyyy"), 
						UtilMethod.getDateToString(fechaFinPresentacion, "dd/MM/yyyy"))); 
	}

	public String getFechaPresentacion() {
		return fechaPresentacion;
	}

	public void setFechaPresentacion(String fechaPresentacion) {
		this.fechaPresentacion = fechaPresentacion;
	}

	public String getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(String idArchivo) {
		this.idArchivo = idArchivo;
	}

	public String getIdEstatusLote() {
		return idEstatusLote;
	}

	public void setIdEstatusLote(String idEstatusLote) {
		this.idEstatusLote = idEstatusLote;
	}

	public String getFechaFinPresentacion() {
		return fechaFinPresentacion;
	}

	public void setFechaFinPresentacion(String fechaFinPresentacion) {
		this.fechaFinPresentacion = fechaFinPresentacion;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	
}
