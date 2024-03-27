package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.PreSolicitudVO;
import mx.secure.nci.business.wrapped.PreSolicitudFilter;

public interface IPreSolicitudService 
{
	
	public Boolean guardar(PreSolicitudVO preSolicitudVO) throws MitBusinessException;
	
	public Boolean actualizar(PreSolicitudVO preSolicitudVO) throws MitBusinessException;
	
	public Boolean actualizarEstatusPorFolioYEstatus(PreSolicitudFilter preSolicitudFilter) throws MitBusinessException;
	
	public List<PreSolicitudVO> consultar(PreSolicitudFilter preSolicitudFilter) throws MitBusinessException;
}
