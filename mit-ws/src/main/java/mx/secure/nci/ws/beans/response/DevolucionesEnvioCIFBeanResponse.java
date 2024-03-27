package mx.secure.nci.ws.beans.response;

public class DevolucionesEnvioCIFBeanResponse {
	
	private String codRet;
	private String msgRet;
	private String desRet;
	private Integer regInst;

	public DevolucionesEnvioCIFBeanResponse() {
	}	
	
	public DevolucionesEnvioCIFBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}	
	
	public DevolucionesEnvioCIFBeanResponse(String codRet, String msgRet, String desRet, Integer regInst) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.regInst = regInst;
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
	
	public Integer getRegInst() {
		return regInst;
	}
	
	public void setRegInst(Integer regInst) {
		this.regInst = regInst;
	}

}
