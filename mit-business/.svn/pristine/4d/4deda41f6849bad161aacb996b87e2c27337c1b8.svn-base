package mx.profuturo.nci.business.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IPreSolicitudService;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.vo.PreSolicitudVO;
import mx.profuturo.nci.business.vo.SolicitudVO;
import mx.profuturo.nci.business.wrapped.PreSolicitudFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=
					  {
						"classpath:/spring/integration-core-config.xml",
						"classpath:/spring/app-nci-business-beans-test.xml",
						"classpath:/spring/app-nci-persistence-db-test.xml",
						"classpath:/spring/app-nci-persistence-mybatis-test.xml"
					  })
public class PreSolicitudServiceIT 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PreSolicitudServiceIT.class);
	@Autowired
	IPreSolicitudService preSolicitudPersistence;
	
	@Test
	public void consultarIT()
	{
		PreSolicitudVO solicitudVO=new PreSolicitudVO();
		
		try 
		{
			GenericCatalogoVO medioNotificacion = new GenericCatalogoVO();
			medioNotificacion.setId((short)842);
			solicitudVO.setMedioNotificacion(medioNotificacion);
			solicitudVO.setClaveSolicitud("1");
			preSolicitudPersistence.actualizar(solicitudVO);
			/*PreSolicitudFilter preSolicitudFilter = new PreSolicitudFilter();
			for(PreSolicitudVO presolicitud : preSolicitudPersistence.consultar(preSolicitudFilter))
			{
				LOGGER.info("cuetna "+presolicitud.getMedioNotificacion().getValor());
			}*/
		} 
		catch (MitBusinessException ex) 
		{
			LOGGER.error(ex.getExceptionDetails().getBaseException().getMessage(),ex);
		}
		
	}
}
