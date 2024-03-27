package mx.secure.nci.ws.beans;

import java.math.BigDecimal;

public class DiversificacionConciliacionBean 
{
	
	private GenericoCatalogoBean fondosecure;
	private BigDecimal monto;
	private Short porcentaje;
	
	
	public GenericoCatalogoBean getFondosecure() {
		return fondosecure;
	}
	public void setFondosecure(GenericoCatalogoBean fondosecure) {
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
	
	
	



}
