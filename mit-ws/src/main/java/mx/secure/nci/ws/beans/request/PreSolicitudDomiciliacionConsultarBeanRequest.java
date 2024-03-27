package mx.secure.nci.ws.beans.request;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class PreSolicitudDomiciliacionConsultarBeanRequest 
{
	private Long numCuentaIndividual;
	private List<Short> estatusSolicitudes;
	private String folio;
	private String claveSolicitud;
	private String folioProcesar;
	
	private String curp;
	private String curpTutor;

	private String cuentaBanco;
	
	@XmlElementWrapper(name = "estatusSolicitudes")
	@XmlElement(name = "estatusSolicitud")
	public List<Short> getEstatusSolicitudes() 
	{
		return estatusSolicitudes;
	}
	
	
	public Long getNumCuentaIndividual() {
		return numCuentaIndividual;
	}
	public void setNumCuentaIndividual(Long numCuentaIndividual) {
		this.numCuentaIndividual = numCuentaIndividual;
	}

	public void setEstatusSolicitudes(List<Short> estatusSolicitudes) {
		this.estatusSolicitudes = estatusSolicitudes;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getClaveSolicitud() {
		return claveSolicitud;
	}
	public void setClaveSolicitud(String claveSolicitud) {
		this.claveSolicitud = claveSolicitud;
	}
	public String getFolioProcesar() {
		return folioProcesar;
	}
	public void setFolioProcesar(String folioProcesar) {
		this.folioProcesar = folioProcesar;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getCurpTutor() {
		return curpTutor;
	}
	public void setCurpTutor(String curpTutor) {
		this.curpTutor = curpTutor;
	}

	public String getCuentaBanco() {
		return cuentaBanco;
	}
	public void setCuentaBanco(String cuentaBanco) {
		this.cuentaBanco = cuentaBanco;
	}
	
}
