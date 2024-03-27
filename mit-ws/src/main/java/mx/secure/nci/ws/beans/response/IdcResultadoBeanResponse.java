package mx.secure.nci.ws.beans.response;

import mx.secure.nci.business.vo.ResultadoIdcVO;

public class IdcResultadoBeanResponse {
	
	/**
	 * @author Montserrat Rodriguez
	 * @date 16/04/2023
	 * OP-1990-MT
	 */
	
	private String codRet ;
	private String msgRet;
	private String desRet;
	private ResultadoIdcVO resultadoIdc;



	public IdcResultadoBeanResponse() {}


	public IdcResultadoBeanResponse(String codRet, String msgRet, String desRet, ResultadoIdcVO resultadoIdc) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.resultadoIdc = resultadoIdc;	
	}
	
	
	public IdcResultadoBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}

	public ResultadoIdcVO getResultadoIdc() {
		return resultadoIdc;
	}

	public void setResultadoIdc(ResultadoIdcVO resultadoIdc) {
		this.resultadoIdc = resultadoIdc;
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
