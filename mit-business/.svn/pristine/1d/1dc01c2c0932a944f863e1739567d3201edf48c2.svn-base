package mx.profuturo.nci.business.service.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IConciliacionService;
import mx.profuturo.nci.business.vo.ConciliacionVO;
import mx.profuturo.nci.business.vo.DiversificacionConciliacionVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.wrapped.ConciliacionFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/integration-core-config.xml",
		"classpath:/spring/app-nci-business-beans-test.xml", "classpath:/spring/app-nci-persistence-db-test.xml",
		"classpath:/spring/app-nci-persistence-mybatis-test.xml" })
public class ConciliacionServiceIT {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConciliacionServiceIT.class);
	
	@Autowired
	IConciliacionService conciliacionService;

	@Test
	public void test()
	{
//		this.insertar();
//		this.update();
		this.consultar();
		
	}
	
	public void insertar() 
	{

		try 
		{
			ConciliacionVO conciliacionVO = new ConciliacionVO();

			conciliacionVO.setFolio("201702011209270053");
			conciliacionVO.setClaveSolicitud("123456");
			conciliacionVO.setNumeroOrden("999");
			conciliacionVO.setOrigenAportacion(new GenericCatalogoVO(new Short("431")));
			conciliacionVO.setNumeroCuentaIndividual(90006002L);
			conciliacionVO.setNumeroPago(3);
			conciliacionVO.setClaveBanco(new Short("461"));
//			conciliacionVO.setCuentaBanco("9998777");
//			conciliacionVO.setImporte(new BigDecimal(150.50));
//			conciliacionVO.setReferenciaSubcuentaApovol(66);
//			conciliacionVO.setFechaCargaArchivo(new Date());
//			conciliacionVO.setNombreCliente("Armando");
//			conciliacionVO.setApellidoClientePaterno("Lopez");
			conciliacionVO.setApellidoClienteMaterno("Sanchez");
			conciliacionVO.setCurp("YALL720319H1");
			conciliacionVO.setRfc("PAPG8612RFC");
			conciliacionVO.setNss(241286286L);
			conciliacionVO.setCorreoElectronico("armando_lopez@gmail.com");
			conciliacionVO.setCelular(553789L);
			conciliacionVO.setEmpresa(new GenericCatalogoVO(new Short("453")));
			conciliacionVO.setTipoNomina(new GenericCatalogoVO(new Short("520")));
			conciliacionVO.setNumeroEmpleado(7327);
			conciliacionVO.setFechaPagoApovol(new Date());
			conciliacionVO.setFechaValorApovol(new Date());
			conciliacionVO.setFolioTransaccion("20161115800");
			conciliacionVO.setFolioProcesar("20171115869");
			conciliacionVO.setClaveRedComercial(new GenericCatalogoVO(new Short("604")));
			conciliacionVO.setSucursal(101L);
			conciliacionVO.setTipoAportacion(new Short("2"));
			conciliacionVO.setRegistroConciliado(new Short("1"));
			conciliacionVO.setMovimientoGenerado(new Short("3"));
			conciliacionVO.setMovimiento(250L);
			conciliacionVO.setUsuarioCreacion("JAVA-PU-CREACION");
			
			List<DiversificacionConciliacionVO> diversificaciones=new ArrayList<DiversificacionConciliacionVO>();
			
			DiversificacionConciliacionVO diver_1=new DiversificacionConciliacionVO();
			diver_1.setFondoApovol(new GenericCatalogoVO(new Short("741")));
			diver_1.setMonto(new BigDecimal(1001.51));
			diver_1.setPorcentaje(new Short("91"));
			diver_1.setUsuarioCreacion("JAVA-PU-CREACION");
			diversificaciones.add(diver_1);
			
			
			DiversificacionConciliacionVO diver_2=new DiversificacionConciliacionVO();
			diver_2.setFondoApovol(new GenericCatalogoVO(new Short("742")));
			diver_2.setMonto(new BigDecimal(1002.52));
			diver_2.setPorcentaje(new Short("92"));
			diver_2.setUsuarioCreacion("JAVA-PU-CREACION");
			diversificaciones.add(diver_2);

			DiversificacionConciliacionVO diver_3=new DiversificacionConciliacionVO();
			diver_3.setFondoApovol(new GenericCatalogoVO(new Short("743")));
			diver_3.setMonto(new BigDecimal(1003.53));
			diver_3.setPorcentaje(new Short("93"));
			diver_3.setUsuarioCreacion("JAVA-PU-CREACION");
			diversificaciones.add(diver_3);
			
			
			
			conciliacionVO.setDiversificaciones(diversificaciones);
			
			LOGGER.info("Estatus Insert = "+conciliacionService.insertar(conciliacionVO));
			
		} 
		catch (MitBusinessException ex) 
		{
			LOGGER.error(ex.getExceptionDetails().getBaseException().getMessage(),ex);
		}

	}
	
	
	
	public void update() 
	{

		try 
		{
			ConciliacionVO conciliacionVO = new ConciliacionVO();

			conciliacionVO.setFolio("201702011209270052");
			conciliacionVO.setClaveSolicitud("123452");
			conciliacionVO.setNumeroOrden("999");
			conciliacionVO.setOrigenAportacion(new GenericCatalogoVO(new Short("432")));
			conciliacionVO.setNumeroCuentaIndividual(90006003L);
			conciliacionVO.setNumeroPago(3);
			conciliacionVO.setClaveBanco(new Short("461"));
			conciliacionVO.setCuentaBanco("9998777");
			conciliacionVO.setImporte(new BigDecimal(150.50));
//			conciliacionVO.setReferenciaSubcuentaApovol(66);
			conciliacionVO.setFechaCargaArchivo(new Date());
			conciliacionVO.setNombreCliente("Armando");
			conciliacionVO.setApellidoClientePaterno("Lopez");
			conciliacionVO.setApellidoClienteMaterno("Sanchez dos");
			conciliacionVO.setCurp("YALL720319H14");
			conciliacionVO.setRfc("PAPG8612RFCA");
			conciliacionVO.setNss(241286286L);
			conciliacionVO.setCorreoElectronico("armando_lopez_2@gmail.com");
			conciliacionVO.setCelular(553789L);
			conciliacionVO.setEmpresa(new GenericCatalogoVO(new Short("453")));
			conciliacionVO.setTipoNomina(new GenericCatalogoVO(new Short("521")));
			conciliacionVO.setNumeroEmpleado(7327);
			conciliacionVO.setFechaPagoApovol(new Date());
			conciliacionVO.setFechaValorApovol(new Date());
			conciliacionVO.setFolioTransaccion("20161115800");
			conciliacionVO.setFolioProcesar("20171115869");
			conciliacionVO.setClaveRedComercial(new GenericCatalogoVO(new Short("604")));
			conciliacionVO.setSucursal(101L);
			conciliacionVO.setTipoAportacion(new Short("3"));
			conciliacionVO.setRegistroConciliado(new Short("0"));
			conciliacionVO.setMovimientoGenerado(new Short("4"));
			conciliacionVO.setMovimiento(250L);
			conciliacionVO.setUsuarioActualizacion("JAVA-PU-CREACION-UP");
			
			List<DiversificacionConciliacionVO> diversificaciones=new ArrayList<DiversificacionConciliacionVO>();
			
			DiversificacionConciliacionVO diver_1=new DiversificacionConciliacionVO();
			diver_1.setFondoApovol(new GenericCatalogoVO(new Short("741")));
			diver_1.setMonto(new BigDecimal(1002.51));
			diver_1.setPorcentaje(new Short("90"));
			diver_1.setUsuarioActualizacion("JAVA-PU-CREACION-UP");
			diversificaciones.add(diver_1);
			
			
			DiversificacionConciliacionVO diver_2=new DiversificacionConciliacionVO();
			diver_2.setFondoApovol(new GenericCatalogoVO(new Short("742")));
			diver_2.setMonto(new BigDecimal(1003.53));
			diver_2.setPorcentaje(new Short("93"));
			diver_2.setUsuarioActualizacion("JAVA-PU-CREACION-UP");
			diversificaciones.add(diver_2);

			DiversificacionConciliacionVO diver_3=new DiversificacionConciliacionVO();
			diver_3.setFondoApovol(new GenericCatalogoVO(new Short("743")));
			diver_3.setMonto(new BigDecimal(1004.54));
			diver_3.setPorcentaje(new Short("94"));
			diver_3.setUsuarioActualizacion("JAVA-PU-CREACION-UP");
			diversificaciones.add(diver_3);
			
			
			
			conciliacionVO.setDiversificaciones(diversificaciones);
			
			LOGGER.info("Estatus actualizar = "+conciliacionService.actualizar(conciliacionVO));
			
		} 
		catch (MitBusinessException ex) 
		{
			LOGGER.error(ex.getExceptionDetails().getBaseException().getMessage(),ex);
		}

	}	
	
	
	public void consultar()
	{
		try 
		{
			ConciliacionFilter conciliacionFilter=new ConciliacionFilter();
			
			conciliacionFilter.setIdConciliacion(96);

			List<ConciliacionVO> lt = conciliacionService.consultar(conciliacionFilter);
			
			if(CollectionUtils.isNotEmpty(lt))
			{
				for(ConciliacionVO vo : lt)
				{
					LOGGER.info(vo.toString());
				}
			}
			
			
		} 
		catch (MitBusinessException ex) 
		{
			LOGGER.error(ex.getExceptionDetails().getBaseException().toString(),ex);
		}		
	}
	
	public void consultarIT() throws BusinessException {

		try {
			
			conciliacionService.conciliarTotales("EFM","0");
		} catch (MitBusinessException ex) {
			LOGGER.error(ex.getExceptionDetails().getBaseException().getMessage(),ex);
		}

	}
	
}
