package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.business.vo.cifctrlreclasificacion.CCOrigDestReclasificacionVO;

public class CifCtrlOrigDestReclasificacionBeanResponse {

	private List<CCOrigDestReclasificacionVO> listaMovimientos;
	
	@XmlElementWrapper(name = "listaMovimientos")
	@XmlElement(name = "movimiento")
	public List<CCOrigDestReclasificacionVO> getListaMovimientos() {
		return listaMovimientos;
	}
	
	public void setListaMovimientos(List<CCOrigDestReclasificacionVO> listaMovimientos) {
		this.listaMovimientos = listaMovimientos;
	}
	
}
