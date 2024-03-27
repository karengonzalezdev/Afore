package mx.profuturo.nci.business.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.service.IIndicadoresService;
import mx.profuturo.nci.ws.webservice.indicadores.ConsultaIndicadoresBeanRequest;
import mx.profuturo.nci.ws.webservice.indicadores.ConsultaIndicadoresBeanRequest.ListaIDsIndicadores;
import mx.profuturo.nci.ws.webservice.indicadores.ConsultaIndicadoresBeanRequest.ListaNumerosCuenta;
import mx.profuturo.nci.ws.webservice.indicadores.IIndicadoresSoapWSConsultaBasicaResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/integration-core-config.xml",
		"classpath:/spring/app-nci-business-beans-test.xml", "classpath:/spring/app-nci-persistence-db-test.xml",
		"classpath:/spring/app-nci-persistence-mybatis-test.xml" })
public class IndicadoresServiceIT {
	private static final Logger LOGGER = LoggerFactory.getLogger(IndicadoresServiceIT.class);
	@Autowired
	IIndicadoresService indicadoresService;

	

	@Test
	public void consultarIT() throws BusinessException {
		try
		{
			
			ConsultaIndicadoresBeanRequest req = new ConsultaIndicadoresBeanRequest();
			
			ListaIDsIndicadores value = new ListaIDsIndicadores();
			List<Integer> listaIndicadores = new ArrayList<Integer>();
			listaIndicadores.add(58);
			value.setIdIndicador(listaIndicadores);
			req.setListaIDsIndicadores(value);
			
			ListaNumerosCuenta listaCuentas = new ListaNumerosCuenta();
			List<Long> numerosCuenta = new ArrayList<Long>();
			Long cuenta = (long) 0;
			numerosCuenta.add(cuenta);
			listaCuentas.setNumCuenta(numerosCuenta);

			req.setListaNumerosCuenta(listaCuentas);
			req.setListaIDsIndicadores(value);
			
			IIndicadoresSoapWSConsultaBasicaResponse respuesta = indicadoresService.consultar(listaIndicadores, numerosCuenta);
			LOGGER.info("salida response "+respuesta.getResponse().getListaCuentasIndividuales().getCuentaIndividual().isEmpty());
			/*IIndicadoresSoapWSConsultaBasicaResponse response = wsPortTypeFactory.indicadoresPortType().consultaBasica(req);
			LOGGER.debug(response.getResponse().getListaCuentasIndividuales().getCuentaIndividual().get(0).getListaConfiguracionesIndicadores().getConfiguracionIndicadores().get(0).getValorIndicador());*/
		}
		catch(Exception e)
		{
			LOGGER.error("Error consumiendo recurso ",e);
		}
	}
}
