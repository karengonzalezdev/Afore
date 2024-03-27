package mx.profuturo.nci.business.service;

import java.util.List;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.vo.CatalogoConfiguracionVO;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ParamCatalogoVO;
import mx.profuturo.nci.business.wrapped.CatalogoConfiguracionFilter;

public interface ICatalogosConfiguracionService {
	public CatalogoConfiguracionVO consultarPorIdConfig(Short idConfig) throws MitBusinessException;
	
	/**
	 * Metodo que actualiza Diferencia Maxima 
	 * @param CatalogoConfiguracionFilter (Obligatorios "idCatalogoConfiguracion, valor y usuarioActualizacion")
	 * @return int (Numero de registros actualizados)
	 * @throws MitBusinessException
	 */
	public int actualizaPorIdCatalogo(CatalogoConfiguracionFilter filter)throws MitBusinessException;
	
}
