package mx.profuturo.nci.persistence;

import java.util.List;

import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ReporteCifrasControlVO;
import mx.profuturo.nci.business.vo.SolicitudReclasificacionVO;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface CifrasControlClientePersistence {
	
	public List<SolicitudReclasificacionVO> consultaSolicitudes(SolicitudReclasificacionVO filtro);
	public List<SolicitudReclasificacionVO> consultaSolicitudesDetalle(String filtro);
	public List<SolicitudReclasificacionVO> consultaSolicitudesDetalleCargo(String filtro);
	public List<ReporteCifrasControlVO> consultaReporte(SolicitudReclasificacionVO filtro);
	
	public List<CatalogoVO> consultaOrigen();
	
	public List<CatalogoVO> consultaEstatus();
	public List<CatalogoVO> consultaMotivos();
	
	public boolean actualizarEstatus(SolicitudReclasificacionVO clave);

}
