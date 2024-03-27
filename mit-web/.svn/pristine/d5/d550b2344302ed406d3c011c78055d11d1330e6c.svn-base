package mx.profuturo.nci.web.service.impl;


import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_CIF_APV_SELCC_ENVIADO_CIF;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.report.IBasicReportService;
import mx.profuturo.nci.business.report.beans.BasicXLSParamReportBean;
import mx.profuturo.nci.business.service.ICIFService;
import mx.profuturo.nci.business.util.UtilMethod;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.business.vo.cif.DevolucionesCabeceraCifVO;
import mx.profuturo.nci.business.vo.cif.DevolucionesDetalleCifVO;
import mx.profuturo.nci.business.vo.cif.DevolucionesEnvioCifCabeceraVO;
import mx.profuturo.nci.business.vo.cif.DevolucionesEnvioCifDetalleVO;
import mx.profuturo.nci.business.vo.cif.DevolucionesReporteCDCifVO;
import mx.profuturo.nci.business.vo.cif.Profuturo_CIFVo;
import mx.profuturo.nci.business.wrapped.NCI_CIFExtVo;
import mx.profuturo.nci.business.wrapped.NCI_CIFFilter;
import mx.profuturo.nci.business.wrapped.cif.Profuturo_CIFFilter;
import mx.profuturo.nci.cif.persistence.ProfuturoCIFPersistence;
import mx.profuturo.nci.web.beans.NCI_CIFDataBean;
import mx.profuturo.nci.web.beans.NCI_CIFDataFilter;
import mx.profuturo.nci.web.service.IMovimientosCifWebService;
import mx.profuturo.nci.web.util.UtilMapping;



