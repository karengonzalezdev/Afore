package mx.secure.nci.ws.beans.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import mx.secure.nci.business.vo.RegistrosArchivoVO;

/**
 * Clase tipo response
 * @author Manuel Loza
 * @category MANTENIMIENTO
 * @date 09/05/2022
 */
@XmlType(propOrder={"codRet", "msgRet","desRet", "archivos", "infoReporte"})
public class RegistrosArchivoBeanResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codRet;
	private String msgRet;
	private String desRet;
	private List<RegistrosArchivoVO> archivos;
	private String infoReporte;
	
	/**
	 * Constructor
	 */
	public RegistrosArchivoBeanResponse() {
	}
	
	/**
	 * Constructor
	 */
	public RegistrosArchivoBeanResponse(String codRet, String msgRet, String desRet,
			List<RegistrosArchivoVO> archivos) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.archivos = archivos;
	}
	
	/**
	 * Constructor
	 */
	public RegistrosArchivoBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
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
	public List<RegistrosArchivoVO> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<RegistrosArchivoVO> archivos) {
		this.archivos = archivos;
	}
}
