package mx.profuturo.nci.web.views;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.profuturo.nci.business.exception.ErrorCodeService;
import mx.profuturo.nci.business.exception.GenerateExceptionDetails;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.util.Constantes;
import mx.profuturo.nci.business.util.UtilMethod;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.web.beans.ConsultaAforeMovilBean;
import mx.profuturo.nci.web.beans.ConsultaDetalleAforeMovilBean;
import mx.profuturo.nci.web.service.IMonitoreoAforeMovilWebService;

@ManagedBean(name = "monitoreoAforeMovilView")
@ViewScoped
//@Component
public class MonitoreoAforeMovilView implements Serializable {

	private static final String XLS_MIME_TYPE= "application/xls";
	private static final long serialVersionUID = 4439492531143705320L;
	private static final Logger LOGGER = LoggerFactory.getLogger(MonitoreoAforeMovilView.class);
	
	@ManagedProperty(value="#{monitoreoAforeMovilWebService}")
	IMonitoreoAforeMovilWebService monitoreoAforeMovilWebService;
	
	private String curp;
	private Date fecInicio;
	private Date fecFin;
	private String filtro;
	private String filtroVal;
	private boolean muestraTabla;
	private List<ConsultaAforeMovilBean> lstMontos;
	private boolean habilitaEnviar;
	private boolean habilitaBtnRechazar;
	private boolean habilitaBtnExportar;
	
	private String cuentaDialog;
	private String curpDialog;
	private String nombreDialog;
	private boolean muestraCuenta;
	private List<ConsultaDetalleAforeMovilBean> detalleDialog;
	
	private String fechaMaxIni;
	private String fechaMaxFin;
	private String errorMessage;
	
	private BigDecimal montoAfiliado;
	private BigDecimal montoIndependiente;

	private String filtroOpc;
	private boolean btnRechazo;
	private boolean btnLiberar;
	
	private String montoMinimoAfiliado;
	private String montoMinimoIndependiente;
	
	public void cargaMontos() {
		RequestContext context = RequestContext.getCurrentInstance();
		try{
			montoMinimoAfiliado = monitoreoAforeMovilWebService.obtenerParamCatConfig(Constantes.MONTO_MINIMO_AFILIADO);
			montoMinimoIndependiente = monitoreoAforeMovilWebService.obtenerParamCatConfig(Constantes.MONTO_MINIMO_INDEPENDENDIENTE);
			}
		catch (MitBusinessException e) {
			setErrorMessage("Ocurrió un error en la consulta de los montos mínimos: " + e.getMessage());
			context.execute("PF('dlgErrorMessage').show()");
			LOGGER.error("Ocurrió un error en la consulta de los montos mínimos", e);
		}
		catch (Exception e) {
			setErrorMessage("Ocurrió un error: " + e.getMessage());
			context.execute("PF('dlgErrorMessage').show()");
			LOGGER.error("Ocurrió un error: " + e.getMessage(), e);
		}
	}
	
	public void limpiarFormulario(){
		 setCurp("");
		 setFecInicio(null);
		 setFecFin(null);
		 setMuestraTabla(false);
		 setHabilitaEnviar(true);
		 setHabilitaBtnRechazar(false);
		 setFiltro("");
		 setFiltroVal("");
		 
		 setFechaMaxIni(null);
		 setFechaMaxFin(null);
		 setErrorMessage("");
		 
		 setFiltroOpc("");
		 setBtnRechazo(false);
		 setBtnLiberar(false);
		 setMontoAfiliado(BigDecimal.ZERO);
		 setMontoIndependiente(BigDecimal.ZERO);
	}
	
