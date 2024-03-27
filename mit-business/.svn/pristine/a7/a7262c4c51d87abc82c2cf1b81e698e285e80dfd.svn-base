package mx.profuturo.nci.business.model.parameter;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SaldosHistoricosParameter implements Serializable
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;



	private Date fecha;
	private Long numCtaInvdual;
	


	public Date getFecha() {
		return fecha;
	}
	public void setFecha(final Date fecha) {
		this.fecha = fecha;
	}
	public Long getNumCtaInvdual() {
		return numCtaInvdual;
	}
	public void setNumCtaInvdual(final Long numCtaInvdual) {
		this.numCtaInvdual = numCtaInvdual;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
				.appendSuper(super.toString()).append("fecha", fecha)
				.append("numCtaInvdual", numCtaInvdual).toString();
	}








}
