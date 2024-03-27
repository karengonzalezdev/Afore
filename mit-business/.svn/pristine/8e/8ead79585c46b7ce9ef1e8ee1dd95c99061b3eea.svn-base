package mx.profuturo.nci.business.service.impl;

import static mx.profuturo.nci.business.util.Constantes.DOMI_FILES_DATE_FORMAT_BANAMEX;
import static mx.profuturo.nci.business.util.Constantes.DOMI_FILES_DATE_FORMAT_BANCOMER;
import static mx.profuturo.nci.business.util.Constantes.FILE_NAME_EXT_DOMICILIACIONES_BANAMEX;
import static mx.profuturo.nci.business.util.Constantes.FILE_NAME_EXT_DOMICILIACIONES_BANCOMER;
import static mx.profuturo.nci.business.util.Constantes.FILE_NAME_OFFSET_DOMICILIACIONES_TRASPASO;
import static mx.profuturo.nci.business.util.Constantes.FILE_NAME_PART_DOMICILIACIONES_BANAMEX;
import static mx.profuturo.nci.business.util.Constantes.FILE_NAME_PART_DOMICILIACIONES_BANCOMER;
import static mx.profuturo.nci.business.util.Constantes.ID_BANAMEX_DOMICILIACIONES;
import static mx.profuturo.nci.business.util.Constantes.ID_BANCOMER_DOMICILIACIONES;
import static mx.profuturo.nci.business.util.Constantes.ID_ESTATUS_ARCHIVO_DOMI_GENERADO;
import static mx.profuturo.nci.business.util.Constantes.ID_ESTATUS_ARCHIVO_DOMI_EN_PROCESO;
import static mx.profuturo.nci.business.util.Constantes.ID_ESTATUS_ARCHIVO_DOMI_CANCELADO;
import static mx.profuturo.nci.business.util.Constantes.PATH_ACHIVO_DOMI_BANAMEX;
import static mx.profuturo.nci.business.util.Constantes.PATH_ACHIVO_DOMI_BANCOMER;
import static mx.profuturo.nci.business.util.Constantes.SPECIAL_CHARACTERS_REPLACERS;
import static mx.profuturo.nci.business.util.Constantes.BANCOMER_CONSECUTIVE_PADDING_POSITIONS;
import static mx.profuturo.nci.business.util.Constantes.BANAMEX_CONSECUTIVE_PADDING_POSITIONS;
import static mx.profuturo.nci.business.util.Constantes.DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO;
import static mx.profuturo.nci.business.util.Constantes.ID_CAT_CATALOGO_MONTO_MAXIMO_ENVIO_BANCO;
import static mx.profuturo.nci.business.util.Constantes.ID_UNICA_PARCIALIDAD;
import static mx.profuturo.nci.business.util.Constantes.ID_MAS_DE_UNA_PARCIALIDAD;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_BANAMEX_NCI;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_BANCOMER_NCI;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ESTATUS_CARGO_GENERADO;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ORIGEN_DOMICILIACION_TRASPASO;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_OTROS_BANCOS_DOMI_FILE;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_PROCESO_RECAUDACION;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_SUBPROCESO_APORTACION_VOLUNTARIA;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_TIPO_MOVIMIENTO_ABONO;


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
import com.jeveris.core.exception.impl.BusinessException;
import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.file.generator.bean.BanamexDomiFixLenghtFileRecordBean;
import mx.profuturo.nci.business.file.generator.bean.BanamexDomiFixLenghtFileRecordBeanPar;
import mx.profuturo.nci.business.file.generator.bean.BanamexDomiFooterRecordBean;
import mx.profuturo.nci.business.file.generator.bean.BanamexDomiHeaderRecordBean;
import mx.profuturo.nci.business.file.generator.bean.BanamexDomiHeaderRecordBeanPar;
import mx.profuturo.nci.business.file.generator.bean.BanamexDomiRecordRecordBean;
import mx.profuturo.nci.business.file.generator.bean.BanamexDomiRecordRecordBeanPar;
import mx.profuturo.nci.business.file.generator.bean.BancomerDomiFixLenghtFileRecordBean;
import mx.profuturo.nci.business.file.generator.bean.BancomerDomiFixLenghtFileRecordBeanPar;
import mx.profuturo.nci.business.file.generator.bean.BancomerDomiFooterRecordBean;
import mx.profuturo.nci.business.file.generator.bean.BancomerDomiHeaderRecordBean;
import mx.profuturo.nci.business.file.generator.bean.BancomerDomiHeaderRecordBeanPar;
import mx.profuturo.nci.business.file.generator.bean.BancomerDomiRecordRecordBean;
import mx.profuturo.nci.business.file.generator.bean.BancomerDomiRecordRecordBeanPar;
import mx.profuturo.nci.business.file.generator.bean.DataFileBean;
import mx.profuturo.nci.business.file.generator.service.IFileTransferService;
import mx.profuturo.nci.business.file.generator.service.IFilesGeneratorService;
import mx.profuturo.nci.business.service.ICatalogosService;
import mx.profuturo.nci.business.service.IGeneracionArchivoDomiService;
import mx.profuturo.nci.business.service.IMatrizConvivenciaService;
import mx.profuturo.nci.business.util.Constantes;
import mx.profuturo.nci.business.util.ConstantesCatalogos;
import mx.profuturo.nci.business.util.ControlHilos;
import mx.profuturo.nci.business.util.Hilo;
import mx.profuturo.nci.business.util.SolicitudFilterType;
import mx.profuturo.nci.business.vo.ArchivosGeneradosVO;
import mx.profuturo.nci.business.vo.CargoVO;
import mx.profuturo.nci.business.vo.CatPrioridadesDiversificacionesVO;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.DetalleArchivoDomiVO;
import mx.profuturo.nci.business.vo.DiversificacionVO;
import mx.profuturo.nci.business.vo.GeneracionArchivoDomiVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.vo.ParamCatalogoVO;
import mx.profuturo.nci.business.vo.SolicitudSumTotalPorBancoVO;
import mx.profuturo.nci.business.vo.SolicitudSumTotalVO;
import mx.profuturo.nci.business.vo.SolicitudVO;
import mx.profuturo.nci.business.wrapped.ApoVolFilter;
import mx.profuturo.nci.business.wrapped.ArchivoDomiciliacionfilter;
import mx.profuturo.nci.business.wrapped.DomiParameterKeyFilter;
import mx.profuturo.nci.business.wrapped.DomiParameterMapFilter;
import mx.profuturo.nci.business.wrapped.DomiParameterValuesFilter;
import mx.profuturo.nci.business.wrapped.GeneraFolioFilter;
import mx.profuturo.nci.business.wrapped.MatrizConvivenciaFilter;
import mx.profuturo.nci.business.wrapped.SolicitudFilter;
import mx.profuturo.nci.persistence.ArchivoDomiPersistence;
import mx.profuturo.nci.persistence.CargoPersistence;
import mx.profuturo.nci.persistence.CatalogoPersistence;
import mx.profuturo.nci.persistence.DetalleArchivoDomiPersistence;
import mx.profuturo.nci.persistence.DiversificacionPersistence;
import mx.profuturo.nci.persistence.GeneraFolioPersistence;
import mx.profuturo.nci.persistence.SolicitudPersistence;
import profuturo.mx.iib.nci.bitacoraprocesos.folioservice.v1.FolioPortType;
import profuturo.mx.iib.nci.bitacoraprocesos.folioservice.v1.types.GenerarFolioReq;
import profuturo.mx.iib.nci.bitacoraprocesos.folioservice.v1.types.GenerarFolioResp;
import profuturo.mx.iib.nci.matrizconvivencia.matrizconvivenciaservice.v1.MatrizConvivenciaPortType;
import profuturo.mx.iib.nci.matrizconvivencia.matrizconvivenciaservice.v1.types.ConsultarConvivenciaReq;
import profuturo.mx.iib.nci.matrizconvivencia.matrizconvivenciaservice.v1.types.ConsultarConvivenciaResp;

