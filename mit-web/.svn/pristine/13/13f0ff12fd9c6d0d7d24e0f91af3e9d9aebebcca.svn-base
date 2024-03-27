package mx.profuturo.nci.web.service;

import java.util.List;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.vo.ReporteVO;

import mx.profuturo.nci.web.beans.BancosBean;
import mx.profuturo.nci.web.beans.BancosFilterBean;
import mx.profuturo.nci.web.beans.DetalleSpeiApoVol;
import mx.profuturo.nci.web.beans.DiversificacionBean;
import mx.profuturo.nci.web.beans.GenericCatalogoBean;
import mx.profuturo.nci.web.beans.SolicitudBean;
import mx.profuturo.nci.web.beans.SolicitudFilterBean;
import mx.profuturo.nci.web.beans.ValidacionesBean;


public interface IConsultaDomiciliacionWebService {
	
	public List<GenericCatalogoBean> consultarOrigenAportacion() throws MitBusinessException;
	public List<GenericCatalogoBean> consultarEstatus() throws MitBusinessException;
	public List<SolicitudBean> consultarCuentas(SolicitudFilterBean solicitudFilter) throws MitBusinessException;
	public List<SolicitudBean> consultar(SolicitudFilterBean solicitudFilter) throws MitBusinessException;
	public List<SolicitudBean> consultarCorta(SolicitudFilterBean solicitudFilter) throws MitBusinessException;
	public List<SolicitudBean> consultarHistSolicitud (SolicitudFilterBean claveSolicitud, Short estatusSol) throws MitBusinessException;
	public List<SolicitudBean> consultarDetalleSolNoDomi (SolicitudFilterBean claveSolicitud) throws MitBusinessException;
	
	public List<DiversificacionBean> consultaDiversificacionSol(SolicitudFilterBean solicitudFilter) throws MitBusinessException;
	public List<DiversificacionBean> consultaDiversificacionCon(SolicitudFilterBean solicitudFilter) throws MitBusinessException;
	
	public List<ValidacionesBean> consultaValidaciones(SolicitudFilterBean solicitudFilter) throws MitBusinessException;
	public ReporteVO generaReporte(List<SolicitudBean> listaSolicitud) throws MitBusinessException;
	public ReporteVO generaReporteDetallado(List<DiversificacionBean> lstDiversificacion, 
			List<ValidacionesBean> lstValidacion, List<SolicitudBean> lstCargos) throws MitBusinessException;
	public DetalleSpeiApoVol obtenerDetalleSpei(String folio) throws MitBusinessException;
	public DetalleSpeiApoVol obtenerDetalleNomina(String folio, Long numCuentaIndividual) throws MitBusinessException;
	
	public List<SolicitudBean> consultarCuentasSolici(SolicitudFilterBean solicitudFilter, Short escenario) throws MitBusinessException;
	public List<SolicitudBean> consultarCuentasConcil(SolicitudFilterBean solicitudFilter) throws MitBusinessException;
	public List<SolicitudBean> consultarCuentasBancos(SolicitudFilterBean solicitudFilter) throws MitBusinessException;
	public List<SolicitudBean> consultarCuentasSpei(SolicitudFilterBean solicitudFilter) throws MitBusinessException;
	public List<SolicitudBean> consultarCuentasTodos(SolicitudFilterBean solicitudFilter) throws MitBusinessException;
	
	public List<SolicitudBean> consultarListSolicitud(SolicitudFilterBean solicitudFilter) throws MitBusinessException;
	public List<SolicitudBean> consultarListConciliacion(SolicitudFilterBean solicitudFilter) throws MitBusinessException;
	public List<SolicitudBean> consultarListBancos(SolicitudFilterBean solicitudFilter) throws MitBusinessException;
	public List<SolicitudBean> consultarListSpei(SolicitudFilterBean solicitudFilter) throws MitBusinessException;
	public List<SolicitudBean> consultarListTodos(SolicitudFilterBean solicitudFilter) throws MitBusinessException;
}
