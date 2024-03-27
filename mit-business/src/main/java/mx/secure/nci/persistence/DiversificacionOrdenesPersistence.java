package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.DiversificacionOrdenesVO;
import mx.secure.nci.business.vo.OrdenesVO;
import mx.secure.nci.business.wrapped.OrdenesFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface DiversificacionOrdenesPersistence 
{

	int insertAll(List<DiversificacionOrdenesVO> diversificacionesOrdenes);
	
	int update(DiversificacionOrdenesVO diversificacionOrdenesVO);
	
	List<DiversificacionOrdenesVO> selectSelective(OrdenesFilter ordenesFilter);
	
}
