package mx.secure.nci.business.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.persistence.exception.PersistenceException;

import mx.secure.nci.business.exception.ErrorCodeService;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.ISolicitudReclasificacionService;
import mx.secure.nci.business.vo.DiversificacionSolicitudReclasificacionVO;
import mx.secure.nci.business.vo.IndicadorSolicitudReclasificacionVO;
import mx.secure.nci.business.vo.SolicitudReclasificacionsecureVO;
import mx.secure.nci.business.wrapped.ClienteFilter;
import mx.secure.nci.business.wrapped.SolicitudReclasificacionFilter;
import mx.secure.nci.persistence.SolicitudReclasificacionPersistence;

@Service("solicitudReclasificacionService")
public class SolicitudReclasificacionServiceImpl implements ISolicitudReclasificacionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SolicitudReclasificacionServiceImpl.class);

	@Autowired
	SolicitudReclasificacionPersistence solicitudReclasificacionPersistence;

	public Boolean guardarSolicitud(SolicitudReclasificacionsecureVO solicitudReclasificacionVO)
			throws MitBusinessException {
		Boolean result = false;
		int cantDiversificacion = 0;
		int persisteSolicitante = 0;
		int cantSaldoOriginal = 0;
		int cantIndicadores = 0;
		try {
			ClienteFilter clienteFilter = new ClienteFilter();
			clienteFilter.setNumCtaInvdual(solicitudReclasificacionVO.getSolicitante().getNumCtaInvdual());
			if (solicitudReclasificacionPersistence.selectSolicitante(clienteFilter) != null) {
				solicitudReclasificacionVO.getSolicitante().setUsuAct(solicitudReclasificacionVO.getSolicitante().getUsuCre());
				persisteSolicitante = solicitudReclasificacionPersistence.actualizarSolicitante(solicitudReclasificacionVO.getSolicitante());
			} else {
				persisteSolicitante = solicitudReclasificacionPersistence.insertaSolicitante(solicitudReclasificacionVO.getSolicitante());
			}
			if (persisteSolicitante == 0) {
				return false;
			}
			
			if (solicitudReclasificacionPersistence.insertarSolicitud(solicitudReclasificacionVO) > 0) {
				
				solicitudReclasificacionPersistence.insertaEstatusSolicitud(solicitudReclasificacionVO);
				
				for (DiversificacionSolicitudReclasificacionVO diversificacionVo : solicitudReclasificacionVO.getLstDiversificacionVO()) {
					solicitudReclasificacionPersistence.insertarDiversificacion(diversificacionVo);
					cantDiversificacion++;
				}
				
				for(DiversificacionSolicitudReclasificacionVO saldoOriginalVO : solicitudReclasificacionVO.getLstSaldosOriginal()) {
					solicitudReclasificacionPersistence.insertarSaldoOriginal(saldoOriginalVO);
					cantSaldoOriginal++;
				}
				for(IndicadorSolicitudReclasificacionVO indicadorVO : solicitudReclasificacionVO.getLstIndicadores()) {
					solicitudReclasificacionPersistence.insertarIndicadores(indicadorVO);
					cantIndicadores++;
				}
				if (cantDiversificacion == solicitudReclasificacionVO.getLstDiversificacionVO().size() &&
						cantSaldoOriginal == solicitudReclasificacionVO.getLstSaldosOriginal().size() &&
								cantIndicadores == solicitudReclasificacionVO.getLstIndicadores().size()) {
					result = true;
				}
			}
			return result;
		} catch (PersistenceException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al Insertar (SolicitudReclasificacion)",
					new Object[] { "SolicitudReclasificacionServiceImpl", "guardarSolicitud()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
	}

	public SolicitudReclasificacionsecureVO consultarSolicitud(
			SolicitudReclasificacionFilter solicitudReclasificacionFilter) throws MitBusinessException {
		try {

			return solicitudReclasificacionPersistence.consultarSolicitud(solicitudReclasificacionFilter);

		} catch (PersistenceException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al consultar(SolicitudReclasificacion)",
					new Object[] { "SolicitudReclasificacionServiceImpl", "consultarSolicitud()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
	}

	public Boolean actualizarEstatusSolicitud(SolicitudReclasificacionsecureVO solicitudReclasificacionVO)
			throws MitBusinessException {
			Boolean update = false;
		try {
			
			if(solicitudReclasificacionPersistence.actualizarSolicitud(solicitudReclasificacionVO) > 0 &&
			solicitudReclasificacionPersistence.insertaEstatusSolicitud(solicitudReclasificacionVO) > 0) {
				update = true;
			}
			
			
			return update;

		} catch (PersistenceException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al ActualizarEstatus (SolicitudReclasificacion)",
					new Object[] { "SolicitudReclasificacionServiceImpl", "actualizarEstatusSolicitud()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
	}

}
