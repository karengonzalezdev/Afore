package mx.profuturo.nci.web.views;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.business.vo.cif.DevolucionesCabeceraCifVO;
import mx.profuturo.nci.business.vo.cif.DevolucionesReporteCDCifVO;
import mx.profuturo.nci.web.beans.DevolucionesSegCIFBean;
import mx.profuturo.nci.web.service.IMovimientosCifWebService;

@ManagedBean(name = "devolucionesSeguimientoCIFView")
@ViewScoped

public class DevolucionesSeguimientoCIFView implements Serializable{
	
	private static final long serialVersionUID = 2362129345693514199L;
	private static final Logger LOGGER = LoggerFactory.getLogger(DevolucionesSeguimientoCIFView.class);
	private static final String XLS_MIME_TYPE= "application/xls";

	private DevolucionesSegCIFBean paramBeanSeg;
	
	@ManagedProperty(value="#{movimientosCifService}")
	IMovimientosCifWebService movimientosCifService;
	
	@PostConstruct
	public void init() {
		LOGGER.info("::::::::::FOP::::::Inicializando::::DevolucionesSeguimientoCIFView::");
		paramBeanSeg = new DevolucionesSegCIFBean();
		paramBeanSeg.setData(new DevolucionesCabeceraCifVO());

	}

	public void buscaReporte() {
		paramBeanSeg.setResultados(movimientosCifService.getListaSeguimientoEstatusCIF(paramBeanSeg.getData()));
		LOGGER.info(paramBeanSeg.getResultados().toString());
	}
	
	public void generarReporte() {
		try {
			ReporteVO reporteVoPrev = movimientosCifService.generaReporteDevSeg(paramBeanSeg.getResultados());
			responseReport(reporteVoPrev.getArchivo(),reporteVoPrev.getNombre(),XLS_MIME_TYPE);
		} catch (Exception e) {
			paramBeanSeg.setMsgMsg(e.getMessage());
			LOGGER.error(e.getMessage(),e);
			RequestContext.getCurrentInstance().update("idDialogIdentificadorCiError");
			RequestContext.getCurrentInstance().execute("PF('dialogIdentificadorCiError').show()");
		}		
	}
	
	
	public void limpiarBusqueda(){
		paramBeanSeg.getData().setPfTranId(null);
	}		
	
	private void responseReport(final byte[] byteArray, final String fileNameReport, final String type)throws MitBusinessException {
		
		 try{
			 final HttpServletResponse response  = (HttpServletResponse )FacesContext.getCurrentInstance().getExternalContext().getResponse();
			 response.setContentType(type);
			 response.setContentLength(byteArray.length);
			 response.setHeader("Content-Disposition", "attachment; filename=" + fileNameReport);
			 response.getOutputStream().write(byteArray);
			 LOGGER.debug(""+byteArray);
			 FacesContext.getCurrentInstance().responseComplete();
		 } catch(final IOException ex){
			 final MitBusinessException mitBusinessException = new MitBusinessException(
					 GenerateExceptionDetails.generate(
							 ErrorCodeService.EX_IOEXCEPTION,"Al Responder Reporte:"+ex.getMessage(), 
							 new Object[] { getClass().getName(), "responseReport()" }, ex));

			 LOGGER.error(mitBusinessException.getMessage(),ex);

			 throw mitBusinessException;
		 } catch(final Exception ex)	      {
			 final MitBusinessException mitBusinessException = new MitBusinessException(
					 GenerateExceptionDetails.generate(
							 ErrorCodeService.EX_EXCEPTION,"Al enviar Reporte:"+ex.getMessage(), 
							 new Object[] { getClass().getName(), "responseReport()" }, ex));

			 LOGGER.error(mitBusinessException.getMessage(),ex);
			 throw mitBusinessException;
		 }
	 }	
	
	public DevolucionesSegCIFBean getParamBeanSeg() {
		return paramBeanSeg;
	}

	public void setParamBeanSeg(DevolucionesSegCIFBean paramBeanSeg) {
		this.paramBeanSeg = paramBeanSeg;
	}
	
	public void setMovimientosCifService(IMovimientosCifWebService movimientosCifService) {
		this.movimientosCifService = movimientosCifService;
	}	
	
}
