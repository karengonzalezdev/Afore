package mx.secure.nci.ws.beans.request;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.business.vo.ActualizaEstatusCifVO;
import mx.secure.nci.business.vo.ActualizaEstatusIdcVO;
import mx.secure.nci.business.vo.ValidacionesVO;

public class ActualizaEstatusCifBeanRequest {

	private String folio;
	private String proceso;
	private String subproceso;
	private String tipoDevolucionsecure;
	private String usuario;
	private Date fechaLiquidacion;

	public ValidacionesVO validarRequest() {

		if (this.folio == null) {
			return new ValidacionesVO(Boolean.FALSE, "Folio requerido");
		}
		if (this.proceso == null) {
			return new ValidacionesVO(Boolean.FALSE, "Proceso requerido");
		}
		if (this.subproceso == null) {
			return new ValidacionesVO(Boolean.FALSE, "Subproceso requerido");
		}
		if (this.tipoDevolucionsecure == null) {
			return new ValidacionesVO(Boolean.FALSE, "Tipo de devolución secure requerido");
		}
		if (this.usuario == null) {
			return new ValidacionesVO(Boolean.FALSE, "Usuario requerido");
		}
		if (this.fechaLiquidacion == null) {
			return new ValidacionesVO(Boolean.FALSE, "Fecha de liquidación requerida");
		}
		
		
		if (this.folio.isEmpty()) {
			return new ValidacionesVO(Boolean.FALSE, "folio requerido");
		}
		if (this.proceso.isEmpty()) {
			return new ValidacionesVO(Boolean.FALSE, "Proceso requerido");
		}
		if (this.subproceso.isEmpty()) {
			return new ValidacionesVO(Boolean.FALSE, "Subproceso requerido");
		}
		if (this.tipoDevolucionsecure.isEmpty()) {
			return new ValidacionesVO(Boolean.FALSE, "Tipo de devolución secure requerido");
		}
		if (this.usuario.isEmpty()) {
			return new ValidacionesVO(Boolean.FALSE, "Usuario requerido");
		}
		if (this.fechaLiquidacion.toString().isEmpty()) {
			return new ValidacionesVO(Boolean.FALSE, "Fecha de liquidación requerida");
		}

		return new ValidacionesVO(Boolean.TRUE, "Validaci�n exitosa.",
				new ActualizaEstatusCifVO(folio, proceso, subproceso, tipoDevolucionsecure,
						usuario, fechaLiquidacion)); 
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getSubproceso() {
		return subproceso;
	}

	public void setSubproceso(String subproceso) {
		this.subproceso = subproceso;
	}

	public String getTipoDevolucionsecure() {
		return tipoDevolucionsecure;
	}

	public void setTipoDevolucionsecure(String tipoDevolucionsecure) {
		this.tipoDevolucionsecure = tipoDevolucionsecure;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	public void setFechaLiquidacion(Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

}
