package mx.profuturo.nci.web.views;

import static mx.profuturo.nci.business.util.ConstantesCatalogos.ID_ORIGEN_APORTACION_SPEI;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.profuturo.nci.business.util.Constantes;
import mx.profuturo.nci.business.vo.ConciliacionVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.vo.IndicadorVO;
import mx.profuturo.nci.business.vo.OrdenesVO;
import mx.profuturo.nci.business.wrapped.ConciliacionFilter;
import mx.profuturo.nci.business.wrapped.OrdenesFilter;
import mx.profuturo.nci.web.beans.ClienteOrdenSpeiBean;
import mx.profuturo.nci.web.beans.DiversificacionOrdenSpeiBean;
import mx.profuturo.nci.web.beans.FondoBean;
import mx.profuturo.nci.web.beans.OrdenSpeiBean;
import mx.profuturo.nci.web.controller.IdentificarClienteController;
import mx.profuturo.nci.web.controller.OrdenSpeiController;
import mx.profuturo.nci.ws.webservice.catalogo.CatalogoBean;

import org.jfree.util.Log;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "identificarClienteView")
@ViewScoped
//@Component
public class IdentificarClienteView implements Serializable {

	private static final long serialVersionUID = 4439992831244707350L;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(IdentificarClienteView.class);
	
	private static Short STATUS_NUEVA_ORDEN = 754; 
	private static Short STATUS_NUEVA_ORDEN_CONCILIADA = 755;

	/*
	 * Varable que permite realizar el manejo de informaci&oacute;n de la
	 * pantalla
	 */
	private String importe;
	private String valor = "monto";
	private String filtro;
	private String filtroVal;
	private String vlrDiversificacion;
	private String errorMessage;
	private String msgNuevaOrden;
	private Double totalTabla;
	private boolean falgNuevaOrdenRelacionar;
	public boolean confirmNvaOrden;

	private OrdenSpeiBean orden = new OrdenSpeiBean();
	private DiversificacionOrdenSpeiBean diversificacion;
	private ClienteOrdenSpeiBean clienteSelected;
	private ConciliacionVO conciliacionSelected;
	private OrdenesVO ordenSelected;
	
	private List<ClienteOrdenSpeiBean> lstClientes;
	private List<FondoBean> diversificacionLst;
	private List<ConciliacionVO> lstConciliacion;
	private List<OrdenesVO> lstOrdenes;
	
	private boolean tieneMultiplesCI;
	private boolean tablaResultado;
	private String regexMontoPorcentaje;
	private List<IndicadorVO> mapa;
	
	@ManagedProperty(value="#{ordenSpeiController}")
	//@Autowired
	private OrdenSpeiController ordenSpeiController;
	
	@ManagedProperty(value="#{identificarClienteController}")
	//@Autowired
	private IdentificarClienteController identificarClienteController;
	
	

	@PostConstruct
	public void init() {
		try {
			this.reset();
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACION",e);
		}
	}

	/**
	 * Metodo para el renicio de las variables
	 */
	private void reset() {
		
		this.filtroVal = null;
		this.filtro = null;
		this.confirmNvaOrden = false;
		this.mapa = null;
		this.lstClientes = new ArrayList<ClienteOrdenSpeiBean>();
		
		this.falgNuevaOrdenRelacionar = false;
		this.vlrDiversificacion = null;
		this.diversificacion = new DiversificacionOrdenSpeiBean();
		this.orden = new OrdenSpeiBean();
		this.diversificacionLst = new ArrayList<FondoBean>();
		this.lstConciliacion = new ArrayList<ConciliacionVO>();
		this.lstOrdenes = new ArrayList<OrdenesVO>();
		this.conciliacionSelected = null;
		this.ordenSelected = null;
		this.clienteSelected = null;
		this.totalTabla = 0.0;
		
		this.tieneMultiplesCI = false;
		this.tablaResultado = false;
		this.regexMontoPorcentaje = null;
	}

