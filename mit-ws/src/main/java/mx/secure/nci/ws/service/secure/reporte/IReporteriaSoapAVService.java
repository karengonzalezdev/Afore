package mx.secure.nci.ws.service.secure.reporte;

import mx.secure.nci.ws.beans.request.CifrasControlReclasecureReportRequest;
import mx.secure.nci.ws.beans.request.CifrasGeneralesReportRequest;
import mx.secure.nci.ws.beans.response.CifrasGeneralesReportResponse;

public interface IReporteriaSoapAVService {
	public CifrasGeneralesReportResponse cifrasControl(CifrasGeneralesReportRequest request);
	
	public CifrasGeneralesReportResponse cifrasControlReclasecure(CifrasControlReclasecureReportRequest request);
}
