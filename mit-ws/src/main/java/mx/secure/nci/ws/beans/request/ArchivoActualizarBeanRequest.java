package mx.secure.nci.ws.beans.request;

import java.util.Date;

public class ArchivoActualizarBeanRequest {
	private Long idArchivo;
	private Short idEstatusArchivo;
	private String folio;
	private Date fechaFinval;
	private String usuarioActualiza;
	private Long tamanoArchivo;


	public Long  getTamanoArchivo() {
		return tamanoArchivo;
	}

	public void setTamanoArchivo(Long  tamanoArchivo) {
		this.tamanoArchivo = tamanoArchivo;
	}

	public String getUsuarioActualiza() {
		return usuarioActualiza;
	}

	public void setUsuarioActualiza(String usuarioActualiza) {
		this.usuarioActualiza = usuarioActualiza;
	}

	public Long getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(Long idArchivo) {
		this.idArchivo = idArchivo;
	}

	public Short getIdEstatusArchivo() {
		return idEstatusArchivo;
	}

	public void setIdEstatusArchivo(Short idEstatusArchivo) {
		this.idEstatusArchivo = idEstatusArchivo;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public Date getFechaFinval() {
		return fechaFinval;
	}

	public void setFechaFinval(Date fechaFinval) {
		this.fechaFinval = fechaFinval;
	}

}
