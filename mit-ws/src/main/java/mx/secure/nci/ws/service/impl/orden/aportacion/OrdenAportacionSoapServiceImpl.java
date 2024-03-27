package mx.secure.nci.ws.service.impl.orden.aportacion;

import mx.secure.nci.business.exception.ErrorCodeSoap;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.IOrdenesService;
import mx.secure.nci.business.vo.OrdenesVO;
import mx.secure.nci.business.wrapped.OrdenesFilter;
import mx.secure.nci.ws.beans.DiversificacionOrdenAportacionBean;
import mx.secure.nci.ws.beans.OrdenAportacionBean;
import mx.secure.nci.ws.beans.request.OrdenAportacionActualizarBeanRequest;
import mx.secure.nci.ws.beans.request.OrdenAportacionConsultarBeanRequest;
import mx.secure.nci.ws.beans.request.OrdenAportacionInsertarBeanRequest;
import mx.secure.nci.ws.beans.response.OrdenAportacionConsultarBeanResponse;
import mx.secure.nci.ws.service.orden.aportacion.IOrdenAportacionSoapService;
import mx.secure.nci.ws.util.UtilMapping;
import mx.secure.nci.ws.util.enums.TransaccionMappingEnum;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.ws.service.exception.WebServiceException;

@Service("ordenAportacionSoapService")
public class OrdenAportacionSoapServiceImpl  implements IOrdenAportacionSoapService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(OrdenAportacionSoapServiceImpl.class);
	
	private static final String EXCEPTION_REQUEST_NULO="Request O OrdenAportacion es nulo";
	private static final String EXCEPTION_ATRIBUTO_NUMERO_ORDEN_NULO_VACIO="Atributo NumeroOrden es nulo o vacio";
	private static final String EXCEPTION_ATRIBUTO_FONDO_secure_NULO="Atributo Fondosecure.Id es nulo";
	private static final String EXCEPTION_ATRIBUTO_DIVERSIFICACION_NULO="Atributo Diversificacion es nulo";

	@Autowired
	IOrdenesService ordenesService; 
	
	@Override
	public OrdenAportacionConsultarBeanResponse consultar(OrdenAportacionConsultarBeanRequest request) throws WebServiceException
	{
		 OrdenesFilter ordenesFilter=null;
				
		try 
		{
			if(request != null)
			{				
				ordenesFilter=new OrdenesFilter();
				
				ordenesFilter.setNumCuentaIndividual(request.getNumCuentaIndividual());
				ordenesFilter.setNumeroOrden(request.getNumeroOrden());
				ordenesFilter.setEstatusOrdenes(request.getEstatusOrdenes());
			}
			
			
			return new OrdenAportacionConsultarBeanResponse(UtilMapping.mappingListOrdenesVO(this.ordenesService.consultaSelectiva(ordenesFilter)));
						
			
		} 
		 catch (MitBusinessException ex) 
		{
				throw new WebServiceException(ex.getExceptionDetails());
		} 
		catch (Exception ex) 
		{
				WebServiceException webServiceException = new WebServiceException(
						GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
								new Object[] { "OrdenAportacionSoapServiceImpl", "consultar(...)" }, ex));

				LOGGER.error(webServiceException.getMessage(), ex);

				throw webServiceException;
		}
	}

	@Override
	public String insertar(OrdenAportacionInsertarBeanRequest request) 
	{
		try
		{
			if(request != null && request.getOrdenAportacion() != null)
			{
				final OrdenAportacionBean ordenAportacion=request.getOrdenAportacion();
				
//				if(StringUtils.isNotEmpty(ordenAportacion.getNumeroOrden()))
//				{
					if(CollectionUtils.isNotEmpty(ordenAportacion.getDiversificaciones()))
					{
						for(DiversificacionOrdenAportacionBean diverOrdenBean : ordenAportacion.getDiversificaciones())
						{
							if(diverOrdenBean != null)
							{
								if(diverOrdenBean.getFondosecure() != null && diverOrdenBean.getFondosecure().getId() != null)
								{
									continue;	
								}
								else
								{
									throw new IllegalArgumentException(EXCEPTION_ATRIBUTO_FONDO_secure_NULO);	
								}
							}
							else
							{
								throw new IllegalArgumentException(EXCEPTION_ATRIBUTO_DIVERSIFICACION_NULO);	
							}
						}
					}
					
//				}
//				else
//				{
//					throw new IllegalArgumentException(EXCEPTION_ATRIBUTO_NUMERO_ORDEN_NULO_VACIO);	
//				}
				OrdenesVO o=UtilMapping.mappingOrdenAportacionBean(request.getOrdenAportacion(),TransaccionMappingEnum.MAPPING_TO_INSERTAR);
				Boolean isInserted =  this.ordenesService.guardar(o);
				
				return o.getNumeroOrden();
				
			}
			else
			{
				throw new IllegalArgumentException(EXCEPTION_REQUEST_NULO);
			}
			
		}
		catch (MitBusinessException ex) 
		{
				throw new WebServiceException(ex.getExceptionDetails());
		} 
		catch(IllegalArgumentException ex)
		{
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_ILLEGAL_ARGUMENT_EXCEPTION, "En WebService",
							new Object[] { ex.getMessage() }, ex));

			LOGGER.error(webServiceException.getMessage(), ex);

			throw webServiceException;			
		}
		catch (Exception ex) 
		{
				WebServiceException webServiceException = new WebServiceException(
						GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
								new Object[] { "OrdenAportacionSoapServiceImpl", "insertar(...)" }, ex));
	
				LOGGER.error(webServiceException.getMessage(), ex);
	
				throw webServiceException;
		}		
	}

	@Override
	public Boolean actualizar(OrdenAportacionActualizarBeanRequest request) 
	{
		try
		{
			if(request != null && request.getOrdenAportacion() != null)
			{
				final OrdenAportacionBean ordenAportacion=request.getOrdenAportacion();
				
				if(StringUtils.isNotEmpty(ordenAportacion.getNumeroOrden()))
				{
					if(CollectionUtils.isNotEmpty(ordenAportacion.getDiversificaciones()))
					{
						for(DiversificacionOrdenAportacionBean diverOrdenBean : ordenAportacion.getDiversificaciones())
						{
							if(diverOrdenBean != null)
							{
								if(diverOrdenBean.getFondosecure() != null && diverOrdenBean.getFondosecure().getId() != null)
								{
									continue;	
								}
								else
								{
									throw new IllegalArgumentException(EXCEPTION_ATRIBUTO_FONDO_secure_NULO);	
								}
							}
							else
							{
								throw new IllegalArgumentException(EXCEPTION_ATRIBUTO_DIVERSIFICACION_NULO);	
							}
						}
					}
					
				}
				else
				{
					throw new IllegalArgumentException(EXCEPTION_ATRIBUTO_NUMERO_ORDEN_NULO_VACIO);	
				}
				
				
				
				return this.ordenesService.actualizar(UtilMapping.mappingOrdenAportacionBean(request.getOrdenAportacion(),TransaccionMappingEnum.MAPPING_TO_ACTUALIZAR));								
				
			}
			else
			{
				throw new IllegalArgumentException(EXCEPTION_REQUEST_NULO);
			}
			
		}
		catch (MitBusinessException ex) 
		{
				throw new WebServiceException(ex.getExceptionDetails());
		} 
		catch(IllegalArgumentException ex)
		{
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_ILLEGAL_ARGUMENT_EXCEPTION, "En WebService",
							new Object[] { ex.getMessage() }, ex));

			LOGGER.error(webServiceException.getMessage(), ex);

			throw webServiceException;			
		}
		catch (Exception ex) 
		{
				WebServiceException webServiceException = new WebServiceException(
						GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
								new Object[] { "OrdenAportacionSoapServiceImpl", "actualizar(...)" }, ex));
	
				LOGGER.error(webServiceException.getMessage(), ex);
	
				throw webServiceException;
		}
	}

}
