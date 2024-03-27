package mx.profuturo.nci.persistence;

import java.util.List;

import mx.profuturo.nci.business.vo.DiversificacionOrdenesVO;
import mx.profuturo.nci.business.vo.OrdenesVO;
import mx.profuturo.nci.business.wrapped.OrdenesFilter;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface DiversificacionOrdenesPersistence 
{

	int insertAll(List<DiversificacionOrdenesVO> diversificacionesOrdenes);
	
	int update(DiversificacionOrdenesVO diversificacionOrdenesVO);
	
	List<DiversificacionOrdenesVO> selectSelective(OrdenesFilter ordenesFilter);
	
}
