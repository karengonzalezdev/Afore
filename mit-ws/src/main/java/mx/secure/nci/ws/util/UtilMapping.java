package mx.secure.nci.ws.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mx.secure.nci.business.exception.ErrorCodeSoap;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.CifrasGeneralesTipoVO;
import mx.secure.nci.business.vo.CifrasGeneralesVO;
import mx.secure.nci.business.vo.ConciliacionVO;
import mx.secure.nci.business.vo.DiversificacionConciliacionVO;
import mx.secure.nci.business.vo.DiversificacionOrdenesVO;
import mx.secure.nci.business.vo.DiversificacionVO;
import mx.secure.nci.business.vo.ElementoVO;
import mx.secure.nci.business.vo.FrecuenciaVO;
import mx.secure.nci.business.vo.GenericCatalogoVO;
import mx.secure.nci.business.vo.OrdenesVO;
import mx.secure.nci.business.vo.PreSolicitudVO;
import mx.secure.nci.business.vo.SeccionVO;
import mx.secure.nci.business.vo.SolicitudVO;
import mx.secure.nci.ws.beans.CifrasGeneralesBean;
import mx.secure.nci.ws.beans.CifrasGeneralesPorTipoBean;
import mx.secure.nci.ws.beans.ConciliacionBean;
import mx.secure.nci.ws.beans.DiversificacionBean;
import mx.secure.nci.ws.beans.DiversificacionConciliacionBean;
import mx.secure.nci.ws.beans.DiversificacionOrdenAportacionBean;
import mx.secure.nci.ws.beans.DiversificacionPreSolBean;
import mx.secure.nci.ws.beans.ElementoBean;
import mx.secure.nci.ws.beans.FrecuenciaBean;
import mx.secure.nci.ws.beans.GenericoCatalogoBean;
import mx.secure.nci.ws.beans.OrdenAportacionBean;
import mx.secure.nci.ws.beans.PreSolicitudDomiciliacionBean;
import mx.secure.nci.ws.beans.SeccionBean;
import mx.secure.nci.ws.beans.SolicitudDomiciliacionBean;
import mx.secure.nci.ws.util.enums.PresolicitudEnum;
import mx.secure.nci.ws.util.enums.TransaccionMappingEnum;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.jeveris.core.ws.service.exception.WebServiceException;

public final class UtilMapping {

