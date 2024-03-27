package mx.secure.nci.business.report;

import java.util.List;

import com.jeveris.core.exception.impl.BusinessException;

import mx.secure.nci.business.events.report.transaccion.CifrasControlReclasecureReportRequestReadEvent;
import mx.secure.nci.business.vo.ReporteVO;

public interface ICifrasControlReportService 
{
	ReporteVO generarArchivoCifrasGenerales(List cifrasControlReporteVO) throws BusinessException;
	
	ReporteVO generarArchivoCifrasCtrlReclasificacionsecure(CifrasControlReclasecureReportRequestReadEvent requestCifrasContrlEventVO) throws BusinessException;
	
}
