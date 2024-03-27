package mx.secure.nci.ws.beans.request;

import java.util.Date;


import mx.secure.nci.business.vo.ValidacionesVO;

public class CifrasLiquidacionBeanRequest {
	private Date fechaInicio;
	private Date fechaFin;
	private String usuario;

	public CifrasLiquidacionBeanRequest() {
		super();
	}

	public CifrasLiquidacionBeanRequest(Date fechaInicio, Date fechaFin) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public ValidacionesVO validaRequest() {
		if (fechaInicio == null) {
			return new ValidacionesVO(Boolean.FALSE, "Se debe recibir una fecha inicio");
		}
		if (fechaFin == null) {
			return new ValidacionesVO(Boolean.FALSE, "Se debe recibir una fecha fin");
		}

		return new ValidacionesVO(Boolean.TRUE, "Validaciones correctas");
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
}
