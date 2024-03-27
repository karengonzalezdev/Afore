package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.CatalogoVO;
import mx.secure.nci.business.vo.ReporteCifrasControlVO;
import mx.secure.nci.business.vo.SolicitudReclasificacionVO;
import mx.secure.nci.stereotype.Mapper;

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
