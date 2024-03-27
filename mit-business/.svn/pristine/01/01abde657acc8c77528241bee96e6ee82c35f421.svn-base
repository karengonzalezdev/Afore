package mx.profuturo.nci.persistence;

import java.util.List;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ClienteVO;
import mx.profuturo.nci.business.vo.DiversificacionSolicitudReclasificacionVO;
import mx.profuturo.nci.business.vo.IndicadorSolicitudReclasificacionVO;
import mx.profuturo.nci.business.vo.SolicitudReclasificacionApoVolVO;
import mx.profuturo.nci.business.vo.SolicitudReclasificacionVO;
import mx.profuturo.nci.business.wrapped.ClienteFilter;
import mx.profuturo.nci.business.wrapped.SolicitudReclasificacionFilter;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface SolicitudReclasificacionPersistence {
	
	public List<SolicitudReclasificacionVO> consultaSolicitudes(SolicitudReclasificacionVO filtro);
	
	public List<CatalogoVO> consultaOrigen();
	
	public List<CatalogoVO> consultaEstatus();
	
	int insertarSolicitud(SolicitudReclasificacionApoVolVO solicitudReclasificacionVO) throws BusinessException;
	
	int actualizarSolicitud(SolicitudReclasificacionApoVolVO solicitudReclasificacionVO) throws BusinessException;
	
	int insertarDiversificacion(DiversificacionSolicitudReclasificacionVO diversificacionVO) throws BusinessException;
	
	int insertarSaldoOriginal(DiversificacionSolicitudReclasificacionVO saldoOriginalVO) throws BusinessException;
	
	int insertarIndicadores(IndicadorSolicitudReclasificacionVO indicadorVO) throws BusinessException;
	
	int insertaSolicitante(ClienteVO solicitanteVO) throws BusinessException;
	
	int insertaEstatusSolicitud(SolicitudReclasificacionApoVolVO solicitudVO);
	
	SolicitudReclasificacionApoVolVO consultarSolicitud(SolicitudReclasificacionFilter solicitudReclasificacionFilter) throws BusinessException;
	
	ClienteVO selectSolicitante(ClienteFilter clienteFilter) throws BusinessException;
	
	int actualizarSolicitante(ClienteVO solicitanteVO) throws BusinessException;

}
