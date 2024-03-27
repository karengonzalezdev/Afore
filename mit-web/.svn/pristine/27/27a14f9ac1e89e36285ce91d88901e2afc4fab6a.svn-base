package mx.profuturo.nci.web.views.traspasos;

import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ESTATUS_SOLICITUD_CANCELADO;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ESTATUS_TRASPASO_ACEPTADO;
import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ESTATUS_TRASPASO_CANCELADO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.util.Constantes;
import mx.profuturo.nci.business.vo.RolVO;
import mx.profuturo.nci.web.beans.CuentaIndividualBean;
import mx.profuturo.nci.web.beans.GenericCatalogoBean;
import mx.profuturo.nci.web.beans.traspasos.MonitoreoSolicitudesDetalleBean;
import mx.profuturo.nci.web.service.IArchivoDomiciliacionWebService;
import mx.profuturo.nci.web.service.ICatalogoWebService;
import mx.profuturo.nci.web.service.IRolesWebService;


@ManagedBean(name = "monitoreoSolicitudesView")
@ViewScoped
public class MonitoreoSolicitudesView implements Serializable{
	private static final Logger LOGGER = LoggerFactory.getLogger(MonitoreoSolicitudesView.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{catalogoWebService}")
	ICatalogoWebService catalogoWebService;
	
	@ManagedProperty(value="#{archivoDomiciliacionService}")
	IArchivoDomiciliacionWebService archivoDomiciliacionWebService;
	
	@ManagedProperty(value="#{rolesWebService}")
	IRolesWebService rolesWebService;
	
	private String tipoBusqueda;
	private String txtBusquedaCuenta;
	private String txtBusquedaNss;
	private String txtBusquedaCurp;
	private boolean searchCuenta;
	private boolean searchNss;
	private boolean searchCurp;
	private boolean searchBtnVisible;
	
	private boolean muestraTblBusqueda;
	private List<MonitoreoSolicitudesDetalleBean> lstSolicitudes;
//	private List<MonitoreoSolicitudesDetalleBean> lstSolicitudesResumen;
	private List<MonitoreoSolicitudesDetalleBean> lstSolicitudesHist;
	private MonitoreoSolicitudesDetalleBean selectedSol;
	private MonitoreoSolicitudesDetalleBean selectedEstatusSol;
	private boolean muestraBoton;

	private boolean editPeriodiUni;
	private boolean habilitaEdicion;
	private boolean habilitaBtnEdit;
	private boolean muestraTablaDos;
	
	private List<SelectItem> lstPeriodicidad;
	private List<SelectItem> listFrqInic;
	private List<SelectItem> listFrqFin;
	private List<SelectItem> listDomis;
	private Short origenBusqueda;

	private String curpDos;
	
	private String resPeriodicidadDesc;
	private Short resPeriodicidad;
	private Short resFrecuenciaInicial;
	private Short resFrecuenciaFinal;
	
	public void test(){
		 LOGGER.info("TEST");
	}
	
	@PostConstruct
	public void init() {
		setSelectedSol(null);
		setHabilitaBtnEdit(false);
		setMuestraTablaDos(false);
		selectedSol = new MonitoreoSolicitudesDetalleBean();
		setEditPeriodiUni(false);
//		lstSolicitudesResumen = new ArrayList<MonitoreoSolicitudesDetalleBean>();
		lstSolicitudesHist = new ArrayList<MonitoreoSolicitudesDetalleBean>();
		cargaOrigenes();
	}
	
	public void cargaOrigenes(){
		List<SelectItem> listDomis = new ArrayList<SelectItem>();
		boolean isRegistroTrapado = false;
		try {
			List<RolVO> lstRoles = rolesWebService.obtenerRoles(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
			for(RolVO roli:lstRoles){
				if(roli.getDescRol().contains("REGISTRO Y TRASPASO") || roli.getDescRol().contains("Registro y Traspaso") ||
				   roli.getDescRol().contains("registro y traspaso")){
					isRegistroTrapado = true;
					break;
				}
			}
			
			
			List<Short> lstIdsCatalogos = new ArrayList<Short>();
			if(!isRegistroTrapado){
				lstIdsCatalogos.add(Constantes.ORIGENES_DOMICILIACION);
			}
			lstIdsCatalogos.add(Constantes.ORIGENES_DOMICILIACION_TRASPASOS);
			
			listDomis = catalogoWebService.consultaCatalogo(lstIdsCatalogos);
		} catch (MitBusinessException e) {
			LOGGER.error("ERROR UBICACION",e);
		}
		
		setListDomis(listDomis);
	}
	
	public void limpiarBusqueda(){
		setTipoBusqueda("");
		setTxtBusquedaCuenta("");
		setTxtBusquedaNss("");
		setTxtBusquedaCurp("");
		setMuestraTblBusqueda(false);
		setMuestraBoton(false);
		setSearchBtnVisible(false);
		setHabilitaBtnEdit(false);
		setEditPeriodiUni(false);
		lstSolicitudesHist.clear();
		setLstSolicitudes(null);
//		lstSolicitudesResumen.clear();
	}
	
	public void busquedaFiltro(){
//		if(!lstSolicitudesResumen.isEmpty())
//			lstSolicitudesResumen.clear();
//		if(!lstSolicitudesHist.isEmpty())
//			lstSolicitudesHist.clear();
		if(tipoBusqueda!=null && !tipoBusqueda.isEmpty() && ((txtBusquedaCuenta!=null && !txtBusquedaCuenta.isEmpty())||(txtBusquedaNss!=null && !txtBusquedaNss.isEmpty())||(txtBusquedaCurp!=null && !txtBusquedaCurp.isEmpty()) ))
		{
			try{
				List<Short> lstOrigen = new ArrayList<Short>();
				if(origenBusqueda==Constantes.ORIGENES_DOMICILIACION_TRASPASOS){
					lstOrigen.add(Constantes.ORIGENES_DOMICILIACION_TRASPASOS);
					List<MonitoreoSolicitudesDetalleBean> lista = archivoDomiciliacionWebService.obtenerMonitoreoSolPorValor(txtBusquedaCuenta, txtBusquedaNss, txtBusquedaCurp, lstOrigen);
						if(lista == null || lista.isEmpty() || lista.size() < 1){
							  RequestContext.getCurrentInstance().execute("PF('sinResultadoTablaDialog').show()");
							  setLstSolicitudes(null);
						  }
						else{
							setLstSolicitudes(lista);				
						}
				}else{
					lstOrigen.add(Constantes.ORIGENES_DOMICILIACION);
					lstOrigen.add(Constantes.ORIGENES_DOMICILIACION_ESAR);
					lstOrigen.add(Constantes.ORIGENES_DOMICILIACION_CHATBOT);
					List<MonitoreoSolicitudesDetalleBean> lista = archivoDomiciliacionWebService.obtenerMonitoreoSolPorValor(txtBusquedaCuenta, txtBusquedaNss, txtBusquedaCurp, lstOrigen);
					if(lista == null || lista.isEmpty() || lista.size() < 1){
						  RequestContext.getCurrentInstance().execute("PF('sinResultadoTablaDialog').show()");
					  }
					else{
						setLstSolicitudes(lista);				
					}
				}
//				int i=0;
//				for(MonitoreoSolicitudesDetalleBean moniSol : getLstSolicitudes()){
//					if(i==0){
//						lstSolicitudesResumen.add(moniSol);
//					}
//					if(i>0){
//						String cuenta = getLstSolicitudes().get(i-1).getCuentaIndividual();
//						Short periodicidad = getLstSolicitudes().get(i-1).getPeriodicidad();
//						if(!moniSol.getCuentaIndividual().equals(cuenta) || !moniSol.getPeriodicidad().equals(periodicidad)){
//							lstSolicitudesResumen.add(moniSol);
//						}
//					}
//					i++;
//				}
			} catch (	MitBusinessException e) {
				LOGGER.error("Error al obtener el monitoreo de cargos domiciliacion traspasos",e);
			}	
			
			setSelectedSol(null);
			setHabilitaBtnEdit(false);
			setMuestraTblBusqueda(true);
			setHabilitaEdicion(false);
			setEditPeriodiUni(false);
		}else{
			setMuestraTblBusqueda(false);
		}
	}
	
	public void muestraDetalleSol(){
		 LOGGER.info("DETALLESOL");
		setMuestraTblBusqueda(false);
		setEditPeriodiUni(false);
	}
	
	public String getTipoBusqueda() {
		return tipoBusqueda;
	}
	
	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}
	
	public boolean isMuestraTblBusqueda() {
		return muestraTblBusqueda;
	}
	
	public void setMuestraTblBusqueda(boolean muestraTblBusqueda) {
		this.muestraTblBusqueda = muestraTblBusqueda;
	}
	
	public String getTxtBusquedaCuenta() {
		return txtBusquedaCuenta;
	}
	
	public void setTxtBusquedaCuenta(String txtBusquedaCuenta) {
		this.txtBusquedaCuenta = txtBusquedaCuenta;
	}
	
	public String getTxtBusquedaNss() {
		return txtBusquedaNss;
	}
	
	public void setTxtBusquedaNss(String txtBusquedaNss) {
		this.txtBusquedaNss = txtBusquedaNss;
	}
	
	public String getTxtBusquedaCurp() {
		return txtBusquedaCurp;
	}
	
	public void setTxtBusquedaCurp(String txtBusquedaCurp) {
		this.txtBusquedaCurp = txtBusquedaCurp;
	}
	
	public List<MonitoreoSolicitudesDetalleBean> getLstSolicitudes() {
		return lstSolicitudes;
	}
	
	public void setLstSolicitudes(List<MonitoreoSolicitudesDetalleBean> lstSolicitudes) {
		this.lstSolicitudes = lstSolicitudes;
	}
	
	public MonitoreoSolicitudesDetalleBean getSelectedSol() {
		return selectedSol;
	}
	
	public void setSelectedSol() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();

		String sol= ((String) map.get("claveSol"));
		if(lstSolicitudes!=null){
			for(MonitoreoSolicitudesDetalleBean bean : lstSolicitudes){
				if(bean.getClaveSolicitud().equals(sol)){
					setSelectedSol(bean);
					break;
				}
			}
		}
		
		if(selectedSol.getPeriodicidadDesc()!=null)
			resPeriodicidadDesc = selectedSol.getPeriodicidadDesc();
		if(selectedSol.getPeriodicidad()!=null)
			resPeriodicidad = selectedSol.getPeriodicidad();
		if(selectedSol.getFrecuenciaInicial()!=null)
			resFrecuenciaInicial = selectedSol.getFrecuenciaInicial();
		if(selectedSol.getFrecuenciaFinal()!=null)
			resFrecuenciaFinal = selectedSol.getFrecuenciaFinal();
		
		List<MonitoreoSolicitudesDetalleBean> historico = archivoDomiciliacionWebService.historicocargoSol(getSelectedSol());
		
//		historico.add(selectedSol);
		
		Collections.sort(historico, new Comparator<MonitoreoSolicitudesDetalleBean>() {
	        public int compare(MonitoreoSolicitudesDetalleBean beans2, MonitoreoSolicitudesDetalleBean beans1)
	        {
	        	if(beans1.getFechaCargo()!= null && beans2.getFechaCargo() != null){
	        		return  beans1.getFechaCargo().compareTo(beans2.getFechaCargo());
	        	}else{
	        		return 0;
	        	}
	        }
	    });
		
		setLstSolicitudesHist(historico);
		
//		if(!lstSolicitudesHist.isEmpty())
//			lstSolicitudesHist.clear();
//		int i=0;
//		for(MonitoreoSolicitudesDetalleBean moniSol : getLstSolicitudes()){
//			String cuenta = selectedSol.getCuentaIndividual();
//			Short periodicidad = selectedSol.getPeriodicidad();
//			if(moniSol.getCuentaIndividual().equals(cuenta) && moniSol.getPeriodicidad().equals(periodicidad)){
//				lstSolicitudesHist.add(moniSol);
//			}
//			i++;
//		}

	}
	
