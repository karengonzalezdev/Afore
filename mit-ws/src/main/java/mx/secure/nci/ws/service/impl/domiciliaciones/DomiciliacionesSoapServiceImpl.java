package mx.secure.nci.ws.service.impl.domiciliaciones;

import static mx.secure.nci.business.util.Constantes.FILE_NAME_OFFSET_DOMICILIACIONES_TRASPASO;
import static mx.secure.nci.business.util.ConstantesCatalogos.ID_secure_NCI;
import static mx.secure.nci.business.util.ConstantesCatalogos.ID_BANCOMER_NCI;
import static mx.secure.nci.business.util.ConstantesCatalogos.ID_ORIGEN_DOMICILIACION_TRASPASO;
import static mx.secure.nci.business.util.Constantes.PATH_ACHIVO_DOMI_secure;
import static mx.secure.nci.business.util.Constantes.PATH_ACHIVO_DOMI_BANCOMER;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.ibm.icu.text.DecimalFormat;

import mx.secure.nci.business.bean.RespGeneracionArchivosDomi;
import mx.secure.nci.business.service.IDomiciliacionesService;
import mx.secure.nci.business.util.UtilValidate;
import mx.secure.nci.business.util.enums.CtrlResponseWSEnum;
import mx.secure.nci.business.vo.ArchivoDomiVO;
import mx.secure.nci.business.vo.ArchivosDomiciliacionBitacoraVO;
import mx.secure.nci.business.vo.ArchivosDomiciliacionVO;
import mx.secure.nci.business.vo.ArchivosGeneradosDomiVO;
import mx.secure.nci.business.vo.CatalogosDomiVO;
import mx.secure.nci.business.vo.CifrasTotalesDomiVO;
import mx.secure.nci.business.vo.GeneracionArchivoDomiVO;
import mx.secure.nci.business.vo.PeticionDomiciliacionVO;
import mx.secure.nci.business.vo.ProcesamientoDomiVO;
import mx.secure.nci.business.vo.RegistroCifrasDomiVO;
import mx.secure.nci.business.vo.RegistrosArchivoVO;
import mx.secure.nci.business.vo.TiposCuentaDomiVO;
import mx.secure.nci.business.vo.TotalesRegistroDomiVO;
import mx.secure.nci.business.vo.ValidacionesVO;
import mx.secure.nci.business.wrapped.ArchivoDomiciliacionfilter;
import mx.secure.nci.business.wrapped.ArchivosDomiBitacoraFilter;
import mx.secure.nci.business.wrapped.CatalogosDomiFilter;
import mx.secure.nci.business.wrapped.CifraDomiFilter;
import mx.secure.nci.business.wrapped.CifrasTotalesDomiFilter;
import mx.secure.nci.business.wrapped.DomiParameterMapFilter;
import mx.secure.nci.business.wrapped.FiltroArchivosDomiciliacionFilter;
import mx.secure.nci.business.wrapped.PeticionesDomiFilter;
import mx.secure.nci.business.wrapped.SolicitudFilter;
import mx.secure.nci.business.wrapped.UltimosArchivosGeneradosFilter;
import mx.secure.nci.ws.beans.request.ArchivosDomiBitacoraBeanRequest;
import mx.secure.nci.ws.beans.request.CatalogosDomiBeanRequest;
import mx.secure.nci.ws.beans.request.FiltroArchivosDomiciliacionBeanRequest;
import mx.secure.nci.ws.beans.request.PeticionesDomiBeanRequest;
import mx.secure.nci.ws.beans.request.TablaDinamicaArchDomiBeanRequest;
import mx.secure.nci.ws.beans.request.TipoProcesamientoDomisBeanRequest;
import mx.secure.nci.ws.beans.request.UltimosArchivosGeneradosBeanRequest;
import mx.secure.nci.ws.beans.response.ArchivosDomiBitacoraBeanResponse;
import mx.secure.nci.ws.beans.response.ArchivosDomiciliacionBeanResponse;
import mx.secure.nci.ws.beans.response.ArchivosDomiciliacionBitacoraBeanResponse;
import mx.secure.nci.ws.beans.response.ArchivosGeneradosDomiBeanResponse;
import mx.secure.nci.ws.beans.response.CatalogosDomiBeanResponse;
import mx.secure.nci.ws.beans.response.CifrasDomiBeanResponse;
import mx.secure.nci.ws.beans.response.GeneracionArchivosDomiBeanResponse;
import mx.secure.nci.ws.beans.response.RegistrosArchivoBeanResponse;
import mx.secure.nci.ws.beans.response.TipoProcesamientoDomiResponse;
import mx.secure.nci.ws.beans.response.TablaDinamicaArchDomiBeanResponse;
import mx.secure.nci.ws.beans.response.TipoProcesamientoDomisBeanResponse;
import mx.secure.nci.ws.exceptions.FaultBeanServiceInfo;
import mx.secure.nci.ws.exceptions.MitWebServiceException;
import mx.secure.nci.ws.service.domiciliaciones.IDomiciliacionesSoapService;
import mx.secure.nci.business.wrapped.OperacionDomiBitacoraFilter;
import mx.secure.nci.business.wrapped.TablaDinamicaArchDomiFilter;
import mx.secure.nci.business.wrapped.TipoProcesamientoDomisFilter;

