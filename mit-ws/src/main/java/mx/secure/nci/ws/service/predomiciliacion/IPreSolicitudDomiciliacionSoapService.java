package mx.secure.nci.ws.service.predomiciliacion;

import mx.secure.nci.ws.beans.request.PreSolicitudDomiciliacionActualizarBeanRequest;
import mx.secure.nci.ws.beans.request.PreSolicitudDomiciliacionConsultarBeanRequest;
import mx.secure.nci.ws.beans.request.PreSolicitudDomiciliacionInsertarBeanRequest;
import mx.secure.nci.ws.beans.request.PreSolicitudDomiciliacionRechazarBeanRequest;
import mx.secure.nci.ws.beans.response.PreSolicitudDomiciliacionConsultarBeanResponse;

public interface IPreSolicitudDomiciliacionSoapService 
{
	public PreSolicitudDomiciliacionConsultarBeanResponse consultar(PreSolicitudDomiciliacionConsultarBeanRequest request);
	
	public Boolean insertar(PreSolicitudDomiciliacionInsertarBeanRequest request);
	
	public Boolean actualizar(PreSolicitudDomiciliacionActualizarBeanRequest request);
	
	public Boolean rechazarPendientesPorFolio(PreSolicitudDomiciliacionRechazarBeanRequest request);
}
