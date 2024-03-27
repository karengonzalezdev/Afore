package mx.profuturo.nci.business.report;

import java.util.List;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.events.report.transaccion.CifrasControlReclaApoVolReportRequestReadEvent;
import mx.profuturo.nci.business.vo.ReporteVO;

public interface ICifrasControlReportService 
{
	ReporteVO generarArchivoCifrasGenerales(List cifrasControlReporteVO) throws BusinessException;
	
	ReporteVO generarArchivoCifrasCtrlReclasificacionApoVol(CifrasControlReclaApoVolReportRequestReadEvent requestCifrasContrlEventVO) throws BusinessException;
	
}
