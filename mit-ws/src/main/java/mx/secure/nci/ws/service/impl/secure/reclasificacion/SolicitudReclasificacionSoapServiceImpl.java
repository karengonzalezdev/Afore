package mx.secure.nci.ws.service.impl.secure.reclasificacion;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;

import mx.secure.nci.business.service.ISolicitudReclasificacionService;
import mx.secure.nci.business.util.Constantes;
import mx.secure.nci.business.vo.ClienteVO;
import mx.secure.nci.business.vo.DiversificacionSolicitudReclasificacionVO;
import mx.secure.nci.business.vo.IndicadorSolicitudReclasificacionVO;
import mx.secure.nci.business.vo.SolicitudReclasificacionsecureVO;
import mx.secure.nci.ws.beans.DiversificacionSolicitudReclasificacionBean;
import mx.secure.nci.ws.beans.GenericoCatalogoBean;
import mx.secure.nci.ws.beans.SolicitudReclasificacionBean;
import mx.secure.nci.ws.beans.request.SolicitudReclasificacionActualizarBeanRequest;
import mx.secure.nci.ws.beans.request.SolicitudReclasificacionBeanRequest;
import mx.secure.nci.ws.beans.request.SolicitudReclasificacionConsultarBeanRequest;
import mx.secure.nci.ws.beans.response.SolicitudReclasificacionConsultarBeanResponse;
import mx.secure.nci.ws.exceptions.FaultBeanServiceInfo;
import mx.secure.nci.ws.exceptions.MitWebServiceException;
import mx.secure.nci.ws.service.secure.reclasificacion.ISolicitudReclasificacionsecureSoapService;

