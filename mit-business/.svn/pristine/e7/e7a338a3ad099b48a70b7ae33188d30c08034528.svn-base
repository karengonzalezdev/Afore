package mx.profuturo.nci.business.service.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IOrdenesService;
import mx.profuturo.nci.business.vo.DiversificacionOrdenesVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.vo.OrdenesVO;
import mx.profuturo.nci.business.wrapped.OrdenesFilter;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=
					  {
						"classpath:/spring/integration-core-config.xml",
						"classpath:/spring/app-nci-business-beans-test.xml",
						"classpath:/spring/app-nci-persistence-db-test.xml",
						"classpath:/spring/app-nci-persistence-mybatis-test.xml"
					  })
public class OrdenesServiceIT 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(OrdenesServiceIT.class);
	@Autowired
	IOrdenesService ordenesService; 

	
	@Test	
	public void ejecutar()
	{
		//this.guardar();
		//this.update();
		this.select();
	}
	
	private void guardar()
	{
		OrdenesVO ordenesVO=new OrdenesVO();
		
		try 
		{
			String numeroOrden = "1000116";
			String usuarioCreacion = "PU-Java";
			
			ordenesVO.setNumeroOrden(numeroOrden);
			ordenesVO.setNumCuentaIndividual(90006006L);
			ordenesVO.setEstatus(new GenericCatalogoVO(new Short("75")));
			ordenesVO.setImporte(new BigDecimal(150006.50));
			ordenesVO.setUsuarioCreacion(usuarioCreacion);
			
			List<DiversificacionOrdenesVO> diversificacionesOrdenes=new ArrayList<DiversificacionOrdenesVO>();
			
			DiversificacionOrdenesVO diverOrdenesVO_1=new DiversificacionOrdenesVO();
			diverOrdenesVO_1.setNumeroOrden(numeroOrden);
			diverOrdenesVO_1.setFondoApovol(new GenericCatalogoVO(new Short("741")));
			diverOrdenesVO_1.setMonto(new BigDecimal(25000.80));
//			diverOrdenesVO_1.setPorcentaje(new Short("50"));
			diverOrdenesVO_1.setUsuarioCreacion(usuarioCreacion);

			DiversificacionOrdenesVO diverOrdenesVO_2=new DiversificacionOrdenesVO();
			diverOrdenesVO_2.setNumeroOrden(numeroOrden);
			diverOrdenesVO_2.setFondoApovol(new GenericCatalogoVO(new Short("742")));
			diverOrdenesVO_2.setMonto(new BigDecimal(25100.80));
//			diverOrdenesVO_2.setPorcentaje(new Short("50"));
			diverOrdenesVO_2.setUsuarioCreacion(usuarioCreacion);

			DiversificacionOrdenesVO diverOrdenesVO_3=new DiversificacionOrdenesVO();
			diverOrdenesVO_3.setNumeroOrden(numeroOrden);
//			diverOrdenesVO_3.setFondoApovol(new GenericCatalogoVO(new Short("742")));
//			diverOrdenesVO_3.setMonto(new BigDecimal(25100.80));
//			diverOrdenesVO_2.setPorcentaje(new Short("50"));
//			diverOrdenesVO_3.setUsuarioCreacion(usuarioCreacion);
			
			DiversificacionOrdenesVO diverOrdenesVO_4=new DiversificacionOrdenesVO();
			diverOrdenesVO_4.setNumeroOrden(numeroOrden);
			diverOrdenesVO_4.setFondoApovol(new GenericCatalogoVO(new Short("743")));
			diverOrdenesVO_4.setMonto(new BigDecimal(2550.91));
			diverOrdenesVO_4.setPorcentaje(new Short("60"));
			diverOrdenesVO_4.setUsuarioCreacion(usuarioCreacion);
			
			
			diversificacionesOrdenes.add(diverOrdenesVO_1);
			diversificacionesOrdenes.add(diverOrdenesVO_2);
			diversificacionesOrdenes.add(diverOrdenesVO_3);
			diversificacionesOrdenes.add(diverOrdenesVO_4);
			
			ordenesVO.setDiversificacionesOrdenes(diversificacionesOrdenes);
			
			LOGGER.info("Estatus Guardo = "+this.ordenesService.guardar(ordenesVO));
		} 
		catch (MitBusinessException ex) 
		{
			LOGGER.error(ex.getExceptionDetails().getBaseException().getLocalizedMessage(),ex);
		}
	}
	
	private void update()
	{
		OrdenesVO ordenesVO=new OrdenesVO();
		
		try 
		{
			String numeroOrden = "10001033";
			String usuarioActualizacion = "PU-Java-Update";
			
			ordenesVO.setNumeroOrden(numeroOrden);
			ordenesVO.setNumCuentaIndividual(90006009L);
			ordenesVO.setEstatus(new GenericCatalogoVO(new Short("755")));
			ordenesVO.setImporte(new BigDecimal(15.55));
			ordenesVO.setUsuarioActualizacion(usuarioActualizacion);
			
			List<DiversificacionOrdenesVO> diversificacionesOrdenes=new ArrayList<DiversificacionOrdenesVO>();
			
			DiversificacionOrdenesVO diverOrdenesVO_1=new DiversificacionOrdenesVO();
			diverOrdenesVO_1.setNumeroOrden(numeroOrden);
			diverOrdenesVO_1.setFondoApovol(new GenericCatalogoVO(new Short("741")));
			diverOrdenesVO_1.setMonto(new BigDecimal(25000.81));
			diverOrdenesVO_1.setPorcentaje(new Short("10"));
			diverOrdenesVO_1.setUsuarioActualizacion(usuarioActualizacion);

			DiversificacionOrdenesVO diverOrdenesVO_2=new DiversificacionOrdenesVO();
			diverOrdenesVO_2.setNumeroOrden(numeroOrden);
			diverOrdenesVO_2.setFondoApovol(new GenericCatalogoVO(new Short("742")));
			diverOrdenesVO_2.setMonto(new BigDecimal(25100.42));
			diverOrdenesVO_2.setPorcentaje(new Short("11"));
			diverOrdenesVO_2.setUsuarioActualizacion(usuarioActualizacion);

			DiversificacionOrdenesVO diverOrdenesVO_3=new DiversificacionOrdenesVO();
			diverOrdenesVO_3.setNumeroOrden(numeroOrden);
			diverOrdenesVO_3.setFondoApovol(new GenericCatalogoVO(new Short("743")));
			diverOrdenesVO_3.setMonto(new BigDecimal(25100.43));
			diverOrdenesVO_3.setPorcentaje(new Short("12"));
			diverOrdenesVO_3.setUsuarioActualizacion(usuarioActualizacion);
			
			DiversificacionOrdenesVO diverOrdenesVO_4=new DiversificacionOrdenesVO();
			diverOrdenesVO_4.setNumeroOrden(numeroOrden);
			diverOrdenesVO_4.setFondoApovol(new GenericCatalogoVO(new Short("745")));
			diverOrdenesVO_4.setMonto(new BigDecimal(25509.44));
			diverOrdenesVO_4.setPorcentaje(new Short("13"));
			diverOrdenesVO_4.setUsuarioActualizacion(usuarioActualizacion);
			
			
			diversificacionesOrdenes.add(diverOrdenesVO_1);
			diversificacionesOrdenes.add(diverOrdenesVO_2);
			diversificacionesOrdenes.add(diverOrdenesVO_3);
			diversificacionesOrdenes.add(diverOrdenesVO_4);
			
			ordenesVO.setDiversificacionesOrdenes(diversificacionesOrdenes);			

			
			LOGGER.info("Estatus Actualizado = "+this.ordenesService.actualizar(ordenesVO));
		} 
		catch (MitBusinessException ex) 
		{
			LOGGER.error(ex.getExceptionDetails().getBaseException().getMessage(),ex);
		}		
		
	}
	
	private void select()
	{
		try
		{
			OrdenesFilter ordenesFilter=new OrdenesFilter();
			
			ordenesFilter.setNumeroOrden("1000103");
//			ordenesFilter.setNumCuentaIndividual(90006009L);
//			ordenesFilter.setEstatusOrdenes(
//												new ArrayList<Short>() 
//												{
//													/**
//													 * 
//													 */
//													private static final long serialVersionUID = 1L;
//
//													{
//														add(new Short("754"));
//														add(new Short("755"));
//													}
//												}
//											);
			
			List<OrdenesVO> listOrdenesVo = this.ordenesService.consultaSelectiva(ordenesFilter);
			
			if(CollectionUtils.isNotEmpty(listOrdenesVo))
			{
				for(OrdenesVO vo : listOrdenesVo)
				{
					LOGGER.info(vo.toString());
				}
			}
			
			
		}			
		catch (MitBusinessException ex) 
		{
			LOGGER.error(ex.getExceptionDetails().getBaseException().getMessage(),ex);
		}			
	}
	
	
	
}
