package mx.profuturo.nci.business.service.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.ISumConciliacionService;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.wrapped.SumConciliacionFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/integration-core-config.xml",
		"classpath:/spring/app-nci-business-beans-test.xml", "classpath:/spring/app-nci-persistence-db-test.xml",
		"classpath:/spring/app-nci-persistence-mybatis-test.xml" })
public class SumConciliacionServiceIT {
	private static final Logger LOGGER = LoggerFactory.getLogger(SumConciliacionServiceIT.class);
	@Autowired
	ISumConciliacionService sumConciliacionService;

	

	@Test
	public void actualizarIT() throws BusinessException {

		try {
			LOGGER.info("ejecutando ");
			SumConciliacionFilter sumConciliacionFilter = new SumConciliacionFilter();
			sumConciliacionFilter.setFolio("3312");
			GenericCatalogoVO empresa = new GenericCatalogoVO();
			empresa.setId((short)3);
			sumConciliacionFilter.setEmpresa(empresa);
			GenericCatalogoVO origenApo = new GenericCatalogoVO();
			origenApo.setId((short)2);
			BigDecimal importe = new BigDecimal("5552.50");
			sumConciliacionFilter.setImporte(importe);
			LOGGER.info(""+sumConciliacionService.actualizarSumConciliacion(sumConciliacionFilter));
		} catch (MitBusinessException ex) {
			LOGGER.error("Error:",ex.getExceptionDetails().getBaseException());
		}

	}

	
}