@Service("generacionArchivoDomiService")
public class GeneracionArchivoDomiServiceImpl implements IGeneracionArchivoDomiService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GeneracionArchivoDomiServiceImpl.class);
	
	@Autowired SolicitudPersistence solicitudPersistence;
	@Autowired ArchivoDomiPersistence archivoDomiPersistence;
	@Autowired DetalleArchivoDomiPersistence detalleArchivoDomiPersistence;
	@Autowired CatalogoPersistence catalogoPersistence;
	@Autowired CargoPersistence cargoPersistence; 
	@Autowired DiversificacionPersistence diversificacionPersistence;

	@Autowired ICatalogosService catalogosService;
	
	@Autowired IMatrizConvivenciaService matrizConvivenciaService;
	
	@Autowired IFilesGeneratorService fileGeneratorService;
	@Autowired IFileTransferService fileTransferService;
	@Autowired WSPortTypeFactory wsPortTypeFactory;
	
	@Autowired
	GeneraFolioPersistence generaFolioPersistence;
	
	@Autowired
	Hilo hilo;
	
	public GeneracionArchivoDomiServiceImpl() {
		
	}

	@Transactional(rollbackFor = MitBusinessException.class, propagation = Propagation.REQUIRED)
	public List<GeneracionArchivoDomiVO> generarArchivosDomi(DomiParameterMapFilter filters) throws MitBusinessException {
		LOGGER.debug( "#######################################################################");
		LOGGER.debug( "#################### ANALIZANDO DOMICILIACIÓN #########################");
		LOGGER.debug( "#######################################################################");
		List<GeneracionArchivoDomiVO> resultados = new ArrayList<GeneracionArchivoDomiVO>();
		LOGGER.debug( "OBTENIENDO FILTROS ....");
		List<SolicitudFilter> filtros = getCustomFilter(filters,SolicitudFilterType.REGISTROS);
		LOGGER.debug( "OBTENIENDO FILTROS .... [OK] ");
		LOGGER.debug( "NUMERO DE FILTROS ["+ filtros.size() +"]" );
		if(filtros != null && !filtros.isEmpty()){
			Date fechaHoy= Calendar.getInstance().getTime();
			
			for(SolicitudFilter filtro:filtros){
				GeneracionArchivoDomiVO archivoGenerado= new GeneracionArchivoDomiVO();
				String archivo= null;
				try{
					ArchivoDomiciliacionfilter filter = new ArchivoDomiciliacionfilter();
					filter.setIdOrigenSolicitud((filtro.getOrigenAportacion()!=null?filtro.getOrigenAportacion().getId():null));
					Integer consecutivo = archivoDomiPersistence.selectConsecutivoHoy(filter);
					if(ID_ORIGEN_DOMICILIACION_TRASPASO.equals(filtro.getOrigenAportacion().getId())) {
						consecutivo+=FILE_NAME_OFFSET_DOMICILIACIONES_TRASPASO;
					}
					System.out.println( "ARCHIVO CONSECUTIVO :" + consecutivo  );
					archivo = generateName(filtro.getOrigenAportacion().getId(),filtro.getIdTipoArchivo(),consecutivo,fechaHoy);
					System.out.println( "ARCHIVO NOMBRE      :" + archivo  );
					if(archivo!=null){
						System.out.println( "GENERAR ARCHIVO DOMICILIACIÓN .....       ##########################################################" );
						archivoGenerado.setNombreArchivo(archivo);
						generarArchivoDomiF4(filtro, fechaHoy, archivo, consecutivo,filters.getIdOrigenDomiciliacion());
						archivoGenerado.setGenerated(Boolean.TRUE);
						String message=getFileGeneratedData(archivo,filtro)+" - "+"Archivo generado correctamente";
						archivoGenerado.getErrors().add(message);
						System.out.println( "GENERAR ARCHIVO DOMICILIACIÓN ..... [OK]  ##########################################################" );
					}
				}catch(Exception e){
					archivoGenerado.setGenerated(Boolean.FALSE);
					String[] error = e.getMessage().split(":");
					archivoGenerado.getErrors().add(error[0]);
//					archivoGenerado.getErrors().add(e.getMessage());
				}
				resultados.add(archivoGenerado);
			}
		}
		return resultados;
	}

	private List<SolicitudFilter> getCustomFilter(DomiParameterMapFilter filters,SolicitudFilterType type){
		List<SolicitudFilter> solFilters= new ArrayList<SolicitudFilter>();
		if(SolicitudFilterType.REGISTROS.equals(type) || SolicitudFilterType.CIFRAS_POR_BANCO.equals(type)){	
									// SE GENERAN LOS FILTROS PARA OBTENER LOS REGISTROS
			for(DomiParameterKeyFilter key:filters.getValues().keySet()){
				DomiParameterValuesFilter values = filters.getValues().get(key);
				if(values.getUnSoloArchivo() || SolicitudFilterType.CIFRAS_POR_BANCO.equals(type)){		
									// FILTROS PARA GENERAR UN SOLO ARCHIVO POR BANCO O CIFRAS POR BANCO
					SolicitudFilter solFilter = genSolFilterBasicData(filters,key);
					addIdsBancosTo(solFilter,values.getIdsBancos());
					solFilter.getIdsTiposCuenta().addAll(values.getIdsTiposCuenta());
					solFilters.add(solFilter);
				}else{ 				//FILTROS PARA REGISTROS NORMALES (POR TIPO ARCHIVO Y TIPO CUENTA
					for(Short idTipoCuenta:values.getIdsTiposCuenta()){
						SolicitudFilter solFilter = genSolFilterBasicData(filters,key);
						addIdsBancosTo(solFilter,values.getIdsBancos());
						solFilter.getIdsTiposCuenta().add(idTipoCuenta);
						solFilters.add(solFilter);
					}
				}
			}
		}else if(SolicitudFilterType.CIFRAS_TOTALES.equals(type)){	
									//FILTRO PARA CIFRAS TOTALES
			SolicitudFilter solFilter = genSolFilterBasicData(filters,null);
			for(DomiParameterKeyFilter key:filters.getValues().keySet()){
				DomiParameterValuesFilter values = filters.getValues().get(key);
				addIdsBancosTo(solFilter,values.getIdsBancos());
				solFilter.getIdsTiposCuenta().addAll(values.getIdsTiposCuenta());
			}
			solFilters.add(solFilter);
		}
		return solFilters;
	}
	
	private SolicitudFilter genSolFilterBasicData(DomiParameterMapFilter filters,DomiParameterKeyFilter key){
		SolicitudFilter solFilter = new SolicitudFilter();
		solFilter.setFechaInicio(filters.getFechaInicio());
		solFilter.setFechaFin(filters.getFechaFin());
		if(key!=null){
			solFilter.setIdTipoArchivo(key.getIdTipoArchivo());
		}
		solFilter.setUsuarioCreacion(filters.getUsuario());
		solFilter.setOrigenAportacion(new GenericCatalogoVO(filters.getIdOrigenDomiciliacion()));
		return solFilter;
	}
	
	
	private void addIdsBancosTo(SolicitudFilter solFilter,Collection<Short> idsBancos){
		for(Short idBanco:idsBancos){
			if(ID_OTROS_BANCOS_DOMI_FILE.equals(idBanco)){
				solFilter.setFlagOtrosBancos(Boolean.TRUE);
				solFilter.setClaveBanco(new GenericCatalogoVO(ID_OTROS_BANCOS_DOMI_FILE));
			}else{
				solFilter.getIdsBancos().add(idBanco);
				solFilter.setClaveBanco(new GenericCatalogoVO(idBanco));
			}
		}
	}
	
	private String getFileGeneratedData(String archivo,SolicitudFilter solicitudFilter){
		//  NombreDeArchivo.Extensión – Tipo de Cuenta – Banco – mensaje
		Short idTipoBanco=(solicitudFilter.getIdsBancos()!=null && !solicitudFilter.getIdsBancos().isEmpty() 
						? solicitudFilter.getIdsBancos().get(0) 
						: ID_OTROS_BANCOS_DOMI_FILE);
		String tipoBanco =  getCatalogDescription(idTipoBanco);
		Short idTipoCuenta=(solicitudFilter.getIdsTiposCuenta()!=null && !solicitudFilter.getIdsTiposCuenta().isEmpty() 
						? solicitudFilter.getIdsTiposCuenta().get(0) 
						: -1);
		String tipoCuenta= getCatalogDescription(idTipoCuenta);
		
		
		String message=
//				(archivo!=null?archivo+" - ":" ")
//				+
				(tipoCuenta!=null?tipoCuenta+" - ":" ")
				+(tipoBanco!=null?tipoBanco:" ");
		return message;
	}
	
	private String getFileGeneratedDataF4(String archivo,SolicitudFilter solicitudFilter){
		//  NombreDeArchivo.Extensión – Tipo de Cuenta – Banco – mensaje
		Short idTipoBanco	= null;
		String tipoBanco 	= null;
		Short idTipoCuenta  = null;
		String tipoCuenta   = null;
		String message      = null;
		try {
			
			idTipoBanco=(solicitudFilter.getIdsBancos()!=null && !solicitudFilter.getIdsBancos().isEmpty() 
					? solicitudFilter.getIdsBancos().get(0) 
					: ID_OTROS_BANCOS_DOMI_FILE);
			
			tipoBanco =  getCatalogDescription(idTipoBanco);
			idTipoCuenta=(solicitudFilter.getIdsTiposCuenta()!=null && !solicitudFilter.getIdsTiposCuenta().isEmpty() 
					? solicitudFilter.getIdsTiposCuenta().get(0) 
					: -1);
			tipoCuenta= getCatalogDescription(idTipoCuenta);
	
	
			message=
//			(archivo!=null?archivo+" - ":" ")
//			+
					(tipoCuenta!=null?tipoCuenta+" - ":" ")
					+(tipoBanco!=null?tipoBanco:" ");
			return message;
			
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACIÓN   :" + GeneracionArchivoDomiServiceImpl.class.getCanonicalName() );
			LOGGER.error( "ERROR METODO      :" + "getFileGeneratedDataF4" );
			LOGGER.error( "ERROR MENSAJE     :" + e.getMessage() );
			LOGGER.error( "ERROR DESCRIPCIÓN :" , e );
			return null;
		}finally {
			idTipoBanco		= null;
			tipoBanco 		= null;
			idTipoCuenta  	= null;
			tipoCuenta   	= null;
			message      	= null;
		}
		
		
	}
	
	private String getCatalogDescription(Short id){
		String desc = null;
		ParamCatalogoVO paramCatalogoVO = new ParamCatalogoVO();
		paramCatalogoVO.setIdCatalogo(id);
		List<CatalogoVO> result = catalogoPersistence.select(paramCatalogoVO);
		if(result!=null && !result.isEmpty()){
			CatalogoVO c = result.get(0);
			desc = /*WordUtils.capitalizeFully(*/ c.getValor()/*,new char[]{'.'} );*/;
		}
		return desc;
	}
	
	
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private void generarArchivoDomi(SolicitudFilter solicitudFilter,Date fecha,String archivo,Integer consecutivo, Short origenDomi) throws MitBusinessException{
		try{
			List<SolicitudVO> domis= null;
			//if no es domi traspaso
//			addNOConvivenToSolFilter(solicitudFilter,noConviven);
		
			domis=solicitudPersistence.selectDomiRecords(solicitudFilter);
			//FOP 2229 Obtengo el catalogo de prioridades de Diversificaciones
			List<CatPrioridadesDiversificacionesVO> catDivPrioridades = archivoDomiPersistence.selectCatPrioridadDiversificaciones();
			//Determinación del monto maximo por operación
			ParamCatalogoVO paramCatalogoVO = new ParamCatalogoVO();
			paramCatalogoVO.setIdCatalogo(new Short(ID_CAT_CATALOGO_MONTO_MAXIMO_ENVIO_BANCO));
			List<CatalogoVO> ltsImporteMax = new ArrayList<CatalogoVO>();
			ltsImporteMax = catalogosService.consultarLista(paramCatalogoVO);
			if(!ltsImporteMax.isEmpty()) {
				DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO = new BigDecimal(ltsImporteMax.get(0).getValor());
			}
				
			if(domis.isEmpty()) {
				String message = getFileGeneratedData(archivo,solicitudFilter)+" - "+"No se encontraron registros para los parámetros especificados";
				throw new Exception(message);
			}
			
			StringWriter swPar = null;
			DataFileBean dfbPar = new DataFileBean();
			StringBuffer namePar = new StringBuffer();
			Long idArchivo = insertArchivoGeneradoPar(solicitudFilter,archivo,origenDomi);
			
			if(insertCargos(domis, idArchivo, solicitudFilter.getUsuarioCreacion(), catDivPrioridades)) {
				try {
					if(ID_BANAMEX_NCI.equals(solicitudFilter.getIdTipoArchivo())){
						BanamexDomiFixLenghtFileRecordBeanPar bmxRecords =  mapVOToBanamexRecordBean(domis, consecutivo, fecha, idArchivo);
						swPar = fileGeneratorService.generateFile(bmxRecords);
						LOGGER.info(swPar.getBuffer().toString());
						namePar.append(PATH_ACHIVO_DOMI_BANAMEX);
					}else if(ID_BANCOMER_NCI.equals(solicitudFilter.getIdTipoArchivo())){
						BancomerDomiFixLenghtFileRecordBeanPar bbvaRecords = mapVOToBancomerRecordBean(domis, consecutivo, fecha, idArchivo);
						swPar = fileGeneratorService.generateFile(bbvaRecords);
						LOGGER.info(swPar.getBuffer().toString());
						namePar.append(PATH_ACHIVO_DOMI_BANCOMER);
					}
					namePar.append(archivo);
					if( swPar!=null ){
						dfbPar.setFile(new File(namePar.toString()));
						dfbPar.setStream(swPar.getBuffer().toString().getBytes());
						boolean enviadoPar = fileTransferService.sendFile(dfbPar);
						if(enviadoPar){
							updateEstatusSolicitud(domis,solicitudFilter.getUsuarioCreacion());
							updateEstatusArchivoGeneradoPar(idArchivo, ID_ESTATUS_ARCHIVO_DOMI_GENERADO,solicitudFilter.getUsuarioCreacion());
						}else {
							throw new Exception("El archivo no pudo escribirse en la ruta");
						}													
					}
				} catch (Exception e) {
					if(idArchivo > 0) {updateEstatusArchivoGeneradoPar(idArchivo, ID_ESTATUS_ARCHIVO_DOMI_CANCELADO,solicitudFilter.getUsuarioCreacion());}
				}
				
			}else {
				throw new Exception("No se pudo insertar en la Tabla de Cargos");
			}
			
		}catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION_GEN_DOMI, "",
					new Object[] {ex.getMessage()}, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}
	
	
	/*************************************************************************************************GENERA FILE *********************************************************************/
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private void generarArchivoDomiF4(SolicitudFilter solicitudFilter,Date fecha,String archivo,Integer consecutivo, Short origenDomi) throws MitBusinessException{
		
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
		
		try{
			
			System.out.println( "CONSULTA  DOMICILIACIONES  ......." );
			domis				= solicitudPersistence.selectDomiRecords(solicitudFilter);
			System.out.println( "CONSULTA  DOMICILIACIONES  ....... [OK]" );
			System.out.println( "CONSULTA  DOMICILIACIONES SIZE  ....... ["+ domis.size() +"]" );
			//FOP 2229 Obtengo el catalogo de prioridades de Diversificaciones
			System.out.println( "CONSULTA  PRIORIDAD DIVERSIFICACIONES  ......." );
			catDivPrioridades 	= archivoDomiPersistence.selectCatPrioridadDiversificaciones();
			System.out.println( "CONSULTA  PRIORIDAD DIVERSIFICACIONES  ....... [OK]" );
			System.out.println( "CONSULTA  PRIORIDAD DIVERSIFICACIONES SIZE ....... ["+ catDivPrioridades.size() +"]" );
			//Determinación del monto maximo por operación
			paramCatalogoVO 	= new ParamCatalogoVO();
			paramCatalogoVO.setIdCatalogo(new Short(ID_CAT_CATALOGO_MONTO_MAXIMO_ENVIO_BANCO));
			ltsImporteMax 		= new ArrayList<CatalogoVO>();
			System.out.println( "CONSULTA  MONTO MAXIMO POR BANCO  ......." );
			ltsImporteMax 		= catalogosService.consultarLista(paramCatalogoVO);
			System.out.println( "CONSULTA  MONTO MAXIMO POR BANCO  ....... [OK]" );
			System.out.println( "CONSULTA  MONTO MAXIMO POR BANCO  ....... ["+(( !ltsImporteMax.isEmpty() )?ltsImporteMax.get(0).getValor():"SIN DATO")+"]" );
			
			if(!ltsImporteMax.isEmpty()) {
				DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO = new BigDecimal(ltsImporteMax.get(0).getValor());
			}
				
			if(domis.isEmpty()) {
				System.out.println( "TERMINA FLUJO PORQUE NO EXISTEN REGISTROS PARA LOS PARAMETROS ESPECIFICADOS EN LA CONSULTA DE DOMICILIACIONES" );
				message = getFileGeneratedDataF4( archivo , solicitudFilter )+" - "+"No se encontraron registros para los parámetros especificados";
				throw new Exception(message);
			}
			
			swPar 			= null;
			dfbPar 			= new DataFileBean();
			namePar 		= new StringBuffer();
			idArchivo 		= insertArchivoGeneradoParF4(solicitudFilter,archivo,origenDomi); // Guardar
			
			
			
			if(insertCargosF4(domis, idArchivo, solicitudFilter.getUsuarioCreacion(), catDivPrioridades)) {
				try {
					
					System.out.println("GENERAMOS ARCHIVO F4 ");
					if(ID_BANAMEX_NCI.equals(solicitudFilter.getIdTipoArchivo())){									  
						bmxRecords =  mapVOToBanamexRecordBeanF4(domis, consecutivo, fecha, idArchivo);
						swPar = fileGeneratorService.generateFileF4(bmxRecords);
						LOGGER.info(swPar.getBuffer().toString());
						namePar.append(PATH_ACHIVO_DOMI_BANAMEX);
					}else if(ID_BANCOMER_NCI.equals(solicitudFilter.getIdTipoArchivo())){
						bbvaRecords = mapVOToBancomerRecordBeanF4(domis, consecutivo, fecha, idArchivo);
						swPar = fileGeneratorService.generateFileF4(bbvaRecords);
						LOGGER.info( swPar.getBuffer().toString() );
						namePar.append(PATH_ACHIVO_DOMI_BANCOMER);
					}
					
					System.out.println("RUTA: " + (( namePar != null )?namePar.toString():"NO HAY DATO F4"));
					System.out.println("GENERAMOS ARCHIVO F4 ... OK");
					
					namePar.append(archivo);
					if( swPar!=null ){
						dfbPar.setFile(new File(namePar.toString()));
						dfbPar.setStream(swPar.getBuffer().toString().getBytes());
						boolean enviadoPar = fileTransferService.sendFile(dfbPar);
						if(enviadoPar){
							System.out.println("REALIZAR UPDATE F4::");
							updateEstatusSolicitud(domis,solicitudFilter.getUsuarioCreacion());
							updateEstatusArchivoGeneradoPar(idArchivo, ID_ESTATUS_ARCHIVO_DOMI_GENERADO,solicitudFilter.getUsuarioCreacion());
							System.out.println("REALIZAR UPDATE F4:: FIN");
						}else {
							System.out.println("ARCHIVO NO SE ESCRIBIO F4");
							throw new Exception("El archivo no pudo escribirse en la ruta");
						}													
					}else {
						System.out.println("NO ESCRIBIRA ARCHIVO F4::");
					}
					
					System.out.println("FIN ARCHIVO F4 ::: DOMI");
					
				} catch (Exception e) {
					System.out.println("OCURRIO UNA EXCEPCIÓN DESPUES DE INSERTAR :" + e.getMessage());
					e.printStackTrace();
					if(idArchivo > 0) {updateEstatusArchivoGeneradoPar(idArchivo, ID_ESTATUS_ARCHIVO_DOMI_CANCELADO,solicitudFilter.getUsuarioCreacion());}
				}
				
			}else {
				System.out.println("OCURRIO UN ERROR AL INSERTAR LOS CARGOS");
				throw new Exception("No se pudo insertar en la Tabla de Cargos");
			}
			
		}catch(Exception ex){
			System.out.println( "ERROR UBICACIÓN    :" + GeneracionArchivoDomiServiceImpl.class.getCanonicalName() );
			System.out.println( "ERROR METODO       :" + "generarArchivoDomiF4" );
			System.out.println( "ERROR MENSAJE      :" + ex.getMessage());
			ex.printStackTrace();
			
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION_GEN_DOMI, "",
					new Object[] {ex.getMessage()}, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}finally {
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
	private Boolean insertCargos(List<SolicitudVO> domis, Long idArchivo, String usuario, List<CatPrioridadesDiversificacionesVO> catDivPrioridades) {
		Boolean rgs = Boolean.TRUE;
		long nlinea = 1;
		for(SolicitudVO sol:domis) {
			BigDecimal importe = sol.getImporte();
			List<BigDecimal> montosParciales = parcializaMonto(importe,DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO);
			LOGGER.info(montosParciales.toString());
			CargoVO cargo = convertSolicitudToCargo(sol, getFolioIdArchivo(), idArchivo, usuario);
			if(montosParciales.size() > 1) {
				cargo.setParcialidad(ID_MAS_DE_UNA_PARCIALIDAD);
			}else {
				cargo.setParcialidad(ID_UNICA_PARCIALIDAD);
			}
			try {
				Integer inserted = cargoPersistence.insertCargo(cargo);
				if(inserted > 0) {
					short numParcialidad  = 0;
					//FOP 2229 Inserta Diversificaciones
					//hay que ir por las diversificaciones
					DiversificacionVO divF = new DiversificacionVO();
					divF.setClaveSolicitud(sol.getClaveSolicitud());
					
					List<DiversificacionVO> ltsDiversificaciones = diversificacionPersistence.getDiversificacionesByCveSolicitud(divF);
					for(DiversificacionVO d: ltsDiversificaciones) {
						if(d.getPorcentaje() != null) {
							d.setIdArchivoDomi(idArchivo);
							d.setIdCargoStr(cargo.getIdCargo());
							short idDiversif = d.getIdArchivo().shortValue();
							d.setIdPrioridad(new Integer(this.getIdPrioridad(catDivPrioridades, idDiversif)));
							d.setUsuarioCreacion(usuario);
							d.setFondoAportacionVoluntaria(new GenericCatalogoVO(idDiversif));
							int diversif = diversificacionPersistence.insertarDiversificacionSolicitudDomiciliacionPar(d);
							if(diversif == 0) {
								throw new MitBusinessException(GenerateExceptionDetails.generate(
										ErrorCodeService.EX_EXCEPTION_GEN_DOMI, "Fallo en la operacion de insertarDiversificacionSolicitudDomiciliacionPar " + d.toString(),	new Object[] {}, null));
							}
						}
					}
					divF = null;
					//FOP 2229 Inserta Detalle Cargos
					for(BigDecimal monto: montosParciales) {
						cargo.setParcialidad(++ numParcialidad);
						cargo.setImporte(monto);
						cargo.setnLinea(++ nlinea);
						cargo.setIdArchivo(null);
						cargo.setIdArchivoDomi(new Long(idArchivo));
						Integer insertedDetalle = cargoPersistence.insertDetalleCargo(cargo);
						if(insertedDetalle == 0) {
							throw new MitBusinessException(GenerateExceptionDetails.generate(
									ErrorCodeService.EX_EXCEPTION_GEN_DOMI, "Fallo en la operacion de insertDetalleCargo " + cargo.toString(),	new Object[] {}, null));
						}
					}
				}else {
					throw new MitBusinessException(GenerateExceptionDetails.generate(
							ErrorCodeService.EX_EXCEPTION_GEN_DOMI, "Fallo en la operacion de insertCargo " + cargo.toString(),	new Object[] {}, null));
				}
			} catch (Exception e) {
				rgs = Boolean.FALSE;
				LOGGER.error(e.getMessage(), e);
				break;
			}
		}
		return rgs;
	}
	
	
	public List<Object> segementarPorHilos( List<SolicitudVO> domisIN , int numHilos ) {
		
		List<SolicitudVO> domi  = null;
		List<Object> listas		= null;
		
		try {
			
			listas	= new ArrayList<Object>();
			while( !domisIN.isEmpty() ) {
				
				domi = new ArrayList<SolicitudVO>();
				
				for( int i = 0; i<= numHilos; i++ ) {
					
					if( domisIN.isEmpty() ) {
						break;
					}else {
						domi.add( domisIN.get(0) );
						domisIN.remove(0);
					}
				}
				listas.add(domi);
			}
			return listas;
		}catch( Exception e ) {
			System.out.println( "ERROR UBICACIÓN    :" + GeneracionArchivoDomiServiceImpl.class.getCanonicalName());
			System.out.println( "ERROR METODO       :" + "segmentarPorHilos" );
			System.out.println( "ERROR MENSAJE      :" + e.getMessage() );
			e.printStackTrace();
			return null;
		}finally {
			domi   = null;
			listas = null;
		}
		
		
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private Boolean insertCargosF4(List<SolicitudVO> domis, Long idArchivo, String usuario, List<CatPrioridadesDiversificacionesVO> catDivPrioridades) {
		
		System.out.println("VERSION F4 HILOS.");
		Boolean rgs 						= Boolean.TRUE;
		List<Object> particionesHilo		= null;
		int numHilos						= 5;
		boolean estatusSinTerminar			= Boolean.TRUE;
		List<SolicitudVO> domiAux			= null;
		
	    //List<Hilo> hilos					 = null;
		
		List<SolicitudVO> domisClon			= new ArrayList<SolicitudVO>();
		
		try {
			
			domisClon.addAll(domis);
			particionesHilo = segementarPorHilos(domisClon, numHilos);
			System.out.println("Particiones :::" + particionesHilo.size());
			
			int numCargos = 0;
			int iteracionHilos = 0;
			
			for( Object iterador : particionesHilo ) {
				iteracionHilos ++;
				//hilos			    = new ArrayList<Hilo>();
				estatusSinTerminar	= Boolean.TRUE;
				domiAux				= ( ArrayList<SolicitudVO> ) iterador;
				
				//CREANDO HILOS
				for( int i = 0; i<= ControlHilos.numHilos; i++ ) {
					numCargos++;
					if( i < domiAux.size() ) {
						hilo.execute( i +"", domiAux.get(i), idArchivo , usuario , catDivPrioridades );
						ControlHilos.hilosTerminados[i]	    = "0";
						ControlHilos.hilosEstatus[i]		= "0";
						System.out.println("CREANDO HILOS  ITERACIÓN ["+ iteracionHilos +"] NUMCARGO["+numCargos+"]....");
					}else {
						ControlHilos.hilosTerminados[i] = "1";
						ControlHilos.hilosEstatus[i]    = "1";
					}
				}
				
				
				
//				//INICIANDO HILOS
//				System.out.println("INICIANDO HILOS  ....");
//				for (int i = 0; i <= numHilos; i++) {
//					if (i < domiAux.size()) {
//						hilos.get(i).start();
//					}
//                }
//				System.out.println("INICIANDO HILOS  ....[OK]");
				
				//MONITOR DE HILOS
				while( estatusSinTerminar ) {
					estatusSinTerminar = false;
					
					//System.out.println("");
					//System.out.print("ESTATUS HILO"); 
					//for( int i = 0; i < ControlHilos.hilosTerminados.length; i++ ) {
					//	System.out.print("["+ControlHilos.hilosTerminados[i]+"]");
					//}
					
					for( int i = 0; i < ControlHilos.hilosTerminados.length; i++ ) {
						if( ControlHilos.hilosTerminados[i].equals("0")  ) {
							estatusSinTerminar = true;
						}
						
						if(ControlHilos.errorHilo) {
							estatusSinTerminar = false;
							System.out.println("---> OCURRIO UN ERROR EN UN HILO" + ControlHilos.msgError);
						}
						//System.out.println("ESTATUS ERROR HILO :::::::::::::::::::::%%%%%%%%%%%%%%% " + "["+ ControlHilos.errorHilo +"]" + ControlHilos.msgError  );
					}
				}
				
				//VALIDAMOS SI EXISTIO UN ERROR EN ALGUN HILO
				if( ControlHilos.errorHilo ) {
					System.out.println("OCURRIO UN ERROR EN UN HILO :" + ControlHilos.msgError);
					rgs = Boolean.FALSE;
					break;
				}else {
					System.out.println("#################################################################");
					System.out.println("#################   TERMINAMOS ITERACION    #####################");
					System.out.println("#################################################################");
					for( int i = 0; i < ControlHilos.hilosTerminados.length; i++ ) {
						System.out.print("HILO ESTATUS FIN ["+ControlHilos.hilosTerminados[i]+"]");
					}
				}
			}
			
			return rgs;
		}catch( Exception e ) {
			rgs = Boolean.FALSE;
			System.out.println("ERROR UBICACIÓN    :" + GeneracionArchivoDomiServiceImpl.class.getCanonicalName());
			System.out.println("ERROR METODO       :" + "insertCargosF4");
			System.out.println("ERROR MENSAJE      :" + e.getMessage());
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
			return rgs;
		}finally {
			rgs 						= null;
		}
		
	}
	
	
	public String generaFolioDB(  ) {
		GeneraFolioFilter generaFolio = null;
		try {
			generaFolio = new GeneraFolioFilter();
			generaFolio.setIdProceso(Integer.parseInt( ConstantesCatalogos.ID_PROCESO_REGISTRO + "") );
			generaFolio.setIdSubproceso(Integer.parseInt( ConstantesCatalogos.ID_SUBPROCESO_REGISTRO_NORMAL + ""));
			generaFolioPersistence.generaFolio(generaFolio);
			return generaFolio.getFolio();
		}catch( Exception e ) {
			System.out.println("ERROR AL GENERAR FOLIO ::");
			e.printStackTrace();
			return null;
		}finally {
			generaFolio = null;
		}
		
	}
	
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private Boolean insertCargosF3(List<SolicitudVO> domis, Long idArchivo, String usuario, List<CatPrioridadesDiversificacionesVO> catDivPrioridades) {
		
		CargoVO cargo 									= null;
		List<BigDecimal> montosParciales 				= null;
		BigDecimal importe 								= null;
		DiversificacionVO divF 							= null;
		List<DiversificacionVO> ltsDiversificaciones 	= null;
		Boolean rgs 						= Boolean.TRUE;
		long nlinea 						= 1;
		int numRegs							= 0;
		int divercifica						= 0;
		int detCargo						= 0;
		try {
			System.out.println( "INSERTAMOS CARGOS DE DOMICILIACION      ........." );
			System.out.println( "INSERTAMOS CARGOS DE DOMICILIACION SIZE ......... ["+ domis.size() +"] " );
			for(SolicitudVO sol:domis) {
				numRegs = numRegs + 1;
				importe 		= sol.getImporte();
				System.out.println( "GENERANDO LOTES PARA EL REGISTRO ["+numRegs+"]  ####################################" );
				montosParciales = parcializaMontoF4(importe,DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO);
				System.out.println( "LOTES GENERADOS .... " + montosParciales.size() );
				System.out.println(montosParciales.toString());
				cargo = convertSolicitudToCargoF4(sol, getFolioIdArchivoF4(), idArchivo, usuario);
				
				if(montosParciales.size() > 1) {
					cargo.setParcialidad(ID_MAS_DE_UNA_PARCIALIDAD);
				}else {
					cargo.setParcialidad(ID_UNICA_PARCIALIDAD);
				}
				
				try {
					System.out.println( "INSERTAMOS CARGO ......................." );
					if( cargoPersistence.insertCargo(cargo).intValue() > 0) {
						System.out.println( "INSERTAMOS CARGO ....................... [OK]" );
						short numParcialidad  = 0;
						//FOP 2229 Inserta Diversificaciones
						//hay que ir por las diversificaciones
						divF = new DiversificacionVO();
						divF.setClaveSolicitud(sol.getClaveSolicitud());
						System.out.println( "CONSULTAMOS DIVERSIFICACIONES DE SOLICITUD ......................." );
						ltsDiversificaciones = diversificacionPersistence.getDiversificacionesByCveSolicitud(divF);
						System.out.println( "CONSULTAMOS DIVERSIFICACIONES DE SOLICITUD ....................... [OK] " );
						System.out.println( "CONSULTAMOS DIVERSIFICACIONES DE SOLICITUD SIZE ....................... ["+ltsDiversificaciones.size()+"] " );
						System.out.println( "INSERTAMOS DIVERSIFICACIONES DE SOLICITUD ......................");
						divercifica = 0;
						for(DiversificacionVO d: ltsDiversificaciones) {
							
							if(d.getPorcentaje() != null) {
								
								d.setIdArchivoDomi(idArchivo);
								d.setIdCargoStr(cargo.getIdCargo());
								short idDiversif = d.getIdArchivo().shortValue();
								d.setIdPrioridad(new Integer(this.getIdPrioridad(catDivPrioridades, idDiversif)));
								d.setUsuarioCreacion(usuario);
								d.setFondoAportacionVoluntaria(new GenericCatalogoVO(idDiversif));
								if( diversificacionPersistence.insertarDiversificacionSolicitudDomiciliacionPar(d) == 0 ) {
									System.out.println( "INSERTAMOS DIVERSIFICACIONES DE SOLICITUD ......................[ERROR]");
									System.out.println( "DIVERSIFICACIÓN  DETALLE ... "+ d.toString() );
									throw new MitBusinessException(GenerateExceptionDetails.generate(
											ErrorCodeService.EX_EXCEPTION_GEN_DOMI, "Fallo en la operacion de insertarDiversificacionSolicitudDomiciliacionPar " + d.toString(),	new Object[] {}, null));
								}
								divercifica = divercifica +1;
								if( divercifica % 100 == 0 ) {
									System.out.println("PROCESADAS .....  ["+ divercifica +"]" );
								}
							}
						}
						System.out.println( "INSERTAMOS DIVERSIFICACIONES DE SOLICITUD             ......................[OK]");
						System.out.println( "INSERTAMOS DIVERSIFICACIONES DE SOLICITUD INSERTADAS  ......................["+ divercifica +"]");
						divF = null;
						//FOP 2229 Inserta Detalle Cargos
						
						System.out.println( "INSERTAMOS DETALLES DE CARGO ......................");
						for(BigDecimal monto: montosParciales) {
							cargo.setParcialidad(++ numParcialidad);
							cargo.setImporte(monto);
							cargo.setnLinea(++ nlinea);
							cargo.setIdArchivo(null);
							cargo.setIdArchivoDomi(new Long(idArchivo));
							if(cargoPersistence.insertDetalleCargo(cargo).intValue() == 0) {
								System.out.println( "INSERTAMOS DETALLES DE CARGO ......................[ERROR]");
								System.out.println("DETALLE DEL CARGO ::" + cargo.toString());
								throw new MitBusinessException(GenerateExceptionDetails.generate(
										ErrorCodeService.EX_EXCEPTION_GEN_DOMI, "Fallo en la operacion de insertDetalleCargo " + cargo.toString(),	new Object[] {}, null));
							}
							detCargo = detCargo +1;
							if( detCargo % 100 == 0 ) {
								System.out.println("PROCESADAS .....  ["+ detCargo +"]" );
							}
						}
						System.out.println( "INSERTAMOS DETALLES DE CARGO ......................[OK]");
						System.out.println( "INSERTAMOS DETALLES DE CARGO INSERTADAS ................["+ detCargo +"]");
					}else {
						System.out.println( "Fallo en la operacion de insertCargo" );
						throw new MitBusinessException(GenerateExceptionDetails.generate(
								ErrorCodeService.EX_EXCEPTION_GEN_DOMI, "Fallo en la operacion de insertCargo " + cargo.toString(),	new Object[] {}, null));
					}
				} catch (Exception e) {
					rgs = Boolean.FALSE;
					System.out.println("ERROR AL INSERTAR CARGO :: DETALLE  " + e.getMessage());
					LOGGER.error(e.getMessage(), e);
					break;
				}
			}
			
			System.out.println("TERMINAMOS DE INSERTAR REGISTROS F4");
			return rgs;
		}catch( Exception e ) {
			rgs = Boolean.FALSE;
			System.out.println("ERROR UBICACIÓN    :" + GeneracionArchivoDomiServiceImpl.class.getCanonicalName());
			System.out.println("ERROR METODO       :" + "insertCargosF4");
			System.out.println("ERROR MENSAJE      :" + e.getMessage());
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
			return rgs;
		}finally {
			cargo 						= null;
			montosParciales 			= null;
			importe 					= null;
			divF 						= null;
			ltsDiversificaciones 		= null;
			rgs 						= null;
			nlinea 						= 0;
		}
		
	}
	
	private Short getIdPrioridad(List<CatPrioridadesDiversificacionesVO> catDivPrioridades, Short idAportacion) {
		Short rgs = 0;
		for (CatPrioridadesDiversificacionesVO cat : catDivPrioridades) {
			if(cat.getIdCatCatalogo().equals(idAportacion)) {
				rgs = cat.getIdPrioridad();
				break;
			}
		}
		return rgs;
	}
	
	private String getFolioIdArchivoF4() {
		
		GeneraFolioFilter generaFolio = null;
		
		try {
			generaFolio = new GeneraFolioFilter();
			generaFolio.setIdProceso(Integer.parseInt( ConstantesCatalogos.ID_PROCESO_REGISTRO + "") );
			generaFolio.setIdSubproceso(Integer.parseInt( ConstantesCatalogos.ID_SUBPROCESO_REGISTRO_NORMAL + ""));
			generaFolioPersistence.generaFolio(generaFolio);
			return generaFolio.getFolio();
		} catch (Exception e) {
			System.out.println("ERROR UBICACIÓN  :" + GeneracionArchivoDomiServiceImpl.class.getCanonicalName());
			System.out.println("ERROR METODO     :" + "getFolioIdArchivo");
			System.out.println("ERROR MENSAJE    :" + e.getMessage());
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	private String getFolioIdArchivo() {
		//Genero folio unico
		FolioPortType portFolio;
		String rgs = "";
		try {
			portFolio = wsPortTypeFactory.folioPortType();
			GenerarFolioReq parameters = new GenerarFolioReq();
			parameters.setIdProceso(ConstantesCatalogos.ID_PROCESO_REGISTRO);
			parameters.setIdSubproceso(ConstantesCatalogos.ID_SUBPROCESO_REGISTRO_NORMAL);
			GenerarFolioResp response=portFolio.generarFolio(parameters);
			rgs = response.getFolio();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR UBICACIÓN  :" + GeneracionArchivoDomiServiceImpl.class.getCanonicalName());
			System.out.println("ERROR METODO     :" + "getFolioIdArchivo");
			System.out.println("ERROR MENSAJE    :" + e.getMessage());
			e.printStackTrace();
		}
		return rgs;
		
	}
	
	private CargoVO convertSolicitudToCargo(SolicitudVO s, String  idCargo, Long idArchivo, String usuario) {
		
			CargoVO c = null;
			
			if(s != null) {
				c = new CargoVO();
				c.setIdCargo(idCargo);
				c.setClaveSolicitud(s.getClaveSolicitud());
				c.setNumCuentaIndividual(s.getNumCuentaIndividual());
				c.setImporte(s.getImporte());
				c.setImporteRecaudado(new BigDecimal(0));
				c.setFolioArchivo(null);
				c.setEstatusCargo(new GenericCatalogoVO(ID_ESTATUS_CARGO_GENERADO));
				c.setFechaCargoBanco(null);
				c.setMotivoRechazo(null);
				//Hay que identificar si es 1 cuando es parcialidad
				//c.setParcialidad((short)(DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO.compareTo(s.getImporte()) < 0?1:0));
				c.setIdArchivoDomi(new Long(idArchivo));
				c.setFechaGeneracionCargo(null);
				c.setReIntentos(null);
				c.setFechaProximoCargo(s.getFechaProximoCargo());
				c.setFechaCreacion(new Date());
				c.setUsuarioCreacion(usuario);
				c.setFechaActualizacion(null);
				c.setUsuarioActualizacion(null);
			}
			return c;
		
	}
	
	private CargoVO convertSolicitudToCargoF4(SolicitudVO s, String  idCargo, Long idArchivo, String usuario) {
		
		CargoVO c = null;
		
		try {
			System.out.println("Convirtiendo objeto .... ");
			if(s != null) {
				c = new CargoVO();
				c.setIdCargo(idCargo);
				c.setClaveSolicitud(s.getClaveSolicitud());
				c.setNumCuentaIndividual(s.getNumCuentaIndividual());
				c.setImporte(s.getImporte());
				c.setImporteRecaudado(new BigDecimal(0));
				c.setFolioArchivo(null);
				c.setEstatusCargo(new GenericCatalogoVO(ID_ESTATUS_CARGO_GENERADO));
				c.setFechaCargoBanco(null);
				c.setMotivoRechazo(null);
				//Hay que identificar si es 1 cuando es parcialidad
				//c.setParcialidad((short)(DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO.compareTo(s.getImporte()) < 0?1:0));
				c.setIdArchivoDomi(new Long(idArchivo));
				c.setFechaGeneracionCargo(null);
				c.setReIntentos(null);
				c.setFechaProximoCargo(s.getFechaProximoCargo());
				c.setFechaCreacion(new Date());
				c.setUsuarioCreacion(usuario);
				c.setFechaActualizacion(null);
				c.setUsuarioActualizacion(null);
			}
			
			System.out.println("Convirtiendo objeto .... [OK]");
			return c;
			
		}catch( Exception e ) {
			System.out.println("Convirtiendo objeto .... [ERROR]");
			System.out.println( "ERROR UBICACIÓN     :" + GeneracionArchivoDomiServiceImpl.class.getCanonicalName() );
			System.out.println( "ERROR METODO        :" + "convertSolicitudToCargoF4" );
			System.out.println( "ERROR MENSAJE       :" + e.getMessage() );
			System.out.println(  e );
			return null;
		}finally {
			c = null;
		}
	}
	
	
	@Transactional(rollbackFor = MitBusinessException.class, propagation = Propagation.REQUIRED)
	private void insertArchivoGenerado(SolicitudFilter filter, String nombreArchivo, Short origen) throws MitBusinessException{
		try{
			ArchivosGeneradosVO archivoGen= new ArchivosGeneradosVO();
			archivoGen.setClaveBanco(new GenericCatalogoVO(filter.getIdTipoArchivo()));
			archivoGen.setEstatus(new GenericCatalogoVO(ID_ESTATUS_ARCHIVO_DOMI_GENERADO));
			archivoGen.setFechaFin(filter.getFechaFin());
			archivoGen.setFechaInicio(filter.getFechaInicio());
			archivoGen.setNombreArchivo(nombreArchivo);
			archivoGen.setUsuario(filter.getUsuarioCreacion());
			int status = archivoDomiPersistence.insert(archivoGen);
			if(status==1 ){
				if(!filter.getFlagOtrosBancos()){
					for(Short idBanco:filter.getIdsBancos()){
						for(Short idTipoCuenta:filter.getIdsTiposCuenta()){
							DetalleArchivoDomiVO detalle = new DetalleArchivoDomiVO();
							detalle.setTipoCuenta(new GenericCatalogoVO(idTipoCuenta));
							detalle.setClaveBanco(new GenericCatalogoVO(idBanco));
							detalle.setIdArchivo(archivoGen.getIdArchivo());
							detalle.setIdOrigen(origen);
							detalleArchivoDomiPersistence.insertDetalle(detalle);
						}
					}
				}else{
					for(Short idTipoCuenta:filter.getIdsTiposCuenta()){
						DetalleArchivoDomiVO detalle = new DetalleArchivoDomiVO();
						detalle.setTipoCuenta(new GenericCatalogoVO(idTipoCuenta));
						detalle.setClaveBanco(new GenericCatalogoVO(ID_OTROS_BANCOS_DOMI_FILE));
						detalle.setIdArchivo(archivoGen.getIdArchivo());
						detalle.setIdOrigen(origen);
						detalleArchivoDomiPersistence.insertDetalle(detalle);
					}
				}
			}
		}catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al generar archivo de domiciliacion.Insertar registro de archivo generado"+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "insertArchivoGenerado()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
	}
	

	@Transactional(rollbackFor = MitBusinessException.class, propagation = Propagation.REQUIRED)
	private Long insertArchivoGeneradoPar(SolicitudFilter filter, String nombreArchivo, Short origen) throws MitBusinessException{
		ArchivosGeneradosVO archivoGen= new ArchivosGeneradosVO(new Long("0"));
		try{
			archivoGen.setClaveBanco(new GenericCatalogoVO(filter.getIdTipoArchivo()));
			archivoGen.setEstatus(new GenericCatalogoVO(ID_ESTATUS_ARCHIVO_DOMI_EN_PROCESO));
			archivoGen.setFechaFin(filter.getFechaFin());
			archivoGen.setFechaInicio(filter.getFechaInicio());
			archivoGen.setNombreArchivo(nombreArchivo);
			archivoGen.setUsuario(filter.getUsuarioCreacion());
			int status = archivoDomiPersistence.insertPar(archivoGen);
			if(status==1 ){
				if(!filter.getFlagOtrosBancos()){
					for(Short idBanco:filter.getIdsBancos()){
						for(Short idTipoCuenta:filter.getIdsTiposCuenta()){
							DetalleArchivoDomiVO detalle = new DetalleArchivoDomiVO();
							detalle.setTipoCuenta(new GenericCatalogoVO(idTipoCuenta));
							detalle.setClaveBanco(new GenericCatalogoVO(idBanco));
							detalle.setIdArchivo(archivoGen.getIdArchivo());
							detalle.setIdOrigen(origen);
							detalleArchivoDomiPersistence.insertDetallePar(detalle);
						}
					}
				}else{
					for(Short idTipoCuenta:filter.getIdsTiposCuenta()){
						DetalleArchivoDomiVO detalle = new DetalleArchivoDomiVO();
						detalle.setTipoCuenta(new GenericCatalogoVO(idTipoCuenta));
						detalle.setClaveBanco(new GenericCatalogoVO(ID_OTROS_BANCOS_DOMI_FILE));
						detalle.setIdArchivo(archivoGen.getIdArchivo());
						detalle.setIdOrigen(origen);
						detalleArchivoDomiPersistence.insertDetallePar(detalle);
					}
				}
			}
		}catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al generar archivo de domiciliacion.Insertar registro de archivo generado"+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "insertArchivoGenerado()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
		return archivoGen.getIdArchivo();
	}
	
	@Transactional(rollbackFor = MitBusinessException.class, propagation = Propagation.REQUIRED)
	private Long insertArchivoGeneradoParF4(SolicitudFilter filter, String nombreArchivo, Short origen) throws MitBusinessException{
		
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
			if(status==1 ){
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
		}catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al generar archivo de domiciliacion.Insertar registro de archivo generado"+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "insertArchivoGenerado()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}finally {
			archivoGen			= null;
			detalle 			= null;
		}
		
	}
	
	@Transactional(rollbackFor = MitBusinessException.class, propagation = Propagation.REQUIRED)
	private Integer updateEstatusArchivoGenerado(Long idArchivo, Short idEstatus,String usuario) throws MitBusinessException{
		Integer updated= 0;
		try {
			ArchivosGeneradosVO archivoGen= new ArchivosGeneradosVO();
			archivoGen.setEstatus(new GenericCatalogoVO(idEstatus));//
			archivoGen.setIdArchivo(idArchivo);
			archivoGen.setUsuario(usuario);
			updated = archivoDomiPersistence.update(archivoGen);
		}catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al generar archivo de archivoDomiPersistence.update registro de archivo generado"+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "updateEstatusArchivoGenerado()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
		return updated;
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
		}
	}
	
	public List<SolicitudSumTotalVO> consultarDomiResumen(DomiParameterMapFilter filter) throws MitBusinessException  {
		List<SolicitudSumTotalVO> res = null;
		try {
			List<SolicitudFilter> l = getCustomFilter(filter,SolicitudFilterType.CIFRAS_TOTALES);
			if( l!=null && !l.isEmpty() ){
				if( l.size() == 1 ){
					SolicitudFilter f = l.get(0);
					//if no es domi traspaso
//					addNOConvivenToSolFilter(f,noConviven);
					res= solicitudPersistence.selectDomiResumenTotal(f);
				}else throw new Exception("Se encontro mas de un filtro para el calculo de las cifras resumen");
			}else throw new Exception("No se encontro un filtro correcto para el calculo de las cifras resumen");
			return res;
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al Consultar Solicitudes ",
					new Object[] { getClass().getSimpleName(), "consultarDomiResumen()" }, ex));
	
			LOGGER.error(mitBusinessException.getMessage(), ex);
	
			throw mitBusinessException;
		}	
	}
	
	public boolean cancelarCuentasNoConviven(List<SolicitudVO> noConviven)throws MitBusinessException {
		int result;
		if(noConviven!=null && !noConviven.isEmpty()){
			for(int i=0;i<noConviven.size();i++){
				result= solicitudPersistence.cancelaSolicitud(noConviven.get(i));
			}
		}
		return true;
	}
	
	public Integer updateEstatusTramiteAportaciones(SolicitudVO solicitudVO) throws MitBusinessException{
		return solicitudPersistence.updateEstatusTramiteAportaciones(solicitudVO);
	}
	
	//FOP | 2314 | 12/03/2018
	public Boolean reactivarDomicializacion(SolicitudVO solicitud) throws MitBusinessException{
		Boolean rgs = Boolean.FALSE;
		int result = solicitudPersistence.reactivarDomicializacionUpdate(solicitud);
		if(result > 0) {
			rgs = Boolean.TRUE;
		}
		return rgs;
	}	
	
	
	public List<SolicitudSumTotalPorBancoVO> consultarDomiResumenPorBanco(DomiParameterMapFilter filter)throws MitBusinessException {
		List<SolicitudSumTotalPorBancoVO> res= new ArrayList<SolicitudSumTotalPorBancoVO>();
		try{
			List<SolicitudFilter> l = getCustomFilter(filter,SolicitudFilterType.CIFRAS_POR_BANCO);
			for(SolicitudFilter f:l){
				//if no es domi traspaso
//				addNOConvivenToSolFilter(f,noConviven);
				res.addAll( solicitudPersistence.selectDomiResumenPorBanco(f));
			}
			return res;
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al Consultar Cifras por Banco "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "consultarDomiResumenPorBanco()" }, ex));
	
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
	}

	private BancomerDomiFixLenghtFileRecordBean  mapVOToBancomerRecordBean(List<SolicitudVO> solicitudes,Integer consecutivo,Date fecha){
		Integer numSeq = 0;
		BancomerDomiFixLenghtFileRecordBean bean = new BancomerDomiFixLenghtFileRecordBean();
		//Header 
		BancomerDomiHeaderRecordBean header = new BancomerDomiHeaderRecordBean();
		header.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		header.setConsecutivo(consecutivo);
		header.setNumSecuencia(++numSeq);
		header.setFechaPresentacion(fecha);
		
		bean.setHeader(header);
		
		bean.setRecords(new ArrayList<BancomerDomiRecordRecordBean>());
		BigDecimal total = new BigDecimal(0);
		for(SolicitudVO sol :solicitudes){
			BancomerDomiRecordRecordBean record = new BancomerDomiRecordRecordBean();
			try{
				record.setBancoReceptorClienteUsuario(sol.getClaveBanco().getId().intValue());
				record.setFechaLiquidacion(fecha);
				record.setFechaVencimiento(fecha);
				String nombreCompleto = (sol.getClienteNombre()!=null?sol.getClienteNombre():"")+' '
						+(sol.getClientePaterno()!=null?sol.getClientePaterno():"")+' '
						+(sol.getClienteMaterno()!=null?sol.getClienteMaterno():"");
				record.setNombreClienteUsuario(cleanSpecialCaracters(nombreCompleto));
				record.setNombreTitularServicio(cleanSpecialCaracters(nombreCompleto));
				record.setNumCuentaClienteUsuario(new BigInteger(sol.getCuentaBanco()));
//				record.setReferenciaNumericaEmisor(referenciaNumericaEmisor);
				record.setReferenciaServicioEmisor(sol.getNumCuentaIndividual().toString());
				sol.setNumCargo(sol.getNumCargo()!=null ? sol.getNumCargo() + 1 : 1);
				record.setNumDomiciliacion(sol.getNumCargo());
				record.setClaveSolicitud(sol.getClaveSolicitud());
				record.setTipoCuentaClienteUsuario(sol.getTipoCuenta().getId().intValue());
				record.setImporteOperacion(sol.getImporte());
				total=total.add(sol.getImporte());
				record.setNumSecuencia(++numSeq);
				bean.getRecords().add(record);
			}catch(Exception e){
				LOGGER.warn("La solicitud "+sol.getClaveSolicitud()+" no se incluye en el archivo",e);
			}
		}
		
		BancomerDomiFooterRecordBean footer = new BancomerDomiFooterRecordBean();
		footer.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		footer.setConsecutivo(consecutivo);
		footer.setNumeroOperaciones(numSeq-1);//Total de registros = numSeq -1 (Encabezado)
		footer.setNumeroSecuencia(++numSeq);
		footer.setImporteTotalOperaciones(total);
		bean.setFooter(footer);
		return bean;
	}
	
	
	
	//FOP 2229 Nuevos metodos
	private BanamexDomiFixLenghtFileRecordBeanPar  mapVOToBanamexRecordBeanF4(List<SolicitudVO> solicitudes,Integer consecutivo, Date fecha, Long idArchivo){
			
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
						records = getBanamexRecordsF4(sol,fecha);
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
				LOGGER.error( "ERROR UBICACIÓN     :" + GeneracionArchivoDomiServiceImpl.class.getCanonicalName() );
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
	
	
	
	//FOP 2229 Nuevos metodos
	private BanamexDomiFixLenghtFileRecordBeanPar  mapVOToBanamexRecordBean(List<SolicitudVO> solicitudes,Integer consecutivo, Date fecha, Long idArchivo){
		Integer numSeq=0;
		BanamexDomiFixLenghtFileRecordBeanPar bean = new BanamexDomiFixLenghtFileRecordBeanPar();
		//Header 
		BanamexDomiHeaderRecordBeanPar header = new BanamexDomiHeaderRecordBeanPar();
		header.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		header.setConsecutivo(consecutivo);
		header.setNumSecuencia(++numSeq);
		header.setFechaPresentacion(fecha);
		
		bean.setHeader(header);
		
		bean.setRecords(new ArrayList<BanamexDomiRecordRecordBeanPar>());
		BigDecimal total = new BigDecimal(0);
		for(SolicitudVO sol :solicitudes){
			try{
				List<BanamexDomiRecordRecordBeanPar> records = getBanamexRecords(sol,fecha);
				for(BanamexDomiRecordRecordBeanPar record:records) {
					total=total.add(record.getImporteOperacion());				
					record.setNumSecuencia(++numSeq);
					bean.getRecords().add(record);
				}
			}catch(Exception e){
				LOGGER.warn("La solicitud "+sol.getClaveSolicitud()+" no se incluye en el archivo:"+e.getMessage(),e);
			}
		}
		
		BanamexDomiFooterRecordBean footer = new BanamexDomiFooterRecordBean();
		footer.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		footer.setConsecutivo(consecutivo);
		footer.setNumeroOperaciones(numSeq-1);//Total de registros = numSeq -1 (Encabezado)
		footer.setNumeroSecuencia(++numSeq);
		footer.setImporteTotalOperaciones(total);
		bean.setFooter(footer);
		return bean;
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
					List<BancomerDomiRecordRecordBeanPar> records= getBancomerRecordsF4(sol, fecha);
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
			LOGGER.error( "ERROR UBICACIÓN     :" + GeneracionArchivoDomiServiceImpl.class.getCanonicalName() );
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
	
	private BancomerDomiFixLenghtFileRecordBeanPar  mapVOToBancomerRecordBean(List<SolicitudVO> solicitudes,Integer consecutivo,Date fecha, Long idArchivo){
		Integer numSeq = 0;
		BancomerDomiFixLenghtFileRecordBeanPar bean = new BancomerDomiFixLenghtFileRecordBeanPar();
		//Header 
		BancomerDomiHeaderRecordBeanPar header = new BancomerDomiHeaderRecordBeanPar();
		header.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		header.setConsecutivo(consecutivo);
		header.setNumSecuencia(++numSeq);
		header.setFechaPresentacion(fecha);
		
		bean.setHeader(header);
		
		bean.setRecords(new ArrayList<BancomerDomiRecordRecordBeanPar>());
		BigDecimal total = new BigDecimal(0);
		for(SolicitudVO sol :solicitudes){
			try{
				List<BancomerDomiRecordRecordBeanPar> records= getBancomerRecords(sol, fecha);
				for(BancomerDomiRecordRecordBeanPar record:records) {
					total=total.add(record.getImporteOperacion());
					record.setNumSecuencia(++numSeq);
					bean.getRecords().add(record);
				}
			}catch(Exception e){
				LOGGER.warn("La solicitud "+sol.getClaveSolicitud()+" no se incluye en el archivo:"+e.getMessage(),e);
			}
		}
		
		BancomerDomiFooterRecordBean footer = new BancomerDomiFooterRecordBean();
		footer.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		footer.setConsecutivo(consecutivo);
		footer.setNumeroOperaciones(numSeq-1);//Total de registros = numSeq -1 (Encabezado)
		footer.setNumeroSecuencia(++numSeq);
		footer.setImporteTotalOperaciones(total);
		bean.setFooter(footer);
		return bean;
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
			LOGGER.error( "ERROR UBICACIÓN     :" + GeneracionArchivoDomiServiceImpl.class.getCanonicalName() );
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
	
	private List<BancomerDomiRecordRecordBeanPar> getBancomerRecords(SolicitudVO sol, Date fecha){
		List<BancomerDomiRecordRecordBeanPar> records = new ArrayList<BancomerDomiRecordRecordBeanPar>();
		//if(sol.getCargos() != null && !sol.getCargos().isEmpty()) {
			BigDecimal importe = sol.getImporte();
			List<BigDecimal> montosParciales = parcializaMonto(importe, Constantes.DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO);
			int numParcialidad  = 0;
			for(BigDecimal monto: montosParciales) {
				BancomerDomiRecordRecordBeanPar record = new BancomerDomiRecordRecordBeanPar();
				//FOP 2229 
				//record.setIdCargo(sol.getCargos().get(0).getIdCargo());
				record.setIdCargo(12);
				record.setNumParcialidad(++numParcialidad);
				record.setBancoReceptorClienteUsuario(sol.getClaveBanco().getId().intValue());
				record.setFechaLiquidacion(fecha);
				record.setFechaVencimiento(fecha);
				String nombreCompleto = (sol.getClienteNombre()!=null?sol.getClienteNombre():"")+' '
						+(sol.getClientePaterno()!=null?sol.getClientePaterno():"")+' '
						+(sol.getClienteMaterno()!=null?sol.getClienteMaterno():"");
				record.setNombreClienteUsuario(cleanSpecialCaracters(nombreCompleto));
				record.setNombreTitularServicio(cleanSpecialCaracters(nombreCompleto));
				record.setNumCuentaClienteUsuario(new BigInteger(sol.getCuentaBanco()));
		//		record.setReferenciaNumericaEmisor(referenciaNumericaEmisor);
				record.setReferenciaServicioEmisor(sol.getNumCuentaIndividual().toString());
				sol.setNumCargo(sol.getNumCargo()!=null ? sol.getNumCargo() + 1 : 1);
				record.setNumDomiciliacion(sol.getNumCargo());
				record.setClaveSolicitud(sol.getClaveSolicitud());
				record.setTipoCuentaClienteUsuario(sol.getTipoCuenta().getId().intValue());
				record.setImporteOperacion(monto);
				records.add(record);
			}
		//}
		return records;
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
			montosParciales = parcializaMontoF4(importe, Constantes.DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO);
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
				record.setNombreClienteUsuario(cleanSpecialCaracters(nombreCompleto));
				record.setNombreTitularServicio(cleanSpecialCaracters(nombreCompleto));
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
			LOGGER.error( "ERROR UBICACIÓN    :" + GeneracionArchivoDomiServiceImpl.class.getCanonicalName() );
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
	
	private List<BanamexDomiRecordRecordBeanPar> getBanamexRecords(SolicitudVO sol, Date fecha) {
		List<BanamexDomiRecordRecordBeanPar> records = new ArrayList<BanamexDomiRecordRecordBeanPar>();
		//if(sol.getCargos()!= null && !sol.getCargos().isEmpty()) {
			BigDecimal importe = sol.getImporte();
			List<BigDecimal> montosParciales = parcializaMonto(importe, Constantes.DOMICILIACIONES_MONTO_MAXIMO_ENVIO_BANCO);
			int numParcialidad = 0 ;
			for(BigDecimal monto: montosParciales) {
				BanamexDomiRecordRecordBeanPar record = new BanamexDomiRecordRecordBeanPar();
				//record.setIdCargo(sol.getCargos().get(0).getIdCargo());
				record.setIdCargo(12);
				record.setNumParcialidad(++numParcialidad);
				record.setBancoReceptorClienteUsuario(sol.getClaveBanco().getId().intValue());
				record.setFechaLiquidacion(fecha);
				record.setFechaVencimiento(fecha);
				String nombreCompleto = (sol.getClienteNombre()!=null?sol.getClienteNombre():"")+' '
										+(sol.getClientePaterno()!=null?sol.getClientePaterno():"")+' '
										+(sol.getClienteMaterno()!=null?sol.getClienteMaterno():"");
				record.setNombreClienteUsuario(cleanSpecialCaracters(nombreCompleto));
				record.setNombreTitularServicio(cleanSpecialCaracters(nombreCompleto));
				record.setNumCuentaClienteUsuario(new BigInteger(sol.getCuentaBanco()));
		//		record.setReferenciaNumericaEmisor(referenciaNumericaEmisor);
				record.setReferenciaServicioEmisor(sol.getNumCuentaIndividual().toString());
				sol.setNumCargo(sol.getNumCargo()!=null ? sol.getNumCargo() + 1 : 1);
				record.setNumDomiciliacion(sol.getNumCargo());
				record.setClaveSolicitud(sol.getClaveSolicitud());
				record.setTipoCuentaClienteUsuario(sol.getTipoCuenta().getId().intValue());
				record.setImporteOperacion(monto);
				records.add(record);
			//}
		}
		return records;
		
	}
	
	private List<BigDecimal> parcializaMonto(BigDecimal monto, BigDecimal maxMontoParcial){
		List<BigDecimal> parcialidades = new ArrayList<BigDecimal>();
		BigDecimal[] divYRemanente = monto.divideAndRemainder(maxMontoParcial);
		if(divYRemanente!=null && divYRemanente.length > 1) {
			Integer registrosCompletos = divYRemanente[0].intValue();
			BigDecimal restante = divYRemanente[1];
			for(int i = 1; i <= registrosCompletos; i++) {
				parcialidades.add(maxMontoParcial);
			}
			if(BigDecimal.ZERO.compareTo(restante) < 0) {
				parcialidades.add(restante);
			}
		}
		return parcialidades;
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
			LOGGER.error( "ERROR UBICACIÓN   :" + GeneracionArchivoDomiServiceImpl.class.getCanonicalName() );
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
	
	private BanamexDomiFixLenghtFileRecordBean  mapVOToBanamexRecordBean(List<SolicitudVO> solicitudes,Integer consecutivo, Date fecha){
		Integer numSeq=0;
		BanamexDomiFixLenghtFileRecordBean bean = new BanamexDomiFixLenghtFileRecordBean();
		//Header 
		BanamexDomiHeaderRecordBean header = new BanamexDomiHeaderRecordBean();
		header.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		header.setConsecutivo(consecutivo);
		header.setNumSecuencia(++numSeq);
		header.setFechaPresentacion(fecha);
		
		bean.setHeader(header);
		
		bean.setRecords(new ArrayList<BanamexDomiRecordRecordBean>());
		BigDecimal total = new BigDecimal(0);
		for(SolicitudVO sol :solicitudes){
			BanamexDomiRecordRecordBean record = new BanamexDomiRecordRecordBean();
			try{
				record.setBancoReceptorClienteUsuario(sol.getClaveBanco().getId().intValue());
				record.setFechaLiquidacion(fecha);
				record.setFechaVencimiento(fecha);
				String nombreCompleto = (sol.getClienteNombre()!=null?sol.getClienteNombre():"")+' '
										+(sol.getClientePaterno()!=null?sol.getClientePaterno():"")+' '
										+(sol.getClienteMaterno()!=null?sol.getClienteMaterno():"");
				record.setNombreClienteUsuario(cleanSpecialCaracters(nombreCompleto));
				record.setNombreTitularServicio(cleanSpecialCaracters(nombreCompleto));
				record.setNumCuentaClienteUsuario(new BigInteger(sol.getCuentaBanco()));
//				record.setReferenciaNumericaEmisor(referenciaNumericaEmisor);
				record.setReferenciaServicioEmisor(sol.getNumCuentaIndividual().toString());
				sol.setNumCargo(sol.getNumCargo()!=null ? sol.getNumCargo() + 1 : 1);
				record.setNumDomiciliacion(sol.getNumCargo());
				record.setClaveSolicitud(sol.getClaveSolicitud());
				record.setTipoCuentaClienteUsuario(sol.getTipoCuenta().getId().intValue());
				record.setImporteOperacion(sol.getImporte());
				total=total.add(sol.getImporte());				
				record.setNumSecuencia(++numSeq);
				bean.getRecords().add(record);
			}catch(Exception e){
				LOGGER.warn("La solicitud "+sol.getClaveSolicitud()+" no se incluye en el archivo.",e);
			}
		}
		
		BanamexDomiFooterRecordBean footer = new BanamexDomiFooterRecordBean();
		footer.setDiaMes(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		footer.setConsecutivo(consecutivo);
		footer.setNumeroOperaciones(numSeq-1);//Total de registros = numSeq -1 (Encabezado)
		footer.setNumeroSecuencia(++numSeq);
		footer.setImporteTotalOperaciones(total);
		bean.setFooter(footer);
		return bean;
	}
	
	private String cleanSpecialCaracters(String string){
		if(string!=null){
			for(String special:SPECIAL_CHARACTERS_REPLACERS.keySet()){
				string=string.replace(special, SPECIAL_CHARACTERS_REPLACERS.get(special));
			}
		}
		return string;
	}
	
	private String generateName(Short idOrigenAportacion, Short type,Integer consecutivo, Date fecha){
		StringBuffer fileName=new StringBuffer();
//		if(ID_ORIGEN_DOMICILIACION_TRASPASO.equals(idOrigenAportacion)){		//Nombre de archivo domi traspas
//			if(ID_BANAMEX_NCI.equals(type)){
//				SimpleDateFormat sdf = new SimpleDateFormat(DOMI_FILES_DATE_FORMAT_BANAMEX);
//				fileName .append(ID_BANAMEX_DOMICILIACIONES)
//					.append(sdf.format(fecha))
//					.append(FILE_NAME_PART_DOMICILIACIONES_BANAMEX)
//					.append(StringUtils.leftPad(
//								String.valueOf(consecutivo+FILE_NAME_OFFSET_DOMICILIACIONES_TRASPASO), 
//								BANAMEX_CONSECUTIVE_PADDING_POSITIONS,
//								'0')
//							)
//					.append(FILE_NAME_EXT_DOMICILIACIONES_BANAMEX);
//			}else if(ID_BANCOMER_NCI.equals(type)){
//				SimpleDateFormat sdf = new SimpleDateFormat(DOMI_FILES_DATE_FORMAT_BANCOMER);
//				fileName .append( ID_BANCOMER_DOMICILIACIONES+sdf.format(fecha))
//					.append(FILE_NAME_PART_DOMICILIACIONES_BANCOMER	)
//					.append(StringUtils.leftPad(
//								String.valueOf(consecutivo+FILE_NAME_OFFSET_DOMICILIACIONES_TRASPASO), 
//								BANCOMER_CONSECUTIVE_PADDING_POSITIONS,
//								'0')
//							)
//					.append(FILE_NAME_EXT_DOMICILIACIONES_BANCOMER);
//			}
//		}else{ 
			if(ID_BANAMEX_NCI.equals(type)){
				SimpleDateFormat sdf = new SimpleDateFormat(DOMI_FILES_DATE_FORMAT_BANAMEX);
				fileName.append(ID_BANAMEX_DOMICILIACIONES)
					.append(sdf.format(fecha))
					.append(FILE_NAME_PART_DOMICILIACIONES_BANAMEX)
					.append(StringUtils.leftPad(
								String.valueOf(consecutivo), 
								BANAMEX_CONSECUTIVE_PADDING_POSITIONS,
								'0')
							)
					.append(FILE_NAME_EXT_DOMICILIACIONES_BANAMEX);
			}else if(ID_BANCOMER_NCI.equals(type)){
				SimpleDateFormat sdf = new SimpleDateFormat(DOMI_FILES_DATE_FORMAT_BANCOMER);
				fileName.append( ID_BANCOMER_DOMICILIACIONES+sdf.format(fecha))
					.append(FILE_NAME_PART_DOMICILIACIONES_BANCOMER	)
					.append(StringUtils.leftPad(
								String.valueOf(consecutivo), 
								BANCOMER_CONSECUTIVE_PADDING_POSITIONS,
								'0')
							)
					.append(FILE_NAME_EXT_DOMICILIACIONES_BANCOMER);
			}
//		}
		
		return fileName.toString();
	}

//	private void addNOConvivenToSolFilter(SolicitudFilter f) throws MitBusinessException{
//		List<SolicitudVO> noConviven= getSolicitudesNOConviven(f);
//		addNOConvivenToSolFilter(f,noConviven);
//	}
	
	private void addNOConvivenToSolFilter(SolicitudFilter f,List<SolicitudVO> noConviven) throws MitBusinessException{
		if(noConviven!=null &&!noConviven.isEmpty()){
			for(SolicitudVO noc:noConviven){
				f.getExcludeNumCuentaIndividual().add(noc.getNumCuentaIndividual());
			}
		}
	}
	
	public List<SolicitudVO> getSolicitudesNOConviven(DomiParameterMapFilter filter) throws MitBusinessException{
		List<SolicitudVO> noConviven= null;
		
		List<SolicitudFilter> l = getCustomFilter(filter,SolicitudFilterType.CIFRAS_TOTALES);
		if(l!=null && !l.isEmpty()){
			SolicitudFilter f = l.get(0);
			List<SolicitudVO> all =	solicitudPersistence.selectIdsDomiRecords(f);
			if(all!=null && !all.isEmpty()){
				noConviven = new ArrayList<SolicitudVO>();
//				for(SolicitudVO vo : all){
//					try {
//						//if(!validaConvivenciaApovol(vo)){
//						//	noConviven.add(vo);
//						//}
//					} catch (Exception ex) {
//						MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
//								ErrorCodeService.EX_EXCEPTION, "con la cuenta "+vo.getNumCuentaIndividual()+": "+ex.getMessage(),
//								new Object[] { getClass().getSimpleName(), "getSolicitudesNOConviven()" }, ex));
//				
//						LOGGER.error(mitBusinessException.getMessage(), ex);
//						throw mitBusinessException;
//					}
//				}
			}
		}
		return noConviven;
	}
	
	@Deprecated
	public Boolean validaConvivencia(SolicitudVO solicitud){
		Boolean convive= false;
		try {
			MatrizConvivenciaPortType port = wsPortTypeFactory.matrizConvivenciaPortType();
			ConsultarConvivenciaReq req = new ConsultarConvivenciaReq();
			req.setIdMovimiento(ID_TIPO_MOVIMIENTO_ABONO);//181 - Tipo Movimiento: ABONO
			req.setIdProceso(ID_PROCESO_RECAUDACION);//7-RECAUDACION
			req.setIdSubproceso(ID_SUBPROCESO_APORTACION_VOLUNTARIA);//101- APORTACION VOLUNTARIA
			req.setNumeroCuenta(solicitud.getNumCuentaIndividual());

			ConsultarConvivenciaResp resp =port.consultarConvivencia(req);
			convive = resp.isEstatusConvivencia();
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al validar convivencia "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "validaConvivencia()" }, ex));
	
			LOGGER.error(mitBusinessException.getMessage(), ex);
//			throw mitBusinessException;
		}
		return convive;
	}
	
	public Boolean validaConvivenciaApovol(SolicitudVO solicitud){
		Boolean convive= Boolean.TRUE;
		try {
			MatrizConvivenciaFilter filter = new MatrizConvivenciaFilter();
			filter.setNumeroCuentaIndividual(solicitud.getNumCuentaIndividual());
			filter.setIdSubproceso(ID_SUBPROCESO_APORTACION_VOLUNTARIA);
			convive = matrizConvivenciaService.consultarConvivenciaSimple(filter);
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al validar convivencia "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "validaConvivencia()" }, ex));
	
			LOGGER.error(mitBusinessException.getMessage(), ex);
	//		throw mitBusinessException;
		}
		return convive;
	}

	@Transactional(rollbackFor = MitBusinessException.class, propagation = Propagation.REQUIRED)
	public Integer actualizarCargo(ApoVolFilter filter) {
		int actualizacion = 0;
		try {
		//FOP 2229 se comenta para poder continuar
		//actualizacion = cargoPersistence.actualizarCargo(filter);
		return actualizacion;
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al actualizar Cargo "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "actualizarCargo()" }, ex));
	
			LOGGER.error(mitBusinessException.getMessage(), ex);
		}
		return actualizacion;
	}	
}
