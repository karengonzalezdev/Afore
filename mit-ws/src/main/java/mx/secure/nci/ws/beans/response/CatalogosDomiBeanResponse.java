package mx.secure.nci.ws.beans.response;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import mx.secure.nci.business.vo.CatalogosDomiVO;

@XmlType(propOrder={"codRet", "msgRet","desRet", "valores"})
public class CatalogosDomiBeanResponse {
	private String codRet;
	private String msgRet;
	private String desRet;
	private List <CatalogosDomiVO> valores;
	
	public CatalogosDomiBeanResponse () {
		
	}
	
	/**
	 * CONSTRUCTOR Sobrecarga
	 * @param codRet
	 * @param msgRet
	 * @param desRet
	 */
	public CatalogosDomiBeanResponse(String codRet
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
	 * @param valores
	 */
	public CatalogosDomiBeanResponse(String codRet
									, String msgRet
									, String desRet
									, List <CatalogosDomiVO> valores) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.valores = valores;
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

	@XmlElementWrapper(name = "valores")
	@XmlElement(name = "valor")
	public List<CatalogosDomiVO> getValores() {
		return valores;
	}

	public void setValores(List<CatalogosDomiVO> valores) {
		this.valores = valores;
	}
}
