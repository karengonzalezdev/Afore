package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.business.vo.cifctrlreclasificacion.CCTotalSubcuentaReclasificacionVO;

public class CifCtrlReclasificacionBeanResponse {
	
	private List<CCTotalSubcuentaReclasificacionVO> listaTotalSubcuentas;
	
	@XmlElementWrapper(name = "totalSubcuentas")
	@XmlElement(name = "subCuenta")
	public List<CCTotalSubcuentaReclasificacionVO> getListaTotalSubcuentas() {
		return listaTotalSubcuentas;
	}
	
	public void setListaTotalSubcuentas(List<CCTotalSubcuentaReclasificacionVO> listaTotalSubcuentas) {
		this.listaTotalSubcuentas = listaTotalSubcuentas;
	}
	
}
