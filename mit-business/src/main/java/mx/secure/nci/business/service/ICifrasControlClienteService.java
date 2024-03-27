package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.CatalogoVO;
import mx.secure.nci.business.vo.ReporteCifrasControlVO;
import mx.secure.nci.business.vo.SolicitudReclasificacionVO;

public interface ICifrasControlClienteService {

	
	public List<SolicitudReclasificacionVO> consultaSolicitudes(SolicitudReclasificacionVO filtro) throws MitBusinessException;
	
	public List<SolicitudReclasificacionVO> consultaSolicitudesDetalle(SolicitudReclasificacionVO filtro) throws MitBusinessException;
	public List<SolicitudReclasificacionVO> consultaSolicitudesDetalleCargo(SolicitudReclasificacionVO filtro) throws MitBusinessException;
	
	public List<ReporteCifrasControlVO> consultaReporte(SolicitudReclasificacionVO filtro) throws MitBusinessException;
	
	public boolean actializarEstatus(String clave, int estatus) throws MitBusinessException;
	
	public List<CatalogoVO> consultaOrigen() throws MitBusinessException;
	
	public List<CatalogoVO> consultaEstatus() throws MitBusinessException;
	
	public List<CatalogoVO> consultaMotivos() throws MitBusinessException;
	
	public String totalDiversifica(SolicitudReclasificacionVO filtro) throws MitBusinessException;
	
	public String totalCargo(SolicitudReclasificacionVO filtro) throws MitBusinessException;
	

	
	

}
