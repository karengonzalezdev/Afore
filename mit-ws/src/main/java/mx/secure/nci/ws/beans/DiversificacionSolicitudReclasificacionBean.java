package mx.secure.nci.ws.beans;

import java.math.BigDecimal;

public class DiversificacionSolicitudReclasificacionBean {
	private Long idSiefore;
	private Long idSubcta;
	private BigDecimal pesos;
	private BigDecimal acciones;
	private BigDecimal porcentaje;
	private String idValorAccion;
	private Short idTipoMov;
	private Short idFondoApp;
	private Short deducible;
	private Short plazo;
	
	public Short getPlazo() {
		return plazo;
	}

	public void setPlazo(Short plazo) {
		this.plazo = plazo;
	}

	public Short getDeducible() {
		return deducible;
	}

	public void setDeducible(Short deducible) {
		this.deducible = deducible;
	}

	
	
	
	public Long getIdSiefore() {
		return idSiefore;
	}

	public void setIdSiefore(Long idSiefore) {
		this.idSiefore = idSiefore;
	}

	public Long getIdSubcta() {
		return idSubcta;
	}

	public void setIdSubcta(Long idSubcta) {
		this.idSubcta = idSubcta;
	}

	public BigDecimal getPesos() {
		return pesos;
	}

	public void setPesos(BigDecimal pesos) {
		this.pesos = pesos;
	}

	public BigDecimal getAcciones() {
		return acciones;
	}

	public void setAcciones(BigDecimal acciones) {
		this.acciones = acciones;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getIdValorAccion() {
		return idValorAccion;
	}

	public void setIdValorAccion(String idValorAccion) {
		this.idValorAccion = idValorAccion;
	}

	public Short getIdTipoMov() {
		return idTipoMov;
	}

	public void setIdTipoMov(Short idTipoMov) {
		this.idTipoMov = idTipoMov;
	}
	
	public Short getIdFondoApp() {
		return idFondoApp;
	}
	
	public void setIdFondoApp(Short idFondoApp) {
		this.idFondoApp = idFondoApp;
	}

	@Override
	public String toString() {
		return "DiversificacionSolicitudReclasificacionBean [idSiefore=" + idSiefore + ", idSubcta=" + idSubcta
				+ ", pesos=" + pesos + ", acciones=" + acciones + ", porcentaje=" + porcentaje + ", idValorAccion="
				+ idValorAccion + ", idTipoMov=" + idTipoMov + ", idFondoApp=" + idFondoApp + "]";
	}
	
}
