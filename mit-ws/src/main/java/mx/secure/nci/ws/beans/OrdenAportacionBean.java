package mx.secure.nci.ws.beans;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class OrdenAportacionBean 
{
	
	private String numeroOrden;
	private Long   numCuentaIndividual;
	private GenericoCatalogoBean  estatus;
	private BigDecimal importe;	
	private List<DiversificacionOrdenAportacionBean> diversificaciones;
	private String usuario;
	
	@XmlElementWrapper(name="diversificaciones")
	@XmlElement(name="diversificacion")
	public List<DiversificacionOrdenAportacionBean> getDiversificaciones() 
	{
		return diversificaciones;
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
	public GenericoCatalogoBean getEstatus() {
		return estatus;
	}
	public void setEstatus(GenericoCatalogoBean estatus) {
		this.estatus = estatus;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}	
	public void setDiversificaciones(
			List<DiversificacionOrdenAportacionBean> diversificaciones) {
		this.diversificaciones = diversificaciones;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	

}
