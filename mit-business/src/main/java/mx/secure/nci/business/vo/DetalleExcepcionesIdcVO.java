package mx.secure.nci.business.vo;

import java.io.Serializable;
import java.util.Date;

public class DetalleExcepcionesIdcVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String folio;
	private Integer numConsecutivo;
	private String nss;
	private String curp;
	private String cuenta;
	private String nombreCompleto;
	private String diagn;
	private String subprcs;
	private Date certif;
	
	public DetalleExcepcionesIdcVO() {}
	

	public DetalleExcepcionesIdcVO(String folio) {
		this.folio = folio;
	}

	public DetalleExcepcionesIdcVO(String folio, Integer numConsecutivo, String nss, String curp, String cuenta,
			String nombreCompleto, String diagn, String subprcs, Date certif) {
		this.folio = folio;
		this.numConsecutivo = numConsecutivo;
		this.nss = nss;
		this.curp = curp;
		this.cuenta = cuenta;
		this.nombreCompleto = nombreCompleto;
		this.diagn = diagn;
		this.subprcs = subprcs;
		this.certif = certif;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public Integer getNumConsecutivo() {
		return numConsecutivo;
	}

	public void setNumConsecutivo(Integer numConsecutivo) {
		this.numConsecutivo = numConsecutivo;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getDiagn() {
		return diagn;
	}

	public void setDiagn(String diagn) {
		this.diagn = diagn;
	}

	public String getSubprcs() {
		return subprcs;
	}

	public void setSubprcs(String subprcs) {
		this.subprcs = subprcs;
	}

	public Date getCertif() {
		return certif;
	}

	public void setCertif(Date certif) {
		this.certif = certif;
	}
}
