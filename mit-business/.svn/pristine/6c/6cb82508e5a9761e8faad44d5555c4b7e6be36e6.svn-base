package mx.profuturo.nci.business.service;

import java.util.List;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.vo.IndicadorVO;
import mx.profuturo.nci.business.vo.OrdenesVO;
import mx.profuturo.nci.business.wrapped.OrdenesFilter;

public interface IOrdenesService 
{

	Boolean guardar(OrdenesVO ordenesVO)throws MitBusinessException;
	
	Boolean actualizar(OrdenesVO ordenesVO)throws MitBusinessException;
	
	List<OrdenesVO> consultaSelectiva(OrdenesFilter ordenesFilter)throws MitBusinessException;
	
	List<IndicadorVO> agregarInfoCuenta(String cuenta) throws MitBusinessException;
}
