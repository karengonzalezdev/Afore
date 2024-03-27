package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.business.vo.ArchivoDetalleCifVO;
import mx.secure.nci.ws.beans.ReportBean;

public class DetalleArchivoCifBeanResponse {

	private String codRet;
	private String msgRet;
	private String desRet;
	private ReportBean reportBean;
	private List<ArchivoDetalleCifVO> lstDetalleCif;
	
	public DetalleArchivoCifBeanResponse() {}

	public DetalleArchivoCifBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}

	public DetalleArchivoCifBeanResponse(String codRet, String msgRet, String desRet, ReportBean reportBean) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.reportBean = reportBean;
	}

	public DetalleArchivoCifBeanResponse(String codRet, String msgRet, String desRet, ReportBean reportBean,
			List<ArchivoDetalleCifVO> lstDetalleCif) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.reportBean = reportBean;
		this.lstDetalleCif = lstDetalleCif;
	}

	public DetalleArchivoCifBeanResponse(String codRet, String msgRet, String desRet,
			List<ArchivoDetalleCifVO> lstDetalleCif) {
		super();
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.lstDetalleCif = lstDetalleCif;
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

	@XmlElement(name="reporte")
	public ReportBean getReportBean() {
		return reportBean;
	}

	public void setReportBean(ReportBean reportBean) {
		this.reportBean = reportBean;
	}

	@XmlElementWrapper(name="listaDetalleCif")
	@XmlElement(name = "detalleCif")
	public List<ArchivoDetalleCifVO> getLstDetalleCif() {
		return lstDetalleCif;
	}

	public void setLstDetalleCif(List<ArchivoDetalleCifVO> lstDetalleCif) {
		this.lstDetalleCif = lstDetalleCif;
	}
}
