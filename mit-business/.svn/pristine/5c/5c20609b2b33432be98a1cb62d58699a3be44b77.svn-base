package mx.profuturo.nci.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.persistence.exception.PersistenceException;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IPrincipalService;
import mx.profuturo.nci.business.vo.PrincipalVO;
import mx.profuturo.nci.business.wrapped.PrincipalFilter;
import mx.profuturo.nci.persistence.PrincipalPersistence;

@Service("principalService")
public class PrincipalServiceImpl implements IPrincipalService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrincipalServiceImpl.class);
	
	@Autowired
	PrincipalPersistence principalPersistence;
	
	public List<PrincipalVO> consultar(final PrincipalFilter filter) throws MitBusinessException {
		try 
		{
			return principalPersistence.select(filter);
		} 
		catch (PersistenceException ex) 
		{
			throw new MitBusinessException(ex.getExceptionDetails());
		} 
		catch (Exception ex) 
		{
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En consultar principal",
							new Object[] { "PrincipalServiceImpl", "consultar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
	}
}