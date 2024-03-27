package mx.profuturo.nci.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IIndicadoresService;
import mx.profuturo.nci.ws.webservice.indicadores.ConsultaIndicadoresBeanRequest;
import mx.profuturo.nci.ws.webservice.indicadores.ConsultaIndicadoresBeanRequest.ListaIDsIndicadores;
import mx.profuturo.nci.ws.webservice.indicadores.ConsultaIndicadoresBeanRequest.ListaNumerosCuenta;
import mx.profuturo.nci.ws.webservice.indicadores.IIndicadoresSoapWSConsultaBasicaResponse;

@Service("indicadoresService")
public class IndicadoresServiceImpl implements IIndicadoresService {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndicadoresServiceImpl.class);
	
	@Autowired
	WSPortTypeFactory wsPortTypeFactory;

	public IIndicadoresSoapWSConsultaBasicaResponse consultar(List<Integer> listaIndicadores, List<Long> numerosCuenta)
			throws MitBusinessException {
		try
		{
			
			ConsultaIndicadoresBeanRequest req = new ConsultaIndicadoresBeanRequest();
			ListaIDsIndicadores value = new ListaIDsIndicadores();
			value.setIdIndicador(listaIndicadores);
			req.setListaIDsIndicadores(value);
			ListaNumerosCuenta listaCuentas = new ListaNumerosCuenta();
			listaCuentas.setNumCuenta(numerosCuenta);
			req.setListaNumerosCuenta(listaCuentas);
			req.setListaIDsIndicadores(value);
			return wsPortTypeFactory.indicadoresPortType().consultaBasica(req);
		}
		catch (BusinessException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al consultar indicador",
							new Object[] { "IndicadoresServiceImpl", "consultar indicador()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
		
	}

	
}
