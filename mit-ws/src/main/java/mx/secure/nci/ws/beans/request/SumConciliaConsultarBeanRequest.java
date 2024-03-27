package mx.secure.nci.ws.beans.request;

public class SumConciliaConsultarBeanRequest {
	private String folio;
	private Short idOrigen;
	private Short idEmpresa;
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public Short getIdOrigen() {
		return idOrigen;
	}
	public void setIdOrigen(Short idOrigen) {
		this.idOrigen = idOrigen;
	}
	public Short getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Short idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
}