	public void setSelectedSol(MonitoreoSolicitudesDetalleBean selectedSol) {
		if(selectedSol!=null){
			try{	
				CuentaIndividualBean reg = archivoDomiciliacionWebService.validarCuentaInd(new Long(selectedSol.getCuentaIndividual()));
				selectedSol.setCuentaBuc(reg.getCuentaBuc());
				selectedSol.setNombreBuc(reg.getNombreCliente());
				if(reg.getIsVigente()){
					selectedSol.setVigenteBuc("VIGENTE");
				}else{
					selectedSol.setVigenteBuc("NO VIGENTE");
				}
			} catch (MitBusinessException e) {
				LOGGER.error("Error al obtener el monitoreo de cargos domiciliacion traspasos",e);
				selectedSol.setCuentaBuc("NO ENCONTRADA");
			}
			setHabilitaEdicion(false);
			setMuestraTablaDos(true);
			setEditPeriodiUni(false);
			
			setEditPeriodiUni(false);

			try{
				lstPeriodicidad = new ArrayList<SelectItem>();
				List<GenericCatalogoBean> period = archivoDomiciliacionWebService.obtenerPeriodicidad();
				for(int i=0;i<period.size();i++){
					lstPeriodicidad.add(new SelectItem(period.get(i).getId().toString(),period.get(i).getValor()));
				}
			} catch (MitBusinessException e) {
				LOGGER.error("Error al obtener el listado de periodicicades",e);
			}
			if(selectedSol.getPeriodicidad().equals(new Short("494")) || selectedSol.getPeriodicidad().equals(new Short("493"))
			   || selectedSol.getPeriodicidad().equals(new Short("492"))){
				listFrqInic = new ArrayList<SelectItem>(); 
				listFrqFin = new ArrayList<SelectItem>();
				if(selectedSol.getPeriodicidad().equals(new Short("494"))){
					try{
						List<GenericCatalogoBean> period = archivoDomiciliacionWebService.obtenerTiposFrecMensual();
						for(int i=0;i<period.size();i++){
							listFrqInic.add(new SelectItem(period.get(i).getId().toString(),period.get(i).getValor()));
						}
					} catch (MitBusinessException e) {
						LOGGER.error("Error al obtener el listado de frecuencias MENSUAL",e);
					}
				}else if(selectedSol.getPeriodicidad().equals(new Short("493"))){
					try{
						if(habilitaEdicion) {
							List<GenericCatalogoBean> frecIni = archivoDomiciliacionWebService.obtenerTiposFrecQuincenalIni();
							for(int i=0;i<frecIni.size();i++){
								listFrqInic.add(new SelectItem(frecIni.get(i).getId().toString(),frecIni.get(i).getValor()));
							}
							
							if(selectedSol.getFrecuenciaInicial()!=null){
								List<GenericCatalogoBean> frecFin = archivoDomiciliacionWebService.obtenerTiposFrecQuincenalFin(selectedSol.getFrecuenciaInicial().intValue());
								for(int i=0;i<frecFin.size();i++){
									listFrqFin.add(new SelectItem(frecFin.get(i).getId().toString(),frecFin.get(i).getValor()));
								}
							}
						}else {
							listFrqInic.add(new SelectItem(selectedSol.getFrecuenciaInicial()));
							listFrqFin.add(new SelectItem(selectedSol.getFrecuenciaFinal()));
						}
					} catch (MitBusinessException e) {
						LOGGER.error("Error al obtener el listado de frecuencias QUINCENAL",e);
					}
				}else if(selectedSol.getPeriodicidad().equals(new Short("492"))){
					try{
						List<GenericCatalogoBean> period = archivoDomiciliacionWebService.obtenerTiposFrecSemanal();
						for(int i=0;i<period.size();i++){
							listFrqInic.add(new SelectItem(period.get(i).getId().toString(),period.get(i).getValor()));
						}
					} catch (MitBusinessException e) {
						LOGGER.error("Error al obtener el listado de frecuencias SEMANAL",e);
					}
				}
			}
		}
		this.selectedSol = selectedSol;
		setHabilitaBtnEdit(true);
	}
	
