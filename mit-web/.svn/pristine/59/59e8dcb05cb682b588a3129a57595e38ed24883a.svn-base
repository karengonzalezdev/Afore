package mx.profuturo.nci.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.events.report.ElementoReporteGeneral;
import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.report.IReportesService;
import mx.profuturo.nci.business.report.beans.CargosDataReportBean;
import mx.profuturo.nci.business.report.beans.DiversificacionesDataReportBean;
import mx.profuturo.nci.business.report.beans.DomiliacionesDataReportBean;
import mx.profuturo.nci.business.report.beans.ValidacionesDataReportBean;
import mx.profuturo.nci.business.service.ICatalogosService;
import mx.profuturo.nci.business.service.IMapaReferenciasService;
import mx.profuturo.nci.business.service.ISolicitudService;
import mx.profuturo.nci.business.util.Constantes;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.DiversificacionVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.vo.ParamCatalogoVO;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.business.vo.SolicitudVO;
import mx.profuturo.nci.business.vo.SpeiDetalleApoVolVO;
import mx.profuturo.nci.business.vo.ValidacionVO;
import mx.profuturo.nci.business.wrapped.SolicitudFilter;
import mx.profuturo.nci.web.beans.DiversificacionBean;
import mx.profuturo.nci.web.beans.FondoBean;
import mx.profuturo.nci.web.beans.GenericCatalogoBean;
import mx.profuturo.nci.web.beans.SolicitudBean;
import mx.profuturo.nci.web.beans.SolicitudFilterBean;
import mx.profuturo.nci.web.beans.ValidacionesBean;
import mx.profuturo.nci.web.beans.DetalleSpeiApoVol;
import mx.profuturo.nci.web.service.IConsultaDomiciliacionWebService;
import mx.profuturo.nci.web.util.UtilMapping;

