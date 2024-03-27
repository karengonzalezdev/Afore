package mx.secure.nci.ws.beans.request;

public class CifCtrlCargoAbonoReclasifBeanRequest {

	private String folioReclasificacion;
	private Integer tipoMov;

	public String getFolioReclasificacion() {
		return folioReclasificacion;
	}

	public void setFolioReclasificacion(String folioReclasificacion) {
		this.folioReclasificacion = folioReclasificacion;
	}

	public Integer getTipoMov() {
		return tipoMov;
	}

	public void setTipoMov(Integer tipoMov) {
		this.tipoMov = tipoMov;
	}
}
