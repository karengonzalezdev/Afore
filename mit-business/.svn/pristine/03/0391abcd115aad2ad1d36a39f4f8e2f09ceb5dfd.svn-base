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

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IBancosService;
import mx.profuturo.nci.business.vo.BancosVO;
import mx.profuturo.nci.business.wrapped.BancosFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/integration-core-config.xml",
		"classpath:/spring/app-nci-business-beans-test.xml", "classpath:/spring/app-nci-persistence-db-test.xml",
		"classpath:/spring/app-nci-persistence-mybatis-test.xml" })
public class BancosServiceIT {
	private static final Logger LOGGER = LoggerFactory.getLogger(BancosServiceIT.class);
	@Autowired
	IBancosService bancosService;

	@Test
	public void consultarIT() throws BusinessException {
/*
		try {
			BancosFilter bancosFilter = new BancosFilter();
			List<Short> aport = new ArrayList<Short>();
			aport.add((short) 290);
			aport.add((short) 291);
			bancosFilter.setOrigenAportaciones(aport);
			LOGGER.info("consultando");
			for (BancosVO bancos : bancosService.consultar(bancosFilter)) {
				LOGGER.info(bancos.getFolio());
				LOGGER.info(""+bancos.getIdBanco());
			}
			LOGGER.info("termino");
		} catch (MitBusinessException ex) {
			LOGGER.error(ex.getExceptionDetails().getBaseException().getMessage(),ex);
		}
*/
	}
	
	/*@Test
	public void guardarIT() throws BusinessException {

		try {
			BancosVO bancosVo = new BancosVO();
			
			LOGGER.info(bancosService.actualizar(bancosVo));
		} catch (MitBusinessException ex) {
			LOGGER.error(ex.getExceptionDetails().getBaseException().getMessage(),ex);
		}

	}*/
}
