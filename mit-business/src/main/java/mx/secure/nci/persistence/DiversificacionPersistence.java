package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.DiversificacionVO;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface DiversificacionPersistence {
	public List<DiversificacionVO> selectDiversificacionSolicitudDomiciliacion(DiversificacionVO diversificacionVO);
	
	public List<DiversificacionVO> selectDiversificacionPreSolicitudDomiciliacion(DiversificacionVO diversificacionVO);
	
	public int insertarDiversificacionSolicitudDomiciliacion(DiversificacionVO diversificacionVO);
	public int insertarDiversificacionSolicitudDomiciliacionPar(DiversificacionVO diversificacionVO);
	
	public int actualizarDiversificacionSolicitudDomiciliacion(DiversificacionVO diversificacionVO);
	
	public int insertDiversificacionPreSolicitud(DiversificacionVO diversificacionVO);
	
	public int actualizarDiversificacionPreSolicitudDomiciliacion(DiversificacionVO diversificacionVO);
	
	public int logicDeleteDiversificacionSolicitudDomiciliacion(DiversificacionVO diversificacionVO);
	public List<DiversificacionVO> getDiversificacionesByCveSolicitud(DiversificacionVO diversificacionVO);
}
