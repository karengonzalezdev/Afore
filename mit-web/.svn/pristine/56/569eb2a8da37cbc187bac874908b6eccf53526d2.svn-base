package mx.profuturo.nci.web.views;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.web.beans.ArchivosBean;
import mx.profuturo.nci.web.beans.BancosBean;
import mx.profuturo.nci.web.beans.BancosFilterBean;
import mx.profuturo.nci.web.beans.CuentaIndividualBean;
import mx.profuturo.nci.web.beans.GenericCatalogoBean;
import mx.profuturo.nci.web.service.IValidacionManualWebService;

@ManagedBean(name = "validacionManualView")
@ViewScoped
//@Component
public class ValidacionManualView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{validacionManualService}")
//	@Autowired
	IValidacionManualWebService validacionManualWebService;
	
	private Date maxDate;
	
	
	

	public IValidacionManualWebService getValidacionManualWebService() {
		return validacionManualWebService;
	}

	public void setValidacionManualWebService(IValidacionManualWebService validacionManualWebService) {
		this.validacionManualWebService = validacionManualWebService;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidacionManualView.class);
	public List<GenericCatalogoBean> bancos;
	public List<BancosBean> listaBancos;
	public List<GenericCatalogoBean> origen;
	public List<GenericCatalogoBean> empresa;
	public List<GenericCatalogoBean> apovol;
	public Date fechaInicio;
	public Date fechaFin;
	public Short claveBanco;
	public UploadedFile file;
	private BancosBean selectedBanco;
	public boolean habilitarEmpresa = false;
	private CuentaIndividualBean datosCuenta;
	private Short idOrigen;
	private static String REGEX_ARCHIVO = "(jpg|JPG|pdf|PDF)";
	private String listaArchivos;
	private List<ArchivosBean> archivos = new ArrayList<ArchivosBean>();
	private String idArchivo;
	private Short idEmpresa;
	private Long cuentaAfore = null;
	private Short idApovol;
	private String mensajeError;
	private static String MESSAGE_SUCCESS = "EXITO";
	private static String MESSAGE_ERROR = "ERROR";
	public boolean habiliaGuardar;
	public boolean muestraEstatus;
	
	public void validarGuardarInfo() {
		if((idOrigen.equals(new Short("0")) || idOrigen.equals(new Short("287")) || idOrigen.equals(new Short("288"))) && archivos.size() != 2)
		{
			sendMessage(MESSAGE_ERROR, "Se debe subir la documentación Copia IFE y Comprobante");
		}
		else
		{
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update("form:display");
			requestContext.execute("PF('dialogConfirmarOperacion').show()");
		}
	}
	
	public void guardarInfo() throws MitBusinessException {
		String usuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

		GenericCatalogoBean origenAportacion = new GenericCatalogoBean();
		origenAportacion.setId(idOrigen);
		selectedBanco.setOrigenAportacion(origenAportacion);
		GenericCatalogoBean subcuenta = new GenericCatalogoBean();
		subcuenta.setId(idApovol);
		selectedBanco.setSubctaApovol(subcuenta);
		selectedBanco.setNumCuentaIndividual(cuentaAfore);
		setMensajeError(this.validacionManualWebService.guardarInfo(selectedBanco, datosCuenta,idEmpresa, archivos, usuario));
		
		limpiarDialogNoRecono();
		clickLimpiar();
	}

	public void cargar(FileUploadEvent event) {
		listaArchivos = "";
		if (event != null && event.getFile() != null) {
			String extension = event.getFile().getFileName().substring(event.getFile().getFileName().length() - 3,
					event.getFile().getFileName().length());
			Pattern pattern = Pattern.compile(REGEX_ARCHIVO);
			Matcher matcher = pattern.matcher(extension);
			if (validaArchivo(idArchivo)) {
				sendMessage(MESSAGE_ERROR, "El tipo de documentación seleccionado ya ha sido cargado");
			} else {
				if (matcher.matches()) {
					listaArchivos += archivos.isEmpty() ? idArchivo
							: archivos.get(0).getListaCargados() + ", " + idArchivo;
					if (!archivos.isEmpty()) {
						archivos.get(0).setListaCargados(listaArchivos);
					}
					ArchivosBean archivosCargados = new ArchivosBean();
					archivosCargados.setNombreArchivo(event.getFile().getFileName());
					archivosCargados.setContenidoArchivo(event.getFile().getContents());
					archivosCargados.setListaCargados(listaArchivos);
					archivosCargados.setTipoArchivo(idArchivo);
					archivosCargados.setExtension(extension);
					archivos.add(archivosCargados);
					this.idArchivo = "Seleccionar";
				} else {
					sendMessage(MESSAGE_ERROR, "El archivo seleccionado no es válido");
				}
			}
		}
	}

	private void sendMessage(String tipo, String mensaje) {
		if (tipo.equals(MESSAGE_SUCCESS)) {
			FacesContext.getCurrentInstance().addMessage("messages-validacion",
					new FacesMessage(mensaje, mensaje));
		} else if (tipo.equals(MESSAGE_ERROR)) {
			FacesContext.getCurrentInstance().addMessage("messages-validacion", new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Error: " + mensaje, mensaje));
		}
	}

	private boolean validaArchivo(String idArchivo) {
		boolean cargado = false;
		if (!archivos.isEmpty()) {
			cargado = archivos.get(0).getListaCargados().contains(idArchivo);
		}
		return cargado;
	}

	public void clickValidarCuenta() throws MitBusinessException {
		if(idOrigen==0 || cuentaAfore==null || idApovol==0 ){
			if(idOrigen==0){
				FacesContext.getCurrentInstance().addMessage("messages-validacion", new FacesMessage(FacesMessage.SEVERITY_ERROR,
						null, "Debe seleccionar un origen"));
				
			}
			
			if(cuentaAfore==null){
				FacesContext.getCurrentInstance().addMessage("messages-validacion", new FacesMessage(FacesMessage.SEVERITY_ERROR,
						null, "Debe ingresar una cuenta"));
			
			}
			if(idApovol==0 ){
				FacesContext.getCurrentInstance().addMessage("messages-validacion", new FacesMessage(FacesMessage.SEVERITY_ERROR,
						null, "Debe seleccionar un número de referencia Apovol"));
			
			}
		}else{
			datosCuenta = new CuentaIndividualBean();
			try {
				datosCuenta = this.validacionManualWebService.validarCuentaInd(cuentaAfore);
			}catch(Exception e) {
				sendMessage(MESSAGE_ERROR, e.getMessage());
			}
			setMuestraEstatus(false);
			if(habiliaGuardar){
				if(datosCuenta.getIsVigente()){
					setHabiliaGuardar(false);
				}else{
					setHabiliaGuardar(true);
				}
			}
		}
	}

	public String init() {
		return "movimientos";
	}

	public void clickLimpiar() {
		this.selectedBanco = null;
		fechaInicio = null;
		fechaFin = null;
		claveBanco = null;
		datosCuenta = new CuentaIndividualBean();
		idOrigen = null;
		listaArchivos = null;
		archivos.clear();
		idArchivo = null;
		idEmpresa = null;
		cuentaAfore = null;
		idApovol = null;
		listaBancos= null;
		  RequestContext.getCurrentInstance().reset("form:idDialogIdentMov");
		  RequestContext.getCurrentInstance().reset("form:idEmpresaSel");
		  RequestContext.getCurrentInstance().reset("formMovimientos:idpanelBuscaMov");
		  RequestContext.getCurrentInstance().reset("formMovimientos:idpanelDetalleMov");
		  habiliaGuardar=true;
		setMuestraEstatus(true);
	}

	public void limpiarDialogNoRecono() {
		idOrigen = null;
		idEmpresa = null;
		cuentaAfore = null;
		idApovol = null;
		datosCuenta = new CuentaIndividualBean();
		idArchivo = null;
		
		archivos = new ArrayList<ArchivosBean>();
		habiliaGuardar =true;
		setMuestraEstatus(true);
	}
	
	public void clickCancelar() {
		RequestContext.getCurrentInstance().reset("form:idDialogIdentMov");
		RequestContext.getCurrentInstance().reset("form:idEmpresaSel");
		
		limpiarDialogNoRecono();
	}

	public void clickBuscar() throws MitBusinessException, ParseException {
		if(fechaInicio == null){
	  		  RequestContext.getCurrentInstance().execute("PF('dCriterioBusqueda').show()");
		}
		else{
			selectedBanco = null;
			this.listaBancos = new ArrayList<BancosBean>();
			BancosFilterBean bancosFilter = new BancosFilterBean();
			
			SimpleDateFormat sdfInicio = new SimpleDateFormat("dd/MM/yy hh:mm:ss.FF");
			String strDateInicio = sdfInicio.format(fechaInicio);
			Date dtInicio = sdfInicio.parse(strDateInicio);
			
			bancosFilter.setFechaInicio(dtInicio);
			
			String strDateFin;
			SimpleDateFormat sdfFin = new SimpleDateFormat("dd/MM/yy hh:mm:ss.FF");
			if(fechaFin != null){
				strDateFin = sdfFin.format(fechaFin);
			}else{
				Date fechaActual = new Date();
				strDateFin = sdfFin.format(fechaActual);
			}
			Date dtFin = sdfFin.parse(strDateFin);
		
			bancosFilter.setFechaFinal(dtFin);
			GenericCatalogoBean claveBancoCat = new GenericCatalogoBean();
			claveBancoCat.setId(claveBanco != null ? (claveBanco == 0 ? null : claveBanco) : null);
			bancosFilter.setClaveBanco(claveBancoCat);
			listaBancos = validacionManualWebService.consultar(bancosFilter);
			if(listaBancos.isEmpty() || listaBancos.size()==0){
				RequestContext.getCurrentInstance().execute("PF('dNoExistenRegistros').show()");
			}
			
		}
		setIdArchivo("Seleccionar");

	}
	
	public void limpiar(){
		this.listaBancos=new ArrayList<BancosBean>();
		this.fechaInicio=null;
		this.fechaFin=null;
		this.claveBanco=null;
		setIdArchivo("Seleccionar");
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
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

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

	public List<GenericCatalogoBean> getBancos() throws MitBusinessException {
		bancos = new ArrayList<GenericCatalogoBean>();
		bancos = validacionManualWebService.obtenerBancos();
		return bancos;
	}

	public void setBancos(List<GenericCatalogoBean> bancos) {
		this.bancos = bancos;
	}

	public List<BancosBean> getListaBancos() {
		return listaBancos;
	}

	public void setListaBancos(List<BancosBean> listaBancos) {
		this.listaBancos = listaBancos;
	}

	public BancosBean getSelectedBanco() {
		return selectedBanco;
	}

	public void setSelectedBanco(BancosBean selectedBanco) {
		setHabiliaGuardar(true);
		setMuestraEstatus(true);
		this.selectedBanco = selectedBanco;
	}

	public List<GenericCatalogoBean> getOrigen() throws MitBusinessException {
		origen = new ArrayList<GenericCatalogoBean>();
		origen = validacionManualWebService.obtenerOrigen();
		
		int indexSUA = origen.indexOf(new GenericCatalogoBean((short)293,null));
		if(indexSUA > 0){
			origen.remove(indexSUA);
		}
		
		return origen;
	}

	public void setOrigen(List<GenericCatalogoBean> origen) {
		this.origen = origen;
	}

	public List<GenericCatalogoBean> getEmpresa() throws MitBusinessException {
		empresa = new ArrayList<GenericCatalogoBean>();
		empresa = validacionManualWebService.obtenerEmpresa();
		return empresa;
	}

	public List<GenericCatalogoBean> getApovol() throws MitBusinessException {
		apovol = new ArrayList<GenericCatalogoBean>();
		apovol = validacionManualWebService.obtenerApovol();
		return apovol;
	}

	public void setApovol(List<GenericCatalogoBean> apovol) {
		this.apovol = apovol;
	}

	public void setEmpresa(List<GenericCatalogoBean> empresa) {
		this.empresa = empresa;
	}

	public boolean isHabilitarEmpresa() {
		return habilitarEmpresa;
	}

	public void setHabilitarEmpresa(boolean habilitarEmpresa) {
		this.habilitarEmpresa = habilitarEmpresa;
	}

	public Short getIdOrigen() {
		return idOrigen;
	}

	public void setIdOrigen(Short idOrigen) {
		this.idOrigen = idOrigen;
	}

	public CuentaIndividualBean getDatosCuenta() {
		return datosCuenta;
	}

	public void setDatosCuenta(CuentaIndividualBean datosCuenta) {
		this.datosCuenta = datosCuenta;
	}

	public Short getClaveBanco() {
		return claveBanco;
	}

	public void setClaveBanco(Short claveBanco) {
		this.claveBanco = claveBanco;
	}

	public String getListaArchivos() {
		return listaArchivos;
	}

	public void setListaArchivos(String listaArchivos) {
		this.listaArchivos = listaArchivos;
	}

	public String getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(String idArchivo) {
		this.idArchivo = idArchivo;
	}

	public Short getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Short idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Long getCuentaAfore() {
		return cuentaAfore;
	}

	public void setCuentaAfore(Long cuentaAfore) {
		cuentaAfore = cuentaAfore == 0 ? null : cuentaAfore;
		this.cuentaAfore = cuentaAfore;
	}

	public Short getIdApovol() {
		return idApovol;
	}

	public void setIdApovol(Short idApovol) {
		this.idApovol = idApovol;
	}

	public List<ArchivosBean> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<ArchivosBean> archivos) {
		this.archivos = archivos;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public Date getMaxDate() {
		this.maxDate=new Date();
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public void cambioOrigen(){
		this.idEmpresa = new Short("0");
		if(idOrigen==null){
			setHabiliaGuardar(true);
		}else if(idOrigen.equals(new Short("0")) || idOrigen.equals(new Short("287")) || idOrigen.equals(new Short("288"))){
			setHabiliaGuardar(true);
		}else{
			setHabiliaGuardar(false);
		}
		
		idEmpresa = null;
		cuentaAfore = null;
		idApovol = null;
		datosCuenta = new CuentaIndividualBean();
		idArchivo = null;
		
		archivos = new ArrayList<ArchivosBean>();
		setMuestraEstatus(true);
	}

	public boolean isHabiliaGuardar() {
		return habiliaGuardar;
	}
	
	public void setHabiliaGuardar(boolean habiliaGuardar) {
		this.habiliaGuardar = habiliaGuardar;
	}
	
	public boolean isMuestraEstatus() {
		return muestraEstatus;
	}
	
	public void setMuestraEstatus(boolean muestraEstatus) {
		this.muestraEstatus = muestraEstatus;
	}	
}