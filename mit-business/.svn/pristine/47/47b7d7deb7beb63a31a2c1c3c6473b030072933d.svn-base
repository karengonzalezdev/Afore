package mx.profuturo.nci.business.service.impl;

import static mx.profuturo.nci.business.util.Constantes.BANAMEX_CONSECUTIVE_PADDING_POSITIONS;
import static mx.profuturo.nci.business.util.Constantes.BANCOMER_CONSECUTIVE_PADDING_POSITIONS;
import static mx.profuturo.nci.business.util.Constantes.DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO;
import static mx.profuturo.nci.business.util.Constantes.DOMI_FILES_DATE_FORMAT_BANAMEX;
import static mx.profuturo.nci.business.util.Constantes.DOMI_FILES_DATE_FORMAT_BANCOMER;
import static mx.profuturo.nci.business.util.Constantes.FILE_NAME_EXT_DOMICILIACIONES_BANAMEX;
import static mx.profuturo.nci.business.util.Constantes.FILE_NAME_EXT_DOMICILIACIONES_BANCOMER;
import static mx.profuturo.nci.business.util.Constantes.FILE_NAME_OFFSET_DOMICILIACIONES_TRASPASO;
import static mx.profuturo.nci.business.util.Constantes.FILE_NAME_PART_DOMICILIACIONES_BANAMEX;
import static mx.profuturo.nci.business.util.Constantes.FILE_NAME_PART_DOMICILIACIONES_BANCOMER;
import static mx.profuturo.nci.business.util.Constantes.ID_BANAMEX_DOMICILIACIONES;
import static mx.profuturo.nci.business.util.Constantes.ID_BANCOMER_DOMICILIACIONES;
import static mx.profuturo.nci.business.util.Constantes.ID_CAT_CATALOGO_MONTO_MAXIMO_ENVIO_BANCO;
import static mx.profuturo.nci.business.util.Constantes.ID_ESTATUS_ARCHIVO_DOMI_CANCELADO;
import static mx.profuturo.nci.business.util.Constantes.ID_ESTATUS_ARCHIVO_DOMI_EN_PROCESO;
import static mx.profuturo.nci.business.util.Constantes.ID_ESTATUS_ARCHIVO_DOMI_GENERADO;
import static mx.profuturo.nci.business.util.Constantes.PATH_ACHIVO_DOMI_BANAMEX;
import static mx.profuturo.nci.business.util.Constantes.PATH_ACHIVO_DOMI_BANCOMER;
import static mx.profuturo.nci.business.util.Constantes.SPECIAL_CHARACTERS_REPLACERS;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_BANAMEX_NCI;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_BANCOMER_NCI;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ESTATUS_CARGO_GENERADO;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ORIGEN_DOMICILIACION_TRASPASO;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_OTROS_BANCOS_DOMI_FILE;

import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.profuturo.nci.business.util.ConsumeWS;
import mx.profuturo.aportaciones.ws.utils.UtilsParseXML;
import mx.profuturo.nci.business.bean.RespGeneracionArchivosDomi;
import mx.profuturo.nci.business.envelop.ClientesEnvelope;
import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.file.generator.bean.BanamexDomiFixLenghtFileRecordBeanPar;
import mx.profuturo.nci.business.file.generator.bean.BanamexDomiFooterRecordBean;
import mx.profuturo.nci.business.file.generator.bean.BanamexDomiHeaderRecordBeanPar;
import mx.profuturo.nci.business.file.generator.bean.BanamexDomiRecordRecordBeanPar;
import mx.profuturo.nci.business.file.generator.bean.BancomerDomiFixLenghtFileRecordBeanPar;
import mx.profuturo.nci.business.file.generator.bean.BancomerDomiFooterRecordBean;
import mx.profuturo.nci.business.file.generator.bean.BancomerDomiHeaderRecordBeanPar;
import mx.profuturo.nci.business.file.generator.bean.BancomerDomiRecordRecordBeanPar;
import mx.profuturo.nci.business.file.generator.bean.DataFileBean;
import mx.profuturo.nci.business.file.generator.service.IFileTransferService;
import mx.profuturo.nci.business.file.generator.service.IFilesGeneratorService;
import mx.profuturo.nci.business.wrapped.ArchivosDomiBitacoraFilter;
import mx.profuturo.nci.business.service.ICatalogosService;
import mx.profuturo.nci.business.service.IDomiciliacionesService;
import mx.profuturo.nci.business.util.Constantes;
import mx.profuturo.nci.business.util.NameSpaceBindingProvider;
import mx.profuturo.nci.business.util.WebSphereNamespace;
import mx.profuturo.nci.business.vo.ArchivosDomiciliacionBitacoraVO;
import mx.profuturo.nci.business.vo.ArchivoDomiVO;
import mx.profuturo.nci.business.vo.ArchivosDomiciliacionVO;
import mx.profuturo.nci.business.vo.ArchivosGeneradosDomiVO;
import mx.profuturo.nci.business.vo.ArchivosGeneradosVO;
import mx.profuturo.nci.business.vo.CatPrioridadesDiversificacionesVO;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.CatalogosDomiVO;
import mx.profuturo.nci.business.vo.CifrasTotalesDomiVO;
import mx.profuturo.nci.business.vo.ConsecutivosDomiVO;
import mx.profuturo.nci.business.vo.CtrlArchivoDomisBean;
import mx.profuturo.nci.business.vo.DetalleArchivoDomiVO;
import mx.profuturo.nci.business.vo.FechaArchivoDomiVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.vo.ParamCatalogoVO;
import mx.profuturo.nci.business.vo.PeticionDomiciliacionVO;
import mx.profuturo.nci.business.vo.ProcesamientoDomiVO;
import mx.profuturo.nci.business.vo.RegistroCifrasDomiVO;
import mx.profuturo.nci.business.vo.RegistrosArchivoVO;
import mx.profuturo.nci.business.vo.RegistrosMaxArchVO;
import mx.profuturo.nci.business.vo.SolicitudVO;
import mx.profuturo.nci.business.vo.TiposCuentaDomiVO;
import mx.profuturo.nci.business.vo.TotalesRegistroDomiVO;
import mx.profuturo.nci.business.wrapped.ArchivoDomiciliacionfilter;
import mx.profuturo.nci.business.wrapped.ArchivoDomisFilter;
import mx.profuturo.nci.business.wrapped.CatalogosDomiFilter;
import mx.profuturo.nci.business.wrapped.CifraDomiFilter;
import mx.profuturo.nci.business.wrapped.CifrasTotalesDomiFilter;
import mx.profuturo.nci.business.wrapped.DomiParameterKeyFilter;
import mx.profuturo.nci.business.wrapped.DomiParameterMapFilter;
import mx.profuturo.nci.business.wrapped.DomiParameterValuesFilter;
import mx.profuturo.nci.business.wrapped.FiltroArchivosDomiciliacionFilter;
import mx.profuturo.nci.business.wrapped.OperacionDomiBitacoraFilter;
import mx.profuturo.nci.business.wrapped.PeticionesDomiFilter;
import mx.profuturo.nci.business.wrapped.SolicitudFilter;
import mx.profuturo.nci.business.wrapped.TablaDinamicaArchDomiFilter;
import mx.profuturo.nci.business.wrapped.TipoProcesamientoDomisFilter;
import mx.profuturo.nci.business.wrapped.TipoRegistrosMaxFilter;
import mx.profuturo.nci.business.wrapped.UltimosArchivosGeneradosFilter;
import mx.profuturo.nci.persistence.ArchivoDomiPersistence;
import mx.profuturo.nci.persistence.CatalogoPersistence;
import mx.profuturo.nci.persistence.DetalleArchivoDomiPersistence;
import mx.profuturo.nci.persistence.DomiciliacionesPersistence;
import mx.profuturo.nci.persistence.DomisBitacoraPersistence;
import mx.profuturo.nci.persistence.SolicitudPersistence;
import java.util.Collections;
import java.util.Comparator;

@Service("domiciliacionesService")
public class DomiciliacionesServiceImpl implements IDomiciliacionesService{
	private static final Logger LOGGER = LoggerFactory.getLogger(DomiciliacionesServiceImpl.class);
	
	@Autowired
	DomisBitacoraPersistence domisBitacoraPersistence;
	
	@Autowired
	DomiciliacionesPersistence domiciliacionesPersistence;
	
	@Autowired 
	ArchivoDomiPersistence archivoDomiPersistence;
	
	@Autowired 
	SolicitudPersistence solicitudPersistence;
	
	@Autowired 
	ICatalogosService catalogosService;
	
	@Autowired 
	CatalogoPersistence catalogoPersistence;
	
	@Autowired 
	DetalleArchivoDomiPersistence detalleArchivoDomiPersistence;
	
	@Autowired 
	IFilesGeneratorService fileGeneratorService;
	
	@Autowired 
	IFileTransferService fileTransferService;
	
