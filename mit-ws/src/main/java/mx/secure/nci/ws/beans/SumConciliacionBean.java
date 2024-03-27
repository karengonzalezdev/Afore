package mx.secure.nci.ws.beans;

import java.math.BigDecimal;

public class SumConciliacionBean {
	private String folio;
	private GenericoCatalogoBean origen;
	private BigDecimal importe;
	private GenericoCatalogoBean empresa;
	private String usuario;
	private Short regConciliado;

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public GenericoCatalogoBean getOrigen() {
		return origen;
	}

	public void setOrigen(GenericoCatalogoBean origen) {
		this.origen = origen;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public GenericoCatalogoBean getEmpresa() {
		return empresa;
	}

	public void setEmpresa(GenericoCatalogoBean empresa) {
		this.empresa = empresa;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Short getRegConciliado() {
		return regConciliado;
	}

	public void setRegConciliado(Short regConciliado) {
		this.regConciliado = regConciliado;
	}
	
}