@Service("domiciliacionesSoapService")
public class DomiciliacionesSoapServiceImpl implements IDomiciliacionesSoapService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DomiciliacionesSoapServiceImpl.class);

	@Autowired
	IDomiciliacionesService domiciliacionesService;

	@Override
	public TipoProcesamientoDomiResponse consultarTipoProcesamientoDomi() {
		TipoProcesamientoDomiResponse response = null;
		ProcesamientoDomiVO procesamiento = null;

		try {
			procesamiento = this.domiciliacionesService.consultarProcesamientoDomi();

			if (procesamiento != null && procesamiento.getValor() != null) {
				response = new TipoProcesamientoDomiResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(),
						"Consulta de  parametro de tipo de procesamiento realizada correctamente", procesamiento);
			} else {
				response = new TipoProcesamientoDomiResponse(CtrlResponseWSEnum.WS_NO_RECORD.getCodRet(),
						CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet(),
						"No se ha encontrado el parametro de tipo de procesamiento");
			}

			return response;
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN    :" + DomiciliacionesSoapServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO       :" + "consultarTipoProcesamientoDomi");
			LOGGER.error("ERROR MENSAJE      :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCIÓN  :", e);
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", DomiciliacionesSoapServiceImpl");
			throw new MitWebServiceException(e.getMessage(), missing);

		} finally {
			response = null;
			procesamiento = null;
		}
	}

	@Override
	public ArchivosDomiBitacoraBeanResponse consultarArchivosDomiBitacora(ArchivosDomiBitacoraBeanRequest request) {
		Boolean dummy = Boolean.FALSE;
		ArchivosDomiBitacoraBeanResponse response = null;
		ValidacionesVO val = null;
		List<ArchivoDomiVO> valores = null;

		try {
			// Validacion reponse nulo
			if (request == null) {
				return new ArchivosDomiBitacoraBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), "Request, es necesario para el servicio.");
			}

			// Validación de request
			if (!(val = request.validarRequest()).isEstatus()) {

				return new ArchivosDomiBitacoraBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), val.getMensaje());
			}

			if (dummy) {
				valores = new ArrayList<ArchivoDomiVO>();
				valores.add(new ArchivoDomiVO("secure", "Bancomer", "Cuenta clabe", "archivo24112021BancomerA.txt",
						"JAVA", "1", Boolean.TRUE, "MSG", "PATH"));
				valores.add(new ArchivoDomiVO("secure", "Bancomer", "Tarjeta", "archivo24112021BancomerC.txt", "JAVA",
						"2", Boolean.TRUE, "MSG", "PATH"));
				valores.add(new ArchivoDomiVO("secure", "Bancomer", "Chequera", "archivo24112021BancomerB.txt", "JAVA",
						"3", Boolean.TRUE, "MSG", "PATH"));

				response = new ArchivosDomiBitacoraBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Generacion de archivos correcta", valores);
			} else {
				valores = this.domiciliacionesService
						.consultarArchivosDomiBitacora(new ArchivosDomiBitacoraFilter(request.getFolio()));

				if (valores != null && !valores.isEmpty()) {
					response = new ArchivosDomiBitacoraBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
							CtrlResponseWSEnum.WS_OK.getMsgRet(), "Bitacora de archivos obtenida correctamente",
							valores);
				} else {
					response = new ArchivosDomiBitacoraBeanResponse(CtrlResponseWSEnum.WS_NO_RECORD.getCodRet(),
							CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet(),
							"Sin bitacora de archivos con folio ingresado");
				}
			}

			return response;
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN    :" + DomiciliacionesSoapServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO       :" + "consultarArchivosDomiBitacora");
			LOGGER.error("ERROR MENSAJE      :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCIÓN  :", e);
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", DomiciliacionesSoapServiceImpl");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {

		}
	}

	@Override
	public CatalogosDomiBeanResponse catalogosControlDomi(CatalogosDomiBeanRequest request) {
		CatalogosDomiBeanResponse response = null;
		List<CatalogosDomiVO> valores = null;
		Boolean dummy = Boolean.FALSE;
		ValidacionesVO val = null;

		// Validacion reponse nulo
		if (request == null) {
			return new CatalogosDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
					CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), "Request, es necesario para el servicio.");
		}

		try {
			if (dummy) {
				valores = new ArrayList<CatalogosDomiVO>();
				valores.add(new CatalogosDomiVO("1", "secure"));
				valores.add(new CatalogosDomiVO("2", "BBVA"));
				valores.add(new CatalogosDomiVO("3", "OTROS"));

				response = new CatalogosDomiBeanResponse(
						(valores.isEmpty()) ? CtrlResponseWSEnum.WS_NO_RECORD.getCodRet()
								: CtrlResponseWSEnum.WS_OK.getCodRet(),
						(valores.isEmpty()) ? CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet()
								: CtrlResponseWSEnum.WS_OK.getMsgRet(),
						(valores.isEmpty()) ? "No existen registros con ese tipo de Catálogo" : "Consulta Exitosa",
						valores);
				return response;
			} else {

				// Validación de request
				if (!(val = request.validarRequest()).isEstatus()) {
					return new CatalogosDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
							CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), val.getMensaje());
				}

				// Validación del tipo de catalogo
				if ((Integer.parseInt(request.getTipoCatalogo()) > 5)
						|| (Integer.parseInt(request.getTipoCatalogo()) < 0)) {
					return new CatalogosDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
							CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), "TIPO DE CAT INGRESADO NO CONFIGURADO");
				}

				// Consulta de valores
				valores = this.domiciliacionesService
						.getCatalogosControlDomi(new CatalogosDomiFilter(request.getTipoCatalogo()));

				if (valores == null || valores.isEmpty()) {
					return new CatalogosDomiBeanResponse(CtrlResponseWSEnum.WS_NO_RECORD.getCodRet(),
							CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet(), "CAT SIN RESULTADOS");
				}

				response = new CatalogosDomiBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "CAT CON RESULTADOS", valores);

			}

			return response;

		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN    :" + DomiciliacionesSoapServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO       :" + "catalogosControlDomi");
			LOGGER.error("ERROR MENSAJE      :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCIÓN  :", e);

			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", DomiciliacionesSoapServiceImpl");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {
			response = null;
			valores = null;
			dummy = null;
			val = null;
		}
	}

	/********************** GEN DE ARCHIVOS ORDINARIA ************************/
	@Override
	public GeneracionArchivosDomiBeanResponse generarArchivosDomiLotes(PeticionesDomiBeanRequest request) {
		Boolean dummy = Boolean.FALSE;
		GeneracionArchivosDomiBeanResponse response = null;
		List<ArchivoDomiVO> valores = null;
		ValidacionesVO val = null;
		DomiParameterMapFilter filters = null;
		List<SolicitudFilter> filtros = null;
		Date fechaHoy = null;
		String archivo = null;
		ArchivoDomiciliacionfilter filter = null;
		String message = null;
		GeneracionArchivoDomiVO archivoGenerado = null;
		Integer consecutivo = null;
		String respGeneracion = null;
		String[] error = null;
		List<GeneracionArchivoDomiVO> resultados = null;
		Long idArchivoGenerado = null;
		String pathFile = null;
		String idTipoCuentaGenerado = null;
		String idBancoGenerado = null;
		String idTipoArchivoGenerado = null;

		try {
			// Validacion reponse nulo
			if (request == null) {
				return new GeneracionArchivosDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), "Request, es necesario para el servicio.");
			}

			// Validacion de request
			if (!(val = request.validarRequestArchivosDomi()).isEstatus()) {
				return new GeneracionArchivosDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), val.getMensaje());
			}

			// Validacion si se encuntra liberado proceso en ambiente
			if (Integer.parseInt(this.domiciliacionesService.getParametroLiberacion()) <= 0) {
				return new GeneracionArchivosDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(),
						(this.domiciliacionesService.getParametroLiberacion().equals("-1")
								|| this.domiciliacionesService.getParametroLiberacion().equals("-2"))
										? "No se encuntra el parametro de liberacion"
										: "Parametro de liberacion apagado");
			}

			if (dummy) {
				valores = new ArrayList<ArchivoDomiVO>();
				valores.add(new ArchivoDomiVO("secure", "Bancomer", "Cuenta clabe", "archivo24112021BancomerA.txt",
						"JAVA", "1", Boolean.TRUE, "MSG", "PATH"));
				valores.add(new ArchivoDomiVO("secure", "Bancomer", "Tarjeta", "archivo24112021BancomerC.txt", "JAVA",
						"2", Boolean.TRUE, "MSG", "PATH"));
				valores.add(new ArchivoDomiVO("secure", "Bancomer", "Chequera", "archivo24112021BancomerB.txt", "JAVA",
						"3", Boolean.TRUE, "MSG", "PATH"));

				response = new GeneracionArchivosDomiBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Generación de archivos correcta", valores,
						request.getFolio());
			} else {
				// Inicialización de variables
				resultados = new ArrayList<GeneracionArchivoDomiVO>();
				valores = new ArrayList<ArchivoDomiVO>();

				// Request to filter
				filters = this.domiciliacionesService.getDomiParameterMapFilter(
						new PeticionesDomiFilter(request.getOrigenAportacion(), request.getFechaInicio(),
								request.getFechaFin(), request.getUsuario(), request.getPeticiones()));

				filtros = this.domiciliacionesService.getCustomFilter(filters);

				// Generar archivos
				if (filtros != null && !filtros.isEmpty()) {
					fechaHoy = Calendar.getInstance().getTime();

					for (SolicitudFilter filtro : filtros) {
						archivoGenerado = new GeneracionArchivoDomiVO();
						archivo = null;
						idTipoCuentaGenerado = "0";
						idBancoGenerado = "0";
						idTipoArchivoGenerado = "0";

						// Try Catch propoio para tratar resultados
						try {
							filter = new ArchivoDomiciliacionfilter();
							filter.setIdOrigenSolicitud(
									(filtro.getOrigenAportacion() != null ? filtro.getOrigenAportacion().getId()
											: null));
							consecutivo = this.domiciliacionesService.getConsecutivoArchivoDomi(filter);

							if (ID_ORIGEN_DOMICILIACION_TRASPASO.equals(filtro.getOrigenAportacion().getId())) {
								consecutivo += FILE_NAME_OFFSET_DOMICILIACIONES_TRASPASO;
							}

							archivo = this.domiciliacionesService.getNombreArchivoDomi(
									filtro.getOrigenAportacion().getId(), filtro.getIdTipoArchivo(), consecutivo,
									fechaHoy);

							if (archivo != null) {
								archivoGenerado.setNombreArchivo(archivo);
								respGeneracion = this.domiciliacionesService.generarArchivoDomiF4Mant(filtro, fechaHoy,
										archivo, consecutivo, filters.getIdOrigenDomiciliacion(), idArchivoGenerado);

								if (respGeneracion != null && this.domiciliacionesService.isNumber(respGeneracion)
										&& Long.parseLong(respGeneracion) > 0) {
									idArchivoGenerado = Long.parseLong(respGeneracion);
									archivoGenerado.setGenerated(Boolean.TRUE);
									message = this.domiciliacionesService.getFileGeneratedData(archivo, filtro) + " - "
											+ "Archivo generado correctamente";
									archivoGenerado.getErrors().add(message);
								} else {
									idArchivoGenerado = Long.parseLong("0");
									archivoGenerado.setGenerated(Boolean.FALSE);
									archivoGenerado.getErrors().add(respGeneracion);
								}
							}

						} catch (Exception e) {
							archivoGenerado.setGenerated(Boolean.FALSE);
							error = e.getMessage().split(":");
							archivoGenerado.getErrors().add(error[0]);
						}

						// Se agrega resultado (independientemente si se generó o no) a la lista
						// original
						resultados.add(archivoGenerado);

						// Se anexa ruta
						if (ID_secure_NCI.equals(filtro.getIdTipoArchivo())) {
							pathFile = PATH_ACHIVO_DOMI_secure;
						} else if (ID_BANCOMER_NCI.equals(filtro.getIdTipoArchivo())) {
							pathFile = PATH_ACHIVO_DOMI_BANCOMER;
						}

						// Se determina el tipo de cuenta
						if (filtro.getIdsTiposCuenta() != null) {
							idTipoCuentaGenerado = (filtro.getIdsTiposCuenta().size() > 1) ? "0"
									: String.valueOf(filtro.getIdsTiposCuenta().get(0));
						} else {
							idTipoCuentaGenerado = "0";
						}

						// Se determina el banco ("Ctas a incluir")
						if (filtro.getIdsBancos() != null && !filtro.getIdsBancos().isEmpty()) {
							idBancoGenerado = String.valueOf(filtro.getIdsBancos().get(0));
						} else {
							idBancoGenerado = "848";
						}

						// Se determina tipo de archivo (unico o individual)
						idTipoArchivoGenerado = (filtro.getArchivoUnico()) ? "1" : "2";

						// Se registra en tabla relacion folio-archivo(s)
						this.domiciliacionesService.registrarArchivoDomiBitacora(new OperacionDomiBitacoraFilter(
								request.getFolio(), idArchivoGenerado, Long.parseLong(request.getIdTipoContrato()),
								Long.parseLong((archivoGenerado.getGenerated()) ? "1" : "0"),
								Long.parseLong(idTipoArchivoGenerado), Long.parseLong(idBancoGenerado),
								Long.parseLong(String.valueOf(filtro.getIdTipoArchivo())),
								Long.parseLong(idTipoCuentaGenerado), archivoGenerado.getNombreArchivo(),
								(archivoGenerado.getErrors() != null && !archivoGenerado.getErrors().isEmpty())
										? archivoGenerado.getErrors().get(0)
										: "",
								pathFile, (archivoGenerado.getGenerated()) ? 1 : 0, request.getUsuario()));

						// Se agrega resultado
						valores.add(new ArchivoDomiVO(null, null, null, archivoGenerado.getNombreArchivo(),
								request.getUsuario(), String.valueOf(idArchivoGenerado), archivoGenerado.getGenerated(),
								(archivoGenerado.getErrors() != null && !archivoGenerado.getErrors().isEmpty())
										? archivoGenerado.getErrors().get(0)
										: "",
								pathFile));

					}

					// Formalización del response
					if (valores != null && !valores.isEmpty()) {
						response = new GeneracionArchivosDomiBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
								CtrlResponseWSEnum.WS_OK.getMsgRet(),
								"Generacion de archivos correcta (archivos generados explicitos en lista)", valores,
								request.getFolio());
					} else {
						response = new GeneracionArchivosDomiBeanResponse(CtrlResponseWSEnum.WS_NO_RECORD.getCodRet(),
								CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet(), "Sin archivos generados");
					}

				} else {
					response = new GeneracionArchivosDomiBeanResponse(CtrlResponseWSEnum.WS_NO_RECORD.getCodRet(),
							CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet(),
							"Sin resultados para generar archivos de domiciliacion con base en parametros de entrada");
				}
			}

			// Mandar a log resultados para posible rastreabilidad de operaciones
			LOGGER.info("*********** ARCHIVOS DOMI GENERADOS: " + new Gson().toJson(resultados));

			return response;
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN    :" + DomiciliacionesSoapServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO       :" + "generarArchivosDomi");
			LOGGER.error("ERROR MENSAJE      :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCIÓN  :", e);
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", DomiciliacionesSoapServiceImpl");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {
			dummy = null;
			response = null;
			valores = null;
			val = null;
			filters = null;
			filtros = null;
			fechaHoy = null;
			archivo = null;
			filter = null;
			message = null;
			archivoGenerado = null;
			consecutivo = null;
			respGeneracion = null;
			error = null;
			resultados = null;
			idArchivoGenerado = null;
			pathFile = null;
			idTipoCuentaGenerado = null;
			idBancoGenerado = null;
			idTipoArchivoGenerado = null;
		}
	}

	@Override
	public ArchivosGeneradosDomiBeanResponse ultimosArchivosGenerados(UltimosArchivosGeneradosBeanRequest request) {

		ArchivosGeneradosDomiBeanResponse response = null;
		List<ArchivosGeneradosDomiVO> valores = null;
		Boolean dummy = Boolean.FALSE;
		ValidacionesVO val = null;

		try {
			// Validación reponse nulo
			if (request == null) {
				return new ArchivosGeneradosDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), "Request, es necesario para el servicio.");
			}

			if (dummy) {
				valores = new ArrayList<ArchivosGeneradosDomiVO>();
				valores.add(new ArchivosGeneradosDomiVO("18/12/2020", "18/12/2020", "460", "BBVA", "847", "CHEQUERA",
						"459", "secure", true));
				valores.add(new ArchivosGeneradosDomiVO("18/12/2020", "18/12/2020", "460", "BBVA", "496",
						"TARJETA DE DEBITO", "459", "secure", true));
				valores.add(new ArchivosGeneradosDomiVO("18/12/2020", "18/12/2020", "460", "BBVA", "497", "CLABE",
						"459", "secure", true));

				response = new ArchivosGeneradosDomiBeanResponse(
						(valores.isEmpty()) ? CtrlResponseWSEnum.WS_NO_RECORD.getCodRet()
								: CtrlResponseWSEnum.WS_OK.getCodRet(),
						(valores.isEmpty()) ? CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet()
								: CtrlResponseWSEnum.WS_OK.getMsgRet(),
						(valores.isEmpty()) ? "No existen registros con ese tipo de Archivo" : "Consulta Exitosa",
						valores);
				return response;
			} else {

				// Validación de request
				if (!(val = request.validarOrigenAportacion()).isEstatus()) {
					return new ArchivosGeneradosDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
							CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), val.getMensaje());
				}
				if (!(val = request.validarCuentasIncluir()).isEstatus()) {
					return new ArchivosGeneradosDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
							CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), val.getMensaje());
				}
				if (!(val = request.validarArchivoGenerar()).isEstatus()) {
					return new ArchivosGeneradosDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
							CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), val.getMensaje());
				}

				valores = this.domiciliacionesService.ultimosArchivosGenerados(new UltimosArchivosGeneradosFilter(
						request.getOrigenAportacion(), request.getCuentasIncluir(), request.getArchivoGenerar()));

				response = new ArchivosGeneradosDomiBeanResponse(
						(valores.isEmpty()) ? CtrlResponseWSEnum.WS_NO_RECORD.getCodRet()
								: CtrlResponseWSEnum.WS_OK.getCodRet(),
						(valores.isEmpty()) ? CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet()
								: CtrlResponseWSEnum.WS_OK.getMsgRet(),
						(valores.isEmpty()) ? "No existen registros con los datos ingresados" : "Consulta Exitosa",
						valores);

			}

			return response;

		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesSoapServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "ultimosArchivosGenerados");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCIÓN :", e);

			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", ultimosArchivosGenerados");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {
			response = null;
			valores = null;
			dummy = null;
		}

	}

	@Override
	public CifrasDomiBeanResponse cifrasDomi(PeticionesDomiBeanRequest request) {
		CifrasDomiBeanResponse response = null;
		RegistroCifrasDomiVO valores = null;
		List<RegistroCifrasDomiVO> registros = null;
		List<TotalesRegistroDomiVO> totales = null;
		List<CifrasTotalesDomiVO> cifrasTotales = null;
		List<CifrasTotalesDomiVO> cifrasTotalesAux = null;
		RegistroCifrasDomiVO registroCifraAux = null;
		ValidacionesVO val = null;
		List<Integer> tipoCtas = null;
		List<Integer> cveBancos = null;
		CifrasTotalesDomiVO cifraTotAux = null;
		List<String> idPeriodosTot = null;
		Boolean existePeriodo = null;
		Double pesos = null;
		String nombrePeriodo = null;
		DecimalFormat formatterPesos = null;
		DecimalFormat formatterCount = null;
		Boolean cifrasTotalesConPeriodos = null;

		try {
			// Validacion reponse nulo
			if (request == null) {
				return new CifrasDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), "Request, es necesario para el servicio.");
			}

			// Validación de request
			if (!(val = request.validarRequestCifras()).isEstatus()) {
				return new CifrasDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), val.getMensaje());
			}

			// Busqueda de totales
			cifrasTotalesAux = new ArrayList<CifrasTotalesDomiVO>();
			cifrasTotales = new ArrayList<CifrasTotalesDomiVO>();
			cifrasTotalesConPeriodos = Boolean.FALSE;

			for (PeticionDomiciliacionVO reg : request.getPeticiones()) {
				tipoCtas = new ArrayList<Integer>();
				cveBancos = new ArrayList<Integer>();

				for (TiposCuentaDomiVO cta : reg.getTiposCuenta()) {
					tipoCtas.add(Integer.parseInt(cta.getIdTipoCta()));
				}

				cveBancos.add(Integer.parseInt(reg.getCveBancoIncluir()));
				cifrasTotalesAux = new ArrayList<CifrasTotalesDomiVO>();

				if (reg.getCveBancoIncluir().equals("848")) { // CONSULTA OTROS BANCOS
					cifrasTotalesAux = this.domiciliacionesService
							.getCifrasTotalesOtrosBancos(new CifrasTotalesDomiFilter(request.getOrigenAportacion(),
									request.getFechaInicio(), request.getFechaFin(), tipoCtas, cveBancos));
				} else { // BBVA Y secure
					cifrasTotalesAux = this.domiciliacionesService
							.getCifrasTotales(new CifrasTotalesDomiFilter(request.getOrigenAportacion(),
									request.getFechaInicio(), request.getFechaFin(), tipoCtas, cveBancos));
				}

				cifrasTotales.addAll(cifrasTotalesAux);
			}

			if (cifrasTotales == null || cifrasTotales.isEmpty()) {
				return new CifrasDomiBeanResponse(CtrlResponseWSEnum.WS_NO_RECORD.getCodRet(),
						CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet(),
						"No existen registros de cifras con los parametros de entrada");
			}

			// Verificamos que si tenga un periodo (SEMANAL, QUINCENAL, MENSUAL, UNICO, ETC)
			for (CifrasTotalesDomiVO cifra : cifrasTotales) {
				if (Integer.parseInt(cifra.getIdPerido()) > 0) {
					cifrasTotalesConPeriodos = Boolean.TRUE;
					break;
				}
			}

			if (!cifrasTotalesConPeriodos) { /*
												 * SI EL TAMANIO ES IGUAL A 2 ENTONCES ES VACIO YA QUE SOLO TRAE TOTALES
												 * (IMPORTE TOTAL Y REGISTROS TOTALES)
												 */
				return new CifrasDomiBeanResponse(CtrlResponseWSEnum.WS_NO_RECORD.getCodRet(),
						CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet(),
						"No existen registros de cifras con los parametros de entrada");
			}

			// Limpieza de cifras totales
			cifrasTotalesAux = new ArrayList<CifrasTotalesDomiVO>();
			cifrasTotalesAux = cifrasTotales;
			cifrasTotales = new ArrayList<CifrasTotalesDomiVO>();
			idPeriodosTot = new ArrayList<String>();
			formatterPesos = new DecimalFormat("###,###,##0.00");
			formatterCount = new DecimalFormat("###,###,##0");

			// Peridodos obtenidos
			for (CifrasTotalesDomiVO cifra : cifrasTotalesAux) {
				existePeriodo = Boolean.FALSE;

				for (String periodo : idPeriodosTot) {
					if (periodo.equals(cifra.getIdPerido())) {
						existePeriodo = Boolean.TRUE;
						break;
					}
				}

				if (!existePeriodo)
					idPeriodosTot.add(cifra.getIdPerido());
			}

			// Sumatoria de los periodos
			for (String periodo : idPeriodosTot) {
				cifraTotAux = new CifrasTotalesDomiVO();
				pesos = 0.0;
				nombrePeriodo = "";

				for (CifrasTotalesDomiVO cifra : cifrasTotalesAux) {
					if (periodo.equals(cifra.getIdPerido())) {
						if (cifra.getPesos() != null)
							pesos += Double.parseDouble(cifra.getPesos());

						nombrePeriodo = cifra.getDescPeriodo();
					}
				}

				cifraTotAux.setPesos(
						(periodo.equals("-1")) ? formatterCount.format(pesos) : ("$" + formatterPesos.format(pesos)));
				cifraTotAux.setDescPeriodo(nombrePeriodo);
				cifraTotAux.setIdPerido(periodo);
				cifrasTotales.add(cifraTotAux);
			}

			// Orden de cifras totales
			if (cifrasTotales != null && !cifrasTotales.isEmpty()) {
				cifrasTotales = this.domiciliacionesService.ordenarListaCifrasTotales(cifrasTotales);
			}

			// Busqueda de detalles
			registros = new ArrayList<RegistroCifrasDomiVO>();
			for (PeticionDomiciliacionVO reg : request.getPeticiones()) {
				for (TiposCuentaDomiVO cta : reg.getTiposCuenta()) {
					registroCifraAux = this.domiciliacionesService.getCifraDomi(new CifraDomiFilter(
							reg.getCveBancoIncluir(), reg.getArchivoGenenerar(), cta.getIdTipoCta()));
					if (registroCifraAux != null) {
						if (reg.getCveBancoIncluir().equals("848")) { // CONSULTA OTROS BANCOS
							totales = this.domiciliacionesService
									.getImportesTotalesCifraOtrosBancos(new CifraDomiFilter(reg.getCveBancoIncluir(),
											reg.getArchivoGenenerar(), cta.getIdTipoCta(), request.getFechaInicio(),
											request.getFechaFin(), request.getOrigenAportacion()));

						} else { // secure Y BBVA
							totales = this.domiciliacionesService.getImportesTotalesCifra(new CifraDomiFilter(
									reg.getCveBancoIncluir(), reg.getArchivoGenenerar(), cta.getIdTipoCta(),
									request.getFechaInicio(), request.getFechaFin(), request.getOrigenAportacion()));
						}

						registroCifraAux.setTotales(totales);
						registros.add(registroCifraAux);
					}

				}
			}

			// Armado de response
			response = new CifrasDomiBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
					CtrlResponseWSEnum.WS_OK.getMsgRet(), "CONSULTA CORRECTA CIFRAS BANCOS", cifrasTotales, registros);

			return response;

		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN    :" + DomiciliacionesSoapServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO       :" + "cifrasDomi");
			LOGGER.error("ERROR MENSAJE      :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCIÓN  :", e);

			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", cifrasDomi");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {
			response = null;
			valores = null;
			registros = null;
			totales = null;
			cifrasTotales = null;
			val = null;
			tipoCtas = null;
			cveBancos = null;
			registroCifraAux = null;
			cifrasTotalesAux = null;
			cifraTotAux = null;
			idPeriodosTot = null;
			nombrePeriodo = null;
			pesos = null;
			existePeriodo = null;
			formatterPesos = null;
			cifrasTotalesConPeriodos = null;
			formatterCount = null;
		}

	}

	/**
	 * Metodo que consulta el Business para obtener la informacion.
	 * 
	 * @param request,
	 *            objeto de entrada
	 */
	@Override
	public ArchivosDomiciliacionBeanResponse consultarArchivosDomiciliacion(
			FiltroArchivosDomiciliacionBeanRequest request) {
		FiltroArchivosDomiciliacionFilter filter = null;
		ValidacionesVO validacion = null;
		List<ArchivosDomiciliacionVO> lArchivos = null;
		ArchivosDomiciliacionBeanResponse response = null;

		try {

			// Request vacio.
			if (request == null) {
				return new ArchivosDomiciliacionBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), "Request, es necesario para el servicio.");
			}

			// Validamos los parametros de entrada no sean vacios
			validacion = request.validarRequest();
			if (!validacion.isEstatus()) {
				return new ArchivosDomiciliacionBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), validacion.getMensaje());
			}

			// Armamos el objeto para mandarlo a Business
			filter = new FiltroArchivosDomiciliacionFilter(request.getFechaInicio(), request.getFechaFin());
			lArchivos = domiciliacionesService.consultarArchivosDomiciliacion(filter);

			// Validamos que la lista no se encuntre vacia.
			if (lArchivos.isEmpty() || lArchivos == null) {
				return new ArchivosDomiciliacionBeanResponse(CtrlResponseWSEnum.WS_NO_RECORD.getCodRet(),
						CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet(),
						"Sin resultados en archivos domiciliacion con parametros ingresados.");

			} else {
				// Retornamos los registros
				response = new ArchivosDomiciliacionBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Consulta exitosa.", lArchivos);

				response.setInfoReporte(new Gson().toJson(response));

				return response;
			}

		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN    :" + DomiciliacionesSoapServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO       :" + "consultarArchivosDomiciliacion");
			LOGGER.error("ERROR MENSAJE      :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCIÓN  :", e);

			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", consultarArchivosDomiciliacion");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {
			// Liberacion de memoria.
			filter = null;
			validacion = null;
			lArchivos = null;
			response = null;
		}
	}

	/**
	 * Metodo que consulta el Business para obtener la informacion.
	 * 
	 * @param request,
	 *            objeto de entrada
	 */
	@Override
	public ArchivosDomiciliacionBitacoraBeanResponse consultarArchivosDomiciliacionBitacora(
			FiltroArchivosDomiciliacionBeanRequest request) {
		FiltroArchivosDomiciliacionFilter filter = null;
		ValidacionesVO validacion = null;
		List<ArchivosDomiciliacionBitacoraVO> lArchivos = null;
		ArchivosDomiciliacionBitacoraBeanResponse response = null;

		try {

			// Request vacio.
			if (request == null) {
				return new ArchivosDomiciliacionBitacoraBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), "Request, es necesario para el servicio.");
			}

			// Validamos los parametros de entrada no sean vacios
			validacion = request.validarRequest();
			if (!validacion.isEstatus()) {
				return new ArchivosDomiciliacionBitacoraBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), validacion.getMensaje());
			}

			// Armamos el objeto para mandarlo a Business
			filter = new FiltroArchivosDomiciliacionFilter(request.getFechaInicio(), request.getFechaFin());
			lArchivos = domiciliacionesService.consultarArchivosDomiciliacionBitacora(filter);

			// Validamos que la lista no se encuntre vacia.
			if (lArchivos.isEmpty() || lArchivos == null) {
				return new ArchivosDomiciliacionBitacoraBeanResponse(CtrlResponseWSEnum.WS_NO_RECORD.getCodRet(),
						CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet(),
						"Sin resultados en archivos domiciliacion bitacora con parametros ingresados.");
			} else {
				// Retornamos los registros
				response = new ArchivosDomiciliacionBitacoraBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Consulta exitosa.", lArchivos);

				response.setInfoReporte(new Gson().toJson(response));

				return response;
			}

		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN    :" + DomiciliacionesSoapServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO       :" + "consultarArchivosDomiciliacionBitacora");
			LOGGER.error("ERROR MENSAJE      :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCIÓN  :", e);

			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(
					CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", consultarArchivosDomiciliacionBitacora");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {
			// Liberacion de memoria.
			filter = null;
			validacion = null;
			lArchivos = null;
			response = null;
		}
	}

	// ******************* GEN DE ARCHIVOS POR LOTES ******************************
	@Override
	public GeneracionArchivosDomiBeanResponse generarArchivosDomi(PeticionesDomiBeanRequest request) {
		Boolean dummy = Boolean.FALSE;
		GeneracionArchivosDomiBeanResponse response = null;
		List<ArchivoDomiVO> valores = null;
		ValidacionesVO val = null;
		DomiParameterMapFilter filters = null;
		List<SolicitudFilter> filtros = null;
		Date fechaHoy = null;
		ArchivoDomiciliacionfilter filter = null;
		String message = null;
		GeneracionArchivoDomiVO archivoGenerado = null;
		List<RespGeneracionArchivosDomi> generaciones = null;
		String respGeneracion = null;
		String[] error = null;
		List<GeneracionArchivoDomiVO> resultados = null;
		Long idArchivoGenerado = null;
		String pathFile = null;
		String idTipoCuentaGenerado = null;
		String idBancoGenerado = null;
		String idTipoArchivoGenerado = null;
		String nombreContrato = null;
		Boolean resouestaFinalizacionArch = null;

		try {
			// Validacion reponse nulo
			if (request == null) {
				return new GeneracionArchivosDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), "Request, es necesario para el servicio.");
			}

			// Validacion de request
			if (!(val = request.validarRequestArchivosDomi()).isEstatus()) {
				return new GeneracionArchivosDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), val.getMensaje());
			}

			// Validacion si se encuntra liberado proceso en ambiente
			if (Integer.parseInt(this.domiciliacionesService.getParametroLiberacion()) <= 0) {
				return new GeneracionArchivosDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(),
						(this.domiciliacionesService.getParametroLiberacion().equals("-1")
								|| this.domiciliacionesService.getParametroLiberacion().equals("-2"))
										? "No se encuntra el parametro de liberacion"
										: "Parametro de liberacion apagado");
			}

			// Obtener nombre del contrato (Se realiza validacion)
			nombreContrato = this.domiciliacionesService.getNombreContrato(request.getIdTipoContrato());

			if (nombreContrato == null || nombreContrato.equals("-1") || nombreContrato.equals("")) {

				return new GeneracionArchivosDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(),
						"No se ha encontrado descripcion en cat_catalogo del tipo de contrato especificado.");
			}

			// Inicialización de variables
			resultados = new ArrayList<GeneracionArchivoDomiVO>();
			valores = new ArrayList<ArchivoDomiVO>();
			generaciones = new ArrayList<RespGeneracionArchivosDomi>();

			// Request to filter
			filters = this.domiciliacionesService.getDomiParameterMapFilter(
					new PeticionesDomiFilter(request.getOrigenAportacion(), request.getFechaInicio(),
							request.getFechaFin(), request.getUsuario(), request.getPeticiones()));

			filtros = this.domiciliacionesService.getCustomFilter(filters);

			// Generar archivos
			if (filtros != null && !filtros.isEmpty()) {
				fechaHoy = Calendar.getInstance().getTime();

				for (SolicitudFilter filtro : filtros) {
					archivoGenerado = new GeneracionArchivoDomiVO();
					idTipoCuentaGenerado = "0";
					idBancoGenerado = "0";
					idTipoArchivoGenerado = "0";

					// Try Catch propoio para tratar resultados
					try {
						filter = new ArchivoDomiciliacionfilter();
						filter.setIdOrigenSolicitud(
								(filtro.getOrigenAportacion() != null ? filtro.getOrigenAportacion().getId() : null));

						generaciones = this.domiciliacionesService.generarArchivoDomiF4MantLotes(filtro, fechaHoy,
								filters.getIdOrigenDomiciliacion(), idArchivoGenerado, nombreContrato, filter);

						// Se traen las generaciones (posibles lotes)
						for (RespGeneracionArchivosDomi gen : generaciones) {
							archivoGenerado = new GeneracionArchivoDomiVO();
							idTipoCuentaGenerado = "0";
							idBancoGenerado = "0";
							idTipoArchivoGenerado = "0";
							respGeneracion = gen.getIdArchivo();

							archivoGenerado.setNombreArchivo(gen.getNombreArchivo());
							if (respGeneracion != null && this.domiciliacionesService.isNumber(respGeneracion)
									&& Long.parseLong(respGeneracion) > 0) {

								idArchivoGenerado = Long.parseLong(respGeneracion);
								archivoGenerado.setGenerated(Boolean.TRUE);

								message = this.domiciliacionesService.getFileGeneratedData(gen.getNombreArchivo(),
										filtro) + " - " + "Archivo generado correctamente";
								archivoGenerado.getErrors().add(message);
							} else {
								idArchivoGenerado = Long.parseLong("0");
								archivoGenerado.setGenerated(Boolean.FALSE);
								archivoGenerado.getErrors().add(respGeneracion);
							}

							// Se agrega resultado (independientemente si se generó o no) a la lista
							// original
							resultados.add(archivoGenerado);

							// Se anexa ruta
							if (ID_secure_NCI.equals(filtro.getIdTipoArchivo())) {
								pathFile = PATH_ACHIVO_DOMI_secure;
							} else if (ID_BANCOMER_NCI.equals(filtro.getIdTipoArchivo())) {
								pathFile = PATH_ACHIVO_DOMI_BANCOMER;
							}

							// Se determina el tipo de cuenta
							if (filtro.getIdsTiposCuenta() != null) {
								idTipoCuentaGenerado = (filtro.getIdsTiposCuenta().size() > 1) ? "0"
										: String.valueOf(filtro.getIdsTiposCuenta().get(0));
							} else {
								idTipoCuentaGenerado = "0";
							}

							// Se determina el banco ("Ctas a incluir")
							if (filtro.getIdsBancos() != null && !filtro.getIdsBancos().isEmpty()) {
								idBancoGenerado = String.valueOf(filtro.getIdsBancos().get(0));
							} else {
								idBancoGenerado = "848";
							}

							// Se determina tipo de archivo (unico o individual)
							idTipoArchivoGenerado = (filtro.getArchivoUnico()) ? "1" : "2";

							// Se registra en tabla relacion folio-archivo(s)
							this.domiciliacionesService.registrarArchivoDomiBitacora(new OperacionDomiBitacoraFilter(
									request.getFolio(), idArchivoGenerado, Long.parseLong(request.getIdTipoContrato()),
									Long.parseLong((archivoGenerado.getGenerated()) ? "1" : "0"),
									Long.parseLong(idTipoArchivoGenerado), Long.parseLong(idBancoGenerado),
									Long.parseLong(String.valueOf(filtro.getIdTipoArchivo())),
									Long.parseLong(idTipoCuentaGenerado),
									(archivoGenerado.getNombreArchivo() == null
											|| archivoGenerado.getNombreArchivo().length() == 0) ? "SIN NOMBRE"
													: archivoGenerado.getNombreArchivo(),
									(archivoGenerado.getErrors() != null && !archivoGenerado.getErrors().isEmpty())
											? archivoGenerado.getErrors().get(0)
											: "",
									pathFile, (archivoGenerado.getGenerated()) ? 1 : 0, request.getUsuario()));

							// Se agrega resultado
							valores.add(new ArchivoDomiVO(null, null, null, archivoGenerado.getNombreArchivo(),
									request.getUsuario(), String.valueOf(idArchivoGenerado),
									archivoGenerado.getGenerated(),
									(archivoGenerado.getErrors() != null && !archivoGenerado.getErrors().isEmpty())
											? archivoGenerado.getErrors().get(0)
											: "",
									pathFile));

						}

					} catch (Exception e) {
						archivoGenerado.setGenerated(Boolean.FALSE);
						error = e.getMessage().split(":");
						archivoGenerado.getErrors().add(error[0]);
					}

				}
				
				// Se respode a BUS si terminó la ejecución (en caso de aplicar)
				resouestaFinalizacionArch = this.domiciliacionesService.responderFinalizacionArchivosDomi(request.getFolio());
				
				if (!resouestaFinalizacionArch) {
					response = new GeneracionArchivosDomiBeanResponse( CtrlResponseWSEnum.WS_ERROR.getCodRet()
																	 , CtrlResponseWSEnum.WS_ERROR.getMsgRet(),
																	  "Errro al responder la finalización de archivos a BUS");
				}

				// Formalización del response
				if (valores != null && !valores.isEmpty()) {
					response = new GeneracionArchivosDomiBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
							CtrlResponseWSEnum.WS_OK.getMsgRet(),
							"Generacion de archivos correcta (archivos generados explicitos en lista)", valores,
							request.getFolio());
				} else {
					response = new GeneracionArchivosDomiBeanResponse(CtrlResponseWSEnum.WS_NO_RECORD.getCodRet(),
							CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet(), "Sin archivos generados");
				}

			} else {
				response = new GeneracionArchivosDomiBeanResponse(CtrlResponseWSEnum.WS_NO_RECORD.getCodRet(),
						CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet(),
						"Sin resultados para generar archivos de domiciliacion con base en parametros de entrada");
			}

			// Mandar a log resultados para posible rastreabilidad de operaciones
			LOGGER.info("*********** ARCHIVOS DOMI GENERADOS: " + new Gson().toJson(resultados));

			return response;
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN    :" + DomiciliacionesSoapServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO       :" + "generarArchivosDomiLotes");
			LOGGER.error("ERROR MENSAJE      :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCIÓN  :", e);
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", generarArchivosDomiLotes");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {
			dummy = null;
			response = null;
			valores = null;
			val = null;
			filters = null;
			filtros = null;
			fechaHoy = null;
			filter = null;
			message = null;
			archivoGenerado = null;
			respGeneracion = null;
			error = null;
			resultados = null;
			idArchivoGenerado = null;
			pathFile = null;
			idTipoCuentaGenerado = null;
			idBancoGenerado = null;
			idTipoArchivoGenerado = null;
			nombreContrato = null;
			generaciones = null;
			resouestaFinalizacionArch = null;
		}
	}

	/**
	 * Metodo que retorna renglones de una tabla dinamica Ejemplo de estructura a
	 * crear:
	 * <tr>
	 * <td>1</td>
	 * <td>archivo1.dom</td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>archivo2.dom</td>
	 * </tr>
	 */
	@Override
	public TablaDinamicaArchDomiBeanResponse generarTablaDinamicaArchDomi(TablaDinamicaArchDomiBeanRequest request) {
		List<ArchivosDomiciliacionBitacoraVO> lArchivos = null;
		TablaDinamicaArchDomiFilter filter = null;
		ValidacionesVO validacion = null;
		String tablaDinamica = null;
		Integer contador = null;
		try {
			// Request vacio.
			if (request == null) {
				return new TablaDinamicaArchDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), "Request, es necesario para el servicio.", null);
			}

			// Validamos los parametros de entrada no sean vacios
			validacion = request.validarRequest();
			if (!validacion.isEstatus()) {
				return new TablaDinamicaArchDomiBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), validacion.getMensaje(), null);
			} else {
				// Parametros de entrada correcto

				// Creamos el filter que se mandara a Business
				filter = new TablaDinamicaArchDomiFilter(request.getFolio());

				// Mandamos llamar el metodo que nos traera los archivos de acuerdo al folio
				lArchivos = domiciliacionesService.generarTablaDinamicaArchDomi(filter);

				// Verificamos que la lista no venga vacia.
				if (lArchivos == null || lArchivos.isEmpty()) {
					return new TablaDinamicaArchDomiBeanResponse(CtrlResponseWSEnum.WS_NO_RECORD.getCodRet(),
							CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet(),
							"Sin resultados en archivos domiciliacion bitacora con parametros ingresados.", null);
				} else {
					// Inicializamos variables
					contador = 1;
					tablaDinamica = "";

					// Recorremos la lista para obtnere el nombre del archivo
					for (ArchivosDomiciliacionBitacoraVO archivo : lArchivos) {
						// Formamos la estructura
						tablaDinamica += "<tr><td>" + contador + "</td><td>" + archivo.getNombreArchivo()
								+ "</td></tr>";
						contador++;
					}

					// Retornamos el mensaje
					return new TablaDinamicaArchDomiBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
							CtrlResponseWSEnum.WS_OK.getMsgRet(), "Consulta exitosa.", tablaDinamica);
				}
			}

		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN    :" + DomiciliacionesSoapServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO       :" + "generarTablaDinamicaArchDomi");
			LOGGER.error("ERROR MENSAJE      :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCIÓN  :", e);
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", generarTablaDinamicaArchDomi");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {
			lArchivos = null;
			filter = null;
			validacion = null;
			tablaDinamica = null;
			contador = null;
		}
	}

	/**
	 * Metodo que actualiza el valor del procesamiento de domiciliacion valor 1 =
	 * PROCESAMIENTO ORDINARIO DE ARCHIVOS DE DOMICILIACION (MANEJO DE UCA POR BUS)
	 * valor 2 = PROCESAMIENTO ESPECIAL DE ARCHIVOS DE DOMICILIACION (MANEJO DE UCA
	 * POR JAVA)
	 * 
	 * @param request
	 *            | parametros de entrada
	 */
	@Override
	public TipoProcesamientoDomisBeanResponse actValorProcesamientoDomi(TipoProcesamientoDomisBeanRequest request) {
		ValidacionesVO validacion = null;
		TipoProcesamientoDomisFilter filter = null;
		Integer valor = null;
		try {
			// Request vacio.
			if (request == null) {
				return new TipoProcesamientoDomisBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), "Request, es necesario para el servicio.", null);
			}

			// Validamos los parametros de entrada.
			validacion = request.validarRequest();
			if (!validacion.isEstatus()) {
				return new TipoProcesamientoDomisBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), validacion.getMensaje(), null);
			} else {
				// Validacion correcta de los parametros de entrada.

				// Formamos filter para mandar a Business.
				filter = new TipoProcesamientoDomisFilter(request.getValor(),
						(request.getUsuario() != null && !request.getUsuario().isEmpty()) ? request.getUsuario()
								: null);

				// Mandamos actualizar.
				valor = domiciliacionesService.actValorProcesamientoDomi(filter);

				if (valor == 1) {
					// Retornamos el mensaje exitoso
					return new TipoProcesamientoDomisBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
							CtrlResponseWSEnum.WS_OK.getMsgRet(),
							"El valor se a actualizado a " + request.getValor() + " correctamente.",
							(Integer.parseInt(request.getValor()) == 1)
									? "PROCESAMIENTO ORDINARIO DE ARCHIVOS DE DOMICILIACION (MANEJO DE UCA POR BUS)"
									: "PROCESAMIENTO ESPECIAL DE ARCHIVOS DE DOMICILIACION (MANEJO DE UCA POR JAVA)");
				} else {
					// Mensaje de error
					return new TipoProcesamientoDomisBeanResponse(CtrlResponseWSEnum.WS_BD_ERROR.getCodRet(),
							CtrlResponseWSEnum.WS_BD_ERROR.getMsgRet(),
							"Hubo un error al tratar de actualizar el valor.", null);
				}
			}
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN    :" + DomiciliacionesSoapServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO       :" + "actValorProcesamientoDomi");
			LOGGER.error("ERROR MENSAJE      :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCIÓN  :", e);
			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", actValorProcesamientoDomi");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {
			validacion = null;
			filter = null;
			valor = null;
		}
	}

	/**
	 * Metodo que obtiene los registros que contiene cada archivo de Domiciliacion.
	 * Consume Business.
	 */
	@Override
	public RegistrosArchivoBeanResponse consultarRegistrosArchivo(String folio) {
		TablaDinamicaArchDomiFilter filter = null;
		List<RegistrosArchivoVO> lArchivos = null;
		RegistrosArchivoBeanResponse response = null;

		try {

			// Validamos los parametros de entrada no sean vacios
			if (folio == null || folio.isEmpty()) {
				return new RegistrosArchivoBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
						CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(),
						"El folio es requerido como parametro de entrada.", null);
			} else {
				if (!UtilValidate.validaFormatoIntegerF4(folio, 20)) {
					return new RegistrosArchivoBeanResponse(CtrlResponseWSEnum.WS_INPUT_ERROR.getCodRet(),
							CtrlResponseWSEnum.WS_INPUT_ERROR.getMsgRet(), "Validar formato de folio, LONG MAX 20");
				}
			}

			// Armamos el objeto para mandarlo a Business
			filter = new TablaDinamicaArchDomiFilter(folio);
			lArchivos = domiciliacionesService.consultarRegistrosArchivo(filter);

			// Validamos que la lista no se encuntre vacia.
			if (lArchivos.isEmpty() || lArchivos == null) {
				return new RegistrosArchivoBeanResponse(CtrlResponseWSEnum.WS_NO_RECORD.getCodRet(),
						CtrlResponseWSEnum.WS_NO_RECORD.getMsgRet(),
						"No se encontraron registros con el folio ingresado.");

			} else {

				// Retornamos los registros
				response = new RegistrosArchivoBeanResponse(CtrlResponseWSEnum.WS_OK.getCodRet(),
						CtrlResponseWSEnum.WS_OK.getMsgRet(), "Consulta exitosa.", lArchivos);

				response.setInfoReporte(new Gson().toJson(response));

				return response;
			}

		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN    :" + DomiciliacionesSoapServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO       :" + "consultarRegistrosArchivo");
			LOGGER.error("ERROR MENSAJE      :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCIÓN  :", e);

			FaultBeanServiceInfo missing = new FaultBeanServiceInfo();
			missing.setFaultstring(CtrlResponseWSEnum.WS_ERROR.getMsgRet() + ", consultarRegistrosArchivo");
			throw new MitWebServiceException(e.getMessage(), missing);
		} finally {
			// Liberacion de memoria.
			filter = null;
			lArchivos = null;
			response = null;
		}

	}
}
