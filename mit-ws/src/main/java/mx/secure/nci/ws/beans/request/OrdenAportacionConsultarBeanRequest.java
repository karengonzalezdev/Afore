package mx.secure.nci.ws.beans.request;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class OrdenAportacionConsultarBeanRequest 
{
	private String numeroOrden;
	private Long   numCuentaIndividual;
    private List<Short> estatusOrdenes;
    
    @XmlElementWrapper(name="estatusOrdenes")
    @XmlElement(name="estatus")
	public List<Short> getEstatusOrdenes() {
		return estatusOrdenes;
	}
    
    
    
	public String getNumeroOrden() {
		return numeroOrden;
	}
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}
	public Long getNumCuentaIndividual() {
		return numCuentaIndividual;
	}
	public void setNumCuentaIndividual(Long numCuentaIndividual) {
		this.numCuentaIndividual = numCuentaIndividual;
	}

	public void setEstatusOrdenes(List<Short> estatusOrdenes) {
		this.estatusOrdenes = estatusOrdenes;
	}
    
    
    
    
	

}
