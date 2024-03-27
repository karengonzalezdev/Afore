package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import mx.secure.nci.business.vo.CifrasTotalesDomiVO;
import mx.secure.nci.business.vo.RegistroCifrasDomiVO;

@XmlType(propOrder={"codRet", "msgRet","desRet", "cifrasTotales", "registros"})
public class CifrasDomiBeanResponse {
	private String codRet;
	private String msgRet;
	private String desRet;
	private List<CifrasTotalesDomiVO> cifrasTotales;
	private List <RegistroCifrasDomiVO> registros;
	
	public CifrasDomiBeanResponse () {
		
	}
	
	/**
	 * CONSTRUCTOR Sobrecarga
	 * @param codRet
	 * @param msgRet
	 * @param desRet
	 */
	public CifrasDomiBeanResponse ( String codRet
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
	 * @param cifrasTotales
	 * @param registros
	 */
	public CifrasDomiBeanResponse ( String codRet
								  , String msgRet
								  , String desRet
								  , List<CifrasTotalesDomiVO> cifrasTotales
								  , List <RegistroCifrasDomiVO> registros) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.cifrasTotales = cifrasTotales;
		this.registros = registros;
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


	@XmlElementWrapper(name = "cifrasTotales")
	@XmlElement(name = "cifraTotal")
	public List<CifrasTotalesDomiVO> getCifrasTotales() {
		return cifrasTotales;
	}

	public void setCifrasTotales(List<CifrasTotalesDomiVO> cifrasTotales) {
		this.cifrasTotales = cifrasTotales;
	}

	@XmlElementWrapper(name = "cifras")
	@XmlElement(name = "cifra")
	public List<RegistroCifrasDomiVO> getRegistros() {
		return registros;
	}

	public void setRegistros(List<RegistroCifrasDomiVO> registros) {
		this.registros = registros;
	}
}
