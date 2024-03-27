package mx.secure.nci.ws.service.impl.secure.reporte;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;
import com.jeveris.core.ws.service.exception.WebServiceException;

import mx.secure.nci.business.events.report.AportacionesVoluntariasBancarias;
import mx.secure.nci.business.events.report.AportacionesVoluntariasOrigenReport;
import mx.secure.nci.business.events.report.AportacionesVoluntariasSBC;
import mx.secure.nci.business.events.report.ElementoReporteGeneral;
import mx.secure.nci.business.events.report.ElementoReporteGeneralPorTipo;
import mx.secure.nci.business.events.report.ElementoReporteGeneralSBC;
import mx.secure.nci.business.events.report.transaccion.CifrasControlReclasecureReportRequestReadEvent;
import mx.secure.nci.business.events.report.transaccion.CifrasGeneralesReportRequestReadEvent;
import mx.secure.nci.business.events.report.transaccion.CifrasGeneralesReportResponseReadEvent;
import mx.secure.nci.business.exception.ErrorCodeSoap;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.report.ICifrasControlReportService;
import mx.secure.nci.business.report.ICifrasGeneralesReportService;
import mx.secure.nci.business.util.Constantes;
import mx.secure.nci.business.vo.ReporteVO;
import mx.secure.nci.business.vo.cifctrlreclasificacion.CCOrigDestReclasificacionVO;
import mx.secure.nci.business.vo.cifctrlreclasificacion.CCTotalSubcuentaReclasificacionVO;
import mx.secure.nci.ws.beans.CifrasGeneralesBean;
import mx.secure.nci.ws.beans.CifrasGeneralesPorTipoBean;
import mx.secure.nci.ws.beans.ElementoBean;
import mx.secure.nci.ws.beans.ReportBean;
import mx.secure.nci.ws.beans.SeccionBean;
import mx.secure.nci.ws.beans.request.CifCtrlCargoAbonoReclasifBeanRequest;
import mx.secure.nci.ws.beans.request.CifCtrlTotalReclasificacionBeanRequest;
import mx.secure.nci.ws.beans.request.CifrasControlReclasecureReportRequest;
import mx.secure.nci.ws.beans.request.CifrasGeneralesPorBancoBeanRequest;
import mx.secure.nci.ws.beans.request.CifrasGeneralesPorOrigenBeanRequest;
import mx.secure.nci.ws.beans.request.CifrasGeneralesPorSBCBeanRequest;
import mx.secure.nci.ws.beans.request.CifrasGeneralesPorTipoBeanRequest;
import mx.secure.nci.ws.beans.request.CifrasGeneralesReportRequest;
import mx.secure.nci.ws.beans.response.CifrasGeneralesPorBancoBeanResponse;
import mx.secure.nci.ws.beans.response.CifrasGeneralesPorOrigenBeanResponse;
import mx.secure.nci.ws.beans.response.CifrasGeneralesPorSBCBeanResponse;
import mx.secure.nci.ws.beans.response.CifrasGeneralesPorTipoBeanResponse;
import mx.secure.nci.ws.beans.response.CifrasGeneralesReportResponse;
import mx.secure.nci.ws.service.secure.reporte.IReporteriaSoapAVService;
import mx.secure.nci.ws.service.cifctrlreclasificacion.ICifCtrlReclasificacionSoapService;
import mx.secure.nci.ws.service.cifrasgenerales.ICifrasGeneralesSoapService;

@Service("reporteriaSoapService")
public class ReporteriaAVSoapServiceImpl implements IReporteriaSoapAVService
{

	private static final Logger LOGGER = LoggerFactory.getLogger(ReporteriaAVSoapServiceImpl.class);

	@Autowired
	ICifrasGeneralesSoapService cifrasGeneralesSoapService;

	@Autowired
	ICifrasGeneralesReportService cifrasGeneralesReportService;
	
	@Autowired
	ICifrasControlReportService cifrasControlReportService;
	
	@Autowired
	ICifCtrlReclasificacionSoapService cifCtrlReclasificacionSoapService;
	
