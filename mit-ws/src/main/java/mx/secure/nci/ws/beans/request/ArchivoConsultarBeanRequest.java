package mx.secure.nci.ws.beans.request;

import java.util.Date;

public class ArchivoConsultarBeanRequest {

	private Long idArchivo;
	private Short idSubproceso;
	private Short idEstatusArchivo;
	private Date fechaArchivoDesde;
	private Date fechaArchivoHasta;
	private Date fechaRecepcionNCIDesde;
	private Date fechaRecepcionNCIHasta;
	private Short consecutivo;
	private String nombre;
	private Short idOrigenArchivo;
	private Short idDetalleOrigen;
	
	public Long getIdArchivo() {
		return idArchivo;
	}
	public void setIdArchivo(Long idArchivo) {
		this.idArchivo = idArchivo;
	}
	public Short getIdSubproceso() {
		return idSubproceso;
	}
	public void setIdSubproceso(Short idSubproceso) {
		this.idSubproceso = idSubproceso;
	}
	public Short getIdEstatusArchivo() {
		return idEstatusArchivo;
	}
	public void setIdEstatusArchivo(Short idEstatusArchivo) {
		this.idEstatusArchivo = idEstatusArchivo;
	}
	public Date getFechaArchivoDesde() {
		return fechaArchivoDesde;
	}
	public void setFechaArchivoDesde(Date fechaArchivoDesde) {
		this.fechaArchivoDesde = fechaArchivoDesde;
	}
	public Date getFechaArchivoHasta() {
		return fechaArchivoHasta;
	}
	public void setFechaArchivoHasta(Date fechaArchivoHasta) {
		this.fechaArchivoHasta = fechaArchivoHasta;
	}
	public Date getFechaRecepcionNCIDesde() {
		return fechaRecepcionNCIDesde;
	}
	public void setFechaRecepcionNCIDesde(Date fechaRecepcionNCIDesde) {
		this.fechaRecepcionNCIDesde = fechaRecepcionNCIDesde;
	}
	public Date getFechaRecepcionNCIHasta() {
		return fechaRecepcionNCIHasta;
	}
	public void setFechaRecepcionNCIHasta(Date fechaRecepcionNCIHasta) {
		this.fechaRecepcionNCIHasta = fechaRecepcionNCIHasta;
	}
	public Short getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(Short consecutivo) {
		this.consecutivo = consecutivo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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

}
