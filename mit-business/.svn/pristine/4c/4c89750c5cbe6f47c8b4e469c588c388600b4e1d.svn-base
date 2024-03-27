package mx.profuturo.nci.business.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IFilenetService;
import profuturo.mx.iib.apovol.filenet.filenetservice.v1.types.SubirArchivosReq;

@Service("filenetService")
public class FilenetServiceImpl implements IFilenetService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FilenetServiceImpl.class);
	
	@Autowired
	WSPortTypeFactory wsPortTypeFactory;

	public int guardarArchivos(SubirArchivosReq subirArchivosReq) throws MitBusinessException {
		try
		{
			boolean estatusGuardado = true;//wsPortTypeFactory.fileNetPortType().subirArchivos(subirArchivosReq).getMensajes().getMensaje().get(0).getCodigo().equalsIgnoreCase("1001");
			return estatusGuardado ? 1 : 0;
		}
		/*catch (BusinessException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		}*/ catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Identificar Cliente",
							new Object[] { "IdentificarClienteServiceImpl", "consultarClienteBasico()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

	

}
