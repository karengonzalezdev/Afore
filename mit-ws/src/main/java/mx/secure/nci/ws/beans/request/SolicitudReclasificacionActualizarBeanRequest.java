package mx.secure.nci.ws.beans.request;

import mx.secure.nci.business.wrapped.SolicitudReclasificacionFilter;

public class SolicitudReclasificacionActualizarBeanRequest extends SolicitudReclasificacionFilter {
	private Short idEstatusSolicitud;
	private Short idMotivoRechazo;
	private String usuActualiza;

	public Short getIdEstatusSolicitud() {
		return idEstatusSolicitud;
	}

	public void setIdEstatusSolicitud(Short idEstatusSolicitud) {
		this.idEstatusSolicitud = idEstatusSolicitud;
	}

	public Short getIdMotivoRechazo() {
		return idMotivoRechazo;
	}

	public void setIdMotivoRechazo(Short idMotivoRechazo) {
		this.idMotivoRechazo = idMotivoRechazo;
	}
	
	public String getUsuActualiza() {
		return usuActualiza;
	}
	
	public void setUsuActualiza(String usuActualiza) {
		this.usuActualiza = usuActualiza;
	}
}
