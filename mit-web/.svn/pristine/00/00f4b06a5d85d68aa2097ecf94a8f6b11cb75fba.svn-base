

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ParamCatalogoVO;
import mx.profuturo.nci.web.beans.GenericCatalogoBean;
import mx.profuturo.nci.web.service.IValidacionManualWebService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/integration-core-config.xml",
		"classpath:/spring/app-nci-business-beans-test.xml", "classpath:/spring/app-nci-persistence-db-test.xml",
		"classpath:/spring/app-nci-persistence-mybatis-test.xml" })
public class CatalogoServiceIT {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogoServiceIT.class);

	@Autowired
	IValidacionManualWebService bancosService;

	@Test
	public void consultarIT() throws BusinessException {

	/*	ParamCatalogoVO paramCatalogoVO = new ParamCatalogoVO();
		paramCatalogoVO.setIdTipoCatalogo((short)47);
		for(GenericCatalogoBean generic : bancosService.obtenerBancos())
		{
			LOGGER.info(generic.getValor());
		}
*/
	}
}
