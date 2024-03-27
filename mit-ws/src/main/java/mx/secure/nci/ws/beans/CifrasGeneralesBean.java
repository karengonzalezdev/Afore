package mx.secure.nci.ws.beans;

import java.util.List;

public class CifrasGeneralesBean {

	private List<SeccionBean> secciones;
	private ElementoBean totalGeneralConciliado;
	private ElementoBean totalGeneralNoConciliado;

	public List<SeccionBean> getSecciones() {
		return secciones;
	}

	public void setSecciones(List<SeccionBean> secciones) {
		this.secciones = secciones;
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