	@Override
	public CifrasGeneralesReportResponse cifrasControl(CifrasGeneralesReportRequest request) {
		// TODO Auto-generated method stub
		CifrasGeneralesReportResponse cifrasGeneralesReportResponse = new CifrasGeneralesReportResponse();
		CifrasGeneralesReportRequestReadEvent requestEvent=null;

		try{
			 cifrasGeneralesReportResponse = new CifrasGeneralesReportResponse();
			 requestEvent = new CifrasGeneralesReportRequestReadEvent();
			 
			 CifrasGeneralesPorOrigenBeanRequest cifrasPorOrigenReq = new CifrasGeneralesPorOrigenBeanRequest();
			 cifrasPorOrigenReq.setIdOrigen(request.getIdOrigen());
			 cifrasPorOrigenReq.setFolioConciliacion(request.getFolio());
			 CifrasGeneralesPorOrigenBeanResponse cifPorOrigen = this.cifrasGeneralesSoapService.consultarPorOrigen(cifrasPorOrigenReq);
			 
			 List<AportacionesVoluntariasOrigenReport> lstOrigen = new ArrayList<AportacionesVoluntariasOrigenReport>();
			 if(CollectionUtils.isNotEmpty(cifPorOrigen.getCifrasGeneralesPorOrigen())){
				 for(final CifrasGeneralesBean bean : cifPorOrigen.getCifrasGeneralesPorOrigen()){
					 for(final SeccionBean seccion : bean.getSecciones() ){
						 AportacionesVoluntariasOrigenReport apo = new AportacionesVoluntariasOrigenReport(); 
						 apo.setSeccion(seccion.getNombreSeccion());
						 List<ElementoReporteGeneral> registros = new ArrayList<ElementoReporteGeneral>();
						 for(final ElementoBean elem : seccion.getElementos()){
							 registros.add(new ElementoReporteGeneral(elem.getNombreElemento(),
									 								  elem.getImporte(),
									 								  elem.getRegistros(),
									 								  elem.getEstatus()));
						 }
						 apo.setRegistros(registros);
						 lstOrigen.add(apo);
					 }
				 }
			 }
			 requestEvent.setOrigen(lstOrigen);
			 
			 
			 CifrasGeneralesPorTipoBeanRequest cifrasPorTipoReq = new CifrasGeneralesPorTipoBeanRequest(); 
			 cifrasPorTipoReq.setIdTipoCuenta(request.getIdTipo());
			 cifrasPorTipoReq.setFolioConciliacion(request.getFolio());
			 CifrasGeneralesPorTipoBeanResponse cifPorTipo = this.cifrasGeneralesSoapService.consultaPorTipoReporte(cifrasPorTipoReq);
			 
			 List<ElementoReporteGeneralPorTipo> registrosCp = new ArrayList<ElementoReporteGeneralPorTipo>();	
		     List<ElementoReporteGeneralPorTipo> registrosLp = new ArrayList<ElementoReporteGeneralPorTipo>();
				
			 if(CollectionUtils.isNotEmpty(cifPorTipo.getCifrasGeneralesPorTipo())){
				 for(final CifrasGeneralesPorTipoBean bean : cifPorTipo.getCifrasGeneralesPorTipo()){
					 if(bean!=null && bean.getSeccionesCortoPlazo()!=null){
						 for(final SeccionBean seccion : bean.getSeccionesCortoPlazo()){
							 ElementoReporteGeneralPorTipo elemCp = new ElementoReporteGeneralPorTipo();
							 elemCp.setNombreElemento(seccion.getNombreSeccion());
							 List<ElementoReporteGeneral>  registros = new ArrayList<ElementoReporteGeneral>();
							 for(final ElementoBean elem : seccion.getElementos()){
								 registros.add(new ElementoReporteGeneral(elem.getNombreElemento(),
										 								  elem.getImporte(),
										 								  elem.getRegistros(),
										 								  elem.getEstatus()));
							 }
							 elemCp.setRegistros(registros);
							 registrosCp.add(elemCp);
						 }
					 }
					 if(bean!=null && bean.getSeccionesLargoPlazo()!=null){
						 for(final SeccionBean seccion : bean.getSeccionesLargoPlazo()){
							 ElementoReporteGeneralPorTipo elemLp = new ElementoReporteGeneralPorTipo();
							 elemLp.setNombreElemento(seccion.getNombreSeccion());
							 List<ElementoReporteGeneral>  registros = new ArrayList<ElementoReporteGeneral>();
							 for(final ElementoBean elem : seccion.getElementos()){
								 registros.add(new ElementoReporteGeneral(elem.getNombreElemento(),
										 								  elem.getImporte(),
										 								  elem.getRegistros(),
										 								  elem.getEstatus()));
							 }
							 elemLp.setRegistros(registros);
							 registrosLp.add(elemLp);
						 }
					 }
				 }
			 }
			 requestEvent.setTipoCp(registrosCp);
			 requestEvent.setTipoLp(registrosLp);
			 
			 
			 CifrasGeneralesPorBancoBeanRequest cifrasGenReq = new CifrasGeneralesPorBancoBeanRequest();
			 cifrasGenReq.setFolioConciliacion(request.getFolio());
			 CifrasGeneralesPorBancoBeanResponse cifPorBanco = this.cifrasGeneralesSoapService.consultaPorBanco(cifrasGenReq);
			 
			 List<AportacionesVoluntariasBancarias> lstPorBanco = new ArrayList<AportacionesVoluntariasBancarias>();
			 if(CollectionUtils.isNotEmpty(cifPorBanco.getCifrasGenerales())){
				 for(final CifrasGeneralesBean bean : cifPorBanco.getCifrasGenerales()){
					 for(final SeccionBean seccion : bean.getSecciones() ){
						 AportacionesVoluntariasBancarias banca = new AportacionesVoluntariasBancarias(); 
						 banca.setSeccion(seccion.getNombreSeccion());
						 List<ElementoReporteGeneral> registros = new ArrayList<ElementoReporteGeneral>();
						 for(final ElementoBean elem : seccion.getElementos()){
							 registros.add(new ElementoReporteGeneral(elem.getNombreElemento(),
									 								  elem.getImporte(),
									 								  elem.getRegistros(),
									 								  elem.getEstatus()));
						 }
						 banca.setRegistros(registros);
						 lstPorBanco.add(banca);
					 }
				 }
			 }
			 requestEvent.setBancarios(lstPorBanco);
			 
			 
			 
			 CifrasGeneralesPorSBCBeanRequest cifrasGenPorSBC = new CifrasGeneralesPorSBCBeanRequest();
			 
			 CifrasGeneralesPorSBCBeanResponse cifPorBuenCobro = this.cifrasGeneralesSoapService.consultaPorSaldoBuenCobro(cifrasGenPorSBC);
			 		 
			 BigDecimal impCon = new BigDecimal("0");
			 BigDecimal impNoCon = new BigDecimal("0");
			 Integer regCon = 0;
			 Integer regNoCon = 0;
			 
			 List<AportacionesVoluntariasSBC> lstPorSBC = new ArrayList<AportacionesVoluntariasSBC>();
			 if(CollectionUtils.isNotEmpty(cifPorBuenCobro.getCifrasGeneralesBean())){
				 for(final CifrasGeneralesBean bean : cifPorBuenCobro.getCifrasGeneralesBean()){
					 for(final SeccionBean seccion : bean.getSecciones() ){
						 AportacionesVoluntariasSBC sbc = new AportacionesVoluntariasSBC(); 
						 sbc.setSeccion(seccion.getNombreSeccion());
						 List<ElementoReporteGeneralSBC> registros = new ArrayList<ElementoReporteGeneralSBC>();
						 for(final ElementoBean elem : seccion.getElementos()){
							 registros.add(new ElementoReporteGeneralSBC(elem.getFecha(),
									 									 elem.getImporte(),
									 									 elem.getNombreElemento(),
									 									 elem.getBanco(),
									 									 elem.getEstatus()));
							 impNoCon = impNoCon.add(elem.getImporte());

						 }
						 sbc.setRegistros(registros);
						 lstPorSBC.add(sbc);
						 if(bean.getTotalGeneralConciliado()!=null && bean.getTotalGeneralConciliado().getRegistros()!=null){
							regCon = regCon + bean.getTotalGeneralConciliado().getRegistros();
						 }
						 if(bean.getTotalGeneralNoConciliado()!=null && bean.getTotalGeneralNoConciliado().getRegistros()!=null){
//							regNoCon = regNoCon + bean.getTotalGeneralNoConciliado().getRegistros();
							 regNoCon = bean.getTotalGeneralNoConciliado().getRegistros();
						 }
					 }
				 }			 
			 }
			 requestEvent.setSbc(lstPorSBC);

			 List<ElementoReporteGeneral> element = new ArrayList<ElementoReporteGeneral>();
			 element.add(new ElementoReporteGeneral("Conciliados",impCon,regCon,""));
			 element.add(new ElementoReporteGeneral("Pendientes",impNoCon,regNoCon,""));

			 List<AportacionesVoluntariasBancarias> lstTotPorSBC = new ArrayList<AportacionesVoluntariasBancarias>();
			 AportacionesVoluntariasBancarias reg = new AportacionesVoluntariasBancarias();
			 reg.setSeccion("TOTALES");
			 reg.setRegistros(element);
			 lstTotPorSBC.add(reg);
			 requestEvent.setTotSbc(lstTotPorSBC);
			 
			 final CifrasGeneralesReportResponseReadEvent eventResponse = this.cifrasGeneralesReportService.generarArchivoCifrasGenerales(requestEvent);

			 CifrasGeneralesReportResponse response=null;

			 if(eventResponse !=null){
				response = new CifrasGeneralesReportResponse(new ReportBean(eventResponse.getByteArray(), eventResponse.getFileName()));
				
//				 File someFile = null;
//				 someFile = new File("C:\\temp\\"+eventResponse.getFileName());
//				 FileOutputStream fos = new FileOutputStream(someFile);
//			     fos.write(eventResponse.getByteArray());
//			     fos.flush();
//			     fos.close();
			 }
			 return response;
		}catch(final BusinessException ex){
			throw new WebServiceException(ex.getExceptionDetails());
		}
		catch(final Exception ex)
		{
			final WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails
							.generate(ErrorCodeSoap.EX_EXCEPTION,
									"Al Generar Reporte Cifras Generales",
									new Object[] { "ReporteriaAVSoapServiceImpl",
											"cifrasControl()" }, ex));

			LOGGER.error(webServiceException.getMessage(), ex);

			throw webServiceException;
		}
	}

	@Override
	public CifrasGeneralesReportResponse cifrasControlReclasecure(CifrasControlReclasecureReportRequest request) {
		CifrasGeneralesReportResponse cifrasGeneralesReportResponse = new CifrasGeneralesReportResponse();
		
		try {
			CifCtrlTotalReclasificacionBeanRequest requestSoapService = new CifCtrlTotalReclasificacionBeanRequest();
			requestSoapService.setFolioReclasificacion(request.getFolio());
			
			List<CCOrigDestReclasificacionVO> reporteRegistrosVO = this.cifCtrlReclasificacionSoapService.consultaOrigDestReclasificacion(requestSoapService).getListaMovimientos();
			List<CCTotalSubcuentaReclasificacionVO> totalesVO	 = this.cifCtrlReclasificacionSoapService.consultaTotalPorSubcuentas(requestSoapService).getListaTotalSubcuentas();
			
			CifCtrlCargoAbonoReclasifBeanRequest requestSoapServiceCargoAbono = new CifCtrlCargoAbonoReclasifBeanRequest();
			requestSoapServiceCargoAbono.setFolioReclasificacion(request.getFolio());
			requestSoapServiceCargoAbono.setTipoMov(Integer.parseInt(String.valueOf(Constantes.CAT_CARGO)));
			List<CCTotalSubcuentaReclasificacionVO> cargosVO = this.cifCtrlReclasificacionSoapService.consultaCargAboPorSubcuentas(requestSoapServiceCargoAbono).getListaTotalSubcuentas();
			
			requestSoapServiceCargoAbono.setTipoMov(Integer.parseInt(String.valueOf(Constantes.CAT_ABONO)));
			List<CCTotalSubcuentaReclasificacionVO> abonosVO = this.cifCtrlReclasificacionSoapService.consultaCargAboPorSubcuentas(requestSoapServiceCargoAbono).getListaTotalSubcuentas();

			CifrasControlReclasecureReportRequestReadEvent requestEvent = new CifrasControlReclasecureReportRequestReadEvent();
			requestEvent.setFolio(request.getFolio());
			requestEvent.setProceso(request.getNombreProceso());
			requestEvent.setSubProceso(request.getNombreSubproceso());
			requestEvent.setFechaLiquidacion(request.getFechaLiquidacion());
			requestEvent.setReporteRegistrosVO(reporteRegistrosVO);
			requestEvent.setTotalesVO(totalesVO);
			requestEvent.setCargosVO(cargosVO);
			requestEvent.setAbonosVO(abonosVO);
			
			final ReporteVO eventResponse = this.cifrasControlReportService.generarArchivoCifrasCtrlReclasificacionsecure(requestEvent);
			eventResponse.setNombre(request.getNombreArchivo());

			 if(eventResponse !=null){
				cifrasGeneralesReportResponse.setReportBean(new ReportBean(eventResponse.getArchivo(), eventResponse.getNombre()));
			 }
			 
		} catch(final BusinessException ex){
			throw new WebServiceException(ex.getExceptionDetails());
		} catch(final Exception ex){
			final WebServiceException webServiceException = new WebServiceException(
					GenerateExceptionDetails
							.generate(ErrorCodeSoap.EX_EXCEPTION,
									"Al Generar Reporte Cifras Control por Reclasificacion",
									new Object[] { "ReporteriaAVSoapServiceImpl",
											"cifrasControlReclasecure()" }, ex));

			LOGGER.error(webServiceException.getMessage(), ex);

			throw webServiceException;
		}
		
		
		return cifrasGeneralesReportResponse;
	}
}