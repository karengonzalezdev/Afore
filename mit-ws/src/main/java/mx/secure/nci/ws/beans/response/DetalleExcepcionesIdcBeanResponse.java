package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.business.vo.DetalleExcepcionesIdcVO;

public class DetalleExcepcionesIdcBeanResponse {
	
	private String codRet;
	private String msgRet;
	private String desRet;
	private List<DetalleExcepcionesIdcVO> lstDetalleExcepcionesIdc;
	
	public DetalleExcepcionesIdcBeanResponse() {}

	public DetalleExcepcionesIdcBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}

	public DetalleExcepcionesIdcBeanResponse(String codRet, String msgRet, String desRet,
			List<DetalleExcepcionesIdcVO> lstDetalleExcepcionesIdc) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.lstDetalleExcepcionesIdc = lstDetalleExcepcionesIdc;
	}

	@XmlElementWrapper(name="listaDetalleExcepcionesIdc")
	@XmlElement(name = "detalleExcepcionesIdc")
	public List<DetalleExcepcionesIdcVO> getLstDetalleExcepcionesIdc() {
		return lstDetalleExcepcionesIdc;
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

	public void setLstDetalleExcepcionesIdc(List<DetalleExcepcionesIdcVO> lstDetalleExcepcionesIdc) {
		this.lstDetalleExcepcionesIdc = lstDetalleExcepcionesIdc;
	}
}
