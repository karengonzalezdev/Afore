package mx.profuturo.nci.business.service.impl.cifctrlreclasificacion;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.cliente.service.IWSCCatalogoComunService;
import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.report.impl.CifrasGeneralesReportServiceImpl;
import mx.profuturo.nci.business.service.cifctrlreclasificacion.ICifCtrlReclasificacionService;
import mx.profuturo.nci.business.service.impl.IdentificarClienteServiceImpl;
import mx.profuturo.nci.business.service.impl.WSPortTypeFactory;
import mx.profuturo.nci.business.util.Constantes;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.business.vo.cifctrlreclasificacion.CCOrigDestReclasificacionVO;
import mx.profuturo.nci.business.vo.cifctrlreclasificacion.CCTotalReclasificacionVO;
import mx.profuturo.nci.business.vo.cifctrlreclasificacion.CCTotalSubcuentaReclasificacionVO;
import mx.profuturo.nci.business.wrapped.cifctrlreclasificacion.CifCtrlReclasificacionFilter;
import mx.profuturo.nci.persistence.CifCtrlReclasificacionPersistence;

import net.sf.jasperreports.engine.JRException;
import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
 
import profuturo.mx.iib.nci.bitacoraprocesos.folioservice.v1.FolioPortType;
import profuturo.mx.iib.nci.bitacoraprocesos.folioservice.v1.FolioService;
import profuturo.mx.iib.nci.bitacoraprocesos.folioservice.v1.types.ConsultarFolioARelacionarResp;
import profuturo.mx.iib.nci.bitacoraprocesos.folioservice.v1.types.ConsultarFoliosRelacionadosReq;
import profuturo.mx.iib.nci.bitacoraprocesos.folioservice.v1.types.ConsultarFoliosRelacionadosResp;
import profuturo.mx.iib.nci.identificacioncliente.clienteservice.v1.FaultMsg;
import profuturo.mx.nci.modelo.FolioProceso;
import profuturo.mx.nci.modelo.ListaFoliosProceso;


@Service
public class CifCtrlReclasificacionServiceImpl implements ICifCtrlReclasificacionService{
	

	private static final Logger LOGGER = LoggerFactory.getLogger(CifCtrlReclasificacionServiceImpl.class);
	 
	
	@Autowired
	CifCtrlReclasificacionPersistence ccReclacificacion;
   

	@Autowired WSPortTypeFactory wsPortTypeFactory;
	
