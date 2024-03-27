package mx.profuturo.nci.business.util;

import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.jeveris.core.exception.impl.BusinessException;
import com.jeveris.core.exception.impl.ExceptionDetails;

public final class UtilMapping {

	public static <VO, ENTITY> void mapEntityToVO(final VO vo, final ENTITY entity) throws BusinessException {

		try {
			if (vo != null && entity != null) {
				final BeanWrapper source = new BeanWrapperImpl(entity);
				final BeanWrapper target = new BeanWrapperImpl(vo);
				final VOMap type = VOMap.getVOMapOf(vo.getClass());
				if (type != null && type.getEntity().equals(entity.getClass())) {
					final Set<String> propiedades = type.getMapaPropiedades().keySet();

					for (final String propertyName : propiedades) {
						target.setPropertyValue(propertyName,
								source.getPropertyValue(type.getMapaPropiedades().get(propertyName)));
					}
				} else {
					throw new BusinessException(
							"La clase " + vo.getClass().getSimpleName() + " no tiene configurado mapeo para " + entity);
				}
			}
		} catch (Exception ex) {
			String errorCode = "2000", causeMessage = "PersistenceException Al Realizar Map entity to VO";
			ExceptionDetails exceptionDetails = new ExceptionDetails(errorCode, causeMessage);
			exceptionDetails.setBaseException(ex);
			throw new BusinessException(exceptionDetails);
		}
	}

	public static <VO, ENTITY> void mapVOToEntity(final VO vo, final ENTITY entity) throws BusinessException {
		if (vo != null && entity != null) {
			final BeanWrapper source = new BeanWrapperImpl(vo);
			final BeanWrapper target = new BeanWrapperImpl(entity);
			final VOMap type = VOMap.getVOMapOf(vo.getClass());
			if (type != null && type.getEntity().equals(entity.getClass())) {
				final Set<String> propiedades = type.getMapaPropiedades().keySet();
				for (final String propertyName : propiedades) {
					target.setPropertyValue(type.getMapaPropiedades().get(propertyName),
							source.getPropertyValue(propertyName));
				}
			} else {
				throw new BusinessException("La clase " + vo.getClass().getSimpleName() + " no tiene configurado mapeo para " + entity);
			}
		}
	}
}