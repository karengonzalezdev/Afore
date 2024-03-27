package mx.secure.nci.ws.beans.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.business.vo.ReporteDetalleIdcVO;
import mx.secure.nci.ws.beans.ReportBean;

public class DetalleIdcReporteBeanResponse 
{
	private String codRet;
	private String msgRet;
	private String desRet;
	private ReportBean reportBean;
	private List<ReporteDetalleIdcVO> lstDetalleIdc;
		
	public DetalleIdcReporteBeanResponse(){}
	
	public DetalleIdcReporteBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}
	
	/**
	 * @param reportBean
	 */
	public DetalleIdcReporteBeanResponse(String codRet, String msgRet, String desRet,
			ReportBean reportBean) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.reportBean = reportBean;
	}
	
	/**
	 * @param reportBean, lstDetalleIdc
	 */
	public DetalleIdcReporteBeanResponse(String codRet, String msgRet, String desRet,
			ReportBean reportBean, List<ReporteDetalleIdcVO> lstDetalleIdc) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.reportBean = reportBean;
		this.lstDetalleIdc = lstDetalleIdc;
	}
	
	/**
	 * @param lstDetalleIdc
	 */
	public DetalleIdcReporteBeanResponse(String codRet, String msgRet, String desRet,
			List<ReporteDetalleIdcVO> lstDetalleIdc) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.lstDetalleIdc = lstDetalleIdc;
	}
	
	@XmlElement(name="reporte")
	public ReportBean getReportBean() {
		return reportBean;
	}
	public void setReportBean(ReportBean reportBean) {
		this.reportBean = reportBean;
	}
	
	@XmlElementWrapper(name="listaDetalleIdc")
	@XmlElement(name = "detalleIdc")
	public List<ReporteDetalleIdcVO> getLstDetalleIdc() {
		return lstDetalleIdc;
	}
	
	public void setLstDetalleIdc(List<ReporteDetalleIdcVO> lstDetalleIdc) {
		this.lstDetalleIdc = lstDetalleIdc;
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
	
}