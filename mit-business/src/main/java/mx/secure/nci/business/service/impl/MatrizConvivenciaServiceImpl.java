package mx.secure.nci.business.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeveris.core.persistence.exception.PersistenceException;

import mx.secure.nci.business.exception.ErrorCodeService;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.IMatrizConvivenciaService;
import mx.secure.nci.business.vo.MatrizConvivenciaVO;
import mx.secure.nci.business.wrapped.MatrizConvivenciaFilter;
import mx.secure.nci.persistence.MatrizConvivenciaPersistence;

@Service("matrizConvivenciaService")
public class MatrizConvivenciaServiceImpl implements IMatrizConvivenciaService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MatrizConvivenciaServiceImpl.class);
//	private static final Integer UNO = new Integer(1);
	private static final Integer CERO = new Integer(0);
	
	@Autowired MatrizConvivenciaPersistence matrizConvicenciaPersistence;

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Boolean insertar(MatrizConvivenciaVO vo) throws MitBusinessException {
		try{
			return matrizConvicenciaPersistence.insert(vo) > 0;
		} catch (PersistenceException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En insertar matrizConvivencia:"+ex.getMessage(),
							new Object[] { "MatrizConvivenciaServiceImpl", "insertar()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
	}
	
	public Boolean consultarConvivenciaSimple(MatrizConvivenciaFilter filter) throws MitBusinessException {
		try{
			Integer valConvive = matrizConvicenciaPersistence.countNoConvivenSimple(filter);
			// Si la consulta countNoConvivenSimple regresa valores mayores a cero, eso implica que hay al menos un registro que no convive
			return CERO.equals(valConvive);
		} catch (PersistenceException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar matrizConvivencia:"+ex.getMessage(),
							new Object[] { "MatrizConvivenciaServiceImpl", "consultarConvivenciaSimple()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
	}
}