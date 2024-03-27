package mx.secure.nci.ws.beans.request;

public class CifrasGeneralesPorTipoBeanRequest {
	
	private Short idTipoCuenta ;
	private String folioConciliacion;

	public Short getIdTipoCuenta() {
		return idTipoCuenta;
	}

	public void setIdTipoCuenta(Short idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}

	public String getFolioConciliacion() {
		return folioConciliacion;
	}

	public void setFolioConciliacion(String folioConciliacion) {
		this.folioConciliacion = folioConciliacion;
	}
	
	

}
