package mx.profuturo.nci.business.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IMapaReferenciasService;
import mx.profuturo.nci.business.vo.MapaReferenciasVO;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = { "classpath:/spring/integration-core-config.xml",
		"classpath:/spring/app-nci-business-beans-test.xml", "classpath:/spring/app-nci-persistence-db-test.xml",
		"classpath:/spring/app-nci-persistence-mybatis-test.xml" })

public class ReferenciasServiceIT {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReferenciasServiceIT.class);

	private static final String LOCALPART_CURP = "curp";
	private static final String LOCALPART_NSS = "nss";
	private static final String LOCALPART_NUM_CUENTA = "numeroCuenta";
	 
	@Autowired
	IMapaReferenciasService mapaReferenciasService;
	
	
	
	@Test
	public void consultarIT() throws BusinessException {
/*
		try {
			for(MapaReferenciasVO maps : mapaReferenciasService.consultar())
			{
				LOGGER.info(maps.getDescripcionItgy());
			}
		} catch (MitBusinessException e) {
			LOGGER.error(e.getMessage(),e);
		}*/
	}
}
