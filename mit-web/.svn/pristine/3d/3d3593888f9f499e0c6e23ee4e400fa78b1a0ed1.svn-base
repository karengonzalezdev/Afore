package mx.profuturo.nci.web.service.impl;

import java.util.List;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IConfiguracionSubprocesoOrigenService;
import mx.profuturo.nci.business.vo.ConfigSubprocesoOrigenVO;
import mx.profuturo.nci.web.beans.ConfigSubprocesoOrigenBean;
import mx.profuturo.nci.web.service.IDepositoArchivoWebService;
import mx.profuturo.nci.web.util.UtilMappingBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("depositoArchivoWebService")
public class DepositoArchivoWebServiceImpl implements IDepositoArchivoWebService 
{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepositoArchivoWebServiceImpl.class);

	@Autowired
	IConfiguracionSubprocesoOrigenService configuracionSubprocesoOrigenService;
	
	public List<ConfigSubprocesoOrigenBean> obtenerConfigSubprocesoOrigen()throws MitBusinessException 
	{		
		try
		{
					
			List<ConfigSubprocesoOrigenVO>  listConfigSubOrigenVo = this.configuracionSubprocesoOrigenService.consultarTodo();

			return UtilMappingBean.getListConfigSubprocesoOrigenBean(listConfigSubOrigenVo);	
		
		}
		catch (MitBusinessException ex) 
		{
			throw ex;
			
		}
		catch(final Exception ex)
		{
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(
							ErrorCodeService.EX_EXCEPTION,
							"Al Validar Nombre Del Archivo ", new Object[] {
									"DepositoArchivoWebServiceImpl", "validarNombreArchivo()" }, ex));

				LOGGER.error(mitBusinessException.getMessage(),ex);

				throw mitBusinessException;
		}	
		
		
	}

}
