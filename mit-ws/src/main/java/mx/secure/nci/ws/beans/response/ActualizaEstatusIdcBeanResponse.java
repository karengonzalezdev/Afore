package mx.secure.nci.ws.beans.response;

public class ActualizaEstatusIdcBeanResponse {

	
	private String codRet;
	private String msgRet;
	private String desRet;
	private Integer regAct;
	
	public Integer getRegAct() {
		return regAct;
	}

	public void setRegAct(Integer regAct) {
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
	
	public ActualizaEstatusIdcBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}

	public ActualizaEstatusIdcBeanResponse(String codRet, String msgRet, String desRet, Integer regAct) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.regAct = regAct;
	}

	public ActualizaEstatusIdcBeanResponse() {
	}
}