	public IArchivoDomiciliacionWebService getArchivoDomiciliacionWebService() {
		return archivoDomiciliacionWebService;
	}
	
	public void setArchivoDomiciliacionWebService(IArchivoDomiciliacionWebService archivoDomiciliacionWebService) {
		this.archivoDomiciliacionWebService = archivoDomiciliacionWebService;
	}
	
	public ICatalogoWebService getCatalogoWebService() {
		return catalogoWebService;
	}

	public void setCatalogoWebService(ICatalogoWebService catalogoWebService) {
		this.catalogoWebService = catalogoWebService;
	}
	
	public void seleccionMenu(final AjaxBehaviorEvent event){
		setTxtBusquedaCuenta("");
		setTxtBusquedaNss("");
		setTxtBusquedaCurp("");
				
		setMuestraTblBusqueda(false);
		setSearchCuenta(false);
		setSearchNss(false);
		setSearchCurp(false);
		setSearchBtnVisible(false);
		setHabilitaEdicion(false);
		setEditPeriodiUni(false);
		
		if(tipoBusqueda!=null && !tipoBusqueda.isEmpty()){
			setMuestraBoton(true);
			setSearchBtnVisible(true);
			if(tipoBusqueda.equals("Cuenta individual")){
				setSearchCuenta(true);
			}else if(tipoBusqueda.equals("NSS")){
				setSearchNss(true);
			}else if (tipoBusqueda.equals("CURP")){
				setSearchCurp(true);
			}
		}else{
			setSearchBtnVisible(false);
		}	
	}
	