	public void ejecutarConsulta(){	
		RequestContext context = RequestContext.getCurrentInstance();
		
		if(filtroVal!=null && !filtro.isEmpty()){
			if(filtroVal.equals("NSS")){
				if(filtro!=null && filtro.length()<11){
					setErrorMessage("NSS, no es un valor válido.");
					context.execute("PF('dlgErrorMessage').show()");
					return;
				}
			}			
			if(filtroVal.equals("CURP")){
				if(filtro!=null && filtro.length()<18){
					setErrorMessage("CURP, no es un valor válido.");
					context.execute("PF('dlgErrorMessage').show()");
					return;
				}
			}
		}
		
		if(fecInicio!=null && fecFin!=null){
			Calendar fecI = Calendar.getInstance();
			fecI.setTime(fecInicio);
			fecI.set(Calendar.HOUR_OF_DAY, 0);
			fecI.set(Calendar.MINUTE, 0);
			fecI.set(Calendar.SECOND, 0);
			fecI.set(Calendar.MILLISECOND, 0);
			
			Calendar fecF = Calendar.getInstance();
			fecF.setTime(fecFin);
			fecF.set(Calendar.HOUR_OF_DAY, 0);
			fecF.set(Calendar.MINUTE, 0);
			fecF.set(Calendar.SECOND, 0);
			fecF.set(Calendar.MILLISECOND, 0);
	
			if(fecF.getTime().compareTo(fecI.getTime())<0){
				setErrorMessage("La fecha final no puede ser menor a la fecha inicial");
				context.execute("PF('dlgErrorMessage').show()");

				setMuestraTabla(false);
				setHabilitaEnviar(false);
				setHabilitaBtnExportar(false);
				setHabilitaBtnRechazar(false);
				return;
			}
			
			Short plazoRes = (short) ((fecF.getTime().getTime() - fecI.getTime().getTime())  / (1000 * 60 * 60 * 24));
			if(plazoRes.intValue()>90){
				setErrorMessage("La diferencia entre fechas no puede ser mayor a 90 días");
				context.execute("PF('dlgErrorMessage').show()");

				setMuestraTabla(false);
				setHabilitaEnviar(false);
				setHabilitaBtnExportar(false);
				setHabilitaBtnRechazar(false);
				return;
			}
		}else if((fecInicio==null && fecFin!=null)||(fecInicio!=null && fecFin==null)){
			setErrorMessage("Debe capturar ambas fechas para realizar la búsqueda por fechas");
			context.execute("PF('dlgErrorMessage').show()");
			
			setMuestraTabla(false);
			setHabilitaEnviar(false);
			setHabilitaBtnExportar(false);
			setHabilitaBtnRechazar(false);
			return;
		}else{
			if(filtro==null || (filtro.trim().isEmpty())){
				setErrorMessage("Es obligatorio seleccionar un filtro");
				context.execute("PF('dlgErrorMessage').show()");
				
				setMuestraTabla(false);
				setHabilitaEnviar(false);
				setHabilitaBtnExportar(false);
				setHabilitaBtnRechazar(false);
				return;			
			}			
		}

		setErrorMessage("");
		setMuestraTabla(true);
		setHabilitaEnviar(true);
		setHabilitaBtnExportar(false);
		setHabilitaBtnRechazar(false);
		
		setBtnRechazo(false);
		setBtnLiberar(false);
		setMontoAfiliado(BigDecimal.ZERO);
		setMontoIndependiente(BigDecimal.ZERO);
		setFiltroOpc("");
		try {
			cargaMontos();
			List<ConsultaAforeMovilBean> lstM = monitoreoAforeMovilWebService.consultarMovimientosAforeMovil(filtroVal, filtro, fecInicio, fecFin);
			Collections.sort(lstM, new Comparator<ConsultaAforeMovilBean>() {
		        public int compare(ConsultaAforeMovilBean beans2, ConsultaAforeMovilBean beans1)
		        {
		            return  beans1.getPrimerAportacion().compareTo(beans2.getPrimerAportacion());
		        }
		    });
			Collections.reverse(lstM);
			int rowId = 1;
			for(ConsultaAforeMovilBean aforeMovil : lstM){
				lstM.get(rowId-1).setRowId(rowId);
				rowId++;
			}
			setLstMontos(lstM); 
		} catch (MitBusinessException e) {
			LOGGER.error("ERROR UBICACION",e);
		} 
	}
	
	public void enviarTraspasos(){
		
	}
	
