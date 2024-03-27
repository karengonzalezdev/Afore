package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import mx.secure.nci.business.vo.ArchivosGeneradosDomiVO;
import mx.secure.nci.business.vo.CatalogosDomiVO;

@XmlType(propOrder={"codRet", "msgRet","desRet", "archivos"})
public class ArchivosGeneradosDomiBeanResponse {
	private String codRet;
	private String msgRet;
	private String desRet;
	private List <ArchivosGeneradosDomiVO> archivos;
	
	public ArchivosGeneradosDomiBeanResponse () {
		
	}
	
	/**
	 * CONSTRUCTOR Sobrecarga
	 * @param codRet
	 * @param msgRet
	 * @param desRet
	 */
	public ArchivosGeneradosDomiBeanResponse ( String codRet
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
	 * @param archivos
	 */
	public ArchivosGeneradosDomiBeanResponse ( String codRet
										     , String msgRet
										     , String desRet
										     , List <ArchivosGeneradosDomiVO> archivos) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.archivos = archivos;
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

	@XmlElementWrapper(name = "archivos")
	@XmlElement(name = "archivo")
	public List<ArchivosGeneradosDomiVO> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<ArchivosGeneradosDomiVO> archivos) {
		this.archivos = archivos;
	}
	
	

}
