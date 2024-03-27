package mx.profuturo.nci.web.views;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.ICalendarioService;
import mx.profuturo.nci.web.util.UtilMapping;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.business.vo.cif.Profuturo_CIFVo;
import mx.profuturo.nci.web.beans.NCI_CIFDataBean;
import mx.profuturo.nci.web.beans.NCI_CIFDataFilter;
import mx.profuturo.nci.web.beans.MovimientosCIFParamBean;
import mx.profuturo.nci.web.service.IMovimientosCifWebService;

@ManagedBean(name = "movimientosCifView")
@ViewScoped
public class MovimeintosCIFView implements Serializable{
	
	private static final long serialVersionUID = 2362129804693514199L;
	private static final String XLS_MIME_TYPE= "application/xls";
	private static final String MSG_EXITO= "La operación se realizó exitosamente";
	private static final Logger LOGGER = LoggerFactory.getLogger(MovimeintosCIFView.class);
	private MovimientosCIFParamBean paramBean;
	private Date siguienteHabil;
	
	@ManagedProperty(value="#{movimientosCifService}")
	IMovimientosCifWebService movimientosCifService;
	
	@ManagedProperty(value="#{calendarioService}")
	ICalendarioService calendarioService;
	
	@PostConstruct
	public void init() {
		siguienteDiaHabil();
		paramBean = new MovimientosCIFParamBean();
		paramBean.setFiltros(new NCI_CIFDataFilter());
		paramBean.getFiltros().setFechaFin(new Date());
		paramBean.getFiltros().setFechaInicio(new Date());
		paramBean.setMaxDate(siguienteHabil==null?new Date():siguienteHabil);
		paramBean.setMensaje(MSG_EXITO);
	}
	
	public void siguienteDiaHabil()  {
		try {
		
			siguienteHabil = calendarioService.consultaSiguienteDiaHabil(new Date());
			
		} catch (MitBusinessException e) {
			FacesContext.getCurrentInstance().addMessage(
					"errorConsulta",  
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR, 
							e.getLocalizedMessage(), 
							"Ocurrió un error en la consulta:" + e.getMessage()));
			LOGGER.error("siguienteDiaHabil():"+e.getMessage(),e);
		}
	}
		
	
	public void consultaCargos() {
		LOGGER.debug("Entra consultaCargos()");
		try {
			paramBean.setResultados(movimientosCifService.consultar(paramBean.getFiltros()));
			LOGGER.debug(paramBean.getResultados().toString());
		} catch (MitBusinessException e) {
			FacesContext.getCurrentInstance().addMessage(
					"errorConsulta",  
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR, 
							e.getLocalizedMessage(), 
							"Ocurrió un error en la consulta:" + e.getMessage()));
			LOGGER.error("consultaCargos():"+e.getMessage(),e);
		}
	}
	
	public void selectMov() {
		LOGGER.debug("Entra selectMov()");
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		String idItem = (String) map.get("idItem");
		paramBean.setMovDetalle(
				paramBean.getResultados().get(
						paramBean.getResultados().indexOf(
								new NCI_CIFDataBean(idItem)
						)
				).clone()
		);
//		//TODO el item en base de datos viene generado
//		regeneraIdItem();
	}
	
	public void editMov() {
		paramBean.getMovDetalle().setUsuarioActualizacion(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
		try {
			movimientosCifService.actualizarNCICIF(UtilMapping.mapCIFBeanToVo(paramBean.getMovDetalle()));
			paramBean.getResultados().set(
					paramBean.getResultados().indexOf(new NCI_CIFDataBean(paramBean.getMovDetalle().getIdItem())), 
					paramBean.getMovDetalle()
			);
			paramBean.setMensaje(MSG_EXITO);
		} catch (MitBusinessException e) {
			paramBean.setMensaje(e.getMessage());
		}
	}
	
	public void send2CIF() {
		try {
			List<Profuturo_CIFVo> sended = UtilMapping.mapListPCIFBeanToVO(paramBean.getCuentasAenviar());
			movimientosCifService.send2CIF(sended,FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
			paramBean.setReporte(movimientosCifService.getDataSended(sended));
			paramBean.setMensaje(MSG_EXITO);
			consultaCargos();
		} catch (MitBusinessException e) {
			paramBean.setMensaje(e.getMessage());
		}
	}

	public void delete2CIF() {
		
		for(NCI_CIFDataBean bean: paramBean.getCuentasAenviar()) {
			paramBean.setMovDetalle(bean);
			paramBean.getMovDetalle().setUsuarioActualizacion(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
			try {
				movimientosCifService.borrarNCICIF(UtilMapping.mapCIFBeanToVo(paramBean.getMovDetalle()));
				paramBean.getResultados().set(
						paramBean.getResultados().indexOf(new NCI_CIFDataBean(paramBean.getMovDetalle().getIdItem())), 
						paramBean.getMovDetalle()
				);
				paramBean.setMensaje(MSG_EXITO);
			} catch (MitBusinessException e) {
				paramBean.setMensaje(e.getMessage());
			}			
		}
		
	}	
	
	public void regeneraIdItem() {
		movimientosCifService.generaIdItem(paramBean.getMovDetalle());
		paramBean.getMovDetalle().setFechaItem(paramBean.getMovDetalle().getFechaDepositoBancario());
	}
	
	public void limpiarBusqueda(){
		init();
	}
	
	public void getReport() {
		try {
			ReporteVO reporte = movimientosCifService.generaReporte(paramBean.getReporte());
			responseReport(reporte.getArchivo(),reporte.getNombre(),XLS_MIME_TYPE);
		} catch (Exception e) {
			paramBean.setMensaje(e.getMessage());
			LOGGER.error(e.getMessage(),e);
		}
	}

	public void getReportPrev() {
		try {
			List<Profuturo_CIFVo> reportePrev = UtilMapping.mapListPCIFBeanToVO(paramBean.getCuentasAenviar());
			paramBean.setReporte(reportePrev);
			ReporteVO reporteVoPrev = movimientosCifService.generaReportePrev(paramBean.getReporte());
			responseReport(reporteVoPrev.getArchivo(),reporteVoPrev.getNombre(),XLS_MIME_TYPE);
			paramBean.setMensaje(MSG_EXITO);
		} catch (Exception e) {
			paramBean.setMensaje(e.getMessage());
			LOGGER.error(e.getMessage(),e);
		}
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
			 paramBean.setMensaje(ex.getMessage());
			 throw mitBusinessException;
		 }
	 }
	
	
	
	public MovimientosCIFParamBean getParamBean() {
		return paramBean;
	}

	public void setParamBean(MovimientosCIFParamBean paramBean) {
		this.paramBean = paramBean;
	}

	public void setMovimientosCifService(IMovimientosCifWebService movimientosCifService) {
		this.movimientosCifService = movimientosCifService;
	}

	public ICalendarioService getCalendarioService() {
		return calendarioService;
	}

	public void setCalendarioService(ICalendarioService calendarioService) {
		this.calendarioService = calendarioService;
	}
	
	
	
}
