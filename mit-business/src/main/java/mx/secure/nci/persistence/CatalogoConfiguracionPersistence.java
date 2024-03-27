package mx.secure.nci.persistence;

import mx.secure.nci.business.vo.CatalogoConfiguracionVO;
import mx.secure.nci.business.wrapped.CatalogoConfiguracionFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface CatalogoConfiguracionPersistence 
{

	public CatalogoConfiguracionVO selectById(Short idCatalogoConfiguracion);
	
	public int updateByIdCatalogo(CatalogoConfiguracionFilter filter);
}