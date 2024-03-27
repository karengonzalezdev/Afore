package mx.profuturo.nci.business.service.test;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.ICifrasGenerales;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.business.wrapped.CifrasGeneralesFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/integration-core-config.xml",
		"classpath:/spring/app-nci-business-beans-test.xml", "classpath:/spring/app-nci-persistence-db-test.xml",
		"classpath:/spring/app-nci-persistence-mybatis-test.xml" })
public class CifrasGeneralesServiceIT {

	@Autowired
	ICifrasGenerales cifrasGenerales;

	@Test
	public void consultarIT() throws BusinessException {

		try {

			File archivo = new File("/main/resources/reports/cifrasControl.jrxml");
			CifrasGeneralesFilter cifrasGeneralesFilter = new CifrasGeneralesFilter();

			ReporteVO cifras = cifrasGenerales.generarReport(cifrasGeneralesFilter);

		} catch (MitBusinessException ex) {
			System.err.println(ex.getExceptionDetails().getBaseException());
		}

	}
}
