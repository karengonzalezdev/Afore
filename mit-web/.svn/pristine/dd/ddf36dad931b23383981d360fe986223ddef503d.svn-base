package mx.profuturo.nci.web.views;


import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.primefaces.context.RequestContext;
import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.vo.ActualizarEstatusSolicitudVO;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.DesmarcarCuentaMatrizConvivenciaVO;
import mx.profuturo.nci.business.vo.DictamenRechazadaRespuestaVO;
import mx.profuturo.nci.business.vo.FinalizaFolioVO;
import mx.profuturo.nci.business.vo.ReporteCifrasControlVO;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.business.vo.ResultadoDictamenRechazadoVO;
import mx.profuturo.nci.business.vo.SolicitudReclasificacionVO;
import mx.profuturo.nci.web.beans.CifrasControlClienteFilterBean;
import mx.profuturo.nci.web.service.ICifrasControlClienteWebService;

@ManagedBean(name = "cifrasControlClienteView")
@ViewScoped
public class CifrasControlClienteView implements Serializable{
	

	private static final long serialVersionUID = 2087513853226809443L;
	private static final Logger LOGGER = LoggerFactory.getLogger(CifrasControlClienteView.class);

	
	
	@ManagedProperty(value="#{cifrasControlClienteWebService}")
	private ICifrasControlClienteWebService cifrasControlClienteWebService;
	
	private String mensaje;
	private String mensajeError;
	private boolean estatusRechazado = false;
	private static final String XLS_MIME_TYPE = "application/xls";



	private String clientsSearchType;
	private String clientsSearchValue;
	private Date fechaInicio;
	private Date fechaFin;
	private Short claveOrigen;
	private Short claveEstatus;
	private Short claveMotivo;
	private String totalDiversifica;
	private String totalCargo;
	
	private String claveOrigenFil;
	private String claveEstatusFil;
	private boolean realizarBus=false;

	
	private List<CatalogoVO> listOrigen;
	private List<CatalogoVO> listEstatus;
	private List<CatalogoVO> listMotivos;
	
	public List<SolicitudReclasificacionVO> resultados;

	public SolicitudReclasificacionVO seleccionado;
	
	public List<SolicitudReclasificacionVO> resultadosDetalle;
	public List<SolicitudReclasificacionVO> resultadosDetalleCargo;
	
	public void limpiarTxt() {
		
		clientsSearchValue=null;
	}
	
	public void limpiar() {
		
		clientsSearchType=null;
		clientsSearchValue=null;
		fechaInicio= null;
		fechaFin= null;
		claveOrigen=null;
		claveEstatus=null;
		resultados=null;
		seleccionado=null;
	}
	
