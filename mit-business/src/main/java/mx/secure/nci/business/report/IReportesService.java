package mx.secure.nci.business.report;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.report.beans.CargosDataReportBean;
import mx.secure.nci.business.report.beans.ConsultaAforeMovilDataReportBean;
import mx.secure.nci.business.report.beans.DetalleArchivoCifReportBean;
import mx.secure.nci.business.report.beans.DetalleIdcReportBean;
import mx.secure.nci.business.report.beans.DiversificacionesDataReportBean;
import mx.secure.nci.business.report.beans.DomiliacionesDataReportBean;
import mx.secure.nci.business.report.beans.ValidacionesDataReportBean;
import mx.secure.nci.business.vo.ReporteVO;

public interface IReportesService {
	
	public ReporteVO generaReporteDomiliacion(List<DomiliacionesDataReportBean> dataReportBean) throws MitBusinessException;
	
	public ReporteVO generaReporteDetalleDomiliacion(List<DiversificacionesDataReportBean> diverReportBean, 
			List<ValidacionesDataReportBean> validReportBean, List<CargosDataReportBean> cargoReportBean) throws MitBusinessException;

	public ReporteVO generaReporteDepositosAforeMovil(List<ConsultaAforeMovilDataReportBean> aforeMovilReportBean) throws MitBusinessException;

	public ReporteVO generaReporteDetalleIdc(List<DetalleIdcReportBean> reporteDetalleIdcVO) throws MitBusinessException;
	
	public ReporteVO generarReporteDetalleCif(List<DetalleArchivoCifReportBean> reporteDetalleCifVO) throws MitBusinessException;
}