package mx.profuturo.nci.persistence;

import java.util.List;

import mx.profuturo.nci.business.vo.IndicadorVO;
import mx.profuturo.nci.business.vo.OrdenesVO;
import mx.profuturo.nci.business.wrapped.OrdenesFilter;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface OrdenesPersistence 
{
	int insert(OrdenesVO ordenesVO);
	
	int update(OrdenesVO ordenesVO);
	
	List<OrdenesVO> selectSelective(OrdenesFilter ordenesFilter);
	
	List<IndicadorVO> agregarInfoCuenta (String cuenta);
	
	

}
