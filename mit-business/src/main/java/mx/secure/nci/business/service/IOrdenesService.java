package mx.secure.nci.business.service;

import java.util.List;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.IndicadorVO;
import mx.secure.nci.business.vo.OrdenesVO;
import mx.secure.nci.business.wrapped.OrdenesFilter;

public interface IOrdenesService 
{

	Boolean guardar(OrdenesVO ordenesVO)throws MitBusinessException;
	
	Boolean actualizar(OrdenesVO ordenesVO)throws MitBusinessException;
	
	List<OrdenesVO> consultaSelectiva(OrdenesFilter ordenesFilter)throws MitBusinessException;
	
	List<IndicadorVO> agregarInfoCuenta(String cuenta) throws MitBusinessException;
}
