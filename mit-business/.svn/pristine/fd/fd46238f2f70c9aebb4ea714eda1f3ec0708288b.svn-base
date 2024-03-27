package mx.profuturo.nci.business.report;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.report.beans.BasicParamReportBean;
import mx.profuturo.nci.business.report.beans.BasicReportBean;
import mx.profuturo.nci.business.report.beans.BasicXLSParamReportBean;
import mx.profuturo.nci.business.vo.ReporteVO;

public interface IBasicReportService {
	<T>ReporteVO crearReporteXls(BasicXLSParamReportBean<T> param) throws MitBusinessException ;
	<T>ReporteVO crearReportePDF(BasicParamReportBean<T> param) throws MitBusinessException;
}
