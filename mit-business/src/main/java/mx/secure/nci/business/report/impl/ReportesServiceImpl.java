package mx.secure.nci.business.report.impl;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import mx.secure.nci.business.exception.ErrorCodeService;
import mx.secure.nci.business.exception.GenerateExceptionDetails;
import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.report.IBasicReportService;
import mx.secure.nci.business.report.IReportesService;
import mx.secure.nci.business.report.beans.BasicXLSParamReportBean;
import mx.secure.nci.business.report.beans.CargosDataReportBean;
import mx.secure.nci.business.report.beans.ConsultaAforeMovilDataReportBean;
import mx.secure.nci.business.report.beans.DetalleArchivoCifReportBean;
import mx.secure.nci.business.report.beans.DetalleIdcReportBean;
import mx.secure.nci.business.report.beans.DiversificacionesDataReportBean;
import mx.secure.nci.business.report.beans.DomiliacionesDataReportBean;
import mx.secure.nci.business.report.beans.ValidacionesDataReportBean;
import mx.secure.nci.business.vo.ReporteVO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

@Service("reporteService")
public class ReportesServiceImpl implements IReportesService {
	
	@Autowired IBasicReportService reportService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportesServiceImpl.class);

	public ReporteVO generaReporteDomiliacion(List<DomiliacionesDataReportBean> dataReportBean) throws MitBusinessException{
		ReporteVO repoBean = null;
		try{
			BasicXLSParamReportBean<DomiliacionesDataReportBean> paramBean = new BasicXLSParamReportBean<DomiliacionesDataReportBean>();
			paramBean.setMapParameters(getReportParameters());
			paramBean.setDataSource(dataReportBean);
			paramBean.setJrxmlReportPath("reports/consultasecure.jrxml");
			paramBean.setOutputReportFileName("Consultasecure.xls");
			repoBean = 	reportService.crearReporteXls(paramBean)	;
		} catch(Exception ex){
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al generar reporte CuentaCertificada ",
					new Object[] { getClass().getSimpleName(), "generaReporte()" }, ex));
			throw mitBusinessException;			
		}
		return repoBean;
	}
	
	private Map<String, Object> getReportParameters(){
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("encabezado", "Reporte de consulta aportaciones voluntarias");
		
		return params;
	}
	
	public ReporteVO generaReporteDetalleDomiliacion(List<DiversificacionesDataReportBean> diverReportBean, 
			List<ValidacionesDataReportBean> validReportBean, List<CargosDataReportBean> cargoReportBean) throws MitBusinessException{
	ReporteVO repoBean = new ReporteVO();
		try{
			final Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("lstDiversificacion", diverReportBean);
			parameters.put("lstValidacion", validReportBean);
			parameters.put("lstCargos", cargoReportBean);
			
			final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JasperPrint jasperPrint;
			jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("reports/consultaDetallesecure.jrxml")), parameters);
			final JRXlsExporter xlsExporter = new JRXlsExporter();
			xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			
			SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();		    
			configuration.setSheetNames(new String[]{"Diversificación","Validación","Cargos"});
			configuration.setRemoveEmptySpaceBetweenRows(true);
			configuration.setRemoveEmptySpaceBetweenColumns(true);
			configuration.setDetectCellType(true);
			configuration.setWhitePageBackground(false);
			Map<String, String> map=new HashMap<String, String>();
			map.put("¤ #,##0.00", "$ #,##0.00");
			configuration.setFormatPatternsMap(map);
			xlsExporter.setConfiguration(configuration);
			xlsExporter.exportReport();
			
			repoBean.setArchivo(outputStream.toByteArray());
			repoBean.setNombre("ConsultaDetallesecure.xls");
		}catch (JRException e){
			LOGGER.error("Ocurrió un error en la generación del reporte en la capa de negocio: " + e.getMessage());
		}catch (Exception e){
			LOGGER.error("Ocurrió un error en la generación del reporte, no de controlador [JASPER]: " + e.getMessage());
		}
		return repoBean;
	}
	
	public ReporteVO generaReporteDepositosAforeMovil(List<ConsultaAforeMovilDataReportBean> aforeMovilReportBean) 
			throws MitBusinessException{
		ReporteVO repoBean = new ReporteVO();
		
		try{
			final Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("lstAforeMovil", aforeMovilReportBean);
			
			final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JasperPrint jasperPrint;
			jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("reports/consultaAforeMovil.jrxml")), parameters);
			final JRXlsExporter xlsExporter = new JRXlsExporter();
			xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			
			
			SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();		    
			configuration.setSheetNames(new String[]{"AforeMovil"});
			configuration.setRemoveEmptySpaceBetweenRows(true);
			configuration.setRemoveEmptySpaceBetweenColumns(true);
			configuration.setDetectCellType(true);
			configuration.setWhitePageBackground(false);
			Map<String, String> map=new HashMap<String, String>();
			map.put("¤ #,##0.00", "$ #,##0.00");
			configuration.setFormatPatternsMap(map);
			xlsExporter.setConfiguration(configuration);
			xlsExporter.exportReport();
			
			repoBean.setArchivo(outputStream.toByteArray());
			repoBean.setNombre("ConsultaAforeMovil.xls");
			
		}catch (JRException e){
			LOGGER.error("Ocurrió un error en la generación del reporte en la capa de negocio: " + e.getMessage());
		}catch (Exception e){
			LOGGER.error("Ocurrió un error en la generación del reporte, no de controlador [JASPER]: " + e.getMessage());
		}
		return repoBean;
	}
	
	public ReporteVO generaReporteDetalleIdc(List<DetalleIdcReportBean> reporteDetalleIdcVO) 
			throws MitBusinessException{
		ReporteVO repoBean = null;
		
		try{
			
			/*     GENERACION ARCHIVO CSV     INICIO*/
			final Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("lstDetalleIdc", reporteDetalleIdcVO);
			
			final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JasperPrint jasperPrint;
			jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("reports/reporteDetalleIdc.jrxml")), parameters);
			final JRCsvExporter csvExporter = new JRCsvExporter();
			csvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			csvExporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
			SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
			configuration.setWriteBOM(Boolean.TRUE);
			//configuration.setFieldDelimiter(",");
			//configuration.setRecordDelimiter("\r\n");
			
			csvExporter.setConfiguration(configuration);
			csvExporter.exportReport();
			/*     GENERACION ARCHIVO CSV     FIN*/
			repoBean = new ReporteVO();
			repoBean.setArchivo(outputStream.toByteArray());
			repoBean.setNombre("ReporteDetalleIdc.csv");
			
		}catch (Exception e){
			LOGGER.error("Ocurrió un error en la generación del reporte, no de controlador [JASPER]: " + e.getMessage());
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al generar reporte DetalleMovimientosIDC ",
					new Object[] { getClass().getSimpleName(), "generaReporteDetalleIdc()" }, e));
			throw mitBusinessException;			
		}
		return repoBean;
	}

	public ReporteVO generarReporteDetalleCif(List<DetalleArchivoCifReportBean> reporteDetalleCifVO)
			throws MitBusinessException{
		ReporteVO reporteBean = null;
		
		try{
			final Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("lstDetalleCif", reporteDetalleCifVO);
			
			final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JasperPrint jasperPrint;
			jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("reports/reporteDetalleArchivoCif.jrxml")), parameters);
			final JRCsvExporter csvExporter = new JRCsvExporter();
			csvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			csvExporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
			SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
			configuration.setWriteBOM(Boolean.TRUE);
			
			csvExporter.setConfiguration(configuration);
			csvExporter.exportReport();
			
			reporteBean = new ReporteVO();
			reporteBean.setArchivo(outputStream.toByteArray());
			reporteBean.setNombre("ReporteDetalleArchivoCif.csv");
			
		}catch (Exception e){
			LOGGER.error("Ocurrió un error en la generación del reporte, no de controlador [JASPER]: " + e.getMessage());
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "Al generar reporte DetalleMovimientosCIF ",
					new Object[] { getClass().getSimpleName(), "generarReporteDetalleCif()" }, e));
			throw mitBusinessException;			
		}
		return reporteBean;
	}
}