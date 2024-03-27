package mx.profuturo.nci.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.jeveris.core.exception.impl.BusinessException;
import com.jeveris.core.exception.impl.ExceptionDetails;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.ErrorCodeSoap;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ConfigSubprocesoOrigenVO;
import mx.profuturo.nci.business.vo.ConfiguracionSubprocesoVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.web.beans.ConfigSubprocesoOrigenBean;
import mx.profuturo.nci.web.beans.ConfiguracionSubprocesoBean;
import mx.profuturo.nci.web.beans.GenericCatalogoBean;
import mx.profuturo.nci.web.beans.ProcesoBean;
import mx.profuturo.nci.web.beans.SubprocesoBean;

public final class UtilMappingBean {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilMappingBean.class);

	public static List<ConfigSubprocesoOrigenBean> getListConfigSubprocesoOrigenBean(List<ConfigSubprocesoOrigenVO> listConfigSubOrigenVo)throws MitBusinessException
	{
			List<ConfigSubprocesoOrigenBean> listConfigSubOrigenBean=null;
		
		  try
		  {
				if(CollectionUtils.isNotEmpty(listConfigSubOrigenVo))
				{
					listConfigSubOrigenBean= new ArrayList<ConfigSubprocesoOrigenBean>();
					
					for(ConfigSubprocesoOrigenVO vo : listConfigSubOrigenVo)
					{
						listConfigSubOrigenBean.add(mappingConfigSubprocesoOrigenBean(vo));
						
					}
				}
				
				return listConfigSubOrigenBean;
			
		  }
		  catch(MitBusinessException ex)
		  {
			  throw ex;
		  }
		  catch (Exception ex) 
		  {
				MitBusinessException mitBusinessException = new MitBusinessException(
						GenerateExceptionDetails
								.generate(ErrorCodeSoap.EX_EXCEPTION,
										"Al Generar List Maping ConfigSubprocesoOrigen De VO a BEAN",
										new Object[] { "UtilMappingBean",
												"getListConfigSubprocesoOrigenBean()" }, ex));
				
				LOGGER.error(mitBusinessException.getMessage(),ex);

				throw mitBusinessException;
		 }		
	}

	public static ConfigSubprocesoOrigenBean mappingConfigSubprocesoOrigenBean(ConfigSubprocesoOrigenVO vo) throws MitBusinessException
	{
		ConfigSubprocesoOrigenBean configSubprocesoOrigenBean=null;
		  
		  try
		  {
		
			   if(vo != null)
			   {
				   configSubprocesoOrigenBean=new ConfigSubprocesoOrigenBean();
				   
				   mapVOToBean(configSubprocesoOrigenBean, vo);
				   
				   
				   if(vo.getSubproceso() != null)
				   {
					   
					   configSubprocesoOrigenBean.setSubproceso(mappingConfiguracionSubprocesoBean(vo.getSubproceso()));
					    
				   }
				   
				   if(vo.getOrigenArchivo() != null)
				   {
					   GenericCatalogoBean origenArchivo=new GenericCatalogoBean();
					   mapVOToBean(origenArchivo, vo.getOrigenArchivo());
					   
					   configSubprocesoOrigenBean.setOrigenArchivo(origenArchivo);
					   
				   }
				   

				   if(vo.getDetalleOrigen() != null)
				   {
					   GenericCatalogoBean detalleOrigen=new GenericCatalogoBean();
					   mapVOToBean(detalleOrigen, vo.getDetalleOrigen());
					   
					   configSubprocesoOrigenBean.setDetalleOrigen(detalleOrigen);
					   
				   }
				   
				   if(vo.getArchivoMovBancario() != null)
				   {
					   GenericCatalogoBean archivoMovBancario=new GenericCatalogoBean();
					   mapVOToBean(archivoMovBancario, vo.getArchivoMovBancario());
					   
					   configSubprocesoOrigenBean.setArchivoMovBancario(archivoMovBancario);
					   
				   }				   
				   
				   
				   
				
			   }
			   
			   return configSubprocesoOrigenBean;
			   
		  }		
		  catch(MitBusinessException ex)
		  {
			  throw ex;
		  }
		  catch (Exception ex) 
		  {
				MitBusinessException mitBusinessException = new MitBusinessException(
						GenerateExceptionDetails
								.generate(ErrorCodeSoap.EX_EXCEPTION,
										"Al Generar Maping ConfigSubprocesoOrigen De VO a BEAN",
										new Object[] { "UtilMappingBean",
												"mappingConfigSubprocesoOrigenBean()" }, ex));
				
				LOGGER.error(mitBusinessException.getMessage(),ex);

				throw mitBusinessException;
		 }
		  
		   		   		
	}	

	public static <BEAN,VO>void mapVOToBean(BEAN bean,VO vo) throws MitBusinessException{
		
		if(bean!=null && vo!=null){
			BeanWrapper source = new BeanWrapperImpl(vo);
			BeanWrapper target = new BeanWrapperImpl(bean);
			BeanMap type = BeanMap.getBeanMapOf(bean.getClass(),vo.getClass());
			try {
			if(type!=null && type.getClaseVo().equals(vo.getClass())){
				Set<String> propiedades = type.getMapaPropiedades().keySet();
				
				for(final String propertyName : propiedades){
			        target.setPropertyValue(
			            propertyName,
			            source.getPropertyValue(type.getMapaPropiedades().get(propertyName))			            							 
			        );
			    }
			}else{
				MitBusinessException mitBusinessException = new MitBusinessException(
						GenerateExceptionDetails.generate(
								ErrorCodeService.EX_EXCEPTION,
								"La clase "+bean.getClass()+" no tiene configurado mapeo para "+vo, new Object[] {
										"UtilMappingBean", "mapVOToBean()" }, null));
				
					LOGGER.error(mitBusinessException.getMessage());
				
					throw mitBusinessException;
			}
			} catch (Exception ex) {
				String errorCode = "2000", causeMessage = "PersistenceException Al Realizar Map VO to Bean";
				Throwable baseException = ex;

				ExceptionDetails exceptionDetails = new ExceptionDetails(errorCode,causeMessage);
				exceptionDetails.setBaseException(baseException);

				throw new MitBusinessException(exceptionDetails);
			}
		}
	}

	public static ConfiguracionSubprocesoBean mappingConfiguracionSubprocesoBean(ConfiguracionSubprocesoVO vo) throws MitBusinessException
	{
		try
		{
			ConfiguracionSubprocesoBean configSubprocesoBean=null;
			
			if(vo != null)
			{
				configSubprocesoBean=new ConfiguracionSubprocesoBean();
				
				mapVOToBean(configSubprocesoBean, vo);
				
				
				if(vo.getSubproceso() != null)
				{
					SubprocesoBean subprocesoBean=new SubprocesoBean();
					mapVOToBean(subprocesoBean, vo.getSubproceso());
					
					configSubprocesoBean.setSubproceso(subprocesoBean);
					
				}
				
				if(vo.getProceso() != null)
				{
					ProcesoBean procesoBean=new ProcesoBean();
					mapVOToBean(procesoBean, vo.getProceso());
					
					configSubprocesoBean.setProceso(procesoBean);
				}
				
				if(vo.getSaldoOpera() != null)
				{
					GenericCatalogoBean saldoOperaBean=new GenericCatalogoBean();
					mapVOToBean(saldoOperaBean, vo.getSaldoOpera());
					
					configSubprocesoBean.setSaldoOpera(saldoOperaBean);
				}
				
			}
			
			
			return configSubprocesoBean;
			
		}
		catch(MitBusinessException ex)
		{
			  throw ex;
		}		
		catch (Exception ex) 
		  {
			
			MitBusinessException mitBusinessException = new MitBusinessException(
						GenerateExceptionDetails
								.generate(ErrorCodeSoap.EX_EXCEPTION,
										"Al Generar Maping ConfiguracionSubproceso De VO a BEAN",
										new Object[] { "UtilMappingBean",
												"mappingConfiguracionSubprocesoBean()" }, ex));
				
				LOGGER.error(mitBusinessException.getMessage(),ex);
	
				throw mitBusinessException;
		 }		
	}

}