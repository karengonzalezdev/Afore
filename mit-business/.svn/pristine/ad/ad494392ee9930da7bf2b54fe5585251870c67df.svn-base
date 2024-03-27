package mx.profuturo.nci.business.vo;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DiversificacionConciliacionVO extends AbstractAuditoriaVO 
{
	private Integer idConciliacion;
	private GenericCatalogoVO fondoApovol;
	private BigDecimal monto;
	private Short porcentaje;

	public Integer getIdConciliacion() {
		return idConciliacion;
	}
	public void setIdConciliacion(Integer idConciliacion) {
		this.idConciliacion = idConciliacion;
	}

	public GenericCatalogoVO getFondoApovol() {
		return fondoApovol;
	}
	public void setFondoApovol(GenericCatalogoVO fondoApovol) {
		this.fondoApovol = fondoApovol;
	}

	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Short getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Short porcentaje) {
		this.porcentaje = porcentaje;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)				
				.append("idConciliacion", idConciliacion)
				.append("fondoApovol.id", (fondoApovol!=null?fondoApovol.getId():null))
				.append("monto", monto).append("porcentaje", porcentaje)
				.appendSuper(super.toString())
				.toString();
	}

}
