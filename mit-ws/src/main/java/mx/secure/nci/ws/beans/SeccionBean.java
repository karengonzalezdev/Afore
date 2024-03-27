package mx.secure.nci.ws.beans;

import java.util.List;

public class SeccionBean {
	
	private String nombreSeccion;
	private List<ElementoBean> elementos;
	private ElementoBean totalConciliado;
	private ElementoBean totalNoConciliado;
	
	public String getNombreSeccion() {
		return nombreSeccion;
	}
	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}
	public List<ElementoBean> getElementos() {
		return elementos;
	}
	public void setElementos(List<ElementoBean> elementos) {
		this.elementos = elementos;
	}
	public ElementoBean getTotalConciliado() {
		return totalConciliado;
	}
	public void setTotalConciliado(ElementoBean totalConciliado) {
		this.totalConciliado = totalConciliado;
	}
	public ElementoBean getTotalNoConciliado() {
		return totalNoConciliado;
	}
	public void setTotalNoConciliado(ElementoBean totalNoConciliado) {
		this.totalNoConciliado = totalNoConciliado;
	}

}