@Service("solicitudReclasificacionSoapService")
public class SolicitudReclasificacionSoapServiceImpl implements ISolicitudReclasificacionsecureSoapService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SolicitudReclasificacionSoapServiceImpl.class);

	@Autowired
	ISolicitudReclasificacionService solicitudReclasificacionService;

	@Override
	public SolicitudReclasificacionConsultarBeanResponse guardarSolicitudsecure(
			SolicitudReclasificacionBeanRequest solicitudReclasificacionBeanRequest) {

		try {
			SolicitudReclasificacionBean solicitudBean = solicitudReclasificacionBeanRequest
					.getSolicitudReclasificacion();
			SolicitudReclasificacionsecureVO solicitudVO = new SolicitudReclasificacionsecureVO();

			solicitudVO.setCveSolicitud(solicitudBean.getCveSolicitud());
			solicitudVO.setNumCtaInvdual(solicitudBean.getNumCtaInvdual());
			solicitudVO.setFolioFus(solicitudBean.getFolioFus());
			solicitudVO.setMontoReclasificacion(solicitudBean.getMontoReclasificacion());
			solicitudVO.setIdFondoApp(solicitudBean.getIdFondoApp());
			solicitudVO.setUsuCaptura(solicitudBean.getUsuCaptura());
			solicitudVO.setIdOrigenCaptura(solicitudBean.getIdOrigenCaptura());
			solicitudVO.setIdMedioNotificacion(solicitudBean.getIdMedioNotificacion());
			solicitudVO.setMedioNotificacion(solicitudBean.getMedioNotificacion());
			solicitudVO.setDestinoNotificacion(solicitudBean.getDestinoNotificacion());
			solicitudVO.setIdEstatusSolicitud(solicitudBean.getIdEstatusSolicitud());
			solicitudVO.setIdMotivoRechazo(solicitudBean.getIdMotivoRechazo());
			solicitudVO.setIdTipoReclasificacion(solicitudBean.getIdTipoReclasificacion());
			solicitudVO.setIdTipoSiefore(solicitudBean.getIdTipoSiefore());
			solicitudVO.setUsuCre(solicitudBean.getUsuCre());

			List<DiversificacionSolicitudReclasificacionVO> lstDiversificacionVO = new ArrayList<DiversificacionSolicitudReclasificacionVO>();

			for (DiversificacionSolicitudReclasificacionBean diversificacionBean : solicitudBean
					.getLstDiversificacionBean()) {
				DiversificacionSolicitudReclasificacionVO diversificacionVO = new DiversificacionSolicitudReclasificacionVO();

				diversificacionVO.setCveSolicitud(solicitudBean.getCveSolicitud());
				diversificacionVO.setIdSiefore(diversificacionBean.getIdSiefore());
				diversificacionVO.setIdSubcta(diversificacionBean.getIdSubcta());
				diversificacionVO.setPesos(diversificacionBean.getPesos());
				diversificacionVO.setAcciones(diversificacionBean.getAcciones());
				diversificacionVO.setPorcentaje(diversificacionBean.getPorcentaje());
				diversificacionVO.setIdValorAccion(diversificacionBean.getIdValorAccion());
				diversificacionVO.setIdTipoMov(diversificacionBean.getIdTipoMov());
				diversificacionVO.setUsuCre(solicitudBean.getUsuCre());
				diversificacionVO.setIdFondoApp(diversificacionBean.getIdFondoApp());
				diversificacionVO.setPlazo( diversificacionBean.getPlazo() );
				diversificacionVO.setDeducible( diversificacionBean.getDeducible() );

				lstDiversificacionVO.add(diversificacionVO);
			}
			solicitudVO.setLstDiversificacionVO(lstDiversificacionVO);

			ClienteVO solicitante = new ClienteVO();
			solicitante.setNombreCte(solicitudBean.getSolicitante().getNombreCte());
			solicitante.setApPaternoCte(solicitudBean.getSolicitante().getApPaternoCte() == null ? ""
					: solicitudBean.getSolicitante().getApPaternoCte());
			solicitante.setApMaternoCte(solicitudBean.getSolicitante().getApMaternoCte() == null ? ""
					: solicitudBean.getSolicitante().getApMaternoCte());
			solicitante.setNss(
					solicitudBean.getSolicitante().getNss() == null ? "" : solicitudBean.getSolicitante().getNss());
			solicitante.setNumCtaInvdual(solicitudBean.getNumCtaInvdual());
			solicitante.setCurp(
					solicitudBean.getSolicitante().getCurp() == null ? "" : solicitudBean.getSolicitante().getCurp());
			solicitante.setRfcCte(solicitudBean.getSolicitante().getRfcCte() == null ? ""
					: solicitudBean.getSolicitante().getRfcCte());
			solicitante.setCorreoElec(solicitudBean.getSolicitante().getCorreoElec() == null ? ""
					: solicitudBean.getSolicitante().getCorreoElec());
			solicitante.setCelular(solicitudBean.getSolicitante().getCelular() == null ? ""
					: String.valueOf(solicitudBean.getSolicitante().getCelular()));
			solicitante.setCalle(
					solicitudBean.getSolicitante().getCalle() == null ? "" : solicitudBean.getSolicitante().getCalle());
			solicitante.setNoExterior(solicitudBean.getSolicitante().getNumeroExterior() == null ? ""
					: solicitudBean.getSolicitante().getNumeroExterior());
			solicitante.setNoInterior(solicitudBean.getSolicitante().getNumeroInterior() == null ? ""
					: solicitudBean.getSolicitante().getNumeroInterior());
			solicitante.setCodigoPostal(solicitudBean.getSolicitante().getCodigoPostal() == null ? ""
					: solicitudBean.getSolicitante().getCodigoPostal());
			solicitante.setColonia(solicitudBean.getSolicitante().getColonia() == null ? ""
					: solicitudBean.getSolicitante().getColonia());
			solicitante.setDelegMunicipio(solicitudBean.getSolicitante().getMunicipioDelegacion() == null ? ""
					: solicitudBean.getSolicitante().getMunicipioDelegacion());
			solicitante.setEstado(solicitudBean.getSolicitante().getEstado() == null ? ""
					: solicitudBean.getSolicitante().getEstado());
			solicitante.setTelefono(solicitudBean.getSolicitante().getTelefono() == null ? ""
					: solicitudBean.getSolicitante().getTelefono());
			solicitante.setUsuCre(solicitudBean.getUsuCre());

			solicitudVO.setSolicitante(solicitante);

			List<DiversificacionSolicitudReclasificacionVO> lstSaldosOriginalVO = new ArrayList<DiversificacionSolicitudReclasificacionVO>();
			for (DiversificacionSolicitudReclasificacionBean saldosOriginal : solicitudBean.getLstSaldosOriginal()) {
				DiversificacionSolicitudReclasificacionVO saldoOriginalVO = new DiversificacionSolicitudReclasificacionVO();

				saldoOriginalVO.setCveSolicitud(solicitudBean.getCveSolicitud());
				saldoOriginalVO.setIdSiefore(saldosOriginal.getIdSiefore());
				saldoOriginalVO.setIdSubcta(saldosOriginal.getIdSubcta());
				saldoOriginalVO.setPesos(saldosOriginal.getPesos());
				saldoOriginalVO.setAcciones(saldosOriginal.getAcciones());
				saldoOriginalVO.setIdValorAccion(saldosOriginal.getIdValorAccion());
				saldoOriginalVO.setUsuCre(solicitudBean.getUsuCre());

				lstSaldosOriginalVO.add(saldoOriginalVO);
			}
			solicitudVO.setLstSaldosOriginal(lstSaldosOriginalVO);

			List<IndicadorSolicitudReclasificacionVO> lstIndicadores = new ArrayList<IndicadorSolicitudReclasificacionVO>();
			for (GenericoCatalogoBean indicadorBean : solicitudBean.getLstIndicadores()) {
				IndicadorSolicitudReclasificacionVO indicadorVO = new IndicadorSolicitudReclasificacionVO();

				indicadorVO.setCveSolicitud(solicitudBean.getCveSolicitud());
				indicadorVO.setIdIndicador(indicadorBean.getId());
				indicadorVO.setValor(indicadorBean.getValor());
				indicadorVO.setUsuCre(solicitudBean.getUsuCre());

				lstIndicadores.add(indicadorVO);
			}
			solicitudVO.setLstIndicadores(lstIndicadores);
			
			solicitudReclasificacionService.guardarSolicitud(solicitudVO);
			
			SolicitudReclasificacionConsultarBeanRequest solicitudRequest = new SolicitudReclasificacionConsultarBeanRequest();
			solicitudRequest.setCveSolicitud(solicitudBean.getCveSolicitud());
			SolicitudReclasificacionsecureVO solicitudReclasificacionVO = solicitudReclasificacionService.consultarSolicitud(solicitudRequest);
			
			return new SolicitudReclasificacionConsultarBeanResponse(solicitudReclasificacionVO);
			
		} catch (NullPointerException npe) {
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultcode(Constantes.NULL_ITEM_REQ);
			missing.setFaultstring(Constantes.ERROR_AL_LEER_DATOS_DE_ENTRADA);
			LOGGER.error(npe.getMessage(), npe);
			throw new MitWebServiceException("El valor de un dato de inserción es nulo.", missing);
		} catch (BusinessException e) {
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring("Ocurrio un error al realizar la inserción del servicio de Guardar Solicitud");
			LOGGER.error(e.getMessage(), e);
			throw new MitWebServiceException("Ocurrio un error al realizar la inserción del servicio de Guardar Solicitud", missing);
		} catch (Exception e) {
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring("Ocurrio un error al insertar Solicitud");
			LOGGER.error(e.getMessage(), e);
			throw new MitWebServiceException("Ocurrio un error al insertar Solicitud", missing);
		}

	}

	@Override
	public SolicitudReclasificacionConsultarBeanResponse consultarSolicitudsecure(
			SolicitudReclasificacionConsultarBeanRequest solicitudReclasificacionConsultarBeanRequest) {

		try {
			SolicitudReclasificacionsecureVO solicitudReclasificacionVO = solicitudReclasificacionService.consultarSolicitud(solicitudReclasificacionConsultarBeanRequest);

			return new SolicitudReclasificacionConsultarBeanResponse(solicitudReclasificacionVO);

		} catch (NullPointerException npe) {
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultcode(Constantes.NULL_ITEM_REQ);
			missing.setFaultstring(Constantes.ERROR_AL_LEER_DATOS_DE_ENTRADA);
			LOGGER.error(npe.getMessage(), npe);
			throw new MitWebServiceException("El valor de un dato de consulta es nulo.", missing);
		} catch (BusinessException e) {
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring("Ocurrio un error al realizar la inserción del servicio");
			LOGGER.error(e.getMessage(), e);
			throw new MitWebServiceException("Ocurrio un error al realizar la consulta del servicio", missing);
		} catch (Exception e) {
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring("Ocurrio un error al consultar Solicitud");
			LOGGER.error(e.getMessage(), e);
			throw new MitWebServiceException("Ocurrio un error al consultar Solicitud", missing);
		}

	}

	@Override
	public Boolean actualizarEstatusSolicitud(
			SolicitudReclasificacionActualizarBeanRequest solicitudReclasificacionActualizarBeanRequest) {

		try {
			SolicitudReclasificacionsecureVO solicitudReclasificacionVO = new SolicitudReclasificacionsecureVO();
			solicitudReclasificacionVO.setCveSolicitud(solicitudReclasificacionActualizarBeanRequest.getCveSolicitud());
			solicitudReclasificacionVO.setIdEstatusSolicitud(solicitudReclasificacionActualizarBeanRequest.getIdEstatusSolicitud());
			solicitudReclasificacionVO.setIdMotivoRechazo(solicitudReclasificacionActualizarBeanRequest.getIdMotivoRechazo());
			solicitudReclasificacionVO.setUsuCre(solicitudReclasificacionActualizarBeanRequest.getUsuActualiza());

			return solicitudReclasificacionService.actualizarEstatusSolicitud(solicitudReclasificacionVO);

		} catch (NullPointerException npe) {
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultcode(Constantes.NULL_ITEM_REQ);
			missing.setFaultstring(Constantes.ERROR_AL_LEER_DATOS_DE_ENTRADA);
			LOGGER.error(npe.getMessage(), npe);
			throw new MitWebServiceException("El valor de un dato de actualizacion es nulo.", missing);
		} catch (BusinessException e) {
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring("Ocurrio un error al realizar la inserción del servicio");
			LOGGER.error(e.getMessage(), e);
			throw new MitWebServiceException("Ocurrio un error al realizar la actualizacion del servicio", missing);
		} catch (Exception e) {
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring("Ocurrio un error al consultar Solicitud");
			LOGGER.error(e.getMessage(), e);
			throw new MitWebServiceException("Ocurrio un error al actualizar la Solicitud", missing);
		}

	}

}
