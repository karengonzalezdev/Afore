package mx.secure.nci.ws.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {		
		"classpath:/spring/integration-core-config.xml"
		})
public class ConfiguracionSoapServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguracionSoapServiceTest.class);
	
	@Test
	public void consultaMatrizConvivenciaTest(){
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Caso de prueba para CONSULTA MATRIZ DE CONVIVENCIA.");
		}
	}
	
	

}
