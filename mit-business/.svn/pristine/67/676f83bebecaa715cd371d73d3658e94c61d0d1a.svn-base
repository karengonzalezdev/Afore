package mx.profuturo.nci.business.events.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ElementoReporteGeneral implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String nombreElemento;
	private BigDecimal importe;
	private Integer registros;
	private String estatus;	

	public ElementoReporteGeneral(){}
	
	public ElementoReporteGeneral(String nombreElemento,
							BigDecimal importe,
							Integer registros,
							String estatus) 
	{
		this.nombreElemento = nombreElemento;
		this.importe = importe;
		this.registros = registros;
		this.estatus = estatus;
	}
	
	public String getNombreElemento() {
		return nombreElemento;
	}
	
	public void setNombreElemento(String nombreElemento) {
		this.nombreElemento = nombreElemento;
	}
	
	public BigDecimal getImporte() {
		return importe;
	}
	
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	public Integer getRegistros() {
		return registros;
	}
	
	public void setRegistros(Integer registros) {
		this.registros = registros;
	}
	
	public String getEstatus() {
		return estatus;
	}
	
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.appendSuper(super.toString())
				.append("nombreElemento", nombreElemento)
				.append("importe", importe)
				.append("registros", registros)
				.append("estatus", estatus).toString();
	}
}