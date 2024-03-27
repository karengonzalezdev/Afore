package mx.profuturo.nci.web.views.traspasos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.util.UtilMethod;
import mx.profuturo.nci.web.beans.traspasos.InversionDomiTraspasosBean;
import mx.profuturo.nci.web.beans.traspasos.InversionDomiTraspasosDetalleBean;
import mx.profuturo.nci.web.beans.traspasos.MonitoreoCargosDomiBean;
import mx.profuturo.nci.web.service.IArchivoDomiciliacionWebService;

@ManagedBean(name = "inversionDomiciliacionView")
@ViewScoped
public class InversionDomiciliacionView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InversionDomiciliacionView.class);
	
	@ManagedProperty(value="#{archivoDomiciliacionService}")
	IArchivoDomiciliacionWebService archivoDomiciliacionWebService;
	
	private String fechaConsulta;
	private InversionDomiTraspasosBean porAcreditar;
	private InversionDomiTraspasosBean pendientesTraspaso;
	private InversionDomiTraspasosBean devolucion;
	private InversionDomiTraspasosBean acreditados;
	
	public String init() {
		return "inversionDomiciliacion";
	}	
	
	public void test(){
		LOGGER.info("TEST");
	}
	
	public String getFechaConsulta() {
		Date fechaAct = new Date(System.currentTimeMillis());
		return UtilMethod.dateFormatter(fechaAct,"dd/MM/yyyy");
	}
	
	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}
	
	public InversionDomiTraspasosBean getPorAcreditar() {
	
		if(porAcreditar==null || porAcreditar.getLstRecurrencias().isEmpty())
		{
			try{
				setPorAcreditar(archivoDomiciliacionWebService.generaInvDomiTrasPorAcreditar());
			} catch (MitBusinessException e) {
				LOGGER.error("Error al obtener el detalle de inversión traspasos por Acreditar",e);
			}	
		}
		return porAcreditar;
	}
	
	public void setPorAcreditar(InversionDomiTraspasosBean porAcreditar) {
		this.porAcreditar = porAcreditar;
	}
	
	public InversionDomiTraspasosBean getPendientesTraspaso() {
		if(pendientesTraspaso==null || pendientesTraspaso.getLstRecurrencias().isEmpty()){
			try{
				setPendientesTraspaso(archivoDomiciliacionWebService.generaInvDomiTrasPendientesTraspaso());
			} catch (MitBusinessException e) {
				LOGGER.error("Error al obtener el detalle de inversión traspasos pendientes",e);
			}
		}
		return pendientesTraspaso;
	}
	
	public void setPendientesTraspaso(InversionDomiTraspasosBean pendientesTraspaso) {
		this.pendientesTraspaso = pendientesTraspaso;
	}
	
	public InversionDomiTraspasosBean getDevolucion() {
		if(devolucion==null || devolucion.getLstRecurrencias().isEmpty()){
			try{
				setDevolucion(archivoDomiciliacionWebService.generaInvDomiTrasDevolucion());
			} catch (MitBusinessException e) {
				LOGGER.error("Error al obtener el detalle de inversión traspasos con Devolución",e);
			}
		}
		return devolucion;
	}
	
	public void setDevolucion(InversionDomiTraspasosBean devolucion) {
		this.devolucion = devolucion;
	}
	
	public InversionDomiTraspasosBean getAcreditados() {
		if(acreditados==null || acreditados.getLstRecurrencias().isEmpty()){
			try{
				setAcreditados(archivoDomiciliacionWebService.generaInvDomiTrasAcreditados());
			} catch (MitBusinessException e) {
				LOGGER.error("Error al obtener el detalle de inversión traspasos con Devolución",e);
			}
		}
		return acreditados;
	}
	
	public void setAcreditados(InversionDomiTraspasosBean acreditados) {
		this.acreditados = acreditados;
	}
	
	public IArchivoDomiciliacionWebService getArchivoDomiciliacionWebService() {
		return archivoDomiciliacionWebService;
	}
	
	public void setArchivoDomiciliacionWebService(IArchivoDomiciliacionWebService archivoDomiciliacionWebService) {
		this.archivoDomiciliacionWebService = archivoDomiciliacionWebService;
	}
}