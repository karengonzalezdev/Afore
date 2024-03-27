package mx.secure.nci.ws.beans.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.business.vo.PendientesTimbradoCifVO;

import java.util.List;

public class PendientesTimbradoCifBeanResponse {

	private String codRet;
	private String msgRet;
	private String desRet;
	private List<PendientesTimbradoCifVO> lstDevolucionessecure;

	public PendientesTimbradoCifBeanResponse() {
	}

	public PendientesTimbradoCifBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}

	public PendientesTimbradoCifBeanResponse(String codRet, String msgRet, String desRet,
			List<PendientesTimbradoCifVO> lstDevolucionessecure) {
		super();
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.lstDevolucionessecure = lstDevolucionessecure;
	}

	@XmlElementWrapper(name = "lstDevolucionessecure")
	@XmlElement(name = "devolucionessecure")
	public List<PendientesTimbradoCifVO> getLstDevolucionessecure() {
		return lstDevolucionessecure;
	}

	public void setLstDevolucionessecure(List<PendientesTimbradoCifVO> lstDevolucionessecure) {
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
