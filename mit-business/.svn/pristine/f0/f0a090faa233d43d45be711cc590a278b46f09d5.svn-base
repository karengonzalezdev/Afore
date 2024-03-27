package mx.profuturo.nci.business.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.ISolicitudService;
import mx.profuturo.nci.business.vo.DiversificacionVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.vo.SolicitudVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=
					  {
						"classpath:/spring/integration-core-config.xml",
						"classpath:/spring/app-nci-business-beans-test.xml",
						"classpath:/spring/app-nci-persistence-db-test.xml",
						"classpath:/spring/app-nci-persistence-mybatis-test.xml"
					  })
public class AltaSolicitudServiceIT 
{

	@Autowired
	ISolicitudService solicitudService;
	
	@Test
	public void consultarIT()
	{
		SolicitudVO solicitudVO=new SolicitudVO();
		
		try 
		{
			List<DiversificacionVO> listaDiversificacion = new ArrayList<DiversificacionVO>();
			DiversificacionVO diversificacionVO = new DiversificacionVO();
			diversificacionVO.setClaveSolicitud("20170126112538");
			diversificacionVO.setFolio("20170126112538");
			java.math.BigDecimal decimal = new java.math.BigDecimal("0.1");
			diversificacionVO.setMonto(decimal);
			GenericCatalogoVO fondoAportacionVoluntaria = new GenericCatalogoVO();
			fondoAportacionVoluntaria.setId((short)5);
			diversificacionVO.setFondoAportacionVoluntaria(fondoAportacionVoluntaria);
			diversificacionVO.setPorcentaje((short)15);
			diversificacionVO.setUsuarioActualizacion("JAVA");
			listaDiversificacion.add(diversificacionVO);		
			
			
			DiversificacionVO diversificacionVO1 = new DiversificacionVO();
			diversificacionVO1.setClaveSolicitud("20170126112538");
			diversificacionVO1.setFolio("20170126112538");
			java.math.BigDecimal decimal2 = new java.math.BigDecimal("33.12");
			diversificacionVO1.setMonto(decimal2);
			GenericCatalogoVO fondoAportacionVoluntaria2 = new GenericCatalogoVO();
			fondoAportacionVoluntaria.setId((short)1);
			diversificacionVO1.setFondoAportacionVoluntaria(fondoAportacionVoluntaria2);
			diversificacionVO1.setPorcentaje((short)15);
			diversificacionVO1.setUsuarioActualizacion("JAVA ACT FAIL");
			listaDiversificacion.add(diversificacionVO1);
			solicitudVO.setDiversificaciones(listaDiversificacion);
			
			
			solicitudService.actualizar(solicitudVO);
			
			
		} 
		catch (MitBusinessException ex) 
		{
			System.err.println(ex.getExceptionDetails().getBaseException());
		}
		
	}
}
