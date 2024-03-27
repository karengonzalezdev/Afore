package mx.secure.nci.web.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.CatalogoVO;
import mx.secure.nci.business.vo.DictamenRechazadaRespuestaVO;
import mx.secure.nci.business.vo.ReporteCifrasControlVO;
import mx.secure.nci.business.vo.ReporteVO;
import mx.secure.nci.business.vo.ResultadoDictamenRechazadoVO;
import mx.secure.nci.business.vo.SolicitudReclasificacionVO;
import mx.secure.nci.web.beans.CifrasControlClienteFilterBean;

public interface ICifrasControlClienteWebService {
	
	

	public List<CatalogoVO> consultarOrigen() throws MitBusinessException;
	public List<CatalogoVO> consultarEstatus() throws MitBusinessException;
	public List<CatalogoVO> consultarMotivos() throws MitBusinessException;
	public boolean actializarEstatus(String clave, int estatus) throws MitBusinessException;
	public List<SolicitudReclasificacionVO> consultaSolicitudes(CifrasControlClienteFilterBean filtro) throws MitBusinessException;
	public List<SolicitudReclasificacionVO> consultaSolicitudesDetalle(SolicitudReclasificacionVO filtro) throws MitBusinessException;
	public List<SolicitudReclasificacionVO> consultaSolicitudesDetalleCargo(SolicitudReclasificacionVO filtro) throws MitBusinessException;
	public List<ReporteCifrasControlVO> consultaReporte(SolicitudReclasificacionVO filtro) throws MitBusinessException;
	public ReporteVO generarReporte(List<ReporteCifrasControlVO> datos) throws MitBusinessException;
	public DictamenRechazadaRespuestaVO dictamenRechazada( ResultadoDictamenRechazadoVO datos) throws MitBusinessException;
	
	public String totalDiversifica(SolicitudReclasificacionVO filtro) throws MitBusinessException;
	public String totalCargo(SolicitudReclasificacionVO filtro) throws MitBusinessException;
	
	
	
}
