package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.ws.beans.CifrasGeneralesBean;

public class CifrasGeneralesPorSBCBeanResponse {
	
	private List<CifrasGeneralesBean> cifrasGeneralesBean;

	public CifrasGeneralesPorSBCBeanResponse(){}
	
	public CifrasGeneralesPorSBCBeanResponse(List<CifrasGeneralesBean> cifrasGeneralesBean)
	{
		super();
		this.cifrasGeneralesBean = cifrasGeneralesBean;
	}
	@XmlElementWrapper(name = "cifrasGenerales")
	@XmlElement(name = "cifraGeneral")
	public List<CifrasGeneralesBean> getCifrasGeneralesBean() {
		return cifrasGeneralesBean;
	}

	public void setCifrasGeneralesBean(List<CifrasGeneralesBean> cifrasGeneralesBean) {
		this.cifrasGeneralesBean = cifrasGeneralesBean;
	}

}
