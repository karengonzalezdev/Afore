package mx.secure.nci.ws.beans.response;

import javax.xml.bind.annotation.XmlElement;

import mx.secure.nci.ws.beans.ReportBean;

public class CifrasGeneralesReportResponse 
{
	
	private ReportBean reportBean;
		
	public CifrasGeneralesReportResponse(){}
	
	/**
	 * @param reportBean
	 */
	public CifrasGeneralesReportResponse(ReportBean reportBean) {
		this.reportBean = reportBean;
	}
		@XmlElement(name="reporte")
	public ReportBean getReportBean() {
		return reportBean;
	}
		public void setReportBean(ReportBean reportBean) {
		this.reportBean = reportBean;
	}
}