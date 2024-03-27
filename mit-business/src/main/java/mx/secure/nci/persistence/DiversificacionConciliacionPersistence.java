package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.DiversificacionConciliacionVO;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface DiversificacionConciliacionPersistence 
{
	public int insert(DiversificacionConciliacionVO diversificacionConciliacionVO);
	
	public int update(DiversificacionConciliacionVO diversificacionConciliacionVO);
	
	int insertAll(List<DiversificacionConciliacionVO> lstDiversificacionesConciliacion);
}
