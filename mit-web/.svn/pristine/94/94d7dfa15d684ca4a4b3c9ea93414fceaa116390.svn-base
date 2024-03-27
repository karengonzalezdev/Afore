package mx.profuturo.nci.web.service;

import java.util.List;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.DictamenRechazadaRespuestaVO;
import mx.profuturo.nci.business.vo.ReporteCifrasControlVO;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.business.vo.ResultadoDictamenRechazadoVO;
import mx.profuturo.nci.business.vo.SolicitudReclasificacionVO;
import mx.profuturo.nci.web.beans.CifrasControlClienteFilterBean;

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
