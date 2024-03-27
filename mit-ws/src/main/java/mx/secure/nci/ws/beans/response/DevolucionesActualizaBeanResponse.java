package mx.secure.nci.ws.beans.response;

public class DevolucionesActualizaBeanResponse {

	private String codRet;
	private String msgRet;
	private String desRet;
	private String regAct;
	
	public DevolucionesActualizaBeanResponse() {}
	
	public DevolucionesActualizaBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}

	public DevolucionesActualizaBeanResponse(String codRet, String msgRet, String desRet, String regAct) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.regAct = regAct;
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
	public String getRegAct() {
		return regAct;
	}
	public void setRegAct(String regAct) {
		this.regAct = regAct;
	}
	
	
}
