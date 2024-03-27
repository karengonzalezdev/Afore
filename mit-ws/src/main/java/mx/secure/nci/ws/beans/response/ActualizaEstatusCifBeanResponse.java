package mx.secure.nci.ws.beans.response;

public class ActualizaEstatusCifBeanResponse {

	private String codRet;
	private String msgRet;
	private String desRet;
	private Integer rgsAct;
	
	public Integer getRgsAct() {
		return rgsAct;
	}

	public void setRgsAct(Integer rgsAct) {
		this.rgsAct = rgsAct;
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
	
	public ActualizaEstatusCifBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}

	public ActualizaEstatusCifBeanResponse(String codRet, String msgRet, String desRet, Integer rgsAct) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.rgsAct = rgsAct;
	}

	public ActualizaEstatusCifBeanResponse() {
	}
}
