package mx.secure.nci.ws.beans.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * Clase tipo response
 * @author E0000548
 * @category MANTENIMIENTO
 * @date 28/03/2022
 */
@XmlType(propOrder={"codRet", "msgRet","desRet", "contenido"})
public class TablaDinamicaArchDomiBeanResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codRet;
	private String msgRet;
	private String desRet;
	private String contenido;
	
	/**
	 * Constructor
	 */
	public TablaDinamicaArchDomiBeanResponse() {
	}
	
	/**
	 * Constructor
	 * @param codRet
	 * @param msgRet
	 * @param desRet
	 * @param contenido
	 */
	public TablaDinamicaArchDomiBeanResponse(String codRet, String msgRet, String desRet, String contenido) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.contenido = contenido;
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

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
}
