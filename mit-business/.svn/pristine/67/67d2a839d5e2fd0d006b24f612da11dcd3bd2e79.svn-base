package mx.profuturo.nci.business.service.impl;

import java.util.List;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IConfiguracionSubprocesoOrigenService;
import mx.profuturo.nci.business.vo.ConfigSubprocesoOrigenVO;
import mx.profuturo.nci.persistence.ConfigSubprocesoOrigenPersistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("configuracionSubprocesoOrigenService")
public class ConfiguracionSubprocesoOrigenServiceImpl implements  IConfiguracionSubprocesoOrigenService
{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguracionSubprocesoOrigenServiceImpl.class);
	
	@Autowired
	ConfigSubprocesoOrigenPersistence configSubprocesoOrigenPersistence;
	

	public List<ConfigSubprocesoOrigenVO> consultarTodo() throws MitBusinessException 
	{
		try
		{
			return this.configSubprocesoOrigenPersistence.selectAll();
			
		}
		catch(Exception ex)
		{
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(
							ErrorCodeService.EX_EXCEPTION,
							"Al Consultar Todo Cofig Subproceso Origen", new Object[] {
									"ConfiguracionSubprocesoOrigenServiceImpl", "consultarTodo()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
			
		}  			
		
	}

}