	/**
	 * M&eacute;todo que permite realizar la operaci&oacute;n para una orden
	 * SPEI.
	 */
	public void generarOrdenSpei(ActionEvent actionEvent) {
		boolean isOk = false;
		
		if(this.validaOrden()){
			this.orden.setImporte(new Double(this.importe.replaceAll("\\$", "").replaceAll("\\,", "").replaceAll("\\%", "")));
			this.orden.setConciliacionVo(this.falgNuevaOrdenRelacionar 
					? this.conciliacionSelected : null);
			
			if(clienteSelected != null && tieneMultiplesCI == true){
				this.clienteSelected.setOrdenSpei(this.orden);
				this.clienteSelected.getOrdenSpei().setDiversificacionSpei(
						this.diversificacionLst);
				this.clienteSelected.getOrdenSpei().setUsuarioCreacion(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
				
				isOk = this.ordenSpeiController.guardarOrdenSpei(
						this.clienteSelected,
						this.vlrDiversificacion.equalsIgnoreCase("Monto"));
			}else{
				this.lstClientes.get(0).setOrdenSpei(this.orden);
				this.lstClientes.get(0).getOrdenSpei().setDiversificacionSpei(this.diversificacionLst);
				this.lstClientes.get(0).getOrdenSpei().setUsuarioCreacion(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
				
				isOk = this.ordenSpeiController.guardarOrdenSpei(
						this.lstClientes.get(0),
						this.vlrDiversificacion.equalsIgnoreCase("Monto"));
			}

			if (isOk) {
				this.errorMessage = 
						!this.falgNuevaOrdenRelacionar ? 
								"La orden ha sido generada exitosamente. Se ha enviado una notificación al cliente con los datos y procedimientos para realizar su aportación vía SPEI." 
								: "La orden SPEI se generó de manera exitosa y se relacionó con el depósito seleccionado";
				this.setMessage(FacesMessage.SEVERITY_INFO, "", this.errorMessage);
			} else {
				this.errorMessage= "Ocurrió un error en el proceso";
				this.setMessage(FacesMessage.SEVERITY_ERROR, "Error", this.errorMessage);
			}
			
			RequestContext context = RequestContext.getCurrentInstance();
			if(this.falgNuevaOrdenRelacionar){
				this.onCargarDatosRelacionar();
			}
			//context.update("idFormGeneral:formGrowl");

			context.update("idFormDlgInfoOrdenGenerada:idDlgInfoOrdenGenerada");
			context.execute("PF('dialogConfirm').hide();");
			context.execute("PF('dlgNvaOrden').hide();");
			context.execute("PF('dlgOrdenGenerada').show()");
			this.importe = "";
			//context.execute("PF('dlgErrorMessage').show();");
			//context.update("idFormDlgErrorMessage:idDlgErrorMessage");
		}else{
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dlgErrorMessage').show();");
			context.update("idFormDlgErrorMessage:idDlgErrorMessage");
		}
		
	}
	
	public void validaOrdenSpei(){
		if(this.validaOrden()){
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dialogConfirm').show();");
		}else{
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("idFormDlgErrorMessage:idDlgErrorMessage");
			context.execute("PF('dlgErrorMessage').show();");
		}
	}

	/**
	 * Metodo para validar la Orden a guardar Autor: EJLF
	 * 
	 * @return
	 */
	private boolean validaOrden() {
		boolean success = true;
		boolean isSelectAtLeastOne = false;
		boolean flagMontoPorcenta = false;
		String importeAux = this.importe.replaceAll("\\$","").replaceAll("\\%","").replaceAll("\\,", "");

		// Validar la suma de los montos o los porcentajes
		if(this.importe == null || this.importe.isEmpty()){
			this.errorMessage = "Debe ingresar un valor en el importe a invertir";
			return false;
		}else if(!this.isNumber(this.importe)){
			this.errorMessage = "Solo se permiten valores numéricos en la cantidad a invertir";
			return false;
		}
		
		Double monto = Double.parseDouble(importeAux);
		double total = 0;
		
		if(monto.compareTo(new Double(0)) <= 0){
			this.errorMessage = "Debe ingresar un importe mayor a cero";
			return false;
		}
		
		if (this.vlrDiversificacion != null
				&& !this.vlrDiversificacion.isEmpty()) {
			flagMontoPorcenta = this.vlrDiversificacion
					.equalsIgnoreCase("Monto");
		} else {
			this.errorMessage = "Debe seleccionar un tipo de diversificacón";
			return false;
		}

		for (FondoBean fondo : this.diversificacionLst) {
			if (fondo.isSelecion()) {
				isSelectAtLeastOne = true;
				if(fondo.getValor() != null && !fondo.getValor().isEmpty()){
					if(this.isNumber(fondo.getValor())){
						total = total + Double.parseDouble(fondo.getValor().replaceAll("\\$","").replaceAll("\\%","").replaceAll("\\,", ""));
					}else{
						this.errorMessage = "Solo se permiten valores numéricos positivos";
						return false;
					}
				}else{
					this.errorMessage = "No se permiten aportaciones en blanco en fondos seleccionados";
					return false;
				}
				
			}
		}

		if (isSelectAtLeastOne) {
			if (flagMontoPorcenta) {// Monto
				if (monto != total) {
					this.errorMessage = "La suma de cantidades no es la misma que la de importe";
					return false;
				}
			} else {// Porcentage
				if (total != 100) {
					this.errorMessage = "La suma de cantidades no es el 100%";
					return false;
				}
			}
		} else {
			this.errorMessage = "Debe seleccionar por lo menos un fondo de ahorro";
			return false;
		}

		return success;
	}
	
	/**
	 * Metodo para validar si una cadena de texto es numerica
	 * 
	 * @param numString
	 * @return
	 */
	private boolean isNumber(String numString){
		boolean isNumber = false;
		numString = numString.replaceAll("\\$", "");
		numString = numString.replaceAll("\\,", "");
		numString = numString.replaceAll("\\%", "");
		if (numString.matches("\\d*")){
			isNumber = true;
		}else if(numString.matches("^[0-9]+([.][0-9]+)?$")){
			isNumber = true;
		}
		
		return isNumber;
	}
	
	/**
	 * Metodo para consultar cliente
	 * 
	 */
	public void consultarCliente(ActionEvent actionEvent) {
		this.lstClientes = new ArrayList<ClienteOrdenSpeiBean>();
		this.clienteSelected = null;
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext contextR = RequestContext.getCurrentInstance();
		String regex = null;
		
		this.tablaResultado = true;
		boolean val = false;
		boolean tam = false;
		
		if(this.filtroVal != null && !this.filtroVal.isEmpty()){
			if(this.filtro != null && !this.filtro.isEmpty()){
				if(filtroVal.contains("NSS")){
					if(filtro.length() > 11){
						context.addMessage("idFormDlgErrorMessage:errorMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "",  "No es un valor válido.") );
						this.errorMessage = "El tamaño máximo es de 11.";
						this.tablaResultado = false;
						contextR.execute("PF('dlgErrorMessage').show();");
					}else{
						regex = "(\\d{11})";
						tam = true;
					}
				}
				if(filtroVal.contains("CURP")){
					if(filtro.length() > 18){
						context.addMessage("idFormDlgErrorMessage:errorMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "",  "No es un valor válido.") );
						this.errorMessage = "El tamaño máximo es de 18.";
						this.tablaResultado = false;
						contextR.execute("PF('dlgErrorMessage').show();");
					}else{
						regex = "([A-Z]{1}[AEIOUX]{1}[A-Z]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[HM]{1}(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[0-9A-Z]{1}[0-9]{1})";
						tam= true;
					}
				}
				if(filtroVal.contains("Cuenta")){
					if(filtro.length() > 10){
						context.addMessage("idFormDlgErrorMessage:errorMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "",  "No es un valor válido.") );
						this.errorMessage = "El tamaño máximo es de 10.";
						this.tablaResultado = false;
						contextR.execute("PF('dlgErrorMessage').show();");
					}else{
						regex = "([0-9]{1,11})";
						tam = true;
					}
				}
				
				if(tam == true){
					Pattern patron = Pattern.compile(regex);
					Matcher match = patron.matcher(filtro);
					if(!match.find()){
						context.addMessage("idFormDlgErrorMessage:errorMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "",  "No es un valor válido.") );
						this.errorMessage = "No es un valor válido.";
						this.tablaResultado = false;
						contextR.execute("PF('dlgErrorMessage').show();");
					}else{
						val = true;
					}
					
					if(val == true){
						this.lstClientes = this.identificarClienteController.identificarCliente(this.filtroVal, this.filtro);
						//Se agregan datos de Régimen, Estatus de vigencia y Última actualización
						try{
							for(int i = 0; i < lstClientes.size(); i++){
								this.mapa = this.ordenSpeiController.agregarInfoCuenta(lstClientes.get(i).getNumeroCuenta());
								this.lstClientes.get(i).setRegimen(mapa.get(0).getDescIndicador());
								this.lstClientes.get(i).setVigencia(mapa.get(1).getValorIndicador());
								this.lstClientes.get(i).setFechaUltimaAct(mapa.get(1).getFechaVigencia());
								this.lstClientes.get(i).setDetalle(mapa.get(1).getDetalle());
								}
							}
						catch (Exception e) {
							LOGGER.error("Ocurrió un error en la búsqueda de información adicional de la cuenta: " + this.filtroVal + ":" + e.getMessage(), e);
							context.addMessage("idFormDlgErrorMessage:errorMessage", new FacesMessage(FacesMessage.SEVERITY_FATAL, "",  "Ocurrió un error en la búsqueda de información de cuenta.") );
							}
						if (this.lstClientes != null && !this.lstClientes.isEmpty()) {
							if(lstClientes.size() > 1){
								this.tieneMultiplesCI = Boolean.TRUE;
							}else{
								this.tieneMultiplesCI = Boolean.FALSE;
								//lstClientes.get(0).setr
								onCargarDatosRelacionar();
							}
//							context.addMessage("idFormDlgErrorMessage:errorMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "",  "Operacion realizada con éxito") );
						} else {
							this.lstClientes = null;
							this.errorMessage = "Sin registros. No se han encontrado resultados con los datos de búsqueda proporcionados.";
							this.tablaResultado = false;
							contextR.execute("PF('dlgErrorMessage').show();");
						}
					}
				}
			}else{
				this.errorMessage = "Debe ingresar algún dato en el campo filtro.";
				this.tablaResultado = false;
				contextR.execute("PF('dlgErrorMessage').show();");
			}
		}else{
			this.errorMessage = "Debe seleccionar un tipo de consulta.";
			this.tablaResultado = false;
			contextR.execute("PF('dlgErrorMessage').show();");
		}
	
	}
	private UIComponent messageError;
	public void setMessageError(UIComponent messageError1) {
        this.messageError = messageError;
    }

    public UIComponent getMessageError() {
        return messageError;
    }

	/**
	 * Metodo (Listener) para cargar los datos de 
	 * las ordenes spei pendientes de relacionar y 
	 * las ordenes pendientes de deposito
	 * 
	 * Autor:EJLF
	 * 
	 */
	public void onCargarDatosRelacionar() {
		
		if(this.clienteSelected != null || this.tieneMultiplesCI == false){
			try {
				//Lista de no conciliados
				ConciliacionFilter filterCon = new ConciliacionFilter();
				if(clienteSelected != null)
					filterCon.setNumeroCuentaIndividual(new Long(clienteSelected.getNumeroCuenta()));//Este si va pero aun no tengo caso de uso para probar
				else
					filterCon.setNumeroCuentaIndividual(new Long(lstClientes.get(0).getNumeroCuenta()));
				filterCon.setRegistroConciliado(new Short(Constantes.STRING_FALSE));
				filterCon.setOrigenAportacion(new GenericCatalogoVO(ID_ORIGEN_APORTACION_SPEI));
				this.lstConciliacion = this.identificarClienteController.consultarConciliaciones(filterCon);
	
				//Lista pendientes de pago
				OrdenesFilter filterOrd = new OrdenesFilter();
				if(clienteSelected != null)
					filterOrd.setNumCuentaIndividual(new Long(this.clienteSelected.getNumeroCuenta()));
				else
					filterOrd.setNumCuentaIndividual(new Long(lstClientes.get(0).getNumeroCuenta()));
				List<Short> lstEstatusOrdenes = new ArrayList<Short>();
				lstEstatusOrdenes.add(STATUS_NUEVA_ORDEN);
				filterOrd.setEstatusOrdenes(lstEstatusOrdenes);
				this.lstOrdenes = this.ordenSpeiController.consultarOrdenes(filterOrd);
				
				
				//Actualizar p:dialog
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("idFormRelacion:idDlgRelacion");
	            context.execute("PF('dlgRelacionar').show();");
				
			} catch (Exception e) {
				LOGGER.error("ERROR UBICACION",e);
			}
		}else{
			RequestContext context = RequestContext.getCurrentInstance();
			this.errorMessage = "Debe seleccionar un cliente.";
			context.execute("PF('dlgErrorMessage').show();");
			context.update("idFormDlgErrorMessage:idDlgErrorMessage");
		}
	}
	
	/**
	 * Metodo para actualizar el Dialog de Nueva Orden
	 */
	public void onActualizarDlgNvaOrden(){
		this.vlrDiversificacion = "";
		if(this.clienteSelected != null || this.tieneMultiplesCI == false){
			try {
				this.diversificacionLst = this.identificarClienteController.obtenerCatalogo();
			} catch (Exception e) {
				LOGGER.error("ERROR UBICACION",e);
			}
			
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("idFrmNuevaOrden:idDlgNvaOrden");
	        context.execute("PF('dlgNvaOrden').show();");
		}else{
			this.setMessage(FacesMessage.SEVERITY_ERROR,"Error", "Debe seleccionar un cliente");
			RequestContext context = RequestContext.getCurrentInstance();
			this.errorMessage = "Debe seleccionar un cliente.";
			context.execute("PF('dlgErrorMessage').show();");
			context.update("idFormDlgErrorMessage:idDlgErrorMessage");
		}
		
	}
	
	/**
	 * Metodo para actualizar el Dialog de Nueva Orden pero ya para relacionar
	 */
	public void onActualizarDlgNvaOrdenRelacion(){
		if(this.conciliacionSelected != null){
			this.falgNuevaOrdenRelacionar = true;
			this.importe = this.formatNumber(this.conciliacionSelected.getImporte().toString(), true);
			this.onActualizarDlgNvaOrden();
		}else{
			RequestContext context = RequestContext.getCurrentInstance();
			this.errorMessage = "Debe seleccionar un depósito pendiente de identificar.";
			context.execute("PF('dlgErrorMessage').show();");
			context.update("idFormDlgErrorMessage:idDlgErrorMessage");
		}
	}

	/**
	 * Metodo para relacionar las orden seleccionada con la conciliacion
	 * 
	 * Autor: EJLF
	 */
	public void onRelacionarOrdenConciliacion(){
		if(this.validarRelacion()){
			RequestContext context = RequestContext.getCurrentInstance();
			try {
				this.conciliacionSelected.setUsuarioActualizacion(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
				this.ordenSelected.setUsuarioActualizacion(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
				this.ordenSpeiController.guardarConciliciacion(this.conciliacionSelected, this.ordenSelected);
				this.setMessage(FacesMessage.SEVERITY_INFO, "", "El depósito SPEI y la orden seleccionada han sido asociados satisfactoriamente.");
				this.onCargarDatosRelacionar();
			} catch (Exception e) {
				LOGGER.error("ERROR UBICACION",e);
				this.errorMessage = "Ocurrió un error en el proceso.";
				context.execute("PF('dlgErrorMessage').show();");
				context.update("idFormDlgErrorMessage:idDlgErrorMessage");
			}
		}else{
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dlgErrorMessage').show();");
			context.update("idFormDlgErrorMessage:idDlgErrorMessage");
		}
	}
	
	/**
	 * Metodo para validar la relacion entre la conciliacion y la orden
	 * 
	 * @return
	 */
	private boolean validarRelacion(){
		boolean success = true;
		if(this.conciliacionSelected != null){
			if(this.ordenSelected == null){
				this.errorMessage = "Debe seleccionar una orden pendiente de depósito";
				return false;
			}
		}else{
			this.errorMessage = "Debe seleccionar un depósito pendiente de identificar";
			return false;
		}
		
		if(this.ordenSelected.getImporte().compareTo(this.conciliacionSelected.getImporte()) != 0){
			this.errorMessage = "El importe de la orden y el depósito SPEI no coinciden";
			return false;
		}
		
		return success;
	}

	/**
	 * Metodo para cancelar las acciones de el DlgRelacionar
	 */
	public void cancelarRelacion(){
		this.conciliacionSelected = null;
		this.ordenSelected = null;
		this.falgNuevaOrdenRelacionar = false;
		this.vlrDiversificacion = null;
		
		this.lstConciliacion = new ArrayList<ConciliacionVO>();
		this.lstOrdenes = new ArrayList<OrdenesVO>();
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("idFormRelacion:idDlgRelacion");
	}

	
	/**
	 * M&eacute;todo que permite cancelar una orden
	 * SPEI nueva.
	 */
	public void cancelarOrdenSpei(ActionEvent actionEvent) {
		this.importe = null;
		this.diversificacionLst = new ArrayList<FondoBean>();
		this.vlrDiversificacion = null;
		this.totalTabla = 0.0; 
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("idFrmNuevaOrden:idDlgNvaOrden");
	}
	
	
	/**
	 * Metodo para setear los errores en el facesContext
	 * @param severity
	 * @param msg
	 * @param detail
	 */
	private void setMessage(Severity severity,String msg, String detail){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(severity, msg,detail));
	}
	
	
	/**
	 *Metodo para actualizar el dialog dlgDetalleDiver 
	 */
	public void consultarDetalleOrden(){
		RequestContext context = RequestContext.getCurrentInstance();
		if(this.ordenSelected != null){
			context.update("idFormDetalle:idDlgDetalle");
			context.execute("PF('dlgDetalleDiver').show()");
		}else{
			this.errorMessage = "Debe seleccionar una orden para consultar su detalle.";
			context.execute("PF('dlgErrorMessage').show();");
			context.update("idFormDlgErrorMessage:idDlgErrorMessage");
		}
	}
	
	/**
	 * Metodo para cancelar la identificacion de cliente
	 * @return
	 */
	public String onCancelar(){
		this.reset();
		return "../index.xhtml?faces-redirect=true";
	}
	
	
	/**
	 * Metodo para saber el nombre del banco
	 * 
	 * @param idCatalogo
	 * @return
	 */
	public String consultaBancoById(String idCatalogo){
		String strBanco = "";
		try {
			if(idCatalogo != null && !idCatalogo.isEmpty()){
				CatalogoBean catBanco = this.ordenSpeiController.consultaBancoById(idCatalogo);
				strBanco = catBanco.getValor();
			}
			
		} catch (Exception e) {
			LOGGER.error("ERROR UBICACION",e);
		}
		
		return strBanco;
	}
	
	/**
	 * Metodo para formatear las cantidades
	 * 
	 * @param num
	 * @param falgImportePorcentaje
	 * @return
	 */
	public String formatNumber(Object num, boolean flagImportePorcentaje){
		String number = "";
		
		if(num != null && !num.toString().isEmpty()){
			num = num.toString().replaceAll("\\$", "").replaceAll("\\,", "").replaceAll("\\%", "");
			String formatNumber = flagImportePorcentaje ? "###,###,###,##0.00" : "#0";
			DecimalFormat formatter = new DecimalFormat(formatNumber);
//			Double numPercent = (Double.parseDouble(num.toString()) / 100);
			try{
				number = formatter.format(new Double(num.toString()));
				}
			catch(NumberFormatException e){
				LOGGER.error("Ocurrió un error en la conversión del parámetro: " + num, e);
				number = "";
			}
		}
		return number;
	}
	
	
	/**
	 * Metodo para actualizar el total de la tabla
	 */
	public void onUpdateTotalTabla(){
		this.totalTabla = 0.0;
		for(FondoBean fondo : this.diversificacionLst){
			if(fondo.isSelecion() && !fondo.getValor().isEmpty()){
				try{
				this.totalTabla += Double.parseDouble(fondo.getValor().replaceAll("\\$", "").replaceAll("\\,", "").replaceAll("\\%", ""));
				fondo.setValor(formatNumber(fondo.getValor(), this.vlrDiversificacion.equalsIgnoreCase("Monto")));
				}catch(NumberFormatException e){
					LOGGER.error("Ocurrió un error en la conversión del parámetro: " + fondo.getValor(), e);
					fondo.setValor("");
					this.totalTabla = 0.0;
				}
			}
		}
	}
	
	
	public void cleanDiversificacionValues(){
		this.totalTabla = 0.0;
		for(FondoBean fondo : this.diversificacionLst){
			fondo.setValor("");
			fondo.setSelecion(false);
		}
		if(this.vlrDiversificacion.contains("Monto")){
//			this.regexMontoPorcentaje = "'[0-9,\\.]'";
			this.regexMontoPorcentaje = "'([0-9]*[.]?[0-9]*)'";
		}else{
			this.regexMontoPorcentaje = "'[0-9]'";
		}
		
	}
	/**
	 * Metodo para formatear el importe al ingresarlo en el inputText
	 */
	public void onUpdateImporte(){
		this.importe = this.formatNumber(this.importe, true);
	}
	
	
	/**
	 * Metodo para limpiar el campo de filtrar cuando cambie el radio button de tipo de busqueda
	 */
	public void onCleanCampoFiltrar(){
		this.tieneMultiplesCI = Boolean.FALSE;
		this.filtro = "";
		this.lstClientes = new ArrayList<ClienteOrdenSpeiBean>();
		this.tablaResultado = false;
	}
	
	/*************************** Getters & Setters **************************/
	/**
	 * @return the filtro
	 */
	public String getFiltro() {
		return filtro;
	}

	/**
	 * @param filtro
	 *            the filtro to set
	 */
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	/**
	 * @return the identificarClienteController
	 */
	public IdentificarClienteController getIdentificarClienteController() {
		return identificarClienteController;
	}

	/**
	 * @param identificarClienteController
	 *            the identificarClienteController to set
	 */
	public void setIdentificarClienteController(
			IdentificarClienteController identificarClienteController) {
		this.identificarClienteController = identificarClienteController;
	}

	public List<ClienteOrdenSpeiBean> getLstClientes() {
		return lstClientes;
	}

	public void setLstClientes(List<ClienteOrdenSpeiBean> lstClientes) {
		this.lstClientes = lstClientes;
	}

	/**
	 * @return the orden
	 */
	public final OrdenSpeiBean getOrden() {
		return orden;
	}

	/**
	 * @param orden
	 *            the orden to set
	 */
	public final void setOrden(OrdenSpeiBean orden) {
		this.orden = orden;
	}

	/**
	 * @return the diversificacion
	 */
	public final DiversificacionOrdenSpeiBean getDiversificacion() {
		return diversificacion;
	}

	/**
	 * @param diversificacion
	 *            the diversificacion to set
	 */
	public final void setDiversificacion(
			DiversificacionOrdenSpeiBean diversificacion) {
		this.diversificacion = diversificacion;
	}

	public List<FondoBean> getDiversificacionLst() {
		return diversificacionLst;
	}

	public void setDiversificacionLst(ArrayList<FondoBean> diversificacionLst) {
		this.diversificacionLst = diversificacionLst;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * @return the ordenSpeiController
	 */
	public OrdenSpeiController getOrdenSpeiController() {
		return ordenSpeiController;
	}

	/**
	 * @param ordenSpeiController
	 *            the ordenSpeiController to set
	 */
	public void setOrdenSpeiController(OrdenSpeiController ordenSpeiController) {
		this.ordenSpeiController = ordenSpeiController;
	}

	public ClienteOrdenSpeiBean getClienteSelected() {
		return clienteSelected;
	}

	public void setClienteSelected(ClienteOrdenSpeiBean clienteSelected) {
		this.clienteSelected = clienteSelected;
	}

	public String getFiltroVal() {
		return filtroVal;
	}

	public void setFiltroVal(String filtroVal) {
		this.filtroVal = filtroVal;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getVlrDiversificacion() {
		return vlrDiversificacion;
	}

	public void setVlrDiversificacion(String vlrDiversificacion) {
		this.vlrDiversificacion = vlrDiversificacion;
	}

	public void setDiversificacionLst(List<FondoBean> diversificacionLst) {
		this.diversificacionLst = diversificacionLst;
	}

	public ConciliacionVO getConciliacionSelected() {
		return conciliacionSelected;
	}

	public void setConciliacionSelected(ConciliacionVO conciliacionSelected) {
		this.conciliacionSelected = conciliacionSelected;
	}
	
	public List<ConciliacionVO> getLstConciliacion() {
		return lstConciliacion;
	}

	public void setLstConciliacion(List<ConciliacionVO> lstConciliacion) {
		this.lstConciliacion = lstConciliacion;
	}

	public List<OrdenesVO> getLstOrdenes() {
		return lstOrdenes;
	}

	public void setLstOrdenes(List<OrdenesVO> lstOrdenes) {
		this.lstOrdenes = lstOrdenes;
	}

	public boolean isFalgNuevaOrdenRelacionar() {
		return falgNuevaOrdenRelacionar;
	}

	public void setFalgNuevaOrdenRelacionar(boolean falgNuevaOrdenRelacionar) {
		this.falgNuevaOrdenRelacionar = falgNuevaOrdenRelacionar;
	}

	public OrdenesVO getOrdenSelected() {
		return ordenSelected;
	}

	public void setOrdenSelected(OrdenesVO ordenSelected) {
		this.ordenSelected = ordenSelected;
	}

	public boolean isConfirmNvaOrden() {
		return confirmNvaOrden;
	}

	public void setConfirmNvaOrden(boolean confirmNvaOrden) {
		this.confirmNvaOrden = confirmNvaOrden;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMsgNuevaOrden() {
		return msgNuevaOrden;
	}

	public void setMsgNuevaOrden(String msgNuevaOrden) {
		this.msgNuevaOrden = msgNuevaOrden;
	}

	public Double getTotalTabla() {
		return totalTabla;
	}

	public void setTotalTabla(Double totalTabla) {
		this.totalTabla = totalTabla;
	}

	public boolean isTieneMultiplesCI() {
		return tieneMultiplesCI;
	}

	public void setTieneMultiplesCI(boolean tieneMultiplesCI) {
		this.tieneMultiplesCI = tieneMultiplesCI;
	}

	public boolean isTablaResultado() {
		return tablaResultado;
	}

	public void setTablaResultado(boolean tablaResultado) {
		this.tablaResultado = tablaResultado;
	}

	public String getRegexMontoPorcentaje() {
		return regexMontoPorcentaje;
	}

	public void setRegexMontoPorcentaje(String regexMontoPorcentaje) {
		this.regexMontoPorcentaje = regexMontoPorcentaje;
	}
	
}
