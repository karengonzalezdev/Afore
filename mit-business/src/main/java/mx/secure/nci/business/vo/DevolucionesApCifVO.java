package mx.secure.nci.business.vo;

import java.io.Serializable;
import java.util.List;


public class DevolucionessecureCifVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String folio;
	private String tipoDevolucionsecure;
	private String transaccionId;
	private String statusCif;
	private List<String>listaEstatusCIF;
	
	
	public DevolucionessecureCifVO () {}

	public DevolucionessecureCifVO (String folio, String tipoDevolucionsecure, List<String>listaEstatusCIF) {
		this.folio = folio;
		this.tipoDevolucionsecure = tipoDevolucionsecure;
		this.listaEstatusCIF = listaEstatusCIF;
	}

	public String getFolio() {
		return folio;
	}


	public void setFolio(String folio) {
		this.folio = folio;
	}


	public String getTipoDevolucionsecure() {
		return tipoDevolucionsecure;
	}


	public void setTipoDevolucionsecure(String tipoDevolucionsecure) {
		this.tipoDevolucionsecure = tipoDevolucionsecure;
	}


	public String getTransaccionId() {
		return transaccionId;
	}


	public void setTransaccionId(String transaccionId) {
		this.transaccionId = transaccionId;
	}


	public String getStatusCif() {
		return statusCif;
	}


	public void setStatusCif(String statusCif) {
		this.statusCif = statusCif;
	}

	public List<String> getListaEstatusCIF() {
		return listaEstatusCIF;
	}

	public void setListaEstatusCIF(List<String> listaEstatusCIF) {
		this.listaEstatusCIF = listaEstatusCIF;
	}
	

	
	
	
	
	
	
	

	
}
