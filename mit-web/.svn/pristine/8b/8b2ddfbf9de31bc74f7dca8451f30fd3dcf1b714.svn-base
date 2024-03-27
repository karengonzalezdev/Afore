package mx.profuturo.nci.web.beans;

import java.util.Date;

import mx.profuturo.nci.business.util.UtilMethod;

public class ArchivoDomiViewParamBean {
	
	private Date fechaInicio;
	private Date fechaFin;
	private Short idOrigenDomiciliacion;
	private String usuario;
	/**
	 * {@value index} 
	 * 
	 * registros[0]: Banamex, 
	 * registros[1]:Bancomer, 
	 * registros[2]:Otros
	 *  
	 */
	private ArchivoDomiParamRegistry[] registros;
	
	
	
	{
		fechaInicio=UtilMethod.truncateHour(new Date(System.currentTimeMillis()));
		fechaFin=UtilMethod.truncateHour(new Date(System.currentTimeMillis()));
		registros = new ArchivoDomiParamRegistry[]{
					new ArchivoDomiParamRegistry(),
					new ArchivoDomiParamRegistry(),
					new ArchivoDomiParamRegistry()};
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin(){
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public ArchivoDomiParamRegistry[] getRegistros() {
		return registros;
	}

	public Short getIdOrigenDomiciliacion() {
		return idOrigenDomiciliacion;
	}

	public void setIdOrigenDomiciliacion(Short idOrigenDomiciliacion) {
		this.idOrigenDomiciliacion = idOrigenDomiciliacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setRegistros(ArchivoDomiParamRegistry[] registros) {
		this.registros = registros;
	}

	
	
}
