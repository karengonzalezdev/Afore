package mx.secure.nci.ws.beans;

import java.util.Date;

public class ArchivoBean extends AbstractAuditoriaBean 
{
	private Long idArchivo;
	private Date fechaRecepcionsecure;
	private Date fechaRecepcionNci;
	private String nombre;
	private Date fechaActualizacion;
	private GenericoCatalogoBean estatusArchivo;
	private GenericoCatalogoBean proceso;
	private GenericoCatalogoBean subproceso;
	private Date fechaGen;
	private String tamanoArchivo;
	private Long idFilenet;
	private Date fechaArchivo;
	private GenericoCatalogoBean origenArchivo;
	private GenericoCatalogoBean detalleOrigen;
	private String folioArchivo;
	private String usuarioCreacion;
	private Date fechaInicioValidacion;
	private Date fechaFinValidacion;
	private String usuarioActualiza;
	
	
	public Long getIdArchivo() {
		return idArchivo;
	}
	public void setIdArchivo(Long idArchivo) {
		this.idArchivo = idArchivo;
	}
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
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public GenericoCatalogoBean getEstatusArchivo() {
		return estatusArchivo;
	}
	public void setEstatusArchivo(GenericoCatalogoBean estatusArchivo) {
		this.estatusArchivo = estatusArchivo;
	}
	public GenericoCatalogoBean getProceso() {
		return proceso;
	}
	public void setProceso(GenericoCatalogoBean proceso) {
		this.proceso = proceso;
	}
	public GenericoCatalogoBean getSubproceso() {
		return subproceso;
	}
	public void setSubproceso(GenericoCatalogoBean subproceso) {
		this.subproceso = subproceso;
	}
	public Date getFechaGen() {
		return fechaGen;
	}
	public void setFechaGen(Date fechaGen) {
		this.fechaGen = fechaGen;
	}
	public String getTamanoArchivo() {
		return tamanoArchivo;
	}
	public void setTamanoArchivo(String tamanoArchivo) {
		this.tamanoArchivo = tamanoArchivo;
	}
	public Long getIdFilenet() {
		return idFilenet;
	}
	public void setIdFilenet(Long idFilenet) {
		this.idFilenet = idFilenet;
	}
	public Date getFechaArchivo() {
		return fechaArchivo;
	}
	public void setFechaArchivo(Date fechaArchivo) {
		this.fechaArchivo = fechaArchivo;
	}
	public GenericoCatalogoBean getOrigenArchivo() {
		return origenArchivo;
	}
	public void setOrigenArchivo(GenericoCatalogoBean origenArchivo) {
		this.origenArchivo = origenArchivo;
	}
	public GenericoCatalogoBean getDetalleOrigen() {
		return detalleOrigen;
	}
	public void setDetalleOrigen(GenericoCatalogoBean detalleOrigen) {
		this.detalleOrigen = detalleOrigen;
	}
	public String getFolioArchivo() {
		return folioArchivo;
	}
	public void setFolioArchivo(String folioArchivo) {
		this.folioArchivo = folioArchivo;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
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
	public String getUsuarioActualiza() {
		return usuarioActualiza;
	}
	public void setUsuarioActualiza(String usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}
	
	
}
