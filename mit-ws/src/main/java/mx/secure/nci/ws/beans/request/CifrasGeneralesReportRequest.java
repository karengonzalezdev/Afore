package mx.secure.nci.ws.beans.request;

public class CifrasGeneralesReportRequest {
	private Short idOrigen;
	private Short idTipo;
	private String folio;
	public Short getIdOrigen() {
		return idOrigen;
	}
	public void setIdOrigen(Short idOrigen) {
		this.idOrigen = idOrigen;
	}
	public Short getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(Short idTipo) {
		this.idTipo = idTipo;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}	
}