package mx.secure.nci.ws.beans.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * Clase tipo response
 * 
 * @author E0000548 | Héctor Manuel Loza Cruz
 * @category MANTENIMIENTO
 * @date 20/04/2022
 */

@XmlType(propOrder={"codRet", "msgRet", "desRet", "tipoProc"})
public class TipoProcesamientoDomisBeanResponse implements Serializable{

	private static final long serialVersionUID = -884403652270914494L;
	private String codRet;
	private String msgRet;
	private String desRet;
	private String tipoProc;
	
	/**
	 * Constructor
	 */
	public TipoProcesamientoDomisBeanResponse() {
	}
	
	/**
	 * Constructor
	 * @param codRet
	 * @param msgRet
	 * @param desRet
	 * @param tipoProc
	 */
	public TipoProcesamientoDomisBeanResponse(String codRet, String msgRet, String desRet, String tipoProc) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.tipoProc = tipoProc;
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
	
	public String getTipoProc() {
		return tipoProc;
	}
	
	public void setTipoProc(String tipoProc) {
		this.tipoProc = tipoProc;
	}

}
