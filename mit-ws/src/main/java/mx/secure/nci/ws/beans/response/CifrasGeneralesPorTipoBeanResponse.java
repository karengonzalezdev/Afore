package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.ws.beans.CifrasGeneralesPorTipoBean;
import mx.secure.nci.ws.beans.ElementoBean;

public class CifrasGeneralesPorTipoBeanResponse {
	
	private List<CifrasGeneralesPorTipoBean> cifrasGeneralesPorTipo;
	private ElementoBean totalGeneralConciliado;
	private ElementoBean totalGeneralNoConciliado;

	public CifrasGeneralesPorTipoBeanResponse(){
		
	}
	public CifrasGeneralesPorTipoBeanResponse(List<CifrasGeneralesPorTipoBean> cifrasGeneralesPorTipo)
	{
		super();
		this.cifrasGeneralesPorTipo = cifrasGeneralesPorTipo;
	}
	@XmlElementWrapper(name = "cifrasGenerales")
	@XmlElement(name = "cifraGeneral")
	public List<CifrasGeneralesPorTipoBean> getCifrasGeneralesPorTipo() {
		return cifrasGeneralesPorTipo;
	}

	public void setCifrasGeneralesPorTipo(
			List<CifrasGeneralesPorTipoBean> cifrasGeneralesPorTipo) {
		this.cifrasGeneralesPorTipo = cifrasGeneralesPorTipo;
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
