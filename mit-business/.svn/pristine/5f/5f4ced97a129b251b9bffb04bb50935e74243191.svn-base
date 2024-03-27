package mx.profuturo.nci.persistence;

import mx.profuturo.nci.business.vo.MatrizConvivenciaVO;
import mx.profuturo.nci.business.wrapped.MatrizConvivenciaFilter;
import mx.profuturo.nci.stereotype.Mapper;

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