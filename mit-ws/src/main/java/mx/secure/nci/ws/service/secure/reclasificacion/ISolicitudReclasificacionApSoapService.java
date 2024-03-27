package mx.secure.nci.ws.service.secure.reclasificacion;

import mx.secure.nci.ws.beans.request.SolicitudReclasificacionActualizarBeanRequest;
import mx.secure.nci.ws.beans.request.SolicitudReclasificacionBeanRequest;
import mx.secure.nci.ws.beans.request.SolicitudReclasificacionConsultarBeanRequest;
import mx.secure.nci.ws.beans.response.SolicitudReclasificacionConsultarBeanResponse;

public interface ISolicitudReclasificacionsecureSoapService {
	public SolicitudReclasificacionConsultarBeanResponse guardarSolicitudsecure(
			SolicitudReclasificacionBeanRequest solicitudReclasificacionBeanRequest);

	public SolicitudReclasificacionConsultarBeanResponse consultarSolicitudsecure(
			SolicitudReclasificacionConsultarBeanRequest solicitudReclasificacionConsultarBeanRequest);

	public Boolean actualizarEstatusSolicitud(
			SolicitudReclasificacionActualizarBeanRequest solicitudReclasificacionActualizarBeanRequest);

}