	public void consumeDictamenRechazado() {
		
		ResultadoDictamenRechazadoVO dictamenEnvelope						= null;
		DictamenRechazadaRespuestaVO respuesta 								= null;
		
		ActualizarEstatusSolicitudVO estatusSolicitud						= null;
		DesmarcarCuentaMatrizConvivenciaVO desmarcarCuentaMatrizConvivencia	= null;
		FinalizaFolioVO finalizaFolio										= null;
		
		try {
			
//			if( seleccionado.getFolio() != null ) {
				if( claveMotivo > 0 ) {
					estatusSolicitud	= new ActualizarEstatusSolicitudVO();
					estatusSolicitud.setCveSolicitud( seleccionado.getClave() );
					estatusSolicitud.setIdEstatusSolicitud( Integer.toString( 511 ) );
					estatusSolicitud.setIdMotivoRechazo( Integer.toString( claveMotivo ) );
					estatusSolicitud.setUsuActualiza( FacesContext.getCurrentInstance().getExternalContext().getRemoteUser() );
					
					desmarcarCuentaMatrizConvivencia	= new DesmarcarCuentaMatrizConvivenciaVO();
					desmarcarCuentaMatrizConvivencia.setIdProceso( "7" );
					desmarcarCuentaMatrizConvivencia.setIdSubProceso( "130" );
					desmarcarCuentaMatrizConvivencia.setNumCuentaIndividual( seleccionado.getCuenta() + ""  );
					desmarcarCuentaMatrizConvivencia.setFolio( seleccionado.getClave() );
					desmarcarCuentaMatrizConvivencia.setIdSubetapa( "3214" );
					desmarcarCuentaMatrizConvivencia.setUsuario( FacesContext.getCurrentInstance().getExternalContext().getRemoteUser() );
					
					finalizaFolio		= new FinalizaFolioVO();
					finalizaFolio.setFolio( seleccionado.getClave());
					finalizaFolio.setIdEstatusProceso( Integer.toString( seleccionado.getEstatus() ) );
					finalizaFolio.setUsuAct( FacesContext.getCurrentInstance().getExternalContext().getRemoteUser() );
					
					dictamenEnvelope	= new ResultadoDictamenRechazadoVO();
					dictamenEnvelope.setDesmarcarCuentaMatrizConvivencia( desmarcarCuentaMatrizConvivencia );
					dictamenEnvelope.setEstatusSolicitud( estatusSolicitud );
					dictamenEnvelope.setFinalizaFolio( finalizaFolio );
					
					LOGGER.info( "" );
					LOGGER.info( " ************   ESTATUS SOLICITUD ******************************************" );
					LOGGER.info( "" );
					LOGGER.info( "Clave Solicitud 		     :" + estatusSolicitud.getCveSolicitud() );
					LOGGER.info( "Estatus Soliciutus         :" + estatusSolicitud.getIdEstatusSolicitud() );
					LOGGER.info( "Motivo Rechazo             :" + estatusSolicitud.getIdMotivoRechazo() );
					LOGGER.info( "Usuario Actualiza          :" + estatusSolicitud.getUsuActualiza() );
					LOGGER.info( "" );
					LOGGER.info( "" );
					LOGGER.info( " ************   DESMARCA CUENTA MATRIZ CONVIVENCIA *************************" );
					LOGGER.info( "" );
					LOGGER.info( "idProceso                  :" + desmarcarCuentaMatrizConvivencia.getIdProceso() );
					LOGGER.info( "idSubproceso               :" + desmarcarCuentaMatrizConvivencia.getIdSubProceso() );
					LOGGER.info( "Numero cuenta individual   :" + desmarcarCuentaMatrizConvivencia.getNumCuentaIndividual() );
					LOGGER.info( "Folio                      :" + desmarcarCuentaMatrizConvivencia.getFolio() );
					LOGGER.info( "idSubetapa                 :" + desmarcarCuentaMatrizConvivencia.getIdSubetapa() );
					LOGGER.info( "usuario                    :" + desmarcarCuentaMatrizConvivencia.getUsuario() );
					LOGGER.info( "" );
					LOGGER.info( "" );
					LOGGER.info( " ************   FINALIZA FOLIO  ********************************************" );
					LOGGER.info( "" );
					LOGGER.info( "folio                      :" + finalizaFolio.getFolio() );
					LOGGER.info( "idEstatusProceso           :" + finalizaFolio.getIdEstatusProceso() );
					LOGGER.info( "usuAct                     :" + finalizaFolio.getUsuAct() );
					LOGGER.info( "" );
					
					respuesta = cifrasControlClienteWebService.dictamenRechazada(dictamenEnvelope);
					
					LOGGER.info( "****************  CONSUMIENDO SERVICIO  *************************************" );
					LOGGER.info( "" );
					LOGGER.info( "respuesta codRet WS :" + respuesta.getCodRet() );
					LOGGER.info( "respuesta msgRet WS :" + respuesta.getMsgRet() );
					LOGGER.info( "respuesta Actualiza        [response] :" + respuesta.getActualizaEstatusResponse() );
					LOGGER.info( "respuesta Actualiza        [success]  :" + respuesta.getActualizaEstatusSuccess() );
					LOGGER.info( "respuesta Proceso Desmarca [response] :" + respuesta.getProcesoDesmarcaResponse() );
					LOGGER.info( "respuesta Procesa Desmarca [success]  :" + respuesta.getProcesoDesmarcaSuccess() );
					LOGGER.info( "respuesta Folio            [folio]    :" + respuesta.getFinalizaFolioSuccess() );
					
					if( respuesta.getCodRet().equals("000") ) {
						if( 	respuesta.getActualizaEstatusResponse().equals("true") && 
								respuesta.getProcesoDesmarcaSuccess().equals( "Desmarca Exitosa" )  &&
								respuesta.getFinalizaFolioSuccess().equals( "true" )
						) {
							mensajeError = "Rechazo exitoso de solicitud.";
							estatusRechazado	= true;
						}
					}else {
						mensajeError = "No se completo la transacción. Favor de volver a intentar.";
						estatusRechazado	= false;
					}
					
				}else {
					mensajeError	= "Para completar la operacion seleccione un motivo";
					estatusRechazado	= false;
				}
//			}else{
//				mensajeError	= "La operación no puede concluir ya que la solicitud no cuenta con folio";
//				estatusRechazado	= false;
//			}
			
			LOGGER.info( "ESTATUS DE RECHAZO : "+ estatusRechazado );
			
			
		}catch( Exception e ) {
			LOGGER.error( "ERROR UBICACION   : " + CifrasControlClienteView.class.getCanonicalName() );
			LOGGER.info( "ERROR METODO      : " + "consumeDictamenRechazado" );
			LOGGER.info( "ERROR MENSAJE     : " + e.getMessage()  );
			LOGGER.info( "ERROR DESCRIPCION : " , e );
		}finally {
			dictamenEnvelope					= null;
			estatusSolicitud					= null;
			desmarcarCuentaMatrizConvivencia	= null;
			finalizaFolio						= null;
			respuesta							= null;
		}
		
	}
	
