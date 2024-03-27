package mx.profuturo.nci.business.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.service.ICatalogosService;
import mx.profuturo.nci.business.vo.ParamCatalogoVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/integration-core-config.xml",
		"classpath:/spring/app-nci-business-beans-test.xml", "classpath:/spring/app-nci-persistence-db-test.xml",
		"classpath:/spring/app-nci-persistence-mybatis-test.xml" })
public class CatalogoServiceIT {
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogoServiceIT.class);
	@Autowired
	ICatalogosService catalogoService;

	

	@Test
	public void consultarIT() throws BusinessException {
		try
		{
			ParamCatalogoVO paramCatalogoVO = new ParamCatalogoVO();
			paramCatalogoVO.setIdTipoCatalogo((short)47);
			catalogoService.consultarLista(paramCatalogoVO);
		}
		catch(Exception e)
		{
			LOGGER.error("Error consumiendo recurso "+e.getMessage(),e);
		}
	}
}
