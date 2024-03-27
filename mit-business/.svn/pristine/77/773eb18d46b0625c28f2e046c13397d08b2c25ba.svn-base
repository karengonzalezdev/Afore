package mx.profuturo.nci.business.events.report;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ElementoReporteGeneralSBC {

	private static final long serialVersionUID = 1L;
	
	private Date fecha;
	private BigDecimal importe;
	private String archivoOrigen;
	private String banco;
	private String estatus;
	
	public ElementoReporteGeneralSBC(){}
	
	public ElementoReporteGeneralSBC(Date fecha,
						BigDecimal importe,
						String archivoOrigen,
						String banco,
						String estatus)
	{
		this.fecha = fecha;
		this.importe = importe;
		this.archivoOrigen = archivoOrigen;
		this.banco = banco;
		this.estatus = estatus;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	
	}
	
	public BigDecimal getImporte() {
		return importe;
	
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	public String getArchivoOrigen() {
		return archivoOrigen;
	}
	
	public void setArchivoOrigen(String archivoOrigen) {
		this.archivoOrigen = archivoOrigen;
	}
	
	public String getBanco() {
		return banco;
	}
	
	public void setBanco(String banco) {
		this.banco = banco;
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
		.append("fecha", fecha)
		.append("importe", importe)
		.append("archivoOrigen", archivoOrigen)
		.append("banco", banco)
		.append("estatus", estatus).toString();
	};
}

