package mx.secure.nci.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;
import com.jeveris.core.persistence.exception.PersistenceException;

import mx.secure.nci.business.exception.ErrorCodeService;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.IMapaReferenciasService;
import mx.secure.nci.business.vo.MapaReferenciasVO;
import mx.secure.nci.persistence.MapaReferenciasPersistence;

@Service("mapaReferenciaService")
public class MapaReferenciaServiceImpl implements IMapaReferenciasService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MapaReferenciaServiceImpl.class);

	@Autowired
	MapaReferenciasPersistence mapaReferenciasPersistence;

	public List<MapaReferenciasVO> consultar() throws BusinessException {
		try {

			return mapaReferenciasPersistence.selectSubCta();
		} catch (PersistenceException ex) {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar Archivo",
							new Object[] { "ArchivoServiceImpl", "consultarLista()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

}
