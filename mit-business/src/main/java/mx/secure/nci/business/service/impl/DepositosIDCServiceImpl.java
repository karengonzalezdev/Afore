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
import mx.secure.nci.business.service.IDepositosIDCService;
import mx.secure.nci.business.vo.DepositosIDCVO;
import mx.secure.nci.business.vo.ValidarCuentaVO;
import mx.secure.nci.business.wrapped.DepositosIDCFilter;
import mx.secure.nci.business.wrapped.ValidarCuentaFilter;
import mx.secure.nci.persistence.DepositosIDCPersistence;

@Service("depositosIDCService")
public class DepositosIDCServiceImpl implements IDepositosIDCService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepositosIDCServiceImpl.class);
	
	@Autowired
	DepositosIDCPersistence depositosIDCPersistence;

	public List<DepositosIDCVO> consultar(DepositosIDCFilter DepositosFilter) throws BusinessException {
		
		try {
			return depositosIDCPersistence.select(DepositosFilter);			
			
		} catch (PersistenceException ex)  {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar Depósitos:"+ex.getMessage(),
							new Object[] { "DepositosIDCServiceImpl", "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}

	}
	
	public ValidarCuentaVO validarCuenta(String cuenta) throws BusinessException {
		
		try {
			ValidarCuentaFilter validarCuentaFilter = new ValidarCuentaFilter();
			validarCuentaFilter.setCuenta(cuenta);
			
			return depositosIDCPersistence.selectValidarCuenta(validarCuentaFilter);
			
		} catch (PersistenceException ex)  {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En validar cuenta:"+ex.getMessage(),
							new Object[] { "DepositosIDCServiceImpl", "validarCuenta()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}

	}

}
