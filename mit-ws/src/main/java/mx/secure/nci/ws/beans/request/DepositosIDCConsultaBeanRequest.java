package mx.secure.nci.ws.beans.request;

import java.util.Date;

public class DepositosIDCConsultaBeanRequest {
	
	private Date fechaInicio;
	private Date fechaFin;
	private String referencia;
	private String banco;
	private Integer estatusIDC;
	
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
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public Integer getEstatusIDC() {
		return estatusIDC;
	}
	public void setEstatusIDC(Integer estatusIDC) {
		this.estatusIDC = estatusIDC;
	}


}
