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
import mx.profuturo.nci.business.service.IArchivoGeneradoService;
import mx.profuturo.nci.business.wrapped.ArchivoDomiciliacionfilter;
import mx.profuturo.nci.business.wrapped.SolicitudFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=
					  {
						"classpath:/spring/integration-core-config.xml",
						"classpath:/spring/app-nci-business-beans-test.xml",
						"classpath:/spring/app-nci-persistence-db-test.xml",
						"classpath:/spring/app-nci-persistence-mybatis-test.xml"
					  })
public class SolicitudServiceIT 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(SolicitudServiceIT.class);
	@Autowired
	IArchivoGeneradoService archivoGeneradoService;
	
	@Test
	public void consultarIT() throws BusinessException
	{
		SolicitudFilter solicitudFilter=new SolicitudFilter();
		
		try 
		{
			ArchivoDomiciliacionfilter archivoDomiciliacionfilter = new ArchivoDomiciliacionfilter();
			
			LOGGER.info(""+archivoGeneradoService.lista(archivoDomiciliacionfilter).size());
		} 
		catch (MitBusinessException ex) 
		{
			LOGGER.error(ex.getExceptionDetails().getBaseException().getMessage(),ex);
		}
		
	}
}
