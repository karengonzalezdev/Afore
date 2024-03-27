package mx.profuturo.nci.business.report.impl;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.events.report.transaccion.CifrasGeneralesReportResponseReadEvent;
import mx.profuturo.nci.business.events.report.AportacionesVoluntariasBancarias;
import mx.profuturo.nci.business.events.report.AportacionesVoluntariasOrigenReport;
import mx.profuturo.nci.business.events.report.ElementoReporteGeneral;
import mx.profuturo.nci.business.events.report.ElementoReporteGeneralPorTipo;
import mx.profuturo.nci.business.events.report.transaccion.CifrasGeneralesReportRequestReadEvent;
import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.report.ICifrasGeneralesReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service("cifrasGralReportService")
public class CifrasGeneralesReportServiceImpl implements ICifrasGeneralesReportService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CifrasGeneralesReportServiceImpl.class);

    private static final String CIFRAS_CONTROL_REPORT_JRXML = "reports/cifras-gral.jrxml";

    private static final String CIFRAS_CONTROL_REPORT_FILE_NAME_PREFIX = "cifras-gral_";

    private static final String CIFRAS_CONTROL_REPORT_FILE_NAME_EXTENSION = ".pdf";

    public CifrasGeneralesReportResponseReadEvent generarArchivoCifrasGenerales(final CifrasGeneralesReportRequestReadEvent event) 
    		throws MitBusinessException {
	try {
		final Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("aportacionesOrigen", event.getOrigen());
    	Map<String,ElementoReporteGeneral> mapOr = new HashMap<String,ElementoReporteGeneral>();
    	for(AportacionesVoluntariasOrigenReport beanOr : event.getOrigen()){
    		for(ElementoReporteGeneral beanOrDet: beanOr.getRegistros()){
    			ElementoReporteGeneral reg;
    			if(mapOr.containsKey(beanOrDet.getNombreElemento()+beanOrDet.getEstatus())){
    				reg = mapOr.get(beanOrDet.getNombreElemento()+beanOrDet.getEstatus());
    				reg.setImporte(reg.getImporte().add(beanOrDet.getImporte()));
    				reg.setRegistros(reg.getRegistros()+beanOrDet.getRegistros());
    			}else{
    				reg = new ElementoReporteGeneral();
    				reg.setNombreElemento(beanOrDet.getNombreElemento());
    				reg.setImporte(beanOrDet.getImporte());
    				reg.setRegistros(beanOrDet.getRegistros());
    				reg.setEstatus(beanOrDet.getEstatus());
    			}
    			mapOr.put(reg.getNombreElemento()+reg.getEstatus(), reg);
    		}	
    	}
    	parameters.put("aportacionesOrigenTot", ordenaMapList(mapOr));
    	
    	parameters.put("aportacionesTipoCp", event.getTipoCp());  	
    	parameters.put("aportacionesTipoLp", event.getTipoLp());
    	Map<String,ElementoReporteGeneral> mapTpo = new HashMap<String,ElementoReporteGeneral>();
    	for(ElementoReporteGeneralPorTipo beanOr : event.getTipoCp()){
    		for(ElementoReporteGeneral beanOrDet: beanOr.getRegistros()){
    			ElementoReporteGeneral reg;
    			if(mapTpo.containsKey("CORTO PLAZO"+beanOrDet.getEstatus())){
    				reg = mapTpo.get("CORTO PLAZO"+beanOrDet.getEstatus());
    				reg.setImporte(reg.getImporte().add(beanOrDet.getImporte()));
    				reg.setRegistros(reg.getRegistros()+beanOrDet.getRegistros());
    			}else{
    				reg = new ElementoReporteGeneral();
    				reg.setNombreElemento("CORTO PLAZO");
    				reg.setImporte(beanOrDet.getImporte());
    				reg.setRegistros(beanOrDet.getRegistros());
    				reg.setEstatus(beanOrDet.getEstatus());
    			}
    			mapTpo.put("CORTO PLAZO"+reg.getEstatus(), reg);
    		}
    	}
    	for(ElementoReporteGeneralPorTipo beanOr : event.getTipoLp()){
    		for(ElementoReporteGeneral beanOrDet: beanOr.getRegistros()){
    			ElementoReporteGeneral reg;
    			if(mapTpo.containsKey("LARGO PLAZO"+beanOrDet.getEstatus())){
    				reg = mapTpo.get("LARGO PLAZO"+beanOrDet.getEstatus());
    				reg.setImporte(reg.getImporte().add(beanOrDet.getImporte()));
    				reg.setRegistros(reg.getRegistros()+beanOrDet.getRegistros());
    			}else{
    				reg = new ElementoReporteGeneral();
    				reg.setNombreElemento("LARGO PLAZO");
    				reg.setImporte(beanOrDet.getImporte());
    				reg.setRegistros(beanOrDet.getRegistros());
    				reg.setEstatus(beanOrDet.getEstatus());
    			}
    			mapTpo.put("LARGO PLAZO"+reg.getEstatus(), reg);
    		}
    	}
    	parameters.put("aportacionesTipoTot", ordenaMapList(mapTpo));
    	
    	parameters.put("aportacionesBancarias", event.getBancarios());
    	Map<String,ElementoReporteGeneral> mapBan = new HashMap<String,ElementoReporteGeneral>();
    	for(AportacionesVoluntariasBancarias beanOr : event.getBancarios()){
    		for(ElementoReporteGeneral beanOrDet: beanOr.getRegistros()){
    			ElementoReporteGeneral reg;
    			if(mapBan.containsKey(beanOrDet.getNombreElemento()+beanOrDet.getEstatus())){
    				reg = mapBan.get(beanOrDet.getNombreElemento()+beanOrDet.getEstatus());
    				reg.setImporte(reg.getImporte().add(beanOrDet.getImporte()));
    				reg.setRegistros(reg.getRegistros()+beanOrDet.getRegistros());
    			}else{
    				reg = new ElementoReporteGeneral();
    				reg.setNombreElemento(beanOrDet.getNombreElemento());
    				reg.setImporte(beanOrDet.getImporte());
    				reg.setRegistros(beanOrDet.getRegistros());
    				reg.setEstatus(beanOrDet.getEstatus());
    			}

    			mapBan.put(reg.getNombreElemento()+reg.getEstatus(), reg);
    		}
    	}
    	parameters.put("aportacionesBancoTot", ordenaMapList(mapBan));
    	
    	parameters.put("aportacionesSbc", event.getSbc());
    	parameters.put("aportacionesTotSbc", event.getTotSbc());

    	final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    	final JasperPrint jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(
        this.getClass().getClassLoader().getResourceAsStream(CIFRAS_CONTROL_REPORT_JRXML)), parameters);
    	
    	final JRPdfExporter pdfExporter=new JRPdfExporter();
    	pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
    	pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
    	pdfExporter.exportReport();
   	
    	return new CifrasGeneralesReportResponseReadEvent()
          .setFileName(
              CIFRAS_CONTROL_REPORT_FILE_NAME_PREFIX + CIFRAS_CONTROL_REPORT_FILE_NAME_EXTENSION)
          .setByteArray(outputStream.toByteArray())
          .setDomainFound(Boolean.TRUE);
		  }
	catch(final JRException ex)
	  {
			final MitBusinessException mitBusinessException = new MitBusinessException(
					GenerateExceptionDetails.generate(
							ErrorCodeService.EX_JREXCEPTION,
							"Al Generara Reporte PDF Cifras Generales"+ex.getMessage(), new Object[] {
									getClass().getName(), "generarArchivoCifrasGenerales()" }, ex));

				LOGGER.error(mitBusinessException.getMessage(),ex);

				throw mitBusinessException;
	}
	catch (final Exception ex)
	{
			final MitBusinessException mitBusinessException = new MitBusinessException(

					GenerateExceptionDetails.generate(
							ErrorCodeService.EX_EXCEPTION,
							"Al Generara Reporte XLS Cifras Control", new Object[] {
									"CifrasControlReportServiceImpl", "crearCifrasControlXls()" }, ex));

				LOGGER.error(mitBusinessException.getMessage(),ex);

				throw mitBusinessException;
		}
    }

    public List<ElementoReporteGeneral> ordenaMapList(Map<String,ElementoReporteGeneral> mapElemento){
    	List<ElementoReporteGeneral> lstReg = new ArrayList<ElementoReporteGeneral>();
    	if(mapElemento!=null && mapElemento.size()>0){
    		Map<String,ElementoReporteGeneral> treeMap = new TreeMap<String,ElementoReporteGeneral>(mapElemento);
    		for (Map.Entry<String,ElementoReporteGeneral> entry : treeMap.entrySet()) {
    			lstReg.add(entry.getValue());
    		}
    	}
    	return lstReg;
    }
}
