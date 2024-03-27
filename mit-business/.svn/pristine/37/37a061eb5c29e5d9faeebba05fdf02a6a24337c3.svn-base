package mx.profuturo.nci.business.events.report.transaccion;

import java.io.Serializable;
import java.util.List;

import mx.profuturo.nci.business.events.RequestReadEvent;
import mx.profuturo.nci.business.events.report.AportacionesVoluntariasBancarias;
import mx.profuturo.nci.business.events.report.AportacionesVoluntariasOrigenReport;
import mx.profuturo.nci.business.events.report.AportacionesVoluntariasSBC;
import mx.profuturo.nci.business.events.report.ElementoReporteGeneralPorTipo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CifrasGeneralesReportRequestReadEvent extends RequestReadEvent
		implements Serializable {

	/**
	 * Serial generado por Eclipse.
	 */
	private static final long serialVersionUID = -6598345601231189210L;

	private List<AportacionesVoluntariasOrigenReport>     	origen;
	private List<ElementoReporteGeneralPorTipo> 		 	tipoCp;
	private List<ElementoReporteGeneralPorTipo> 		 	tipoLp;
	private List<AportacionesVoluntariasBancarias>     		bancarios;
	private List<AportacionesVoluntariasSBC>				sbc;
	
	private List<AportacionesVoluntariasBancarias>     		totSbc;
	
	public List<AportacionesVoluntariasOrigenReport> getOrigen() {
		return origen;
	}

	public void setOrigen(List<AportacionesVoluntariasOrigenReport> origen) {
		this.origen = origen;
	}	

	public List<ElementoReporteGeneralPorTipo> getTipoCp() {
		return tipoCp;
	}

	public void setTipoCp(List<ElementoReporteGeneralPorTipo> tipoCp) {
		this.tipoCp = tipoCp;
	}

	public List<ElementoReporteGeneralPorTipo> getTipoLp() {
		return tipoLp;
	}

	public void setTipoLp(List<ElementoReporteGeneralPorTipo> tipoLp) {
		this.tipoLp = tipoLp;
	}

	public List<AportacionesVoluntariasBancarias> getBancarios() {
		return bancarios;
	}

	public void setBancarios(List<AportacionesVoluntariasBancarias> bancarios) {
		this.bancarios = bancarios;
	}

	public List<AportacionesVoluntariasSBC> getSbc() {
		return sbc;
	}
	
	public void setSbc(List<AportacionesVoluntariasSBC> sbc) {
		this.sbc = sbc;
	}

	public List<AportacionesVoluntariasBancarias> getTotSbc() {
		return totSbc;
	}

	public void setTotSbc(List<AportacionesVoluntariasBancarias> totSbc) {
		this.totSbc = totSbc;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
			.appendSuper(super.toString())
			.append("origen", origen)
			.append("tipoCp", tipoCp)
			.append("tipoLp", tipoLp)
			.append("bancarios", bancarios)
			.append("sbc", sbc)
			.append("totSbc",totSbc).toString();
	}
}