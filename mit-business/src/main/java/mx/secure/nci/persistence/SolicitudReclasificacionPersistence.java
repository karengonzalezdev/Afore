package mx.secure.nci.persistence;

import java.util.List;

import com.jeveris.core.exception.impl.BusinessException;

import mx.secure.nci.business.vo.CatalogoVO;
import mx.secure.nci.business.vo.ClienteVO;
import mx.secure.nci.business.vo.DiversificacionSolicitudReclasificacionVO;
import mx.secure.nci.business.vo.IndicadorSolicitudReclasificacionVO;
import mx.secure.nci.business.vo.SolicitudReclasificacionsecureVO;
import mx.secure.nci.business.vo.SolicitudReclasificacionVO;
import mx.secure.nci.business.wrapped.ClienteFilter;
import mx.secure.nci.business.wrapped.SolicitudReclasificacionFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface SolicitudReclasificacionPersistence {
	
	public List<SolicitudReclasificacionVO> consultaSolicitudes(SolicitudReclasificacionVO filtro);
	
	public List<CatalogoVO> consultaOrigen();
	
	public List<CatalogoVO> consultaEstatus();
	
	int insertarSolicitud(SolicitudReclasificacionsecureVO solicitudReclasificacionVO) throws BusinessException;
	
	int actualizarSolicitud(SolicitudReclasificacionsecureVO solicitudReclasificacionVO) throws BusinessException;
	
	int insertarDiversificacion(DiversificacionSolicitudReclasificacionVO diversificacionVO) throws BusinessException;
	
	int insertarSaldoOriginal(DiversificacionSolicitudReclasificacionVO saldoOriginalVO) throws BusinessException;
	
	int insertarIndicadores(IndicadorSolicitudReclasificacionVO indicadorVO) throws BusinessException;
	
	int insertaSolicitante(ClienteVO solicitanteVO) throws BusinessException;
	
	int insertaEstatusSolicitud(SolicitudReclasificacionsecureVO solicitudVO);
	
	SolicitudReclasificacionsecureVO consultarSolicitud(SolicitudReclasificacionFilter solicitudReclasificacionFilter) throws BusinessException;
	
	ClienteVO selectSolicitante(ClienteFilter clienteFilter) throws BusinessException;
	
	int actualizarSolicitante(ClienteVO solicitanteVO) throws BusinessException;

}
