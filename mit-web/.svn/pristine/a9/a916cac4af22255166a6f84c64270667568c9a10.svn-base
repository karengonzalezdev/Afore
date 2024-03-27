package mx.profuturo.nci.web.beans;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class ConfigSubprocesoOrigenBean implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private ConfiguracionSubprocesoBean   subproceso;
	private GenericCatalogoBean           origenArchivo;
	private GenericCatalogoBean           detalleOrigen;
	private GenericCatalogoBean           archivoMovBancario;
	private String 						  mascaraArchivo;
    private Short 						  indiceFecha;
    private String 						  formatoFecha;
    private Boolean 					  vigencia;
    
    
    
	public ConfiguracionSubprocesoBean getSubproceso() {
		return subproceso;
	}
	public void setSubproceso(ConfiguracionSubprocesoBean subproceso) {
		this.subproceso = subproceso;
	}
	public GenericCatalogoBean getOrigenArchivo() {
		return origenArchivo;
	}
	public void setOrigenArchivo(GenericCatalogoBean origenArchivo) {
		this.origenArchivo = origenArchivo;
	}
	public GenericCatalogoBean getDetalleOrigen() {
		return detalleOrigen;
	}
	public void setDetalleOrigen(GenericCatalogoBean detalleOrigen) {
		this.detalleOrigen = detalleOrigen;
	}
	public GenericCatalogoBean getArchivoMovBancario() {
		return archivoMovBancario;
	}
	public void setArchivoMovBancario(GenericCatalogoBean archivoMovBancario) {
		this.archivoMovBancario = archivoMovBancario;
	}
	public String getMascaraArchivo() {
		return mascaraArchivo;
	}
	public void setMascaraArchivo(String mascaraArchivo) {
		this.mascaraArchivo = mascaraArchivo;
	}
	public Short getIndiceFecha() {
		return indiceFecha;
	}
	public void setIndiceFecha(Short indiceFecha) {
		this.indiceFecha = indiceFecha;
	}
	public String getFormatoFecha() {
		return formatoFecha;
	}
	public void setFormatoFecha(String formatoFecha) {
		this.formatoFecha = formatoFecha;
	}
	public Boolean getVigencia() {
		return vigencia;
	}
	public void setVigencia(Boolean vigencia) {
		this.vigencia = vigencia;
	}
    

    
	@Override
	  public String toString() 
	{
	    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)	    	
	    	.append("subproceso", subproceso)
	        .append("origenArchivo", origenArchivo)
	        .append("detalleOrigen", detalleOrigen)
	        .append("archivoMovBancario", archivoMovBancario)
	        .append("mascaraArchivo", mascaraArchivo)
	        .append("indiceFecha", indiceFecha)
	        .append("formatoFecha", formatoFecha)
	        .append("vigencia", vigencia)
	        .appendSuper(super.toString())	        	
	        .toString();
	  }
    
    

}