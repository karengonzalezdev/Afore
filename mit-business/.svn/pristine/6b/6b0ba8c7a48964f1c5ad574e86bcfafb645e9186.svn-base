package mx.profuturo.nci.business.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.ICatalogosConfiguracionService;
import mx.profuturo.nci.business.vo.CatalogoConfiguracionVO;
import mx.profuturo.nci.business.wrapped.CatalogoConfiguracionFilter;
import mx.profuturo.nci.persistence.CatalogoConfiguracionPersistence;


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
