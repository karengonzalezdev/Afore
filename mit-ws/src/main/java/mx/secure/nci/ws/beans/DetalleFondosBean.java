package mx.secure.nci.ws.beans;


import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "idFondo", "fondo", "noRegistros", "importe" })
public class DetalleFondosBean extends AbstractAuditoriaBean {
	private Integer idFondo;
	private String fondo;
	private Integer noRegistros;
	private BigDecimal importe;
	
	public Integer getIdFondo() {
		return idFondo;
	}
	public void setIdFondo(Integer idFondo) {
		this.idFondo = idFondo;
	}
	public String getFondo() {
		return fondo;
	}
	public void setFondo(String fondo) {
		this.fondo = fondo;
	}
	public Integer getNoRegistros() {
		return noRegistros;
	}
	public void setNoRegistros(Integer noRegistros) {
		this.noRegistros = noRegistros;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	

}