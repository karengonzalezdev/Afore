package mx.profuturo.nci.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import mx.profuturo.nci.business.exception.ErrorCodeSoap;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.vo.cif.Profuturo_CIFVo;
import mx.profuturo.nci.business.wrapped.NCI_CIFExtVo;
import mx.profuturo.nci.web.beans.NCI_CIFDataBean;

public final class UtilMapping 
{
	
	
	public static <BEAN,VO>void mapVoToBean(final BEAN bean,final VO vo) throws MitBusinessException
	{
		
		try {
		if(bean!=null && vo!=null){
			final BeanWrapper source = new BeanWrapperImpl(vo);
			final BeanWrapper target = new BeanWrapperImpl(bean);
			final BeanMap type = BeanMap.getBeanMapOf(bean.getClass(),vo.getClass());
			
			if(type!=null && type.getClaseVo().equals(vo.getClass()))
			{
				final Set<String> propiedades = type.getMapaPropiedades().keySet();

				for(final String propertyName : propiedades)
				{
			        target.setPropertyValue(
			            propertyName,
			            source.getPropertyValue(type.getMapaPropiedades().get(propertyName))
			        );
			    }
			}
			else
			{				
			
				MitBusinessException mitBusinessException = new MitBusinessException(
						GenerateExceptionDetails
								.generate(ErrorCodeSoap.EX_EXCEPTION,
										"La clase "+bean.getClass().getSimpleName()+" no tiene configurado mapeo para "+vo.getClass(),
										new Object[] { "UtilMapping",
												"mapVoToBean()" }, new Exception()));
				

				throw mitBusinessException;				
				
			}
		}
		} 
		catch (final Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION,
							"Al Generar Mapping " + bean.getClass().getSimpleName() + " TO " + vo.getClass().getSimpleName()+":"+ex.getMessage(),
							new Object[] { "UtilMapping", "mapVOToBean()" }, new Exception()));
			throw mitBusinessException;
		}
	}

	public static <BEAN, VO> void mapBeanToVO(BEAN bean, VO vo) throws MitBusinessException 
	{
		if (bean != null && vo != null) 
		{
			BeanWrapper source = new BeanWrapperImpl(bean);
			BeanWrapper target = new BeanWrapperImpl(vo);
			BeanMap type = BeanMap.getBeanMapOf(bean.getClass(), vo.getClass());

			if (type != null && type.getClaseVo().equals(vo.getClass())) 
			{
				Set<String> propiedades = type.getMapaPropiedades().keySet();
				
				for (final String propertyName : propiedades) 
				{
					target.setPropertyValue(type.getMapaPropiedades().get(propertyName),source.getPropertyValue(propertyName));
				}
			} else {

				MitBusinessException mitBusinessException = new MitBusinessException(
						GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION,
								"Al Generar Mapping " + bean.getClass().getSimpleName() + " TO " + vo.getClass().getSimpleName(),
								new Object[] { "UtilMapping", "mapBeanToVO()" }, new Exception()));

				throw mitBusinessException;
			}
		}
	}
	
	public static NCI_CIFDataBean mapCIFVoToBean(NCI_CIFExtVo vo) throws MitBusinessException {
		NCI_CIFDataBean bean = new NCI_CIFDataBean();
		mapVoToBean(bean, vo);
		bean.setIdItemNew(bean.getIdItem());
//		GenericCatalogoBean banco = new GenericCatalogoBean();
//		mapVoToBean(banco, vo.getBanco());
//		bean.setBanco(banco);
		return bean;
	}

	public static List<NCI_CIFDataBean> mapCIFListVoToBean(List<NCI_CIFExtVo> vos) throws MitBusinessException {
		List<NCI_CIFDataBean> beans = new ArrayList<NCI_CIFDataBean>();
		for(NCI_CIFExtVo vo:vos) {
			NCI_CIFDataBean bean = mapCIFVoToBean(vo);
			beans.add(bean);
		}
		return beans;
	}
	
	public static NCI_CIFExtVo mapCIFBeanToVo(NCI_CIFDataBean bean) throws MitBusinessException {
		NCI_CIFExtVo vo = new NCI_CIFExtVo();
		mapBeanToVO(bean, vo);
//		GenericCatalogoVO banco = new GenericCatalogoVO();
//		mapBeanToVO(bean.getBanco(), banco);
//		vo.setBanco(banco);
		return vo;
	}
	
	public static Profuturo_CIFVo mapPCIFBeanToVO(NCI_CIFDataBean bean) {
		Profuturo_CIFVo vo = new Profuturo_CIFVo();
		vo.setIdItem(bean.getIdItem());
		vo.setUnidadDeNegocio(bean.getUnidadDeNegocio());
		vo.setImporte(bean.getImporte());
		vo.setIdCliente(bean.getIdCliente());
		vo.setFechaItem(bean.getFechaItem());
		vo.setFechaContable(bean.getFechaContable());
		vo.setFechaIntroduccion(bean.getFechaIntroduccion());
		vo.setMetodoCobro(bean.getMetodoCobro());
		vo.setCondicionCobro(bean.getCondicionCobro());
		vo.setTipo(bean.getTipo());
		vo.setMotivo(bean.getMotivo());
		vo.setCodigoMoneda(bean.getCodigoMoneda());
		vo.setClaveBanco(bean.getBanco());
		vo.setCuenta(bean.getCuenta());
		vo.setTipoProceso(bean.getTipoProceso());
		vo.setEstatus(bean.getEstatus());
		vo.setSecuencia(bean.getSecuencia());
		
		return vo;
	}
	
	public static List<Profuturo_CIFVo> mapListPCIFBeanToVO(List<NCI_CIFDataBean> beans){
		List<Profuturo_CIFVo> vos = new ArrayList<Profuturo_CIFVo>();
		for(NCI_CIFDataBean bean:beans) {
			vos.add(mapPCIFBeanToVO(bean));
		}
		return vos;
	}
}
