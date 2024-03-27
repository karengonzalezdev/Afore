package mx.profuturo.nci.web.views;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
import mx.profuturo.nci.business.report.beans.BasicReportBean;
import mx.profuturo.nci.business.util.Constantes;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.web.beans.DiversificacionBean;
import mx.profuturo.nci.web.beans.GenericCatalogoBean;
import mx.profuturo.nci.web.beans.SolicitudBean;
import mx.profuturo.nci.web.beans.SolicitudFilterBean;
import mx.profuturo.nci.web.beans.ValidacionesBean;
import mx.profuturo.nci.web.beans.traspasos.MonitoreoSolicitudesDetalleBean;
import mx.profuturo.nci.web.beans.DetalleSpeiApoVol;
import mx.profuturo.nci.web.service.IConsultaDomiciliacionWebService;

@ManagedBean(name = "consultaDomiciliacionView")
@ViewScoped
public class ConsultaDomiciliacionView implements Serializable{

	private static final String XLS_MIME_TYPE= "application/xls";
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaDomiciliacionView.class);
	
	@ManagedProperty(value="#{consultaDomiciliacionWebService}")
	IConsultaDomiciliacionWebService consultaDomiciliacionWebService;
	
	private String clientePorTipo;
	private String clienteBusqueda;
	private Short selOrigenApo;
	private Short selEstatus;
	private Date fechaInicio;
	private Date fechaFin;
	
	private List<SolicitudBean> listaSolicitud;
	private SolicitudBean selectedSolicitud;
	
	private List<SolicitudBean> listaSolicitudHis;
	private List<DiversificacionBean> listaDiversificacion;
	private List<ValidacionesBean> listaValidacion;
	private List<SolicitudBean> listaSolicitudHisNoDomi;
	private DetalleSpeiApoVol detalleSpei;
	
	private List<SolicitudBean> listaCuentasSolicitud;
	private boolean muestraCuentas;
	private SolicitudBean selectedCuentaSolicitud;
	private boolean cuentaSeleccionada;
	
	private List<GenericCatalogoBean> origenAportacionLista;
	private List<GenericCatalogoBean> estatusLista;
	private boolean selectTipoBusq;
	private boolean tablaConsulta;
	
	private boolean habilitaBtnPrincipal;
	
	private String txtFiltroDet;
	private String txtFiltroValorDet;
	private Short txtOrigenDet;
	private String txtOrigenDetLetra;
	private Short txtEstatusDet;
	private Date fechaInicioDet;
	private Date fechaFinDet;
	
	private Date maxDate= new Date();
	
	public void limpiar() {
	}
	
	@PostConstruct
	public void init() {
		setHabilitaBtnPrincipal(true);
	}

	public void limpiarBusq(){
		setClienteBusqueda("");
		selOrigenApo = null;
		selEstatus = null;
		fechaInicio = null;
		fechaFin = null;
		
		
		setMuestraCuentas(false);
		setSelectTipoBusq(true);
		setTablaConsulta(false);
		setCuentaSeleccionada(false);
		
		selectedCuentaSolicitud= null;
		selectedSolicitud = null;
		setHabilitaBtnPrincipal(true);
	}

	public void limpiarBusqDet(){
		setTxtFiltroDet("");
		setTxtFiltroValorDet("");
		setTxtOrigenDet((short)0);
		setTxtEstatusDet((short)0);
	 	setFechaInicioDet(null);
	 	setFechaFinDet(null);
	}
		
	public void busquedaPrincipal() throws MitBusinessException,ParseException{
		if(!(clientePorTipo!=null && !clientePorTipo.trim().isEmpty() && clienteBusqueda!=null && !clienteBusqueda.trim().isEmpty())){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debes ingresar al menos un filtro de búsqueda, Cuenta/NSS/CURP"));
			return;
		}   
		
		/* Atencion defecto 2395
		 * if(!(fechaInicio!=null && fechaFin!=null && selOrigenApo!=null && selOrigenApo>0)){
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debes ingresar al menos un filtro de búsqueda, Cuenta/NSS/CURP u Origen y Fechas"));
		    return;
		}*/
		
		
		selectedCuentaSolicitud= null;
		selectedSolicitud = null;
		
		buscar();
	}
	
	public void buscar() throws MitBusinessException,ParseException{
		SolicitudFilterBean solicitudFilter = new SolicitudFilterBean();
		if(clientePorTipo.equals(Constantes.CUENTA) && !clienteBusqueda.isEmpty()){
			if(!clienteBusqueda.isEmpty()){
				long cuenta = Long.parseLong(clienteBusqueda.trim()); 
				solicitudFilter.setNumCuentaIndividual(cuenta);
			}
		}
		if(clientePorTipo.equals(Constantes.LBL_CURP) && !clienteBusqueda.isEmpty()){
			solicitudFilter.setCurp(clienteBusqueda);
		}
		if(clientePorTipo.equals(Constantes.LBL_NSS) && !clienteBusqueda.isEmpty()){
			solicitudFilter.setNss(clienteBusqueda);
		}
		
		if(selEstatus!=null && selEstatus>0){
			GenericCatalogoBean estatusSolicitud = new GenericCatalogoBean();
			estatusSolicitud.setId(selEstatus);
			solicitudFilter.setEstatusSolicitud(estatusSolicitud);
		}
		
		if(selOrigenApo!=null && selOrigenApo>0){
			GenericCatalogoBean origenApo = new GenericCatalogoBean();
			origenApo.setId(selOrigenApo);
			solicitudFilter.setOrigenAportacion(origenApo);
		}
		
		if(fechaInicio!=null){
			SimpleDateFormat sdfInicio = new SimpleDateFormat("dd/MM/yy hh:mm:ss.FF");
			String strDateInicio = sdfInicio.format(fechaInicio);
			Date dtInicio = sdfInicio.parse(strDateInicio);
			solicitudFilter.setFechaInicio(dtInicio);
		}
		
		if(fechaFin!=null){
			String strDateFin;
			SimpleDateFormat sdfFin = new SimpleDateFormat("dd/MM/yy hh:mm:ss.FF");
			if(fechaFin != null){
				strDateFin = sdfFin.format(fechaFin);
			}else{
				Date fechaActual = new Date();
				strDateFin = sdfFin.format(fechaActual);
			}
			Date dtFin = sdfFin.parse(strDateFin);
			solicitudFilter.setFechaFinal(dtFin);
		}
		
		if(selectedCuentaSolicitud!=null){
			solicitudFilter.setCurp(selectedCuentaSolicitud.getCurp());
			solicitudFilter.setNss(selectedCuentaSolicitud.getNss());
			solicitudFilter.setNumCuentaIndividual(selectedCuentaSolicitud.getNumCuentaIndividual());
			setListaCuentasSolicitud(null);
		}else{
			setListaSolicitud(null);
//			listaCuentasSolicitud = consultaDomiciliacionWebService.consultarCuentas(solicitudFilter);			
			//291(DOMICILIACION), 844(DOMICILIACION ESAR), 845(DOMI TRASPASO), 
			if(selOrigenApo.equals(new Short("291")) || selOrigenApo.equals(new Short("844")) || selOrigenApo.equals(new Short("845"))){
				List<Short> lstOrigen = new ArrayList<Short>();
				Short escenario = new Short("0");
				if(selOrigenApo.equals(new Short("291"))){
					lstOrigen.add(new Short("485"));	//APP ASESORA
					lstOrigen.add(new Short("462"));	//APP IDENTIFICATE
					lstOrigen.add(new Short("463"));	//PORTAL
					lstOrigen.add(new Short("465"));	//MOVIL
					
					escenario = new Short("1");
				}else if (selOrigenApo.equals(new Short("844"))){
					lstOrigen.add(new Short("464"));	//E-SAR
					
					escenario = new Short("2");
				}else if (selOrigenApo.equals(new Short("845"))){
					lstOrigen.add(new Short("466"));	//OPERACIONES
					lstOrigen.add(new Short("1043"));	//OPERACIONES FV
					
					escenario = new Short("3");
				}
				solicitudFilter.setSubOrigenes(lstOrigen);
				listaCuentasSolicitud = consultaDomiciliacionWebService.consultarCuentasSolici(solicitudFilter, escenario);
			}//288(INTERNET), 289(NOMINA), 1115(RECLASIFICACION), 290(RED COMERCIAL), 287(VENTANILLA)
			else if(selOrigenApo.equals(new Short("288")) || selOrigenApo.equals(new Short("289")) || selOrigenApo.equals(new Short("1115"))
					|| selOrigenApo.equals(new Short("290")) || selOrigenApo.equals(new Short("287"))){
				listaCuentasSolicitud = consultaDomiciliacionWebService.consultarCuentasConcil(solicitudFilter);
			}//758(NO IDENTIFICADO), 1004(REINTEGRO)
			else if(selOrigenApo.equals(new Short("758")) || selOrigenApo.equals(new Short("1004"))){
				listaCuentasSolicitud = consultaDomiciliacionWebService.consultarCuentasBancos(solicitudFilter);
			}//292(SPEI)
			else if(selOrigenApo.equals(new Short("292"))){
				listaCuentasSolicitud = consultaDomiciliacionWebService.consultarCuentasSpei(solicitudFilter);
			}else{
				listaCuentasSolicitud = consultaDomiciliacionWebService.consultarCuentasTodos(solicitudFilter);
			}
		}

		if(listaCuentasSolicitud!=null && listaCuentasSolicitud.size()>1){		
			setTablaConsulta(false);
			setMuestraCuentas(true);
			
			setSelectedCuentaSolicitud(null);
			setCuentaSeleccionada(false);
		}else{
			setHabilitaBtnPrincipal(true);
			setSelectedSolicitud(null);
			
			if(selectedCuentaSolicitud!=null){
				if(selectedCuentaSolicitud.getEscenario().equals(new Short("1"))
				   || selectedCuentaSolicitud.getEscenario().equals(new Short("2")) 
				   || selectedCuentaSolicitud.getEscenario().equals(new Short("3"))){//SOLICITUD
					
					List<Short> lstOrigen = new ArrayList<Short>();
					if(selectedCuentaSolicitud.getEscenario().equals(new Short("1"))){
						lstOrigen.add(new Short("485"));	//APP ASESORA
						lstOrigen.add(new Short("462"));	//APP IDENTIFICATE
						lstOrigen.add(new Short("463"));	//PORTAL
						lstOrigen.add(new Short("465"));	//MOVIL
					}else if (selectedCuentaSolicitud.getEscenario().equals(new Short("2"))){
						lstOrigen.add(new Short("464"));	//E-SAR
					}else if (selectedCuentaSolicitud.getEscenario().equals(new Short("3"))){
						lstOrigen.add(new Short("466"));	//OPERACIONES
						lstOrigen.add(new Short("1043"));	//OPERACIONES FV
					}
					solicitudFilter.setSubOrigenes(lstOrigen);
					
					listaSolicitud = consultaDomiciliacionWebService.consultarListSolicitud(solicitudFilter);		
				}else if(selectedCuentaSolicitud.getEscenario().equals(new Short("4"))){//CONCILIACION
					listaSolicitud = consultaDomiciliacionWebService.consultarListConciliacion(solicitudFilter);
//				}else if(selectedCuentaSolicitud.getEscenario().equals(new Short("5"))){//BANCOS
//					listaSolicitud = consultaDomiciliacionWebService.consultarListBancos(solicitudFilter);
				}else if(selectedCuentaSolicitud.getEscenario().equals(new Short("6"))){//SPEI
					listaSolicitud = consultaDomiciliacionWebService.consultarListSpei(solicitudFilter);
				}else{
					listaSolicitud = consultaDomiciliacionWebService.consultarListTodos(solicitudFilter);
				}
			}else{
				if(listaCuentasSolicitud.size()==1){
					SolicitudBean solCuenta = listaCuentasSolicitud.get(0);
				}
				
				if(selOrigenApo.equals(new Short("291")) || selOrigenApo.equals(new Short("844")) || selOrigenApo.equals(new Short("845"))){
					List<Short> lstOrigen = new ArrayList<Short>();
					Short escenario = new Short("0");
					if(selOrigenApo.equals(new Short("291"))){
						lstOrigen.add(new Short("485"));	//APP ASESORA
						lstOrigen.add(new Short("462"));	//APP IDENTIFICATE
						lstOrigen.add(new Short("463"));	//PORTAL
						lstOrigen.add(new Short("465"));	//MOVIL
						
						escenario = new Short("1");
					}else if (selOrigenApo.equals(new Short("844"))){
						lstOrigen.add(new Short("464"));	//E-SAR
						
						escenario = new Short("2");
					}else if (selOrigenApo.equals(new Short("845"))){
						lstOrigen.add(new Short("466"));	//OPERACIONES
						lstOrigen.add(new Short("1043"));	//OPERACIONES FV
						
						escenario = new Short("3");
					}
					solicitudFilter.setSubOrigenes(lstOrigen);
					listaSolicitud = consultaDomiciliacionWebService.consultarListSolicitud(solicitudFilter);
				}//288(INTERNET), 289(NOMINA), 1115(RECLASIFICACION), 290(RED COMERCIAL), 287(VENTANILLA)
				else if(selOrigenApo.equals(new Short("288")) || selOrigenApo.equals(new Short("289")) || selOrigenApo.equals(new Short("1115"))
						|| selOrigenApo.equals(new Short("290")) || selOrigenApo.equals(new Short("287"))){
					listaSolicitud = consultaDomiciliacionWebService.consultarListConciliacion(solicitudFilter);
				}//758(NO IDENTIFICADO), 1004(REINTEGRO)
//				else if(selOrigenApo.equals(new Short("758")) || selOrigenApo.equals(new Short("1004"))){
//					listaCuentasSolicitud = consultaDomiciliacionWebService.consultarCuentasBancos(solicitudFilter);
//				}//292(SPEI)
				else if(selOrigenApo.equals(new Short("292"))){
					listaSolicitud = consultaDomiciliacionWebService.consultarListSpei(solicitudFilter);
				}else{
					listaSolicitud = consultaDomiciliacionWebService.consultarListTodos(solicitudFilter);
				}
			}
			
//			listaSolicitud = consultaDomiciliacionWebService.consultarCorta(solicitudFilter);
			setTablaConsulta(true);
			setMuestraCuentas(false);
		}
	}

	public void buscarDet() throws MitBusinessException,ParseException{
		SolicitudFilterBean solicitudFilter = new SolicitudFilterBean();
		if(selectedSolicitud!=null){
			setHabilitaBtnPrincipal(false);
			limpiarBusqDet();
			
			setTxtFiltroDet(clientePorTipo);
			setTxtFiltroValorDet(clienteBusqueda);
			setTxtOrigenDet(selectedSolicitud.getOrigenSolicitud().getId());
			
			solicitudFilter.setClaveSolicitud(selectedSolicitud.getClaveSolicitud());
		}
		
		if(txtEstatusDet!=null && txtEstatusDet>0){
			GenericCatalogoBean estatusSolicitud = new GenericCatalogoBean();
			estatusSolicitud.setId(selEstatus);
			solicitudFilter.setEstatusSolicitud(estatusSolicitud);
		}			
		
		if(fechaInicio!=null){
			SimpleDateFormat sdfInicio = new SimpleDateFormat("dd/MM/yy hh:mm:ss.FF");
			String strDateInicio = sdfInicio.format(fechaInicio);
			Date dtInicio = sdfInicio.parse(strDateInicio);
			solicitudFilter.setFechaInicio(dtInicio);
		}
			
		if(fechaFin!=null){
			String strDateFin;
			SimpleDateFormat sdfFin = new SimpleDateFormat("dd/MM/yy hh:mm:ss.FF");
			if(fechaFin != null){
				strDateFin = sdfFin.format(fechaFin);
			}else{
				Date fechaActual = new Date();
				strDateFin = sdfFin.format(fechaActual);
			}
				Date dtFin = sdfFin.parse(strDateFin);
			solicitudFilter.setFechaFinal(dtFin);
		}
			
		listaSolicitudHis = consultaDomiciliacionWebService.consultarHistSolicitud(solicitudFilter,selectedSolicitud.getEstatusSolicitud().getId());
		
	}
	
	public void detallar() throws MitBusinessException{
		SolicitudFilterBean solicitudFilter = new SolicitudFilterBean();
		solicitudFilter.setClaveSolicitud(selectedSolicitud.getClaveSolicitud());
		listaSolicitudHis = consultaDomiciliacionWebService.consultarHistSolicitud(solicitudFilter,selectedSolicitud.getEstatusSolicitud().getId());
	}
	
	public List<GenericCatalogoBean> getOrigenAportacionLista() throws MitBusinessException {
		if(origenAportacionLista == null){
			origenAportacionLista = new ArrayList<GenericCatalogoBean>();
			origenAportacionLista = consultaDomiciliacionWebService.consultarOrigenAportacion();
		}
		return origenAportacionLista;
	}
	
	public void setOrigenAportacionLista(List<GenericCatalogoBean> origenAportacionLista) {
		this.origenAportacionLista = origenAportacionLista;
	}
	
	public List<GenericCatalogoBean> getEstatusLista() throws MitBusinessException {
		if(estatusLista == null){
			estatusLista = new ArrayList<GenericCatalogoBean>();
			estatusLista = consultaDomiciliacionWebService.consultarEstatus();
		}
		return estatusLista;
	}
	
	public void setEstatusLista(List<GenericCatalogoBean> estatusLista) {
		this.estatusLista = estatusLista;
	}
	
	public String getClienteBusqueda() {
		return clienteBusqueda;
	}
	
	public void setClienteBusqueda(String clienteBusqueda) {
		this.clienteBusqueda = clienteBusqueda;
	}
	
	public IConsultaDomiciliacionWebService getConsultaDomiciliacionWebService() {
		return consultaDomiciliacionWebService;
	}
	
	public void setConsultaDomiciliacionWebService(IConsultaDomiciliacionWebService consultaDomiciliacionWebService) {
		this.consultaDomiciliacionWebService = consultaDomiciliacionWebService;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public Date getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public List<SolicitudBean> getListaSolicitud() {
		return listaSolicitud;
	}
	
	public void setListaSolicitud(List<SolicitudBean> listaSolicitud) {
		this.listaSolicitud = listaSolicitud;
	}
	
	public SolicitudBean getSelectedSolicitud() {
		return selectedSolicitud;
	}
	
	public void setSelectedSolicitud(SolicitudBean selectedSolicitud) throws MitBusinessException,ParseException{
		SolicitudFilterBean solicitudFilter = new SolicitudFilterBean();
		if(selectedSolicitud!=null){
			setDetalleSpei(null);
			setListaSolicitudHis(null);
			setListaSolicitudHisNoDomi(null);
			setHabilitaBtnPrincipal(false);
			limpiarBusqDet();
			
			setTxtFiltroDet(clientePorTipo);
			setTxtFiltroValorDet(clienteBusqueda);
			setTxtOrigenDet(selectedSolicitud.getOrigenSolicitud().getId());
			
			solicitudFilter.setFolio(selectedSolicitud.getFolio());
			solicitudFilter.setClaveSolicitud(selectedSolicitud.getClaveSolicitud());
			solicitudFilter.setNumCuentaIndividual(selectedSolicitud.getNumCuentaIndividual());
			solicitudFilter.setOrigenSolicitud(selectedSolicitud.getOrigenSolicitud());
			//Unicamente las Domiciliaciones Tienen cargo
			
			listaSolicitudHis = consultaDomiciliacionWebService.consultarHistSolicitud(solicitudFilter,selectedSolicitud.getEstatusSolicitud().getId());
			
//			if(listaSolicitudHis==null || listaSolicitudHis.size()==0){
//				listaSolicitudHisNoDomi = consultaDomiciliacionWebService.consultarDetalleSolNoDomi(solicitudFilter);
//			}
			
			if(selectedSolicitud.getOrigenSolicitud().getId().equals(new Short("288"))||selectedSolicitud.getOrigenSolicitud().getId().equals(new Short("289"))||
			   selectedSolicitud.getOrigenSolicitud().getId().equals(new Short("1115"))||selectedSolicitud.getOrigenSolicitud().getId().equals(new Short("290"))||
			   selectedSolicitud.getOrigenSolicitud().getId().equals(new Short("292"))||selectedSolicitud.getOrigenSolicitud().getId().equals(new Short("287"))){

				solicitudFilter.setIdConciliacion(selectedSolicitud.getIdConciliacion());
				listaDiversificacion = consultaDomiciliacionWebService.consultaDiversificacionCon(solicitudFilter);				
			}else{
				listaDiversificacion = consultaDomiciliacionWebService.consultaDiversificacionSol(solicitudFilter);
			}

			listaValidacion = consultaDomiciliacionWebService.consultaValidaciones(solicitudFilter);

			setTxtOrigenDetLetra(selectedSolicitud.getOrigenSolicitud().getValor());
			
			
			if(selectedSolicitud.getOrigenSolicitud().getId().equals(new Short("292"))){
				DetalleSpeiApoVol detSpei = consultaDomiciliacionWebService.obtenerDetalleSpei(selectedSolicitud.getFolio());
				detSpei.setEmpresa("");
				detSpei.setNoEmpleado("");
				detSpei.setNoEmpleado("");
				if(selectedSolicitud.getCuentaBanco() != null){
					if(selectedSolicitud.getCuentaBanco().length()==18){
						detSpei.setTipoCuenta("CLABE");
					}else if (selectedSolicitud.getCuentaBanco().length()==16){
						detSpei.setTipoCuenta("TD");
					}else{
						detSpei.setTipoCuenta("TELEFONO");
					}
				}
				setDetalleSpei(detSpei);
			}else if (selectedSolicitud.getOrigenSolicitud().getId().equals(new Short("289"))){
				DetalleSpeiApoVol detSpei = consultaDomiciliacionWebService.obtenerDetalleNomina(selectedSolicitud.getFolio(),selectedSolicitud.getNumCuentaIndividual());
				setDetalleSpei(detSpei);
			}
		}
		this.selectedSolicitud = selectedSolicitud;
	}
	
	public List<SolicitudBean> getListaSolicitudHis() {
		if(listaSolicitudHis ==null){
			listaSolicitudHis = new ArrayList<SolicitudBean>();
		}
		return listaSolicitudHis;
	}
	
	public void setListaSolicitudHis(List<SolicitudBean> listaSolicitudHis) {
		this.listaSolicitudHis = listaSolicitudHis;
	}
	
	public String getClientePorTipo() {
		return clientePorTipo;
	}
	
	public void setClientePorTipo(String clientePorTipo) {
		this.clientePorTipo = clientePorTipo;
	}
	
	public Short getSelOrigenApo() {
		return selOrigenApo;
	}
	
	public void setSelOrigenApo(Short selOrigenApo) {
		this.selOrigenApo = selOrigenApo;
	}
	
	public Short getSelEstatus() {
		return selEstatus;
	}
	
	public void setSelEstatus(Short selEstatus) {
		this.selEstatus = selEstatus;
	}
	
	public boolean isTablaConsulta() {
		return tablaConsulta;
	}
	
	public void setTablaConsulta(boolean tablaConsulta) {
		this.tablaConsulta = tablaConsulta;
	}
	
	public boolean isSelectTipoBusq() {
		return selectTipoBusq;
	}
	
	public void setSelectTipoBusq(boolean selectTipoBusq) {
		this.selectTipoBusq = selectTipoBusq;
	}
	
	public boolean isHabilitaBtnPrincipal() {
		return habilitaBtnPrincipal;
	}
	
	public void setHabilitaBtnPrincipal(boolean habilitaBtnPrincipal) {
		this.habilitaBtnPrincipal = habilitaBtnPrincipal;
	}

	public void generaImpresionPrincipal(){
		try {
			ReporteVO reporte = consultaDomiciliacionWebService.generaReporte(listaSolicitud);
			responseReport(reporte.getArchivo(),reporte.getNombre(),XLS_MIME_TYPE);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
	}
	
	public void generaImpresionDetalle(){
		try {
			ReporteVO reporte = consultaDomiciliacionWebService.generaReporteDetallado(listaDiversificacion,listaValidacion,listaSolicitudHis);
			responseReport(reporte.getArchivo(),reporte.getNombre(),XLS_MIME_TYPE);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
	}
	
	public String getTxtFiltroDet() {
		return txtFiltroDet;
	}
	
	public void setTxtFiltroDet(String txtFiltroDet) {
		this.txtFiltroDet = txtFiltroDet;
	}
	
	public String getTxtFiltroValorDet() {
		return txtFiltroValorDet;
	}
	
	public void setTxtFiltroValorDet(String txtFiltroValorDet) {
		this.txtFiltroValorDet = txtFiltroValorDet;
	}
	
	public Short getTxtOrigenDet() {
		return txtOrigenDet;
	}
	
	public void setTxtOrigenDet(Short txtOrigenDet) {
		this.txtOrigenDet = txtOrigenDet;
	}
	
	public Short getTxtEstatusDet() {
		return txtEstatusDet;
	}
	
	public void setTxtEstatusDet(Short txtEstatusDet) {
		this.txtEstatusDet = txtEstatusDet;
	}
	
	public Date getFechaInicioDet() {
		return fechaInicioDet;
	}
	
	public void setFechaInicioDet(Date fechaInicioDet) {
		this.fechaInicioDet = fechaInicioDet;
	}
	
	public Date getFechaFinDet() {
		return fechaFinDet;
	}
	
	public void setFechaFinDet(Date fechaFinDet) {
		this.fechaFinDet = fechaFinDet;
	}
	
	public List<SolicitudBean> getListaCuentasSolicitud() {
		return listaCuentasSolicitud;
	}
	
	public void setListaCuentasSolicitud(List<SolicitudBean> listaCuentasSolicitud) {
		this.listaCuentasSolicitud = listaCuentasSolicitud;
	}
	
	public boolean isMuestraCuentas() {
		return muestraCuentas;
	}
	
	public void setMuestraCuentas(boolean muestraCuentas) {
		this.muestraCuentas = muestraCuentas;
	}
	
	public SolicitudBean getSelectedCuentaSolicitud() {
		return selectedCuentaSolicitud;
	}
	
	public void setSelectedCuentaSolicitud(SolicitudBean selectedCuentaSolicitud) {
		if(selectedCuentaSolicitud!=null){
			setCuentaSeleccionada(true);
		}
		this.selectedCuentaSolicitud = selectedCuentaSolicitud;
	}
	
	public boolean isCuentaSeleccionada() {
		return cuentaSeleccionada;
	}
	
	public void setCuentaSeleccionada(boolean cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
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
	 
	public List<SolicitudBean> getListaSolicitudHisNoDomi() {
		return listaSolicitudHisNoDomi;
	}
	
	public void setListaSolicitudHisNoDomi(List<SolicitudBean> listaSolicitudHisNoDomi) {
		this.listaSolicitudHisNoDomi = listaSolicitudHisNoDomi;
	}
	
	public List<DiversificacionBean> getListaDiversificacion() {
		if(listaDiversificacion==null){
			listaDiversificacion = new ArrayList<DiversificacionBean>();
		}
		return listaDiversificacion;
	}
	
	public void setListaDiversificacion(List<DiversificacionBean> listaDiversificacion) {
		this.listaDiversificacion = listaDiversificacion;
	}
	
	public List<ValidacionesBean> getListaValidacion() {
		if(listaValidacion==null){
			listaValidacion = new ArrayList<ValidacionesBean>();
		}
		return listaValidacion;
	}
	
	public void setListaValidacion(List<ValidacionesBean> listaValidacion) {
		this.listaValidacion = listaValidacion;
	}

	public DetalleSpeiApoVol getDetalleSpei() {
		return detalleSpei;
	}

	public void setDetalleSpei(DetalleSpeiApoVol detalleSpei) {
		this.detalleSpei = detalleSpei;
	}

	public String getTxtOrigenDetLetra() {
//		String variable="";
//		if(txtOrigenDet==null || txtOrigenDet.equals(new Short("0"))){
//			variable="";
//		}else{
//			if(origenAportacionLista!=null && !origenAportacionLista.isEmpty()){
//				List<GenericCatalogoBean> origenAportacionLista;
//				for(int i=0;i<this.origenAportacionLista.size();i++){
//					if(txtOrigenDet.equals(this.origenAportacionLista.get(i).getId())){
//						variable= this.origenAportacionLista.get(i).getValor();
//						break;
//					}
//				}
//			}else{
//				variable="";
//			}
//		}
//		
//		return variable;
		return txtOrigenDetLetra;
	}
	
	public void setTxtOrigenDetLetra(String txtOrigenDetLetra) {
		this.txtOrigenDetLetra = txtOrigenDetLetra;
	}
	
	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
}