	public static <BEAN, VO> void mapVoToBean(final BEAN bean, final VO vo) throws MitBusinessException {

		try {
			if (bean != null && vo != null) {
				final BeanWrapper source = new BeanWrapperImpl(vo);
				final BeanWrapper target = new BeanWrapperImpl(bean);
				final BeanMap type = BeanMap.getBeanMapOf(bean.getClass(), vo.getClass());

				if (type != null && type.getClaseVo().equals(vo.getClass())) {
					final Set<String> propiedades = type.getMapaPropiedades().keySet();

					for (final String propertyName : propiedades) {
						target.setPropertyValue(propertyName,
								source.getPropertyValue(type.getMapaPropiedades().get(propertyName)));
					}
				} else {
					MitBusinessException mitBusinessException = new MitBusinessException(
							GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION,
									"La clase " + bean.getClass().getSimpleName() + " no tiene configurado mapeo para " + vo.getClass(),
									new Object[] { "UtilMapping", "mapVoToBean()" }, new Exception()));

					throw mitBusinessException;

				}
			}
		} catch (Exception ex) {
			WebServiceException webServiceException = new WebServiceException(GenerateExceptionDetails.generate(
					ErrorCodeSoap.EX_EXCEPTION, "Al Generar Mapping " + vo.getClass() + " TO " + bean.getClass(),
					new Object[] { "UtilMapping", "mapVoToBean()" }, ex));

			throw webServiceException;
		}
	}

	public static <BEAN, VO> void mapBeanToVO(BEAN bean, VO vo) throws MitBusinessException {
		if (bean != null && vo != null) {
			BeanWrapper source = new BeanWrapperImpl(bean);
			BeanWrapper target = new BeanWrapperImpl(vo);
			BeanMap type = BeanMap.getBeanMapOf(bean.getClass(), vo.getClass());

			if (type != null && type.getClaseVo().equals(vo.getClass())) {
				Set<String> propiedades = type.getMapaPropiedades().keySet();

				for (final String propertyName : propiedades) {
					target.setPropertyValue(type.getMapaPropiedades().get(propertyName),
							source.getPropertyValue(propertyName));
				}
			} else {

				MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
						ErrorCodeSoap.EX_EXCEPTION, "Al Generar Mapping " + bean.getClass().getSimpleName() + " TO " + vo.getClass().getSimpleName(),
						new Object[] { "UtilMapping", "mapBeanToVO()" }, new Exception()));

				throw mitBusinessException;
			}
		}
	}

	public static List<SolicitudDomiciliacionBean> mappingListSolicitudDomiciliacionVO(
			List<SolicitudVO> listSolicitudDomiciliacionVO) throws MitBusinessException {
		List<SolicitudDomiciliacionBean> listBean = null;

		try {
			if (CollectionUtils.isNotEmpty(listSolicitudDomiciliacionVO)) {
				listBean = new ArrayList<SolicitudDomiciliacionBean>();

				for (SolicitudVO vo : listSolicitudDomiciliacionVO) {
					listBean.add(mappingSolicitudDomiciliacionVO(vo));
				}
			}

		} catch (MitBusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "Al Generar Maping List VO to BEAN:"+ex.getMessage(),
							new Object[] { "UtilMapping", "mappingListSolicitudDomiciliacionVO()" }, ex));

			throw mitBusinessException;
		}

		return listBean;
	}

	public static List<PreSolicitudDomiciliacionBean> mappingListPreSolicitudDomiciliacionVO(
			List<PreSolicitudVO> listPreSolicitudDomiciliacionVO) throws MitBusinessException {
		List<PreSolicitudDomiciliacionBean> listBean = null;

		try {
			if (CollectionUtils.isNotEmpty(listPreSolicitudDomiciliacionVO)) {
				listBean = new ArrayList<PreSolicitudDomiciliacionBean>();

				for (PreSolicitudVO vo : listPreSolicitudDomiciliacionVO) {
					listBean.add(mappingPreSolicitudDomiciliacionVO(vo));
				}
			}

		} catch (MitBusinessException ex) {
			throw ex;
		} catch (Exception ex) {

			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "Al Generar Maping List VO to BEAN",
							new Object[] { "UtilMapping", "mappingListSolicitudDomiciliacionVO()" }, ex));

			throw mitBusinessException;
		}

		return listBean;
	}

	public static SolicitudDomiciliacionBean mappingSolicitudDomiciliacionVO(SolicitudVO vo)
			throws MitBusinessException {
		SolicitudDomiciliacionBean bean = null;

		try {

			if (vo != null) {
				bean = new SolicitudDomiciliacionBean();

				mapVoToBean(bean, vo);

				if (vo.getLineaNegocio() != null) {
					GenericoCatalogoBean lineaNegocioBean = new GenericoCatalogoBean();
					mapVoToBean(lineaNegocioBean, vo.getLineaNegocio());
					bean.setLineaNegocio(lineaNegocioBean);
				}

				if (vo.getOrigenSolicitud() != null) {
					GenericoCatalogoBean origenSolicitudBean = new GenericoCatalogoBean();
					mapVoToBean(origenSolicitudBean, vo.getOrigenSolicitud());
					bean.setOrigenSolicitud(origenSolicitudBean);
				}

				if (vo.getTipoCuenta() != null) {
					GenericoCatalogoBean tipoCuentaBean = new GenericoCatalogoBean();
					mapVoToBean(tipoCuentaBean, vo.getTipoCuenta());
					bean.setTipoCuenta(tipoCuentaBean);
				}

				if (vo.getPeriodo() != null) {
					GenericoCatalogoBean periodoBean = new GenericoCatalogoBean();
					mapVoToBean(periodoBean, vo.getPeriodo());
					bean.setPeriodo(periodoBean);
				}

				if (vo.getFrecuenciaInicial() != null) {
					FrecuenciaBean frecuenciaBean = new FrecuenciaBean();
					mapVoToBean(frecuenciaBean, vo.getFrecuenciaInicial());

					if (vo.getFrecuenciaInicial().getDiaSemana() != null) {
						GenericoCatalogoBean diaSemanaBean = new GenericoCatalogoBean();
						mapVoToBean(diaSemanaBean, vo.getFrecuenciaInicial().getDiaSemana());
						frecuenciaBean.setDiaSemana(diaSemanaBean);
					}

					bean.setFrecuenciaInicial(frecuenciaBean);
				}

				if (vo.getFrecuenciaFinal() != null) {
					FrecuenciaBean frecuenciaBean = new FrecuenciaBean();
					mapVoToBean(frecuenciaBean, vo.getFrecuenciaFinal());

					if (vo.getFrecuenciaFinal().getDiaSemana() != null) {
						GenericoCatalogoBean diaSemanaBean = new GenericoCatalogoBean();
						mapVoToBean(diaSemanaBean, vo.getFrecuenciaFinal().getDiaSemana());
						frecuenciaBean.setDiaSemana(diaSemanaBean);
					}

					bean.setFrecuenciaFinal(frecuenciaBean);
				}

				if (vo.getCompaniaCelular() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getCompaniaCelular());
					bean.setCompaniaCelular(genericBean);
				}
				if (vo.getEstatusTraspaso() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getEstatusTraspaso());
					bean.setEstatusTraspaso(genericBean);
				}

				if (vo.getEstatusSolicitud() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getEstatusSolicitud());
					bean.setEstatusSolicitud(genericBean);
				}

				if (vo.getEstatusCargo() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getEstatusCargo());
					bean.setEstatusCargo(genericBean);
				}

				if (vo.getRechazoBanco() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getRechazoBanco());
					bean.setRechazoBanco(genericBean);
				}

				if (vo.getPorcentajeIncremento() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getPorcentajeIncremento());
					bean.setPorcentajeIncremento(genericBean);
				}

				if (vo.getPeriodoIncrementabilidad() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getPeriodoIncrementabilidad());
					bean.setPeriodoIncrementabilidad(genericBean);
				}

				if (vo.getMoneda() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getMoneda());
					bean.setMoneda(genericBean);
				}
				if (vo.getClaveBanco() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getClaveBanco());
					bean.setClaveBanco(genericBean);
				}
				if (vo.getMedioNotificacion() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getMedioNotificacion());
					bean.setMedioNotificacion(genericBean);
				}
				if (vo.getOrigenAportacion() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getOrigenAportacion());
					bean.setOrigenAportacion(genericBean);
				}
				if (CollectionUtils.isNotEmpty(vo.getDiversificaciones())) {
					List<DiversificacionBean> ltDiversificacionBean = new ArrayList<DiversificacionBean>();
					DiversificacionBean diversificacionBean = null;

					for (DiversificacionVO diversificacionVo : vo.getDiversificaciones()) {
						if (diversificacionVo != null) {
							diversificacionBean = new DiversificacionBean();
							mapVoToBean(diversificacionBean, diversificacionVo);

							if (diversificacionVo.getFondoAportacionVoluntaria() != null) {
								GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
								mapVoToBean(genericBean, diversificacionVo.getFondoAportacionVoluntaria());
								diversificacionBean.setFondoAportacionVoluntaria(genericBean);
							}

							ltDiversificacionBean.add(diversificacionBean);

						}
					}

					bean.setDiversificaciones(ltDiversificacionBean);
				}

			}

			return bean;
		} catch (MitBusinessException ex) {
			throw ex;
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "Al Generar Maping VO to BEAN:"+ex.getMessage(),
							new Object[] { "UtilMapping", "mappingSolicitudDomiciliacionVO()" }, ex));

			throw mitBusinessException;
		}

	}

	public static PreSolicitudDomiciliacionBean mappingPreSolicitudDomiciliacionVO(PreSolicitudVO vo)
			throws MitBusinessException {
		PreSolicitudDomiciliacionBean bean = null;

		try {

			if (vo != null) {
				bean = new PreSolicitudDomiciliacionBean();

				mapVoToBean(bean, vo);

				if (vo.getLineaNegocio() != null) {
					GenericoCatalogoBean lineaNegocioBean = new GenericoCatalogoBean();
					mapVoToBean(lineaNegocioBean, vo.getLineaNegocio());
					bean.setLineaNegocio(lineaNegocioBean);
				}

				if (vo.getOrigenSolicitud() != null) {
					GenericoCatalogoBean origenSolicitudBean = new GenericoCatalogoBean();
					mapVoToBean(origenSolicitudBean, vo.getOrigenSolicitud());
					bean.setOrigenSolicitud(origenSolicitudBean);
				}

				if (vo.getTipoCuenta() != null) {
					GenericoCatalogoBean tipoCuentaBean = new GenericoCatalogoBean();
					mapVoToBean(tipoCuentaBean, vo.getTipoCuenta());
					bean.setTipoCuenta(tipoCuentaBean);
				}

				if (vo.getPeriodo() != null) {
					GenericoCatalogoBean periodoBean = new GenericoCatalogoBean();
					mapVoToBean(periodoBean, vo.getPeriodo());
					bean.setPeriodo(periodoBean);
				}
				if (vo.getMedioNotificacion() != null) {
					GenericoCatalogoBean medioBean = new GenericoCatalogoBean();
					mapVoToBean(medioBean, vo.getMedioNotificacion());
					bean.setMedioNotificacion(medioBean);
				}
				if (vo.getFrecuenciaInicial() != null) {
					FrecuenciaBean frecuenciaBean = new FrecuenciaBean();
					mapVoToBean(frecuenciaBean, vo.getFrecuenciaInicial());

					if (vo.getFrecuenciaInicial().getDiaSemana() != null) {
						GenericoCatalogoBean diaSemanaBean = new GenericoCatalogoBean();
						mapVoToBean(diaSemanaBean, vo.getFrecuenciaInicial().getDiaSemana());
						frecuenciaBean.setDiaSemana(diaSemanaBean);
					}

					bean.setFrecuenciaInicial(frecuenciaBean);
				}

				if (vo.getFrecuenciaFinal() != null) {
					FrecuenciaBean frecuenciaBean = new FrecuenciaBean();
					mapVoToBean(frecuenciaBean, vo.getFrecuenciaFinal());

					if (vo.getFrecuenciaFinal().getDiaSemana() != null) {
						GenericoCatalogoBean diaSemanaBean = new GenericoCatalogoBean();
						mapVoToBean(diaSemanaBean, vo.getFrecuenciaFinal().getDiaSemana());
						frecuenciaBean.setDiaSemana(diaSemanaBean);
					}

					bean.setFrecuenciaFinal(frecuenciaBean);
				}
				if (vo.getRechazoSolicitud() != null) {
					GenericoCatalogoBean rechazoSolicitud = new GenericoCatalogoBean();
					mapVoToBean(rechazoSolicitud, vo.getRechazoSolicitud());
					bean.setRechazoSolicitud(rechazoSolicitud);
				}

				if (vo.getCompaniaCelular() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getCompaniaCelular());
					bean.setCompaniaCelular(genericBean);
				}

				if (vo.getEstatusSolicitud() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getEstatusSolicitud());
					bean.setEstatusSolicitud(genericBean);
				}

				if (vo.getPorcentajeIncremento() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getPorcentajeIncremento());
					bean.setPorcentajeIncremento(genericBean);
				}

				if (vo.getPeriodoIncrementabilidad() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getPeriodoIncrementabilidad());
					bean.setPeriodoIncrementabilidad(genericBean);
				}

				if (vo.getMoneda() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getMoneda());
					bean.setMoneda(genericBean);
				}

				if (vo.getClaveBanco() != null) {
					GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
					mapVoToBean(genericBean, vo.getClaveBanco());
					bean.setClaveBanco(genericBean);
				}
				if (CollectionUtils.isNotEmpty(vo.getDiversificaciones())) {
					List<DiversificacionPreSolBean> ltDiversificacionBean = new ArrayList<DiversificacionPreSolBean>();
					DiversificacionPreSolBean diversificacionBean = null;

					for (DiversificacionVO diversificacionVo : vo.getDiversificaciones()) {
						if (diversificacionVo != null) {
							diversificacionBean = new DiversificacionPreSolBean();
							mapVoToBean(diversificacionBean, diversificacionVo);

							if (diversificacionVo.getFondoAportacionVoluntaria() != null) {
								GenericoCatalogoBean genericBean = new GenericoCatalogoBean();
								mapVoToBean(genericBean, diversificacionVo.getFondoAportacionVoluntaria());
								diversificacionBean.setFondoAportacionVoluntaria(genericBean);
							}

							ltDiversificacionBean.add(diversificacionBean);

						}
					}

					bean.setDiversificaciones(ltDiversificacionBean);
				}
			}

			return bean;
		} catch (MitBusinessException ex) {
			throw ex;
		} catch (Exception ex) {

			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "Al Generar Maping VO to BEAN",
							new Object[] { "UtilMapping", "mappingSolicitudDomiciliacionVO()" }, ex));

			throw mitBusinessException;
		}

	}

	public static PreSolicitudVO mappingPreSolicitudDomiciliacionBean(final PreSolicitudDomiciliacionBean bean,
			final PresolicitudEnum presolicitudEnum) throws MitBusinessException {
		PreSolicitudVO vo = null;
		try {

			if (bean != null) {
				vo = new PreSolicitudVO();

				mapBeanToVO(bean, vo);

				if (bean.getLineaNegocio() != null) {
					GenericCatalogoVO catVo = new GenericCatalogoVO();
					mapBeanToVO(bean.getLineaNegocio(), catVo);
					vo.setLineaNegocio(catVo);
				}

				if (bean.getOrigenSolicitud() != null) {
					GenericCatalogoVO catVo = new GenericCatalogoVO();
					mapBeanToVO(bean.getOrigenSolicitud(), catVo);
					vo.setOrigenSolicitud(catVo);
				}

				if (bean.getTipoCuenta() != null) {
					GenericCatalogoVO catVo = new GenericCatalogoVO();
					mapBeanToVO(bean.getTipoCuenta(), catVo);
					vo.setTipoCuenta(catVo);
				}

				if (bean.getClaveBanco() != null) {
					GenericCatalogoVO catVo = new GenericCatalogoVO();
					mapBeanToVO(bean.getClaveBanco(), catVo);
					vo.setClaveBanco(catVo);
				}

				if (bean.getPeriodo() != null) {
					GenericCatalogoVO catVo = new GenericCatalogoVO();
					mapBeanToVO(bean.getPeriodo(), catVo);
					vo.setPeriodo(catVo);
				}

				if (bean.getFrecuenciaInicial() != null) {
					FrecuenciaVO frecuenciaVO = new FrecuenciaVO();
					mapBeanToVO(bean.getFrecuenciaInicial(), frecuenciaVO);

					if (bean.getFrecuenciaInicial().getDiaSemana() != null) {
						GenericCatalogoVO catVo = new GenericCatalogoVO();
						mapBeanToVO(bean.getFrecuenciaInicial().getDiaSemana(), catVo);
						frecuenciaVO.setDiaSemana(catVo);
					}

					vo.setFrecuenciaInicial(frecuenciaVO);
				}

				if (bean.getFrecuenciaFinal() != null) {
					FrecuenciaVO frecuenciaVO = new FrecuenciaVO();
					mapBeanToVO(bean.getFrecuenciaFinal(), frecuenciaVO);

					if (bean.getFrecuenciaFinal().getDiaSemana() != null) {
						GenericCatalogoVO catVo = new GenericCatalogoVO();
						mapBeanToVO(bean.getFrecuenciaFinal().getDiaSemana(), catVo);
						frecuenciaVO.setDiaSemana(catVo);
					}

					vo.setFrecuenciaFinal(frecuenciaVO);
				}

				if (bean.getCompaniaCelular() != null) {
					GenericCatalogoVO catVo = new GenericCatalogoVO();
					mapBeanToVO(bean.getCompaniaCelular(), catVo);
					vo.setCompaniaCelular(catVo);
				}

				if (bean.getEstatusSolicitud() != null) {
					GenericCatalogoVO catVo = new GenericCatalogoVO();
					mapBeanToVO(bean.getEstatusSolicitud(), catVo);
					vo.setEstatusSolicitud(catVo);
				}

				if (bean.getRechazoSolicitud() != null) {
					GenericCatalogoVO catVo = new GenericCatalogoVO();
					mapBeanToVO(bean.getRechazoSolicitud(), catVo);
					vo.setRechazoSolicitud(catVo);
				}

				if (bean.getPorcentajeIncremento() != null) {
					GenericCatalogoVO catVo = new GenericCatalogoVO();
					mapBeanToVO(bean.getPorcentajeIncremento(), catVo);
					vo.setPorcentajeIncremento(catVo);
				}

				if (bean.getPeriodoIncrementabilidad() != null) {
					GenericCatalogoVO catVo = new GenericCatalogoVO();
					mapBeanToVO(bean.getPeriodoIncrementabilidad(), catVo);
					vo.setPeriodoIncrementabilidad(catVo);
				}

				if (bean.getMoneda() != null) {
					GenericCatalogoVO catVo = new GenericCatalogoVO();
					mapBeanToVO(bean.getMoneda(), catVo);
					vo.setMoneda(catVo);
				}
				if (bean.getMedioNotificacion() != null) {
					GenericCatalogoVO genericBean = new GenericCatalogoVO();
					mapBeanToVO(bean.getMedioNotificacion(), genericBean);
					vo.setMedioNotificacion(genericBean);
				}
				if (CollectionUtils.isNotEmpty(bean.getDiversificaciones())) {
					List<DiversificacionVO> diversificaciones = new ArrayList<DiversificacionVO>();

					for (DiversificacionPreSolBean diversificacionBean : bean.getDiversificaciones()) {
						DiversificacionVO diversificacionVo = new DiversificacionVO();
						mapBeanToVO(diversificacionBean, diversificacionVo);

						diversificacionVo.setClaveSolicitud(bean.getClaveSolicitud());

						if (presolicitudEnum.equals(PresolicitudEnum.MAPPING_TO_INSERTAR)) {
							diversificacionVo.setUsuarioCreacion(bean.getUsuario());
						} else if (presolicitudEnum.equals(PresolicitudEnum.MAPPING_TO_ACTUALIZAR)) {
							diversificacionVo.setUsuarioActualizacion(bean.getUsuario());
						}

						if (diversificacionBean.getFondoAportacionVoluntaria() != null) {
							GenericCatalogoVO catVo = new GenericCatalogoVO();
							mapBeanToVO(diversificacionBean.getFondoAportacionVoluntaria(), catVo);
							diversificacionVo.setFondoAportacionVoluntaria(catVo);
						}

						diversificaciones.add(diversificacionVo);

					}

					vo.setDiversificaciones(diversificaciones);
				}


				if (presolicitudEnum.equals(PresolicitudEnum.MAPPING_TO_INSERTAR)) {
					vo.setUsuarioCreacion(bean.getUsuario());
				} else if (presolicitudEnum.equals(PresolicitudEnum.MAPPING_TO_ACTUALIZAR)) {
					vo.setUsuarioActualizacion(bean.getUsuario());
				}

			}

			return vo;

		} catch (MitBusinessException ex) {
			throw ex;
		} catch (Exception ex) {

			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "Al Generar Mapping BEAN to VO",
							new Object[] { "UtilMapping", "mappingPreSolicitudDomiciliacionBean()" }, ex));

			throw mitBusinessException;
		}

	}

	public static SolicitudVO mappingSolicitudDomiciliacionBean(SolicitudDomiciliacionBean solicitudDomiciliacion) throws MitBusinessException{
		SolicitudVO solicitudDomiciliacionVO = new SolicitudVO();
		
		solicitudDomiciliacionVO.setUsuarioCreacion(solicitudDomiciliacion.getUsuario());
		mapBeanToVO(solicitudDomiciliacion,solicitudDomiciliacionVO); 
		
		if (solicitudDomiciliacion.getLineaNegocio() != null) {
			GenericCatalogoVO lineaNegocio = new GenericCatalogoVO();
			mapBeanToVO(solicitudDomiciliacion.getLineaNegocio(), lineaNegocio);
			solicitudDomiciliacionVO.setLineaNegocio(lineaNegocio);
		}
		if (solicitudDomiciliacion.getOrigenSolicitud() != null) {
			GenericCatalogoVO origenSolicitud = new GenericCatalogoVO();
			mapBeanToVO(solicitudDomiciliacion.getOrigenSolicitud(), origenSolicitud);
			solicitudDomiciliacionVO.setOrigenSolicitud(origenSolicitud);
		}
		if (solicitudDomiciliacion.getTipoCuenta() != null) {
			GenericCatalogoVO tipoCuenta = new GenericCatalogoVO();
			mapBeanToVO(solicitudDomiciliacion.getTipoCuenta(), tipoCuenta);
			solicitudDomiciliacionVO.setTipoCuenta(tipoCuenta);
		}
		if (solicitudDomiciliacion.getClaveBanco() != null) {
			GenericCatalogoVO claveBanco = new GenericCatalogoVO();
			mapBeanToVO(solicitudDomiciliacion.getClaveBanco(), claveBanco);
			solicitudDomiciliacionVO.setClaveBanco(claveBanco);
		}				
		if (solicitudDomiciliacion.getPeriodo() != null) {
			GenericCatalogoVO periodo = new GenericCatalogoVO();
			mapBeanToVO(solicitudDomiciliacion.getPeriodo(), periodo);
			solicitudDomiciliacionVO.setPeriodo(periodo);
		}
		if (solicitudDomiciliacion.getFrecuenciaInicial() != null) {
			FrecuenciaVO frecuenciaInicial = new FrecuenciaVO();
			mapBeanToVO(solicitudDomiciliacion.getFrecuenciaInicial(), frecuenciaInicial);
			if (solicitudDomiciliacion.getFrecuenciaInicial().getDiaSemana() != null) {
				GenericCatalogoVO diaSemana = new GenericCatalogoVO();
				mapBeanToVO(solicitudDomiciliacion.getFrecuenciaInicial().getDiaSemana(),
						diaSemana);
				frecuenciaInicial.setDiaSemana(diaSemana);
			}
			solicitudDomiciliacionVO.setFrecuenciaInicial(frecuenciaInicial);
		}
		if (solicitudDomiciliacion.getFrecuenciaFinal() != null) {
			FrecuenciaVO frecuenciaFinal = new FrecuenciaVO();
			mapBeanToVO(solicitudDomiciliacion.getFrecuenciaFinal(), frecuenciaFinal);
			if (solicitudDomiciliacion.getFrecuenciaFinal().getDiaSemana() != null) {
				GenericCatalogoVO diaSemana = new GenericCatalogoVO();
				mapBeanToVO(solicitudDomiciliacion.getFrecuenciaFinal().getDiaSemana(), diaSemana);
				frecuenciaFinal.setDiaSemana(diaSemana);
			}
			solicitudDomiciliacionVO.setFrecuenciaFinal(frecuenciaFinal);
		}
		if (solicitudDomiciliacion.getEstatusTraspaso() != null) {
			GenericCatalogoVO estatusTraspaso = new GenericCatalogoVO();
			mapBeanToVO(solicitudDomiciliacion.getEstatusTraspaso(),estatusTraspaso);
			solicitudDomiciliacionVO.setEstatusTraspaso(estatusTraspaso);
		}
		if (solicitudDomiciliacion.getCompaniaCelular() != null) {
			GenericCatalogoVO companiaCelular = new GenericCatalogoVO();
			mapBeanToVO(solicitudDomiciliacion.getCompaniaCelular(),companiaCelular);
			solicitudDomiciliacionVO.setCompaniaCelular(companiaCelular);
		}
		if (solicitudDomiciliacion.getEstatusSolicitud() != null) {
			GenericCatalogoVO estatusSolicitud = new GenericCatalogoVO();
			mapBeanToVO(solicitudDomiciliacion.getEstatusSolicitud(), estatusSolicitud);
			solicitudDomiciliacionVO.setEstatusSolicitud(estatusSolicitud);
		}
		if (solicitudDomiciliacion.getEstatusCargo() != null) {
			GenericCatalogoVO estatusCargo = new GenericCatalogoVO();
			mapBeanToVO(solicitudDomiciliacion.getEstatusCargo(), estatusCargo);
			solicitudDomiciliacionVO.setEstatusCargo(estatusCargo);
		}
		if (solicitudDomiciliacion.getRechazoBanco() != null) {
			GenericCatalogoVO rechazoBanco = new GenericCatalogoVO();
			mapBeanToVO(solicitudDomiciliacion.getRechazoBanco(), rechazoBanco);
			solicitudDomiciliacionVO.setRechazoBanco(rechazoBanco);
		}
		if (solicitudDomiciliacion.getPorcentajeIncremento() != null) {
			GenericCatalogoVO porcentajeIncremento = new GenericCatalogoVO();
			mapBeanToVO(solicitudDomiciliacion.getPorcentajeIncremento(), porcentajeIncremento);
			solicitudDomiciliacionVO.setPorcentajeIncremento(porcentajeIncremento);
		}
		if (solicitudDomiciliacion.getPeriodoIncrementabilidad() != null) {
			GenericCatalogoVO periodoIncrementabilidad = new GenericCatalogoVO();
			mapBeanToVO(solicitudDomiciliacion.getPeriodoIncrementabilidad(),
					periodoIncrementabilidad);
			solicitudDomiciliacionVO.setPeriodoIncrementabilidad(periodoIncrementabilidad);
		}
		if (solicitudDomiciliacion.getMoneda() != null) {
			GenericCatalogoVO moneda = new GenericCatalogoVO();
			mapBeanToVO(solicitudDomiciliacion.getMoneda(), moneda);
			solicitudDomiciliacionVO.setMoneda(moneda);
		}
		if (solicitudDomiciliacion.getMedioNotificacion() != null) {
			GenericCatalogoVO medioNotificacion = new GenericCatalogoVO();
			mapBeanToVO(solicitudDomiciliacion.getMedioNotificacion(), medioNotificacion);
			solicitudDomiciliacionVO.setMedioNotificacion(medioNotificacion);
		}
		if (solicitudDomiciliacion.getOrigenAportacion() != null) {
			GenericCatalogoVO origenAportacion= new GenericCatalogoVO();
			mapBeanToVO(solicitudDomiciliacion.getOrigenAportacion(), origenAportacion);
			solicitudDomiciliacionVO.setOrigenAportacion(origenAportacion);
		}
		if (solicitudDomiciliacion.getDiversificaciones() != null) {
			List<DiversificacionVO> listaVO = new ArrayList<DiversificacionVO>();
			for (DiversificacionBean diversificacionBean : solicitudDomiciliacion.getDiversificaciones()) {
				DiversificacionVO diversificacionVO = new DiversificacionVO();
				diversificacionVO.setClaveSolicitud(solicitudDomiciliacionVO.getClaveSolicitud());
				diversificacionVO.setUsuarioCreacion(solicitudDomiciliacion.getUsuario());
				diversificacionVO.setUsuarioActualizacion(solicitudDomiciliacion.getUsuario());
				mapBeanToVO(diversificacionBean, diversificacionVO);
				if (diversificacionBean.getFondoAportacionVoluntaria() != null) {
					GenericCatalogoVO fondoAportacionVoluntaria = new GenericCatalogoVO();
					mapBeanToVO(diversificacionBean.getFondoAportacionVoluntaria(),
							fondoAportacionVoluntaria);
					diversificacionVO.setFondoAportacionVoluntaria(fondoAportacionVoluntaria);
				}
				listaVO.add(diversificacionVO);
				
			}
			solicitudDomiciliacionVO.setDiversificaciones(listaVO);
			solicitudDomiciliacionVO.setUsuarioActualizacion(solicitudDomiciliacion.getUsuario());
			solicitudDomiciliacionVO.setUsuarioCreacion(solicitudDomiciliacion.getUsuario());
			
		}
		return solicitudDomiciliacionVO;
	}
	
	public static ConciliacionBean ConciliacionBeanVOToBean(ConciliacionVO conciliacion) {
		ConciliacionBean conciliacionBean = new ConciliacionBean();
		try {
			mapVoToBean(conciliacionBean, conciliacion);
			if (conciliacion.getOrigenAportacion() != null) {
				GenericoCatalogoBean origenAportacion = new GenericoCatalogoBean();
				mapVoToBean(origenAportacion, conciliacion.getOrigenAportacion());
				conciliacionBean.setOrigenAportacion(origenAportacion);
			}
			if (conciliacion.getEmpresa() != null) {
				GenericoCatalogoBean empresa = new GenericoCatalogoBean();
				mapVoToBean(empresa, conciliacion.getEmpresa());
				conciliacionBean.setEmpresa(empresa);
			}
			if (conciliacion.getTipoNomina() != null) {
				GenericoCatalogoBean tipoNomina = new GenericoCatalogoBean();
				mapVoToBean(tipoNomina, conciliacion.getTipoNomina());
				conciliacionBean.setTipoNomina(tipoNomina);
			}
			if (conciliacion.getClaveRedComercial() != null) {
				GenericoCatalogoBean claveRedComercial = new GenericoCatalogoBean();
				mapVoToBean(claveRedComercial, conciliacion.getClaveRedComercial());
				conciliacionBean.setClaveRedComercial(claveRedComercial);
			}
			if(conciliacion.getEstatusTramite()!=null){
				GenericoCatalogoBean estatusTramite = new GenericoCatalogoBean();
				mapVoToBean(estatusTramite, conciliacion.getEstatusTramite());
				conciliacionBean.setEstatusTramite(estatusTramite);
			}
			
			//Se agregan al mapper VO MAP
//			if(conciliacion.getNombreOrdenante() != null){
//				conciliacionBean.setNombreOrdenante(conciliacion.getNombreOrdenante());
//			}
//			if(conciliacion.getRfcOrdenante() != null){
//				conciliacionBean.setRfcOrdenante(conciliacion.getRfcOrdenante());
//			}
//			if(conciliacion.getCurpOrdenante() != null){
//				conciliacionBean.setCurpOrdenante(conciliacion.getCurpOrdenante());
//			}
			
			if (conciliacion.getDiversificaciones() != null) {
				List<DiversificacionConciliacionBean> listaDiversificacionBean = new ArrayList<DiversificacionConciliacionBean>();
				for (DiversificacionConciliacionVO diversificacion : conciliacion.getDiversificaciones()) {
					DiversificacionConciliacionBean diversificacionConciliacionBean = new DiversificacionConciliacionBean();
					mapVoToBean(diversificacionConciliacionBean, diversificacion);
					if (diversificacion.getFondosecure() != null) {
						GenericoCatalogoBean fondoAportacion = new GenericoCatalogoBean();
						mapVoToBean(fondoAportacion, diversificacion.getFondosecure());
						diversificacionConciliacionBean.setFondosecure(fondoAportacion);
					}
					listaDiversificacionBean.add(diversificacionConciliacionBean);
				}
				conciliacionBean.setDiversificaciones(listaDiversificacionBean);
			}
		} catch (Exception e) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService:"+e.getMessage(),
							new Object[] { "SolicitudDomiciliacionServiceImpl", "ConvertVoTOBean(...)" }, e));

			throw webServiceException;
		}
		return conciliacionBean;
	}

	public static ConciliacionVO ConciliacionBeanTOVO(ConciliacionBean conciliacion, String Operacion) {
		ConciliacionVO conciliacionVO = new ConciliacionVO();
		try {
			mapBeanToVO(conciliacion, conciliacionVO);
	
			if(conciliacion.getNumeroCuentaIndividual()!=null && !conciliacion.getNumeroCuentaIndividual().isEmpty()){
				conciliacionVO.setNumeroCuentaIndividual(new Long(conciliacion.getNumeroCuentaIndividual()));
			}
			
			if(conciliacion.getClaveBanco() != null && !conciliacion.getClaveBanco().isEmpty()){
				conciliacionVO.setClaveBanco(new Short(conciliacion.getClaveBanco()));	
			}
			
			if(conciliacion.getNss() != null && !conciliacion.getNss().isEmpty()){
				conciliacionVO.setNss(new Long(conciliacion.getNss()));				
			}
			
			if(conciliacion.getCelular() != null && !conciliacion.getCelular().isEmpty()){
				conciliacionVO.setCelular(new Long(conciliacion.getCelular()));				
			}
			
			if (Operacion.equalsIgnoreCase("INSERT")) {
				conciliacionVO.setUsuarioCreacion(conciliacion.getUsuario());
				if(conciliacion.getMovimientoGenerado()==null){
					conciliacionVO.setMovimientoGenerado((short)0);
				}
				if(conciliacion.getRegistroConciliado()==null){
					conciliacionVO.setRegistroConciliado((short)0);
				}
			} else {
				conciliacionVO.setUsuarioActualizacion(conciliacion.getUsuario());
			}
			if (conciliacion.getOrigenAportacion() != null) {
				GenericCatalogoVO origenAportacion = new GenericCatalogoVO();
				mapBeanToVO(conciliacion.getOrigenAportacion(), origenAportacion);
				conciliacionVO.setOrigenAportacion(origenAportacion);
			}
			if (conciliacion.getEmpresa() != null) {
				GenericCatalogoVO empresa = new GenericCatalogoVO();
				mapBeanToVO(conciliacion.getEmpresa(), empresa);
				conciliacionVO.setEmpresa(empresa);
			}
			if (conciliacion.getTipoNomina() != null) {
				GenericCatalogoVO tipoNomina = new GenericCatalogoVO();
				mapBeanToVO(conciliacion.getTipoNomina(), tipoNomina);
				conciliacionVO.setTipoNomina(tipoNomina);
			}
			if (conciliacion.getClaveRedComercial() != null) {
				GenericCatalogoVO claveRedComercial = new GenericCatalogoVO();
				mapBeanToVO(conciliacion.getClaveRedComercial(), claveRedComercial);
				conciliacionVO.setClaveRedComercial(claveRedComercial);
			}
			if(conciliacion.getEstatusTramite() != null){
				GenericCatalogoVO estatus = new GenericCatalogoVO();
				mapBeanToVO(conciliacion.getEstatusTramite(),estatus);
				conciliacionVO.setEstatusTramite(estatus);
			}
			
//		Se agregan al mapper, clase:  mx.secure.nci.ws.util.BeanMap.java
//			if(conciliacion.getNombreOrdenante() != null){
//				conciliacionVO.setNombreOrdenante(conciliacion.getNombreOrdenante());
//			}
//			if(conciliacion.getCurpOrdenante() != null){
//				conciliacionVO.setCurpOrdenante(conciliacion.getCurpOrdenante());
//			}
//			if(conciliacion.getRfcOrdenante() != null){
//				conciliacionVO.setRfcOrdenante(conciliacion.getRfcOrdenante());
//			}
			
			if (conciliacion.getDiversificaciones() != null) {
				List<DiversificacionConciliacionVO> listaDiversificacionVO = new ArrayList<DiversificacionConciliacionVO>();
				for (DiversificacionConciliacionBean diversificacion : conciliacion.getDiversificaciones()) {
					DiversificacionConciliacionVO diversificacionConciliacionVO = new DiversificacionConciliacionVO();
					mapBeanToVO(diversificacion, diversificacionConciliacionVO);
					if (diversificacion.getFondosecure() != null) {
						GenericCatalogoVO fondoAportacion = new GenericCatalogoVO();
						mapBeanToVO(diversificacion.getFondosecure(), fondoAportacion);
						diversificacionConciliacionVO.setFondosecure(fondoAportacion);
					}
					
					if (Operacion.equalsIgnoreCase("INSERT")) {
						diversificacionConciliacionVO.setUsuarioCreacion(conciliacion.getUsuario());
					} else {
						diversificacionConciliacionVO.setUsuarioActualizacion(conciliacion.getUsuario());
					}
					
					listaDiversificacionVO.add(diversificacionConciliacionVO);
				}
				conciliacionVO.setDiversificaciones(listaDiversificacionVO);
			}
		} catch (Exception e) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
							new Object[] { "SolicitudDomiciliacionServiceImpl", "ConvertVoTOBean(...)" }, e));
			throw webServiceException;
		}
		return conciliacionVO;
	}

	public static CifrasGeneralesBean cifraVOToCifraBean(CifrasGeneralesVO cifras) {
		CifrasGeneralesBean cifrasGeneralesBean = new CifrasGeneralesBean();
		try {
			if (cifras.getSecciones() != null) {
				List<SeccionBean> listaSeccion = new ArrayList<SeccionBean>();
				
				 List<SeccionVO> listaSeccionVO = cifras.getSecciones() ; 
				 Map <String, List<List<ElementoVO>>> map = new HashMap<String, List<List<ElementoVO>>>();
				 for (SeccionVO item : listaSeccionVO) { 
					 List<List<ElementoVO>>  subList = map.get(item.getNombreSeccion()); 
				        if (subList == null) { 
				            subList = new ArrayList<List <ElementoVO>>();
				            map.put(item.getNombreSeccion(), subList); 
				        }
				        subList.add(item.getElementos());
				    }
				 listaSeccionVO = new ArrayList<SeccionVO>();
				 Map <String, List <ElementoVO>> simpleMap = new HashMap<String, List <ElementoVO>>();
				 
				 for (SeccionVO seccion : cifras.getSecciones()) {
					 if(map.get(seccion.getNombreSeccion()) != null){
					 List<List<ElementoVO>> lista = map.get(seccion.getNombreSeccion());
					 List<ElementoVO> listaElementosVO = new ArrayList<ElementoVO>();
					 for(List <ElementoVO> vo : lista){
						 for(ElementoVO elemento : vo){
							 listaElementosVO.add(elemento);
						 	}
					 	}
					 simpleMap.put(seccion.getNombreSeccion(), listaElementosVO);
					 map.remove(seccion.getNombreSeccion());
					 }
				 }

				for (SeccionVO seccion : cifras.getSecciones()) {
					SeccionBean seccionBean = new SeccionBean();
					seccionBean.setNombreSeccion(seccion.getNombreSeccion());
					List<ElementoBean> listaElementos = new ArrayList<ElementoBean>();
					if (simpleMap.get(seccion.getNombreSeccion()) != null) {
						for(ElementoVO elemento : simpleMap.get(seccion.getNombreSeccion())){
							ElementoBean elementoB = new ElementoBean();
							mapVoToBean(elementoB, elemento);
							listaElementos.add(elementoB);
							}
						seccionBean.setElementos(listaElementos);
						if (seccion.getTotalConciliado() != null) {
							ElementoBean totalConciliado = new ElementoBean();
							mapVoToBean(totalConciliado, seccion.getTotalConciliado());
							seccionBean.setTotalConciliado(totalConciliado);
						}
						if (seccion.getTotalNoConciliado() != null) {
							ElementoBean totalNoConciliado = new ElementoBean();
							mapVoToBean(totalNoConciliado, seccion.getTotalNoConciliado());
							seccionBean.setTotalNoConciliado(totalNoConciliado);
						}
						listaSeccion.add(seccionBean);
						simpleMap.remove(seccion.getNombreSeccion());
						}
					}
				cifrasGeneralesBean.setSecciones(listaSeccion);
			}
			if (cifras.getTotalGeneralConciliado() != null) {
				ElementoBean totalGralConciliado = new ElementoBean();
				mapVoToBean(totalGralConciliado, cifras.getTotalGeneralConciliado());
				cifrasGeneralesBean.setTotalGeneralConciliado(totalGralConciliado);
			}
			if (cifras.getTotalGeneralNoConciliado() != null) {
				ElementoBean totalGralNoConciliado = new ElementoBean();
				mapVoToBean(totalGralNoConciliado, cifras.getTotalGeneralNoConciliado());
				cifrasGeneralesBean.setTotalGeneralNoConciliado(totalGralNoConciliado);
			}
		} catch (Exception e) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
							new Object[] { "SolicitudDomiciliacionServiceImpl", "ConvertVoTOBean(...)" }, e));
			throw webServiceException;
		}
		return cifrasGeneralesBean;
	}
	
	/**
	 * @author iriosher
	 * @since 30/05/2017
	 * @purpose Metodo para convertir de bean a VO 
	 * */
	public static CifrasGeneralesPorTipoBean cifraTipoVOToCifraTipoBeanLP(CifrasGeneralesTipoVO cifras) {
		CifrasGeneralesPorTipoBean cifrasGeneralesBean = new CifrasGeneralesPorTipoBean();
		try {
			if (cifras.getSeccionesLargoPlazo() != null) {
				List<SeccionBean> listaSeccion = new ArrayList<SeccionBean>();
				
				 List<SeccionVO> listaSeccionVO = cifras.getSeccionesLargoPlazo() ; 
				 Map <String, List<List<ElementoVO>>> map = new HashMap<String, List<List<ElementoVO>>>();
				 for (SeccionVO item : listaSeccionVO) { 
					 List<List<ElementoVO>>  subList = map.get(item.getNombreSeccion()); 
				        if (subList == null) { 
				            subList = new ArrayList<List <ElementoVO>>();
				            map.put(item.getNombreSeccion(), subList); 
				        }
				        subList.add(item.getElementos());
				    }
				 listaSeccionVO = new ArrayList<SeccionVO>();
				 Map <String, List <ElementoVO>> simpleMap = new HashMap<String, List <ElementoVO>>();
				 
				 for (SeccionVO seccion : cifras.getSeccionesLargoPlazo()) {
					 if(map.get(seccion.getNombreSeccion()) != null){
					 List<List<ElementoVO>> lista = map.get(seccion.getNombreSeccion());
					 List<ElementoVO> listaElementosVO = new ArrayList<ElementoVO>();
					 for(List <ElementoVO> vo : lista){
						 for(ElementoVO elemento : vo){
							 listaElementosVO.add(elemento);
						 	}
					 	}
					 simpleMap.put(seccion.getNombreSeccion(), listaElementosVO);
					 map.remove(seccion.getNombreSeccion());
					 }
				 }
				 for (SeccionVO seccion : cifras.getSeccionesLargoPlazo()) {
						SeccionBean seccionBean = new SeccionBean();
						seccionBean.setNombreSeccion(seccion.getNombreSeccion() != null ? seccion.getNombreSeccion() : null);
						List<ElementoBean> listaElementos = new ArrayList<ElementoBean>();
						if (simpleMap.get(seccion.getNombreSeccion()) != null) {
							for(ElementoVO elemento : simpleMap.get(seccion.getNombreSeccion())){
								ElementoBean elementoB = new ElementoBean();
								mapVoToBean(elementoB, elemento);
								listaElementos.add(elementoB);
								}
							seccionBean.setElementos(listaElementos);
							if (seccion.getTotalConciliado() != null) {
								ElementoBean totalConciliado = new ElementoBean();
								mapVoToBean(totalConciliado, seccion.getTotalConciliado());
								seccionBean.setTotalConciliado(totalConciliado);
							}
							if (seccion.getTotalNoConciliado() != null) {
								ElementoBean totalNoConciliado = new ElementoBean();
								mapVoToBean(totalNoConciliado, seccion.getTotalNoConciliado());
								seccionBean.setTotalNoConciliado(totalNoConciliado);
							}
							listaSeccion.add(seccionBean);
							simpleMap.remove(seccion.getNombreSeccion());
							}
						}
					cifrasGeneralesBean.setSeccionesLargoPlazo(listaSeccion);
					 }
			if (cifras.getTotalGeneralConciliado() != null) {
				ElementoBean totalGralConciliado = new ElementoBean();
				mapVoToBean(totalGralConciliado, cifras.getTotalGeneralConciliado());
				cifrasGeneralesBean.setTotalGeneralConciliado(totalGralConciliado);
			}
			if (cifras.getTotalGeneralNoConciliado() != null) {
				ElementoBean totalGralNoConciliado = new ElementoBean();
				mapVoToBean(totalGralNoConciliado, cifras.getTotalGeneralNoConciliado());
				cifrasGeneralesBean.setTotalGeneralNoConciliado(totalGralNoConciliado);
			}
		} catch (Exception e) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
							new Object[] { "SolicitudDomiciliacionServiceImpl", "ConvertVoTOBean(...)" }, e));
			throw webServiceException;
		}
		return cifrasGeneralesBean;
	}
	
	/**
	 * @author iriosher
	 * @since 30/05/2017
	 * @purpose Metodo para convertir de bean a VO 
	 * */
	public static CifrasGeneralesPorTipoBean cifraTipoVOToCifraTipoBeanCP(CifrasGeneralesTipoVO cifras) {
		CifrasGeneralesPorTipoBean cifrasGeneralesBean = new CifrasGeneralesPorTipoBean();
		try {
			if (cifras.getSeccionesCortoPlazo() != null) {
				List<SeccionBean> listaSeccion = new ArrayList<SeccionBean>();
				
				 List<SeccionVO> listaSeccionVO = cifras.getSeccionesCortoPlazo() ; 
				 Map <String, List<List<ElementoVO>>> map = new HashMap<String, List<List<ElementoVO>>>();
				 for (SeccionVO item : listaSeccionVO) { 
					 List<List<ElementoVO>>  subList = map.get(item.getNombreSeccion()); 
				        if (subList == null) { 
				            subList = new ArrayList<List <ElementoVO>>();
				            map.put(item.getNombreSeccion(), subList); 
				        }
				        subList.add(item.getElementos());
				    }
				 listaSeccionVO = new ArrayList<SeccionVO>();
				 Map <String, List <ElementoVO>> simpleMap = new HashMap<String, List <ElementoVO>>();
				 
				 for (SeccionVO seccion : cifras.getSeccionesCortoPlazo()) {
					 if(map.get(seccion.getNombreSeccion()) != null){
					 List<List<ElementoVO>> lista = map.get(seccion.getNombreSeccion());
					 List<ElementoVO> listaElementosVO = new ArrayList<ElementoVO>();
					 for(List <ElementoVO> vo : lista){
						 for(ElementoVO elemento : vo){
							 listaElementosVO.add(elemento);
						 	}
					 	}
					 simpleMap.put(seccion.getNombreSeccion(), listaElementosVO);
					 map.remove(seccion.getNombreSeccion());
					 }
				 }
				 for (SeccionVO seccion : cifras.getSeccionesCortoPlazo()) {
						SeccionBean seccionBean = new SeccionBean();
						seccionBean.setNombreSeccion(seccion.getNombreSeccion() != null ? seccion.getNombreSeccion() : null);
						List<ElementoBean> listaElementos = new ArrayList<ElementoBean>();
						if (simpleMap.get(seccion.getNombreSeccion()) != null) {
							for(ElementoVO elemento : simpleMap.get(seccion.getNombreSeccion())){
								ElementoBean elementoB = new ElementoBean();
								mapVoToBean(elementoB, elemento);
								listaElementos.add(elementoB);
								}
							seccionBean.setElementos(listaElementos);
							if (seccion.getTotalConciliado() != null) {
								ElementoBean totalConciliado = new ElementoBean();
								mapVoToBean(totalConciliado, seccion.getTotalConciliado());
								seccionBean.setTotalConciliado(totalConciliado);
							}
							if (seccion.getTotalNoConciliado() != null) {
								ElementoBean totalNoConciliado = new ElementoBean();
								mapVoToBean(totalNoConciliado, seccion.getTotalNoConciliado());
								seccionBean.setTotalNoConciliado(totalNoConciliado);
							}
							listaSeccion.add(seccionBean);
							simpleMap.remove(seccion.getNombreSeccion());
							}
						}
					cifrasGeneralesBean.setSeccionesCortoPlazo(listaSeccion);
					 }
			if (cifras.getTotalGeneralConciliado() != null) {
				ElementoBean totalGralConciliado = new ElementoBean();
				mapVoToBean(totalGralConciliado, cifras.getTotalGeneralConciliado());
				cifrasGeneralesBean.setTotalGeneralConciliado(totalGralConciliado);
			}
			if (cifras.getTotalGeneralNoConciliado() != null) {
				ElementoBean totalGralNoConciliado = new ElementoBean();
				mapVoToBean(totalGralNoConciliado, cifras.getTotalGeneralNoConciliado());
				cifrasGeneralesBean.setTotalGeneralNoConciliado(totalGralNoConciliado);
			}
		} catch (Exception e) {
			WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "En WebService",
							new Object[] { "SolicitudDomiciliacionServiceImpl", "ConvertVoTOBean(...)" }, e));
			throw webServiceException;
		}
		return cifrasGeneralesBean;
	}

	public static OrdenesVO mappingOrdenAportacionBean(final OrdenAportacionBean bean,final TransaccionMappingEnum transaccion) throws MitBusinessException 
	{
		OrdenesVO vo = null;
		try 
		{

			if (bean != null) 
			{
				vo=new OrdenesVO();
				
				mapBeanToVO(bean, vo);
				
				if(bean.getEstatus() != null)
				{
					GenericCatalogoVO estatusV0=new GenericCatalogoVO();
					mapBeanToVO(bean.getEstatus() , estatusV0);
					vo.setEstatus(estatusV0);						
				}
				
				if(CollectionUtils.isNotEmpty(bean.getDiversificaciones()))
				{
					vo.setDiversificacionesOrdenes(mappingListDiversificacionOrdenAportacionBean(bean.getDiversificaciones(),bean.getNumeroOrden(), bean.getUsuario(), transaccion));
				}
				
				
				if(transaccion.equals(TransaccionMappingEnum.MAPPING_TO_INSERTAR))
				{
					vo.setUsuarioCreacion(bean.getUsuario());
				}
				else if(transaccion.equals(TransaccionMappingEnum.MAPPING_TO_ACTUALIZAR))
				{
					vo.setUsuarioActualizacion(bean.getUsuario());
				}
				
			
			}

			return vo;

		} 
		catch (MitBusinessException ex) 
		{
			throw ex;
		} 
		catch (Exception ex) 
		{

			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "Al Generar Mapping BEAN to VO",
							new Object[] { "UtilMapping", "mappingOrdenAportacionBean()" }, ex));

			throw mitBusinessException;
		}

	}

	public static List<OrdenAportacionBean> mappingListOrdenesVO(final List<OrdenesVO> listOrdenesVO) throws MitBusinessException 
	{
		List<OrdenAportacionBean> listOrdenesAportacionBean=null;
		try 
		{
		
			if(CollectionUtils.isNotEmpty(listOrdenesVO))
			{
				listOrdenesAportacionBean=new ArrayList<OrdenAportacionBean>();
				
				for(OrdenesVO vo : listOrdenesVO)
				{
					listOrdenesAportacionBean.add(mappingOrdenesVO(vo));
				}
								
			}
			
			return listOrdenesAportacionBean;
			
		}
		catch (MitBusinessException ex) 
		{
			throw ex;
		} 
		catch (Exception ex) 
		{

			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "Al Generar Mapping VO to BEAN",
							new Object[] { "UtilMapping", "mappingListOrdenesVO()" }, ex));

			throw mitBusinessException;
		}		
	}
	
	public static OrdenAportacionBean mappingOrdenesVO(final OrdenesVO vo) throws MitBusinessException 
	{
		OrdenAportacionBean bean = null;
		try 
		{

			if (vo != null) 
			{
				bean=new OrdenAportacionBean();
				
				mapVoToBean(bean, vo);
				
				if(vo.getEstatus() != null)
				{
					GenericoCatalogoBean estatusBean=new GenericoCatalogoBean();
					mapVoToBean(estatusBean, vo.getEstatus());
					
					bean.setEstatus(estatusBean);					
				}
				
				if(CollectionUtils.isNotEmpty(vo.getDiversificacionesOrdenes()))
				{
					
					bean.setDiversificaciones(mappingListDiversificacionOrdenesVO(vo.getDiversificacionesOrdenes()));
					
				}
							
			}

			return bean;

		} 
		catch (MitBusinessException ex) 
		{
			throw ex;
		} 
		catch (Exception ex) 
		{

			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "Al Generar Mapping VO to BEAN",
							new Object[] { "UtilMapping", "mappingOrdenesVO()" }, ex));

			throw mitBusinessException;
		}

	}	

	public static List<DiversificacionOrdenAportacionBean> mappingListDiversificacionOrdenesVO(final List<DiversificacionOrdenesVO> listDiversificacionOrdenesVO)throws MitBusinessException
	{
		List<DiversificacionOrdenAportacionBean> listDiversificacionOrdenesBean=null;
	
		try
		{
		
			if(CollectionUtils.isNotEmpty(listDiversificacionOrdenesVO))
			{
				listDiversificacionOrdenesBean=new ArrayList<DiversificacionOrdenAportacionBean>();
				
				for(DiversificacionOrdenesVO vo : listDiversificacionOrdenesVO)
				{
					listDiversificacionOrdenesBean.add(mappingDiversificacionOrdenesVO(vo));
				}
			}
			
			
			return listDiversificacionOrdenesBean;
			
		}
		catch (MitBusinessException ex) 
		{
			throw ex;
		} 
		catch (Exception ex) 
		{

			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "Al Generar Mapping VO to BEAN",
							new Object[] { "UtilMapping", "mappingListDiversificacionOrdenesVO()" }, ex));
			
			throw mitBusinessException;

		}
		
	}
		
	public static DiversificacionOrdenAportacionBean mappingDiversificacionOrdenesVO(final DiversificacionOrdenesVO vo)throws MitBusinessException
	{
		DiversificacionOrdenAportacionBean bean=null;
		
		try
		{
		
			if(vo != null)
			{
				bean=new DiversificacionOrdenAportacionBean();
				
				mapVoToBean(bean, vo);
				
				if(vo.getFondosecure() != null)
				{
					GenericoCatalogoBean fondosecureBean=new GenericoCatalogoBean();
					mapVoToBean(fondosecureBean, vo.getFondosecure());
					
					bean.setFondosecure(fondosecureBean);
					
				}
							
			}
			
			return bean;

		
		} 
		catch (MitBusinessException ex) 
		{
			throw ex;
		} 
		catch (Exception ex) 
		{
	
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "Al Generar Mapping VO to BEAN",
							new Object[] { "UtilMapping", "mappingDiversificacionOrdenesVO()" }, ex));
	
			throw mitBusinessException;
		}		
			
	}
		
	public static List<DiversificacionOrdenesVO> mappingListDiversificacionOrdenAportacionBean(final List<DiversificacionOrdenAportacionBean> listDiversificacionBean,final String numeroOrden,final String usuario,final TransaccionMappingEnum transaccion)throws MitBusinessException
	{
		List<DiversificacionOrdenesVO> listDiversificacionVo=null;
		
		try
		{
			if(CollectionUtils.isNotEmpty(listDiversificacionBean))
			{
				listDiversificacionVo=new ArrayList<DiversificacionOrdenesVO>();
				
				for(DiversificacionOrdenAportacionBean bean : listDiversificacionBean)
				{
					listDiversificacionVo.add(mappingDiversificacionOrdenAportacionBean(bean,numeroOrden,usuario,transaccion));
				}
				
			}
			
			
			return listDiversificacionVo;
			
		}
		catch (MitBusinessException ex) 
		{
			throw ex;
		} 
		catch (Exception ex) 
		{

			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "Al Generar Mapping BEAN to VO",
							new Object[] { "UtilMapping", "mappingListDiversificacionOrdenAportacionBean()" }, ex));

			throw mitBusinessException;
		}			
		
	}
	
	public static DiversificacionOrdenesVO mappingDiversificacionOrdenAportacionBean(final DiversificacionOrdenAportacionBean bean,final String numeroOrden,final String usuario,final TransaccionMappingEnum transaccion)throws MitBusinessException
	{
		DiversificacionOrdenesVO vo=null;
		
		try
		{
			if(bean != null)
			{
				vo=new DiversificacionOrdenesVO();
								
				mapBeanToVO(bean, vo);
				
				vo.setNumeroOrden(numeroOrden);
				
				if(bean.getFondosecure() != null)
				{
					GenericCatalogoVO fondosecureVo=new GenericCatalogoVO();
					mapBeanToVO(bean.getFondosecure(), fondosecureVo);
					vo.setFondosecure(fondosecureVo);
				}
				
				if(transaccion.equals(TransaccionMappingEnum.MAPPING_TO_INSERTAR))
				{
					vo.setUsuarioCreacion(usuario);
				}
				else if(transaccion.equals(TransaccionMappingEnum.MAPPING_TO_ACTUALIZAR))
				{
					vo.setUsuarioActualizacion(usuario);
				}
				
			}
			
			return vo;
			
		}
		catch (MitBusinessException ex) 
		{
			throw ex;
		} 
		catch (Exception ex) 
		{

			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeSoap.EX_EXCEPTION, "Al Generar Mapping BEAN to VO",
							new Object[] { "UtilMapping", "mappingDiversificacionOrdenAportacionBean()" }, ex));

			throw mitBusinessException;
		}		
		
		
	}	
	
}