	public CCTotalReclasificacionVO consultaTotalReclasificacion(CifCtrlReclasificacionFilter filter)  {
		CCTotalReclasificacionVO datos = new CCTotalReclasificacionVO();
		try {   
		
			  datos = ccReclacificacion.consultaTotalReclasificacion(filter);
			  
			  CCTotalReclasificacionVO infoGral = ccReclacificacion.consultaGeneralCifrasControl(filter);
			  
			  /**
			   * 1. Mapear los datos que se generen de esta consulta para enviarlos CCTotalRelcaSificacion como coresponde. 
			   */
			  if(infoGral != null) {
				  datos.setDescEstatusProceso(infoGral.getDescEstatusProceso()); 
			      datos.setDescProceso(infoGral.getDescProceso());
			      datos.setFchUltimoEstatus(infoGral.getFchUltimoEstatus());
			      datos.setFchIncioProceso(infoGral.getFchIncioProceso()); 
			      datos.setFolio(infoGral.getFolio());
			      datos.setNombreClte(""); // esto no se sabe si se va amostrar.  
			  }
		  
	    } catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "consulta Total Reclasificacion "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "consultaTotalReclasificacion()" }, ex));
	
			LOGGER.error(mitBusinessException.getMessage(), ex);

		}
			

		  return datos;	
	
	}

	public List<CCOrigDestReclasificacionVO> consultaOrigDestReclasificacion(CifCtrlReclasificacionFilter filter) {
		return ccReclacificacion.consultaOrigDestReclasificacion(filter);
	}

	public List<CCTotalSubcuentaReclasificacionVO> consultaTotalPorSubcuentas(CifCtrlReclasificacionFilter filter) {
		//List<CCTotalSubcuentaReclasificacionVO> listDatos = ccReclacificacion.consultaTotalPorSubcuentas(filter);
		List<CCTotalSubcuentaReclasificacionVO> listDatos = ccReclacificacion.consultaTotalPorSubcuentasV2(filter);
		 double totalesMontoSolicProfBp = 0;
		 double totalesMontoSolicProfB1 = 0;
		 double totalesMontoSolicProfB2 = 0;
		 double totalesMontoSolicProfB3 = 0;
		 double totalesMontoSolicProfB4 = 0;
		 double totalesMontoSolicProfCp = 0;
		 double totalesMontoSolicProfLp = 0;
		 double totalesTotalAceptados = 0;
		 double totalesTotalRechazados = 0;
		 
		 BigDecimal totalesMontoSolicProfB11;
		 BigDecimal totalesMontoSolicProfB21;
		 BigDecimal totalesMontoSolicProfB31;
		 BigDecimal totalesMontoSolicProfB41;
		 BigDecimal totalesMontoSolicProfBp1;
		 BigDecimal totalesMontoSolicProfCp1;
		 BigDecimal totalesMontoSolicProfLp1;
		 BigDecimal totalesTotalAceptados1;
		 BigDecimal totalesTotalRechazados1;
		 
		 
		 try {
		 CCTotalSubcuentaReclasificacionVO listDato = new CCTotalSubcuentaReclasificacionVO();
		 if (listDatos.size()>0) {
			for (CCTotalSubcuentaReclasificacionVO dato: listDatos) {
				    
				   totalesMontoSolicProfB1 = totalesMontoSolicProfB1 + dato.getMontoSolicProfB1().doubleValue(); 
				   totalesMontoSolicProfB2 = totalesMontoSolicProfB2 + dato.getMontoSolicProfB2().doubleValue();
				   totalesMontoSolicProfB3 = totalesMontoSolicProfB3 + dato.getMontoSolicProfB3().doubleValue();
				   totalesMontoSolicProfB4 = totalesMontoSolicProfB4 + dato.getMontoSolicProfB4().doubleValue();
				   totalesMontoSolicProfBp = totalesMontoSolicProfBp + dato.getMontoSolicProfBp().doubleValue();
				   totalesMontoSolicProfCp = totalesMontoSolicProfCp + dato.getMontoSolicProfCp().doubleValue();
				   totalesMontoSolicProfLp = totalesMontoSolicProfLp + dato.getMontoSolicProfLp().doubleValue();
				   totalesTotalAceptados = totalesTotalAceptados + dato.getTotalAceptados().doubleValue();
				   totalesTotalRechazados = totalesTotalRechazados + dato.getTotalRechazados().doubleValue();
			}
			
			totalesMontoSolicProfB11 = new BigDecimal(totalesMontoSolicProfB1);
			totalesMontoSolicProfB21 = new BigDecimal(totalesMontoSolicProfB2);
			totalesMontoSolicProfB31 = new BigDecimal(totalesMontoSolicProfB3);
			totalesMontoSolicProfB41 = new BigDecimal(totalesMontoSolicProfB4);
			totalesMontoSolicProfBp1 = new BigDecimal(totalesMontoSolicProfBp); 
			totalesMontoSolicProfCp1 = new BigDecimal(totalesMontoSolicProfCp);
			totalesMontoSolicProfLp1 = new BigDecimal(totalesMontoSolicProfLp);
			totalesTotalAceptados1 = new BigDecimal(totalesTotalAceptados);
			totalesTotalRechazados1 = new BigDecimal(totalesTotalRechazados);
			
			listDato.setSubcuenta(new GenericCatalogoVO(new Short("0"), "TOTALES"));
			listDato.setMontoSolicProfB1(totalesMontoSolicProfB11.setScale(2, RoundingMode.HALF_UP));
			listDato.setMontoSolicProfB2(totalesMontoSolicProfB21.setScale(2, RoundingMode.HALF_UP));
			listDato.setMontoSolicProfB3(totalesMontoSolicProfB31.setScale(2, RoundingMode.HALF_UP));
			listDato.setMontoSolicProfB4(totalesMontoSolicProfB41.setScale(2, RoundingMode.HALF_UP));
			listDato.setMontoSolicProfBp(totalesMontoSolicProfBp1.setScale(2, RoundingMode.HALF_UP));
			listDato.setMontoSolicProfCp(totalesMontoSolicProfCp1.setScale(2, RoundingMode.HALF_UP));
			listDato.setMontoSolicProfLp(totalesMontoSolicProfLp1.setScale(2, RoundingMode.HALF_UP));
			listDato.setTotalAceptados(totalesTotalAceptados1.setScale(2, RoundingMode.HALF_UP));
			listDato.setTotalRechazados(totalesTotalRechazados1.setScale(2, RoundingMode.HALF_UP));
			listDatos.add(listDato);
		 }
		 
    } catch (Exception ex) {
		MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
				ErrorCodeService.EX_EXCEPTION, "consultar folio  "+ex.getMessage(),
				new Object[] { getClass().getSimpleName(), "validaConvivencia()" }, ex));

		LOGGER.error(mitBusinessException.getMessage(), ex);

	}
		
		return listDatos;
	}
	                                 	           
	public List<CCTotalSubcuentaReclasificacionVO> consultaCargAboPorSubcuentas(CifCtrlReclasificacionFilter filter) {
		//List<CCTotalSubcuentaReclasificacionVO> listDatos = ccReclacificacion.consultaCarAboPorSubcuentas(filter);
		List<CCTotalSubcuentaReclasificacionVO> listDatos = ccReclacificacion.consultaCarAboPorSubcuentasV2(filter);
		 double totalesMontoSolicProfBp = 0;
		 double totalesMontoSolicProfB1 = 0;
		 double totalesMontoSolicProfB2 = 0;
		 double totalesMontoSolicProfB3 = 0;
		 double totalesMontoSolicProfB4 = 0;
		 double totalesMontoSolicProfCp = 0;
		 double totalesMontoSolicProfLp = 0;
		 double totalesTotalAceptados = 0;
		 double totalesTotalRechazados = 0;
		 
		 BigDecimal totalesMontoSolicProfB11;
		 BigDecimal totalesMontoSolicProfB21;
		 BigDecimal totalesMontoSolicProfB31;
		 BigDecimal totalesMontoSolicProfB41;
		 BigDecimal totalesMontoSolicProfBp1;
		 BigDecimal totalesMontoSolicProfCp1;
		 BigDecimal totalesMontoSolicProfLp1;
		 BigDecimal totalesTotalAceptados1;
		 BigDecimal totalesTotalRechazados1;
		 
		 try {
			 CCTotalSubcuentaReclasificacionVO listDato = new CCTotalSubcuentaReclasificacionVO(); 
			 if(listDatos.size()>0) { 
				for (CCTotalSubcuentaReclasificacionVO dato: listDatos) {
					    
					   totalesMontoSolicProfB1 = totalesMontoSolicProfB1 + dato.getMontoSolicProfB1().doubleValue(); 
					   totalesMontoSolicProfB2 = totalesMontoSolicProfB2 + dato.getMontoSolicProfB2().doubleValue();
					   totalesMontoSolicProfB3 = totalesMontoSolicProfB3 + dato.getMontoSolicProfB3().doubleValue();
					   totalesMontoSolicProfB4 = totalesMontoSolicProfB4 + dato.getMontoSolicProfB4().doubleValue();
					   totalesMontoSolicProfBp = totalesMontoSolicProfBp + dato.getMontoSolicProfBp().doubleValue();
					   totalesMontoSolicProfCp = totalesMontoSolicProfCp + dato.getMontoSolicProfCp().doubleValue();
					   totalesMontoSolicProfLp = totalesMontoSolicProfLp + dato.getMontoSolicProfLp().doubleValue();
					   totalesTotalAceptados = totalesTotalAceptados + dato.getTotalAceptados().doubleValue();
					   totalesTotalRechazados = totalesTotalRechazados + dato.getTotalRechazados().doubleValue();
				}
				
				totalesMontoSolicProfB11 = new BigDecimal(totalesMontoSolicProfB1);
				totalesMontoSolicProfB21 = new BigDecimal(totalesMontoSolicProfB2);
				totalesMontoSolicProfB31 = new BigDecimal(totalesMontoSolicProfB3);
				totalesMontoSolicProfB41 = new BigDecimal(totalesMontoSolicProfB4);
				totalesMontoSolicProfBp1 = new BigDecimal(totalesMontoSolicProfBp); 
				totalesMontoSolicProfCp1 = new BigDecimal(totalesMontoSolicProfCp);
				totalesMontoSolicProfLp1 = new BigDecimal(totalesMontoSolicProfLp);
				totalesTotalAceptados1 = new BigDecimal(totalesTotalAceptados);
				totalesTotalRechazados1 = new BigDecimal(totalesTotalRechazados);
				
				listDato.setSubcuenta(new GenericCatalogoVO(new Short("0"), "TOTALES"));
				listDato.setMontoSolicProfB1(totalesMontoSolicProfB11.setScale(2, RoundingMode.HALF_UP));
				listDato.setMontoSolicProfB2(totalesMontoSolicProfB21.setScale(2, RoundingMode.HALF_UP));
				listDato.setMontoSolicProfB3(totalesMontoSolicProfB31.setScale(2, RoundingMode.HALF_UP));
				listDato.setMontoSolicProfB4(totalesMontoSolicProfB41.setScale(2, RoundingMode.HALF_UP));
				listDato.setMontoSolicProfBp(totalesMontoSolicProfBp1.setScale(2, RoundingMode.HALF_UP));
				listDato.setMontoSolicProfCp(totalesMontoSolicProfCp1.setScale(2, RoundingMode.HALF_UP));
				listDato.setMontoSolicProfLp(totalesMontoSolicProfLp1.setScale(2, RoundingMode.HALF_UP));
				listDato.setTotalAceptados(totalesTotalAceptados1.setScale(2, RoundingMode.HALF_UP));
				listDato.setTotalRechazados(totalesTotalRechazados1.setScale(2, RoundingMode.HALF_UP));
				listDatos.add(listDato);
			 }
		 
		 
	    } catch (Exception ex) {
			MitBusinessException mitBusinessException = new MitBusinessException(GenerateExceptionDetails.generate(
					ErrorCodeService.EX_EXCEPTION, "consulta Carg Abo Por Subcuentas "+ex.getMessage(),
					new Object[] { getClass().getSimpleName(), "consultaCargAboPorSubcuentas()" }, ex));
	
			LOGGER.error(mitBusinessException.getMessage(), ex);
	
		}
			return listDatos;
		}
	
	

}
