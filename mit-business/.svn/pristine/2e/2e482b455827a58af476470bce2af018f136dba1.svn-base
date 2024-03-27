package mx.profuturo.nci.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IIdentificarClienteService;
import mx.profuturo.nci.business.util.Constantes;
import mx.profuturo.nci.business.util.IdentificarClienteEnum;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;

import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.FaultMsg;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.ConsultarClienteBasicoReq;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.ConsultarClienteBasicoResp;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.Identificadores;
import profuturo.mx.nci.modelo.Mensaje;

@Service("identificarClienteService")
public class IdentificarClienteServiceImpl implements IIdentificarClienteService {
	private static final Logger LOGGER = LoggerFactory.getLogger(IdentificarClienteServiceImpl.class);

	private static final String LOCALPART_CURP = "curp";
	private static final String LOCALPART_NSS = "nss";
	private static final String LOCALPART_NUM_CUENTA = "numeroCuenta";

	@Autowired
	WSPortTypeFactory wsPortTypeFactory;

	public ConsultarClienteBasicoResp consultarClienteBasico(final IdentificarClienteEnum identificador,
			final String valorIdentificador) throws MitBusinessException {

		try {

			ConsultarClienteBasicoReq requesWs = null;
			Identificadores identificadores = null;
			List<JAXBElement<String>> listIdentificadores = null;

			requesWs = new ConsultarClienteBasicoReq();
			identificadores = new Identificadores();

			listIdentificadores = new ArrayList<JAXBElement<String>>();

			listIdentificadores
					.add(new JAXBElement<String>(new QName("numeroCuenta"), String.class, valorIdentificador));
			identificadores.setNssOrRfcOrCurp(listIdentificadores);
			requesWs.setIdentificadores(identificadores);

			ConsultarClienteBasicoResp response = wsPortTypeFactory.clientePortType().consultarClienteBasico(requesWs);
			/*
			 * if (response.getMensajes() != null &&
			 * CollectionUtils.isNotEmpty(response.getMensajes().getMensaje()))
			 * {
			 * 
			 * for (final Mensaje mensaje : response.getMensajes().getMensaje())
			 * { if (mensaje != null && mensaje.getCodigo() != null &&
			 * !mensaje.getCodigo().isEmpty()) {
			 * 
			 * LOGGER.error("IDENTIFICACION CLIENTE BUC, CODIGO: " +
			 * mensaje.getCodigo() + " MENSAJE: " + mensaje.getDescripcion());
			 * 
			 * throw new Exception("IDENTIFICACION CLIENTE BUC, CODIGO: " +
			 * mensaje.getCodigo() + " MENSAJE: " + mensaje.getDescripcion()); }
			 * 
			 * } }
			 */
			return response;

		} catch (final FaultMsg ex) {
			profuturo.mx.iib.iibexception.iibexception.Error error = null;

			if (ex.getFaultInfo() != null && ex.getFaultInfo().getErrores() != null
					&& CollectionUtils.isNotEmpty(ex.getFaultInfo().getErrores().getError())) {
				error = ex.getFaultInfo().getErrores().getError().get(0);
			}

			final MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails
					.generate(ErrorCodeService.EX_FAULTMSG, "Al Identificar Cliente", new Object[] {
							"IdentificarClienteServiceImpl", "consultarClienteBasico()",
							ex.getFaultInfo() != null && ex.getFaultInfo().getServicio() != null
									? ex.getFaultInfo().getServicio() : "ClienteService",
							ex.getFaultInfo() != null && ex.getFaultInfo().getOperacion() != null
									? ex.getFaultInfo().getOperacion() : "consultarClienteBasico",
							error != null && error.getTipo() != null ? error.getTipo() : "SIN-TIPO",
							error != null && error.getCodigo() != null ? error.getCodigo() : "SIN-CODIGO",
							error != null && error.getDescripcion() != null ? error.getDescripcion() : "SIN-DES",
							error != null && error.getServicioOrigen() != null ? error.getServicioOrigen()
									: "SIN-SERVICE-ORIGEN",
							error != null && error.getTrace() != null ? error.getTrace() : "SIN-TRACE", }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;

		} catch (BusinessException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Identificar Cliente",
							new Object[] { "IdentificarClienteServiceImpl", "consultarClienteBasico()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

	public ConsultarClienteBasicoResp consultarClienteBasicoFiltro(final IdentificarClienteEnum identificador,
			final String valorIdentificador) throws MitBusinessException {
		try {
			ConsultarClienteBasicoReq requesWs = null;
			Identificadores identificadores = null;
			List<JAXBElement<String>> listIdentificadores = null;

			requesWs = new ConsultarClienteBasicoReq();
			identificadores = new Identificadores();

			listIdentificadores = new ArrayList<JAXBElement<String>>();
			if (identificador.getValue().equalsIgnoreCase(IdentificarClienteEnum.IDENTIFICADOR_CURP.getValue())) {
				listIdentificadores
						.add(new JAXBElement<String>(new QName(Constantes._CURP), String.class, valorIdentificador));
			} else if (identificador.getValue().equalsIgnoreCase(IdentificarClienteEnum.IDENTIFICADOR_NSS.getValue())) {
				listIdentificadores
						.add(new JAXBElement<String>(new QName(Constantes._NSS), String.class, valorIdentificador));
			} else if (identificador.getValue()
					.equalsIgnoreCase(IdentificarClienteEnum.IDENTIFICADOR_NUM_CUENTA.getValue())) {
				listIdentificadores.add(
						new JAXBElement<String>(new QName(Constantes.NUMERO_CUENTA), String.class, valorIdentificador));
			}

			identificadores.setNssOrRfcOrCurp(listIdentificadores);
			requesWs.setIdentificadores(identificadores);

			ConsultarClienteBasicoResp response = wsPortTypeFactory.clientePortType().consultarClienteBasico(requesWs);

			return response;

		} catch (final FaultMsg ex) {
			profuturo.mx.iib.iibexception.iibexception.Error error = null;

			if (ex.getFaultInfo() != null && ex.getFaultInfo().getErrores() != null
					&& CollectionUtils.isNotEmpty(ex.getFaultInfo().getErrores().getError())) {
				error = ex.getFaultInfo().getErrores().getError().get(0);
			}

			final MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails
					.generate(ErrorCodeService.EX_FAULTMSG, "Al Identificar Cliente", new Object[] {
							"IdentificarClienteServiceImpl", "consultarClienteBasico()",
							ex.getFaultInfo() != null && ex.getFaultInfo().getServicio() != null
									? ex.getFaultInfo().getServicio() : "ClienteService",
							ex.getFaultInfo() != null && ex.getFaultInfo().getOperacion() != null
									? ex.getFaultInfo().getOperacion() : "consultarClienteBasico",
							error != null && error.getTipo() != null ? error.getTipo() : "SIN-TIPO",
							error != null && error.getCodigo() != null ? error.getCodigo() : "SIN-CODIGO",
							error != null && error.getDescripcion() != null ? error.getDescripcion() : "SIN-DES",
							error != null && error.getServicioOrigen() != null ? error.getServicioOrigen()
									: "SIN-SERVICE-ORIGEN",
							error != null && error.getTrace() != null ? error.getTrace() : "SIN-TRACE", }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;

		} catch (BusinessException ex) {
			throw new MitBusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Identificar Cliente",
							new Object[] { "IdentificarClienteServiceImpl", "consultarClienteBasico()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}

}
