package mx.profuturo.nci.web.beans;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class ConfiguracionSubprocesoBean implements Serializable
{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private SubprocesoBean 			subproceso;
    private ProcesoBean 			proceso;
    private GenericCatalogoBean 	saldoOpera;
    private String 					mascaraArchivo;
    private String 					rutaFilesystem;
    
    private Date fechaCreacion;
    private String usuarioCreacion;
    private Date fechaActualizacion;
    private String usuarioActualizacion;
    
	private boolean convivencia;
    
    
    
    
	public Date getFechaCreacion() {
		return fechaCreacion;
	}






	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}






	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}






	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}






	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}






	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}






	public String getUsuarioActualizacion() {
		return usuarioActualizacion;
	}






	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}






	public boolean isConvivencia() {
		return convivencia;
	}






	public void setConvivencia(boolean convivencia) {
		this.convivencia = convivencia;
	}
	
	public boolean getConvivencia() {
		return this.convivencia;
	}






	public SubprocesoBean getSubproceso() {
		return subproceso;
	}






	public void setSubproceso(SubprocesoBean subproceso) {
		this.subproceso = subproceso;
	}






	public ProcesoBean getProceso() {
		return proceso;
	}






	public void setProceso(ProcesoBean proceso) {
		this.proceso = proceso;
	}






	public GenericCatalogoBean getSaldoOpera() {
		return saldoOpera;
	}






	public void setSaldoOpera(GenericCatalogoBean saldoOpera) {
		this.saldoOpera = saldoOpera;
	}






	public String getMascaraArchivo() {
		return mascaraArchivo;
	}






	public void setMascaraArchivo(String mascaraArchivo) {
		this.mascaraArchivo = mascaraArchivo;
	}






	public String getRutaFilesystem() {
		return rutaFilesystem;
	}






	public void setRutaFilesystem(String rutaFilesystem) {
		this.rutaFilesystem = rutaFilesystem;
	}






	@Override
	  public String toString() 
	{
	    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)	    	
	    	.append("subproceso", subproceso)
	        .append("proceso", proceso)
	        .append("saldoOpera", saldoOpera)
	        .append("mascaraArchivo", mascaraArchivo)
	        .append("rutaFilesystem", rutaFilesystem)
	        .appendSuper(super.toString())	        	
	        .toString();
	  }
    
    

}
