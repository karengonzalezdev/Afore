package mx.secure.nci.persistence;

import mx.secure.nci.business.vo.MatrizConvivenciaVO;
import mx.secure.nci.business.wrapped.MatrizConvivenciaFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface MatrizConvivenciaPersistence {

	Integer insert(MatrizConvivenciaVO vo);
	
	/**
	 * 
	 * @param Objeto con el idSubproceso y numero de cuenta individual como parametros
	 * @return La cantidad de registros con el num cuenta en matriz de convivencia que no coniven con el subproceso enviado
	 */
	Integer countNoConvivenSimple(MatrizConvivenciaFilter vo);
	
}