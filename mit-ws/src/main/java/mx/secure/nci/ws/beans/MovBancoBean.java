package mx.secure.nci.ws.beans;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class MovBancoBean {
	private Long idBanco;
	private String folio;
	private List<FolioBancoBean> folioRelacionado;
	private Long referenciaCuenta;
	private String autorizacionBanco;
	private GenericoCatalogoBean refSubcuentasecure;
	private Double importe;
	private GenericoCatalogoBean claveBanco;
	private Date fechaCarga;
	private GenericoCatalogoBean origenAportacion;
	private GenericoCatalogoBean indSalvoBuenCobro;
	private Long numCuentaIndividual;
	private String curp;
	private GenericoCatalogoBean estatusIDC;
	private Short regConciliado;
	private String usuario;
	
	
	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@XmlElementWrapper(name = "foliosRelacionados")
	@XmlElement(name = "folioRelacionado")
	public List<FolioBancoBean> getFolioRelacionado() {
		return folioRelacionado;
	}

	public void setFolioRelacionado(List<FolioBancoBean> folioRelacionado) {
		this.folioRelacionado = folioRelacionado;
	}

	public Long getReferenciaCuenta() {
		return referenciaCuenta;
	}

	public void setReferenciaCuenta(Long referenciaCuenta) {
		this.referenciaCuenta = referenciaCuenta;
	}

	public String getAutorizacionBanco() {
		return autorizacionBanco;
	}

	public void setAutorizacionBanco(String autorizacionBanco) {
		this.autorizacionBanco = autorizacionBanco;
	}

	public GenericoCatalogoBean getRefSubcuentasecure() {
		return refSubcuentasecure;
	}

	public void setRefSubcuentasecure(GenericoCatalogoBean refSubcuentasecure) {
		this.refSubcuentasecure = refSubcuentasecure;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public GenericoCatalogoBean getClaveBanco() {
		return claveBanco;
	}

	public void setClaveBanco(GenericoCatalogoBean claveBanco) {
		this.claveBanco = claveBanco;
	}

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	
	public GenericoCatalogoBean getOrigenAportacion() {
		return origenAportacion;
	}

	public void setOrigenAportacion(GenericoCatalogoBean origenAportacion) {
		this.origenAportacion = origenAportacion;
	}

	public GenericoCatalogoBean getIndSalvoBuenCobro() {
		return indSalvoBuenCobro;
	}

	public void setIndSalvoBuenCobro(GenericoCatalogoBean indSalvoBuenCobro) {
		this.indSalvoBuenCobro = indSalvoBuenCobro;
	}

	public Long getNumCuentaIndividual() {
		return numCuentaIndividual;
	}

	public void setNumCuentaIndividual(Long numCuentaIndividual) {
		this.numCuentaIndividual = numCuentaIndividual;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public GenericoCatalogoBean getEstatusIDC() {
		return estatusIDC;
	}

	public void setEstatusIDC(GenericoCatalogoBean estatusIDC) {
		this.estatusIDC = estatusIDC;
	}

	public Short getRegConciliado() {
		return regConciliado;
	}

	public void setRegConciliado(Short regConciliado) {
		this.regConciliado = regConciliado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
