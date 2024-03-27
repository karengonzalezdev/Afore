package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import mx.secure.nci.business.vo.ArchivoDomiVO;

@XmlType(propOrder={"codRet", "msgRet","desRet", "folio","archivos"})
public class GeneracionArchivosDomiBeanResponse {
	private String codRet;
	private String msgRet;
	private String desRet;
	private String folio;
	private List <ArchivoDomiVO> archivos;
	
	
	public GeneracionArchivosDomiBeanResponse () {
		
	}
	
	/**
	 * CONSTRUCTOR Sobrecarga
	 * @param codRet
	 * @param msgRet
	 * @param desRet
	 */
	public GeneracionArchivosDomiBeanResponse ( String codRet
								  			  , String msgRet
								  			  , String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}
	
	public GeneracionArchivosDomiBeanResponse ( String codRet
										      , String msgRet
										      , String desRet
										      , List <ArchivoDomiVO> archivos
										      , String folio) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.archivos = archivos;
		this.folio = folio;
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
	
	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@XmlElementWrapper(name = "archivos")
	@XmlElement(name = "archivo")
	public List<ArchivoDomiVO> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<ArchivoDomiVO> archivos) {
		this.archivos = archivos;
	}
	
	
}
