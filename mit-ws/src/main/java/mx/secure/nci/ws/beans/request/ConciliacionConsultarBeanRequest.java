package mx.secure.nci.ws.beans.request;

public class ConciliacionConsultarBeanRequest {
	private String folio;
	private Long numCuentaIndividual;
	private Short idOrigenAportacion;
	private Short registroConciliado;
	private Short idEstatusTramite;
	private String claveSolicitud;
	
	//Afore movil
	private String curp;
	private String tipoAhorrador;	
	private Short movimientoGenerado;
	
	public Long getNumCuentaIndividual() {
		return numCuentaIndividual;
	}
	public void setNumCuentaIndividual(Long numCuentaIndividual) {
		this.numCuentaIndividual = numCuentaIndividual;
	}
	public Short getRegistroConciliado() {
		return registroConciliado;
	}
	public void setRegistroConciliado(Short registroConciliado) {
		this.registroConciliado = registroConciliado;
	}
	public Short getIdOrigenAportacion() {
		return idOrigenAportacion;
	}
	public void setIdOrigenAportacion(Short idOrigenAportacion) {
		this.idOrigenAportacion = idOrigenAportacion;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public Short getIdEstatusTramite() {
		return idEstatusTramite;
	}
	public void setIdEstatusTramite(Short idEstatusTramite) {
		this.idEstatusTramite = idEstatusTramite;
	}
	public String getClaveSolicitud() {
		return claveSolicitud;
	}
	public void setClaveSolicitud(String claveSolicitud) {
		this.claveSolicitud = claveSolicitud;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getTipoAhorrador() {
		return tipoAhorrador;
	}
	public void setTipoAhorrador(String tipoAhorrador) {
		this.tipoAhorrador = tipoAhorrador;
	}
	public Short getMovimientoGenerado() {
		return movimientoGenerado;
	}
	public void setMovimientoGenerado(Short movimientoGenerado) {
		this.movimientoGenerado = movimientoGenerado;
	}
	
}