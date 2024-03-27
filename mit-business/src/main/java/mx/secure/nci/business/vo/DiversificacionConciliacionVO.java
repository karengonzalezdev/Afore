package mx.secure.nci.business.vo;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DiversificacionConciliacionVO extends AbstractAuditoriaVO 
{
	private Integer idConciliacion;
	private GenericCatalogoVO fondosecure;
	private BigDecimal monto;
	private Short porcentaje;

	public Integer getIdConciliacion() {
		return idConciliacion;
	}
	public void setIdConciliacion(Integer idConciliacion) {
		this.idConciliacion = idConciliacion;
	}

	public GenericCatalogoVO getFondosecure() {
		return fondosecure;
	}
	public void setFondosecure(GenericCatalogoVO fondosecure) {
		this.fondosecure = fondosecure;
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
				.append("fondosecure.id", (fondosecure!=null?fondosecure.getId():null))
				.append("monto", monto).append("porcentaje", porcentaje)
				.appendSuper(super.toString())
				.toString();
	}

}
