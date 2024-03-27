package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.ws.beans.PrincipalBean;

public class PrincipalConsultarBeanResponse {
		
	public PrincipalConsultarBeanResponse(){}
	
	public PrincipalConsultarBeanResponse(
			List<PrincipalBean> listaPrincipal) {
		super();
		this.listaPrincipal = listaPrincipal;
	}
	
	private List<PrincipalBean> listaPrincipal;
	
	@XmlElementWrapper(name = "lstPrincipal")
	@XmlElement(name = "principal")
	public List<PrincipalBean> getListaPrincipal() {
		return listaPrincipal;
	}
	
	public void setListaPrincipal(List<PrincipalBean> listaPrincipal) {
		this.listaPrincipal = listaPrincipal;
	}	
}