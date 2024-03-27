package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.ws.beans.SumConciliacionBean;

public class SumConciliaConsultarBeanResponse {
	private List<SumConciliacionBean> lista;

	public SumConciliaConsultarBeanResponse() {
	}

	public SumConciliaConsultarBeanResponse(List<SumConciliacionBean> lista) {
		super();
		this.lista = lista;
	}

	@XmlElementWrapper(name = "sumConciliaciones")
	@XmlElement(name = "sumConciliacion")
	public List<SumConciliacionBean> getLista() {
		return lista;
	}

	public void setLista(List<SumConciliacionBean> lista) {
		this.lista = lista;
	}

}
