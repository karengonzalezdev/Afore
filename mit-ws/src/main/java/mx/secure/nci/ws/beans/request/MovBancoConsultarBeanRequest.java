package mx.secure.nci.ws.beans.request;

import java.util.List;

public class MovBancoConsultarBeanRequest {
	private Long idBanco;
	private String folio;
	private Long numCuenta;
	private Short claveBanco;
	private List<Short> listaAportaciones;
	private Short regConciliado;
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public Long getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(Long numCuenta) {
		this.numCuenta = numCuenta;
	}
	public Short getClaveBanco() {
		return claveBanco;
	}
	public void setClaveBanco(Short claveBanco) {
		this.claveBanco = claveBanco;
	}
	
	public Long getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}
	
	public Short getRegConciliado() {
		return regConciliado;
	}
	public void setRegConciliado(Short regConciliado) {
		this.regConciliado = regConciliado;
	}
	public List<Short> getListaAportaciones() {
		return listaAportaciones;
	}
	public void setListaAportaciones(List<Short> listaAportaciones) {
		this.listaAportaciones = listaAportaciones;
	}
	
	
}
