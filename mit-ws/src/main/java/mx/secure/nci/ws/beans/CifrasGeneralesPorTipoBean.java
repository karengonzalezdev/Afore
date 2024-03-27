package mx.secure.nci.ws.beans;

import java.util.List;

public class CifrasGeneralesPorTipoBean {
	
	private List<SeccionBean> seccionesCortoPlazo;
	private List<SeccionBean> seccionesLargoPlazo;
	private ElementoBean totalGeneralConciliado;
	private ElementoBean totalGeneralNoConciliado;
	private String nombreSeccion;
	
	public String getNombreSeccion() {
		return nombreSeccion;
	}
	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}
	public List<SeccionBean> getSeccionesCortoPlazo() {
		return seccionesCortoPlazo;
	}
	public void setSeccionesCortoPlazo(List<SeccionBean> seccionesCortoPlazo) {
		this.seccionesCortoPlazo = seccionesCortoPlazo;
	}
	public List<SeccionBean> getSeccionesLargoPlazo() {
		return seccionesLargoPlazo;
	}
	public void setSeccionesLargoPlazo(List<SeccionBean> seccionesLargoPlazo) {
		this.seccionesLargoPlazo = seccionesLargoPlazo;
	}
	public ElementoBean getTotalGeneralConciliado() {
		return totalGeneralConciliado;
	}
	public void setTotalGeneralConciliado(ElementoBean totalGeneralConciliado) {
		this.totalGeneralConciliado = totalGeneralConciliado;
	}
	public ElementoBean getTotalGeneralNoConciliado() {
		return totalGeneralNoConciliado;
	}
	public void setTotalGeneralNoConciliado(ElementoBean totalGeneralNoConciliado) {
		this.totalGeneralNoConciliado = totalGeneralNoConciliado;
	}

}
