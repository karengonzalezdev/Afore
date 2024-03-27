package mx.secure.nci.ws.beans.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import mx.secure.nci.business.vo.ArchivosDomiciliacionVO;

/**
 * Clase tipo response
 * @author Manuel Loza
 * @category MANTENIMIENTO
 * @date 01/02/2022
 */
@XmlType(propOrder={"codRet", "msgRet","desRet", "archivos", "infoReporte"})
public class ArchivosDomiciliacionBeanResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codRet;
	private String msgRet;
	private String desRet;
	private List<ArchivosDomiciliacionVO> archivos;
	private String infoReporte;
	
	/**
	 * Constructor
	 * @param codRet
	 * @param msgRet
	 * @param desRet
	 * @param archivos
	 */
	public ArchivosDomiciliacionBeanResponse(String codRet, String msgRet, String desRet,
			List<ArchivosDomiciliacionVO> archivos) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.archivos = archivos;
	}
	
	/**
	 * Constructor
	 * @param codRet
	 * @param msgRet
	 * @param desRet
	 */
	public ArchivosDomiciliacionBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}
	
	/**
	 * Constructor
	 */
	public ArchivosDomiciliacionBeanResponse() {
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

	public String getInfoReporte() {
		return infoReporte;
	}

	public void setInfoReporte(String infoReporte) {
		this.infoReporte = infoReporte;
	}

	@XmlElementWrapper(name = "archivos")
	@XmlElement(name = "archivo")
	public List<ArchivosDomiciliacionVO> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<ArchivosDomiciliacionVO> archivos) {
		this.archivos = archivos;
	}
	
}
