package mx.profuturo.nci.business.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeveris.core.persistence.exception.PersistenceException;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IBitacoraProcesoService;
import mx.profuturo.nci.business.vo.BitacoraProcesoVO;
import mx.profuturo.nci.persistence.BitacoraProcesoPersistence;

@Service("bitacoraProcesoService")
public class BitacoraServiceImpl implements IBitacoraProcesoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BitacoraServiceImpl.class);
	
	@Autowired BitacoraProcesoPersistence bitacoraPersistence;

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Boolean insertar(BitacoraProcesoVO vo) throws MitBusinessException {
		try{
			return bitacoraPersistence.insert(vo) > 0;
		} catch (PersistenceException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En insertar conciliacion",
							new Object[] { "ConciliacionServiceImpl", "insertar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

}