	public void exportarRegistros(){
		try {
			ReporteVO reporte = monitoreoAforeMovilWebService.generaReporteDepositosAforeMovil(lstMontos);
			responseReport(reporte.getArchivo(),reporte.getNombre(),XLS_MIME_TYPE);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
	}
	
	public String getCurp() {
		return curp;
	}
	
	public void setCurp(String curp) {
		this.curp = curp;
	}
	
	public Date getFecInicio() {
		return fecInicio;
	}
	
	public void setFecInicio(Date fecInicio) {
		this.fecInicio = fecInicio;
	}
	
	public Date getFecFin() {
		return fecFin;
	}
	
	public void setFecFin(Date fecFin) {
		this.fecFin = fecFin;
	}
	
	public boolean isMuestraTabla() {
		return muestraTabla;
	}
	
	public void setMuestraTabla(boolean muestraTabla) {
		this.muestraTabla = muestraTabla;
	}
	
	public List<ConsultaAforeMovilBean> getLstMontos() {
		return lstMontos;
	}
	
	public void setLstMontos(List<ConsultaAforeMovilBean> lstMontos) {
		this.lstMontos = lstMontos;
	}
	
	/**
	 * Metodo para limpiar el campo de filtrar cuando cambie el radio button de tipo de busqueda
	 */
	public void onCleanCampoFiltrar(){
		this.filtro = "";
		this.lstMontos = new ArrayList<ConsultaAforeMovilBean>();
		
		setFecInicio(null);
		setFecFin(null);
	}
	
	public void changeOption(){
		boolean flujo = false;
		if(filtroOpc!=null && !filtroOpc.isEmpty()){
			if(filtroOpc.equals("RECHAZAR")){
				setBtnRechazo(true);
				setBtnLiberar(false);
				
				flujo=true;
			}else if(filtroOpc.equals("LIBERAR")){
				setBtnRechazo(false);
				setBtnLiberar(true);
				
				flujo=true;
			}
			
			setHabilitaBtnRechazar(false);
			for(ConsultaAforeMovilBean bean: lstMontos){
				if(filtroOpc.equals("LIBERAR")){
					if(bean.getEstatus().getId().equals(new Short("968"))){
						if(bean.getDiasPrimerAportacion()!=null && bean.getDiasPrimerAportacion()>=55){
							bean.setEditabled(true);
						}else{
							bean.setEditabled(false);
						}
					}else{
						bean.setEditabled(false);
					}
					bean.setSelect(false);
				}else{
					if(bean.getEstatus().getId().equals(new Short("968"))){
						bean.setEditabled(true);
					}else{
						bean.setEditabled(false);
					}					
					bean.setSelect(false);
				}
			}
		}
	}
	
	public boolean isHabilitaEnviar() {
		return habilitaEnviar;
	}
	
	public void setHabilitaEnviar(boolean habilitaEnviar) {
		this.habilitaEnviar = habilitaEnviar;
	}
	
	public void  myChangeListener(){
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();

		setCuentaDialog((String) map.get("cuenta"));
		
		if(getCuentaDialog()!=null && !getCuentaDialog().trim().isEmpty()){
			setMuestraCuenta(true);
		}
		else{
			setMuestraCuenta(false);
		}
		
		setCurpDialog((String) map.get("curp"));
		setNombreDialog((String) map.get("nombre"));
		String idConcil = (String) map.get("idConciliacion");
		
		for(ConsultaAforeMovilBean reg : lstMontos){
			if(reg.getCuenta().equals(getCuentaDialog()) && reg.getCurp().equals(getCurpDialog()) &&
			   reg.getNombre().equals(getNombreDialog()) && reg.getIdConciliacion().toString().equals(idConcil) ){
				
				setDetalleDialog(reg.getMovimientos());				
				break;
			}
		}		
    }
	
	public void addMessage() {
		for(int i=0;i<lstMontos.size();i++){
			LOGGER.info(lstMontos.get(i).toString());
		}		
    }

	public void validaBtnRechazar(){
		setHabilitaBtnRechazar(false);
		for(ConsultaAforeMovilBean reg : lstMontos){
			if(reg.isSelect()){
				setHabilitaBtnRechazar(true);
				break;
			}
		}
	}
	
	
	public void rechazarRegistros() throws MitBusinessException{
		List<ConsultaAforeMovilBean> beans = new ArrayList<ConsultaAforeMovilBean>();
		for(ConsultaAforeMovilBean bean: lstMontos){
			if(bean.isSelect()){
				beans.add(bean);
			}
		}
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		boolean result = monitoreoAforeMovilWebService.rechazarMovimientos(beans,facesContext.getExternalContext().getRemoteUser());
		RequestContext context = RequestContext.getCurrentInstance();
		if(result){
			context.execute("PF('dlgExitoRechazar').show()");
			context.execute("PF('resultRechazar').hide()");
		}else{
			context.execute("PF('dlgErrorRechazar').show()");
			context.execute("PF('resultRechazar').hide()");
		}
		
		limpiarFormulario();
	}
	
	public void liberarRegistros() throws MitBusinessException{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		List<ConsultaAforeMovilBean> beans = new ArrayList<ConsultaAforeMovilBean>();
		
		BigDecimal impMinAfiliado = new BigDecimal(monitoreoAforeMovilWebService.obtenerParamCatConfig(new Short("13")));
		BigDecimal impMinIndependiente = new BigDecimal(monitoreoAforeMovilWebService.obtenerParamCatConfig(new Short("14")));  
		
		boolean generaFolio = false;
		for(ConsultaAforeMovilBean bean: lstMontos){
			if(bean.isSelect()){
				generaFolio = true;
				break;
			}
		}
		boolean flujo = false;
		if(generaFolio){
			String folio = monitoreoAforeMovilWebService.obtenerFolioNuevoAforeMovil(7, 101, 1, new Short("17"), 
                                                                                     facesContext.getExternalContext().getRemoteUser());
			BigDecimal impCompara = BigDecimal.ZERO;
			List<String> lstCurp = new ArrayList<String>();
			if(!folio.isEmpty()){
				for(ConsultaAforeMovilBean bean: lstMontos){
					if(bean.isSelect()){
						if(bean.getNumCtaInvdual()!=null && !bean.getNumCtaInvdual().trim().isEmpty()){
							impCompara = impMinAfiliado;
						}else{
							impCompara = impMinIndependiente;
						}
	
						int compara = bean.getImporteAcumulado().compareTo(impCompara);
						if(!(compara==0 || compara==1)){

							Long nss = new Long("0");
							Long celular = new Long("0");
							if(bean.getNss()!=null && !bean.getNss().isEmpty()){
								nss = new Long(bean.getNss());
							}
							if(bean.getCelular()!=null && !bean.getCelular().isEmpty()){
								celular = new Long(bean.getCelular());
							}
							
							int inserCon = 0;
							if(bean.getNumCtaInvdual()==null || bean.getNumCtaInvdual().isEmpty()){
								inserCon = monitoreoAforeMovilWebService.insertaRegistroConciliacion(folio, new Short("1097"), 
										null, impCompara.subtract(bean.getImporteAcumulado()), bean.getNombreCli(), 
										bean.getApPaternoCli(), bean.getApMaternoCli(), bean.getCurp(), bean.getRfc(), nss , 
										bean.getCorreo(), celular, facesContext.getExternalContext().getRemoteUser(), 
										new BigInteger(monitoreoAforeMovilWebService.obtenerParamCatConfig(new Short("15"))));	
							}else{
								inserCon = monitoreoAforeMovilWebService.insertaRegistroConciliacion(folio, new Short("1097"), 
										new Long(bean.getNumCtaInvdual()), impCompara.subtract(bean.getImporteAcumulado()), bean.getNombreCli(), 
										bean.getApPaternoCli(), bean.getApMaternoCli(), bean.getCurp(), bean.getRfc(), nss , 
										bean.getCorreo(), celular, facesContext.getExternalContext().getRemoteUser(), 
										new BigInteger(monitoreoAforeMovilWebService.obtenerParamCatConfig(new Short("15"))));
							}

						}
						lstCurp.add(bean.getCurp());
						
						for(ConsultaDetalleAforeMovilBean movs: bean.getMovimientos()){
							monitoreoAforeMovilWebService.actualizaStatusConciliacion(movs.getIdConciliacion(),new Short("1104"), facesContext.getExternalContext().getRemoteUser());							
						}
					}
				}
				flujo = monitoreoAforeMovilWebService.invocaServicioLiberar(lstCurp, facesContext.getExternalContext().getRemoteUser());
			}
		}
		
		
		RequestContext context = RequestContext.getCurrentInstance();
//		if(result){
			context.execute("PF('dlgExitoLiberar').show()");
			context.execute("PF('liberarDialog').hide()");
//		}else{
//			context.execute("PF('dlgErrorRechazar').show()");
//			context.execute("PF('resultRechazar').hide()");
//		}
		limpiarFormulario();
	}
	
	public String getCurpDialog() {
		return curpDialog;
	}
	
	public void setCurpDialog(String curpDialog) {
		this.curpDialog = curpDialog;
	}
	
	public String getNombreDialog() {
		return nombreDialog;
	}
	
	public void setNombreDialog(String nombreDialog) {
		this.nombreDialog = nombreDialog;
	}
	
	public String getFiltro() {
		return filtro;
	}
	
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	public String getFiltroVal() {
		return filtroVal;
	}
	
	public void setFiltroVal(String filtroVal) {
		this.filtroVal = filtroVal;
	}
	
	public String getCuentaDialog() {
		return cuentaDialog;
	}
	
	public void setCuentaDialog(String cuentaDialog) {
		this.cuentaDialog = cuentaDialog;
	}
	
	public IMonitoreoAforeMovilWebService getMonitoreoAforeMovilWebService() {
		return monitoreoAforeMovilWebService;
	}
	
	public void setMonitoreoAforeMovilWebService(IMonitoreoAforeMovilWebService monitoreoAforeMovilWebService) {
		this.monitoreoAforeMovilWebService = monitoreoAforeMovilWebService;
	}
	
	public List<ConsultaDetalleAforeMovilBean> getDetalleDialog() {
		return detalleDialog;
	}
	
	public void setDetalleDialog(List<ConsultaDetalleAforeMovilBean> detalleDialog) {
		this.detalleDialog = detalleDialog;
	}
	
	public boolean isMuestraCuenta() {
		return muestraCuenta;
	}
	
	public void setMuestraCuenta(boolean muestraCuenta) {
		this.muestraCuenta = muestraCuenta;
	}
	
	public boolean isHabilitaBtnRechazar() {
		return habilitaBtnRechazar;
	}
	
	public void setHabilitaBtnRechazar(boolean habilitaBtnRechazar) {
		this.habilitaBtnRechazar = habilitaBtnRechazar;
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
	
	public String getFechaMaxIni() {
		if(fechaMaxIni==null){
			Date fechaAct = new Date(System.currentTimeMillis());
			setFechaMaxIni(UtilMethod.dateFormatter(fechaAct,"dd/MM/yyyy"));
		}
		return fechaMaxIni;
	}

	public void setFechaMaxIni(String fechaMaxIni) {
		this.fechaMaxIni = fechaMaxIni;
	}
	
	public String getFechaMaxFin() {
		if(fechaMaxFin==null){
			Date fechaAct = new Date(System.currentTimeMillis());
			setFechaMaxFin(UtilMethod.dateFormatter(fechaAct,"dd/MM/yyyy"));	
		}
		return fechaMaxFin;
	}

	public void setFechaMaxFin(String fechaMaxFin) {
		this.fechaMaxFin = fechaMaxFin;
	}
	
    public void onDateSelectIni(SelectEvent event) {
    	setFiltroVal("");
    	setFiltro("");
    }
    
    public void onDateSelectFin(SelectEvent event) {
    	
    }
    
	public boolean isHabilitaBtnExportar() {
		boolean varReturn = false;
		if(lstMontos!=null && lstMontos.size()>0){
			for(ConsultaAforeMovilBean bean : lstMontos){
				if(bean.getEstatus().getId()==968){
					varReturn = true;
					break;
				}
			}
		}
		return varReturn;
	}
	
	public void setHabilitaBtnExportar(boolean habilitaBtnExportar) {
		this.habilitaBtnExportar = habilitaBtnExportar;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getFiltroOpc() {
		return filtroOpc;
	}
	
	public void setFiltroOpc(String filtroOpc) {
		this.filtroOpc = filtroOpc;
	}
	
	public boolean isBtnRechazo() {
		return btnRechazo;
	}
	
	public void setBtnRechazo(boolean btnRechazo) {
		this.btnRechazo = btnRechazo;
	}
	
	public boolean isBtnLiberar() {
		return btnLiberar;
	}
	
	public void setBtnLiberar(boolean btnLiberar) {
		this.btnLiberar = btnLiberar;
	}
	
	public BigDecimal getMontoAfiliado() {
		return montoAfiliado;
	}
	
	public void setMontoAfiliado(BigDecimal montoAfiliado) {
		this.montoAfiliado = montoAfiliado;
	}
	
	public BigDecimal getMontoIndependiente() {
		return montoIndependiente;
	}
	
	public void setMontoIndependiente(BigDecimal montoIndependiente) {
		this.montoIndependiente = montoIndependiente;
	}	
	public String getMontoMinimoAfiliado() {
		return montoMinimoAfiliado;
	}

	public void setMontoMinimoAfiliado(String montoMinimoAfiliado) {
		this.montoMinimoAfiliado = montoMinimoAfiliado;
	}

	public String getMontoMinimoIndependiente() {
		return montoMinimoIndependiente;
	}

	public void setMontoMinimoIndependiente(String montoMinimoIndependiente) {
		this.montoMinimoIndependiente = montoMinimoIndependiente;
	}
}