package mx.secure.nci.business.report;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.report.beans.BasicParamReportBean;
import mx.secure.nci.business.report.beans.BasicReportBean;
import mx.secure.nci.business.report.beans.BasicXLSParamReportBean;
import mx.secure.nci.business.vo.ReporteVO;

public interface IBasicReportService {
	<T>ReporteVO crearReporteXls(BasicXLSParamReportBean<T> param) throws MitBusinessException ;
	<T>ReporteVO crearReportePDF(BasicParamReportBean<T> param) throws MitBusinessException;
}
