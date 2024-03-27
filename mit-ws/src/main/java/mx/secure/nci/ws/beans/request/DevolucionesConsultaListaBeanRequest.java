package mx.secure.nci.ws.beans.request;

import mx.secure.nci.business.util.UtilValidate;
import mx.secure.nci.business.vo.DevolucionesVO;
import mx.secure.nci.business.vo.ValidacionesVO;

public class DevolucionesConsultaListaBeanRequest {
	
	private String folio;
	private String folioConc;
	private String folioConciliacion;
	private String claveSolicitud;
	private String idConciliacion;
	private String cuentaIndividual;
	
	public ValidacionesVO validarRequest() {
		
		if( this.folio == null && this.folioConc == null && this.folioConciliacion == null && this.claveSolicitud == null
				&& this.idConciliacion == null && this.cuentaIndividual == null) {
			return new ValidacionesVO( Boolean.FALSE ,  "Por lo menos debe ingresar un criterio." );
		}
		
		Integer id = null;

		if(idConciliacion != null && !"".equals(idConciliacion)) {
			if( !UtilValidate.validaFormatoStringF4( this.idConciliacion , 8) ) {
				return new ValidacionesVO( Boolean.FALSE , "idConciliacion Validar formato NUMERICO MAX 8" );
			}else{
				id = new Integer(idConciliacion);
			}
		}
		
		return new ValidacionesVO(Boolean.TRUE , "Validaci�n exitosa.",
				new DevolucionesVO(folio,folioConc,folioConciliacion,claveSolicitud,cuentaIndividual,id));
	}
	
	public String getFolio() {
		return folio;
	}
	
	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	public String getFolioConc() {
		return folioConc;
	}
	
	public void setFolioConc(String folioConc) {
		this.folioConc = folioConc;
	}
	
	public String getFolioConciliacion() {
		return folioConciliacion;
	}
	
	public void setFolioConciliacion(String folioConciliacion) {
		this.folioConciliacion = folioConciliacion;
	}
	
	public String getClaveSolicitud() {
		return claveSolicitud;
	}
	
	public void setClaveSolicitud(String claveSolicitud) {
		this.claveSolicitud = claveSolicitud;
	}
	
	public String getIdConciliacion() {
		return idConciliacion;
	}
	
	public void setIdConciliacion(String idConciliacion) {
		this.idConciliacion = idConciliacion;
	}
	
	public String getCuentaIndividual() {
		return cuentaIndividual;
	}
	
	public void setCuentaIndividual(String cuentaIndividual) {
		this.cuentaIndividual = cuentaIndividual;
	}

}
