package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.ws.beans.ArchivoBean;



public class ArchivoConsultarBeanResponse {

	private List<ArchivoBean> listaArchivos;

	
	
	@XmlElementWrapper(name = "archivos")
	@XmlElement(name = "archivo")
	public List<ArchivoBean> getListaArchivos() {
		return listaArchivos;
	}

	public void setListaArchivos(List<ArchivoBean> listaArchivos) {
		this.listaArchivos = listaArchivos;
	}

}