@Service("consultaDomiciliacionWebService")
public class ConsultaDomiciliacionWebServiceImpl implements
		IConsultaDomiciliacionWebService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaDomiciliacionWebServiceImpl.class);
	
	@Autowired IMapaReferenciasService mapaReferenciasService;
	
	@Autowired ISolicitudService solicitudService;

	@Autowired ICatalogosService catalogoService;
	
	@Autowired IReportesService reporteService;
	
	public List<GenericCatalogoBean> consultarOrigenAportacion() throws MitBusinessException {
		List<GenericCatalogoBean> regs = new ArrayList<GenericCatalogoBean>();
		TreeMap<String, GenericCatalogoBean> mapOrigen = new TreeMap<String, GenericCatalogoBean>();
		
//		List<CatalogoVO> lstCataDomi = catalogoService.consultarLista(new ParamCatalogoVO(Constantes.CAT_ORIGENES_DOMICILIACION));
//		for(int i=0;i<lstCataDomi.size();i++){
//			GenericCatalogoBean reg = new GenericCatalogoBean();
//			reg.setId(lstCataDomi.get(i).getIdCatCatalogo());
//			//463--INTERNET
//			if(lstCataDomi.get(i).getIdCatCatalogo()==463){
//				reg.setValor("DOMI INTERNET");
//			}else{
//				reg.setValor(lstCataDomi.get(i).getValor());
//			}
//			if(!(lstCataDomi.get(i).getIdCatCatalogo()==1043 || lstCataDomi.get(i).getIdCatCatalogo()==1042)){
//				mapOrigen.put(reg.getValor(), reg);
//			}
//		}
		List<CatalogoVO> lstOrigen = catalogoService.consultarLista(new ParamCatalogoVO(Constantes.CAT_ORIGENES));
		for(int i=0;i<lstOrigen.size();i++){
			CatalogoVO reg = lstOrigen.get(i);
			//291--DOMICILIACIÓN    293--SUA    1097--COMPLEMENTO AFORE
			if( reg.getIdCatCatalogo()!=293 && reg.getIdCatCatalogo()!=1097 && reg.getIdCatCatalogo()!=1004){
				GenericCatalogoBean regBean = new GenericCatalogoBean();
				regBean.setId(lstOrigen.get(i).getIdCatCatalogo());
				regBean.setValor(lstOrigen.get(i).getValor());
				mapOrigen.put(lstOrigen.get(i).getValor(), regBean);
			}
		}
		
		Set set = mapOrigen.entrySet();
	    Iterator i = set.iterator();
	    while(i.hasNext()) {
	    	Map.Entry me = (Map.Entry)i.next();
	    	regs.add((GenericCatalogoBean)me.getValue());
	    } 
		
		return regs;
	}
	
	public List<GenericCatalogoBean> consultarEstatus() throws MitBusinessException {
		List<GenericCatalogoBean> regs = new ArrayList<GenericCatalogoBean>();
		
		List<CatalogoVO> lstCata = catalogoService.consultarLista(new ParamCatalogoVO(Constantes.CAT_ESTATUS));
		for(int i=0;i<lstCata.size();i++){
			GenericCatalogoBean reg = new GenericCatalogoBean();
			reg.setId(lstCata.get(i).getIdCatCatalogo());
			reg.setValor(lstCata.get(i).getValor());
			regs.add(reg);
		}
		//Atencion defecto 2305, Se agregan estatus Conciliado/No Conciliado para SPEI
		//TODO: Agregar los estatus a la BD al tipo cat 55, para ya no seguir haciendo hardcode aqui y alla.
		regs.addAll(getEstatusSPEI());
		
		Map<String,GenericCatalogoBean> mapEsta = new HashMap<String,GenericCatalogoBean>();
		for(GenericCatalogoBean bean: regs){
			mapEsta.put(bean.getValor(), bean);
		}
		Map<String,GenericCatalogoBean> treeMap = new TreeMap<String,GenericCatalogoBean>(mapEsta);
		regs = new ArrayList<GenericCatalogoBean>();
		for (Map.Entry<String,GenericCatalogoBean> entry : treeMap.entrySet()) {
			regs.add(entry.getValue());
		}
		
		return regs;
	}
	
	public List<GenericCatalogoBean> getEstatusSPEI(){
		List<GenericCatalogoBean> estatus= new ArrayList<GenericCatalogoBean>();
		GenericCatalogoBean reg1 = new GenericCatalogoBean();
		reg1.setId((short) 520);
		reg1.setValor("NO CONCILIADO");
		GenericCatalogoBean reg2 = new GenericCatalogoBean();
		reg2.setId((short) 521);
		reg2.setValor("CONCILIADO");
		estatus.add(reg1);
		estatus.add(reg2);
		
		return estatus;
	}

	public List<SolicitudBean> consultar(SolicitudFilterBean solicitudFilter) throws MitBusinessException {
		List<SolicitudBean> listaSolicitud = new ArrayList<SolicitudBean>();
		try{
			SolicitudBean solicitudBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setNumCuentaIndividual(solicitudFilter.getNumCuentaIndividual() != null ? solicitudFilter.getNumCuentaIndividual() : null);
			solicitudFilterVO.setNss(solicitudFilter.getNss() != null ? solicitudFilter.getNss() : null);
			solicitudFilterVO.setCurp(solicitudFilter.getCurp() != null ? solicitudFilter.getCurp() : null);
			solicitudFilterVO.setFechaInicio(solicitudFilter.getFechaInicio() != null ? solicitudFilter.getFechaInicio() : null);
			solicitudFilterVO.setFechaFin(solicitudFilter.getFechaFinal() != null ? solicitudFilter.getFechaFinal() : null);
			if(solicitudFilter.getEstatusSolicitud() != null){
				GenericCatalogoVO estatus = new GenericCatalogoVO();
				estatus.setId(solicitudFilter.getEstatusSolicitud().getId() == null ? null : solicitudFilter.getEstatusSolicitud().getId());
				solicitudFilterVO.setEstatusSolicitud(estatus);
			}
			if(solicitudFilter.getOrigenAportacion() != null){
				GenericCatalogoVO origenApo = new GenericCatalogoVO();
				origenApo.setId(solicitudFilter.getOrigenAportacion().getId() == null ? null : solicitudFilter.getOrigenAportacion().getId());
				solicitudFilterVO.setOrigenAportacion(origenApo);
			}
			
			List<SolicitudVO> listaSolicitudVO = solicitudService.consultarSolicitudDomiciliacion(solicitudFilterVO);
					
			for(SolicitudVO solicitudVO : listaSolicitudVO){
				solicitudBean = new SolicitudBean();
				if(solicitudVO.getClaveSolicitud() != null)
					solicitudBean.setClaveSolicitud(solicitudVO.getClaveSolicitud());
				if(solicitudVO.getNumCuentaIndividual() != null)
					solicitudBean.setNumCuentaIndividual(solicitudVO.getNumCuentaIndividual());
				if(solicitudVO.getClienteNombre() != null)
					solicitudBean.setClienteNombre(solicitudVO.getClienteNombre());
				if(solicitudVO.getClientePaterno() != null)
					solicitudBean.setClientePaterno(solicitudVO.getClientePaterno());
				if(solicitudVO.getClienteMaterno() != null)
					solicitudBean.setClienteMaterno(solicitudVO.getClienteMaterno());
				if(solicitudVO.getFolio() != null)
					solicitudBean.setFolio(solicitudVO.getFolio());
				if(solicitudVO.getImporte() != null)
					solicitudBean.setImporte(solicitudVO.getImporte());
				if(solicitudVO.getCuentaBanco() != null)
					solicitudBean.setCuentaBanco(solicitudVO.getCuentaBanco());
//				if(solicitudVO.getUsuarioActualizacion() != null)
//					solicitudBean.setUsuarioActualizacion(solicitudVO.getUsuarioActualizacion());
				if(solicitudVO.getFechaCaptura() !=null)
					solicitudBean.setFechaCaptura(solicitudVO.getFechaCaptura());
				if(solicitudVO.getFechaEstatuSolicitud() !=null)
					solicitudBean.setFechaEstatuSolicitud(solicitudVO.getFechaEstatuSolicitud());
				if(solicitudVO.getUsuarioCreacion() !=null)
					solicitudBean.setUsuarioActualizacion(solicitudVO.getUsuarioCreacion());
				if(solicitudVO.getFechaActualizacion() !=null)
					solicitudBean.setFechaReactivacion(solicitudVO.getFechaActualizacion());
				if(solicitudVO.getCuentaBanco() != null)
					solicitudBean.setCuentaBanco(solicitudVO.getCuentaBanco());
					
				if(solicitudVO.getEstatusSolicitud() != null){
					GenericCatalogoBean estatusSolicitud = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(estatusSolicitud, solicitudVO.getEstatusSolicitud());
					solicitudBean.setEstatusSolicitud(estatusSolicitud);
				}
				if(solicitudVO.getOrigenAportacion() != null){
					GenericCatalogoBean origenAportacion = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(origenAportacion, solicitudVO.getOrigenAportacion());
					solicitudBean.setOrigenAportacion(origenAportacion);
				}
				if(solicitudVO.getOrigenSolicitud() != null){
					GenericCatalogoBean origenSolicitud = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(origenSolicitud, solicitudVO.getOrigenSolicitud());
					solicitudBean.setOrigenSolicitud(origenSolicitud);
				}
				if(solicitudVO.getFondoApovol() != null){
					GenericCatalogoBean fondoApovol = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(fondoApovol, solicitudVO.getFondoApovol());
					solicitudBean.setFondoApovol(fondoApovol);
				}
				if(solicitudVO.getClaveBanco() != null){
					GenericCatalogoBean claveBanco = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(claveBanco, solicitudVO.getClaveBanco());
					solicitudBean.setClaveBanco(claveBanco);
				}
				if(solicitudVO.getPeriodo() != null){
					GenericCatalogoBean periodo = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(periodo, solicitudVO.getPeriodo());
					solicitudBean.setPeriodo(periodo);
				}
				if(solicitudVO.getTipoCuenta() != null){
					GenericCatalogoBean tipoCuenta = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(tipoCuenta, solicitudVO.getTipoCuenta());
					solicitudBean.setTipoCuenta(tipoCuenta);
				}
				if(solicitudVO.getMoneda() != null){
					GenericCatalogoBean moneda = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(moneda, solicitudVO.getMoneda());
					solicitudBean.setMoneda(moneda);
				}
				listaSolicitud.add(solicitudBean);
				
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar solicitud",
							new Object[] { "SolicitudServiceImpl", "consultar()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		return listaSolicitud;
	}

	public List<DiversificacionBean> consultaDiversificacionSol(SolicitudFilterBean solicitudFilter) throws MitBusinessException{
		List<DiversificacionBean> listaDiversif = new ArrayList<DiversificacionBean>();
		try{
			DiversificacionBean diversificacionBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setClaveSolicitud(solicitudFilter.getClaveSolicitud() != null ? solicitudFilter.getClaveSolicitud() : null);
			solicitudFilterVO.setFolio(solicitudFilter.getFolio() != null ? solicitudFilter.getFolio() : null);

			List<DiversificacionVO> diversiVo = solicitudService.consultarDiversificacionesSol(solicitudFilterVO);
			for(DiversificacionVO diversificacion : diversiVo){
				if(diversificacion!=null){
					diversificacionBean = new DiversificacionBean();
					diversificacionBean.setMonto(diversificacion.getMonto()!=null?diversificacion.getMonto().doubleValue():0);
					diversificacionBean.setPorcentajeInt(diversificacion.getPorcentaje()!=null?diversificacion.getPorcentaje().intValue():0);
					FondoBean fondoBean = new FondoBean();
					fondoBean.setId(diversificacion.getIdFondo());
					fondoBean.setDescripcion(diversificacion.getValor());
					diversificacionBean.setFondo(fondoBean);
					listaDiversif.add(diversificacionBean);
				}
			}

		}catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar diversificacion de solicitudes",
							new Object[] { "COnsutaDomiciliacionWebServiceImpl", "consultaDiversificacion(SolicitudFilterBean)" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		return listaDiversif;
	}

	public List<DiversificacionBean> consultaDiversificacionCon(SolicitudFilterBean solicitudFilter) throws MitBusinessException{
		List<DiversificacionBean> listaDiversif = new ArrayList<DiversificacionBean>();
		try{
			DiversificacionBean diversificacionBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setClaveSolicitud(solicitudFilter.getClaveSolicitud() != null ? solicitudFilter.getClaveSolicitud() : null);
			solicitudFilterVO.setIdConcil(solicitudFilter.getIdConciliacion() != null ? solicitudFilter.getIdConciliacion() : null);
			

			List<DiversificacionVO> diversiVo = solicitudService.consultarDiversificacionesCon(solicitudFilterVO);
			for(DiversificacionVO diversificacion : diversiVo){
				if(diversificacion!=null){
					diversificacionBean = new DiversificacionBean();
					diversificacionBean.setMonto(diversificacion.getMonto()!=null?diversificacion.getMonto().doubleValue():0);
					diversificacionBean.setPorcentajeInt(diversificacion.getPorcentaje()!=null?diversificacion.getPorcentaje().intValue():0);
					FondoBean fondoBean = new FondoBean();
					fondoBean.setId(diversificacion.getIdFondo());
					fondoBean.setDescripcion(diversificacion.getValor());
					diversificacionBean.setFondo(fondoBean);
					listaDiversif.add(diversificacionBean);
				}
			}

		}catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar diversificacion de solicitudes",
							new Object[] { "COnsutaDomiciliacionWebServiceImpl", "consultaDiversificacion(SolicitudFilterBean)" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		return listaDiversif;
	}

	
	public List<ValidacionesBean> consultaValidaciones(SolicitudFilterBean solicitudFilter) throws MitBusinessException{
		List<ValidacionesBean> listaValidacion = new ArrayList<ValidacionesBean>();
		try{
			ValidacionesBean validacionBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setClaveSolicitud(solicitudFilter.getClaveSolicitud() != null ? solicitudFilter.getClaveSolicitud() : null);
			solicitudFilterVO.setFolio(solicitudFilter.getFolio() != null ? solicitudFilter.getFolio() : null);

			List<ValidacionVO> validacionVo = solicitudService.consultaValidaciones(solicitudFilterVO);
			for(ValidacionVO validacion : validacionVo){
				validacionBean = new ValidacionesBean();
				
				GenericCatalogoBean tipoValidacion = new GenericCatalogoBean();
				tipoValidacion.setId(validacion.getIdValidacion());
				tipoValidacion.setValor(validacion.getTipoValidacion());
				validacionBean.setTipoValidacion(tipoValidacion);;
						
				if(validacion.getEstatusValidacion()!=null){
					GenericCatalogoBean estatus = new GenericCatalogoBean();
					estatus.setId(validacion.getEstatusValidacion().getId() != null ? validacion.getEstatusValidacion().getId() : null);
					estatus.setValor(validacion.getEstatusValidacion().getValor() != null ? validacion.getEstatusValidacion().getValor() : null);
					validacionBean.setEstatus(estatus);
				}
				
				if(validacion.getRechazoBanco()!=null){
					GenericCatalogoBean motivoRechazo = new GenericCatalogoBean();
					motivoRechazo.setId(validacion.getRechazoBanco().getId() != null ? validacion.getRechazoBanco().getId() : null);
					motivoRechazo.setValor(validacion.getRechazoBanco().getValor() != null ? validacion.getRechazoBanco().getValor() : null);
					validacionBean.setMotivoRechazo(motivoRechazo);
				}
				
				listaValidacion.add(validacionBean);
			}
		}catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar validaciones de solicitudes",
							new Object[] { "COnsutaDomiciliacionWebServiceImpl", "consultaValidaciones(SolicitudFilterBean)" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		return listaValidacion;
	}
	
	public List<SolicitudBean> consultarCorta(SolicitudFilterBean solicitudFilter) throws MitBusinessException {
		List<SolicitudBean> listaSolicitud = new ArrayList<SolicitudBean>();
		try{
			SolicitudBean solicitudBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setNumCuentaIndividual(solicitudFilter.getNumCuentaIndividual() != null ? solicitudFilter.getNumCuentaIndividual() : null);
			solicitudFilterVO.setNss(solicitudFilter.getNss() != null ? solicitudFilter.getNss() : null);
			solicitudFilterVO.setCurp(solicitudFilter.getCurp() != null ? solicitudFilter.getCurp() : null);
			solicitudFilterVO.setFechaInicio(solicitudFilter.getFechaInicio() != null ? solicitudFilter.getFechaInicio() : null);
			solicitudFilterVO.setFechaFin(solicitudFilter.getFechaFinal() != null ? solicitudFilter.getFechaFinal() : null);
			if(solicitudFilter.getEstatusSolicitud() != null){
				GenericCatalogoVO estatus = new GenericCatalogoVO();
				estatus.setId(solicitudFilter.getEstatusSolicitud().getId() == null ? null : solicitudFilter.getEstatusSolicitud().getId());
				solicitudFilterVO.setEstatusSolicitud(estatus);
			}
			if(solicitudFilter.getOrigenAportacion() != null){
				GenericCatalogoVO origenApo = new GenericCatalogoVO();
				origenApo.setId(solicitudFilter.getOrigenAportacion().getId() == null ? null : solicitudFilter.getOrigenAportacion().getId());
				solicitudFilterVO.setOrigenAportacion(origenApo);
			}
			
			List<SolicitudVO> listaSolicitudVO = solicitudService.consultarSolicitudDomiciliacionCorta(solicitudFilterVO);
					
			for(SolicitudVO solicitudVO : listaSolicitudVO){
				solicitudBean = new SolicitudBean();
				
				solicitudBean.setLlaveLista(solicitudVO.getRowId());
				if(solicitudVO.getClaveSolicitud() != null)
					solicitudBean.setClaveSolicitud(solicitudVO.getClaveSolicitud());
				if(solicitudVO.getNumCuentaIndividual() != null)
					solicitudBean.setNumCuentaIndividual(solicitudVO.getNumCuentaIndividual());
				if(solicitudVO.getClienteNombre() != null)
					solicitudBean.setClienteNombre(solicitudVO.getClienteNombre());
				if(solicitudVO.getClientePaterno() != null)
					solicitudBean.setClientePaterno(solicitudVO.getClientePaterno());
				if(solicitudVO.getClienteMaterno() != null)
					solicitudBean.setClienteMaterno(solicitudVO.getClienteMaterno());
				if(solicitudVO.getFechaEstatuSolicitud() !=null)
					solicitudBean.setFechaEstatuSolicitud(solicitudVO.getFechaEstatuSolicitud());
				if(solicitudVO.getFolio() != null)
					solicitudBean.setFolio(solicitudVO.getFolio());
				if(solicitudVO.getImporte() != null)
					solicitudBean.setImporte(solicitudVO.getImporte());
				if(solicitudVO.getCuentaBanco() != null)
					solicitudBean.setCuentaBanco(solicitudVO.getCuentaBanco());
				if(solicitudVO.getFechaActualizacion() !=null)
					solicitudBean.setFechaReactivacion(solicitudVO.getFechaActualizacion());
				if(solicitudVO.getUsuarioActualizacion() != null)
					solicitudBean.setUsuarioActualizacion(solicitudVO.getUsuarioActualizacion());
				
				if(solicitudVO.getOrigenSolicitud() != null){
					GenericCatalogoBean origenSolicitud = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(origenSolicitud, solicitudVO.getOrigenSolicitud());
					solicitudBean.setOrigenSolicitud(origenSolicitud);
				}				
				if(solicitudVO.getClaveBanco() != null){
					GenericCatalogoBean claveBanco = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(claveBanco, solicitudVO.getClaveBanco());
					solicitudBean.setClaveBanco(claveBanco);
				}
				if(solicitudVO.getEstatusSolicitud() != null){
					GenericCatalogoBean estatusSolicitud = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(estatusSolicitud, solicitudVO.getEstatusSolicitud());
					solicitudBean.setEstatusSolicitud(estatusSolicitud);
				}
				if(solicitudVO.getPeriodo() != null){
					GenericCatalogoBean periodo = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(periodo, solicitudVO.getPeriodo());
					solicitudBean.setPeriodo(periodo);
				}
				listaSolicitud.add(solicitudBean);				
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar solicitud",
							new Object[] { "SolicitudServiceImpl", "consultar()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		return listaSolicitud;
	}

	public List<SolicitudBean> consultarCuentas(SolicitudFilterBean solicitudFilter) throws MitBusinessException {
		List<SolicitudBean> listaSolicitud = new ArrayList<SolicitudBean>();
		try{
			SolicitudBean solicitudBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setNumCuentaIndividual(solicitudFilter.getNumCuentaIndividual() != null ? solicitudFilter.getNumCuentaIndividual() : null);
			solicitudFilterVO.setNss(solicitudFilter.getNss() != null ? solicitudFilter.getNss() : null);
			solicitudFilterVO.setCurp(solicitudFilter.getCurp() != null ? solicitudFilter.getCurp() : null);
			solicitudFilterVO.setFechaInicio(solicitudFilter.getFechaInicio() != null ? solicitudFilter.getFechaInicio() : null);
			solicitudFilterVO.setFechaFin(solicitudFilter.getFechaFinal() != null ? solicitudFilter.getFechaFinal() : null);
			if(solicitudFilter.getEstatusSolicitud() != null){
				GenericCatalogoVO estatus = new GenericCatalogoVO();
				estatus.setId(solicitudFilter.getEstatusSolicitud().getId() == null ? null : solicitudFilter.getEstatusSolicitud().getId());
				solicitudFilterVO.setEstatusSolicitud(estatus);
			}
			if(solicitudFilter.getOrigenAportacion() != null){
				GenericCatalogoVO origenApo = new GenericCatalogoVO();
				origenApo.setId(solicitudFilter.getOrigenAportacion().getId() == null ? null : solicitudFilter.getOrigenAportacion().getId());
				solicitudFilterVO.setOrigenAportacion(origenApo);
			}
			
			List<SolicitudVO> listaSolicitudVO = solicitudService.consultarCuentasSolicitudDomiciliacion(solicitudFilterVO);
					
			for(SolicitudVO solicitudVO : listaSolicitudVO){
				solicitudBean = new SolicitudBean();
				solicitudBean.setLlaveLista(solicitudVO.getRowId());
				
				if(solicitudVO.getClienteNombre() != null)
					solicitudBean.setClienteNombre(solicitudVO.getClienteNombre());
				if(solicitudVO.getClientePaterno() != null)
					solicitudBean.setClientePaterno(solicitudVO.getClientePaterno());
				if(solicitudVO.getClienteMaterno() != null)
					solicitudBean.setClienteMaterno(solicitudVO.getClienteMaterno());
				if(solicitudVO.getNumCuentaIndividual() != null)
					solicitudBean.setNumCuentaIndividual(solicitudVO.getNumCuentaIndividual());
				if(solicitudVO.getCurp() != null)
					solicitudBean.setCurp(solicitudVO.getCurp());
				if(solicitudVO.getNss() != null)
					solicitudBean.setNss(solicitudVO.getNss());
				if(solicitudVO.getClienteRfc() != null)
					solicitudBean.setClienteRfc(solicitudVO.getClienteRfc());
				
				listaSolicitud.add(solicitudBean);				
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar cuentas de solicitud",
							new Object[] { "SolicitudServiceImpl", "consultar()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		return listaSolicitud;
	}
	
	public List<SolicitudBean> consultarHistSolicitud(SolicitudFilterBean claveSolicitud, Short estatusSol)throws MitBusinessException {
		List<SolicitudBean> listaSolicitud = new ArrayList<SolicitudBean>();
		try{
			SolicitudBean solicitudBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setFolio(claveSolicitud.getFolio() != null ? claveSolicitud.getFolio() : null);
			solicitudFilterVO.setClaveSolicitud(claveSolicitud.getClaveSolicitud() != null ? claveSolicitud.getClaveSolicitud() : null);
			solicitudFilterVO.setNumCuentaIndividual(claveSolicitud.getNumCuentaIndividual() != null ? claveSolicitud.getNumCuentaIndividual() : null);
			
			List<SolicitudVO> listaSolicitudVO;
			if(claveSolicitud.getOrigenSolicitud()!=null && (claveSolicitud.getOrigenSolicitud().getId().equals(new Short("485"))
			|| claveSolicitud.getOrigenSolicitud().getId().equals(new Short("462")) || claveSolicitud.getOrigenSolicitud().getId().equals(new Short("463"))
			|| claveSolicitud.getOrigenSolicitud().getId().equals(new Short("465")) || claveSolicitud.getOrigenSolicitud().getId().equals(new Short("464"))
			|| claveSolicitud.getOrigenSolicitud().getId().equals(new Short("466")) || claveSolicitud.getOrigenSolicitud().getId().equals(new Short("1043")))){
				listaSolicitudVO = solicitudService.consultarHistSolicitud(solicitudFilterVO);
			}else{
				listaSolicitudVO = solicitudService.consultarHistConciliacion(solicitudFilterVO);
			}
			
			for(SolicitudVO solicitudVO : listaSolicitudVO){
				solicitudBean = new SolicitudBean();
				if(solicitudVO.getNumCuentaIndividual() != null)
					solicitudBean.setNumCuentaIndividual(solicitudVO.getNumCuentaIndividual());
				if(solicitudVO.getImporte() != null)
					solicitudBean.setImporte(solicitudVO.getImporte());
				if(solicitudVO.getFechaEstatuSolicitud() != null)
					solicitudBean.setFechaEstatuSolicitud(solicitudVO.getFechaEstatuSolicitud());
				if(solicitudVO.getFechaCargo() != null)
					solicitudBean.setFechaCargo(solicitudVO.getFechaCargo());
				
				if(solicitudVO.getEstatusCargo() != null){
					GenericCatalogoBean estatusCargo = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(estatusCargo, solicitudVO.getEstatusCargo());
					solicitudBean.setEstatusCargo(estatusCargo);
				}
				if(solicitudVO.getRechazoBanco() != null){
					GenericCatalogoBean rechazoBanco = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(rechazoBanco, solicitudVO.getRechazoBanco());
					solicitudBean.setRechazoBanco(rechazoBanco);
				}
				if(solicitudVO.getPeriodo() != null){
					GenericCatalogoBean periodo = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(periodo, solicitudVO.getPeriodo());
					solicitudBean.setPeriodo(periodo);
				}
				if(solicitudVO.getFondoApovol() != null){
					GenericCatalogoBean fondoApovol = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(fondoApovol, solicitudVO.getFondoApovol());
					solicitudBean.setFondoApovol(fondoApovol);
				}
				
				listaSolicitud.add(solicitudBean);
			}

			//ESTATUS SOL   -- 511--RECHAZADO, 513--CANCELADO
			//ESTATUS CARGO -- 501--RECHAZADO, 500--APLICADO
			if(estatusSol!= null && (estatusSol.equals(new Short("511")) || estatusSol.equals(new Short("513")))){
				List<SolicitudBean> listaSolicitudFiltro = new ArrayList<SolicitudBean>(); 
				for(SolicitudBean beanFin:listaSolicitud){
					if(beanFin.getEstatusCargo()!=null && (beanFin.getEstatusCargo().getId().equals(new Short("501"))||beanFin.getEstatusCargo().getId().equals(new Short("500")))){
						listaSolicitudFiltro.add(beanFin);
					}
				}
				
				return listaSolicitudFiltro;
			}

		
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar solicitud",
							new Object[] { "SolicitudServiceImpl", "consultarHistSolicitud()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		return listaSolicitud;
	}

	
	public List<SolicitudBean> consultarDetalleSolNoDomi(SolicitudFilterBean claveSolicitud)throws MitBusinessException {
		List<SolicitudBean> listaSolicitud = new ArrayList<SolicitudBean>();
		try{
			SolicitudBean solicitudBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setClaveSolicitud(claveSolicitud.getClaveSolicitud() != null ? claveSolicitud.getClaveSolicitud() : null);
			
			GenericCatalogoVO solicitudEstBean = new GenericCatalogoVO();
			solicitudEstBean.setId((claveSolicitud.getEstatusSolicitud()!=null && claveSolicitud.getEstatusSolicitud().getId()!=null) ? claveSolicitud.getEstatusSolicitud().getId() : null);
			
			solicitudFilterVO.setFechaInicio(claveSolicitud.getFechaInicio() != null ? claveSolicitud.getFechaInicio() : null);
			solicitudFilterVO.setFechaFin(claveSolicitud.getFechaFinal() != null ? claveSolicitud.getFechaFinal() : null);
			
			solicitudFilterVO.setEstatusSolicitud(solicitudEstBean);
		
			List<SolicitudVO> listaSolicitudVO = solicitudService.consultarDetSolicitudNoDomi(solicitudFilterVO);
			for(SolicitudVO solicitudVO : listaSolicitudVO){
				solicitudBean = new SolicitudBean();
				if(solicitudVO.getFolio() != null)
					solicitudBean.setFolio(solicitudVO.getFolio());
				if(solicitudVO.getClienteNombre() != null)
					solicitudBean.setClienteNombre(solicitudVO.getClienteNombre());
				if(solicitudVO.getClientePaterno() != null)
					solicitudBean.setClientePaterno(solicitudVO.getClientePaterno());
				if(solicitudVO.getClienteMaterno() != null)
					solicitudBean.setClienteMaterno(solicitudVO.getClienteMaterno());
				if(solicitudVO.getFechaCaptura() != null)
					solicitudBean.setFechaCaptura(solicitudVO.getFechaCaptura());
				if(solicitudVO.getUsuarioCreacion() != null)
					solicitudBean.setUsuarioActualizacion(solicitudVO.getUsuarioCreacion());
				if(solicitudVO.getImporte() != null)
					solicitudBean.setImporte(solicitudVO.getImporte());
				if(solicitudVO.getFechaEstatuSolicitud() != null)
					solicitudBean.setFechaEstatuSolicitud(solicitudVO.getFechaEstatuSolicitud());
				if(solicitudVO.getCuentaBanco() != null)
					solicitudBean.setCuentaBanco(solicitudVO.getCuentaBanco());
				
				if(solicitudVO.getOrigenAportacion() != null){
					GenericCatalogoBean origenAportacion = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(origenAportacion, solicitudVO.getOrigenAportacion());
					solicitudBean.setOrigenAportacion(origenAportacion);
				}
				if(solicitudVO.getClaveBanco() != null){
					GenericCatalogoBean claveBanco = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(claveBanco, solicitudVO.getClaveBanco());
					solicitudBean.setClaveBanco(claveBanco);
				}
				if(solicitudVO.getPeriodo() != null){
					GenericCatalogoBean periodo = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(periodo, solicitudVO.getPeriodo());
					solicitudBean.setPeriodo(periodo);
				}
				if(solicitudVO.getEstatusCargo() != null){
					GenericCatalogoBean estatusCargo = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(estatusCargo, solicitudVO.getEstatusCargo());
					solicitudBean.setEstatusCargo(estatusCargo);
				}
				if(solicitudVO.getTipoCuenta() != null){
					GenericCatalogoBean tipoCuenta = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(tipoCuenta, solicitudVO.getTipoCuenta());
					solicitudBean.setTipoCuenta(tipoCuenta);
				}
				if(solicitudVO.getPeriodoIncrementabilidad() != null){
					GenericCatalogoBean periodoIncrementabilidad = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(periodoIncrementabilidad, solicitudVO.getPeriodoIncrementabilidad());
					solicitudBean.setPeriodoIncrementabilidad(periodoIncrementabilidad);
				}
				
				listaSolicitud.add(solicitudBean);
			}
		
		
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar solicitud no domi",
							new Object[] { "SolicitudServiceImpl", "consultarDetalleSolNoDomi()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		return listaSolicitud;
	}
	
	public ReporteVO generaReporte(List<SolicitudBean> listaSolicitud) throws MitBusinessException{
		List<DomiliacionesDataReportBean> dataReportBean = convert(listaSolicitud);
		ReporteVO reporte = reporteService.generaReporteDomiliacion(dataReportBean);
		return reporte;
	}	
	
	private List<DomiliacionesDataReportBean> convert(List<SolicitudBean> vBeans) {
		List<DomiliacionesDataReportBean> reporteBeans= new ArrayList<DomiliacionesDataReportBean>();
		for(SolicitudBean vBean:vBeans){
			DomiliacionesDataReportBean rB = new DomiliacionesDataReportBean();
			
			rB.setClaveSolicitud(vBean.getClaveSolicitud());
			rB.setFechaSolicitud(vBean.getFechaEstatuSolicitud());
			rB.setOrigen(vBean.getOrigenSolicitud().getValor());
			
			rB.setCuenta(vBean.getNumCuentaIndividual());
			rB.setNombre(vBean.getClienteNombre()+" "+vBean.getClientePaterno()+" "+vBean.getClienteMaterno());
			rB.setImporte(vBean.getImporte());
			rB.setBanco(vBean.getClaveBanco().getValor());
			rB.setCuentaBanco(vBean.getCuentaBanco());
			
			if(vBean.getPeriodo()!=null){
				rB.setPeriodo(vBean.getPeriodo().getValor());
			}else{
				rB.setPeriodo("");
			}
			
			rB.setEstatus(vBean.getEstatusSolicitud().getValor());
			rB.setFechaModificacion(vBean.getFechaReactivacion());
			rB.setUsuario(vBean.getUsuarioActualizacion());
			
			reporteBeans.add(rB);
		}
		return reporteBeans;
	}
	
	
	public ReporteVO generaReporteDetallado(List<DiversificacionBean> lstDiversificacion, List<ValidacionesBean> lstValidacion, 
			   List<SolicitudBean> lstCargos) throws MitBusinessException{
			List<DiversificacionesDataReportBean> diverReportBean = convertDiver(lstDiversificacion);
			List<ValidacionesDataReportBean>      validReportBean = convertValid(lstValidacion);
			List<CargosDataReportBean>            cargoReportBean = convertCargos(lstCargos);
			ReporteVO reporte = reporteService.generaReporteDetalleDomiliacion(diverReportBean, validReportBean, cargoReportBean);
			return reporte;
		}
		
		private List<DiversificacionesDataReportBean> convertDiver(List<DiversificacionBean> lstDiver) {
			List<DiversificacionesDataReportBean> reporteBeans= new ArrayList<DiversificacionesDataReportBean>();
			for(DiversificacionBean dBean:lstDiver){
				DiversificacionesDataReportBean dB = new DiversificacionesDataReportBean();

				dB.setFondo(dBean.getFondo().getDescripcion());
				dB.setMonto(dBean.getMonto());
				dB.setPorcentaje(Double.parseDouble(dBean.getPorcentajeInt().toString())/100);
				
				reporteBeans.add(dB);
			}
			return reporteBeans;
		}
		
		private List<ValidacionesDataReportBean> convertValid(List<ValidacionesBean> lstValid) {
			List<ValidacionesDataReportBean> validacionBeans= new ArrayList<ValidacionesDataReportBean>();
			for(ValidacionesBean vBean:lstValid){
				ValidacionesDataReportBean vB = new ValidacionesDataReportBean();
				if(vBean.getTipoValidacion()!=null && vBean.getTipoValidacion().getValor()!=null){
					vB.setValidacion(vBean.getTipoValidacion().getValor());
				}
				if(vBean.getEstatus()!=null && vBean.getEstatus().getValor()!=null){
					vB.setEstatus(vBean.getEstatus().getValor());
				}
				if(vBean.getMotivoRechazo()!=null && vBean.getMotivoRechazo().getValor()!=null){
					vB.setMotivoRechazo(vBean.getMotivoRechazo().getValor());
				}
				
				validacionBeans.add(vB);
			}
			return validacionBeans;
		}
		
		private List<CargosDataReportBean> convertCargos(List<SolicitudBean> lstCargos) {
			List<CargosDataReportBean> cargosBeans= new ArrayList<CargosDataReportBean>();
			for(SolicitudBean cBean:lstCargos){
				CargosDataReportBean cB = new CargosDataReportBean();

				cB.setNumCuentaIndividual(cBean.getNumCuentaIndividual());
				cB.setImporte(cBean.getImporte());
				cB.setFechaEstatus(cBean.getFechaEstatuSolicitud());
				if(cBean.getEstatusCargo()!=null && cBean.getEstatusCargo().getValor()!=null){
					cB.setEstatusCargo(cBean.getEstatusCargo().getValor());
				}
				if(cBean.getRechazoBanco()!=null && cBean.getRechazoBanco().getValor()!=null){
					cB.setRechazoBanco(cBean.getRechazoBanco().getValor());
				}
				if(cBean.getPeriodo()!=null && cBean.getPeriodo().getValor()!=null){
					cB.setPeriodo(cBean.getPeriodo().getValor());
				}
					
				cargosBeans.add(cB);
			}
			return cargosBeans;
			
		}

		public DetalleSpeiApoVol obtenerDetalleSpei(String folio) throws MitBusinessException{
			DetalleSpeiApoVol reg = new DetalleSpeiApoVol();
			SolicitudFilter solFilter = new SolicitudFilter();
			solFilter.setFolio(folio);
			
			List<SpeiDetalleApoVolVO> reto = solicitudService.consultaDetalleSpei(solFilter);
			for(int i=0;i<reto.size();i++){
				reg.setReferencia(reto.get(i).getReferencia() != null ? reto.get(i).getReferencia() : "");
				reg.setFechaDeposito(reto.get(i).getFechaDeposito() != null ? reto.get(i).getFechaDeposito() : null);
				reg.setNoOrden(reto.get(i).getNumeroOrden() != null ? reto.get(i).getNumeroOrden(): "");
				reg.setFechaCaptura(reto.get(i).getFechaCreacion() != null ? reto.get(i).getFechaCreacion() : null);
				reg.setUsuarioCaptura(reto.get(i).getUsuarioCreacion() != null ? reto.get(i).getUsuarioCreacion() : "");
			}
			return reg;
		}
		
		public DetalleSpeiApoVol obtenerDetalleNomina(String folio, Long numCuentaIndividual) throws MitBusinessException{
			DetalleSpeiApoVol reg = new DetalleSpeiApoVol();
			SolicitudFilter solFilter = new SolicitudFilter();
			solFilter.setFolio(folio);
			solFilter.setNumCuentaIndividual(numCuentaIndividual);
			
			List<SpeiDetalleApoVolVO> reto = solicitudService.consultaDetalleNomina(solFilter);
			for(int i=0;i<reto.size();i++){
				reg.setEmpresa(reto.get(i).getEmpresa() != null ? reto.get(i).getEmpresa() : "");
				reg.setNoEmpleado(reto.get(i).getNoEmpleado() != null ? reto.get(i).getNoEmpleado() : "");
				reg.setTipoNomina(reto.get(i).getTipoNomina() != null ? reto.get(i).getTipoNomina() : "");
			}
			return reg;
		}
		
	public List<SolicitudBean> consultarCuentasSolici(SolicitudFilterBean solicitudFilter, Short escenario) throws MitBusinessException {
		List<SolicitudBean> listaSolicitud = new ArrayList<SolicitudBean>();

		try{
			SolicitudBean solicitudBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setNumCuentaIndividual(solicitudFilter.getNumCuentaIndividual() != null ? solicitudFilter.getNumCuentaIndividual() : null);
			solicitudFilterVO.setNss(solicitudFilter.getNss() != null ? solicitudFilter.getNss() : null);
			solicitudFilterVO.setCurp(solicitudFilter.getCurp() != null ? solicitudFilter.getCurp() : null);
			solicitudFilterVO.setFechaInicio(solicitudFilter.getFechaInicio() != null ? solicitudFilter.getFechaInicio() : null);
			solicitudFilterVO.setFechaFin(solicitudFilter.getFechaFinal() != null ? solicitudFilter.getFechaFinal() : null);
			solicitudFilterVO.setSubOrigen(solicitudFilter.getSubOrigenes() != null ? solicitudFilter.getSubOrigenes() : null);
			if(solicitudFilter.getEstatusSolicitud() != null){
				GenericCatalogoVO estatus = new GenericCatalogoVO();
				estatus.setId(solicitudFilter.getEstatusSolicitud().getId() == null ? null : solicitudFilter.getEstatusSolicitud().getId());
				solicitudFilterVO.setEstatusSolicitud(estatus);
			}
			if(solicitudFilter.getOrigenAportacion() != null){
				GenericCatalogoVO origenApo = new GenericCatalogoVO();
				origenApo.setId(solicitudFilter.getOrigenAportacion().getId() == null ? null : solicitudFilter.getOrigenAportacion().getId());
				solicitudFilterVO.setOrigenAportacion(origenApo);
			}
			
			List<SolicitudVO> listaSolicitudVO = solicitudService.consultarCuentasSolicitud(solicitudFilterVO);
					
			for(SolicitudVO solicitudVO : listaSolicitudVO){
				solicitudBean = new SolicitudBean();
				solicitudBean.setLlaveLista(solicitudVO.getRowId());
				
				if(solicitudVO.getClienteNombre() != null)
					solicitudBean.setClienteNombre(solicitudVO.getClienteNombre());
				if(solicitudVO.getClientePaterno() != null)
					solicitudBean.setClientePaterno(solicitudVO.getClientePaterno());
				if(solicitudVO.getClienteMaterno() != null)
					solicitudBean.setClienteMaterno(solicitudVO.getClienteMaterno());
				if(solicitudVO.getNumCuentaIndividual() != null)
					solicitudBean.setNumCuentaIndividual(solicitudVO.getNumCuentaIndividual());
				if(solicitudVO.getCurp() != null)
					solicitudBean.setCurp(solicitudVO.getCurp());
				if(solicitudVO.getNss() != null)
					solicitudBean.setNss(solicitudVO.getNss());
				if(solicitudVO.getClienteRfc() != null)
					solicitudBean.setClienteRfc(solicitudVO.getClienteRfc());
				
				solicitudBean.setEscenario(escenario); //TABLA DE SOLICITUD
				listaSolicitud.add(solicitudBean);				
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar cuentas de solicitud",
							new Object[] { "SolicitudServiceImpl", "consultar()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		
		return listaSolicitud;
	}
	
	public List<SolicitudBean> consultarCuentasConcil(SolicitudFilterBean solicitudFilter) throws MitBusinessException {
		List<SolicitudBean> listaSolicitud = new ArrayList<SolicitudBean>();
		
		try{
			SolicitudBean solicitudBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setNumCuentaIndividual(solicitudFilter.getNumCuentaIndividual() != null ? solicitudFilter.getNumCuentaIndividual() : null);
			solicitudFilterVO.setNss(solicitudFilter.getNss() != null ? solicitudFilter.getNss() : null);
			solicitudFilterVO.setCurp(solicitudFilter.getCurp() != null ? solicitudFilter.getCurp() : null);
			solicitudFilterVO.setFechaInicio(solicitudFilter.getFechaInicio() != null ? solicitudFilter.getFechaInicio() : null);
			solicitudFilterVO.setFechaFin(solicitudFilter.getFechaFinal() != null ? solicitudFilter.getFechaFinal() : null);
			if(solicitudFilter.getEstatusSolicitud() != null){
				GenericCatalogoVO estatus = new GenericCatalogoVO();
				estatus.setId(solicitudFilter.getEstatusSolicitud().getId() == null ? null : solicitudFilter.getEstatusSolicitud().getId());
				solicitudFilterVO.setEstatusSolicitud(estatus);
			}
			if(solicitudFilter.getOrigenAportacion() != null){
				GenericCatalogoVO origenApo = new GenericCatalogoVO();
				origenApo.setId(solicitudFilter.getOrigenAportacion().getId() == null ? null : solicitudFilter.getOrigenAportacion().getId());
				solicitudFilterVO.setOrigenAportacion(origenApo);
			}
			
			List<SolicitudVO> listaSolicitudVO = solicitudService.consultarCuentasConciliacion(solicitudFilterVO);
					
			for(SolicitudVO solicitudVO : listaSolicitudVO){
				solicitudBean = new SolicitudBean();
				solicitudBean.setLlaveLista(solicitudVO.getRowId());
				
				if(solicitudVO.getClienteNombre() != null)
					solicitudBean.setClienteNombre(solicitudVO.getClienteNombre());
				if(solicitudVO.getClientePaterno() != null)
					solicitudBean.setClientePaterno(solicitudVO.getClientePaterno());
				if(solicitudVO.getClienteMaterno() != null)
					solicitudBean.setClienteMaterno(solicitudVO.getClienteMaterno());
				if(solicitudVO.getNumCuentaIndividual() != null)
					solicitudBean.setNumCuentaIndividual(solicitudVO.getNumCuentaIndividual());
				if(solicitudVO.getCurp() != null)
					solicitudBean.setCurp(solicitudVO.getCurp());
				if(solicitudVO.getNss() != null)
					solicitudBean.setNss(solicitudVO.getNss());
				if(solicitudVO.getClienteRfc() != null)
					solicitudBean.setClienteRfc(solicitudVO.getClienteRfc());
				solicitudBean.setEscenario(new Short("4")); //TABLA DE CONCILIACION
				listaSolicitud.add(solicitudBean);				
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar cuentas de conciliacion",
							new Object[] { "SolicitudServiceImpl", "consultar()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		
		return listaSolicitud;
	}
	
	public List<SolicitudBean> consultarCuentasBancos(SolicitudFilterBean solicitudFilter) throws MitBusinessException {
		List<SolicitudBean> listaSolicitud = new ArrayList<SolicitudBean>();

		try{
			SolicitudBean solicitudBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setNumCuentaIndividual(solicitudFilter.getNumCuentaIndividual() != null ? solicitudFilter.getNumCuentaIndividual() : null);
			solicitudFilterVO.setNss(solicitudFilter.getNss() != null ? solicitudFilter.getNss() : null);
			solicitudFilterVO.setCurp(solicitudFilter.getCurp() != null ? solicitudFilter.getCurp() : null);
			solicitudFilterVO.setFechaInicio(solicitudFilter.getFechaInicio() != null ? solicitudFilter.getFechaInicio() : null);
			solicitudFilterVO.setFechaFin(solicitudFilter.getFechaFinal() != null ? solicitudFilter.getFechaFinal() : null);
			if(solicitudFilter.getEstatusSolicitud() != null){
				GenericCatalogoVO estatus = new GenericCatalogoVO();
				estatus.setId(solicitudFilter.getEstatusSolicitud().getId() == null ? null : solicitudFilter.getEstatusSolicitud().getId());
				solicitudFilterVO.setEstatusSolicitud(estatus);
			}
			if(solicitudFilter.getOrigenAportacion() != null){
				GenericCatalogoVO origenApo = new GenericCatalogoVO();
				origenApo.setId(solicitudFilter.getOrigenAportacion().getId() == null ? null : solicitudFilter.getOrigenAportacion().getId());
				solicitudFilterVO.setOrigenAportacion(origenApo);
			}
			
			List<SolicitudVO> listaSolicitudVO = solicitudService.consultarCuentasBancos(solicitudFilterVO);
					
			for(SolicitudVO solicitudVO : listaSolicitudVO){
				solicitudBean = new SolicitudBean();
				solicitudBean.setLlaveLista(solicitudVO.getRowId());
				
				if(solicitudVO.getClienteNombre() != null)
					solicitudBean.setClienteNombre(solicitudVO.getClienteNombre());
				if(solicitudVO.getClientePaterno() != null)
					solicitudBean.setClientePaterno(solicitudVO.getClientePaterno());
				if(solicitudVO.getClienteMaterno() != null)
					solicitudBean.setClienteMaterno(solicitudVO.getClienteMaterno());
				if(solicitudVO.getNumCuentaIndividual() != null)
					solicitudBean.setNumCuentaIndividual(solicitudVO.getNumCuentaIndividual());
				if(solicitudVO.getCurp() != null)
					solicitudBean.setCurp(solicitudVO.getCurp());
				if(solicitudVO.getNss() != null)
					solicitudBean.setNss(solicitudVO.getNss());
				if(solicitudVO.getClienteRfc() != null)
					solicitudBean.setClienteRfc(solicitudVO.getClienteRfc());
				
				solicitudBean.setEscenario(new Short("5")); //TABLA DE BANCOS
				listaSolicitud.add(solicitudBean);				
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar cuentas de bancos",
							new Object[] { "SolicitudServiceImpl", "consultar()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		
		return listaSolicitud;
	}
	
	public List<SolicitudBean> consultarCuentasSpei(SolicitudFilterBean solicitudFilter) throws MitBusinessException {
		List<SolicitudBean> listaSolicitud = new ArrayList<SolicitudBean>();
		
		try{
			SolicitudBean solicitudBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setNumCuentaIndividual(solicitudFilter.getNumCuentaIndividual() != null ? solicitudFilter.getNumCuentaIndividual() : null);
			solicitudFilterVO.setNss(solicitudFilter.getNss() != null ? solicitudFilter.getNss() : null);
			solicitudFilterVO.setCurp(solicitudFilter.getCurp() != null ? solicitudFilter.getCurp() : null);
			solicitudFilterVO.setFechaInicio(solicitudFilter.getFechaInicio() != null ? solicitudFilter.getFechaInicio() : null);
			solicitudFilterVO.setFechaFin(solicitudFilter.getFechaFinal() != null ? solicitudFilter.getFechaFinal() : null);
			if(solicitudFilter.getEstatusSolicitud() != null){
				GenericCatalogoVO estatus = new GenericCatalogoVO();
				estatus.setId(solicitudFilter.getEstatusSolicitud().getId() == null ? null : solicitudFilter.getEstatusSolicitud().getId());
				solicitudFilterVO.setEstatusSolicitud(estatus);
			}
			if(solicitudFilter.getOrigenAportacion() != null){
				GenericCatalogoVO origenApo = new GenericCatalogoVO();
				origenApo.setId(solicitudFilter.getOrigenAportacion().getId() == null ? null : solicitudFilter.getOrigenAportacion().getId());
				solicitudFilterVO.setOrigenAportacion(origenApo);
			}
			
			List<SolicitudVO> listaSolicitudVO = solicitudService.consultarCuentasSpei(solicitudFilterVO);
					
			for(SolicitudVO solicitudVO : listaSolicitudVO){
				solicitudBean = new SolicitudBean();
				solicitudBean.setLlaveLista(solicitudVO.getRowId());
				
				if(solicitudVO.getClienteNombre() != null)
					solicitudBean.setClienteNombre(solicitudVO.getClienteNombre());
				if(solicitudVO.getClientePaterno() != null)
					solicitudBean.setClientePaterno(solicitudVO.getClientePaterno());
				if(solicitudVO.getClienteMaterno() != null)
					solicitudBean.setClienteMaterno(solicitudVO.getClienteMaterno());
				if(solicitudVO.getNumCuentaIndividual() != null)
					solicitudBean.setNumCuentaIndividual(solicitudVO.getNumCuentaIndividual());
				if(solicitudVO.getCurp() != null)
					solicitudBean.setCurp(solicitudVO.getCurp());
				if(solicitudVO.getNss() != null)
					solicitudBean.setNss(solicitudVO.getNss());
				if(solicitudVO.getClienteRfc() != null)
					solicitudBean.setClienteRfc(solicitudVO.getClienteRfc());
				
				solicitudBean.setEscenario(new Short("6")); //TABLA DE SPEI
				listaSolicitud.add(solicitudBean);				
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar cuentas de SPEI",
							new Object[] { "SolicitudServiceImpl", "consultar()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		
		return listaSolicitud;
	}
	
	public List<SolicitudBean> consultarCuentasTodos(SolicitudFilterBean solicitudFilter) throws MitBusinessException {
		List<SolicitudBean> listaSolicitud = new ArrayList<SolicitudBean>();
		try{
			SolicitudBean solicitudBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setNumCuentaIndividual(solicitudFilter.getNumCuentaIndividual() != null ? solicitudFilter.getNumCuentaIndividual() : null);
			solicitudFilterVO.setNss(solicitudFilter.getNss() != null ? solicitudFilter.getNss() : null);
			solicitudFilterVO.setCurp(solicitudFilter.getCurp() != null ? solicitudFilter.getCurp() : null);
			solicitudFilterVO.setFechaInicio(solicitudFilter.getFechaInicio() != null ? solicitudFilter.getFechaInicio() : null);
			solicitudFilterVO.setFechaFin(solicitudFilter.getFechaFinal() != null ? solicitudFilter.getFechaFinal() : null);
			if(solicitudFilter.getEstatusSolicitud() != null){
				GenericCatalogoVO estatus = new GenericCatalogoVO();
				estatus.setId(solicitudFilter.getEstatusSolicitud().getId() == null ? null : solicitudFilter.getEstatusSolicitud().getId());
				solicitudFilterVO.setEstatusSolicitud(estatus);
			}
			
			List<SolicitudVO> listaSolicitudVO = solicitudService.consultarCuentasTodos(solicitudFilterVO);
					
			for(SolicitudVO solicitudVO : listaSolicitudVO){
				solicitudBean = new SolicitudBean();
				solicitudBean.setLlaveLista(solicitudVO.getRowId());
				
				if(solicitudVO.getClienteNombre() != null)
					solicitudBean.setClienteNombre(solicitudVO.getClienteNombre());
				if(solicitudVO.getClientePaterno() != null)
					solicitudBean.setClientePaterno(solicitudVO.getClientePaterno());
				if(solicitudVO.getClienteMaterno() != null)
					solicitudBean.setClienteMaterno(solicitudVO.getClienteMaterno());
				if(solicitudVO.getNumCuentaIndividual() != null)
					solicitudBean.setNumCuentaIndividual(solicitudVO.getNumCuentaIndividual());
				if(solicitudVO.getCurp() != null)
					solicitudBean.setCurp(solicitudVO.getCurp());
				if(solicitudVO.getNss() != null)
					solicitudBean.setNss(solicitudVO.getNss());
				if(solicitudVO.getClienteRfc() != null)
					solicitudBean.setClienteRfc(solicitudVO.getClienteRfc());
				
				solicitudBean.setEscenario(new Short("0")); //TABLA DE SPEI
				listaSolicitud.add(solicitudBean);				
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar cuentas de TODOS los origenes",
							new Object[] { "SolicitudServiceImpl", "consultarCuentasTodos()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		
		return listaSolicitud;
	}
	
	public List<SolicitudBean> consultarListSolicitud(SolicitudFilterBean solicitudFilter) throws MitBusinessException {
		List<SolicitudBean> listaSolicitud = new ArrayList<SolicitudBean>();

		try{
			SolicitudBean solicitudBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setNumCuentaIndividual(solicitudFilter.getNumCuentaIndividual() != null ? solicitudFilter.getNumCuentaIndividual() : null);
			solicitudFilterVO.setNss(solicitudFilter.getNss() != null ? solicitudFilter.getNss() : null);
			solicitudFilterVO.setCurp(solicitudFilter.getCurp() != null ? solicitudFilter.getCurp() : null);
			solicitudFilterVO.setFechaInicio(solicitudFilter.getFechaInicio() != null ? solicitudFilter.getFechaInicio() : null);
			solicitudFilterVO.setFechaFin(solicitudFilter.getFechaFinal() != null ? solicitudFilter.getFechaFinal() : null);
			solicitudFilterVO.setSubOrigen(solicitudFilter.getSubOrigenes() != null ? solicitudFilter.getSubOrigenes() : null);
			if(solicitudFilter.getEstatusSolicitud() != null){
				GenericCatalogoVO estatus = new GenericCatalogoVO();
				estatus.setId(solicitudFilter.getEstatusSolicitud().getId() == null ? null : solicitudFilter.getEstatusSolicitud().getId());
				solicitudFilterVO.setEstatusSolicitud(estatus);
			}
			
			List<SolicitudVO> listaSolicitudVO = solicitudService.consultarSolicitudDomiciliacionSolicitud(solicitudFilterVO);
					
			for(SolicitudVO solicitudVO : listaSolicitudVO){
				solicitudBean = new SolicitudBean();
				
				solicitudBean.setLlaveLista(solicitudVO.getRowId());
				if(solicitudVO.getClaveSolicitud() != null)
					solicitudBean.setClaveSolicitud(solicitudVO.getClaveSolicitud());
				if(solicitudVO.getNumCuentaIndividual() != null)
					solicitudBean.setNumCuentaIndividual(solicitudVO.getNumCuentaIndividual());
				if(solicitudVO.getClienteNombre() != null)
					solicitudBean.setClienteNombre(solicitudVO.getClienteNombre());
				if(solicitudVO.getClientePaterno() != null)
					solicitudBean.setClientePaterno(solicitudVO.getClientePaterno());
				if(solicitudVO.getClienteMaterno() != null)
					solicitudBean.setClienteMaterno(solicitudVO.getClienteMaterno());
				if(solicitudVO.getFechaEstatuSolicitud() !=null)
					solicitudBean.setFechaEstatuSolicitud(solicitudVO.getFechaEstatuSolicitud());
				if(solicitudVO.getFolio() != null)
					solicitudBean.setFolio(solicitudVO.getFolio());
				if(solicitudVO.getImporte() != null)
					solicitudBean.setImporte(solicitudVO.getImporte());
				if(solicitudVO.getCuentaBanco() != null)
					solicitudBean.setCuentaBanco(solicitudVO.getCuentaBanco());
				if(solicitudVO.getFechaActualizacion() !=null)
					solicitudBean.setFechaReactivacion(solicitudVO.getFechaActualizacion());
				if(solicitudVO.getUsuarioActualizacion() != null)
					solicitudBean.setUsuarioActualizacion(solicitudVO.getUsuarioActualizacion());
				
				if(solicitudVO.getOrigenSolicitud() != null){
					GenericCatalogoBean origenSolicitud = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(origenSolicitud, solicitudVO.getOrigenSolicitud());
					solicitudBean.setOrigenSolicitud(origenSolicitud);
				}				
				if(solicitudVO.getClaveBanco() != null){
					GenericCatalogoBean claveBanco = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(claveBanco, solicitudVO.getClaveBanco());
					solicitudBean.setClaveBanco(claveBanco);
				}
				if(solicitudVO.getEstatusSolicitud() != null){
					GenericCatalogoBean estatusSolicitud = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(estatusSolicitud, solicitudVO.getEstatusSolicitud());
					solicitudBean.setEstatusSolicitud(estatusSolicitud);
				}
				if(solicitudVO.getPeriodo() != null){
					GenericCatalogoBean periodo = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(periodo, solicitudVO.getPeriodo());
					solicitudBean.setPeriodo(periodo);
				}
				listaSolicitud.add(solicitudBean);				
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar solicitudes de tabla Solicitud",
							new Object[] { "SolicitudServiceImpl", "consultar()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		
		return listaSolicitud;
	}
	
	public List<SolicitudBean> consultarListConciliacion(SolicitudFilterBean solicitudFilter) throws MitBusinessException {
		List<SolicitudBean> listaSolicitud = new ArrayList<SolicitudBean>();

		try{
			SolicitudBean solicitudBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setNumCuentaIndividual(solicitudFilter.getNumCuentaIndividual() != null ? solicitudFilter.getNumCuentaIndividual() : null);
			solicitudFilterVO.setNss(solicitudFilter.getNss() != null ? solicitudFilter.getNss() : null);
			solicitudFilterVO.setCurp(solicitudFilter.getCurp() != null ? solicitudFilter.getCurp() : null);
			solicitudFilterVO.setFechaInicio(solicitudFilter.getFechaInicio() != null ? solicitudFilter.getFechaInicio() : null);
			solicitudFilterVO.setFechaFin(solicitudFilter.getFechaFinal() != null ? solicitudFilter.getFechaFinal() : null);
			if(solicitudFilter.getEstatusSolicitud() != null){
				GenericCatalogoVO estatus = new GenericCatalogoVO();
				estatus.setId(solicitudFilter.getEstatusSolicitud().getId() == null ? null : solicitudFilter.getEstatusSolicitud().getId());
				solicitudFilterVO.setEstatusSolicitud(estatus);
			}
			
			if(solicitudFilter.getOrigenAportacion() != null){
				GenericCatalogoVO origenAportacion = new GenericCatalogoVO();
				origenAportacion.setId(solicitudFilter.getOrigenAportacion().getId() == null ? null : solicitudFilter.getOrigenAportacion().getId());
				solicitudFilterVO.setOrigenAportacion(origenAportacion);
			}
			
			List<SolicitudVO> listaSolicitudVO = solicitudService.consultarSolicitudDomiciliacionConciliacion(solicitudFilterVO);

					
			for(SolicitudVO solicitudVO : listaSolicitudVO){
				solicitudBean = new SolicitudBean();
				
				solicitudBean.setLlaveLista(solicitudVO.getRowId());
				if(solicitudVO.getClaveSolicitud() != null)
					solicitudBean.setClaveSolicitud(solicitudVO.getClaveSolicitud());
				if(solicitudVO.getNumCuentaIndividual() != null)
					solicitudBean.setNumCuentaIndividual(solicitudVO.getNumCuentaIndividual());
				if(solicitudVO.getClienteNombre() != null)
					solicitudBean.setClienteNombre(solicitudVO.getClienteNombre());
				if(solicitudVO.getClientePaterno() != null)
					solicitudBean.setClientePaterno(solicitudVO.getClientePaterno());
				if(solicitudVO.getClienteMaterno() != null)
					solicitudBean.setClienteMaterno(solicitudVO.getClienteMaterno());
				if(solicitudVO.getFechaEstatuSolicitud() !=null)
					solicitudBean.setFechaEstatuSolicitud(solicitudVO.getFechaEstatuSolicitud());
				if(solicitudVO.getFolio() != null)
					solicitudBean.setFolio(solicitudVO.getFolio());
				if(solicitudVO.getImporte() != null)
					solicitudBean.setImporte(solicitudVO.getImporte());
				if(solicitudVO.getCuentaBanco() != null)
					solicitudBean.setCuentaBanco(solicitudVO.getCuentaBanco());
				if(solicitudVO.getFechaActualizacion() !=null)
					solicitudBean.setFechaReactivacion(solicitudVO.getFechaActualizacion());
				if(solicitudVO.getUsuarioActualizacion() != null)
					solicitudBean.setUsuarioActualizacion(solicitudVO.getUsuarioActualizacion());
				if(solicitudVO.getLlave() != null)
					solicitudBean.setIdConciliacion(solicitudVO.getLlave());

				
				if(solicitudVO.getOrigenSolicitud() != null){
					GenericCatalogoBean origenSolicitud = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(origenSolicitud, solicitudVO.getOrigenSolicitud());
					solicitudBean.setOrigenSolicitud(origenSolicitud);
				}				
				if(solicitudVO.getClaveBanco() != null){
					GenericCatalogoBean claveBanco = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(claveBanco, solicitudVO.getClaveBanco());
					solicitudBean.setClaveBanco(claveBanco);
				}
				if(solicitudVO.getEstatusSolicitud() != null){
					GenericCatalogoBean estatusSolicitud = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(estatusSolicitud, solicitudVO.getEstatusSolicitud());
					solicitudBean.setEstatusSolicitud(estatusSolicitud);
				}
				if(solicitudVO.getPeriodo() != null){
					GenericCatalogoBean periodo = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(periodo, solicitudVO.getPeriodo());
					solicitudBean.setPeriodo(periodo);
				}
				listaSolicitud.add(solicitudBean);				
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar solicitudes de tabla Conciliacion",
							new Object[] { "SolicitudServiceImpl", "consultar()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		
		return listaSolicitud;
	}
	
	public List<SolicitudBean> consultarListBancos(SolicitudFilterBean solicitudFilter) throws MitBusinessException {
		List<SolicitudBean> listaSolicitud = new ArrayList<SolicitudBean>();
		
		return listaSolicitud;
	}
	
	public List<SolicitudBean> consultarListSpei(SolicitudFilterBean solicitudFilter) throws MitBusinessException {
		List<SolicitudBean> listaSolicitud = new ArrayList<SolicitudBean>();

		try{
			SolicitudBean solicitudBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setNumCuentaIndividual(solicitudFilter.getNumCuentaIndividual() != null ? solicitudFilter.getNumCuentaIndividual() : null);
			solicitudFilterVO.setNss(solicitudFilter.getNss() != null ? solicitudFilter.getNss() : null);
			solicitudFilterVO.setCurp(solicitudFilter.getCurp() != null ? solicitudFilter.getCurp() : null);
			solicitudFilterVO.setFechaInicio(solicitudFilter.getFechaInicio() != null ? solicitudFilter.getFechaInicio() : null);
			solicitudFilterVO.setFechaFin(solicitudFilter.getFechaFinal() != null ? solicitudFilter.getFechaFinal() : null);
			if(solicitudFilter.getEstatusSolicitud() != null){
				GenericCatalogoVO estatus = new GenericCatalogoVO();
				estatus.setId(solicitudFilter.getEstatusSolicitud().getId() == null ? null : solicitudFilter.getEstatusSolicitud().getId());
				solicitudFilterVO.setEstatusSolicitud(estatus);
			}
			
			if(solicitudFilter.getOrigenAportacion() != null){
				GenericCatalogoVO origenAportacion = new GenericCatalogoVO();
				origenAportacion.setId(solicitudFilter.getOrigenAportacion().getId() == null ? null : solicitudFilter.getOrigenAportacion().getId());
				solicitudFilterVO.setOrigenAportacion(origenAportacion);
			}
			
			List<SolicitudVO> listaSolicitudVO = solicitudService.consultarSolicitudDomiciliacionSpei(solicitudFilterVO);

					
			for(SolicitudVO solicitudVO : listaSolicitudVO){
				solicitudBean = new SolicitudBean();
				
				solicitudBean.setLlaveLista(solicitudVO.getRowId());
				if(solicitudVO.getClaveSolicitud() != null)
					solicitudBean.setClaveSolicitud(solicitudVO.getClaveSolicitud());
				if(solicitudVO.getNumCuentaIndividual() != null)
					solicitudBean.setNumCuentaIndividual(solicitudVO.getNumCuentaIndividual());
				if(solicitudVO.getClienteNombre() != null)
					solicitudBean.setClienteNombre(solicitudVO.getClienteNombre());
				if(solicitudVO.getClientePaterno() != null)
					solicitudBean.setClientePaterno(solicitudVO.getClientePaterno());
				if(solicitudVO.getClienteMaterno() != null)
					solicitudBean.setClienteMaterno(solicitudVO.getClienteMaterno());
				if(solicitudVO.getFechaEstatuSolicitud() !=null)
					solicitudBean.setFechaEstatuSolicitud(solicitudVO.getFechaEstatuSolicitud());
				if(solicitudVO.getFolio() != null)
					solicitudBean.setFolio(solicitudVO.getFolio());
				if(solicitudVO.getImporte() != null)
					solicitudBean.setImporte(solicitudVO.getImporte());
				if(solicitudVO.getCuentaBanco() != null)
					solicitudBean.setCuentaBanco(solicitudVO.getCuentaBanco());
				if(solicitudVO.getFechaActualizacion() !=null)
					solicitudBean.setFechaReactivacion(solicitudVO.getFechaActualizacion());
				if(solicitudVO.getUsuarioActualizacion() != null)
					solicitudBean.setUsuarioActualizacion(solicitudVO.getUsuarioActualizacion());
				if(solicitudVO.getLlave() != null)
					solicitudBean.setIdConciliacion(solicitudVO.getLlave());
				
				
				if(solicitudVO.getOrigenSolicitud() != null){
					GenericCatalogoBean origenSolicitud = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(origenSolicitud, solicitudVO.getOrigenSolicitud());
					solicitudBean.setOrigenSolicitud(origenSolicitud);
				}				
				if(solicitudVO.getClaveBanco() != null){
					GenericCatalogoBean claveBanco = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(claveBanco, solicitudVO.getClaveBanco());
					solicitudBean.setClaveBanco(claveBanco);
				}
				if(solicitudVO.getEstatusSolicitud() != null){
					GenericCatalogoBean estatusSolicitud = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(estatusSolicitud, solicitudVO.getEstatusSolicitud());
					solicitudBean.setEstatusSolicitud(estatusSolicitud);
				}
				if(solicitudVO.getPeriodo() != null){
					GenericCatalogoBean periodo = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(periodo, solicitudVO.getPeriodo());
					solicitudBean.setPeriodo(periodo);
				}
				listaSolicitud.add(solicitudBean);				
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar solicitudes de tabla Conciliacion",
							new Object[] { "SolicitudServiceImpl", "consultar()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		
		return listaSolicitud;
	}
	
	public List<SolicitudBean> consultarListTodos(SolicitudFilterBean solicitudFilter) throws MitBusinessException {
		List<SolicitudBean> listaSolicitud = new ArrayList<SolicitudBean>();

		try{
			SolicitudBean solicitudBean = null;
			SolicitudFilter solicitudFilterVO = new SolicitudFilter();
			solicitudFilterVO.setNumCuentaIndividual(solicitudFilter.getNumCuentaIndividual() != null ? solicitudFilter.getNumCuentaIndividual() : null);
			solicitudFilterVO.setNss(solicitudFilter.getNss() != null ? solicitudFilter.getNss() : null);
			solicitudFilterVO.setCurp(solicitudFilter.getCurp() != null ? solicitudFilter.getCurp() : null);
			solicitudFilterVO.setFechaInicio(solicitudFilter.getFechaInicio() != null ? solicitudFilter.getFechaInicio() : null);
			solicitudFilterVO.setFechaFin(solicitudFilter.getFechaFinal() != null ? solicitudFilter.getFechaFinal() : null);
			if(solicitudFilter.getEstatusSolicitud() != null){
				GenericCatalogoVO estatus = new GenericCatalogoVO();
				estatus.setId(solicitudFilter.getEstatusSolicitud().getId() == null ? null : solicitudFilter.getEstatusSolicitud().getId());
				solicitudFilterVO.setEstatusSolicitud(estatus);
			}
			
			List<SolicitudVO> listaSolicitudVO = solicitudService.consultarSolicitudDomiciliacionTodos(solicitudFilterVO);
			for(SolicitudVO solicitudVO : listaSolicitudVO){
				solicitudBean = new SolicitudBean();
				
				solicitudBean.setLlaveLista(solicitudVO.getRowId());
				if(solicitudVO.getClaveSolicitud() != null)
					solicitudBean.setClaveSolicitud(solicitudVO.getClaveSolicitud());
				if(solicitudVO.getNumCuentaIndividual() != null)
					solicitudBean.setNumCuentaIndividual(solicitudVO.getNumCuentaIndividual());
				if(solicitudVO.getClienteNombre() != null)
					solicitudBean.setClienteNombre(solicitudVO.getClienteNombre());
				if(solicitudVO.getClientePaterno() != null)
					solicitudBean.setClientePaterno(solicitudVO.getClientePaterno());
				if(solicitudVO.getClienteMaterno() != null)
					solicitudBean.setClienteMaterno(solicitudVO.getClienteMaterno());
				if(solicitudVO.getFechaEstatuSolicitud() !=null)
					solicitudBean.setFechaEstatuSolicitud(solicitudVO.getFechaEstatuSolicitud());
				if(solicitudVO.getFolio() != null)
					solicitudBean.setFolio(solicitudVO.getFolio());
				if(solicitudVO.getImporte() != null)
					solicitudBean.setImporte(solicitudVO.getImporte());
				if(solicitudVO.getCuentaBanco() != null)
					solicitudBean.setCuentaBanco(solicitudVO.getCuentaBanco());
				if(solicitudVO.getFechaActualizacion() !=null)
					solicitudBean.setFechaReactivacion(solicitudVO.getFechaActualizacion());
				if(solicitudVO.getUsuarioActualizacion() != null)
					solicitudBean.setUsuarioActualizacion(solicitudVO.getUsuarioActualizacion());
				if(solicitudVO.getLlave() != null)
					solicitudBean.setIdConciliacion(solicitudVO.getLlave());
			
				
				if(solicitudVO.getOrigenSolicitud() != null){
					GenericCatalogoBean origenSolicitud = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(origenSolicitud, solicitudVO.getOrigenSolicitud());
					solicitudBean.setOrigenSolicitud(origenSolicitud);
				}				
				if(solicitudVO.getClaveBanco() != null){
					GenericCatalogoBean claveBanco = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(claveBanco, solicitudVO.getClaveBanco());
					solicitudBean.setClaveBanco(claveBanco);
				}
				if(solicitudVO.getEstatusSolicitud() != null){
					GenericCatalogoBean estatusSolicitud = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(estatusSolicitud, solicitudVO.getEstatusSolicitud());
					solicitudBean.setEstatusSolicitud(estatusSolicitud);
				}
				if(solicitudVO.getPeriodo() != null){
					GenericCatalogoBean periodo = new GenericCatalogoBean();
					UtilMapping.mapVoToBean(periodo, solicitudVO.getPeriodo());
					solicitudBean.setPeriodo(periodo);
				}
				listaSolicitud.add(solicitudBean);				
			}
		} catch (final Exception ex) {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "Al Consultar solicitudes de tabla Conciliacion",
							new Object[] { "SolicitudServiceImpl", "consultar()" }, ex));
			LOGGER.error(mitBusinessException.getMessage(), ex);
			throw mitBusinessException;
		}
		
		return listaSolicitud;
	}
}