package mx.secure.nci.ws.beans.response;

import javax.xml.bind.annotation.XmlType;

import mx.secure.nci.business.vo.ProcesamientoDomiVO;

@XmlType(propOrder={"codRet", "msgRet","desRet", "procesamiento"})
public class TipoProcesamientoDomiResponse {
	private String codRet;
	private String msgRet;
	private String desRet;
	private ProcesamientoDomiVO procesamiento;
	
	public TipoProcesamientoDomiResponse () {
		
	}
	
	/**
	 * CONSTRUCTOR Sobrecarga
	 * @param codRet
	 * @param msgRet
	 * @param desRet
	 */
	public TipoProcesamientoDomiResponse ( String codRet 
										 , String msgRet
										 , String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}
	
	/**
	 * CONSTRUCTOR Sobrecarga
	 * @param codRet
	 * @param msgRet
	 * @param desRet
	 * @param procesamiento
	 */
	public TipoProcesamientoDomiResponse ( String codRet 
										 , String msgRet
										 , String desRet
										 , ProcesamientoDomiVO procesamiento) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.procesamiento = procesamiento;
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

	public ProcesamientoDomiVO getProcesamiento() {
		return procesamiento;
	}

	public void setProcesamiento(ProcesamientoDomiVO procesamiento) {
		this.procesamiento = procesamiento;
	}
	
	

}
