package mx.secure.nci.business.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import mx.secure.nci.business.util.UtilValidate;

public class DetalleMovimientosCifVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String CURRENCY_PATTERN = "$###,###,##0.00";
	private static final String COUNT_PATTERN = "###,###,##0";
	
	private String folio;
	private String nombreProceso;
	private String cveSubProceso;
	private String nombreSubProceso;
	private Integer casosOperados;
	private BigDecimal importePesos;
	private String casosOperadosFormat;
	private String importePesosFormat;
	
	public DetalleMovimientosCifVO() {}
	
	public DetalleMovimientosCifVO(String folio) {
		this.folio = folio;
	}
	
	public String getFolio() {
		return folio;
	}
	
	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	public String getCveSubProceso() {
		return cveSubProceso;
	}

	public void setCveSubProceso(String cveSubProceso) {
		this.cveSubProceso = cveSubProceso;
	}

	public String getNombreSubProceso() {
		return nombreSubProceso;
	}

	public void setNombreSubProceso(String nombreSubProceso) {
		this.nombreSubProceso = nombreSubProceso;
	}

	public Integer getCasosOperados() {
		return casosOperados;
	}

	public void setCasosOperados(Integer casosOperados) {
		this.casosOperados = casosOperados;
	}

	public BigDecimal getImportePesos() {
		return importePesos;
	}

	public void setImportePesos(BigDecimal importePesos) {
		this.importePesos = importePesos;
	}

	public String getCasosOperadosFormat() {
		return UtilValidate.parseString(COUNT_PATTERN, this.casosOperados);
	}

	public void setCasosOperadosFormat(String casosOperadosFormat) {
		this.casosOperadosFormat = casosOperadosFormat;
	}

	public String getImportePesosFormat() {
		return UtilValidate.parseString(CURRENCY_PATTERN, this.importePesos);
	}

	public void setImportePesosFormat(String importePesosFormat) {
		this.importePesosFormat = importePesosFormat;
	}
}
