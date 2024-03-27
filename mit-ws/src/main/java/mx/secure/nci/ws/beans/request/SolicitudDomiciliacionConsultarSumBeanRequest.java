package mx.secure.nci.ws.beans.request;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class SolicitudDomiciliacionConsultarSumBeanRequest {
	
	private Long numCuentaIndividual;
	private List<Short> estatusSolicitudes;
	private String folio;
	private String claveSolicitud;
	private String cuentaBanco;
	
	@XmlElementWrapper(name = "estatusSolicitudes")
	@XmlElement(name = "estatusSolicitud")
	public List<Short> getEstatusSolicitudes() {
		return estatusSolicitudes;
	}

	public Long getNumCuentaIndividual() {
		return numCuentaIndividual;
	}

	public void setNumCuentaIndividual(Long numCuentaIndividual) {
		this.numCuentaIndividual = numCuentaIndividual;
	}

	public String getClaveSolicitud() {
		return claveSolicitud;
	}

	public void setClaveSolicitud(String claveSolicitud) {
		this.claveSolicitud = claveSolicitud;
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

	public String getCuentaBanco() {
		return cuentaBanco;
	}

	public void setCuentaBanco(String cuentaBanco) {
		this.cuentaBanco = cuentaBanco;
	}

	
	
}
