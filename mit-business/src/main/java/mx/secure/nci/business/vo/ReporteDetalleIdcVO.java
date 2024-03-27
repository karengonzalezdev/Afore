package mx.secure.nci.business.vo;

import java.io.Serializable;
import java.util.Date;

public class ReporteDetalleIdcVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String folio;
	private Integer numConsecutivo;
	private String nss;
	private String curp;
	private String numeroCuenta;
	private String nombreCompleto;
	private String diagnosticoIdc;
	private String subProcesoNoVigencia;
	private Date fechaCertificacion;
	
	
	public ReporteDetalleIdcVO() {}
	
	public ReporteDetalleIdcVO(String folio) {
		this.folio = folio;
	}
	
	public String getFolio() {
		return folio;
	}
	
	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	public Integer getNumConsecutivo() {
		return numConsecutivo;
	}

	public void setNumConsecutivo(Integer numConsecutivo) {
		this.numConsecutivo = numConsecutivo;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getDiagnosticoIdc() {
		return diagnosticoIdc;
	}

	public void setDiagnosticoIdc(String diagnosticoIdc) {
		this.diagnosticoIdc = diagnosticoIdc;
	}

	public String getSubProcesoNoVigencia() {
		return subProcesoNoVigencia;
	}

	public void setSubProcesoNoVigencia(String subProcesoNoVigencia) {
		this.subProcesoNoVigencia = subProcesoNoVigencia;
	}

	public Date getFechaCertificacion() {
		return fechaCertificacion;
	}

	public void setFechaCertificacion(Date fechaCertificacion) {
		this.fechaCertificacion = fechaCertificacion;
	}
}
