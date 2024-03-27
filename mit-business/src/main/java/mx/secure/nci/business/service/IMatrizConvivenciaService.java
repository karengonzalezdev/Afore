package mx.secure.nci.business.service;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.MatrizConvivenciaVO;
import mx.secure.nci.business.wrapped.MatrizConvivenciaFilter;

public interface IMatrizConvivenciaService {

	Boolean insertar(MatrizConvivenciaVO vo) throws MitBusinessException;
	Boolean consultarConvivenciaSimple(MatrizConvivenciaFilter filter) throws MitBusinessException ;
}