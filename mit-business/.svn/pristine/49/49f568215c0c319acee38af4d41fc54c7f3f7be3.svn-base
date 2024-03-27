package mx.profuturo.nci.business.report.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.report.IBasicReportService;
import mx.profuturo.nci.business.report.beans.BasicParamReportBean;
import mx.profuturo.nci.business.report.beans.BasicReportBean;
import mx.profuturo.nci.business.report.beans.BasicXLSParamReportBean;
import mx.profuturo.nci.business.vo.ReporteVO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("basicReportService")
public class BasicReportServiceImpl implements IBasicReportService{
	private static final Logger LOGGER = LoggerFactory.getLogger(BasicReportServiceImpl.class);

	
	public <T>ReporteVO crearReporteXls(BasicXLSParamReportBean<T> param) throws MitBusinessException {
		  try {
			    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			    
			    Map<String, Object> mapParameter=param.getMapParameters();
			
			    final JasperPrint jasperPrint = 
			    		JasperFillManager.fillReport(
		    				JasperCompileManager.compileReport(
		    						getClass().getClassLoader().getResourceAsStream(param.getJrxmlReportPath())
//		    						new FileInputStream(new File(param.getJrxmlReportPath()))
		    				),
    						mapParameter,
    						new JRBeanCollectionDataSource(param.getDataSource())
			    		);
			    final JRXlsExporter xlsExporter = new JRXlsExporter();
			    xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			    xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			    
			    SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();		    
			    configuration.setSheetNames(param.getSheetNames());
			    configuration.setRemoveEmptySpaceBetweenRows(true);
			    configuration.setRemoveEmptySpaceBetweenColumns(true);
			    configuration.setDetectCellType(true);
			    configuration.setWhitePageBackground(false);
			    Map<String, String> map=new HashMap<String, String>();
			    map.put("¤ #,##0.00", "$ #,##0.00");
			    configuration.setFormatPatternsMap(map);
			    xlsExporter.setConfiguration(configuration);
			    
			    xlsExporter.exportReport();
			 
			    return new ReporteVO(param.getOutputReportFileName(), outputStream.toByteArray());
			    
		  } catch(final JRException ex){
				final MitBusinessException mitBusinessException = new MitBusinessException(
						GenerateExceptionDetails.generate(
								ErrorCodeService.EX_JREXCEPTION,"Al Generara Reporte: "+ex.getMessage(),
								new Object[] {getClass().getName(), "crearReporteXls()" }, ex));
				LOGGER.error(mitBusinessException.getMessage(),ex);
				throw mitBusinessException;
		  }catch (Exception ex) {
				MitBusinessException mitBusinessException = new MitBusinessException(
						GenerateExceptionDetails.generate(
								ErrorCodeService.EX_JREXCEPTION,"Al Generara Reporte: "+ex.getMessage(),
								new Object[] {getClass().getName(), "crearReporteXls()" }, ex));
				LOGGER.error(mitBusinessException.getMessage(),ex);
				ex.printStackTrace();
				throw mitBusinessException;
		}		  
	}
	
	public <T>ReporteVO crearReportePDF(BasicParamReportBean<T> param) throws MitBusinessException{
		try{
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    
	    Map<String, Object> mapParameter=param.getMapParameters();
	   
	    final JasperPrint jasperPrint = 
	    		JasperFillManager.fillReport(
    				JasperCompileManager.compileReport(this.getClass().
    						getClassLoader().getResourceAsStream(param.getJrxmlReportPath())),
					mapParameter,
					new JRBeanCollectionDataSource(param.getDataSource())
	    		);
	    
	    
	    final JRPdfExporter exporter=new JRPdfExporter();
	    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	    exporter.setExporterOutput((new SimpleOutputStreamExporterOutput(outputStream)));
	    
	    SimplePdfReportConfiguration configuration=new SimplePdfReportConfiguration();
	    /**
	     * Setear parametros de configuracion, si aplica.
	     */
	    
	   // exporter.setConfiguration(configuration);
	    
	    exporter.exportReport();
	    
	    return new ReporteVO(param.getOutputReportFileName(), outputStream.toByteArray());
		
	  } catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(ErrorCodeService.EX_EXCEPTION, "En generar archivo",
							new Object[] { "BasicReportServiceImpl", "crearReportePDF()" }, ex));

			LOGGER.error(mitBusinessException.getMessage(), ex);

			throw mitBusinessException;
		
	}
		
	}
	
}
