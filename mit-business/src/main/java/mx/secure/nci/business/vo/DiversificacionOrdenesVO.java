package mx.secure.nci.business.vo;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DiversificacionOrdenesVO extends AbstractAuditoriaVO 
{

	 private String numeroOrden;
	 private GenericCatalogoVO fondosecure;
	 private BigDecimal monto;
	 private Short porcentaje;
	 private Integer idFondosecure;
	 
	 	 
		
	public String getNumeroOrden() {
		return numeroOrden;
	}








	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
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






		public Integer getIdFondosecure() {
		return idFondosecure;
	}








	public void setIdFondosecure(Integer idFondosecure) {
		this.idFondosecure = idFondosecure;
	}








		@Override
		  public String toString() 
		{
		    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)	    	
		    	.append("numeroOrden", numeroOrden)
		        .append("fondosecure", fondosecure)
		        .append("monto", monto)
		        .append("porcentaje", porcentaje)
		        .append("idFondosecure",idFondosecure)
		        .appendSuper(super.toString())	        	
		        .toString();
		  }		
	 
	 

}
