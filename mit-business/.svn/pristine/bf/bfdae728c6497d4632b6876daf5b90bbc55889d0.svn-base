package mx.profuturo.nci.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IConciliacionService;
import mx.profuturo.nci.business.service.IOrdenesService;
import mx.profuturo.nci.business.vo.DiversificacionConciliacionVO;
import mx.profuturo.nci.business.vo.DiversificacionOrdenesVO;
import mx.profuturo.nci.business.vo.IndicadorVO;
import mx.profuturo.nci.business.vo.OrdenesVO;
import mx.profuturo.nci.business.wrapped.OrdenesFilter;
import mx.profuturo.nci.persistence.DiversificacionConciliacionPersistence;
import mx.profuturo.nci.persistence.DiversificacionOrdenesPersistence;
import mx.profuturo.nci.persistence.OrdenesPersistence;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("ordenesService")
public class OrdenesServiceImpl implements IOrdenesService 
{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrdenesServiceImpl.class);
	
	
	@Autowired
	OrdenesPersistence ordenesPersistence;
	
	@Autowired
	DiversificacionOrdenesPersistence diversificacionOrdenesPersistence;
	
	@Autowired
	IConciliacionService conciliacionService;
	
	@Autowired
	DiversificacionConciliacionPersistence diversificacionConciliacionPersistence;

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Boolean guardar(OrdenesVO ordenesVO) throws MitBusinessException 
	{
		try{
			final int numInsertParent = this.ordenesPersistence.insert(ordenesVO);
			LOGGER.info("ordenesVO.getNumeroOrden(): " + ordenesVO.getNumeroOrden());
			if(numInsertParent > 0){			
					if(CollectionUtils.isNotEmpty(ordenesVO.getDiversificacionesOrdenes())){
						for(DiversificacionOrdenesVO div : ordenesVO.getDiversificacionesOrdenes()){
							div.setNumeroOrden(ordenesVO.getNumeroOrden().toString());
							if(div.getMonto()==null && div.getPorcentaje()!=null){
							div.setMonto(calcularMontoDelPorcentaje(ordenesVO.getImporte(),div.getPorcentaje()));
							}
						}
						final int numInsertChild =	this.diversificacionOrdenesPersistence.insertAll(ordenesVO.getDiversificacionesOrdenes());
						
						if(numInsertChild > 0){
							if(ordenesVO.getConciliacionVO() != null 
									&& ordenesVO.getConciliacionVO().getIdConciliacion() != null 
									&& ordenesVO.getConciliacionVO().getIdConciliacion() > 0){
								//Aqui se actualiza la conciliacion
								ordenesVO.getConciliacionVO().setNumeroOrden(ordenesVO.getNumeroOrden().toString());
								ordenesVO.getConciliacionVO().setRegistroConciliado(new Short("1"));//1 indica que ya esta conciliado
								ordenesVO.getConciliacionVO().setUsuarioActualizacion(ordenesVO.getUsuarioCreacion());
								
								ordenesVO.getConciliacionVO().setDiversificaciones(
										setDiversificacionConciliado(
												ordenesVO.getConciliacionVO().getIdConciliacion(),
												ordenesVO.getDiversificacionesOrdenes(), 
												ordenesVO.getUsuarioCreacion()
											)
								);
								boolean updateOk = this.conciliacionService.actualizar(ordenesVO.getConciliacionVO());
								
//								La actualizacion de las diversificaciones de conciliacion se hace en el metodo del servicio actualizar
//								if(updateOk){
//									this.diversificacionConciliacionPersistence.insertAll(ordenesVO.getConciliacionVO().getDiversificaciones());
//								}else{
//									throw new Exception("No se pudo conciliar el numero de orden");
//								}
								
							}
							return true;
						}else{
							throw new Exception("Numero de registros(DiversificacionOrdenes) insertados es cero");
						}					
					}else{
						return true;
					}
			}else{
				throw new Exception("Numero de registros(Ordenes) insertados es cero");
			}
			
		}catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al Guardar Ordenes ",
					new Object[] { "OrdenesServiceImpl", "guardar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;			
		}				
	}


	private BigDecimal calcularMontoDelPorcentaje(BigDecimal importe, Short porcentaje) {
		BigDecimal nuevoMonto=BigDecimal.ZERO;
		BigDecimal aux=new BigDecimal(porcentaje).divide(new BigDecimal(100));
		nuevoMonto=importe.multiply(aux);
		return nuevoMonto;
	}


	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Boolean actualizar(OrdenesVO ordenesVO) throws MitBusinessException 
	{
		Boolean estatusTransaccion=Boolean.FALSE;
		
		try
		{
			final int numUpdateParent = this.ordenesPersistence.update(ordenesVO);
			
			if(numUpdateParent > 0)
			{
				if(CollectionUtils.isNotEmpty(ordenesVO.getDiversificacionesOrdenes()))
				{
				
					for(DiversificacionOrdenesVO diversificacionOrdenesVO : ordenesVO.getDiversificacionesOrdenes())
					{
						final int numUpdateChild =	this.diversificacionOrdenesPersistence.update(diversificacionOrdenesVO);
						
						if(numUpdateChild > 0)
						{
							continue;
						}
						else
						{
							throw new Exception("Numero de registros(DiversificacionOrdenes) actualizados es cero");
						}
					}
					
					estatusTransaccion = Boolean.TRUE;
					
				}
				else
				{
					estatusTransaccion = Boolean.TRUE;
				}
				
			}
			else
			{
				throw new Exception("Numero de registros(Ordenes) actualizados es cero");
			}
			
			
			return estatusTransaccion;
			
		}
		catch(Exception ex)
		{
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al Actualizar Ordenes ",
					new Object[] { "OrdenesServiceImpl", "actualizar()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;			
		}		
	}


	public List<OrdenesVO> consultaSelectiva(OrdenesFilter ordenesFilter)throws MitBusinessException 
	{
		try
		{
			return this.ordenesPersistence.selectSelective(ordenesFilter);
			
		}
		catch(Exception ex)
		{
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al Consultar Ordenes ",
					new Object[] { "OrdenesServiceImpl", "consultaSelectiva()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;			
		}		
		
	}
	
	/**
	 * Metodo para setear las diversificaciones conciliadas
	 * 
	 * @return
	 */
	private List<DiversificacionConciliacionVO> setDiversificacionConciliado(Integer idConciliacion, List<DiversificacionOrdenesVO> lstDiversificacionOrdenes,String usuarioCreacion){
		List<DiversificacionConciliacionVO> lstDiverConciliado = new ArrayList<DiversificacionConciliacionVO>();
		DiversificacionConciliacionVO diverConciliado;
		for(DiversificacionOrdenesVO diversificacionOrdenes : lstDiversificacionOrdenes){
			diverConciliado = new DiversificacionConciliacionVO();
			diverConciliado.setIdConciliacion(idConciliacion);
			diverConciliado.setFondoApovol(diversificacionOrdenes.getFondoApovol());
			diverConciliado.setMonto(diversificacionOrdenes.getMonto());
			diverConciliado.setPorcentaje(diversificacionOrdenes.getPorcentaje());
			diverConciliado.setUsuarioCreacion(usuarioCreacion);
			
			lstDiverConciliado.add(diverConciliado);
		}
		
		return lstDiverConciliado;
	}


	public List<IndicadorVO> agregarInfoCuenta(String cuenta) throws MitBusinessException {
		try
		{
			return this.ordenesPersistence.agregarInfoCuenta(cuenta);
			
		}
		catch(Exception ex)
		{
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al agregar información de cuenta ",
					new Object[] { "OrdenesServiceImpl", "agregarInfoCuenta()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;			
		}		
		
	}

}
