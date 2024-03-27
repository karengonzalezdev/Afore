package mx.profuturo.nci.web.service.impl;

import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ORIGEN_DOMICILIACION_TRASPASO;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.TXT_DOMICILIACIONES_MAY;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ESTATUS_TRASPASO_PENDIENTE;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_PENDIENTE;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IArchivoGeneradoService;
import mx.profuturo.nci.business.service.ICatalogosService;
import mx.profuturo.nci.business.service.IDomiTraspasosService;
import mx.profuturo.nci.business.service.IGeneracionArchivoDomiService;
import mx.profuturo.nci.business.service.IIdentificarClienteService;
import mx.profuturo.nci.business.service.IIndicadoresService;
import mx.profuturo.nci.business.service.impl.WSPortTypeFactory;
import mx.profuturo.nci.business.util.ConstantesCatalogos;
import mx.profuturo.nci.business.util.HashIndexed3DMatrix;
import mx.profuturo.nci.business.util.IdentificarClienteEnum;
import mx.profuturo.nci.business.util.UtilMethod;
import mx.profuturo.nci.business.vo.ArchivosGeneradosVO;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.DetalleMesDomiTraspasoVO;
import mx.profuturo.nci.business.vo.GeneracionArchivoDomiVO;
import mx.profuturo.nci.business.vo.GenericCatalogoIntegerVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.vo.InversionDomiTraspasoVO;
import mx.profuturo.nci.business.vo.MonitorCargosDomiTraspasoVO;
import mx.profuturo.nci.business.vo.MonitorSolicitudesVO;
import mx.profuturo.nci.business.vo.ParamCatalogoVO;
import mx.profuturo.nci.business.vo.SolicitudSumTotalPorBancoVO;
import mx.profuturo.nci.business.vo.SolicitudSumTotalVO;
import mx.profuturo.nci.business.vo.SolicitudVO;
import mx.profuturo.nci.business.wrapped.ApoVolFilter;
import mx.profuturo.nci.business.wrapped.ArchivoDomiciliacionfilter;
import mx.profuturo.nci.business.wrapped.DomiParameterKeyFilter;
import mx.profuturo.nci.business.wrapped.DomiParameterMapFilter;
import mx.profuturo.nci.business.wrapped.DomiParameterValuesFilter;
import mx.profuturo.nci.web.beans.ArchivoDomiParamRegistry;
import mx.profuturo.nci.web.beans.ArchivoDomiViewParamBean;
import mx.profuturo.nci.web.beans.CifrasBancoBean;
import mx.profuturo.nci.web.beans.CifrasPorBancoBean;
import mx.profuturo.nci.web.beans.CifrasTotalesBean;
import mx.profuturo.nci.web.beans.CuentaIndividualBean;
import mx.profuturo.nci.web.beans.GeneracionArchivoDomiBean;
import mx.profuturo.nci.web.beans.GenericCatalogoBean;
import mx.profuturo.nci.web.beans.RangoFechaParamBean;
import mx.profuturo.nci.web.beans.traspasos.DetalleMesBean;
import mx.profuturo.nci.web.beans.traspasos.DetalleMesDetalleBean;
import mx.profuturo.nci.web.beans.traspasos.InversionDomiTraspasosBean;
import mx.profuturo.nci.web.beans.traspasos.InversionDomiTraspasosDetalleBean;
import mx.profuturo.nci.web.beans.traspasos.MonitoreoCargosDomiBean;
import mx.profuturo.nci.web.beans.traspasos.MonitoreoCargosDomiDetalleBean;
import mx.profuturo.nci.web.beans.traspasos.MonitoreoSolicitudesDetalleBean;
import mx.profuturo.nci.web.service.IArchivoDomiciliacionWebService;
import mx.profuturo.nci.web.util.UtilMapping;
import mx.profuturo.nci.ws.webservice.indicadores.IIndicadoresSoapWSConsultaBasicaResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import profuturo.mx.iib.apovol.domiciliaciones.domiciliacionservice.v1.types.Catalogo;
import profuturo.mx.iib.apovol.domiciliaciones.domiciliacionservice.v1.DomiciliacionPortType;
import profuturo.mx.iib.apovol.domiciliaciones.domiciliacionservice.v1.types.ActualizarSolicitudReq;
import profuturo.mx.iib.apovol.domiciliaciones.domiciliacionservice.v1.types.ActualizarSolicitudResp;
import profuturo.mx.iib.apovol.domiciliaciones.domiciliacionservice.v1.types.ActualizarTraspasoReq;
import profuturo.mx.iib.apovol.domiciliaciones.domiciliacionservice.v1.types.ActualizarTraspasoResp;
import profuturo.mx.iib.apovol.domiciliaciones.domiciliacionservice.v1.types.Domiciliacion;
import profuturo.mx.iib.apovol.domiciliaciones.domiciliacionservice.v1.types.Frecuencia;
import profuturo.mx.iib.apovol.domiciliaciones.domiciliacionservice.v1.types.Solicitud;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.ConsultarClienteBasicoResp;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.Correo;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.ListaCorreos;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.ListaTelefonos;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.types.Telefono;


