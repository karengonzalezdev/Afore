package mx.secure.nci.ws.beans.response;


import javax.xml.bind.annotation.XmlElement;

import mx.secure.nci.business.vo.SolicitudReclasificacionsecureVO;

public class SolicitudReclasificacionConsultarBeanResponse {

	private SolicitudReclasificacionsecureVO solicitudReclasificacionVO;

	public SolicitudReclasificacionConsultarBeanResponse() {}

	public SolicitudReclasificacionConsultarBeanResponse(SolicitudReclasificacionsecureVO solicitudReclasificacion) {
		this.solicitudReclasificacionVO = solicitudReclasificacion;
	}
	
	@XmlElement(name="solicitudReclasificacion")
	public SolicitudReclasificacionsecureVO getSolicitudReclasificacionVO() {
		return solicitudReclasificacionVO;
	}
	
	public void setSolicitudReclasificacionVO(SolicitudReclasificacionsecureVO solicitudReclasificacionVO) {
		this.solicitudReclasificacionVO = solicitudReclasificacionVO;
	}
}