	public ProcesamientoDomiVO consultarProcesamientoDomi ()  throws Exception {
		try {
			return domiciliacionesPersistence.consultarProcesamientoDomi();
		} catch (Exception ex) {
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "consultarProcesamientoDomiVO" );
			LOGGER.error( "ERROR MENSAJE       :" + ex.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , ex );
			throw ex;
		}
	}
	
	public Integer registrarArchivoDomiBitacora (OperacionDomiBitacoraFilter filter) throws Exception {
		try {
			return this.domisBitacoraPersistence.registrarArchivoDomiBitacora(filter);
		} catch( Exception e ) { 
			LOGGER.error( "ERROR UBICACIÓN    :" + IDomiciliacionesService.class.getCanonicalName());
			LOGGER.error( "ERROR METODO       :" + "regsitrarrchivoDomiBitacora" );
			LOGGER.error( "ERROR MENSAJE      :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION  :" , e );
			throw e;
		}
	}
	
	public List<ArchivoDomiVO> consultarArchivosDomiBitacora (ArchivosDomiBitacoraFilter filter) throws Exception {
		try {
			return this.domisBitacoraPersistence.consultarArchivosDomiBitacora(filter);
		} catch( Exception e ) { 
			LOGGER.error( "ERROR UBICACIÓN    :" + IDomiciliacionesService.class.getCanonicalName());
			LOGGER.error( "ERROR METODO       :" + "consultarArchivosDomiBitacora" );
			LOGGER.error( "ERROR MENSAJE      :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION  :" , e );
			throw e;
		}
	}

	public List<CatalogosDomiVO> getCatalogosControlDomi(CatalogosDomiFilter filter) throws Exception {
		try {
			if (filter.getTipoCatalogo().equals("1")) {
				return this.domiciliacionesPersistence.getCatalogoOrigenAportacionDomi();
			} else if (filter.getTipoCatalogo().equals("2")) {
				return this.domiciliacionesPersistence.getCatalogoOrigenBancosCuentasIncluir();
			} else if (filter.getTipoCatalogo().equals("3")) {
				return this.domiciliacionesPersistence.getCatalogoOrigenBancosArchivosGenerar();
			} else if (filter.getTipoCatalogo().equals("4")) {
				return this.domiciliacionesPersistence.getCatalogoTiposCuenta();
			} else if (filter.getTipoCatalogo().equals("5")) {
				return this.domiciliacionesPersistence.getCatalogoContratoDomi();	
			} else {
				return null;
			}
			
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACIÓN    :" + IDomiciliacionesService.class.getCanonicalName());
			LOGGER.error( "ERROR METODO       :" + "getCatalogosControlDomi" );
			LOGGER.error( "ERROR MENSAJE      :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION  :" , e );
			throw e;
		}
	}

	public List<CifrasTotalesDomiVO> getCifrasTotales(CifrasTotalesDomiFilter filter) throws Exception {
		try {
			return this.domiciliacionesPersistence.getCifrasTotales(filter);
		} catch( Exception e ) {
			LOGGER.error( "ERROR UBICACIÓN    :" + IDomiciliacionesService.class.getCanonicalName());
			LOGGER.error( "ERROR METODO       :" + "getCifrasTotales" );
			LOGGER.error( "ERROR MENSAJE      :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION  :" , e );
			throw e;
		}
	}
	
	public List<CifrasTotalesDomiVO> getCifrasTotalesOtrosBancos(CifrasTotalesDomiFilter filter) throws Exception {
		try {
			return this.domiciliacionesPersistence.getCifrasTotalesOtrosBancos(filter);
		} catch( Exception e ) {
			LOGGER.error( "ERROR UBICACIÓN    :" + IDomiciliacionesService.class.getCanonicalName());
			LOGGER.error( "ERROR METODO       :" + "getCifrasTotales" );
			LOGGER.error( "ERROR MENSAJE      :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION  :" , e );
			throw e;
		}
	}

	public List<TotalesRegistroDomiVO> getImportesTotalesCifra(CifraDomiFilter filter) throws Exception {
		try {
			return this.domiciliacionesPersistence.getImportesTotalesCifra(filter);
		} catch( Exception e ) {
			LOGGER.error( "ERROR UBICACIÓN    :" + IDomiciliacionesService.class.getCanonicalName());
			LOGGER.error( "ERROR METODO       :" + "getImportesTotalesCifra" );
			LOGGER.error( "ERROR MENSAJE      :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION  :" , e );
			throw e;
		}
	}
	
	public List<TotalesRegistroDomiVO> getImportesTotalesCifraOtrosBancos(CifraDomiFilter filter) throws Exception {
		try {
			return this.domiciliacionesPersistence.getImportesTotalesCifraOtrosBancos(filter);
		} catch( Exception e ) {
			LOGGER.error( "ERROR UBICACIÓN    :" + IDomiciliacionesService.class.getCanonicalName());
			LOGGER.error( "ERROR METODO       :" + "getImportesTotalesCifraOtrosBancos" );
			LOGGER.error( "ERROR MENSAJE      :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION  :" , e );
			throw e;
		}
	}

	public RegistroCifrasDomiVO getCifraDomi(CifraDomiFilter filter) throws Exception {
		try {
			return this.domiciliacionesPersistence.getCifraDomi(filter);
		} catch( Exception e ) {
			LOGGER.error( "ERROR UBICACIÓN    :" + IDomiciliacionesService.class.getCanonicalName());
			LOGGER.error( "ERROR METODO       :" + "getCifraDomi" );
			LOGGER.error( "ERROR MENSAJE      :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCION  :" , e );
			throw e;
		}
	}
	
	public List<ArchivosGeneradosDomiVO> ultimosArchivosGenerados(UltimosArchivosGeneradosFilter filter) throws Exception {
		try {
			// obtenemos la consulta
			return this.domiciliacionesPersistence.ultimosArchivosGenerados(filter);
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "ultimosArchivosGenerados");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			throw e;
		}
	}

	public CtrlArchivoDomisBean generarIdArchivoDomis(ArchivoDomisFilter filter) { // 1
		// TODO Auto-generated method stub
		return null;
	}

	public CtrlArchivoDomisBean grabarArchivoDomis(ArchivoDomisFilter filter) { // 3
		// TODO Auto-generated method stub
		return null;
	}

	public CtrlArchivoDomisBean consultarDomis(ArchivoDomisFilter filter) { // 2
		// TODO Auto-generated method stub
		return null;
	}

	public DomiParameterMapFilter getDomiParameterMapFilter(PeticionesDomiFilter petDomiFilter) throws Exception {
		DomiParameterMapFilter map		= null; 
		DomiParameterKeyFilter key		= null;
		Short i     					= 0;
		DomiParameterValuesFilter val 	= null;
		
		try {
			map = new DomiParameterMapFilter();
			i = 0;
			map.setFechaFin( new SimpleDateFormat("dd/MM/yyyy").parse(petDomiFilter.getFechaFin()) );
			map.setFechaInicio( new SimpleDateFormat("dd/MM/yyyy").parse(petDomiFilter.getFechaInicio()) );
			map.setIdOrigenDomiciliacion(Short.valueOf(petDomiFilter.getOrigenAportacion()));
			map.setUsuario(petDomiFilter.getUsuario());
			
			// Regitros de Grid
			for (PeticionDomiciliacionVO reg : petDomiFilter.getPeticiones()) {
				key = new DomiParameterKeyFilter();
				val = new DomiParameterValuesFilter();
				
				if (reg.getCveBancoIncluir() != null &&  !reg.getCveBancoIncluir().isEmpty()) {
					key.setIdTipoArchivo(Short.valueOf(reg.getArchivoGenenerar()));
					key.setKey(i);
					
					val.setUnSoloArchivo(reg.isArchivoUnico());
					val.getIdsBancos().add(Short.valueOf(reg.getCveBancoIncluir()));
					
					for(TiposCuentaDomiVO petDomi : reg.getTiposCuenta()) {
						val.getIdsTiposCuenta().add(Short.valueOf(petDomi.getIdTipoCta()));
					}
					
					i++;
					map.getValues().put(key, val);
				}
			}
			
			return map;
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "getDomiParameterMapFilter");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			throw e;
		} finally {
			map = null;
			key = null;
			i = null;
			val = null;
		}
		
	}

	public List<SolicitudFilter> getCustomFilter (DomiParameterMapFilter filters) throws Exception {
		DomiParameterValuesFilter values 		= null;
		List<SolicitudFilter> solFilters		= null;
		SolicitudFilter solFilter 				= null;
		
		try {
			solFilters = new ArrayList<SolicitudFilter>();
			
			for(DomiParameterKeyFilter key:filters.getValues().keySet()){
				values = filters.getValues().get(key);
				
				if( values.getUnSoloArchivo() ) { // FILTROS PARA GENERAR UN SOLO ARCHIVO POR BANCO
					solFilter = this.genSolFilterBasicData(filters,key);
					this.addIdsBancosTo(solFilter,values.getIdsBancos());
					solFilter.getIdsTiposCuenta().addAll(values.getIdsTiposCuenta());
					solFilter.setArchivoUnico(values.getUnSoloArchivo());
					solFilters.add(solFilter);
				} else{ //FILTROS PARA REGISTROS NORMALES (POR TIPO ARCHIVO Y TIPO CUENTA
					for(Short idTipoCuenta:values.getIdsTiposCuenta()){
						solFilter = this.genSolFilterBasicData(filters,key);
						this.addIdsBancosTo(solFilter,values.getIdsBancos());
						solFilter.getIdsTiposCuenta().add(idTipoCuenta);
						solFilter.setArchivoUnico(values.getUnSoloArchivo());
						solFilters.add(solFilter);
					}
				}
			}
			
			return solFilters;
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "getCustomFilter");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			throw e;
		} finally {
			values = null;
			solFilters	= null;
			solFilter = null;
		}
		
	}
	
	public Integer getConsecutivoArchivoDomiLotes (String tipoArchivo, String idAportacionOrigen) throws Exception {
		ConsecutivosDomiVO consecutivo = null;
		
		try {
			
			if (tipoArchivo.equals("459")) { // Banamex
				
				if (idAportacionOrigen.equals("0")) { // Domiciliaciones ordinarias
					consecutivo = this.archivoDomiPersistence.consultarConsecutivoBanamexOrdinario();
				} else { // Domiciliaciones traspasos
					consecutivo = this.archivoDomiPersistence.consultarConsecutivoBanamexTraspasos();
				}
			} else if (tipoArchivo.equals("460")) { // BBVA 
				
				if (idAportacionOrigen.equals("0")) { // Domiciliaciones ordinarias
					consecutivo = this.archivoDomiPersistence.consultarConsecutivoBancomerOrdinario();
				} else { // Domiciliaciones traspaso
					consecutivo = this.archivoDomiPersistence.consultarConsecutivoBancomerTraspasos();
				}
				
				
			}
			
			return Integer.parseInt(consecutivo.getValor());
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "getConsecutivoArchivoDomiLotes");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			throw e;
		} finally {
			consecutivo = null;
		}
	}
	
	/*public Integer getConsecutivoArchivoDomiLotes (String tipoArchivo, String bancoCuentas) throws Exception {
		ConsecutivosDomiVO consecutivo = null;
		
		try {
			
			if (tipoArchivo.equals("459")) { // Banamex
				
				if (bancoCuentas.equals("459")) { // Cuentas Banamex
					consecutivo = this.archivoDomiPersistence.consultarConsecutivoBanamexOrdinario();
				} else { // Cuentas diferentes a Banamex 
					consecutivo = this.archivoDomiPersistence.consultarConsecutivoBanamexTraspasos();
				}
			} else if (tipoArchivo.equals("460")) { // BBVA
				
				if (bancoCuentas.equals("460")) { // Cuentas BBVA
					consecutivo = this.archivoDomiPersistence.consultarConsecutivoBancomerOrdinario();
				} else { // Cuentas diferentes a BBVA
					consecutivo = this.archivoDomiPersistence.consultarConsecutivoBancomerTraspasos();
				}
				
				
			}
			
			return Integer.parseInt(consecutivo.getValor());
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "getConsecutivoArchivoDomiLotes");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			throw e;
		} finally {
			consecutivo = null;
		}
	} */
	
	public Integer getConsecutivoArchivoDomi (ArchivoDomiciliacionfilter filter) throws Exception {
		try {
			return this.archivoDomiPersistence.selectConsecutivoHoy(filter);
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "getConsecutivoArchivoDomi");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			throw e;
		}
	}
	
	public String getNombreArchivoDomiLotes (Integer consecutivo, String tipoArchivo) throws Exception {
		FechaArchivoDomiVO fecha = null;
		String nombreArchivo = null;
		
		try {
			
			if (tipoArchivo.equals("459")) { // Banamex
				fecha = this.archivoDomiPersistence.consultarFechaFormatoDomiBanamex();
				nombreArchivo = "DCB" + fecha.getFecha() + "000106886951" + this.getNumeroArchivoLotes (consecutivo, tipoArchivo) + ".DOM";
				
			} else if (tipoArchivo.equals("460")) { // Bancomer
				fecha = this.archivoDomiPersistence.consultarFechaFormatoDomiBancomer();
				nombreArchivo = "BANCOMER" + "_" + fecha.getFecha() + "_" + this.getNumeroArchivoLotes (consecutivo, tipoArchivo) + ".TXT";
				
			}
			
			return nombreArchivo;
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "getNombreArchivoDomiLotes");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			throw e;
		} finally {
			fecha = null;
			nombreArchivo = null;
		}
	}
	
	public String getNumeroArchivoLotes (Integer consecutivo, String tipoArchivo) throws Exception {
		String numeroArchivo = null;
		
		try {
			
			if (tipoArchivo.equals("459")) {
				if (consecutivo >= 0 && consecutivo <= 9) {
					numeroArchivo = "0" + String.valueOf(consecutivo);
				} else {
					numeroArchivo = String.valueOf(consecutivo);
				}
			} else if (tipoArchivo.equals("460")) {
				if (consecutivo >= 0 && consecutivo <= 9) {
					numeroArchivo = "00" + String.valueOf(consecutivo);
				} else if (consecutivo >= 10 && consecutivo <= 99) {
					numeroArchivo = "0" + String.valueOf(consecutivo);
				} else {
					numeroArchivo = String.valueOf(consecutivo);
				}
			}

			return numeroArchivo;
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "getNumeroArchivoLotes");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			throw e;
		} finally {
			numeroArchivo = null;
		}
	}
	
	public String getNombreArchivoDomi (Short idOrigenAportacion, Short type, Integer consecutivo, Date fecha) throws Exception {
		StringBuffer fileName 	= null;
		SimpleDateFormat sdf 	= null;
		
		try {
			fileName = new StringBuffer();
			
			if( ID_BANAMEX_NCI.equals(type) ) {
				sdf = new SimpleDateFormat(DOMI_FILES_DATE_FORMAT_BANAMEX);
				
				fileName.append(ID_BANAMEX_DOMICILIACIONES).append(sdf.format(fecha))
						.append(FILE_NAME_PART_DOMICILIACIONES_BANAMEX)
						.append(StringUtils.leftPad( String.valueOf(consecutivo)
												   , BANAMEX_CONSECUTIVE_PADDING_POSITIONS
												   , '0'))
						.append(FILE_NAME_EXT_DOMICILIACIONES_BANAMEX);
			} else if( ID_BANCOMER_NCI.equals(type) ) {
				sdf = new SimpleDateFormat(DOMI_FILES_DATE_FORMAT_BANCOMER);
				
				fileName.append( ID_BANCOMER_DOMICILIACIONES+sdf.format(fecha))
						.append(FILE_NAME_PART_DOMICILIACIONES_BANCOMER	)
						.append(StringUtils.leftPad( String.valueOf(consecutivo)
												   , BANCOMER_CONSECUTIVE_PADDING_POSITIONS
												   , '0'))
						.append(FILE_NAME_EXT_DOMICILIACIONES_BANCOMER);
			}
			
			return fileName.toString();
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "getNombreArchivoDomi");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			throw e;
		} finally {
			 sdf = null;
			 fileName = null;
		}
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public String generarArchivoDomiF4Mant ( SolicitudFilter solicitudFilter
									       , Date fecha
									       , String archivo
									       , Integer consecutivo
									       , Short origenDomi
									       , Long idArchivoGenerado) throws Exception {
		
		List<SolicitudVO> domis										= null;
		List<CatPrioridadesDiversificacionesVO> catDivPrioridades 	= null; 
		ParamCatalogoVO paramCatalogoVO 							= null; 
		List<CatalogoVO> ltsImporteMax 								= null;
		String message 												= null;
		
		StringWriter swPar 											= null;
		DataFileBean dfbPar 										= null;
		StringBuffer namePar 										= null;
		
		Long idArchivo 												= null;
		BanamexDomiFixLenghtFileRecordBeanPar bmxRecords 			= null;
		BancomerDomiFixLenghtFileRecordBeanPar bbvaRecords 			= null;
		
		try {
			
			// Regitros a grabar en archivo
			domis = solicitudPersistence.selectDomiRecords(solicitudFilter);
			catDivPrioridades = archivoDomiPersistence.selectCatPrioridadDiversificaciones();
			paramCatalogoVO = new ParamCatalogoVO();
			paramCatalogoVO.setIdCatalogo(new Short(ID_CAT_CATALOGO_MONTO_MAXIMO_ENVIO_BANCO));
			ltsImporteMax = new ArrayList<CatalogoVO>();
			ltsImporteMax = catalogosService.consultarLista(paramCatalogoVO);
			
			if(!ltsImporteMax.isEmpty()) {
				DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO = new BigDecimal(ltsImporteMax.get(0).getValor());
			}
				
			if(domis.isEmpty()) {
				message = this.getFileGeneratedData(archivo, solicitudFilter)+" - "+"No se encontraron registros para los parámetros especificados";
				 return message;
			}
			
			// Almacenamiento de archivo
			swPar  = null;
			dfbPar = new DataFileBean();
			namePar = new StringBuffer();
			idArchivo = this.insertArchivoGeneradoParF4(solicitudFilter,archivo,origenDomi);
			idArchivoGenerado  = idArchivo;
			
			// Grabar el archivo (Con try catch interno  propio para tratar su exception)
			try {
				if(ID_BANAMEX_NCI.equals(solicitudFilter.getIdTipoArchivo())){									  
					bmxRecords =  this.mapVOToBanamexRecordBeanF4(domis, consecutivo, fecha, idArchivo);
					swPar = fileGeneratorService.generateFileF4(bmxRecords);
					LOGGER.info(swPar.getBuffer().toString());
					namePar.append(PATH_ACHIVO_DOMI_BANAMEX);
				} else if(ID_BANCOMER_NCI.equals(solicitudFilter.getIdTipoArchivo())){
					bbvaRecords = this.mapVOToBancomerRecordBeanF4(domis, consecutivo, fecha, idArchivo);
					swPar = fileGeneratorService.generateFileF4(bbvaRecords);
					LOGGER.info( swPar.getBuffer().toString() );
					namePar.append(PATH_ACHIVO_DOMI_BANCOMER);
				}
				
				namePar.append(archivo);
				if( swPar!=null ) {
					dfbPar.setFile(new File(namePar.toString()));
					dfbPar.setStream(swPar.getBuffer().toString().getBytes());
					boolean enviadoPar = fileTransferService.sendFile(dfbPar);
					if(enviadoPar) {
						this.updateEstatusSolicitud(domis,solicitudFilter.getUsuarioCreacion());
						this.updateEstatusArchivoGeneradoPar(idArchivo, ID_ESTATUS_ARCHIVO_DOMI_GENERADO,solicitudFilter.getUsuarioCreacion());
					} else {
						return "El archivo no pudo escribirse en la ruta";
					}													
				}
			} catch (Exception e) {
				if(idArchivo > 0) {
					this.updateEstatusArchivoGeneradoPar(idArchivo, ID_ESTATUS_ARCHIVO_DOMI_CANCELADO,solicitudFilter.getUsuarioCreacion());
					return "Error general al generar archivo: " + e.getMessage();
				}
					
			}
			
			
			return String.valueOf(idArchivoGenerado);
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "generarArchivoDomiF4Mant");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			
			return e.getMessage();
		} finally {
			domis				= null;
			catDivPrioridades 	= null; 
			paramCatalogoVO 	= null; 
			ltsImporteMax 		= null;
			message 			= null;
			swPar 				= null;
			dfbPar 				= null;
			namePar 			= null;
			idArchivo 			= null;
			bmxRecords 			= null;
			bbvaRecords 		= null;
		}
	}
	
	
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public List <RespGeneracionArchivosDomi> generarArchivoDomiF4MantLotes ( SolicitudFilter solicitudFilter
										        	   					   , Date fecha
										        	   					   , Short origenDomi
										        	   					   , Long idArchivoGenerado
										        	   					   , String contrato
										        	   					   , ArchivoDomiciliacionfilter filter) throws Exception {
		
		List<SolicitudVO> domis													= null;
		List<CatPrioridadesDiversificacionesVO> catDivPrioridades 				= null; 
		ParamCatalogoVO paramCatalogoVO 										= null; 
		List<CatalogoVO> ltsImporteMax 											= null;
		String message 															= null;
		
		StringWriter swPar 														= null;
		DataFileBean dfbPar 													= null;
		StringBuffer namePar 													= null;
		
		Long idArchivo 															= null;
		BanamexDomiFixLenghtFileRecordBeanPar bmxRecords 						= null;
		BancomerDomiFixLenghtFileRecordBeanPar bbvaRecords 						= null;
		List <BancomerDomiFixLenghtFileRecordBeanPar> bbvaRecordsLotes 			= null;
		List <RespGeneracionArchivosDomi> arrArchivosGenerados					= null;
		Integer tamMax															= null;
		String archivo															= null;
		Integer consecutivo														= null;
		String idBancoGenerado													= null;
		Boolean actualizarSolicitudes											= null;
		
		try {
			archivo = "";
			arrArchivosGenerados = new ArrayList <RespGeneracionArchivosDomi> ();
			
			// Regitros a grabar en archivo (  Registros originales (sin parcialidades) )
			//domis = solicitudPersistence.selectDomiRecords(solicitudFilter);
			domis = solicitudPersistence.selectDomiRecordsParcialidades(solicitudFilter);

			catDivPrioridades = archivoDomiPersistence.selectCatPrioridadDiversificaciones();
			paramCatalogoVO = new ParamCatalogoVO();
			paramCatalogoVO.setIdCatalogo( new Short(ID_CAT_CATALOGO_MONTO_MAXIMO_ENVIO_BANCO) );
			ltsImporteMax = new ArrayList<CatalogoVO>();
			ltsImporteMax = catalogosService.consultarLista(paramCatalogoVO);
			
			if( !ltsImporteMax.isEmpty() ) {
				DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO = new BigDecimal(ltsImporteMax.get(0).getValor());
			}
			
			// Registro vacío
			if(domis.isEmpty()) {
				message = this.getFileGeneratedData(archivo, solicitudFilter) + " - "+"No se encontraron registros para los parámetros especificados";
				arrArchivosGenerados.add(new RespGeneracionArchivosDomi (message, "") );
				return arrArchivosGenerados;
			}
			
			// Se determina el banco ("Ctas a incluir")
			if (solicitudFilter.getIdsBancos() != null && !solicitudFilter.getIdsBancos().isEmpty()) {
				idBancoGenerado = String.valueOf(solicitudFilter.getIdsBancos().get(0));
			} else {
				idBancoGenerado = "848";
			}
			
			
			// Obtención de lotes
			if( ID_BANAMEX_NCI.equals( solicitudFilter.getIdTipoArchivo()) ) {
				
				// Consectivo del archivo
				String.valueOf( solicitudFilter.getOrigenAportacion().getId() );
	
				consecutivo = this.getConsecutivoArchivoDomiLotes( String.valueOf(solicitudFilter.getIdTipoArchivo())
															     , String.valueOf(solicitudFilter.getOrigenAportacion().getId()) );
				
				// consecutivo = this.getConsecutivoArchivoDomi(filter);

				/* SE COMENTA REGLA CON CARTA DE MANTENIMIENTO DE DOMIS
				 * if (ID_ORIGEN_DOMICILIACION_TRASPASO.equals(solicitudFilter.getOrigenAportacion().getId())) {
					consecutivo += FILE_NAME_OFFSET_DOMICILIACIONES_TRASPASO;
				} */
				
				// Nombre de archivo
				archivo = this.getNombreArchivoDomiLotes(consecutivo, "459");
				/*archivo = this.getNombreArchivoDomi( solicitudFilter.getOrigenAportacion().getId()
						  						   , solicitudFilter.getIdTipoArchivo()
						  						   , consecutivo
						  						   , fecha);*/
				
				// Almacenamiento de archivo
				swPar  = null;
				dfbPar = new DataFileBean();
				namePar = new StringBuffer();
				idArchivo = this.insertArchivoGeneradoParF4(solicitudFilter,archivo,origenDomi);
				idArchivoGenerado  = idArchivo;
				
				bmxRecords =  this.mapVOToBanamexRecordBeanF4(domis, consecutivo, fecha, idArchivo);
				swPar = fileGeneratorService.generateFileF4(bmxRecords);
				LOGGER.info(swPar.getBuffer().toString());
				namePar.append(PATH_ACHIVO_DOMI_BANAMEX);
				
				namePar.append(archivo);
				if( swPar!=null ) {
					dfbPar.setFile(new File(namePar.toString()));
					dfbPar.setStream(swPar.getBuffer().toString().getBytes());
					boolean enviadoPar = fileTransferService.sendFile(dfbPar);
					if(enviadoPar) {
						this.updateEstatusSolicitud(domis,solicitudFilter.getUsuarioCreacion());
						this.updateEstatusArchivoGeneradoPar(idArchivo, ID_ESTATUS_ARCHIVO_DOMI_GENERADO,solicitudFilter.getUsuarioCreacion());
						arrArchivosGenerados.add(new RespGeneracionArchivosDomi (String.valueOf(idArchivo), archivo) );
					} else {
						arrArchivosGenerados.add(new RespGeneracionArchivosDomi ("El archivo no pudo escribirse en la ruta", "") );
					}													
				}
				
			} else if(ID_BANCOMER_NCI.equals( solicitudFilter.getIdTipoArchivo()) ) {
				
				if ( contrato.equals("PGA-961220PB4") ) {
					
					if (idBancoGenerado.equals("460")) { // Lotes
						
						// Tamaño maximo de cada archivo
						tamMax = this.obtenerTamMaximoArchivo( contrato
														   	 , String.valueOf( solicitudFilter.getIdTipoArchivo() )
														   	 , idBancoGenerado);
						
						bbvaRecordsLotes = this.mapVOToBancomerRecordBeanF4Lotes(domis, consecutivo, fecha, tamMax, contrato);
						actualizarSolicitudes = Boolean.TRUE;
						
						for (BancomerDomiFixLenghtFileRecordBeanPar lote : bbvaRecordsLotes) {
							// Consectivo del archivo
							consecutivo = this.getConsecutivoArchivoDomiLotes( String.valueOf(solicitudFilter.getIdTipoArchivo())
								     										 , String.valueOf(solicitudFilter.getOrigenAportacion().getId()) );
							//consecutivo = this.getConsecutivoArchivoDomi(filter);

							/*if (ID_ORIGEN_DOMICILIACION_TRASPASO.equals(solicitudFilter.getOrigenAportacion().getId())) {
								consecutivo += FILE_NAME_OFFSET_DOMICILIACIONES_TRASPASO;
							} */
							
							// Nombre de archivo
							archivo = this.getNombreArchivoDomiLotes(consecutivo, "460");
							/*archivo = this.getNombreArchivoDomi( solicitudFilter.getOrigenAportacion().getId()
									  						   , solicitudFilter.getIdTipoArchivo()
									  						   , consecutivo
									  						   , fecha);*/
							
							// Almacenamiento de archivo
							lote.getHeader().setConsecutivo(consecutivo);
							lote.getFooter().setConsecutivo(consecutivo);
							swPar  = null;
							dfbPar = new DataFileBean();
							namePar = new StringBuffer();
							idArchivo = this.insertArchivoGeneradoParF4(solicitudFilter,archivo,origenDomi);
							idArchivoGenerado  = idArchivo;
							
							swPar = fileGeneratorService.generateFileF4(lote);
							LOGGER.info( swPar.getBuffer().toString() );
							namePar.append(PATH_ACHIVO_DOMI_BANCOMER);
							
							namePar.append(archivo);
							if( swPar!=null ) {
								dfbPar.setFile(new File(namePar.toString()));
								dfbPar.setStream(swPar.getBuffer().toString().getBytes());
								boolean enviadoPar = fileTransferService.sendFile(dfbPar);
								if(enviadoPar) {
									arrArchivosGenerados.add(new RespGeneracionArchivosDomi (String.valueOf(idArchivo), archivo) );
								} else {
									actualizarSolicitudes = Boolean.FALSE;
									arrArchivosGenerados.add(new RespGeneracionArchivosDomi ("El archivo no pudo escribirse en la ruta", "") );
								}													
							}
							
						}
						
						// Se actuaizan los estatus
						if (actualizarSolicitudes) {
							this.updateEstatusSolicitud(domis,solicitudFilter.getUsuarioCreacion());
							this.updateEstatusArchivoGeneradoPar(idArchivo, ID_ESTATUS_ARCHIVO_DOMI_GENERADO,solicitudFilter.getUsuarioCreacion());
						}
						
						
					} else { // Original (simple)
						
						// Consectivo del archivo
						consecutivo = this.getConsecutivoArchivoDomiLotes( String.valueOf(solicitudFilter.getIdTipoArchivo())
							     										 , String.valueOf(solicitudFilter.getOrigenAportacion().getId()) );
						//consecutivo = this.getConsecutivoArchivoDomi(filter);

						/*if (ID_ORIGEN_DOMICILIACION_TRASPASO.equals(solicitudFilter.getOrigenAportacion().getId())) {
							consecutivo += FILE_NAME_OFFSET_DOMICILIACIONES_TRASPASO;
						} */
						
						// Nombre de archivo
						/*archivo = this.getNombreArchivoDomi( solicitudFilter.getOrigenAportacion().getId()
								  						   , solicitudFilter.getIdTipoArchivo()
								  						   , consecutivo
								  						   , fecha);*/
						archivo = this.getNombreArchivoDomiLotes(consecutivo, "460");
						
						// Almacenamiento de archivo
						swPar  = null;
						dfbPar = new DataFileBean();
						namePar = new StringBuffer();
						idArchivo = this.insertArchivoGeneradoParF4(solicitudFilter,archivo,origenDomi);
						idArchivoGenerado  = idArchivo;
						
						bbvaRecords = this.mapVOToBancomerRecordBeanF4(domis, consecutivo, fecha, idArchivo, contrato);
						swPar = fileGeneratorService.generateFileF4(bbvaRecords);
						LOGGER.info( swPar.getBuffer().toString() );
						namePar.append(PATH_ACHIVO_DOMI_BANCOMER);
						
						namePar.append(archivo);
						if( swPar!=null ) {
							dfbPar.setFile(new File(namePar.toString()));
							dfbPar.setStream(swPar.getBuffer().toString().getBytes());
							boolean enviadoPar = fileTransferService.sendFile(dfbPar);
							if(enviadoPar) {
								this.updateEstatusSolicitud(domis,solicitudFilter.getUsuarioCreacion());
								this.updateEstatusArchivoGeneradoPar(idArchivo, ID_ESTATUS_ARCHIVO_DOMI_GENERADO,solicitudFilter.getUsuarioCreacion());
								arrArchivosGenerados.add(new RespGeneracionArchivosDomi (String.valueOf(idArchivo), archivo) );
							} else {
								arrArchivosGenerados.add(new RespGeneracionArchivosDomi ("El archivo no pudo escribirse en la ruta", "") );
							}													
						}
					}
					
				} else if ( contrato.equals("PGA 961220PB4") ) {
					
					if ( !idBancoGenerado.equals("460") ) { // Lotes
						
						// Tamaño maximo de cada archivo
						tamMax = this.obtenerTamMaximoArchivo( contrato
							   	 						     , String.valueOf( solicitudFilter.getIdTipoArchivo() )
							   	 						     , idBancoGenerado);
						
						bbvaRecordsLotes = this.mapVOToBancomerRecordBeanF4Lotes(domis, consecutivo, fecha, tamMax, contrato);
						actualizarSolicitudes = Boolean.TRUE;
						
						for (BancomerDomiFixLenghtFileRecordBeanPar lote : bbvaRecordsLotes) {
							// Consectivo del archivo
							consecutivo = this.getConsecutivoArchivoDomiLotes( String.valueOf(solicitudFilter.getIdTipoArchivo())
								     										 , String.valueOf(solicitudFilter.getOrigenAportacion().getId()) );
							//consecutivo = this.getConsecutivoArchivoDomi(filter);

							/*if (ID_ORIGEN_DOMICILIACION_TRASPASO.equals(solicitudFilter.getOrigenAportacion().getId())) {
								consecutivo += FILE_NAME_OFFSET_DOMICILIACIONES_TRASPASO;
							}*/
							
							// Nombre de archivo
							/*archivo = this.getNombreArchivoDomi( solicitudFilter.getOrigenAportacion().getId()
									  						   , solicitudFilter.getIdTipoArchivo()
									  						   , consecutivo
									  						   , fecha);*/
							archivo = this.getNombreArchivoDomiLotes(consecutivo, "460");
							
							// Almacenamiento de archivo
							lote.getHeader().setConsecutivo(consecutivo);
							lote.getFooter().setConsecutivo(consecutivo);
							swPar  = null;
							dfbPar = new DataFileBean();
							namePar = new StringBuffer();
							idArchivo = this.insertArchivoGeneradoParF4(solicitudFilter,archivo,origenDomi);
							idArchivoGenerado  = idArchivo;
							
							swPar = fileGeneratorService.generateFileF4(lote);
							LOGGER.info( swPar.getBuffer().toString() );
							namePar.append(PATH_ACHIVO_DOMI_BANCOMER);
							
							namePar.append(archivo);
							if( swPar!=null ) {
								dfbPar.setFile(new File(namePar.toString()));
								dfbPar.setStream(swPar.getBuffer().toString().getBytes());
								boolean enviadoPar = fileTransferService.sendFile(dfbPar);
								if(enviadoPar) {
									arrArchivosGenerados.add(new RespGeneracionArchivosDomi (String.valueOf(idArchivo), archivo) );
								} else {
									actualizarSolicitudes = Boolean.FALSE;
									arrArchivosGenerados.add(new RespGeneracionArchivosDomi ("El archivo no pudo escribirse en la ruta", "") );
								}													
							}
							
						}
						
						// Se actuaizan los estatus
						if (actualizarSolicitudes) {
							this.updateEstatusSolicitud(domis,solicitudFilter.getUsuarioCreacion());
							this.updateEstatusArchivoGeneradoPar(idArchivo, ID_ESTATUS_ARCHIVO_DOMI_GENERADO,solicitudFilter.getUsuarioCreacion());
						}
						
					} else { // Original (simple)
						
						// Consectivo del archivo
						consecutivo = this.getConsecutivoArchivoDomiLotes( String.valueOf(solicitudFilter.getIdTipoArchivo())
							     										, String.valueOf(solicitudFilter.getOrigenAportacion().getId()) );
						//consecutivo = this.getConsecutivoArchivoDomi(filter);

						/*if (ID_ORIGEN_DOMICILIACION_TRASPASO.equals(solicitudFilter.getOrigenAportacion().getId())) {
							consecutivo += FILE_NAME_OFFSET_DOMICILIACIONES_TRASPASO;
						} */
						
						// Nombre de archivo
						/*archivo = this.getNombreArchivoDomi( solicitudFilter.getOrigenAportacion().getId()
								  						   , solicitudFilter.getIdTipoArchivo()
								  						   , consecutivo
								  						   , fecha);*/
						archivo = this.getNombreArchivoDomiLotes(consecutivo, "460");
						
						// Almacenamiento de archivo
						swPar  = null;
						dfbPar = new DataFileBean();
						namePar = new StringBuffer();
						idArchivo = this.insertArchivoGeneradoParF4(solicitudFilter,archivo,origenDomi);
						idArchivoGenerado  = idArchivo;
						
						bbvaRecords = this.mapVOToBancomerRecordBeanF4(domis, consecutivo, fecha, idArchivo, contrato);
						swPar = fileGeneratorService.generateFileF4(bbvaRecords);
						LOGGER.info( swPar.getBuffer().toString() );
						namePar.append(PATH_ACHIVO_DOMI_BANCOMER);
						
						namePar.append(archivo);
						if( swPar!=null ) {
							dfbPar.setFile(new File(namePar.toString()));
							dfbPar.setStream(swPar.getBuffer().toString().getBytes());
							boolean enviadoPar = fileTransferService.sendFile(dfbPar);
							if(enviadoPar) {
								this.updateEstatusSolicitud(domis,solicitudFilter.getUsuarioCreacion());
								this.updateEstatusArchivoGeneradoPar(idArchivo, ID_ESTATUS_ARCHIVO_DOMI_GENERADO,solicitudFilter.getUsuarioCreacion());
								arrArchivosGenerados.add(new RespGeneracionArchivosDomi (String.valueOf(idArchivo), archivo) );
							} else {
								arrArchivosGenerados.add(new RespGeneracionArchivosDomi ("El archivo no pudo escribirse en la ruta", "") );
							}													
						}
					}
					
				} 
			}
			
			
			return arrArchivosGenerados;
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "generarArchivoDomiF4MantLotes");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			
			arrArchivosGenerados.add( new RespGeneracionArchivosDomi (e.getMessage(), "") );
			return arrArchivosGenerados;
		} finally {
			domis							= null;
			catDivPrioridades 				= null; 
			paramCatalogoVO 				= null; 
			ltsImporteMax 					= null;
			message 						= null;
			swPar 							= null;
			dfbPar 							= null;
			namePar 						= null;
			idArchivo 						= null;
			bmxRecords 						= null;
			bbvaRecords 					= null;
			bbvaRecordsLotes 				= null;
			arrArchivosGenerados			= null;
			tamMax							= null;
			archivo							= null;
			consecutivo						= null;
			idBancoGenerado					= null;
			actualizarSolicitudes			= null;
		}
	}
	
	public Integer obtenerTamMaximoArchivo ( String contrato
										   , String bancoArchivo
										   , String bancoCuentas) {
		Integer tamMax = null;
		RegistrosMaxArchVO registrosMax = null;
		TipoRegistrosMaxFilter filter = null;
		try {
			tamMax = 0;
			
			if ( contrato.equals("PGA-961220PB4") ) {
				
				if( bancoCuentas.equals("460") ) {
					// Obtenemos la cantidad maxima de registros para archivo BBVA con cuentas BBVA
					filter = new TipoRegistrosMaxFilter("9533");
					registrosMax = archivoDomiPersistence.obtenerRegistrosMaximosArchivo(filter);
					tamMax = Integer.parseInt(registrosMax.getCantidad());
					//tamMax = 250; // 10000 
				}
			} else if ( contrato.equals("PGA 961220PB4") ) {
				
				if( !bancoCuentas.equals("460") ) {
					// Obtenemos la cantidad maxima de registros para archivo BBVA con cuentas distintas a BBVA
					filter = new TipoRegistrosMaxFilter("9534");
					registrosMax = archivoDomiPersistence.obtenerRegistrosMaximosArchivo(filter);
					tamMax = Integer.parseInt(registrosMax.getCantidad());
					//tamMax = 500; // 20000
				}
			}
			
			return tamMax;
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "obtenerTamMaximoArchivo");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			
			return null;
		} finally {
			tamMax = null;
			registrosMax = null;
			filter = null;
		}
	}
	
	public String getFileGeneratedData (String archivo, SolicitudFilter solicitudFilter) {
		Short idTipoBanco 			= null;
		String tipoBanco  			= null;
		Short idTipoCuenta 			= null;
		String tipoCuenta			= null;
		String message 				= null;
		
		try {
			idTipoBanco=(	solicitudFilter.getIdsBancos() != null && !solicitudFilter.getIdsBancos().isEmpty() 
						? solicitudFilter.getIdsBancos().get(0) 
						: ID_OTROS_BANCOS_DOMI_FILE);
			tipoBanco =  this.getCatalogDescription(idTipoBanco);
			idTipoCuenta=( solicitudFilter.getIdsTiposCuenta()!=null && !solicitudFilter.getIdsTiposCuenta().isEmpty() 
						 ? solicitudFilter.getIdsTiposCuenta().get(0) 
						 : -1);
			tipoCuenta= this.getCatalogDescription(idTipoCuenta);
	
	
			message = (tipoCuenta!=null?tipoCuenta+" - ":" ") + (tipoBanco!=null?tipoBanco:" ");
			
			return message;
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "getFileGeneratedData");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			return null;
		} finally {
			idTipoBanco = null;
			tipoBanco = null;
			idTipoCuenta = null;
			tipoCuenta	= null;
			message = null;
		}
	}
	
	public Boolean isNumber(String str) {
		try {
			Long.parseLong(str);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// Metodos internos, para uso exclusivo de clase (no invcables)
	private void addIdsBancosTo (SolicitudFilter solFilter, Collection<Short> idsBancos) {
		try {
			for(Short idBanco:idsBancos) {
				
				if(ID_OTROS_BANCOS_DOMI_FILE.equals(idBanco)){
					solFilter.setFlagOtrosBancos(Boolean.TRUE);
					solFilter.setClaveBanco(new GenericCatalogoVO(ID_OTROS_BANCOS_DOMI_FILE));
				} else{
					solFilter.getIdsBancos().add(idBanco);
					solFilter.setClaveBanco(new GenericCatalogoVO(idBanco));
				}
			}
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "addIdsBancosTo");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
		}
	}
	
	private SolicitudFilter genSolFilterBasicData (DomiParameterMapFilter filters, DomiParameterKeyFilter key) {
		SolicitudFilter solFilter = new SolicitudFilter();
		
		try {
			solFilter = new SolicitudFilter();
			solFilter.setFechaInicio(filters.getFechaInicio());
			solFilter.setFechaFin(filters.getFechaFin());
			
			if(key!=null){
				solFilter.setIdTipoArchivo(key.getIdTipoArchivo());
			}
			
			solFilter.setUsuarioCreacion(filters.getUsuario());
			solFilter.setOrigenAportacion(new GenericCatalogoVO(filters.getIdOrigenDomiciliacion()));
			
			return solFilter;
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "genSolFilterBasicData");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			return null;
		} finally {
			solFilter = null;
		}
	}
	
	private String getCatalogDescription (Short id) {
		String desc 						= null;
		ParamCatalogoVO paramCatalogoVO		= null;
		List<CatalogoVO> result  			= null;
		CatalogoVO c						= null;
		
		try {
			paramCatalogoVO = new ParamCatalogoVO();
			paramCatalogoVO.setIdCatalogo(id);
			result = catalogoPersistence.select(paramCatalogoVO);
			
			if(result!=null && !result.isEmpty()){
				c = result.get(0);
				desc =  c.getValor();
			}
			
			return desc;
		} catch (Exception e) { 
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "getCatalogDescription");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			return null;
		} finally {
			desc = null;
			paramCatalogoVO = null;
			result = null;
			c = null;
		}
	}
	
	@Transactional(rollbackFor = MitBusinessException.class, propagation = Propagation.REQUIRED)
	private Long insertArchivoGeneradoParF4(SolicitudFilter filter, String nombreArchivo, Short origen) throws Exception{
		ArchivosGeneradosVO archivoGen			= null;
		DetalleArchivoDomiVO detalle 			= null;
		
		try{
			
			archivoGen	= new ArchivosGeneradosVO(new Long("0"));
			archivoGen.setClaveBanco(new GenericCatalogoVO(filter.getIdTipoArchivo()));
			archivoGen.setEstatus(new GenericCatalogoVO(ID_ESTATUS_ARCHIVO_DOMI_EN_PROCESO));
			archivoGen.setFechaFin(filter.getFechaFin());
			archivoGen.setFechaInicio(filter.getFechaInicio());
			archivoGen.setNombreArchivo(nombreArchivo);
			archivoGen.setUsuario(filter.getUsuarioCreacion());
			int status = archivoDomiPersistence.insertPar(archivoGen);
			
			if(status == 1 ){
				if(!filter.getFlagOtrosBancos()){
					for(Short idBanco:filter.getIdsBancos()){
						for(Short idTipoCuenta:filter.getIdsTiposCuenta()){
							detalle = new DetalleArchivoDomiVO();
							detalle.setTipoCuenta(new GenericCatalogoVO(idTipoCuenta));
							detalle.setClaveBanco(new GenericCatalogoVO(idBanco));
							detalle.setIdArchivo(archivoGen.getIdArchivo());
							detalle.setIdOrigen(origen);
							detalleArchivoDomiPersistence.insertDetallePar(detalle);
						}
					}
				}else{
					for(Short idTipoCuenta:filter.getIdsTiposCuenta()){
						detalle = new DetalleArchivoDomiVO();
						detalle.setTipoCuenta(new GenericCatalogoVO(idTipoCuenta));
						detalle.setClaveBanco(new GenericCatalogoVO(ID_OTROS_BANCOS_DOMI_FILE));
						detalle.setIdArchivo(archivoGen.getIdArchivo());
						detalle.setIdOrigen(origen);
						detalleArchivoDomiPersistence.insertDetallePar(detalle);
					}
				}
			}
			
			return archivoGen.getIdArchivo();
		}catch(Exception e){
			LOGGER.error("ERROR UBICACIÓN :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO :" + "insertArchivoGeneradoParF4");
			LOGGER.error("ERROR MENSAJE :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCION :", e);
			throw e;
		}finally {
			archivoGen			= null;
			detalle 			= null;
		}
	}
	
	private BanamexDomiFixLenghtFileRecordBeanPar  mapVOToBanamexRecordBeanF4(List<SolicitudVO> solicitudes,Integer consecutivo, Date fecha, Long idArchivo) {
		Integer numSeq									= null;
		BanamexDomiFixLenghtFileRecordBeanPar bean 		= null;
		BanamexDomiHeaderRecordBeanPar header 			= null;
		BigDecimal total 								= null;
		BanamexDomiFooterRecordBean footer 				= null;
		List<BanamexDomiRecordRecordBeanPar> records 	= null;
		
		try {
			
			numSeq	=	0;
			bean 	= new BanamexDomiFixLenghtFileRecordBeanPar();
			//Header 
			header	= new BanamexDomiHeaderRecordBeanPar();
			header.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			header.setConsecutivo(consecutivo);
			header.setNumSecuencia(++numSeq);
			header.setFechaPresentacion(fecha);

			bean.setHeader(header);
			bean.setRecords(new ArrayList<BanamexDomiRecordRecordBeanPar>());
			total = new BigDecimal(0);
			
			for(SolicitudVO sol :solicitudes){
				try{
					records = this.getBanamexRecordsF4(sol,fecha);
					for(BanamexDomiRecordRecordBeanPar record:records) {
						total=total.add(record.getImporteOperacion());				
						record.setNumSecuencia(++numSeq);
						bean.getRecords().add(record);
					}
				}catch(Exception e){
					LOGGER.warn("La solicitud "+sol.getClaveSolicitud()+" no se incluye en el archivo:"+e.getMessage(),e);
				}
			}
			
			footer = new BanamexDomiFooterRecordBean();
			footer.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			footer.setConsecutivo(consecutivo);
			footer.setNumeroOperaciones(numSeq-1);//Total de registros = numSeq -1 (Encabezado)
			footer.setNumeroSecuencia(++numSeq);
			footer.setImporteTotalOperaciones(total);
			bean.setFooter(footer);
			return bean;
			
			
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "mapVOToBanamexRecordBeanF4" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , e );
			return null;
		}finally {
			numSeq	= null;
			bean 	= null;
			header 	= null;
			total 	= null;
			footer 	= null;
		}
	}	
	
	private BancomerDomiFixLenghtFileRecordBeanPar generaBeanArchivoBancomer( Date fecha
																		    , List<BancomerDomiRecordRecordBeanPar> registros
																		    , BigDecimal total
																		    , Integer numSeq
																		    , Integer consecutivo
																		    , String contrato) {
		BancomerDomiFixLenghtFileRecordBeanPar bean = null;
		BancomerDomiHeaderRecordBeanPar header = null;
		BancomerDomiFooterRecordBean footer = null;
		Integer numSeqHeader = null;
		

		try {

			BigDecimal totalAux = new BigDecimal(0);
			
			bean = new BancomerDomiFixLenghtFileRecordBeanPar();
			header = new BancomerDomiHeaderRecordBeanPar(contrato);
			numSeqHeader = 0;

			// Header
			header.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			header.setConsecutivo(1); // 1 Para consecutivo (mas adelante será actualizado)
			header.setNumSecuencia(++numSeqHeader);
			header.setFechaPresentacion(fecha);
			bean.setHeader(header);

			// Registros
			bean.setRecords(registros);

			// Footer
			footer = new BancomerDomiFooterRecordBean();
			footer = new BancomerDomiFooterRecordBean();
			footer.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			footer.setConsecutivo(1); // 1 Para encabezado (mas adelante será actualizado)
			footer.setNumeroOperaciones(registros.size()); // numSeq - 1
			footer.setNumeroSecuencia(++numSeq);
			footer.setImporteTotalOperaciones(total);
			bean.setFooter(footer);
			
			// Total
			for (BancomerDomiRecordRecordBeanPar reg : registros) {
				totalAux = totalAux.add(reg.getImporteOperacion());
			}

			return bean;
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName());
			LOGGER.error("ERROR METODO        :" + "generaBeanArchivoBanamex");
			LOGGER.error("ERROR MENSAJE       :" + e.getMessage());
			LOGGER.error("ERROR DESCRIPCIÓN   :", e);
			return null;
		} finally {
			bean = null;
			header = null;
			footer = null;
			numSeqHeader = null;
		}
	}
	
	
	private List<BanamexDomiRecordRecordBeanPar> getBanamexRecordsF4(SolicitudVO sol, Date fecha) {
		List<BanamexDomiRecordRecordBeanPar> records 	= null;
		BigDecimal importe 								= null;
		List<BigDecimal> montosParciales 				= null;
		int numParcialidad 								= 0 ;
		BanamexDomiRecordRecordBeanPar record 			= null;
		String nombreCompleto 							= null;
		
		try {
			
			records 		= new ArrayList<BanamexDomiRecordRecordBeanPar>();
			importe 		= sol.getImporte();
			montosParciales = this.parcializaMontoF4(importe, Constantes.DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO);
			for(BigDecimal monto: montosParciales) {
				record = new BanamexDomiRecordRecordBeanPar();
				//record.setIdCargo(sol.getCargos().get(0).getIdCargo());
				record.setIdCargo(12);
				record.setNumParcialidad(++numParcialidad);
				record.setBancoReceptorClienteUsuario(sol.getClaveBanco().getId().intValue());
				record.setFechaLiquidacion(fecha);
				record.setFechaVencimiento(fecha);
				nombreCompleto = (sol.getClienteNombre()!=null?sol.getClienteNombre():"")+' '
										+(sol.getClientePaterno()!=null?sol.getClientePaterno():"")+' '
										+(sol.getClienteMaterno()!=null?sol.getClienteMaterno():"");
				record.setNombreClienteUsuario( this.cleanSpecialCaracters(nombreCompleto));
				record.setNombreTitularServicio( this.cleanSpecialCaracters(nombreCompleto));
				record.setNumCuentaClienteUsuario(new BigInteger(sol.getCuentaBanco()));
				//record.setReferenciaNumericaEmisor(referenciaNumericaEmisor);
				record.setReferenciaServicioEmisor(sol.getNumCuentaIndividual().toString());
				sol.setNumCargo(sol.getNumCargo()!=null ? sol.getNumCargo() + 1 : 1);
				record.setNumDomiciliacion(sol.getNumCargo());
				record.setClaveSolicitud(sol.getClaveSolicitud());
				record.setTipoCuentaClienteUsuario(sol.getTipoCuenta().getId().intValue());
				record.setImporteOperacion(monto);
				records.add(record);
		}
			return records;
			
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACIÓN    :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO       :" + "getBanamexRecordsF4" );
			LOGGER.error( "ERROR MENSAJE      :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN  :" , e );
			return null;
		}finally {
			records 				= null;
			importe 				= null;
			montosParciales 		= null;
			numParcialidad 			= 0 ;
			record 					= null;
			nombreCompleto 			= null;
		}
	}
	
	private List<BigDecimal> parcializaMontoF4(BigDecimal monto, BigDecimal maxMontoParcial){
		List<BigDecimal> parcialidades 	= null;
		BigDecimal[] divYRemanente 		= null;
		
		Integer registrosCompletos 		= null;
		BigDecimal restante 			= null;
		
		try {
			
			parcialidades = new ArrayList<BigDecimal>();
			divYRemanente = monto.divideAndRemainder(maxMontoParcial);
			
			if(divYRemanente!=null && divYRemanente.length > 1) {
				registrosCompletos 	= divYRemanente[0].intValue();
				restante 			= divYRemanente[1];
				for(int i = 1; i <= registrosCompletos; i++) {
					parcialidades.add(maxMontoParcial);
				}
				if(BigDecimal.ZERO.compareTo(restante) < 0) {
					parcialidades.add(restante);
				}
			}
			return parcialidades;
			
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACIÓN   :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO      :" + "parcializaMontoF4" );
			LOGGER.error( "ERROR MENSAJE     :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN :" , e );
			return null;
		}finally {
			parcialidades 			= null;
			divYRemanente 			= null;
			registrosCompletos 		= null;
			restante 				= null;
		}
	}
	
	private String cleanSpecialCaracters(String string){
		if(string!=null){
			for(String special:SPECIAL_CHARACTERS_REPLACERS.keySet()){
				string=string.replace(special, SPECIAL_CHARACTERS_REPLACERS.get(special));
			}
		}
		return string;
	}
	
	
	private BancomerDomiFixLenghtFileRecordBeanPar  mapVOToBancomerRecordBeanF4( List<SolicitudVO> solicitudes
																			   , Integer consecutivo
																			   , Date fecha
																			   , Long idArchivo
																			   , String contrato){
		Integer numSeq								= null;
		BancomerDomiFixLenghtFileRecordBeanPar bean = null;
		BancomerDomiHeaderRecordBeanPar header 		= null;
		BigDecimal total 							= null;
		BancomerDomiFooterRecordBean footer 		= null;
		
		try {
			
			numSeq 	= 0;
			bean 	= new BancomerDomiFixLenghtFileRecordBeanPar();
			//HEADER 
			header  = new BancomerDomiHeaderRecordBeanPar(contrato);
			header.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			header.setConsecutivo(consecutivo);
			header.setNumSecuencia(++numSeq);
			header.setFechaPresentacion(fecha);
			
			bean.setHeader(header);
			bean.setRecords(new ArrayList<BancomerDomiRecordRecordBeanPar>());
			
			total = new BigDecimal(0);
			
			for(SolicitudVO sol :solicitudes){
				try{
					List<BancomerDomiRecordRecordBeanPar> records = this.getBancomerRecordsF4(sol, fecha);
					for(BancomerDomiRecordRecordBeanPar record:records) {
						total=total.add(record.getImporteOperacion());
						record.setNumSecuencia(++numSeq);
						bean.getRecords().add(record);
					}
				}catch(Exception e){
					LOGGER.warn("La solicitud " + sol.getClaveSolicitud() + " no se incluye en el archivo: " + e.getMessage() , e);
				}
			}
			
			footer = new BancomerDomiFooterRecordBean();
			footer.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			footer.setConsecutivo(consecutivo);
			footer.setNumeroOperaciones(numSeq-1);//Total de registros = numSeq -1 (Encabezado)
			footer.setNumeroSecuencia(++numSeq);
			footer.setImporteTotalOperaciones(total);
			bean.setFooter(footer);
			return bean;
			
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "mapVOToBancomerRecordBeanF4" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , e );
			return null;
		}finally {
			numSeq	= null;
			bean 	= null;
			header 	= null;
			total 	= null;
			footer 	= null;
		}
	}
	
	private BancomerDomiFixLenghtFileRecordBeanPar  mapVOToBancomerRecordBeanF4(List<SolicitudVO> solicitudes,Integer consecutivo,Date fecha, Long idArchivo){
		Integer numSeq								= null;
		BancomerDomiFixLenghtFileRecordBeanPar bean = null;
		BancomerDomiHeaderRecordBeanPar header 		= null;
		BigDecimal total 							= null;
		BancomerDomiFooterRecordBean footer 		= null;
		
		try {
			
			numSeq 	= 0;
			bean 	= new BancomerDomiFixLenghtFileRecordBeanPar();
			//HEADER 
			header  = new BancomerDomiHeaderRecordBeanPar();
			header.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			header.setConsecutivo(consecutivo);
			header.setNumSecuencia(++numSeq);
			header.setFechaPresentacion(fecha);
			
			bean.setHeader(header);
			bean.setRecords(new ArrayList<BancomerDomiRecordRecordBeanPar>());
			
			total = new BigDecimal(0);
			
			for(SolicitudVO sol :solicitudes){
				try{
					List<BancomerDomiRecordRecordBeanPar> records = this.getBancomerRecordsF4(sol, fecha);
					for(BancomerDomiRecordRecordBeanPar record:records) {
						total=total.add(record.getImporteOperacion());
						record.setNumSecuencia(++numSeq);
						bean.getRecords().add(record);
					}
				}catch(Exception e){
					LOGGER.warn("La solicitud " + sol.getClaveSolicitud() + " no se incluye en el archivo: " + e.getMessage() , e);
				}
			}
			
			footer = new BancomerDomiFooterRecordBean();
			footer.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			footer.setConsecutivo(consecutivo);
			footer.setNumeroOperaciones(numSeq-1);//Total de registros = numSeq -1 (Encabezado)
			footer.setNumeroSecuencia(++numSeq);
			footer.setImporteTotalOperaciones(total);
			bean.setFooter(footer);
			return bean;
			
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "mapVOToBancomerRecordBeanF4" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , e );
			return null;
		}finally {
			numSeq	= null;
			bean 	= null;
			header 	= null;
			total 	= null;
			footer 	= null;
		}
	}
	
	private List <BancomerDomiFixLenghtFileRecordBeanPar>  mapVOToBancomerRecordBeanF4Lotes( List<SolicitudVO> solicitudes
																				    		, Integer consecutivo
																				    		, Date fecha
																				    		, Integer tamMax
																				    		, String contrato) {
		Integer numSeq											= null;
		BancomerDomiFixLenghtFileRecordBeanPar bean 			= null;
		List <BancomerDomiFixLenghtFileRecordBeanPar> beans 	= null;
		BigDecimal total 										= null;
		List<BancomerDomiRecordRecordBeanPar> records 			= null;
		List<BancomerDomiRecordRecordBeanPar> lote 				= null;
		
		try {
			bean 	= new BancomerDomiFixLenghtFileRecordBeanPar();
			lote = new ArrayList <BancomerDomiRecordRecordBeanPar> (); 
			numSeq	=	0;
			total = new BigDecimal(0);
			beans = new ArrayList <BancomerDomiFixLenghtFileRecordBeanPar> ();
			bean.setRecords(new ArrayList<BancomerDomiRecordRecordBeanPar>());
			numSeq++; // Encabezado
			
			// Segmentacion por lotes y parcialidades 
			for(SolicitudVO sol :solicitudes) { 
				
				try{
					records = this.getBancomerRecordsF4(sol, fecha); // Parcialidades
					
					for(BancomerDomiRecordRecordBeanPar record : records) {
						
						if (lote.size() < tamMax) {
							total = total.add(record.getImporteOperacion());				
							record.setNumSecuencia( ++numSeq );
							bean.getRecords().add(record);
							lote.add(record);
			            }
						
						if (lote.size() == tamMax) {
							beans.add(this.generaBeanArchivoBancomer(  Calendar.getInstance().getTime()
																    , lote
																    , total
																    , numSeq
																    , consecutivo
																    , contrato));
							
							bean 	= new BancomerDomiFixLenghtFileRecordBeanPar();
							lote = new ArrayList <BancomerDomiRecordRecordBeanPar> (); 
							numSeq	=	0;
							numSeq++; // Encabezado
							total = new BigDecimal(0);
							bean.setRecords(new ArrayList<BancomerDomiRecordRecordBeanPar>());
			            }

					}
				}catch(Exception e){
					LOGGER.warn( "La solicitud "+sol.getClaveSolicitud() + " no se incluye en el archivo:"+e.getMessage(),e);
				}
			}
			
			if (!lote.isEmpty()) {
				beans.add(this.generaBeanArchivoBancomer(  Calendar.getInstance().getTime()
													    , lote
													    , total
													    , numSeq
													    , consecutivo
													    , contrato));
			}
			
			return beans;
			
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "mapVOToBancomerRecordBeanF4" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , e );
			return null;
		}finally {
			numSeq	= null;
			bean 	= null;
			beans 	= null;
			total 	= null;
			records = null;
			lote 	= null;
		}
	}
	
	private List<BancomerDomiRecordRecordBeanPar> getBancomerRecordsF4(SolicitudVO sol, Date fecha){
		List<BancomerDomiRecordRecordBeanPar> records 	= null;
		BigDecimal importe 								= null;
		List<BigDecimal> montosParciales 				= null;
		String nombreCompleto							= null;
		BancomerDomiRecordRecordBeanPar record 			= null;
		
		try {
			
			records 			= new ArrayList<BancomerDomiRecordRecordBeanPar>();
			importe 			= sol.getImporte();
			montosParciales 	= parcializaMontoF4(importe, Constantes.DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO);
			int numParcialidad  = 0;
			for(BigDecimal monto: montosParciales) {
					record = new BancomerDomiRecordRecordBeanPar();
					record.setIdCargo( 12 );
					record.setNumParcialidad( ++numParcialidad );
					record.setBancoReceptorClienteUsuario(sol.getClaveBanco().getId().intValue());
					record.setFechaLiquidacion(fecha);
					record.setFechaVencimiento(fecha);
					nombreCompleto = (sol.getClienteNombre()!=null?sol.getClienteNombre():"")+' '
							+(sol.getClientePaterno()!=null?sol.getClientePaterno():"")+' '
							+(sol.getClienteMaterno()!=null?sol.getClienteMaterno():"");
					record.setNombreClienteUsuario(cleanSpecialCaracters(nombreCompleto));
					record.setNombreTitularServicio(cleanSpecialCaracters(nombreCompleto));
					record.setNumCuentaClienteUsuario(new BigInteger(sol.getCuentaBanco()));
					record.setReferenciaServicioEmisor(sol.getNumCuentaIndividual().toString());
					sol.setNumCargo(sol.getNumCargo()!=null ? sol.getNumCargo() + 1 : 1);
					record.setNumDomiciliacion(sol.getNumCargo());
					record.setClaveSolicitud(sol.getClaveSolicitud());
					record.setTipoCuentaClienteUsuario(sol.getTipoCuenta().getId().intValue());
					record.setImporteOperacion(monto);
					records.add(record);
				}
			return records;
			
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "getBancomerRecordsF4" );
			LOGGER.error( "ERROR MENSAJE       :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , e );
			return null;
		}finally {
			records 			= null;
			importe 			= null;
			montosParciales 	= null;
			nombreCompleto		= null;
			record 				= null;
		}
	}
	
	@Transactional(rollbackFor = MitBusinessException.class, propagation = Propagation.REQUIRED)
	private void updateEstatusSolicitud(List<SolicitudVO> domis, String usuario) throws MitBusinessException {
		String currentSolicitud			= null;
		SolicitudVO solicitudEstatus 	= null;
		
		try{
			for(SolicitudVO solicitud:domis){
				solicitudEstatus = new SolicitudVO();
				currentSolicitud =solicitud.getClaveSolicitud();
				solicitudEstatus.setClaveSolicitud(currentSolicitud);
				solicitudEstatus.setEstatusCargo(new GenericCatalogoVO(ID_ESTATUS_CARGO_GENERADO));
				solicitudEstatus.setUsuarioActualizacion(usuario);
				solicitudEstatus.setNumCargo(solicitud.getNumCargo());
				solicitudPersistence.actualizarSolicitudDomiciliacion(solicitudEstatus);
			}
		}catch(Exception ex){
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "updateEstatusSolicitud" );
			LOGGER.error( "ERROR MENSAJE       :" + ex.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , ex );
			
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al generar archivo de domiciliacion. "
							+ "Actualizar estatus de solicitud con clave: "+currentSolicitud,
					new Object[] {getClass().getSimpleName(), "updateEstatusSolicitud()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);
			System.out.println("ERROR UPDATE ESTATUS SOLICITUD F4 :: " + ex.getMessage());
			ex.printStackTrace();

			throw mitBusinessException;
		}finally {
			currentSolicitud	= null;
			solicitudEstatus 	= null;
		}
	}
	
	@Transactional(rollbackFor = MitBusinessException.class, propagation = Propagation.REQUIRED)
	private Integer updateEstatusArchivoGeneradoPar(Long idArchivo, Short idEstatus,String usuario) throws MitBusinessException{
		Integer updated = 0;
		
		try {
			ArchivosGeneradosVO archivoGen= new ArchivosGeneradosVO();
			archivoGen.setEstatus(new GenericCatalogoVO(idEstatus));//
			archivoGen.setIdArchivo(idArchivo);
			archivoGen.setUsuario(usuario);
			updated = archivoDomiPersistence.updatePar(archivoGen);
			return updated;
		}catch(Exception ex){
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "updateEstatusArchivoGeneradoPar" );
			LOGGER.error( "ERROR MENSAJE       :" + ex.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , ex );
			
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al generar archivo de archivoDomiPersistence.update registro de archivo generado"+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "updateEstatusArchivoGenerado()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);
			System.out.println("ERROR UPDATE :: ESTATUS ARCHIVO GENERADO PAR :: F4" + ex.getMessage());
			ex.printStackTrace();

			throw mitBusinessException;
		}finally {
			updated = null; 
		}
	}

	public String getParametroLiberacion() throws Exception {
		String value = null;
		
		try {
			value = new NameSpaceBindingProvider().getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_F4_MANTENIMIENTO_JAV_LIBERACION_DOMI);
			
			if (value == null || value.isEmpty()) {
				return "-2";
			} else {
				return value;
			}
		} catch (Exception ex) {
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "getParametroLiberacion" );
			LOGGER.error( "ERROR MENSAJE       :" + ex.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , ex );
			return "-1";
		} finally {
			value = null;
		}
	}
	
	public String getNombreContrato (String idcontrato) throws Exception {
		String nombreContrato = null;
		UtilsParseXML ws = null;
		String resServ = null;
		String respReporte = null;
		
		try {
			nombreContrato = "";
			ws = new UtilsParseXML();
			
			resServ = (String) new ConsumeWS().consumeWS( null
														, new ClientesEnvelope().envelopeContratoArchivosDomi(idcontrato)
														, new NameSpaceBindingProvider().getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_F4_MANTENIMIENTO_JAV_CATALOGO_CONTRATO));
			
			resServ = resServ.replace("&quot;", "\"");
			resServ = new String(resServ.getBytes(), "ISO-8859-1");
			resServ = new String(resServ.getBytes("ISO-8859-1"), "UTF-8");
			
			if (ws.obtenerValor("<success>", "</success>", resServ).trim().equals("true")) {
				nombreContrato = ws.obtenerValor("<valor>", "</valor>", resServ);
			}
			
			return nombreContrato;
		} catch (Exception ex) {
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "getNombreContrato" );
			LOGGER.error( "ERROR MENSAJE       :" + ex.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , ex );
			return "-1";
		} finally {
			 nombreContrato = null;
		}
	}

	public List<CifrasTotalesDomiVO> ordenarListaCifrasTotales(List<CifrasTotalesDomiVO> cifrasTotales) {
		try {
			
			Collections.sort(cifrasTotales, new Comparator<CifrasTotalesDomiVO>() {
	            public int compare(CifrasTotalesDomiVO p1, CifrasTotalesDomiVO p2) {
	                    return new Integer( p1.getIdPerido() ).compareTo(new Integer( p2.getIdPerido() ));
	            }
	        });
			
			 Collections.reverse(cifrasTotales);
			 
			 return cifrasTotales;
			
		} catch (Exception ex) {
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "ordenarListaCifrasTotales" );
			LOGGER.error( "ERROR MENSAJE       :" + ex.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , ex );
			return null;
		}
	}

	/**
	 * Metodo que manda ejecutar la sentencia a BD (Maybatis)
	 * @param filter, parametros de entrada
	 */
	public List<ArchivosDomiciliacionVO> consultarArchivosDomiciliacion(FiltroArchivosDomiciliacionFilter filter) throws Exception {
		try {
			return archivoDomiPersistence.consultarArchivosDomiciliacion(filter);
		} catch (Exception ex) {
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "consultarArchivosDomiciliacion" );
			LOGGER.error( "ERROR MENSAJE       :" + ex.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , ex );
			throw ex;
		}
	}

	/**
	 * Metodo que manda ejecutar la sentencia a BD (Maybatis)
	 * Obtiene los archivos de domiciliacion bitacora
	 * @param filter, parametros de entrada
	 */
	public List<ArchivosDomiciliacionBitacoraVO> consultarArchivosDomiciliacionBitacora(FiltroArchivosDomiciliacionFilter filter) throws Exception {
		try {
			return archivoDomiPersistence.consultarArchivosDomiciliacionBitacora(filter);
		} catch (Exception ex) {
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "consultarArchivosDomiciliacionBitacora" );
			LOGGER.error( "ERROR MENSAJE       :" + ex.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , ex );
			throw ex;
		}
	}

	/**
	 * Metodo que consulta los Archivos domiciliacion bitacora por folio
	 * @param TablaDinamicaArchDomiFilter filter
	 * @return List<ArchivosDomiciliacionBitacoraVO> | Lista de archivos
	 */
	public List<ArchivosDomiciliacionBitacoraVO> generarTablaDinamicaArchDomi(TablaDinamicaArchDomiFilter filter) throws Exception {
		try {
			return archivoDomiPersistence.consultarArchivosDomiBitacora(filter);
		} catch (Exception ex) {
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "generarTablaDinamicaArchDomi" );
			LOGGER.error( "ERROR MENSAJE       :" + ex.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , ex );
			throw ex;
		}
	}

	/**
	 * Metodo que actualiza el valor del procesamiento de domiciliacion
	 * valor 1 = PROCESAMIENTO ORDINARIO DE ARCHIVOS DE DOMICILIACION (MANEJO DE UCA POR BUS)
 	 * valor 2 = PROCESAMIENTO ESPECIAL DE ARCHIVOS DE DOMICILIACION (MANEJO DE UCA POR JAVA)
 	 * @param request | parametros de entrada
	 */
	public Integer actValorProcesamientoDomi(TipoProcesamientoDomisFilter filter) throws Exception {
		try {
			return archivoDomiPersistence.actValorProcesamientoDomi(filter);
		} catch (Exception ex) {
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "actValorProcesamientoDomi" );
			LOGGER.error( "ERROR MENSAJE       :" + ex.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , ex );
			throw ex;
		}
	}

	/**
	 * Metodo que obtiene la cantidad de registros que contiene cada archivo 
	 * de Domiciliacion.
	 * Se filtrara por folio.
	 */
	public List<RegistrosArchivoVO> consultarRegistrosArchivo(TablaDinamicaArchDomiFilter filter) throws Exception {
		try {
			return archivoDomiPersistence.consultarRegistrosArchivo(filter);
		} catch (Exception ex) {
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "consultarRegistrosArchivo" );
			LOGGER.error( "ERROR MENSAJE       :" + ex.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , ex );
			throw ex;
		}
	}

	public Boolean responderFinalizacionArchivosDomi (String folio) throws Exception{
		String nombreContrato = null;
		UtilsParseXML ws = null;
		String resServ = null;
		String respReporte = null;
		ProcesamientoDomiVO procesamiento = null;
		
		try {
			
			procesamiento = this.consultarProcesamientoDomi();
			
			if ( procesamiento.getValor().equals("2") ) {
				
				nombreContrato = "";
				ws = new UtilsParseXML();
				
				resServ = (String) new ConsumeWS().consumeWS( null
															, new ClientesEnvelope().envelopeContratoFinalizacionArchDomi(folio, "1")
															, new NameSpaceBindingProvider().getValue(WebSphereNamespace.WS_ENDPOINT_CLIENT_SOAP_F4_MANTENIMIENTO_BUS_DOMIS));
				
				resServ = resServ.replace("&quot;", "\"");
				resServ = new String(resServ.getBytes(), "ISO-8859-1");
				resServ = new String(resServ.getBytes("ISO-8859-1"), "UTF-8");
				
				System.out.println("**** RESPUESTA FIN BUS: " + resServ);
				
				if (ws.obtenerValor("<success>", "</success>", resServ).trim().equals("true")) {
					return Boolean.TRUE;
				} else {
					return  Boolean.FALSE;
				}
				
			} else {
				return Boolean.TRUE;
			}
			
		} catch (Exception ex) {
			LOGGER.error( "ERROR UBICACIÓN     :" + DomiciliacionesServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO        :" + "responderFinalizacionServicio" );
			LOGGER.error( "ERROR MENSAJE       :" + ex.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN   :" , ex );
			throw ex;
		} finally {
			nombreContrato = null;
			ws = null;
			resServ = null;
			respReporte = null;
			procesamiento = null;
		}
		
	}
	
}

