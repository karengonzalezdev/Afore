package mx.profuturo.nci.web.views;

import java.io.Serializable;
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


import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ConfigIntentosApoVolVO;
import mx.profuturo.nci.web.beans.DomiIntentosCargaFilterBean;
import mx.profuturo.nci.web.beans.SolicitudBean;
import mx.profuturo.nci.web.service.IConfigIntentosCargaApoVolWebService;


@ManagedBean(name = "ConfigIntentosCargaApoVolView")
@ViewScoped
public class ConfigIntentosCargaApoVolView implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaDomiciliacionView.class);

	@ManagedProperty(value = "#{configIntentosCargaApoVolWebService}")
	IConfigIntentosCargaApoVolWebService configIntentosCargaApoVolWebService;
	
	
	
    RequestContext context = RequestContext.getCurrentInstance();
	private Integer numIntentos = 1;
	private List<CatalogoVO> origenAportacionLista;
	private List<CatalogoVO> periodoLista;
	private List<ConfigIntentosApoVolVO> listConfigIntentosApoVol = new ArrayList<ConfigIntentosApoVolVO>();
	private List<ConfigIntentosApoVolVO> listConfigIntentosInsert = new ArrayList<ConfigIntentosApoVolVO>();
	private List<ConfigIntentosApoVolVO> listConfigIntentosEditar = new ArrayList<ConfigIntentosApoVolVO>();		 
	private boolean cuentaSeleccionada;
	private boolean btnNumInten;
	private boolean btnEditar;
	private boolean btnGuardar;
	private Integer selOrigenApo;
	private Integer selPeriodo;


	private SolicitudBean selectedSolicitud;

	private boolean muestraCuentas;
	private ConfigIntentosApoVolVO selectedCuentaSolicitud;




	public void limpiar() {
	}

	@PostConstruct
	public void init() {
		selectedCuentaSolicitud = new ConfigIntentosApoVolVO();
		selectedCuentaSolicitud.setNumIntentos(0);
	}

	public void onAgregarConfiguracion() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		java.util.Date fecha = new Date();
		String descOrigen = "";
		String descPeriodo = "";

		String usuarioActualizacion = facesContext.getExternalContext().getRemoteUser();
		
		if(selOrigenApo.equals("") && selOrigenApo == null) {
			return;
		}

		for (CatalogoVO origenVO : origenAportacionLista) {
			if (Integer.parseInt(origenVO.getIdCatCatalogo() + "") == selOrigenApo) {
				descOrigen = origenVO.getValor();
				
			}

		}

		for (CatalogoVO periodoVO : periodoLista) {
			if (Integer.parseInt(periodoVO.getIdCatCatalogo() + "") == selPeriodo) {
				descPeriodo = periodoVO.getValor();
				
			}

		}


		ConfigIntentosApoVolVO nuevaConfIntentos = new ConfigIntentosApoVolVO(0,selOrigenApo, descOrigen, selPeriodo,
				descPeriodo, numIntentos, 1, fecha, usuarioActualizacion, fecha, usuarioActualizacion);

		listConfigIntentosApoVol.add(nuevaConfIntentos);

	}
	
	public void onSeleccionar() {
		numIntentos = selectedCuentaSolicitud.getNumIntentos();
		btnNumInten = false;
		btnGuardar = false;
		btnEditar = true;
		
	}
	public void onGuardarValidacion() {

		if (selectedCuentaSolicitud == null) {
			context = RequestContext.getCurrentInstance();
	        context.update("alertaDialog");
	        context.execute("PF('alertaDialog').show();");
	        return;
		} 
		if (selectedCuentaSolicitud.getNumIntentos() == numIntentos) {
			context = RequestContext.getCurrentInstance();
		    context.update("alertaDialog2");
		    context.execute("PF('alertaDialog2').show();");
		    return;
	    }
		context = RequestContext.getCurrentInstance();
        context.update("confirmDialogAct");
        context.execute("PF('confirmDialogAct').show();");

	}
	
	public void onGuardarConfiguracion() {
		int insertCorrectot;

		try {
				
			for (ConfigIntentosApoVolVO intentosCarga : listConfigIntentosApoVol) {
				if(intentosCarga.getIdConfigIntentosApoVol() == 0) {
					listConfigIntentosInsert.add(intentosCarga);
				}
			}
			if(listConfigIntentosInsert.size() != 0) {
				insertCorrectot = this.configIntentosCargaApoVolWebService.insertConfIntentosCarga(listConfigIntentosInsert);				
				if(insertCorrectot == 1) {
					context = RequestContext.getCurrentInstance();
		            context.update("DiagMsgNotificacion");
		            context.execute("PF('DialogMsgNotificacion').show();");
				}
			}
			if (selectedCuentaSolicitud.getNumIntentos() != numIntentos) {
				ConfigIntentosApoVolVO confApoVol = selectedCuentaSolicitud;
				confApoVol.setNumIntentos(numIntentos);
				insertCorrectot = this.configIntentosCargaApoVolWebService.editaConfIntentosCarga(confApoVol);
				if(insertCorrectot == 1) {
					limpiarBusq();
					context = RequestContext.getCurrentInstance();
		            context.update("dialogEditarIntentosCarg");
		            context.execute("PF('dialogEditarIntentosCarg').show();");
				}
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
            context.update("DiagMsgNotificacion");
            context.execute("PF('DialogMsgNotificacion').show();");
            LOGGER.error(e.getMessage(), e);
		}
	}
	

	public void limpiarBusq() {
		selOrigenApo = null;
		selPeriodo = null;
		btnNumInten =false;
		btnGuardar = false;
		btnEditar = false;

		setMuestraCuentas(false);
		setCuentaSeleccionada(false);

		selectedCuentaSolicitud = null;
		selectedSolicitud = null;
		
		listConfigIntentosInsert.clear();
		listConfigIntentosEditar.clear();
		listConfigIntentosApoVol.clear();
	}

	public List<CatalogoVO> getOrigenAportacionLista() throws Exception {
		if (origenAportacionLista == null) {
			origenAportacionLista = new ArrayList<CatalogoVO>();
			origenAportacionLista = this.configIntentosCargaApoVolWebService.consultaCatOrigenDomi();
		}
		return origenAportacionLista;
	}

	public List<CatalogoVO> getPeriodoLista() throws Exception {
		if (periodoLista == null) {
			periodoLista = new ArrayList<CatalogoVO>();
			periodoLista = this.configIntentosCargaApoVolWebService.consultaCatPeriodo();
		}
		return periodoLista;
	}

	public void busquedaPrincipal() throws Exception {

		selectedCuentaSolicitud = null;
		selectedSolicitud = null;

		buscar();
	}
	
	public void editar() {
		btnNumInten = true;
		btnGuardar = true;
		
	}

	public void buscar() throws Exception {
		muestraCuentas = true;

		DomiIntentosCargaFilterBean domiIntentoCargaFilter = new DomiIntentosCargaFilterBean();
		if (selOrigenApo != null) {
			domiIntentoCargaFilter.setIdOrigenDomiciliacion(selOrigenApo);
		}
		
		if (selPeriodo != null) {
			domiIntentoCargaFilter.setIdPeriodo(selPeriodo);
		}
	
		listConfigIntentosApoVol = this.configIntentosCargaApoVolWebService.consultaConfigIntentosApoVol(domiIntentoCargaFilter);

	}

	public void setOrigenAportacionLista(List<CatalogoVO> origenAportacionLista) {
		this.origenAportacionLista = origenAportacionLista;
	}

	public void setPeriodoLista(List<CatalogoVO> periodoLista) {
		this.periodoLista = periodoLista;
	}

	public IConfigIntentosCargaApoVolWebService getConfigIntentosCargaApoVolWebService() {
		return configIntentosCargaApoVolWebService;
	}

	public void setConfigIntentosCargaApoVolWebService(
			IConfigIntentosCargaApoVolWebService configIntentosCargaApoVolWebService) {
		this.configIntentosCargaApoVolWebService = configIntentosCargaApoVolWebService;
	}

	public SolicitudBean getSelectedSolicitud() {
		return selectedSolicitud;
	}
	
	public Integer getSelOrigenApo() {
		return selOrigenApo;
	}

	public void setSelOrigenApo(Integer selOrigenApo) {
		this.selOrigenApo = selOrigenApo;
	}

	public Integer getSelPeriodo() {
		return selPeriodo;
	}

	public void setSelPeriodo(Integer selPeriodo) {
		this.selPeriodo = selPeriodo;
	}

	

	public List<ConfigIntentosApoVolVO> getListConfigIntentosApoVol() {
		return listConfigIntentosApoVol;
	}

	public void setListConfigIntentosApoVol(List<ConfigIntentosApoVolVO> listConfigIntentosApoVol) {
		this.listConfigIntentosApoVol = listConfigIntentosApoVol;
	}

	public List<ConfigIntentosApoVolVO> getListConfigIntentosInsert() {
		return listConfigIntentosInsert;
	}

	public void setListConfigIntentosInsert(List<ConfigIntentosApoVolVO> listConfigIntentosInsert) {
		this.listConfigIntentosInsert = listConfigIntentosInsert;
	}

	public List<ConfigIntentosApoVolVO> getListConfigIntentosEditar() {
		return listConfigIntentosEditar;
	}

	public void setListConfigIntentosEditar(List<ConfigIntentosApoVolVO> listConfigIntentosEditar) {
		this.listConfigIntentosEditar = listConfigIntentosEditar;
	}

	public boolean isMuestraCuentas() {
		return muestraCuentas;
	}

	public void setMuestraCuentas(boolean muestraCuentas) {
		this.muestraCuentas = muestraCuentas;
	}

	public ConfigIntentosApoVolVO getSelectedCuentaSolicitud() {
		return selectedCuentaSolicitud;
	}

	public void setSelectedCuentaSolicitud(ConfigIntentosApoVolVO selectedCuentaSolicitud) {
		if (selectedCuentaSolicitud != null) {
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
	
	public boolean isBtnNumInten() {
		return btnNumInten;
	}

	public void setBtnNumInten(boolean numInten) {
		this.btnNumInten = numInten;
	}
	
	public boolean isBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(boolean btnEditar) {
		this.btnEditar = btnEditar;
	}

	public boolean isBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(boolean btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public Integer getNumIntentos() {
		return numIntentos;
	}

	public void setNumIntentos(Integer numIntentos) {
		this.numIntentos = numIntentos;
	}

}