package mx.secure.nci.ws.beans.request;

import java.util.Date;


import mx.secure.nci.business.vo.ValidacionesVO;

public class CifrasLiquidacionExportableBeanRequest {
	private String fechaInicio;
	private String fechaFin;
	private String usuario;

	public CifrasLiquidacionExportableBeanRequest() {
		super();
	}

	public CifrasLiquidacionExportableBeanRequest(String fechaInicio, String fechaFin) {
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

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
}