@Service("movimientosCifService")
public class MovimientosCifWebServiceImpl implements IMovimientosCifWebService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovimientosCifWebServiceImpl.class);
	private static final String STR_ZERO = "0";
	private static final Long LNG_ZERO = 0L;
	private static final String ESTATUS_ENVIADO_CIF = "0";

	@Autowired ICIFService cifService;
	@Autowired IBasicReportService reportService;
	@Autowired ProfuturoCIFPersistence  cifPeristence;
	
	/*
	 * FOP | Devoliciones para envio a CIF
	 * 08/04/2021
	 */
	public List<DevolucionesEnvioCifDetalleVO> getListaEnvioCIFDetalle(String folio) {
		return cifService.buscaListaEnvioCIFDetalle(folio);
	}	
	
	public List<DevolucionesEnvioCifDetalleVO> getListaEnvioCIFDetalle(Date fechaIni, Date fechaFin) {
		return cifService.buscaListaEnvioCIFDetalle(new DevolucionesEnvioCifDetalleVO(fechaIni, fechaFin));
	}	
	
	public DevolucionesEnvioCifCabeceraVO getListaEnvioCIFCabecera(String folio) {
		return cifService.buscaListaEnvioCIFCabecera(folio);
	}	
	
	public Integer actualizaDevolCif(DevolucionesEnvioCifDetalleVO folio) {
		Integer rgs = cifService.updateDetalleDevCif(folio);
		if(rgs > 0) {
			rgs = cifService.updateCabeceraDevCif(folio);
		}
		return rgs;
	}	
	
	public List<DevolucionesCabeceraCifVO> getListaSeguimientoEstatusCIF(DevolucionesCabeceraCifVO data) {
		return cifPeristence.selectReportDevCif(data);
	}	
	
	public String enviaDevCIF(DevolucionesCabeceraCifVO cab, DevolucionesDetalleCifVO det) {
		String rgs = "0";
		Integer rg = 0;
		try {
			rg = cifPeristence.creaDevolucionesCIF(cab);
			if(rg > 0) {
				rg = rg + cifPeristence.creaDevolucionesDetalleCIF(det);
			}	
			rgs = rg.toString();
		}catch (Exception e) {
			rgs = e.getCause().toString();
		}
		return rgs;
	}
	
	
	public List<NCI_CIFDataBean> consultar(NCI_CIFDataFilter filter) throws MitBusinessException {
//		cifService.consultarProfuturo(new Profuturo_CIFFilter());
		List<NCI_CIFDataBean> data = UtilMapping.mapCIFListVoToBean(cifService.consultarNCI(convertFilter(filter)));
//		data.addAll(getDummyData(1));
		return data;
	}
	
	private NCI_CIFFilter convertFilter(NCI_CIFDataFilter df) {
		NCI_CIFFilter f = new NCI_CIFFilter();
		f.setFolioConciliacion((df.getIdConciliacion()!=null && !LNG_ZERO.equals(df.getIdConciliacion())?df.getIdConciliacion().toString():""));
		f.setReferencia(df.getReferencia());
		f.setReferenciaAmpliada(df.getReferenciaAmpliada());
		
		if(		(f.getFolioConciliacion() == null || f.getFolioConciliacion().isEmpty())
				&& (f.getReferencia()== null || f.getReferencia().isEmpty())
				&& (f.getReferenciaAmpliada() == null || f.getReferenciaAmpliada().isEmpty()) ) {
			if(df.getSeleccion()!= null && STR_ZERO.equals(df.getSeleccion())) {
				f.setSelectSeccionNull(Boolean.TRUE);
			}else {
				f.setSeleccion(df.getSeleccion());
			}
			f.setFechaContableFin(df.getFechaFin());
			f.setFechaContableInicio(df.getFechaInicio());
		}
		return f;
	}
	
	public Integer actualizarNCICIF(NCI_CIFExtVo vo) throws MitBusinessException {
		try {
			return cifService.actualizarNCI(vo);
		} catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al actualizar NCI CIF: "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "actualizarNCICIF()" }, ex));
			throw mitBusinessException;			
		}
	}
	
	public Integer borrarNCICIF(NCI_CIFExtVo vo) throws MitBusinessException {
		try {
			return cifService.borrarNCI(vo);
		} catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al actualizar NCI CIF: "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "actualizarNCICIF()" }, ex));
			throw mitBusinessException;			
		}
	}
	
	public List<Profuturo_CIFVo> send2CIF(List<Profuturo_CIFVo> vos,String usuario) throws MitBusinessException {
		List<Profuturo_CIFVo> rejected = new ArrayList<Profuturo_CIFVo>();
		for(Profuturo_CIFVo vo:vos) {
			try {
				Integer ok= null;
				ok=cifService.insertarProfuturo(vo);
				if(ok > 0) {
					NCI_CIFExtVo ncivo = new NCI_CIFExtVo();
					ncivo.setSeleccion(ID_CIF_APV_SELCC_ENVIADO_CIF);
					ncivo.setUsuarioActualizacion(usuario);
					ncivo.setUsuarioEnvioCIF(usuario);
					ncivo.setIdItem(vo.getIdItem());
					cifService.actualizarNCI(ncivo);
				}else {
					rejected.add(vo);
				}
			} catch(Exception ex){
				vo.setMensajeResultado(ex.getMessage());
				rejected.add(vo);
			}
		}
		return rejected;
	}
	
	@Deprecated
	private List<NCI_CIFDataBean> getDummyData(int size) {
		List<NCI_CIFDataBean> dataBeans = new ArrayList<NCI_CIFDataBean>();
		for(int i=0; i<size; i++) {
			dataBeans.add(getDummyCIFDataBean());
		}
		return dataBeans;
	}
	@Deprecated
	private NCI_CIFDataBean getDummyCIFDataBean() {
		NCI_CIFDataBean db = new NCI_CIFDataBean();
		db.setIdItem(UtilMethod.getRandomNumericString(6));
		db.setIdConciliacion(Long.getLong(UtilMethod.getRandomNumericString(6)));
		db.setIdBanco(UtilMethod.getRandomInteger(9999).shortValue());
		db.setImporte(UtilMethod.getRandomMonto(new BigDecimal(10000000)));
		db.setConsecutivo(UtilMethod.getRandomInteger(9));
		db.setFechaDepositoBancario(UtilMethod.getRandomDate(365));
		try {
			db.setReferencia(UtilMethod.leftPadCeros(UtilMethod.getRandomNumericString(6),13));
			db.setReferenciaAmpliada(UtilMethod.leftPadCeros(db.getReferencia(),24));
		} catch (MitBusinessException e) {
			LOGGER.error("",e);
		}
		db.setUsuarioActualizacion(UtilMethod.getRandomCusps());
		db.setFechaActualizacion(UtilMethod.getRandomDate(365));
		db.setUsuarioEnvioCIF(UtilMethod.getRandomCusps());
		db.setFechaEnvioCIF(UtilMethod.getRandomDate(365));
		db.setSeleccion((UtilMethod.getRandomFromArray(new String[] {"0","1"})));
//		db.setOrigenAportacion(new GenericCatalogoBean(UtilMethod.getRandomFromArray(new Short[] {(short)845,(short)290,(short)291})));
//		if(new Short("845").equals(db.getOrigenAportacion().getId())) {
//			db.getOrigenAportacion().setValor("DOMICILIACION TRASPASOS");
//		}else if(new Short("290").equals(db.getOrigenAportacion().getId())) {
//			db.getOrigenAportacion().setValor("RED COMERCIAL");
//		}else if(new Short("291").equals(db.getOrigenAportacion().getId())) {
//			db.getOrigenAportacion().setValor("DOMICILIACION");
//		}
//		db.setBanco(new GenericCatalogoBean(UtilMethod.getRandomFromArray(new Short[] {(short)2,(short)12,(short)44})));
//		if(new Short("2").equals(db.getBanco().getId())) {
//			db.getBanco().setValor("BANAMEX");
//		}else if(new Short("12").equals(db.getBanco().getId())) {
//			db.getBanco().setValor("BANCOMER");
//		}else /*if(new Short("44").equals(db.getIdBanco()))*/ {
//			db.getBanco().setValor("SCOTIABANK");
//		}
		db.setUnidadDeNegocio("PAF01");
		db.setFechaItem(UtilMethod.getRandomDate(365));
		db.setFechaContable(UtilMethod.getRandomDate(365));
		db.setFechaIntroduccion(UtilMethod.getRandomDate(365));
		db.setMetodoCobro("EFT");
		db.setCondicionCobro("CONT");
		db.setCodigoMoneda("MNX");
		db.setCuenta(UtilMethod.getRandomFromArray(new String[] {"860C","266C","017C"}));
		db.setTipoProceso(UtilMethod.getRandomFromArray(new String[] {" APCOM","APVOL"}));
		db.setEstatus("N");
		db.setSecuencia(0L);
		
		generaIdItem(db);
		
		return db;
	}

	public void generaIdItem(NCI_CIFDataBean bean) {
		if(bean!=null) {
			StringBuffer idItem = new StringBuffer();
			try {
				
				idItem.append(UtilMethod.leftPadCeros(bean.getConsecutivo().toString(), 3));
				idItem.append('-');
				//idItem.append(UtilMethod.dateFormatter(bean.getFechaDepositoBancario(), "yyMMdd"));
				idItem.append(UtilMethod.dateFormatter(bean.getFechaDepositoBancario(), "ddMMyy"));
				
				if(bean.getOrigenAportacion().equals("845")) {
					if("012".equals(bean.getBanco())) {
						idItem.append(bean.getReferenciaAmpliada());
					}else {
						idItem.append(bean.getReferencia());
					}
				}else if(bean.getOrigenAportacion().equals("290")) {
					if("012".equals(bean.getBanco())) {
						idItem.append(bean.getReferencia());
					}else {
						idItem.append(bean.getReferenciaAmpliada());
					}					
				}else if("044".equals(bean.getBanco())){
					idItem.append(bean.getImporte());
				}else if(bean.getBanco().equals("002") && bean.getOrigenAportacion().equals("291")) {
					idItem.append(bean.getReferencia());
				}else if(bean.getBanco().equals("002") && !bean.getOrigenAportacion().equals("291")) {
					idItem.append(bean.getReferenciaAmpliada());
				}else if(bean.getBanco().equals("012")) {
					idItem.append(bean.getReferencia());
				}else {
					idItem = new StringBuffer("0");
				}
									
//				idItem.append(UtilMethod.leftPadCeros(bean.getConsecutivo().toString(), 3));
//				idItem.append('-');
//				idItem.append(UtilMethod.dateFormatter(bean.getFechaDepositoBancario(), "yyMMdd"));
//					if("012".equals(bean.getBanco())) {
//						idItem.append(bean.getReferenciaAmpliada());
//					}else {
//						idItem.append(bean.getReferencia());
//					}
			} catch (MitBusinessException e) {
				idItem = new StringBuffer();
				idItem.append("#Error:"+e.getMessage());
			}
			if(idItem.toString().length() <= 30) {
				bean.setIdItemNew(idItem.toString());
			}else {
				bean.setIdItemNew(idItem.substring(0, 30));
			}
		}
	}
	
	public ReporteVO generaReporte(List<Profuturo_CIFVo> dataReportBean) throws MitBusinessException {
		ReporteVO repoBean = null;
		try{
			BasicXLSParamReportBean<Profuturo_CIFVo> paramBean = new BasicXLSParamReportBean<Profuturo_CIFVo>();
			paramBean.setMapParameters(getReportParameters());
			paramBean.setDataSource(dataReportBean);
			paramBean.setJrxmlReportPath("reports/envio-cif-report.jrxml");
			paramBean.setOutputReportFileName("EnvioCIF.xls");
			repoBean = 	reportService.crearReporteXls(paramBean)	;
		} catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al generar reporte Envio a CIF: "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "generaReporte()" }, ex));
			throw mitBusinessException;			
		}
		return repoBean;
	}

	public ReporteVO generaReportePrev(List<Profuturo_CIFVo> dataReportBean) throws MitBusinessException {
		ReporteVO repoBean = null;
		try{
			BasicXLSParamReportBean<Profuturo_CIFVo> paramBean = new BasicXLSParamReportBean<Profuturo_CIFVo>();
			paramBean.setMapParameters(getReportPrevParameters());
			paramBean.setDataSource(dataReportBean);
			paramBean.setJrxmlReportPath("reports/envio-cif-report.jrxml");
			paramBean.setOutputReportFileName("PrevioEnvioCIF.xls");
			repoBean = 	reportService.crearReporteXls(paramBean)	;
		} catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al generar reporte Envio a CIF: "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "generaReporte()" }, ex));
			throw mitBusinessException;			
		}
		return repoBean;
	}

	public ReporteVO generaReportePrevDev(List<DevolucionesReporteCDCifVO> dataReportBean) throws MitBusinessException {
		ReporteVO repoBean = null;
		try{
			BasicXLSParamReportBean<DevolucionesReporteCDCifVO> paramBean = new BasicXLSParamReportBean<DevolucionesReporteCDCifVO>();
			paramBean.setMapParameters(this.getReportPrevDevParameters());
			paramBean.setDataSource(dataReportBean);
			paramBean.setJrxmlReportPath("reports/devolucion-cif-reporte.jrxml");
			paramBean.setOutputReportFileName("PrevioDevolucionesCIF.xls");
			repoBean = 	reportService.crearReporteXls(paramBean)	;
		} catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al generar reporte Devoluciones CIF: "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "generaReportePrevDev()" }, ex));
			throw mitBusinessException;			
		}
		return repoBean;
	}	
	
	public ReporteVO generaReporteDevSeg(List<DevolucionesCabeceraCifVO> data) throws MitBusinessException {
		ReporteVO repoBean = null;
		try{
			BasicXLSParamReportBean<DevolucionesCabeceraCifVO> paramBean = new BasicXLSParamReportBean<DevolucionesCabeceraCifVO>();
			paramBean.setMapParameters(this.getReportPrevDevSegParameters());
			paramBean.setDataSource(data);
			paramBean.setJrxmlReportPath("reports/seguimiento-cif-reporte.jrxml");
			paramBean.setOutputReportFileName("seguimiento_estatus_CIF.xls");
			repoBean = 	reportService.crearReporteXls(paramBean);
		} catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al generar reporte Seguimiento Estatus CIF: "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "generaReporteDevSeg()" }, ex));
			throw mitBusinessException;			
		}		
		return repoBean;
	}
	
	public List<Profuturo_CIFVo> getDataSended(List<Profuturo_CIFVo> sended) throws MitBusinessException{
		List<Profuturo_CIFVo> prf_sended = new ArrayList<Profuturo_CIFVo>();
		try{
			for(Profuturo_CIFVo vo:sended) {
				Profuturo_CIFFilter f = new Profuturo_CIFFilter(vo.getIdItem());
				prf_sended.addAll(cifService.consultarProfuturo(f));
			}
		} catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al obtener enviados a CIF: "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "getDataSended()" }, ex));
			throw mitBusinessException;			
		}
		return prf_sended;
	}
	
	private Map<String, Object> getReportParameters(){
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("encabezado", "Reporte de movimientos enviados a CIF");
		
		return params;
	}

	private Map<String, Object> getReportPrevParameters(){
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("encabezado", "Reporte de movimientos pendientes de envío a CIF");
		
		return params;
	}

	private Map<String, Object> getReportPrevDevParameters(){
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("encabezado", "Reporte de devoluciones pendientes de envío a CIF");
		
		return params;
	}

	private Map<String, Object> getReportPrevDevSegParameters(){
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("encabezado", "Reporte de estatus del CIF");
		
		return params;
	}

	
}
