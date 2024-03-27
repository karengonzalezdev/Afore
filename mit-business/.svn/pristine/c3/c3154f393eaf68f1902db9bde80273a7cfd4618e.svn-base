package mx.profuturo.nci.persistence;

import java.util.List;

import mx.profuturo.nci.business.vo.DiversificacionVO;
import mx.profuturo.nci.stereotype.Mapper;

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
