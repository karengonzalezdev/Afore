package mx.profuturo.nci.business.vo;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrdenesVO extends AbstractAuditoriaVO
{

	private String numeroOrden;
	private Long   numCuentaIndividual;
	private GenericCatalogoVO  estatus;
	private BigDecimal importe;
	
	private List<DiversificacionOrdenesVO> diversificacionesOrdenes;
	private ConciliacionVO conciliacionVO;

	

	
	public String getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public Long getNumCuentaIndividual() {
		return numCuentaIndividual;
	}

	public void setNumCuentaIndividual(Long numCuentaIndividual) {
		this.numCuentaIndividual = numCuentaIndividual;
	}

	public GenericCatalogoVO getEstatus() {
		return estatus;
	}

	public void setEstatus(GenericCatalogoVO estatus) {
		this.estatus = estatus;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public List<DiversificacionOrdenesVO> getDiversificacionesOrdenes() {
		return diversificacionesOrdenes;
	}

	public void setDiversificacionesOrdenes(
			List<DiversificacionOrdenesVO> diversificacionesOrdenes) {
		this.diversificacionesOrdenes = diversificacionesOrdenes;
	}
	
	public ConciliacionVO getConciliacionVO() {
		return conciliacionVO;
	}

	public void setConciliacionVO(ConciliacionVO conciliacionVO) {
		this.conciliacionVO = conciliacionVO;
	}

	@Override
	  public String toString() 
	{
	    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)	    	
	    	.append("numeroOrden", numeroOrden)
	        .append("numCuentaIndividual", numCuentaIndividual)
	        .append("estatus", estatus)
	        .append("importe", importe)
	        .append("diversificacionesOrdenes", diversificacionesOrdenes)
	        .appendSuper(super.toString())	        	
	        .toString();
	  }		
	
	
}
