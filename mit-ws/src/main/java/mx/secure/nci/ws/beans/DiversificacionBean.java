package mx.secure.nci.ws.beans;

import java.math.BigDecimal;

public class DiversificacionBean {
	private String folio;
	private GenericoCatalogoBean fondoAportacionVoluntaria;
	private BigDecimal monto;
	private Short porcentaje;

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public GenericoCatalogoBean getFondoAportacionVoluntaria() {
		return fondoAportacionVoluntaria;
	}

	public void setFondoAportacionVoluntaria(GenericoCatalogoBean fondoAportacionVoluntaria) {
		this.fondoAportacionVoluntaria = fondoAportacionVoluntaria;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Short getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Short porcentaje) {
		this.porcentaje = porcentaje;
	}

}
