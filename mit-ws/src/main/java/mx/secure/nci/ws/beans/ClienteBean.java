package mx.secure.nci.ws.beans;

import java.math.BigInteger;
import java.util.Date;

public class ClienteBean {
	String nombreCte;
	String apPaternoCte;
	String apMaternoCte;
	String nss;
	Long numCtaInvdual;
	String curp;
	String rfcCte;
	String correoElec;
	BigInteger celular;
	String usuCre;
	Date fehCre;
	String usuAct;
	Date fehAct;

	public String getNombreCte() {
		return nombreCte;
	}

	public void setNombreCte(String nombreCte) {
		this.nombreCte = nombreCte;
	}

	public String getApPaternoCte() {
		return apPaternoCte;
	}

	public void setApPaternoCte(String apPaternoCte) {
		this.apPaternoCte = apPaternoCte;
	}

	public String getApMaternoCte() {
		return apMaternoCte;
	}

	public void setApMaternoCte(String apMaternoCte) {
		this.apMaternoCte = apMaternoCte;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public Long getNumCtaInvdual() {
		return numCtaInvdual;
	}

	public void setNumCtaInvdual(Long numCtaInvdual) {
		this.numCtaInvdual = numCtaInvdual;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getRfcCte() {
		return rfcCte;
	}

	public void setRfcCte(String rfcCte) {
		this.rfcCte = rfcCte;
	}

	public String getCorreoElec() {
		return correoElec;
	}

	public void setCorreoElec(String correoElec) {
		this.correoElec = correoElec;
	}

	public BigInteger getCelular() {
		return celular;
	}

	public void setCelular(BigInteger celular) {
		this.celular = celular;
	}

	public String getUsuCre() {
		return usuCre;
	}

	public void setUsuCre(String usuCre) {
		this.usuCre = usuCre;
	}

	public Date getFehCre() {
		return fehCre;
	}

	public void setFehCre(Date fehCre) {
		this.fehCre = fehCre;
	}

	public String getUsuAct() {
		return usuAct;
	}

	public void setUsuAct(String usuAct) {
		this.usuAct = usuAct;
	}

	public Date getFehAct() {
		return fehAct;
	}

	public void setFehAct(Date fehAct) {
		this.fehAct = fehAct;
	}

}
