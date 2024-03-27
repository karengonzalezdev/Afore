package mx.secure.nci.business.report;

import mx.secure.nci.business.events.report.transaccion.CifrasGeneralesReportRequestReadEvent;
import mx.secure.nci.business.events.report.transaccion.CifrasGeneralesReportResponseReadEvent;
import mx.secure.nci.business.exception.MitBusinessException;

public interface ICifrasGeneralesReportService 
{
	CifrasGeneralesReportResponseReadEvent generarArchivoCifrasGenerales(CifrasGeneralesReportRequestReadEvent event) throws MitBusinessException;
	
}
