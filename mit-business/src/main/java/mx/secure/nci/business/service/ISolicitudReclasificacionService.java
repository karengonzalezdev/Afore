package mx.secure.nci.business.service;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.SolicitudReclasificacionsecureVO;
import mx.secure.nci.business.wrapped.SolicitudReclasificacionFilter;

public interface ISolicitudReclasificacionService {
	public Boolean guardarSolicitud(SolicitudReclasificacionsecureVO solicitudReclasificacionVO) throws MitBusinessException;

	public SolicitudReclasificacionsecureVO consultarSolicitud(SolicitudReclasificacionFilter solicitudReclasificacionFilter)
			throws MitBusinessException;
	
	public Boolean actualizarEstatusSolicitud(SolicitudReclasificacionsecureVO solicitudReclasificacionVO) throws MitBusinessException;
	
}
