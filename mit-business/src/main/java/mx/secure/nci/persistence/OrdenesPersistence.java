package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.IndicadorVO;
import mx.secure.nci.business.vo.OrdenesVO;
import mx.secure.nci.business.wrapped.OrdenesFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface OrdenesPersistence 
{
	int insert(OrdenesVO ordenesVO);
	
	int update(OrdenesVO ordenesVO);
	
	List<OrdenesVO> selectSelective(OrdenesFilter ordenesFilter);
	
	List<IndicadorVO> agregarInfoCuenta (String cuenta);
	
	

}
