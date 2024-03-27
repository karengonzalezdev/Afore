package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.business.vo.LoteControlNciVO;

public class DevolucionesListaEstatusBeanResponse {

	private String codRet;
	private String msgRet;
	private String desRet;
	private List<LoteControlNciVO> ltsLotes;
	
	public DevolucionesListaEstatusBeanResponse() {}
	
	public DevolucionesListaEstatusBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}
	
	public DevolucionesListaEstatusBeanResponse(String codRet, String msgRet, String desRet,
			List<LoteControlNciVO> ltsLotes) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.ltsLotes = ltsLotes;
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
	
	@XmlElementWrapper(name="listaLotes")
	@XmlElement(name = "lote")
	public List<LoteControlNciVO> getLtsLotes() {
		return ltsLotes;
	}

	public void setLtsLotes(List<LoteControlNciVO> ltsLotes) {
		this.ltsLotes = ltsLotes;
	}
	
}
