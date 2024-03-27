package mx.profuturo.nci.business.report.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;
import com.jeveris.core.persistence.exception.PersistenceException;

import mx.profuturo.nci.business.events.report.transaccion.CifrasControlReclaApoVolReportRequestReadEvent;
import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.report.IBasicReportService;
import mx.profuturo.nci.business.report.ICifrasControlReportService;
import mx.profuturo.nci.business.report.beans.BasicXLSParamReportBean;
import mx.profuturo.nci.business.report.beans.CifrasControlReclaApoVolDataReportBean;
import mx.profuturo.nci.business.report.beans.CifrasControlReclaApoVolDetalleMixToReportBean;
import mx.profuturo.nci.business.report.beans.CifrasControlReclaApoVolDetalleReportBean;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.business.vo.cifctrlreclasificacion.CCOrigDestReclasificacionVO;
import mx.profuturo.nci.business.vo.cifctrlreclasificacion.CCTotalSubcuentaReclasificacionVO;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service("cifrasControlReportService")
public class CifrasControlReportServiceImpl implements ICifrasControlReportService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CifrasControlReportServiceImpl.class);

    private static final String CIFRAS_CONTROL_REPORT_JRXML = "src/main/resources/reports/cifrasGenerales.jasper";

    private static final String CIFRAS_CONTROL_REPORT_FILE_NAME_PREFIX = "cifras-generales_";

    private static final String CIFRAS_CONTROL_REPORT_FILE_NAME_EXTENSION = ".pdf";
  
    private static final String CIFRAS_CONTROL_RECLASIFICACION_REPORT_JRXML = "reports/reportesApoVol/cifrasControlReclasificacion.jrxml";
    
    @Autowired
    IBasicReportService basicReportService;
    
public ReporteVO generarArchivoCifrasGenerales(List cifrasControlReporteVO) throws BusinessException {
	ReporteVO reporteVO = new ReporteVO();
	try {
		
		reporteVO.setNombre(CIFRAS_CONTROL_REPORT_FILE_NAME_PREFIX);
		File archivo = new File(CIFRAS_CONTROL_REPORT_JRXML);
		
		JasperReport reporte = (JasperReport) JRLoader.loadObject(archivo);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null,
				new JRBeanCollectionDataSource(cifrasControlReporteVO));
		File archivoSalida = new File(CIFRAS_CONTROL_REPORT_FILE_NAME_PREFIX+""+CIFRAS_CONTROL_REPORT_FILE_NAME_EXTENSION);
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, archivoSalida);
		exporter.exportReport();
		//reporteVO.setArchivo(Files.readAllBytes(archivoSalida.toPath()));
		/*final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	      final JasperPrint jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(CIFRAS_CONTROL_REPORT_JRXML), null,new JRBeanCollectionDataSource(cifrasControlReporteVO));

	      final JRPdfExporter pdfExporter=new JRPdfExporter();
	      pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	      pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
	      pdfExporter.exportReport();
	      reporteVO.setArchivo(outputStream.toByteArray());*/
	      LOGGER.debug("terminado");
	}
	catch (PersistenceException ex) {
		throw new BusinessException(ex.getExceptionDetails());
	} catch (Exception ex) {
		MitBusinessException mitBusinessException = new MitBusinessException(
				GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En generar archivo",
						new Object[] { "CifrasControlReportServiceImpl", "generarArchivo()" }, ex));

		LOGGER.error(mitBusinessException.getMessage(), ex);

		throw mitBusinessException;
	}
	return reporteVO;
}

