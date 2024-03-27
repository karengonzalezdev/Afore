package mx.secure.nci.business.vo;

import java.io.Serializable;

public class PendientesTimbradoCifVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String folio;
	private String tipoDevolucion;
	private String idTransaccionCif;

	public PendientesTimbradoCifVO() {
	}

	public PendientesTimbradoCifVO(String folio, String tipoDevolucion) {
		this.folio = folio;
		this.tipoDevolucion = tipoDevolucion;
	}

	public PendientesTimbradoCifVO(String idTransaccionCif) {
		this.idTransaccionCif = idTransaccionCif;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getTipoDevolucion() {
		return tipoDevolucion;
	}

	public void setTipoDevolucion(String tipoDevolucion) {
		this.tipoDevolucion = tipoDevolucion;
	}

	public String getIdTransaccionCif() {
		return idTransaccionCif;
	}

	public void setIdTransaccionCif(String idTransaccionCif) {
		this.idTransaccionCif = idTransaccionCif;
	}

}