	public boolean isMuestraBoton() {
		return muestraBoton;
	}
	
	public void setMuestraBoton(boolean muestraBoton) {
		this.muestraBoton = muestraBoton;
	}
	
	public boolean isSearchCuenta() {
		return searchCuenta;
	}
	
	public void setSearchCuenta(boolean searchCuenta) {
		this.searchCuenta = searchCuenta;
	}
	
	public boolean isSearchNss() {
		return searchNss;
	}
	
	public void setSearchNss(boolean searchNss) {
		this.searchNss = searchNss;
	}
	
	public boolean isSearchCurp() {
		return searchCurp;
	}
	
	public void setSearchCurp(boolean searchCurp) {
		this.searchCurp = searchCurp;
	}
	
	public boolean isHabilitaEdicion() {
		return habilitaEdicion;
	}
	
	public void setHabilitaEdicion(boolean habilitaEdicion) {
		this.habilitaEdicion = habilitaEdicion;
	}
	
	public void habilitaEdicion(){
		setHabilitaEdicion(true);
		
		if(!selectedSol.getPeriodicidad().equals(new Short("495"))){
			setEditPeriodiUni(true);
			
			for(int i=0;i<lstPeriodicidad.size();i++){
				SelectItem item = lstPeriodicidad.get(i);
				if(item.getValue().toString().equals("495")){
					lstPeriodicidad.remove(i);
					break;
				}		
			}
			
			if(selectedSol.getPeriodicidad().equals(new Short("494")) || selectedSol.getPeriodicidad().equals(new Short("493")) 
				|| selectedSol.getPeriodicidad().equals(new Short("492")) ){
				for(int i=0;i<lstPeriodicidad.size();i++){
					SelectItem item = lstPeriodicidad.get(i);
					if(item.getValue().toString().equals("0")){
						lstPeriodicidad.remove(i);
						break;
					}		
				}
			}
		}
		cambioPeriodicidad();
	}
	