	public void limpiarMotivo() {
		mensajeError		= null;
		claveMotivo			= null;
		estatusRechazado	= false;
	}
	
	public void actualizar() {
		
		boolean response;
		
		try {
			
			if(seleccionado.getEstatus() == 512 || seleccionado.getEstatus() == 516) {
				
				response = cifrasControlClienteWebService.actializarEstatus(seleccionado.getClave(), seleccionado.getEstatus());
				
				if(response == true) {
					
					seleccionado=null;
					consultarSolicitudes();
					 mensaje ="La actualizacion fue exitosa";
					 RequestContext.getCurrentInstance().execute("PF('mensajeDialog').show()");
				
				}else {
				
					mensaje ="Ocurrio un problema en la actualizacion";
					RequestContext.getCurrentInstance().execute("PF('mensajeDialog').show()");
				}
			}else {
				mensaje ="El estatus tiene que ser Aceptado/Suspendido";
				RequestContext.getCurrentInstance().execute("PF('mensajeDialog').show()");
			}
		
		} catch(MitBusinessException e){
			
			FacesContext.getCurrentInstance().addMessage(
					"error al actualizar", 
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							e.getLocalizedMessage(),
							"Ocurrio un error al actualizar cifra control cliente:"+e.getMessage()));
			LOGGER.error("actualizar():"+e.getMessage(),e);
			
		}
		
	}
	
	
	public void consultarSolicitudes() {
		realizarBus=false;
		resultados=null;
		seleccionado=null;
		try {
			
			CifrasControlClienteFilterBean parametros= new CifrasControlClienteFilterBean();
		
		if(claveOrigen != null && claveOrigen != 0) {
			claveOrigenFil = claveOrigen.toString();
			parametros.setOrigen(claveOrigenFil);
			realizarBus=true;
			
		}if(claveEstatus != null && claveEstatus != 0) {
			claveEstatusFil = claveEstatus.toString();
			parametros.setEstatus(claveEstatusFil);
			realizarBus=true;
			
		}if(fechaInicio != null) {
			parametros.setFechaInc(fechaInicio);
			realizarBus=true;
			
		}if(fechaFin != null) {
			parametros.setFechaFin(fechaFin);
			realizarBus=true;
		}
		
		if(  clientsSearchValue != null && !clientsSearchValue.equals("") && clientsSearchType != null) {
			
			if(clientsSearchType.contains("Cuenta")) {

				parametros.setCuenta(clientsSearchValue);
				realizarBus=true;
			}else
				if(clientsSearchType.contains("NSS")) {
					parametros.setNss(clientsSearchValue);
					realizarBus=true;
				}
				else
					if(clientsSearchType.contains("CURP")) {
						if(clientsSearchValue.length() == 18) {
							parametros.setCurp(clientsSearchValue);
							realizarBus=true;
							
						}else {
							FacesContext.getCurrentInstance().addMessage(null, new
									FacesMessage(FacesMessage.SEVERITY_ERROR, "El CURP no cuenta con la longitud de 18 caracteres", "CURP no valido"));
							realizarBus=false;
						}
					}
		}
		
		if( realizarBus == true) {
			resultados=cifrasControlClienteWebService.consultaSolicitudes(parametros);
			if(resultados.size() == 0) {

				RequestContext.getCurrentInstance().execute("PF('sinResultadoTablaDialog').show()");
			}
		}

		
		
		}catch (MitBusinessException e) {

			FacesContext.getCurrentInstance().addMessage(
					"error al consultar",
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							e.getLocalizedMessage(),
							"Ocurrio un error al consultar solicitudes:"+e.getMessage()));
			LOGGER.error("consultarSolicitudes():"+e.getMessage(),e);
		
		}
	}
	
	public void consultarSolicitudesDetalle() {
		
		try {
			
		SolicitudReclasificacionVO parametros= new SolicitudReclasificacionVO();
		
		if(claveOrigen != null) {
			
			parametros.setClave(seleccionado.getClave());
			
		}
		
		resultadosDetalle = cifrasControlClienteWebService.consultaSolicitudesDetalle(parametros);
		resultadosDetalleCargo = cifrasControlClienteWebService.consultaSolicitudesDetalleCargo(parametros);
		totalDiversifica = cifrasControlClienteWebService.totalDiversifica(parametros);
		totalCargo = cifrasControlClienteWebService.totalCargo(parametros);
		
		}catch (MitBusinessException e) {

			FacesContext.getCurrentInstance().addMessage(
					"error al consultar detalle", 
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							e.getLocalizedMessage(),
							"Ocurrio un error al consultar el detalle:"+e.getMessage()));
			LOGGER.error("consultarSolicitudesDetalle():"+e.getMessage(),e);
			
		}
	}
	
	
	public StreamedContent genrarReporte() {
		ReporteVO report= new ReporteVO();
		try {
			
			SolicitudReclasificacionVO parametros= new SolicitudReclasificacionVO();
			
			if(claveOrigen != null) {
				
				parametros.setOrigen(claveOrigen);
				
			}if(claveEstatus != null) {
				
				parametros.setEstatus(claveEstatus);
				
			}if(fechaInicio != null) {
				parametros.setFechaInicio(fechaInicio);
				
			}if(fechaFin != null) {
				parametros.setFechaFin(fechaFin);
			}
			
			if( clientsSearchValue != null && !clientsSearchValue.equals("") && clientsSearchType != null) {
				if(clientsSearchValue != null && clientsSearchType.contains("Cuenta")) {
					long cuentaLong = Long.valueOf(clientsSearchValue);
					parametros.setCuenta(cuentaLong);
				}else
					if(clientsSearchValue != null &&  clientsSearchType.contains("NSS")) {
						parametros.setNss(clientsSearchValue);	
					}
					else
						if(clientsSearchValue != null &&  clientsSearchType.contains("CURP")) {
							parametros.setCurp(clientsSearchValue);		
						}
			}
			
			List<ReporteCifrasControlVO> listData = cifrasControlClienteWebService.consultaReporte(parametros);
			report = cifrasControlClienteWebService.generarReporte(listData);
			
			
		} catch(MitBusinessException e){
			
			FacesContext.getCurrentInstance().addMessage(
					"error al genrear reporte",
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							e.getLocalizedMessage(),
							"Ocurrio un error al generar el reporte:"+e.getMessage()));
			LOGGER.error("genrarReporte():"+e.getMessage(),e);
			
		}
		
		return genDocument(report);
		
		
	}
	
	
	private StreamedContent genDocument(ReporteVO report) {
		StreamedContent xls= null;
		ByteArrayInputStream is= new ByteArrayInputStream(report.getArchivo());
		xls = new DefaultStreamedContent(is, XLS_MIME_TYPE, report.getNombre()+".xls");
		return xls;
	}
	
	
	
	public List<SolicitudReclasificacionVO> getResultadosDetalleCargo() {
		return resultadosDetalleCargo;
	}

	public void setResultadosDetalleCargo(List<SolicitudReclasificacionVO> resultadosDetalleCargo) {
		this.resultadosDetalleCargo = resultadosDetalleCargo;
	}

	public List<SolicitudReclasificacionVO> getResultadosDetalle() {
		return resultadosDetalle;
	}

	public void setResultadosDetalle(List<SolicitudReclasificacionVO> resultadosDetalle) {
		this.resultadosDetalle = resultadosDetalle;
	}

	public List<SolicitudReclasificacionVO> getResultados() {
		return resultados;
	}

	public void setResultados(List<SolicitudReclasificacionVO> resultados) {
		this.resultados = resultados;
	}

	public SolicitudReclasificacionVO getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(SolicitudReclasificacionVO seleccionado) {
		this.seleccionado = seleccionado;
	}

	public String getClientsSearchValue() {
		return clientsSearchValue;
	}

	public void setClientsSearchValue(String clientsSearchValue) {
		this.clientsSearchValue = clientsSearchValue;
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

	public Short getClaveOrigen() {
		return claveOrigen;
	}

	public void setClaveOrigen(Short claveOrigen) {
		this.claveOrigen = claveOrigen;
	}

	public Short getClaveEstatus() {
		return claveEstatus;
	}

	public void setClaveEstatus(Short claveEstatus) {
		this.claveEstatus = claveEstatus;
	}

	public ICifrasControlClienteWebService getCifrasControlClienteWebService() {
		return cifrasControlClienteWebService;
	}

	public void setCifrasControlClienteWebService(ICifrasControlClienteWebService cifrasControlClienteWebService) {
		this.cifrasControlClienteWebService = cifrasControlClienteWebService;
	}

	public List<CatalogoVO> getListOrigen() throws MitBusinessException {
		if(listOrigen == null) {
			listOrigen = new ArrayList<CatalogoVO>();
			listOrigen = cifrasControlClienteWebService.consultarOrigen();
		}
		return listOrigen;
	}
	
	public void setListOrigen(List<CatalogoVO> listOrigen) {
		this.listOrigen = listOrigen;
	}
	
	public List<CatalogoVO> getListEstatus()  throws MitBusinessException{
		if(listEstatus == null) {
			listEstatus = new ArrayList<CatalogoVO>();
			listEstatus = cifrasControlClienteWebService.consultarEstatus();
		}
		return listEstatus;
	}
	
	public List<CatalogoVO> getListMotivos() throws MitBusinessException{
		if( listMotivos == null ) {
			listMotivos = new ArrayList<CatalogoVO>();
			listMotivos = cifrasControlClienteWebService.consultarMotivos();
		}
		return listMotivos;
	}
	
	public void setListEstatus(List<CatalogoVO> listEstatus) {
		this.listEstatus = listEstatus;
	}


	public String getClientsSearchType() {
		return clientsSearchType;
	}


	public void setClientsSearchType(String clientsSearchType) {
		this.clientsSearchType = clientsSearchType;
	}



	public Short getClaveMotivo() {
		return claveMotivo;
	}

	public void setClaveMotivo(Short claveMotivo) {
		this.claveMotivo = claveMotivo;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	
	public boolean isEstatusRechazado() {
		return estatusRechazado;
	}

	public void setEstatusRechazado(boolean estatusRechazado) {
		this.estatusRechazado = estatusRechazado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getTotalDiversifica() {
		return totalDiversifica;
	}

	public void setTotalDiversifica(String totalDiversifica) {
		this.totalDiversifica = totalDiversifica;
	}

	public String getTotalCargo() {
		return totalCargo;
	}

	public void setTotalCargo(String totalCargo) {
		this.totalCargo = totalCargo;
	}
	
	

}
