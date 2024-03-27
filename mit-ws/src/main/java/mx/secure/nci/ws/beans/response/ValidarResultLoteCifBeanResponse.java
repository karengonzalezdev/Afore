package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import mx.secure.nci.business.vo.DevolucionessecureCifVO;

public class ValidarResultLoteCifBeanResponse {

	private String codRet;
	private String msgRet;
	private String desRet;
	private List<DevolucionessecureCifVO> lstDevolucionessecure;
	
	public ValidarResultLoteCifBeanResponse() {}
	
	public ValidarResultLoteCifBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}

	public ValidarResultLoteCifBeanResponse(String codRet, String msgRet, String desRet,
			List<DevolucionessecureCifVO> lstDevolucionessecure) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.lstDevolucionessecure = lstDevolucionessecure;
	}
	
	

	@XmlElementWrapper(name="listaDevolucionesAp")
	@XmlElement(name = "validarResultLoteCif")
	public List<DevolucionessecureCifVO> getLstDevolucionessecure() {
		return lstDevolucionessecure;
	}

	public void setLstDevolucionessecure(List<DevolucionessecureCifVO> lstDevolucionessecure) {
		this.lstDevolucionessecure = lstDevolucionessecure;
	}

	public String getCodRet() {
		return codRet;
	}
	public void setCodRet(String codRet) {
		this.codRet = codRet;
	}
	public String getMsgRet() {
		return msgRet;
	}
	public void setMsgRet(String msgRet) {
		this.msgRet = msgRet;
	}
	public String getDesRet() {
		return desRet;
	}
	public void setDesRet(String desRet) {
		this.desRet = desRet;
	}
	
	
	
}