@Service("archivoDomiciliacionService")
public class ArchivoDomiciliacionWebServiceImpl implements IArchivoDomiciliacionWebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArchivoDomiciliacionWebServiceImpl.class);

	@Autowired IArchivoGeneradoService archivoGeneradoService;
	@Autowired IGeneracionArchivoDomiService generacionArchivoDomiService;
	@Autowired IDomiTraspasosService domiTraspasosService;
	@Autowired ICatalogosService catalogoService;
	@Autowired IIdentificarClienteService identificarClienteService;
	@Autowired IIndicadoresService indicadoresService;
	@Autowired WSPortTypeFactory portTypeFactory;
	
	/**
	 * @return Regresa una matriz
	 * @throws MitBusinessException 
	 */
	public HashIndexed3DMatrix<RangoFechaParamBean> getRangosUltimoGenerado(Short idOrigen) throws MitBusinessException{
		HashIndexed3DMatrix<RangoFechaParamBean> resp = new HashIndexed3DMatrix<RangoFechaParamBean>();
		 
		ArchivoDomiciliacionfilter archDomi = new ArchivoDomiciliacionfilter();
		archDomi.setIdOrigenSolicitud(idOrigen);
		
		 List<ArchivosGeneradosVO> listaArchivosGeneradosVO = archivoGeneradoService.lista(archDomi);
		 for(ArchivosGeneradosVO vo : listaArchivosGeneradosVO){
			 RangoFechaParamBean rango = new RangoFechaParamBean()
			 			.setFechaIni(UtilMethod.truncateHour(vo.getFechaInicio()))
			 			.setFechaFin(UtilMethod.truncateHour(vo.getFechaFin()));
			 String tipoArchivo = vo.getTipoArchivo().getId().toString();
			 String claveBanco = vo.getClaveBanco().getId().toString();
			 String tipoCuenta = vo.getTipoCuenta().getId().toString();
			 
			 resp.put(tipoArchivo, claveBanco, tipoCuenta, rango);
		 }
		 return resp;
	 }
	
	public List<GenericCatalogoBean> getTiposOrigen() throws MitBusinessException{
		List<GenericCatalogoBean> beans=new ArrayList<GenericCatalogoBean>();
		
		ParamCatalogoVO paramCatalogoVO = new ParamCatalogoVO();
		paramCatalogoVO.setIdCatalogo(ID_ORIGEN_DOMICILIACION_TRASPASO);
		List<CatalogoVO> vos= catalogoService.consultarLista(paramCatalogoVO);
		for(CatalogoVO vo:vos){
				beans.add(new GenericCatalogoBean(vo.getIdCatCatalogo(), vo.getValor()));
			}
		beans.add(new GenericCatalogoBean(new Short("0"), TXT_DOMICILIACIONES_MAY));
		
		return beans;
	}
	
	public List<GeneracionArchivoDomiBean> generarArchivoDomi(ArchivoDomiViewParamBean paramBean,List<SolicitudVO> noConviven) throws MitBusinessException {
		List<GeneracionArchivoDomiVO> results = 
				generacionArchivoDomiService.generarArchivosDomi(getDomiParameterMapFilter(paramBean));
		LOGGER.debug("ARCHIVOS RESULTANTES " + results.size());
		List<GeneracionArchivoDomiBean> lista = new ArrayList<GeneracionArchivoDomiBean>();
		for (GeneracionArchivoDomiVO archivoDomi : results) {
			LOGGER.debug("GENERANDO BEAN");
			GeneracionArchivoDomiBean archivoBean = new GeneracionArchivoDomiBean();
			archivoBean.setNombreArchivo(archivoDomi.getNombreArchivo() != null ? archivoDomi.getNombreArchivo() : null);
			archivoBean.setErrors(archivoDomi.getErrors());
			lista.add(archivoBean);
		}
		return lista;
	}
	private DomiParameterMapFilter getDomiParameterMapFilter(ArchivoDomiViewParamBean paramBean){
		DomiParameterMapFilter map= new DomiParameterMapFilter();
		map.setFechaFin(paramBean.getFechaFin());
		map.setFechaInicio(paramBean.getFechaInicio());
		map.setIdOrigenDomiciliacion(paramBean.getIdOrigenDomiciliacion());
		map.setUsuario(paramBean.getUsuario());
		short i =0;
		for(ArchivoDomiParamRegistry reg:paramBean.getRegistros()){
			if(reg.getIdBanco()!=null && reg.getIdBanco().size() > 0){
				DomiParameterKeyFilter key = new DomiParameterKeyFilter();
				key.setIdTipoArchivo(reg.getTipoArchivo());
				key.setKey(i);
//				if(!map.getValues().containsKey(tipoArchivo)){
//					map.getValues().put(tipoArchivo, new DomiParameterValuesFilter());
//				}
				DomiParameterValuesFilter val = new DomiParameterValuesFilter();//map.getValues().get(tipoArchivo);
				val.setUnSoloArchivo(reg.getArchivoUnico());
				Short idBanco = Short.valueOf(reg.getIdBanco().get(0));
				val.getIdsBancos().add(idBanco);
				for(String idTipoCuenta:reg.getIdsTiposCuenta()){
					val.getIdsTiposCuenta().add(Short.valueOf(idTipoCuenta));
//					if(ID_CUENTA_CHEQUERA.equals(idTipoCuenta)){
//						val.getIdsTiposCuenta().add(ID_CUENTA_NUM_CELULAR);
//					}
				}
				i++;
				map.getValues().put(key, val);
			}
		}
		return map;
	}
	

	
	public List<CifrasTotalesBean> generaCifrasTotales(ArchivoDomiViewParamBean paramBean) throws MitBusinessException {
		List<CifrasTotalesBean> beans = new ArrayList<CifrasTotalesBean>();
		//	se obtiene un solo filtro con el concentrado de ids bancos y cuentas sin importar el archivo 
		//		debido a que se calulan las cifras totales
		List<SolicitudSumTotalVO> sum=generacionArchivoDomiService.consultarDomiResumen(getDomiParameterMapFilter(paramBean));
		long totalRegistros = 0;
		for(SolicitudSumTotalVO vo : sum){
			if(vo!=null){
				CifrasTotalesBean bean = new CifrasTotalesBean();
				bean.setDescripcion(vo.getDescripcion());
				bean.setImporte(vo.getImporte());
				bean.setTotal(vo.getTotal());
				bean.setTotalRegistros(vo.getNumRegistros());
				beans.add(bean);
				totalRegistros += vo.getNumRegistros();
			}
		}
		if(beans!=null && !beans.isEmpty()){
			beans.get(0).setTotalRegistros(totalRegistros);// TODO Mejorar implementacion
		}
		
		
		return beans;
	}
	
	
	public List<SolicitudVO> getNoConviven(ArchivoDomiViewParamBean paramBean) throws MitBusinessException{
		return generacionArchivoDomiService.getSolicitudesNOConviven(getDomiParameterMapFilter(paramBean));
	}

	public List<CifrasPorBancoBean> generarCifrasPorBanco(ArchivoDomiViewParamBean paramBean) throws MitBusinessException{
		List<CifrasPorBancoBean> beans = new ArrayList<CifrasPorBancoBean>();
		List<SolicitudSumTotalPorBancoVO> sum = generacionArchivoDomiService.consultarDomiResumenPorBanco(getDomiParameterMapFilter(paramBean));
		for(SolicitudSumTotalPorBancoVO vo :sum){
			CifrasPorBancoBean totalBean= new CifrasPorBancoBean();
			totalBean.setTipocuenta(new GenericCatalogoBean());
			UtilMapping.mapVoToBean(totalBean.getTipocuenta(), vo.getTipoCuenta());
			CifrasBancoBean cifrasBanco = new CifrasBancoBean();
			cifrasBanco.setImporteTotal(vo.getTotal());
			cifrasBanco.setTotalRegistros(vo.getNumRegistros());
			cifrasBanco.setBanco(new GenericCatalogoBean());
			cifrasBanco.setBancoIncluir(new GenericCatalogoBean());
			UtilMapping.mapVoToBean(cifrasBanco.getBancoIncluir(),vo.getClaveBanco());
			UtilMapping.mapVoToBean(cifrasBanco.getBanco(), vo.getTipoArchivo());
			if(beans.contains(totalBean)){
				totalBean=beans.get(beans.indexOf(totalBean));
			}else{
				totalBean.setListaBancos(new ArrayList<CifrasBancoBean>());
				beans.add(totalBean);
			}
			totalBean.getListaBancos().add(cifrasBanco);
		}
		
		return beans;
	}
	



	public List<MonitoreoCargosDomiBean> generaMonitoreoCargosDomiTraspaso()  throws MitBusinessException{
		List<MonitoreoCargosDomiBean> beans = new ArrayList<MonitoreoCargosDomiBean>();
		List<String> periodicidad = new ArrayList<String>();
		
		List<CatalogoVO> lstCata = catalogoService.consultarLista(new ParamCatalogoVO(ConstantesCatalogos.ID_TIPO_PERIODOS));
		for(int i=0;i<lstCata.size();i++){
			periodicidad.add(lstCata.get(i).getValor());
		}
		
		Map<String,MonitoreoCargosDomiBean> rego = new HashMap<String,MonitoreoCargosDomiBean>();
		List<MonitorCargosDomiTraspasoVO> lstObjetos = domiTraspasosService.monitorCargosDomiTraspaso();
		MonitoreoCargosDomiBean regFin;
		List<MonitoreoCargosDomiDetalleBean> lstRegFin;
		Integer cargosTot=0;
		
		for(int i=0;i<lstObjetos.size();i++){
			MonitorCargosDomiTraspasoVO reg = lstObjetos.get(i);

			if(rego.containsKey(reg.getMesLlave())){
				regFin = rego.get(reg.getMesLlave());
				lstRegFin = regFin.getLstRecurrencias();
				
			}else{
				regFin = new MonitoreoCargosDomiBean(); 
				lstRegFin = iniciaLstPeriodicidad(periodicidad);
				
				cargosTot = 0;
			}
			
			for(int j=0;j<lstRegFin.size();j++){
				MonitoreoCargosDomiDetalleBean objIte = lstRegFin.get(j);
				if(objIte.getRecurrencia().equals(reg.getRecurencia())){		
					MonitoreoCargosDomiDetalleBean recurren = lstRegFin.get(j);
					recurren.setRecurrencia(reg.getRecurencia());
					recurren.setNoSolicitudes(reg.getNoSolicitudes());
					recurren.setTraspasoLiq(reg.getNoTrasLiquidado());
					recurren.setTraspasoPend(reg.getNoTrasPendientes());
					recurren.setMontoEsperado(reg.getMontoEsperado());
					recurren.setCargosAcep(reg.getCargosAceptados());
					recurren.setCargosRech(reg.getCargosRechazados());
					recurren.setMontoConfirmado(reg.getMontoConfirmado());
					recurren.setMontoRech(reg.getMontoRechazado());					
				}
			}			
			cargosTot = cargosTot + reg.getNoSolicitudes();
			
			String mes = reg.getMesLlave().substring(4,6);
			if(mes.substring(0,1).equals("0")){
				mes = mes.substring(1,2);
			}
			
			regFin.setMesIngreso(ConstantesCatalogos.ID_MESES[Integer.parseInt(mes)-1]);
			regFin.setCargos(cargosTot);
			regFin.setLstRecurrencias(lstRegFin);
			
			rego.put(reg.getMesLlave(), regFin);
		}
		
		if(rego!=null && !rego.isEmpty()){
			Map<String,MonitoreoCargosDomiBean> treeMap = new TreeMap<String,MonitoreoCargosDomiBean>(rego);
			for (Map.Entry<String,MonitoreoCargosDomiBean> entry : treeMap.entrySet()) {
				beans.add(entry.getValue());
			}	
		}
		
		return beans;
	}
	
	private List<MonitoreoCargosDomiDetalleBean> iniciaLstPeriodicidad(List<String> periodicidad){
		List<MonitoreoCargosDomiDetalleBean> lstPeriod = new ArrayList<MonitoreoCargosDomiDetalleBean>();
		
		for(int i=0;i<periodicidad.size();i++){
			MonitoreoCargosDomiDetalleBean perio;
			if(i==0){
				perio = new MonitoreoCargosDomiDetalleBean(periodicidad.get(i), 0, 0, 0, new BigDecimal("0"), 0, 0, new BigDecimal("0"), new BigDecimal("0"),true);
			}
			else{
				perio = new MonitoreoCargosDomiDetalleBean(periodicidad.get(i), 0, 0, 0, new BigDecimal("0"), 0, 0, new BigDecimal("0"), new BigDecimal("0"),false);
			}
			lstPeriod.add(perio);
		}
		
		return lstPeriod;
	}
	
	public InversionDomiTraspasosBean generaInvDomiTrasPorAcreditar() throws MitBusinessException{
		InversionDomiTraspasosBean reg = new InversionDomiTraspasosBean();		
		reg = generarInversionDomiTrasBean(reg, domiTraspasosService.inversionDomiTrasPorAcreditar());
		return reg;
	}
		
	public InversionDomiTraspasosBean generaInvDomiTrasPendientesTraspaso() throws MitBusinessException{
		InversionDomiTraspasosBean reg = new InversionDomiTraspasosBean();
		reg = generarInversionDomiTrasBean(reg, domiTraspasosService.inversionDomiTrasPendientesTraspaso());
		return reg;
	}
	
	public InversionDomiTraspasosBean generaInvDomiTrasDevolucion() throws MitBusinessException{
		InversionDomiTraspasosBean reg = new InversionDomiTraspasosBean();
		reg = generarInversionDomiTrasBean(reg, domiTraspasosService.inversionDomiTrasDevolucion());
		return reg;
	}
	
	public InversionDomiTraspasosBean generaInvDomiTrasAcreditados() throws MitBusinessException{
		InversionDomiTraspasosBean reg = new InversionDomiTraspasosBean();
		reg = generarInversionDomiTrasBean(reg, domiTraspasosService.inversionDomiTrasAcreditados());
		return reg;
	}
	
	private InversionDomiTraspasosBean generarInversionDomiTrasBean(InversionDomiTraspasosBean bean, List<InversionDomiTraspasoVO> lstObjetos){
		Integer registros = 0;
		BigDecimal total = new BigDecimal("0");
		List<InversionDomiTraspasosDetalleBean> lstRecurrencias = new ArrayList<InversionDomiTraspasosDetalleBean>();
		
		for(int i=0;i<lstObjetos.size();i++){
			InversionDomiTraspasoVO regVo = lstObjetos.get(i);
			InversionDomiTraspasosDetalleBean regBean = new InversionDomiTraspasosDetalleBean();
			regBean.setSiefore(regVo.getSiefore());
			regBean.setSubcuenta(regVo.getSubCuenta());
			regBean.setTipoMovimiento(regVo.getTipoMovimiento());
			regBean.setImporte(regVo.getPesos());
			regBean.setRegistros(regVo.getRegistros());
		
			lstRecurrencias.add(regBean);
			registros = registros + regVo.getRegistros();
			total = total.add(regVo.getPesos());
		}
		
		bean.setLstRecurrencias(lstRecurrencias);
		bean.setImporteTot(total);
		bean.setRegistrosTot(registros);
		
		return bean;
	}
	
	public List<SelectItem> obtenerLstMesDetalleMesDomiTras() throws MitBusinessException{
		List<SelectItem> lstMes = new ArrayList<SelectItem>();
			List<GenericCatalogoIntegerVO> regs = domiTraspasosService.obtenerLstMesDetalleMesDomiTras();
			for (GenericCatalogoIntegerVO temp : regs) {
				Integer valor = Integer.valueOf(temp.getValor())-1;
				String []  rego = ConstantesCatalogos.ID_MESES;
				lstMes.add(new SelectItem(temp.getId(),rego[valor]));
			}
			
			if(lstMes==null || lstMes.isEmpty()){
				String [] regoT = ConstantesCatalogos.ID_MESES;
				lstMes = new ArrayList<SelectItem>();
				
				java.util.Date fecha = new Date();
				Calendar calAct = Calendar.getInstance(); 
				calAct.setTime(fecha); 
                if((calAct.get(Calendar.MONTH) + 1)<10){
                	lstMes.add(new SelectItem(calAct.get(Calendar.YEAR)+"0"+(calAct.get(Calendar.MONTH) + 1),regoT[calAct.get(Calendar.MONTH)]));
                }else{
                	lstMes.add(new SelectItem(calAct.get(Calendar.YEAR)+""+(calAct.get(Calendar.MONTH) + 1),regoT[calAct.get(Calendar.MONTH)]));
                }
                
				Calendar calUno = Calendar.getInstance(); 
				calUno.setTime(fecha); 
				calUno.add(Calendar.MONTH,-1);
                if((calUno.get(Calendar.MONTH) + 1)<10){
                	lstMes.add(new SelectItem(calUno.get(Calendar.YEAR)+"0"+(calUno.get(Calendar.MONTH) + 1),regoT[calUno.get(Calendar.MONTH)]));
                }else{
                	lstMes.add(new SelectItem(calUno.get(Calendar.YEAR)+""+(calUno.get(Calendar.MONTH) + 1),regoT[calUno.get(Calendar.MONTH)]));
                }
                
                Calendar calDos = Calendar.getInstance(); 
                calDos.setTime(fecha); 
                calDos.add(Calendar.MONTH,-2);
                if((calDos.get(Calendar.MONTH) + 1)<10){
                	lstMes.add(new SelectItem(calDos.get(Calendar.YEAR)+"0"+(calDos.get(Calendar.MONTH) + 1),regoT[calDos.get(Calendar.MONTH)]));
                }else{
                	lstMes.add(new SelectItem(calDos.get(Calendar.YEAR)+""+(calDos.get(Calendar.MONTH) + 1),regoT[calDos.get(Calendar.MONTH)]));
                }
			}
			
		return lstMes;
	}
	
	//FOP 27/09/2018 | Se agrega Domi Registro C/S Liquidaciones
	private void setTotales(List<DetalleMesDomiTraspasoVO> lst, List<DetalleMesDetalleBean> lstBean, DetalleMesBean detBean) {
		Integer totSolIngre = 0;
		Integer totSolAcep = 0;
		Integer totSolCancel = 0;
		Integer totSolUnCargoAcep = 0;
		Integer totSolUnCargoRech = 0;
		BigDecimal totRecAcred= new BigDecimal("0");
		BigDecimal totRecDevoTeso = new BigDecimal("0");
		for(int i=0;i<lst.size();i++){
			DetalleMesDomiTraspasoVO objeto = lst.get(i);
			for(int j=0;j<lstBean.size();j++){
				if(lstBean.get(j).getRecurrencia().equals(objeto.getRecurrencia())){
					lstBean.get(j).setRecursosAcreditados(objeto.getRecuAcre());
					lstBean.get(j).setRecursosDevoPorTesoreria(objeto.getRecuDevo());
					lstBean.get(j).setSolAceptadas(objeto.getSolAceptadas());
					lstBean.get(j).setSolCanceladas(objeto.getSolCancel());
					lstBean.get(j).setSolIngresadas(objeto.getSolIngresadas());
					lstBean.get(j).setSolUnCargoAceptadas(objeto.getSolAcepUnCargo());
					lstBean.get(j).setSolUnCargoRechazadas(objeto.getSolRechUnCargo());
					
					totSolIngre = totSolIngre+ objeto.getSolIngresadas(); 
					totSolAcep = totSolAcep + objeto.getSolAceptadas();
					totSolCancel = totSolCancel + objeto.getSolCancel() ;
					totSolUnCargoAcep = totSolUnCargoAcep + objeto.getSolAcepUnCargo() ;
					totSolUnCargoRech = totSolUnCargoRech + objeto.getSolRechUnCargo(); 
					totRecAcred = totRecAcred.add(objeto.getRecuAcre());
					totRecDevoTeso = totRecDevoTeso.add(objeto.getRecuDevo());
					
					break;
				}
			}
		}
		detBean.setLstSolicitudes(lstBean);
		detBean.setTotSolIngresadas(totSolIngre);
		detBean.setTotSolAceptadas(totSolAcep);
		detBean.setTotSolCanceladas(totSolCancel);
		detBean.setTotSolUnCargoAceptadas(totSolUnCargoAcep);
		detBean.setTotSolUnCargoRechazadas(totSolUnCargoRech);
		detBean.setTotRecursosAcreditados(totRecAcred);
		detBean.setTotRecursosDevoporTesoreria(totRecDevoTeso);

	}
	
	//FOP 27/09/2018 | Se agrega Domi Registro C/S Liquidaciones
	public List<DetalleMesBean> obtenerLtsDetalleMesDomiTrasAll(Integer mesBusqueda) throws MitBusinessException{
		List<DetalleMesBean> rgs = new ArrayList<DetalleMesBean>();
		//Ya existente
		DetalleMesBean detBean = new DetalleMesBean();
		List<DetalleMesDetalleBean> lstBean; 	 
		List<String> periodicidad = new ArrayList<String>();
		List<CatalogoVO> lstCata = catalogoService.consultarLista(new ParamCatalogoVO(ConstantesCatalogos.ID_TIPO_PERIODOS));
		for(int i=0;i<lstCata.size();i++){
			periodicidad.add(lstCata.get(i).getValor());
		}
		lstBean = iniciaLstPeriodicidadMesDomi(periodicidad);
		List<DetalleMesDomiTraspasoVO> lst = domiTraspasosService.obtenerLstDetalleMesDomiTras(mesBusqueda);
		this.setTotales(lst, lstBean, detBean);
		rgs.add(detBean);
		//DSL
		DetalleMesBean detBeanDSL = new DetalleMesBean();
		List<DetalleMesDetalleBean> lstBeanDSL = iniciaLstPeriodicidadMesDomi(periodicidad);
		List<DetalleMesDomiTraspasoVO> lstDSL = domiTraspasosService.obtenerLstDetalleMesDomiTrasSL(mesBusqueda);
		this.setTotales(lstDSL, lstBeanDSL, detBeanDSL);
		rgs.add(detBeanDSL);
		//DCL
		DetalleMesBean detBeanDCL = new DetalleMesBean();
		List<DetalleMesDetalleBean> lstBeanDCL = iniciaLstPeriodicidadMesDomi(periodicidad);
		List<DetalleMesDomiTraspasoVO> lstDCL = domiTraspasosService.obtenerLstDetalleMesDomiTrasCL(mesBusqueda);
		this.setTotales(lstDCL, lstBeanDCL, detBeanDCL);
		rgs.add(detBeanDCL);
		return rgs;
	}
	
	public DetalleMesBean obtenerLstDetalleMesDomiTras(Integer mesBusqueda) throws MitBusinessException{
		DetalleMesBean detBean = new DetalleMesBean();
		List<DetalleMesDetalleBean> lstBean; 	 
		
		List<String> periodicidad = new ArrayList<String>();
		List<CatalogoVO> lstCata = catalogoService.consultarLista(new ParamCatalogoVO(ConstantesCatalogos.ID_TIPO_PERIODOS));
		for(int i=0;i<lstCata.size();i++){
			periodicidad.add(lstCata.get(i).getValor());
		}
		lstBean = iniciaLstPeriodicidadMesDomi(periodicidad);
		
		Integer totSolIngre = 0;
		Integer totSolAcep = 0;
		Integer totSolCancel = 0;
		Integer totSolUnCargoAcep = 0;
		Integer totSolUnCargoRech = 0;
		BigDecimal totRecAcred= new BigDecimal("0");
		BigDecimal totRecDevoTeso = new BigDecimal("0");
		
		List<DetalleMesDomiTraspasoVO> lst = domiTraspasosService.obtenerLstDetalleMesDomiTras(mesBusqueda);
		for(int i=0;i<lst.size();i++){
			DetalleMesDomiTraspasoVO objeto = lst.get(i);
			for(int j=0;j<lstBean.size();j++){
				if(lstBean.get(j).getRecurrencia().equals(objeto.getRecurrencia())){
					lstBean.get(j).setRecursosAcreditados(objeto.getRecuAcre());
					lstBean.get(j).setRecursosDevoPorTesoreria(objeto.getRecuDevo());
					lstBean.get(j).setSolAceptadas(objeto.getSolAceptadas());
					lstBean.get(j).setSolCanceladas(objeto.getSolCancel());
					lstBean.get(j).setSolIngresadas(objeto.getSolIngresadas());
					lstBean.get(j).setSolUnCargoAceptadas(objeto.getSolAcepUnCargo());
					lstBean.get(j).setSolUnCargoRechazadas(objeto.getSolRechUnCargo());
					
					totSolIngre = totSolIngre+ objeto.getSolIngresadas(); 
					totSolAcep = totSolAcep + objeto.getSolAceptadas();
					totSolCancel = totSolCancel + objeto.getSolCancel() ;
					totSolUnCargoAcep = totSolUnCargoAcep + objeto.getSolAcepUnCargo() ;
					totSolUnCargoRech = totSolUnCargoRech + objeto.getSolRechUnCargo(); 
					totRecAcred = totRecAcred.add(objeto.getRecuAcre());
					totRecDevoTeso = totRecDevoTeso.add(objeto.getRecuDevo());
					
					break;
				}
			}
		}
		detBean.setLstSolicitudes(lstBean);
		detBean.setTotSolIngresadas(totSolIngre);
		detBean.setTotSolAceptadas(totSolAcep);
		detBean.setTotSolCanceladas(totSolCancel);
		detBean.setTotSolUnCargoAceptadas(totSolUnCargoAcep);
		detBean.setTotSolUnCargoRechazadas(totSolUnCargoRech);
		detBean.setTotRecursosAcreditados(totRecAcred);
		detBean.setTotRecursosDevoporTesoreria(totRecDevoTeso);
		
		return detBean;
	}
	
	private List<DetalleMesDetalleBean> iniciaLstPeriodicidadMesDomi(List<String> periodicidad){
		List<DetalleMesDetalleBean> lstPeriod = new ArrayList<DetalleMesDetalleBean>();
		
		for(int i=0;i<periodicidad.size();i++){
			DetalleMesDetalleBean perio = new DetalleMesDetalleBean(periodicidad.get(i), 0, 0, 0, 0, 0, new BigDecimal("0"), new BigDecimal("0"));
			lstPeriod.add(perio);
		}
		
		return lstPeriod;
	}
	
	public List<MonitoreoSolicitudesDetalleBean> historicocargoSol(MonitoreoSolicitudesDetalleBean selSolicitud){
		List<MonitoreoSolicitudesDetalleBean> valores = new ArrayList<MonitoreoSolicitudesDetalleBean>();
		List<MonitorSolicitudesVO> regs = domiTraspasosService.obtenerLstHistSolicitud(selSolicitud.getClaveSolicitud(), selSolicitud.getIdOrigenSolicitud() );
		//Atencion defecto 2336
		boolean solicitudCancelada = selSolicitud.getEstatusSol().equals("CANCELADO");
		for(int i=0;i<regs.size();i++){	
			MonitoreoSolicitudesDetalleBean rego  = new MonitoreoSolicitudesDetalleBean();
			rego.setClaveSolicitud(regs.get(i).getClaveSolicitud());
			rego.setCuentaIndividual(regs.get(i).getCuentaIndividual());
			rego.setCuentaBancaria(regs.get(i).getCtaBanco());
			rego.setNombre(regs.get(i).getNombre());
			rego.setNss(regs.get(i).getNss());
			rego.setCurp(regs.get(i).getCurp());
			rego.setImporte(regs.get(i).getImporte());
			rego.setFechaCaptura(regs.get(i).getFecRecepcion());
			rego.setPeriodicidad(regs.get(i).getRecurrencia());
			rego.setPeriodicidadDesc(regs.get(i).getRecurrenciaDesc());
			rego.setEstatusSol(regs.get(i).getEstatusSol()!=null?regs.get(i).getEstatusSol():"");
			
			rego.setFechaRecepcion(regs.get(i).getFecRecepcion());
			rego.setEstatusTraspaso(regs.get(i).getEstatusTraspaso());
			rego.setFrecuenciaInicial(regs.get(i).getFrecInicial());
			rego.setFrecuenciaFinal(regs.get(i).getFrecFinal());
			rego.setOrigenSolicitud(regs.get(i).getOrigenDomicilio());
			rego.setCpDosMeses(regs.get(i).getCp());
			rego.setLpDoceMeses(regs.get(i).getLp());
			rego.setAvpilp(regs.get(i).getAvpilp());
			rego.setAcr(regs.get(i).getAcr());
			rego.setTipoCuenta(regs.get(i).getTipoCta());
			rego.setCuentaCargo(regs.get(i).getCtaBanco());
			rego.setEstatusSol(regs.get(i).getEstatusSol());
			
			rego.setCuentaInd(regs.get(i).getCtaInd());
			rego.setCuentaBan(regs.get(i).getCtaBan());
			rego.setFechaCargo(regs.get(i).getFecCargo());
			rego.setImporteIi(regs.get(i).getImporteDos());
			rego.setEstaConcil(regs.get(i).getEstConcil());
			rego.setEstaMov(regs.get(i).getEstMot());

			rego.setFehActCargo(regs.get(i).getFehActCargo());
			
//			if(rego.getEstaConcil()!=null && rego.getEstaConcil().equals("CONCILIADO")){
//				rego.setEstCargo("");
//				rego.setMotivoRechazo("");			
//			}else{
				rego.setEstCargo(regs.get(i).getEstCargo()!=null?regs.get(i).getEstCargo():"");
				rego.setMotivoRechazo(regs.get(i).getMotRechazoBanco());
			
//			}
			if(solicitudCancelada){
				if(rego.getEstCargo().equals("PENDIENTE")){
					LOGGER.info("Fix ALM 2336: Solicitud con estatus Cancelado y con cargo pendiente(en Solicitud) no debe mostrarse");
				}else{
					valores.add(rego);
				}
				//
			}else{
				valores.add(rego);	
			}
			
		}
		return valores;
	}
	
	public List<MonitoreoSolicitudesDetalleBean> obtenerMonitoreoSolPorValor(String numCuenta, String nss, String curp, List<Short> origenSol) throws MitBusinessException{
		List<MonitoreoSolicitudesDetalleBean> valores = new ArrayList<MonitoreoSolicitudesDetalleBean>();
		
		List<MonitorSolicitudesVO> regs = domiTraspasosService.obtenerLstMonitorSolicitud(numCuenta, nss, curp, origenSol);
		for(int i=0;i<regs.size();i++){			
			MonitoreoSolicitudesDetalleBean rego  = new MonitoreoSolicitudesDetalleBean();
			rego.setClaveSolicitud(regs.get(i).getClaveSolicitud());
			rego.setCuentaIndividual(regs.get(i).getCuentaIndividual());
			rego.setCuentaBancaria(regs.get(i).getCtaBanco());
			rego.setNombre(regs.get(i).getNombre());
			rego.setNss(regs.get(i).getNss());
			rego.setCurp(regs.get(i).getCurp());
			rego.setImporte(regs.get(i).getImporte());
			rego.setFechaCaptura(regs.get(i).getFecRecepcion());
			rego.setPeriodicidad(regs.get(i).getRecurrencia());
			rego.setPeriodicidadDesc(regs.get(i).getRecurrenciaDesc());
			rego.setEstatusSol(regs.get(i).getEstatusSol());
			rego.setEstatusTraspaso(regs.get(i).getEstatusTraspaso());
			rego.setFechaRecepcion(regs.get(i).getFecRecepcion());
			rego.setEstatusTraspaso(regs.get(i).getEstatusTraspaso());
			rego.setFrecuenciaInicial(regs.get(i).getFrecInicial());
			rego.setFrecuenciaFinal(regs.get(i).getFrecFinal());
			rego.setIdOrigenSolicitud(regs.get(i).getOrigenAportacion());
			rego.setOrigenSolicitud(regs.get(i).getOrigenDomicilio());
			rego.setCpDosMeses(regs.get(i).getCp());
			rego.setLpDoceMeses(regs.get(i).getLp());
			rego.setAvpilp(regs.get(i).getAvpilp());
			rego.setAcr(regs.get(i).getAcr());
			rego.setTipoCuenta(regs.get(i).getTipoCta());
			rego.setCuentaCargo(regs.get(i).getCtaBanco());
			rego.setEstatusSol(regs.get(i).getEstatusSol());
			rego.setCuentaInd(regs.get(i).getCtaInd());
			rego.setCuentaBan(regs.get(i).getCtaBan());
			rego.setFechaCargo(regs.get(i).getFecCargo());
			rego.setImporteIi(regs.get(i).getImporteDos());
			rego.setEstaConcil(regs.get(i).getEstConcil());
			rego.setEstaMov(regs.get(i).getEstMot());
			rego.setEstCargo(regs.get(i).getEstCargo());
			rego.setMotivoRechazo("");
			rego.setFechaProxCargo(regs.get(i).getFecProxCargo());
			rego.setFehCre(regs.get(i).getFehCre());
			rego.setUsuCre(regs.get(i).getUsuCre());
			rego.setFehAct(regs.get(i).getFehAct());
			rego.setUsuAct(regs.get(i).getUsuAct());
			//FOP 06/07/2018 Domi Registro			
			rego.setIdOrigenSolicitudNoAportacion(regs.get(i).getIdOrigenSolicitud());
			rego.setOrigenSolicitudNoAportacion(regs.get(i).getOrigenSolicitud());
			rego.setGerencia(regs.get(i).getGerencia());
			rego.setPromotor(regs.get(i).getPromotor());
			rego.setRegional(regs.get(i).getRegional());
			rego.setIdTipoRegistro(regs.get(i).getIdTipoRegistro());
			rego.setTipoRegistro(regs.get(i).getTipoRegistro());
			rego.setTutor(regs.get(i).getTutor());
			valores.add(rego);
		}
		
		return valores;
	}
	
	public List<MonitoreoSolicitudesDetalleBean> obtenerMonitoreoSolPorValor(String numCuenta, String nss, String curp) throws MitBusinessException{
		List<MonitoreoSolicitudesDetalleBean> valores = new ArrayList<MonitoreoSolicitudesDetalleBean>();
		
		List<MonitorSolicitudesVO> regs = domiTraspasosService.obtenerLstMonitorSolicitud(numCuenta, nss, curp);
		for(int i=0;i<regs.size();i++){
			MonitoreoSolicitudesDetalleBean rego  = new MonitoreoSolicitudesDetalleBean();
			rego.setClaveSolicitud(regs.get(i).getClaveSolicitud());
			rego.setCuentaIndividual(regs.get(i).getCuentaIndividual());
			rego.setCuentaBancaria(regs.get(i).getCtaBanco());
			rego.setNombre(regs.get(i).getNombre());
			rego.setNss(regs.get(i).getNss());
			rego.setCurp(regs.get(i).getCurp());
			rego.setImporte(regs.get(i).getImporte());
			rego.setFechaCaptura(regs.get(i).getFecRecepcion());
			rego.setPeriodicidad(regs.get(i).getRecurrencia());
			rego.setPeriodicidadDesc(regs.get(i).getRecurrenciaDesc());
			rego.setEstatusSol(regs.get(i).getEstatusSol());
			
			rego.setFechaRecepcion(regs.get(i).getFecRecepcion());
			rego.setEstatusTraspaso(regs.get(i).getEstatusTraspaso());
			rego.setFrecuenciaInicial(regs.get(i).getFrecInicial());
			rego.setFrecuenciaFinal(regs.get(i).getFrecFinal());
			rego.setOrigenSolicitud(regs.get(i).getOrigenDomicilio());
			rego.setCpDosMeses(regs.get(i).getCp());
			rego.setLpDoceMeses(regs.get(i).getLp());
			rego.setAvpilp(regs.get(i).getAvpilp());
			rego.setAcr(regs.get(i).getAcr());
			rego.setTipoCuenta(regs.get(i).getTipoCta());
			rego.setCuentaCargo(regs.get(i).getCtaBanco());
			rego.setEstatusSol(regs.get(i).getEstatusSol());
			rego.setMotivoRechazo(regs.get(i).getMotRechazoBanco());
			
			
			rego.setCuentaInd(regs.get(i).getCtaInd());
			rego.setCuentaBan(regs.get(i).getCtaBan());
			rego.setFechaCargo(regs.get(i).getFecCargo());
			rego.setImporteIi(regs.get(i).getImporteDos());
			rego.setEstaConcil(regs.get(i).getEstConcil());
			rego.setEstaMov(regs.get(i).getEstMot());
			
			rego.setEstCargo(regs.get(i).getEstCargo());
			
			valores.add(rego);
		}
		
		return valores;
	}
	
	public List<GenericCatalogoBean> obtenerPeriodicidad() throws MitBusinessException{
		List<GenericCatalogoBean> regs = new ArrayList<GenericCatalogoBean>();
		
		GenericCatalogoBean regCero = new GenericCatalogoBean();
		regCero.setId(new Short("0"));
		regCero.setValor("Selecciona uno");
		regs.add(regCero);
		
		List<CatalogoVO> lstCata = catalogoService.consultarLista(new ParamCatalogoVO(ConstantesCatalogos.ID_PERIODICIDAD));
		for(int i=0;i<lstCata.size();i++){
			GenericCatalogoBean reg = new GenericCatalogoBean();
			reg.setId(lstCata.get(i).getIdCatCatalogo());
			reg.setValor(lstCata.get(i).getValor());
			regs.add(reg);
		}
		
		return regs;
	}	
	
	public List<GenericCatalogoBean> obtenerTiposFrecSemanal() throws MitBusinessException{
		List<GenericCatalogoBean> regs = new ArrayList<GenericCatalogoBean>();

		Map<Short,GenericCatalogoBean> antesOrde = new HashMap<Short,GenericCatalogoBean>();
		List<CatalogoVO> lstCata = catalogoService.consultarLista(new ParamCatalogoVO(ConstantesCatalogos.ID_FEC_PERIODICIDAD));
		for(int i=0;i<lstCata.size();i++){
			GenericCatalogoBean reg = new GenericCatalogoBean();
			reg.setId(lstCata.get(i).getIdCatCatalogo());
			reg.setValor(lstCata.get(i).getValor());
			
			antesOrde.put(lstCata.get(i).getIdCatCatalogo(), reg);
		}
		
		Map<Short,GenericCatalogoBean> treeMap = new TreeMap<Short,GenericCatalogoBean>(antesOrde);

		Set<Entry<Short, GenericCatalogoBean>> s = treeMap.entrySet();
		Iterator<Entry<Short, GenericCatalogoBean>> it = s.iterator();
		while ( it.hasNext() ) {
			Map.Entry entry = (Map.Entry) it.next();
			regs.add((GenericCatalogoBean) entry.getValue());
		}
		
		return regs;
	}
	
	public List<GenericCatalogoBean> obtenerTiposFrecQuincenalIni() throws MitBusinessException{
		List<GenericCatalogoBean> regs = new ArrayList<GenericCatalogoBean>();
		for(int i=1;i<=15;i++){
			GenericCatalogoBean reg = new GenericCatalogoBean();
			reg.setId(new Short(String.valueOf(i)));
			reg.setValor(String.valueOf(i));
			
			regs.add(reg);
		}
			
		return regs;
	}
	
	public List<GenericCatalogoBean> obtenerTiposFrecQuincenalFin(Integer frecIni) throws MitBusinessException{
		List<GenericCatalogoBean> regs = new ArrayList<GenericCatalogoBean>();
		
		for(int i=(frecIni+14);i<(frecIni+17);i++){
			GenericCatalogoBean reg = new GenericCatalogoBean();
			reg.setId(new Short(String.valueOf(i)));
			reg.setValor(String.valueOf(i));
			
			regs.add(reg);
		}
		
		
		return regs;
	}
	
	public List<GenericCatalogoBean> obtenerTiposFrecMensual() throws MitBusinessException{
		List<GenericCatalogoBean> regs = new ArrayList<GenericCatalogoBean>();
		
		for(int i=1;i<=30;i++){
			GenericCatalogoBean reg = new GenericCatalogoBean();
			reg.setId(new Short(String.valueOf(i)));
			reg.setValor(String.valueOf(i));
			
			regs.add(reg);
		}
		
		return regs;
	}
	
	
	public int actualizaDomiTraspaso(MonitoreoSolicitudesDetalleBean bean)throws MitBusinessException{
//		domiTraspasosService.actualizaDomiSol(bean.getClaveSolicitud(), bean.getPeriodicidad(), bean.getFrecuenciaInicial(), bean.getFrecuenciaFinal());
//		domiTraspasosService.actualizaDomiDiversificacion(bean.getClaveSolicitud(), ConstantesCatalogos.IDAPOVOL_CP, bean.getCpDosMeses());
//		domiTraspasosService.actualizaDomiDiversificacion(bean.getClaveSolicitud(), ConstantesCatalogos.IDAPOVOL_LP, bean.getLpDoceMeses());
//		domiTraspasosService.actualizaDomiDiversificacion(bean.getClaveSolicitud(), ConstantesCatalogos.IDAPOVOL_AVPILP, bean.getAvpilp());
//		domiTraspasosService.actualizaDomiDiversificacion(bean.getClaveSolicitud(), ConstantesCatalogos.IDAPOVOL_ACR, bean.getAcr());
		
		return 1;
	}
	
	
	public int actualizaEstatusSolicitudTraspaso(String cveSol,String numCta,Short idEstatus,String usuario) throws MitBusinessException {
		//return domiTraspasosService.actualizaEstatusDomiSol(cveSol,idEstatus, usuario);
		int result = 0;
		try {
			DomiciliacionPortType port = portTypeFactory.domiciliacionPortType();
			
			ActualizarTraspasoReq parameters = new ActualizarTraspasoReq();
			parameters.setClaveSolicitud(cveSol);
			parameters.setIdEstatusTraspaso(idEstatus);
			parameters.setNumeroCuenta(numCta);
			parameters.setUsuario(usuario);
			
			ActualizarTraspasoResp resp = port.actualizarTraspaso(parameters);
			
//			resp.getMensajes().getMensaje().get(0).getCodigo();
			
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al validar cuenta",
							new Object[] { "BancosServiceImpl", "validarCuentaInd()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
		
		return result;
	}
	
	public int actualizaEstatusSolicitud(MonitoreoSolicitudesDetalleBean solicitud,Short idEstatus,String usuario) throws MitBusinessException {
		//return domiTraspasosService.actualizaEstatusDomiSol(cveSol,idEstatus, usuario);
		int result = 0;
		try {
			DomiciliacionPortType port = portTypeFactory.domiciliacionPortType();
			ActualizarSolicitudReq parameters = new ActualizarSolicitudReq();
			Domiciliacion domi = new Domiciliacion();
			Solicitud sol = new Solicitud();
			Catalogo estatusSolicitud = new Catalogo();
			estatusSolicitud.setIdCatalogo(idEstatus);
			
			sol.setClaveSolicitud(solicitud.getClaveSolicitud());
			sol.setCuentaIndividual(solicitud.getCuentaIndividual());
			sol.setOrigenSolicitud(new Catalogo(solicitud.getIdOrigenSolicitud()));
			sol.setEstatusSolicitud(estatusSolicitud);
			sol.setCuentaBancaria(solicitud.getCuentaBancaria());
			sol.setUsuarioActualizacion(usuario);
			
			setVoidWSParameters(sol);
			domi.setSolicitud(sol);
			parameters.setDomiciliacion(domi);
			ActualizarSolicitudResp resp = port.actualizarSolicitud(parameters);
//			resp.getMensajes().getMensaje().get(0).getCodigo();
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al actualizar estatus:"+ex.getMessage(),
							new Object[] { "BancosServiceImpl", "validarCuentaInd()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
		
		return result;
	}
	
	private void setVoidWSParameters(Solicitud sol) {
		Catalogo dummy = new Catalogo();
		dummy.setDescCatalogo("");
		dummy.setIdCatalogo((short)0);
		
		sol.setLineaNegocio(dummy);
		sol.setTipoCuenta(dummy);
		sol.setClaveBanco(dummy);
		Frecuencia frec = new Frecuencia();
		frec.setFrecuencia(dummy);
		frec.setDiaCargo((short)0);
		sol.setFrecuenciaInicial(frec);
	}
	
	public CuentaIndividualBean validarCuentaInd(Long cuenta) throws MitBusinessException {

		CuentaIndividualBean cuentaBean = new CuentaIndividualBean();
		cuentaBean.setIsVigente(false);
		try {
			IdentificarClienteEnum dentificarClienteEnum = null;
			dentificarClienteEnum = dentificarClienteEnum.IDENTIFICADOR_NUM_CUENTA;
			ConsultarClienteBasicoResp consultarClienteBasicoResp = identificarClienteService.consultarClienteBasico(dentificarClienteEnum, cuenta.toString());
			if(consultarClienteBasicoResp.getClientes() != null && consultarClienteBasicoResp.getClientes().getInformacionCliente().get(0).getInformacionBasica().getPersona()!=null)
			{
				String correoBandPref="";
				String correoPrimAct="";
				String telBanPref="";
				String telCel="";
				String telCas="";
				String telOfi="";
				
				cuentaBean.setNombreCliente(consultarClienteBasicoResp.getClientes().getInformacionCliente().get(0)
						.getInformacionBasica().getPersona().getNombre()+" "+consultarClienteBasicoResp.getClientes().getInformacionCliente().get(0)
						.getInformacionBasica().getPersona().getApellidoPaterno()+ " "+consultarClienteBasicoResp.getClientes().getInformacionCliente().get(0)
						.getInformacionBasica().getPersona().getApellidoMaterno());
				
				
				
				if(consultarClienteBasicoResp.getClientes().getInformacionCliente().get(0).getInformacionDemografica() != null)
				{
					ListaCorreos   lstCor = consultarClienteBasicoResp.getClientes().getInformacionCliente().get(0).getInformacionDemografica().getCorreos();
					ListaTelefonos lstTel = consultarClienteBasicoResp.getClientes().getInformacionCliente().get(0).getInformacionDemografica().getTelefonos();
					
					if(lstCor!=null){
						for(Correo corTmp : lstCor.getCorreo()){
							if(corTmp.isValido() && corTmp.isPreferente()){
								correoBandPref = corTmp.getEmail();
								break;
							}else if(corTmp.isValido() && correoBandPref.isEmpty()){
								correoPrimAct = corTmp.getEmail();
							}
						}
					}
					
					if(lstTel!=null){
						for(Telefono telTmp : lstTel.getTelefono()){
							if(telTmp.isValido()) {
								if(telTmp.isPreferente()){
									telBanPref=telTmp.getNumero();	
									break;
								}else if(telTmp.getTipoTelefono().getClave().equals("3")){	//TELEFONO CELULAR
									telCel = telTmp.getNumero();
								}else if(telTmp.getTipoTelefono().getClave().equals("1")){	//TELEFONO PARTICULAR
									telCas = telTmp.getNumero();
								}else if(telTmp.getTipoTelefono().getClave().equals("2")){	//TELEFONO TRABAJO
									telOfi = telTmp.getNumero();
								}
							}
						}
					}
				}
				if(!correoBandPref.isEmpty()){
					cuentaBean.setCorreo(correoBandPref);
				}else if (!correoPrimAct.isEmpty()){
					cuentaBean.setCorreo(correoPrimAct);
				}else{
					cuentaBean.setCorreo(correoBandPref);
				}
					
				if(!telBanPref.isEmpty()){
					cuentaBean.setTelefono(telBanPref);
				}else if(!telCel.isEmpty()){
					cuentaBean.setTelefono(telCel);
				}else if(!telCas.isEmpty()){
					cuentaBean.setTelefono(telCas);
				}else if(!telOfi.isEmpty()){
					cuentaBean.setTelefono(telOfi);
				}else{
					cuentaBean.setTelefono(correoBandPref);
				}
				
				List<Integer> listaIndicadores = new ArrayList<Integer>();
				listaIndicadores.add(58);
				List<Long> numerosCuenta = new ArrayList<Long>();
				numerosCuenta.add(cuenta);
				IIndicadoresSoapWSConsultaBasicaResponse response = indicadoresService.consultar(listaIndicadores, numerosCuenta);
				if(response.getResponse().getListaCuentasIndividuales().getCuentaIndividual().isEmpty())
				{
					cuentaBean.setIsVigente(false);
				}
				else
				{
					cuentaBean.setIsVigente(response.getResponse().getListaCuentasIndividuales().getCuentaIndividual().get(0).getListaConfiguracionesIndicadores().getConfiguracionIndicadores().get(0).getValorIndicador().equals("1") ? true : false);
				}
				cuentaBean.setCuentaBuc(cuenta.toString());
				
			}
			else
			{
				cuentaBean.setCuentaBuc(cuenta.toString());
				cuentaBean.setNombreCliente("No encontrado");
				cuentaBean.setCorreo("No encontrado");
				cuentaBean.setTelefono("No encontrado");
				cuentaBean.setIsVigente(false);
			}
			
			
		} catch (final MitBusinessException ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al validar cuenta",
							new Object[] { "BancosServiceImpl", "validarCuentaInd()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
		return cuentaBean;
	}

	public boolean cancelarCuentasNoConviven(List<SolicitudVO> noConviven) throws MitBusinessException{
		return generacionArchivoDomiService.cancelarCuentasNoConviven(noConviven);
	}
	
	public Date actualizaSolicitud(String cveSolicitud, Short periodicidad, Short frecIni, Short frecFin, BigDecimal impTotal, String usuario) throws MitBusinessException{
		return domiTraspasosService.actualizaDomiSol(cveSolicitud, periodicidad, frecIni, frecFin,impTotal, usuario );		
	}
	
	public boolean actualizaDiversificacion(String cveSolicitud, BigDecimal cp, BigDecimal lp, BigDecimal avpilp, BigDecimal acr, BigDecimal totalDiver, String usuario) throws MitBusinessException{

		domiTraspasosService.actualizaDomiDiversificacionVigencia(cveSolicitud, 0, usuario);
		
		MathContext mc = new MathContext(2);
		Integer total = 0;
		List<ApoVolFilter> lstInsert = new ArrayList<ApoVolFilter>(); 
		
		if(cp!=null && cp.compareTo(new BigDecimal("0"))==1){
			BigDecimal cpPor = ((cp.multiply(new BigDecimal("100"), mc)).divide(totalDiver,3,RoundingMode.CEILING));
			ApoVolFilter regCp = new ApoVolFilter();
			regCp.setFondoApovol(ConstantesCatalogos.IDAPOVOL_CP);
			regCp.setMonto(cp);
			regCp.setPorcentaje(cpPor.intValue());
			
			lstInsert.add(regCp);
			total += cpPor.intValue();
//			domiTraspasosService.insertarDomiDiversificacion(cveSolicitud, ConstantesCatalogos.IDAPOVOL_CP, cp, cpPor.intValue(), usuario, 1);
		}
		if(lp!=null && lp.compareTo(new BigDecimal("0"))==1){
			BigDecimal lpPor = ((lp.multiply(new BigDecimal("100"), mc)).divide(totalDiver,3,RoundingMode.CEILING));
			ApoVolFilter regLp = new ApoVolFilter();
			regLp.setFondoApovol(ConstantesCatalogos.IDAPOVOL_LP);
			regLp.setMonto(lp);
			regLp.setPorcentaje(lpPor.intValue());

			lstInsert.add(regLp);
			total += lpPor.intValue();
//			domiTraspasosService.insertarDomiDiversificacion(cveSolicitud, ConstantesCatalogos.IDAPOVOL_LP, lp, lpPor.intValue(), usuario, 1);
		}
		if(avpilp!=null && avpilp.compareTo(new BigDecimal("0"))==1){
			BigDecimal avpilpPor = ((avpilp.multiply(new BigDecimal("100"), mc)).divide(totalDiver,3,RoundingMode.CEILING));
			ApoVolFilter regAv = new ApoVolFilter();
			regAv.setFondoApovol(ConstantesCatalogos.IDAPOVOL_AVPILP);
			regAv.setMonto(avpilp);
			regAv.setPorcentaje(avpilpPor.intValue());

			lstInsert.add(regAv);
			total += avpilpPor.intValue();
//			domiTraspasosService.insertarDomiDiversificacion(cveSolicitud, ConstantesCatalogos.IDAPOVOL_AVPILP, avpilp, avpilpPor.intValue(), usuario, 1);
		}
		if(acr!=null && acr.compareTo(new BigDecimal("0"))==1){
			BigDecimal acrPor = ((acr.multiply(new BigDecimal("100"), mc)).divide(totalDiver,3,RoundingMode.CEILING));
			ApoVolFilter regAc = new ApoVolFilter();
			regAc.setFondoApovol(ConstantesCatalogos.IDAPOVOL_ACR);
			regAc.setMonto(acr);
			regAc.setPorcentaje(acrPor.intValue());

			lstInsert.add(regAc);
			total += acrPor.intValue();
//			domiTraspasosService.insertarDomiDiversificacion(cveSolicitud, ConstantesCatalogos.IDAPOVOL_ACR, acr, acrPor.intValue(), usuario, 1);
		}
		
		for(int i=0;i<lstInsert.size();i++){
			ApoVolFilter reg = lstInsert.get(i);
			if(i==lstInsert.size()-1){
				if(total==100){
					domiTraspasosService.insertarDomiDiversificacion(cveSolicitud, reg.getFondoApovol(), reg.getMonto(), reg.getPorcentaje(), usuario, 1);
				}else if(total<100){
					domiTraspasosService.insertarDomiDiversificacion(cveSolicitud, reg.getFondoApovol(), reg.getMonto(), reg.getPorcentaje()+(100-total), usuario, 1);
				}else{
					domiTraspasosService.insertarDomiDiversificacion(cveSolicitud, reg.getFondoApovol(), reg.getMonto(), reg.getPorcentaje()+(total-100), usuario, 1);
				}
			}else{
				domiTraspasosService.insertarDomiDiversificacion(cveSolicitud, reg.getFondoApovol(), reg.getMonto(), reg.getPorcentaje(), usuario, 1);
			}
		}
		
		return true;
	}
}
