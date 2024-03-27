package mx.profuturo.nci.business.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;



public class FrecuenciaVO 
{
	private GenericCatalogoVO diaSemana;
	private Short dia;


	public FrecuenciaVO(){}
	

	public FrecuenciaVO(GenericCatalogoVO diaSemana, Short dia) 
	{
		super();
		this.diaSemana = diaSemana;
		this.dia = dia;
	}


	public GenericCatalogoVO getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(GenericCatalogoVO diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Short getDia() {
		return dia;
	}

	public void setDia(Short dia) {
		this.dia = dia;
	}
	
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("diaSemana", diaSemana).append("dia", dia).toString();
	}
	
	
}
