package mx.secure.nci.business.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ArchivoDetalleCifVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String folio;
	private String portafolio;
	private Integer entradas;
	private Integer numRegEnt;
	private BigDecimal salidas;
	private Integer numRegSal;
	private Integer estatus;
	
	public ArchivoDetalleCifVO() {}

	public ArchivoDetalleCifVO(String folio) {
		this.folio = folio;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getPortafolio() {
		return portafolio;
	}

	public void setPortafolio(String portafolio) {
		this.portafolio = portafolio;
	}

	public Integer getEntradas() {
		return entradas;
	}

	public void setEntradas(Integer entradas) {
		this.entradas = entradas;
	}

	public Integer getNumRegEnt() {
		return numRegEnt;
	}

	public void setNumRegEnt(Integer numRegEnt) {
		this.numRegEnt = numRegEnt;
	}

	public BigDecimal getSalidas() {
		return salidas;
	}

	public void setSalidas(BigDecimal salidas) {
		this.salidas = salidas;
	}

	public Integer getNumRegSal() {
		return numRegSal;
	}

	public void setNumRegSal(Integer numRegSal) {
		this.numRegSal = numRegSal;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
}
