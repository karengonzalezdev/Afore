package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.CatalogoConfiguracionVO;
import mx.secure.nci.business.vo.CatalogoVO;
import mx.secure.nci.business.vo.ParamCatalogoVO;
import mx.secure.nci.business.wrapped.CatalogoConfiguracionFilter;

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