public ReporteVO generarArchivoCifrasCtrlReclasificacionApoVol(
		CifrasControlReclaApoVolReportRequestReadEvent requestCifrasContrlEventVO) throws BusinessException {
		ReporteVO reporteVO = new ReporteVO();
		
		try {
			List<CifrasControlReclaApoVolDetalleReportBean> reporteRegistrosDataBean 	= new ArrayList<CifrasControlReclaApoVolDetalleReportBean>();
			List<CifrasControlReclaApoVolDetalleReportBean> totalesDataBean 			= new ArrayList<CifrasControlReclaApoVolDetalleReportBean>();
			List<CifrasControlReclaApoVolDetalleReportBean> cargosDataBean 				= new ArrayList<CifrasControlReclaApoVolDetalleReportBean>();
			List<CifrasControlReclaApoVolDetalleReportBean> abonosDataBean 				= new ArrayList<CifrasControlReclaApoVolDetalleReportBean>();
			List<CifrasControlReclaApoVolDetalleMixToReportBean> mixtoDataBean 			= new ArrayList<CifrasControlReclaApoVolDetalleMixToReportBean>();
			
			for (CCOrigDestReclasificacionVO origenDestinoVO : requestCifrasContrlEventVO.getReporteRegistrosVO()) {
				CifrasControlReclaApoVolDetalleReportBean cifCtrlReclaDetalleBean = new CifrasControlReclaApoVolDetalleReportBean();
				cifCtrlReclaDetalleBean.setSubCuenta(origenDestinoVO.getFuenteSiefore());
				cifCtrlReclaDetalleBean.setProfBP(String.valueOf(origenDestinoVO.getNumSolicProfBp()));
				cifCtrlReclaDetalleBean.setProfB1(String.valueOf(origenDestinoVO.getNumSolicProfB1()));
				cifCtrlReclaDetalleBean.setProfB2(String.valueOf(origenDestinoVO.getNumSolicProfB2()));
				cifCtrlReclaDetalleBean.setProfB3(String.valueOf(origenDestinoVO.getNumSolicProfB3()));
				cifCtrlReclaDetalleBean.setProfB4(String.valueOf(origenDestinoVO.getNumSolicProfB4()));
				cifCtrlReclaDetalleBean.setProfCP(String.valueOf(origenDestinoVO.getNumSolicProfCp()));
				cifCtrlReclaDetalleBean.setProfLP(String.valueOf(origenDestinoVO.getNumSolicProfLp()));
				
				reporteRegistrosDataBean.add(cifCtrlReclaDetalleBean);
			}
			
			for (CCTotalSubcuentaReclasificacionVO totalesVO : requestCifrasContrlEventVO.getTotalesVO()) {
				
				CifrasControlReclaApoVolDetalleReportBean cifCtrlReclaDetalleBean = new CifrasControlReclaApoVolDetalleReportBean();
				
				cifCtrlReclaDetalleBean.setSubCuenta(totalesVO.getSubcuenta().getValor());
				cifCtrlReclaDetalleBean.setProfBP(String.valueOf(totalesVO.getMontoSolicProfBp()));
				cifCtrlReclaDetalleBean.setProfB1(String.valueOf(totalesVO.getMontoSolicProfB1()));
				cifCtrlReclaDetalleBean.setProfB2(String.valueOf(totalesVO.getMontoSolicProfB2()));
				cifCtrlReclaDetalleBean.setProfB3(String.valueOf(totalesVO.getMontoSolicProfB3()));
				cifCtrlReclaDetalleBean.setProfB4(String.valueOf(totalesVO.getMontoSolicProfB4()));
				cifCtrlReclaDetalleBean.setProfCP(String.valueOf(totalesVO.getMontoSolicProfCp()));
				cifCtrlReclaDetalleBean.setProfLP(String.valueOf(totalesVO.getMontoSolicProfLp()));
				cifCtrlReclaDetalleBean.setTotalAceptado(String.valueOf(totalesVO.getTotalAceptados()));
				cifCtrlReclaDetalleBean.setTotalRechazado(String.valueOf(totalesVO.getTotalRechazados()));
				
				totalesDataBean.add(cifCtrlReclaDetalleBean);
				
			}
			
			for (CCTotalSubcuentaReclasificacionVO cargosVO : requestCifrasContrlEventVO.getCargosVO()) {
				
				CifrasControlReclaApoVolDetalleReportBean cifCtrlReclaDetalleBean = new CifrasControlReclaApoVolDetalleReportBean();
				
				cifCtrlReclaDetalleBean.setSubCuenta(cargosVO.getSubcuenta().getValor());

				
				cifCtrlReclaDetalleBean.setProfBP(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(cargosVO.getMontoSolicProfBp()));
				cifCtrlReclaDetalleBean.setProfB1(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(cargosVO.getMontoSolicProfB1()));
				cifCtrlReclaDetalleBean.setProfB2(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(cargosVO.getMontoSolicProfB2()));
				cifCtrlReclaDetalleBean.setProfB3(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(cargosVO.getMontoSolicProfB3()));
				cifCtrlReclaDetalleBean.setProfB4(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(cargosVO.getMontoSolicProfB4()));
				cifCtrlReclaDetalleBean.setProfCP(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(cargosVO.getMontoSolicProfCp()));
				cifCtrlReclaDetalleBean.setProfLP(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(cargosVO.getMontoSolicProfLp()));
				cifCtrlReclaDetalleBean.setTotalAceptado(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(cargosVO.getTotalAceptados()));
				cifCtrlReclaDetalleBean.setTotalRechazado(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(cargosVO.getTotalRechazados()));
				
				cargosDataBean.add(cifCtrlReclaDetalleBean);
				
			}
			
			
			
			for (CCTotalSubcuentaReclasificacionVO abonosVO : requestCifrasContrlEventVO.getAbonosVO()) {
				
				CifrasControlReclaApoVolDetalleReportBean cifCtrlReclaDetalleBean = new CifrasControlReclaApoVolDetalleReportBean();
				
				cifCtrlReclaDetalleBean.setSubCuenta(abonosVO.getSubcuenta().getValor());
				cifCtrlReclaDetalleBean.setProfBP(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(abonosVO.getMontoSolicProfBp()));
				cifCtrlReclaDetalleBean.setProfB1(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(abonosVO.getMontoSolicProfB1()));
				cifCtrlReclaDetalleBean.setProfB2(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(abonosVO.getMontoSolicProfB2()));
				cifCtrlReclaDetalleBean.setProfB3(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(abonosVO.getMontoSolicProfB3()));
				cifCtrlReclaDetalleBean.setProfB4(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(abonosVO.getMontoSolicProfB4()));
				cifCtrlReclaDetalleBean.setProfCP(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(abonosVO.getMontoSolicProfCp()));
				cifCtrlReclaDetalleBean.setProfLP(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(abonosVO.getMontoSolicProfLp()));
				cifCtrlReclaDetalleBean.setTotalAceptado(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(abonosVO.getTotalAceptados()));
				cifCtrlReclaDetalleBean.setTotalRechazado(NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(abonosVO.getTotalRechazados()));
				abonosDataBean.add(cifCtrlReclaDetalleBean);
				
			}
			
			CifrasControlReclaApoVolDetalleMixToReportBean totales	= new CifrasControlReclaApoVolDetalleMixToReportBean();
			
			List<String> subcuentasAux	= new ArrayList<String>();
			for( CifrasControlReclaApoVolDetalleReportBean aux : cargosDataBean ) {
				if( !subcuentasAux.contains( aux.getSubCuenta() ) ) {
					CifrasControlReclaApoVolDetalleMixToReportBean subcuenta	= new CifrasControlReclaApoVolDetalleMixToReportBean();
					subcuentasAux.add( aux.getSubCuenta() );
					subcuenta.setSubCuenta( aux.getSubCuenta() );
					subcuenta.setCargoProfBP( aux.getProfBP() );
					subcuenta.setCargoProfB1( aux.getProfB1() );
					subcuenta.setCargoProfB2( aux.getProfB2() );
					subcuenta.setCargoProfB3( aux.getProfB3() );
					subcuenta.setCargoProfB4( aux.getProfB4() );
					subcuenta.setCargoProfCP( aux.getProfCP() );
					subcuenta.setCargoProfLP( aux.getProfLP() );
					subcuenta.setCargoTotalAceptado( aux.getTotalAceptado() );
					subcuenta.setCargoTotalRechazado( aux.getTotalRechazado() );
					
					subcuenta.setAbonoProfBP( " " );
					subcuenta.setAbonoProfB1( " " );
					subcuenta.setAbonoProfB2( " " );
					subcuenta.setAbonoProfB3( " " );
					subcuenta.setAbonoProfB4( " " );
					subcuenta.setAbonoProfCP( " " );
					subcuenta.setAbonoProfLP( " " );
					subcuenta.setAbonoTotalAceptado( aux.getTotalAceptado() );
					subcuenta.setAbonoTotalRechazado( aux.getTotalRechazado() );
					if( aux.getSubCuenta().equals("TOTALES") ) {
						totales = subcuenta;
					}else {
						mixtoDataBean.add( subcuenta );
					}
				}
			}
			
			for( CifrasControlReclaApoVolDetalleReportBean aux : abonosDataBean ) {
				if( !subcuentasAux.contains( aux.getSubCuenta() ) ) {
					CifrasControlReclaApoVolDetalleMixToReportBean subcuenta	= new CifrasControlReclaApoVolDetalleMixToReportBean();
					subcuentasAux.add( 			aux.getSubCuenta() );
					subcuenta.setSubCuenta( 	aux.getSubCuenta() );
					subcuenta.setAbonoProfBP( 	aux.getProfBP() );
					subcuenta.setAbonoProfB1( 	aux.getProfB1() );
					subcuenta.setAbonoProfB2( 	aux.getProfB2() );
					subcuenta.setAbonoProfB3( 	aux.getProfB3() );
					subcuenta.setAbonoProfB4( 	aux.getProfB4() );
					subcuenta.setAbonoProfCP( 	aux.getProfCP() );
					subcuenta.setAbonoProfLP( 	aux.getProfLP() );
					subcuenta.setAbonoTotalAceptado( aux.getTotalAceptado() );
					subcuenta.setAbonoTotalRechazado( aux.getTotalRechazado() );
					
					subcuenta.setCargoProfBP( " " );
					subcuenta.setCargoProfB1( " " );
					subcuenta.setCargoProfB2( " " );
					subcuenta.setCargoProfB3( " ");
					subcuenta.setCargoProfB4( " " );
					subcuenta.setCargoProfCP( " " );
					subcuenta.setCargoProfLP( " " );
					subcuenta.setCargoTotalAceptado( " " );
					subcuenta.setCargoTotalRechazado( " " );
					mixtoDataBean.add( subcuenta );
				}else {
					//YA EXISTE ENTONCES TENEMOS QUE AGREGARLE LOS CAMPOS RESTANTES
					if( aux.getSubCuenta().equals( "TOTALES" ) ) {
						totales.setAbonoProfBP( aux.getProfBP() );
						totales.setAbonoProfB1( aux.getProfB1() );
						totales.setAbonoProfB2( aux.getProfB2() );
						totales.setAbonoProfB3( aux.getProfB3() );
						totales.setAbonoProfB4( aux.getProfB4() );
						totales.setAbonoProfCP( aux.getProfCP() );
						totales.setAbonoProfLP( aux.getProfLP() );
						totales.setAbonoTotalAceptado( aux.getTotalAceptado() );
						totales.setAbonoTotalRechazado( aux.getTotalRechazado() );
					}else {
						for( CifrasControlReclaApoVolDetalleMixToReportBean existente: mixtoDataBean ) {
							if( existente.getSubCuenta().equals( aux.getSubCuenta() ) ) {
								existente.setAbonoProfBP( aux.getProfBP() );
								existente.setAbonoProfB1( aux.getProfB1() );
								existente.setAbonoProfB2( aux.getProfB2() );
								existente.setAbonoProfB3( aux.getProfB3() );
								existente.setAbonoProfB4( aux.getProfB4() );
								existente.setAbonoProfCP( aux.getProfCP() );
								existente.setAbonoProfLP( aux.getProfLP() );
								existente.setAbonoTotalAceptado( aux.getTotalAceptado() );
								existente.setAbonoTotalRechazado( aux.getTotalRechazado() );
							}
						}
					}
					
				}
			}		
			
			mixtoDataBean.add( totales );
			
			CifrasControlReclaApoVolDataReportBean cifrasCtrlDataBean = new CifrasControlReclaApoVolDataReportBean();
			cifrasCtrlDataBean.setReporteRegistros(reporteRegistrosDataBean);
			cifrasCtrlDataBean.setTotales(totalesDataBean);
			cifrasCtrlDataBean.setCargos(cargosDataBean);
			cifrasCtrlDataBean.setAbonos(abonosDataBean);
			cifrasCtrlDataBean.setAbonosCargos(mixtoDataBean);
			
			final Map<String, Object> parameters = new HashMap<String, Object>();
	    	parameters.put("folio", requestCifrasContrlEventVO.getFolio());
	    	parameters.put("proceso", requestCifrasContrlEventVO.getProceso());
	    	parameters.put("subproceso", requestCifrasContrlEventVO.getSubProceso());
	    	parameters.put("fechaLiquidacion", requestCifrasContrlEventVO.getFechaLiquidacion());
	    	
	    	List<CifrasControlReclaApoVolDataReportBean> lstBean = new ArrayList<CifrasControlReclaApoVolDataReportBean>();
	    	lstBean.add(cifrasCtrlDataBean);
	
	    	BasicXLSParamReportBean<CifrasControlReclaApoVolDataReportBean> paramBean = new BasicXLSParamReportBean<CifrasControlReclaApoVolDataReportBean>();
			
			paramBean.setMapParameters(parameters);
			paramBean.setDataSource(lstBean);
			paramBean.setJrxmlReportPath(CIFRAS_CONTROL_RECLASIFICACION_REPORT_JRXML);
			paramBean.setOutputReportFileName(requestCifrasContrlEventVO.getFolio());
			
	    	reporteVO = basicReportService.crearReportePDF(paramBean);
	    	
		} catch (PersistenceException ex) {
			throw new BusinessException(ex.getExceptionDetails());
		} catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En generar archivo",
							new Object[] { "CifrasControlReportServiceImpl", "generarArchivoCifrasCtrlReclasificacionApoVol()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		}
		
		return reporteVO;
	}
}
