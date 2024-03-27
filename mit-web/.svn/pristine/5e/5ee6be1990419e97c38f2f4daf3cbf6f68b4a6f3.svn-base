package mx.profuturo.nci.web.service;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.vo.CatalogoConfiguracionVO;
import mx.profuturo.nci.business.wrapped.CatalogoConfiguracionFilter;

public interface ICatalogoConfiguracionWebService {
	
	public CatalogoConfiguracionVO consultarPorIdConfig(Short idConfig) throws MitBusinessException;
	
	/**
	 * Metodo que actualiza Diferencia Maxima 
	 * @param CatalogoConfiguracionFilter (Obligatorios "idCatalogoConfiguracion, valor y usuarioActualizacion")
	 * @return int (Numero de registros actualizados)
	 * @throws MitBusinessException
	 */
	public int actualizaPorIdCatalogo(CatalogoConfiguracionFilter filter)throws MitBusinessException;
}