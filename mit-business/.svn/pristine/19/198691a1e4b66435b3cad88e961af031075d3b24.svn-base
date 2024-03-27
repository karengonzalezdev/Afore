package mx.profuturo.nci.business.service.test;

import java.util.List;

import mx.profuturo.nci.business.service.IArchivoService;
import mx.profuturo.nci.business.vo.ArchivoVO;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jeveris.core.exception.impl.BusinessException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=
					  {
						"classpath:/spring/app-nci-business-beans-test.xml",
						"classpath:/spring/app-nci-persistence-db-test.xml",
						"classpath:/spring/app-nci-persistence-mybatis-test.xml"					
					  })
public class ArchivoServicesIT 
{
//	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArchivoServicesIT.class);
	@Autowired
	IArchivoService archivoService;
	
	
	
	@Test
	public void metodoTest()
	{
		
		try 
		{
			List<ArchivoVO> vos = archivoService.consultar(null);//"PREFT.DP.P01534.F150415.DISPAF.C001");
			
			if (CollectionUtils.isNotEmpty(vos))
			for(ArchivoVO vo : vos)
			{
				LOGGER.info("idArchivo= "+vo.getIdArchivo());	
			}
			
//			ArchivoVO vo = this.archivoService.consultarMaximoPorId("PREFT.DP.P01534.F150415.DISPAF.C001.t4v0",Short.valueOf("10"));
//			
//			if(vo != null)
//			
//			LOGGER.info("idArchivo= "+vo.getIdArchivo()+ ", Nombre= "+ vo.getNombre());

		} 
		catch (NumberFormatException e) 
		{
			LOGGER.error(e.getMessage(),e);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(),e);
		}
	}

}
