package mx.secure.nci.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;

import mx.secure.nci.business.exception.ErrorCodeService;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.IServiciosCifService;
import mx.secure.nci.business.vo.ActualizaEstatusCifVO;
import mx.secure.nci.business.vo.ArchivoDetalleCifVO;
import mx.secure.nci.business.vo.DetalleMovimientosCifVO;
import mx.secure.nci.business.vo.DevolucionessecureCifVO;
import mx.secure.nci.business.vo.PendientesTimbradoCifVO;
import mx.secure.nci.persistence.ServiciosCifPersistence;

@Service("serviciosCifService")
public class ServiciosCifServiceImpl implements IServiciosCifService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiciosCifServiceImpl.class);

	@Autowired
	ServiciosCifPersistence serviciosCifPersistence;	
	
	public Integer actualizaEstatusCif(ActualizaEstatusCifVO actualizaEstatusVO) throws Exception {
		return serviciosCifPersistence.actualizaEstatusCif(actualizaEstatusVO);
	}

	public Integer actualizaEstatusCifDatamarts(ActualizaEstatusCifVO actualizaEstatusVO)
			throws Exception {
		return serviciosCifPersistence.actEstatCifData(actualizaEstatusVO);
	}
	
	public List<PendientesTimbradoCifVO> obtenerDevolucionessecure(PendientesTimbradoCifVO param) throws MitBusinessException {
		String tipoDevolucionsecure = param.getTipoDevolucion();
		if (tipoDevolucionsecure.equals("DEV-PROSPECTO") || tipoDevolucionsecure.equals("DEV-CLIENTE")) {
			return this.serviciosCifPersistence.getDevolucionesCif(param);
		} else if (tipoDevolucionsecure.equals("DEV-PROCESAR") || tipoDevolucionsecure.equals("DEV-secure")) {
			return this.serviciosCifPersistence.getDevolucionessecure(param);
		} else if (tipoDevolucionsecure.equals("DEV-PROFU-PROSPEC")) {
			return null;
		} else {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "El tipo de devolucion no existe",
							new Object[] { "PendientesTimbradoCifServiceImpl", "obtenerDevolucionessecure()" },
							new Exception("Se debe incluir un valor valido para el tipo de devolucion DEV-PROSPECTO, DEV-CLIENTE, DEV-PROCESAR, DEV-secure, DEV-PROFU-PROSPEC")));
			throw mitBusinessException;
		}
	}
	
	public List<ArchivoDetalleCifVO> obtenerListaDetalleCif(ArchivoDetalleCifVO param) throws Exception {
		return serviciosCifPersistence.getListaDetalleCif(param);
	}
	
	public List<DevolucionessecureCifVO> obtenerListaDevolucionessecureCif(DevolucionessecureCifVO param)throws Exception {
		try{
			String tipoDevolucionsecure = param.getTipoDevolucionsecure();
			
			if(tipoDevolucionsecure.equals("DEV-PROSPECTO") || tipoDevolucionsecure.equals("DEV-CLIENTE")) {
				return this.serviciosCifPersistence.getListaDevolucionessecure(param);
				
			}else if(tipoDevolucionsecure.equals("DEV-PROCESAR")|| tipoDevolucionsecure.equals("DEV-secure")) {
				return this.serviciosCifPersistence.getListadoDevolucionessecure(param);
			}else if (tipoDevolucionsecure.equals("DEV-PROFU-PROSPEC")) {
				  return null;
			}else {
				MitBusinessException mitBusinessException = new MitBusinessException(
						GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "El tipo de devolucion no existe",
								new Object[] { "ValidarResultLoteCifServiceImpl", "obtenerListaDevolucionessecureCif()" },
								new Exception("Se debe incluir un valor valido para el tipo de devolucion DEV-PROSPECTO, DEV-CLIENTE, DEV-PROCESAR, DEV-secure, DEV-PROFU-PROSPEC")));
				throw mitBusinessException;
			}
		}catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
			ErrorCodeService.EX_EXCEPTION, "El tipo de devolución no existe.",
			new Object[] { getClass().getSimpleName(), "obtenerListaDevolucionessecureCif()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
	}
	
	public List<DetalleMovimientosCifVO> obtenerListaDetalleMovCif(DetalleMovimientosCifVO param) throws Exception {
		LOGGER.info("******************************     persistence:obtenerListaDetalleMovCif     ******************************");
		return serviciosCifPersistence.getListaDetalleMovCif(param);
	}

}
