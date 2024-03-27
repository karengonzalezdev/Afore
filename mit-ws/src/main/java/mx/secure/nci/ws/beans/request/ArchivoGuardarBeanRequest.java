package mx.secure.nci.ws.beans.request;

import java.util.Date;

public class ArchivoGuardarBeanRequest {
	
	private Date    fechaRecepcionsecure;
	private Date    fechaRecepcionNci;
	private String  nombre;
	private Short   idEstatusArchivo;
	private Short   idProceso;
	private Short   idSubproceso;
	private Date    fechaGeneracion;
	private Long    tamanoArchivo;
	private Long    idFilenet;
	private String  usuarioCreacion;
	private Date    fechaArchivo;
	private Short   idOrigenArchivo;
	private Short   idDetalleOrigen;
	private Date    fechaInicioValidacion;
	private Date    fechaFinValidacion;
	public Date getFechaRecepcionsecure() {
		return fechaRecepcionsecure;
	}
	public void setFechaRecepcionsecure(Date fechaRecepcionsecure) {
		this.fechaRecepcionsecure = fechaRecepcionsecure;
	}
	public Date getFechaRecepcionNci() {
		return fechaRecepcionNci;
	}
	public void setFechaRecepcionNci(Date fechaRecepcionNci) {
		this.fechaRecepcionNci = fechaRecepcionNci;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Short getIdEstatusArchivo() {
		return idEstatusArchivo;
	}
	public void setIdEstatusArchivo(Short idEstatusArchivo) {
		this.idEstatusArchivo = idEstatusArchivo;
	}
	public Short getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(Short idProceso) {
		this.idProceso = idProceso;
	}
	public Short getIdSubproceso() {
		return idSubproceso;
	}
	public void setIdSubproceso(Short idSubproceso) {
		this.idSubproceso = idSubproceso;
	}
	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}
	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}
	public Long getTamanoArchivo() {
		return tamanoArchivo;
	}
	public void setTamanoArchivo(Long tamanoArchivo) {
		this.tamanoArchivo = tamanoArchivo;
	}
	public Long getIdFilenet() {
		return idFilenet;
	}
	public void setIdFilenet(Long idFilenet) {
		this.idFilenet = idFilenet;
	}
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Date getFechaArchivo() {
		return fechaArchivo;
	}
	public void setFechaArchivo(Date fechaArchivo) {
		this.fechaArchivo = fechaArchivo;
	}
	public Short getIdOrigenArchivo() {
		return idOrigenArchivo;
	}
	public void setIdOrigenArchivo(Short idOrigenArchivo) {
		this.idOrigenArchivo = idOrigenArchivo;
	}
	public Short getIdDetalleOrigen() {
		return idDetalleOrigen;
	}
	public void setIdDetalleOrigen(Short idDetalleOrigen) {
		this.idDetalleOrigen = idDetalleOrigen;
	}
	public Date getFechaInicioValidacion() {
		return fechaInicioValidacion;
	}
	public void setFechaInicioValidacion(Date fechaInicioValidacion) {
		this.fechaInicioValidacion = fechaInicioValidacion;
	}
	public Date getFechaFinValidacion() {
		return fechaFinValidacion;
	}
	public void setFechaFinValidacion(Date fechaFinValidacion) {
		this.fechaFinValidacion = fechaFinValidacion;
	}
	
	

}
