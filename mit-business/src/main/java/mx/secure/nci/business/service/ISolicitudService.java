package mx.secure.nci.business.service;

import java.util.Date;
import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.DiversificacionVO;
import mx.secure.nci.business.vo.SolicitudVO;
import mx.secure.nci.business.vo.SpeiDetallesecureVO;
import mx.secure.nci.business.vo.ValidacionVO;
import mx.secure.nci.business.wrapped.SolicitudFilter;

public interface ISolicitudService {

	public List<SolicitudVO> consultar(SolicitudFilter solicitudFilter) throws MitBusinessException;
	
	public List<SolicitudVO> consultarMonto(SolicitudFilter solicitudFilter) throws MitBusinessException;
	
	public int insertar(SolicitudVO solicitudVO) throws MitBusinessException;
	public int actualizar(SolicitudVO solicitudVO) throws MitBusinessException;
	public Date getProximaFechaDeCargo(SolicitudVO solVO) throws MitBusinessException ;
//	public List<ArchivoDomiciliacionVO> consultarInfoArchivos(ArchivoDomiciliacionfilter archivoFilter) throws MitBusinessException;
//	List<SolicitudVO> consultarDomiRecord(SolicitudFilter archivoDomiFilter)throws MitBusinessException;
	
	public List<SolicitudVO> consultarSolicitudDomiciliacion(SolicitudFilter solicitudFilter) throws MitBusinessException;
	public List<SolicitudVO> consultarSolicitudDomiciliacionCorta(SolicitudFilter solicitudFilter) throws MitBusinessException;
	public List<SolicitudVO> consultarHistSolicitud(SolicitudFilter claveSolicitud) throws MitBusinessException;
	public List<SolicitudVO> consultarHistConciliacion(SolicitudFilter claveSolicitud) throws MitBusinessException;
	public List<SolicitudVO> consultarDetSolicitudNoDomi(SolicitudFilter claveSolicitud) throws MitBusinessException;
	
	public List<ValidacionVO> consultaValidaciones(SolicitudFilter solicitudFilter) throws MitBusinessException;	
	public List<SolicitudVO> consultarCuentasSolicitudDomiciliacion(SolicitudFilter claveSolicitud) throws MitBusinessException;
	
	public List<DiversificacionVO> consultarDiversificacionesSol(SolicitudFilter solicitudFilter) throws MitBusinessException;
	public List<DiversificacionVO> consultarDiversificacionesCon(SolicitudFilter solicitudFilter) throws MitBusinessException;
	
	public List<SpeiDetallesecureVO> consultaDetalleSpei(SolicitudFilter solicitudFilter) throws MitBusinessException;
	
	public List<SpeiDetallesecureVO> consultaDetalleNomina(SolicitudFilter solicitudFilter) throws MitBusinessException;
	
	public List<SolicitudVO> consultarCuentasSolicitud(SolicitudFilter claveSolicitud) throws MitBusinessException;
	public List<SolicitudVO> consultarCuentasConciliacion(SolicitudFilter claveSolicitud) throws MitBusinessException;
	public List<SolicitudVO> consultarCuentasBancos(SolicitudFilter claveSolicitud) throws MitBusinessException;
	public List<SolicitudVO> consultarCuentasSpei(SolicitudFilter claveSolicitud) throws MitBusinessException;
	public List<SolicitudVO> consultarCuentasTodos(SolicitudFilter claveSolicitud) throws MitBusinessException;

	public List<SolicitudVO> consultarSolicitudDomiciliacionSolicitud(SolicitudFilter solicitudFilter) throws MitBusinessException;
	public List<SolicitudVO> consultarSolicitudDomiciliacionConciliacion(SolicitudFilter solicitudFilter) throws MitBusinessException;
	public List<SolicitudVO> consultarSolicitudDomiciliacionBancos(SolicitudFilter solicitudFilter) throws MitBusinessException;
	public List<SolicitudVO> consultarSolicitudDomiciliacionSpei(SolicitudFilter solicitudFilter) throws MitBusinessException;
	public List<SolicitudVO> consultarSolicitudDomiciliacionTodos(SolicitudFilter solicitudFilter) throws MitBusinessException;
	
}