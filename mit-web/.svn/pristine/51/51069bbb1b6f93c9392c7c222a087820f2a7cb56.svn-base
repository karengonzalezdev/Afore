package mx.profuturo.nci.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.ICatalogosConfiguracionService;
import mx.profuturo.nci.business.vo.CatalogoConfiguracionVO;
import mx.profuturo.nci.business.wrapped.CatalogoConfiguracionFilter;
import mx.profuturo.nci.web.service.ICatalogoConfiguracionWebService;


@Service("catalogoConfiguracionWebService")
public class CatalogoConfiguracionWebServiceImpl implements ICatalogoConfiguracionWebService 
{

	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogoConfiguracionWebServiceImpl.class);
	
	@Autowired ICatalogosConfiguracionService catalogoConfiguracionService;
	
	public CatalogoConfiguracionVO consultarPorIdConfig(Short idConfig ) throws MitBusinessException  {
		return catalogoConfiguracionService.consultarPorIdConfig(idConfig);
	}


	public int actualizaPorIdCatalogo(CatalogoConfiguracionFilter filter) throws MitBusinessException  {
		return catalogoConfiguracionService.actualizaPorIdCatalogo(filter);
	}
}