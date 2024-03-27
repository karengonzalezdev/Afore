package mx.secure.nci.business.vo;

import java.io.Serializable;
import java.util.List;

public class ActualizaEstatusIdcVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String folio;
	private List<String> clientesCurp;	

	
	public ActualizaEstatusIdcVO(String folio, List<String> clientesDescartados) {
		this.folio=folio;
		this.clientesCurp = clientesDescartados;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public List<String> getClientesCurp() {
		return clientesCurp;
	}
	public void setClientesCurp(List<String> clientesCurp) {
		this.clientesCurp = clientesCurp;
	}

	
}
