package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.business.vo.DevolucionesVO;

public class DevolucionesConsultaListaBeanResponse {
	
	private String codRet;
	private String msgRet;
	private String desRet;
	private List<DevolucionesVO> ltsDevoluciones;
	private String strLtsDevoluciones;
	
	public DevolucionesConsultaListaBeanResponse() {}

	public DevolucionesConsultaListaBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}
	
	public DevolucionesConsultaListaBeanResponse(String codRet, String msgRet, String desRet,
			List<DevolucionesVO> ltsDevoluciones) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.ltsDevoluciones = ltsDevoluciones;
	}

	public DevolucionesConsultaListaBeanResponse(String codRet, String msgRet, String desRet,
			List<DevolucionesVO> ltsDevoluciones, String strLtsDevoluciones) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.ltsDevoluciones = ltsDevoluciones;
		this.strLtsDevoluciones = strLtsDevoluciones;
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
	
	@XmlElementWrapper(name="listaDevoluciones")
	@XmlElement(name = "devolucion")	
	public List<DevolucionesVO> getLtsDevoluciones() {
		return ltsDevoluciones;
	}
	
	public void setLtsDevoluciones(List<DevolucionesVO> ltsDevoluciones) {
		this.ltsDevoluciones = ltsDevoluciones;
	}

	public String getStrLtsDevoluciones() {
		return strLtsDevoluciones;
	}

	public void setStrLtsDevoluciones(String strLtsDevoluciones) {
		this.strLtsDevoluciones = strLtsDevoluciones;
	}

}
