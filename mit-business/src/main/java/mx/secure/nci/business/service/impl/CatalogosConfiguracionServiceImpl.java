package mx.secure.nci.business.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.secure.nci.business.exception.ErrorCodeService;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.ICatalogosConfiguracionService;
import mx.secure.nci.business.vo.CatalogoConfiguracionVO;
import mx.secure.nci.business.wrapped.CatalogoConfiguracionFilter;
import mx.secure.nci.persistence.CatalogoConfiguracionPersistence;


@Service("catalogoConfiguracionService")
public class CatalogosConfiguracionServiceImpl implements ICatalogosConfiguracionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogosConfiguracionServiceImpl.class);
	
	@Autowired
	CatalogoConfiguracionPersistence catalogoConfiguracionPersistence;
	
	
	public CatalogoConfiguracionVO consultarPorIdConfig(Short idConfig ) throws MitBusinessException  {
		try {
			return this.catalogoConfiguracionPersistence.selectById(idConfig);
			
		} catch(Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al Consultar Diferencia Maxima ",
					new Object[] { "CatalogoConfiguracionServiceImpl", "consultarDiferenciaMaxima()" }, ex));
				LOGGER.error(mitBusinessException.getMessage(), ex);
				throw mitBusinessException;			
		}		
	}

	public int actualizaPorIdCatalogo(CatalogoConfiguracionFilter filter) throws MitBusinessException  {
		try {
			return this.catalogoConfiguracionPersistence.updateByIdCatalogo(filter);
			
		} catch(Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al Actualizar Diferencia Maxima ",
					new Object[] { "CatalogoConfiguracionServiceImpl", "actualizaDiferenciaMaxima()" }, ex));
				LOGGER.error(mitBusinessException.getMessage(), ex);
				throw mitBusinessException;			
		}	
	}
}
