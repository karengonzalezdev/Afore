package idcWS;

import java.util.Date;

public class IDCMarBeanResponse {

	private String ident;
	private Long nss;
	private String curp;
	private Long cuenta;
	private String nombre;
	
	private String apMaterno;
	private String apPaterno;
	
	private Boolean diagn;
	private String subprcs;
	private Date certif;
	
	public IDCMarBeanResponse() {}
	
	public IDCMarBeanResponse(String ident, Long nss, String curp, Long cuenta, String nombre, 
			String apMaterno, String apPaterno, Boolean diagn, String subprcs, Date certif) {
		this.ident = ident;
		this.nss = nss;
		this.curp = curp;
		this.cuenta = cuenta;
		this.nombre = nombre;
		this.apMaterno = apMaterno;
		this.apPaterno = apPaterno;
		this.diagn = diagn;
		this.subprcs = subprcs;
		this.certif = certif;
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public Long getNss() {
		return nss;
	}

	public void setNss(Long nss) {
		this.nss = nss;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public Long getCuenta() {
		return cuenta;
	}

	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getDiagn() {
		return diagn;
	}

	public void setDiagn(Boolean diagn) {
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
