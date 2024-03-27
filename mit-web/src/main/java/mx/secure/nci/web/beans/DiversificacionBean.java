package mx.secure.nci.web.beans;

import java.io.Serializable;
import java.util.Date;

public class DiversificacionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FondoBean fondo;
	private Double monto;
	private String porcentaje;
	private Integer porcentajeInt;
	private Date fechaCreacion;
	private String usuarioCreacion;
	private Date fechaActualizacion;
	private String usuarioActualizacion;

	public FondoBean getFondo() {
		return fondo;
	}

	public void setFondo(FondoBean fondo) {
		this.fondo = fondo;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}

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

	public Integer getPorcentajeInt() {
		return porcentajeInt;
	}

	public void setPorcentajeInt(Integer porcentajeInt) {
		this.porcentajeInt = porcentajeInt;
	}

	
}