	public void actualizarRegistros() throws MitBusinessException{
		BigDecimal impTot = selectedSol.getCpDosMeses().add(selectedSol.getLpDoceMeses().add(selectedSol.getAvpilp()).add(selectedSol.getAcr()));
		
		if(impTot.compareTo(BigDecimal.ZERO) == 0){
			RequestContext.getCurrentInstance().execute("PF('resResultadoErrorTablaDialog').show()");
			return;
		}
		
		
		Date fechaProxCargo = archivoDomiciliacionWebService.actualizaSolicitud(selectedSol.getClaveSolicitud(),selectedSol.getPeriodicidad(), selectedSol.getFrecuenciaInicial(),
				selectedSol.getFrecuenciaFinal(), impTot,FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
//		selectedSol.setEstatusSol("MODIFICADA");
		selectedSol.setFechaProxCargo(fechaProxCargo);
		archivoDomiciliacionWebService.actualizaDiversificacion(selectedSol.getClaveSolicitud(),selectedSol.getCpDosMeses(),selectedSol.getLpDoceMeses(),
				selectedSol.getAvpilp(),selectedSol.getAcr(),selectedSol.getTotalDiver(),FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
		
		setHabilitaEdicion(false);
		setEditPeriodiUni(false);
		
		RequestContext.getCurrentInstance().execute("PF('resResultadoTablaDialog').show()");
	}
	
	public void actualizarSolicitud(){
		try{
			int reg = archivoDomiciliacionWebService.actualizaDomiTraspaso(selectedSol);
		} catch (MitBusinessException e) {
			LOGGER.error("Error al obtener el monitoreo de cargos domiciliacion traspasos",e);
		}	
		busquedaFiltro();
		
		setHabilitaBtnEdit(true);
		setMuestraTblBusqueda(true);
		
		setMuestraTablaDos(false);
	}
	
//	public void enableButtons() {
////		RequestContext.getCurrentInstance().update("formMonitoreoSolicitudes:btnReactivarSol");
////		RequestContext.getCurrentInstance().update("formMonitoreoSolicitudes:btnCancelarSol");
////		RequestContext.getCurrentInstance().update("formMonitoreoSolicitudes:test");
//		habilitaCancelacion = !"CANCELADO".equals((selectedEstatusSol!=null?selectedEstatusSol.getEstatusSol():null));
//		LOGGER.error(":::::::::"+selectedEstatusSol.getEstatusSol()+"::"+habilitaCancelacion);
//	}
	
	public void cancelarSolicitud(){
		try{
			int reg = archivoDomiciliacionWebService.actualizaEstatusSolicitud(
					selectedEstatusSol,
					ID_ESTATUS_SOLICITUD_CANCELADO,
					FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
			selectedEstatusSol.setEstatusSol("CANCELADO");
		} catch (MitBusinessException e) {
			LOGGER.error("Error al cancelar la solicitud",e);
		}	
		busquedaFiltro();
		
		setHabilitaBtnEdit(false);
		setMuestraTblBusqueda(true);
		
		setMuestraTablaDos(false);
	}
	
	public void cancelarSolicitudTaaspaso(){
		try{
			int reg = archivoDomiciliacionWebService.actualizaEstatusSolicitudTraspaso(
					selectedEstatusSol.getClaveSolicitud(),
					selectedEstatusSol.getCuentaIndividual(),
					ID_ESTATUS_TRASPASO_CANCELADO,
					FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
			selectedEstatusSol.setEstatusSol("CANCELADO");
		} catch (MitBusinessException e) {
			LOGGER.error("Error al cancelar la solicitud",e);
		}	
		busquedaFiltro();
		
		setHabilitaBtnEdit(false);
		setMuestraTblBusqueda(true);
		
		setMuestraTablaDos(false);
	}
	
	@Deprecated
	public void activarSolicitud(){
		try{
			int reg = archivoDomiciliacionWebService.actualizaEstatusSolicitud(
					selectedEstatusSol,
					ID_ESTATUS_TRASPASO_ACEPTADO,
					FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
			selectedEstatusSol.setEstatusSol("ACEPTADO");
		} catch (MitBusinessException e) {
			LOGGER.error("Error al activar la solicitud",e);
		}	
		busquedaFiltro();
		
		setHabilitaBtnEdit(false);
		setMuestraTblBusqueda(true);
		
		setMuestraTablaDos(false);
	}
	
	public void cancelarEdicion(){	
		setHabilitaEdicion(false);
		setEditPeriodiUni(false);
		if(resPeriodicidad!=null)
			selectedSol.setPeriodicidad(resPeriodicidad);
		if(resPeriodicidadDesc!=null)
			selectedSol.setPeriodicidadDesc(resPeriodicidadDesc);
		if(resFrecuenciaInicial!=null)
			selectedSol.setFrecuenciaInicial(resFrecuenciaInicial);
		if(resFrecuenciaFinal!=null)
			selectedSol.setFrecuenciaFinal(resFrecuenciaFinal);
		cambioPeriodicidad();
		
		
		List<Short> lstOrigen = new ArrayList<Short>();
		lstOrigen.add(selectedSol.getIdOrigenSolicitud());
		
		try {
			List<MonitoreoSolicitudesDetalleBean> lista = archivoDomiciliacionWebService.obtenerMonitoreoSolPorValor(selectedSol.getCuentaIndividual(), selectedSol.getNss(), selectedSol.getCurp(), lstOrigen);
			for(MonitoreoSolicitudesDetalleBean lstSol : lista){
				if(lstSol.getClaveSolicitud().equals(selectedSol.getClaveSolicitud())){
					setSelectedSol(lstSol);
					break;
				}
			}
						
			
		} catch (MitBusinessException e) {
			LOGGER.error("ERROR UBICACION",e);
		}
	}
	
	public List<SelectItem> getLstPeriodicidad() {
		if(lstPeriodicidad==null || lstPeriodicidad.isEmpty()){
			lstPeriodicidad = new ArrayList<SelectItem>();
			try{
				List<GenericCatalogoBean> period = archivoDomiciliacionWebService.obtenerPeriodicidad();
				for(int i=0;i<period.size();i++){
					lstPeriodicidad.add(new SelectItem(period.get(i).getId().toString(),period.get(i).getValor()));
				}
			} catch (MitBusinessException e) {
				LOGGER.error("Error al obtener el listado de periodicicades",e);
			}	
		}
		
		return lstPeriodicidad;
	}
	
	public void cambioPeriodicidad(){
		if(selectedSol.getPeriodicidad().equals(new Short("494")) || selectedSol.getPeriodicidad().equals(new Short("493"))
				|| selectedSol.getPeriodicidad().equals(new Short("492"))){
					listFrqInic = new ArrayList<SelectItem>(); 
					listFrqFin = new ArrayList<SelectItem>();
					if(selectedSol.getPeriodicidad().equals(new Short("494"))){
						try{
							List<GenericCatalogoBean> period = archivoDomiciliacionWebService.obtenerTiposFrecMensual();
							for(int i=0;i<period.size();i++){
								listFrqInic.add(new SelectItem(period.get(i).getId().toString(),period.get(i).getValor()));
							}
						} catch (MitBusinessException e) {
							LOGGER.error("Error al obtener el listado de frecuencias MENSUAL",e);
						}
					}else if(selectedSol.getPeriodicidad().equals(new Short("493"))){
						try{
							if(habilitaEdicion) {
								List<GenericCatalogoBean> frecIni = archivoDomiciliacionWebService.obtenerTiposFrecQuincenalIni();
								for(int i=0;i<frecIni.size();i++){
									listFrqInic.add(new SelectItem(frecIni.get(i).getId().toString(),frecIni.get(i).getValor()));
								}
								
								if(selectedSol.getFrecuenciaInicial()!=null){
									List<GenericCatalogoBean> frecFin = archivoDomiciliacionWebService.obtenerTiposFrecQuincenalFin(1);
									for(int i=0;i<frecFin.size();i++){
										listFrqFin.add(new SelectItem(frecFin.get(i).getId().toString(),frecFin.get(i).getValor()));
									}
								}
							}else {
								listFrqInic.add(new SelectItem(selectedSol.getFrecuenciaInicial()));
								listFrqFin.add(new SelectItem(selectedSol.getFrecuenciaFinal()));
							}
						} catch (MitBusinessException e) {
							LOGGER.error("Error al obtener el listado de frecuencias QUINCENAL",e);
						}
					}else if(selectedSol.getPeriodicidad().equals(new Short("492"))){
						try{
							List<GenericCatalogoBean> period = archivoDomiciliacionWebService.obtenerTiposFrecSemanal();
							for(int i=0;i<period.size();i++){
								listFrqInic.add(new SelectItem(period.get(i).getId().toString(),period.get(i).getValor()));
							}
						} catch (MitBusinessException e) {
							LOGGER.error("Error al obtener el listado de frecuencias SEMANAL",e);
						}
					}
					cambioFrecInicial();
				}
	}
	
	public void cambioFrecInicial(){
		listFrqFin = new ArrayList<SelectItem>();
		if(habilitaEdicion) {
			if(selectedSol.getPeriodicidad().equals(new Short("493"))){
				try{
					if(selectedSol.getFrecuenciaInicial()!=null){
						if(selectedSol.getFrecuenciaInicial().intValue()>15) {
							selectedSol.setFrecuenciaInicial(new Short(String.valueOf(15)));
						}
						List<GenericCatalogoBean> frecFin = archivoDomiciliacionWebService.obtenerTiposFrecQuincenalFin(selectedSol.getFrecuenciaInicial().intValue());
						for(int i=0;i<frecFin.size();i++){
							listFrqFin.add(new SelectItem(frecFin.get(i).getId().toString(),frecFin.get(i).getValor()));
						}
					}
					
				} catch (MitBusinessException e) {
					LOGGER.error("Error al obtener el listado de frecuencias QUINCENAL",e);
				}
				
			}
		}else {
			listFrqFin.add(new SelectItem(selectedSol.getFrecuenciaFinal()));
		}
	}
	
	
	public void setLstPeriodicidad(List<SelectItem> lstPeriodicidad) {
		this.lstPeriodicidad = lstPeriodicidad;
	}
	
	public List<SelectItem> getListFrqInic() {
		return listFrqInic;
	}
	
	public void setListFrqInic(List<SelectItem> listFrqInic) {
		this.listFrqInic = listFrqInic;
	}
	
	public List<SelectItem> getListFrqFin() {
		return listFrqFin;
	}
	
	public void setListFrqFin(List<SelectItem> listFrqFin) {
		this.listFrqFin = listFrqFin;
	}
	
	public boolean isSearchBtnVisible() {
		return searchBtnVisible;
	}
	
	public void setSearchBtnVisible(boolean searchBtnVisible) {
		this.searchBtnVisible = searchBtnVisible;
	}
	
	public boolean isHabilitaBtnEdit() {
		return habilitaBtnEdit;
	}
	
	public void setHabilitaBtnEdit(boolean habilitaBtnEdit) {
		this.habilitaBtnEdit = habilitaBtnEdit;
	}
	
	public void habilitaTablaDos(){
		setMuestraTablaDos(true);
	}
	
	public boolean isMuestraTablaDos() {
		return muestraTablaDos;
	}
	
	public void setMuestraTablaDos(boolean muestraTablaDos) {
		this.muestraTablaDos = muestraTablaDos;
	}

	public String getCurpDos() {
		return curpDos;
	}

	public void setCurpDos(String curpDos) {
		this.curpDos = curpDos;
	}

	public void ocultarTablaDos() {
		setMuestraTablaDos(false);
		setSelectedSol(null);
		busquedaFiltro();
	}
	
	public boolean isEditPeriodiUni() {
		return editPeriodiUni;
	}
	
	public void setEditPeriodiUni(boolean editPeriodiUni) {
		this.editPeriodiUni = editPeriodiUni;
	}
	
	public List<MonitoreoSolicitudesDetalleBean> getLstSolicitudesHist() {
		return lstSolicitudesHist;
	}
	
	public void setLstSolicitudesHist(
			List<MonitoreoSolicitudesDetalleBean> lstSolicitudesHist) {
		this.lstSolicitudesHist = lstSolicitudesHist;
	}
	
	public List<SelectItem> getListDomis() {
		return listDomis;
	}
	
	public void setListDomis(List<SelectItem> listDomis) {
		this.listDomis = listDomis;
	}

	public Short getOrigenBusqueda() {
		return origenBusqueda;
	}

	public void setOrigenBusqueda(Short origenBusqueda) {
		this.origenBusqueda = origenBusqueda;
	}

	public IRolesWebService getRolesWebService() {
		return rolesWebService;
	}

	public void setRolesWebService(IRolesWebService rolesWebService) {
		this.rolesWebService = rolesWebService;
	}

	public String getResPeriodicidadDesc() {
		return resPeriodicidadDesc;
	}

	public void setResPeriodicidadDesc(String resPeriodicidadDesc) {
		this.resPeriodicidadDesc = resPeriodicidadDesc;
	}

	public Short getResPeriodicidad() {
		return resPeriodicidad;
	}

	public void setResPeriodicidad(Short resPeriodicidad) {
		this.resPeriodicidad = resPeriodicidad;
	}

	public Short getResFrecuenciaInicial() {
		return resFrecuenciaInicial;
	}

	public void setResFrecuenciaInicial(Short resFrecuenciaInicial) {
		this.resFrecuenciaInicial = resFrecuenciaInicial;
	}

	public Short getResFrecuenciaFinal() {
		return resFrecuenciaFinal;
	}

	public void setResFrecuenciaFinal(Short resFrecuenciaFinal) {
		this.resFrecuenciaFinal = resFrecuenciaFinal;
	}


	public MonitoreoSolicitudesDetalleBean getSelectedEstatusSol() {
		return selectedEstatusSol;
	}

	public void setSelectedEstatusSol(MonitoreoSolicitudesDetalleBean selectedEstatusSol) {
		this.selectedEstatusSol = selectedEstatusSol;
	}
}