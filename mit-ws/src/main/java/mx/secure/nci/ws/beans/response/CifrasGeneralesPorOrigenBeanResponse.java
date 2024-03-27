package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.ws.beans.CifrasGeneralesBean;
import mx.secure.nci.ws.beans.ElementoBean;

public class CifrasGeneralesPorOrigenBeanResponse {
	
	private List<CifrasGeneralesBean> cifrasGeneralesBean;
	private ElementoBean totalGeneralConciliado;
	private ElementoBean totalGeneralNoConciliado;

	public CifrasGeneralesPorOrigenBeanResponse(){}

	public CifrasGeneralesPorOrigenBeanResponse(List<CifrasGeneralesBean> cifrasGeneralesBean)
	{
		super();
		this.cifrasGeneralesBean = cifrasGeneralesBean;
	}
	
	@XmlElementWrapper(name = "cifrasGenerales")
	@XmlElement(name = "cifraGeneral")
	public List<CifrasGeneralesBean> getCifrasGeneralesPorOrigen() {
		return cifrasGeneralesBean;
	}

	public void setCifrasGeneralesPorOrigen(
			List<CifrasGeneralesBean> cifrasGeneralesPorOrigen) {
		this.cifrasGeneralesBean = cifrasGeneralesPorOrigen;
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
