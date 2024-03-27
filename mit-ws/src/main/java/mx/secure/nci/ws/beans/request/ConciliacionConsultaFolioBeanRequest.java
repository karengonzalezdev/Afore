package mx.secure.nci.ws.beans.request;

public class ConciliacionConsultaFolioBeanRequest {
	private Short regConciliado;
	private Short movGenerado;
	private Short idOrigenAportacion;
	private Short idEstatusTramite;
	private String folioArch;
	private String folioConciliacion;
	private Short idSubEtapa;
	private Short bandera;
	
	
	public Short getBandera() {
		return bandera;
	}
	public void setBandera(Short bandera) {
		this.bandera = bandera;
	}
	public Short getIdSubEtapa() {
		return idSubEtapa;
	}
	public void setIdSubEtapa(Short idSubEtapa) {
		this.idSubEtapa = idSubEtapa;
	}
	public Short getRegConciliado() {
		return regConciliado;
	}
	public void setRegConciliado(Short regConciliado) {
		this.regConciliado = regConciliado;
	}
	public Short getMovGenerado() {
		return movGenerado;
	}
	public void setMovGenerado(Short movGenerado) {
		this.movGenerado = movGenerado;
	}
	public Short getIdOrigenAportacion() {
		return idOrigenAportacion;
	}
	public void setIdOrigenAportacion(Short idOrigenAportacion) {
		this.idOrigenAportacion = idOrigenAportacion;
	}
	public Short getIdEstatusTramite() {
		return idEstatusTramite;
	}
	public void setIdEstatusTramite(Short idEstatusTramite) {
		this.idEstatusTramite = idEstatusTramite;
	}
	public String getFolioArch() {
		return folioArch;
	}
	public void setFolioArch(String folioArch) {
		this.folioArch = folioArch;
	}
	public String getFolioConciliacion() {
		return folioConciliacion;
	}
	public void setFolioConciliacion(String folioConciliacion) {
		this.folioConciliacion = folioConciliacion;
	}
	
}
