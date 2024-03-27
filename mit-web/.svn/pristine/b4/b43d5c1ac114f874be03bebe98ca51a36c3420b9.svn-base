package mx.profuturo.nci.web.beans;

import java.io.Serializable;
import java.util.Date;

public class BancosBean implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rowId;
	private Long idBanco;
	private String folio;
//	private String folioRelacionado;
	private Long cuenta;
	private String autBanco;
	private GenericCatalogoBean subctaApovol;
	private Double importe;
	private GenericCatalogoBean claveBanco; //referencia CAT_CATALOGO id 47;
	private Date fechaCarga;
	private Date fechaDeposito;
	private GenericCatalogoBean estatusProceso; //referencia a CAT_CATALOGO id 6
	private GenericCatalogoBean origenAportacion; // referencia a CAT_CATALOGO id 40 y 45
	private GenericCatalogoBean indicadorSbc; //referencia aCAT_CATALOGO id 63
	private Long numCuentaIndividual;
	private String curp;
	private GenericCatalogoBean estatusIDC;
	private Short regConciliado; // 0.Sin Conciliar, 1. Conciliado
	private GenericCatalogoBean oldOrigenAportacion;
	
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
//	public String getFolioRelacionado() {
//		return folioRelacionado;
//	}
//	public void setFolioRelacionado(String folioRelacionado) {
//		this.folioRelacionado = folioRelacionado;
//	}
	public Long getCuenta() {
		return cuenta;
	}
	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
	}
	public String getAutBanco() {
		return autBanco;
	}
	public void setAutBanco(String autBanco) {
		this.autBanco = autBanco;
	}
	

	public GenericCatalogoBean getSubctaApovol() {
		return subctaApovol;
	}
	public void setSubctaApovol(GenericCatalogoBean subctaApovol) {
		this.subctaApovol = subctaApovol;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public GenericCatalogoBean getClaveBanco() {
		return claveBanco;
	}
	public void setClaveBanco(GenericCatalogoBean claveBanco) {
		this.claveBanco = claveBanco;
	}
	public Date getFechaCarga() {
		return fechaCarga;
	}
	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	public GenericCatalogoBean getEstatusProceso() {
		return estatusProceso;
	}
	public void setEstatusProceso(GenericCatalogoBean estatusProceso) {
		this.estatusProceso = estatusProceso;
	}
	public GenericCatalogoBean getOrigenAportacion() {
		return origenAportacion;
	}
	public void setOrigenAportacion(GenericCatalogoBean origenAportacion) {
		this.oldOrigenAportacion=this.origenAportacion;
		this.origenAportacion = origenAportacion;
	}
	public GenericCatalogoBean getIndicadorSbc() {
		return indicadorSbc;
	}
	public void setIndicadorSbc(GenericCatalogoBean indicadorSbc) {
		this.indicadorSbc = indicadorSbc;
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
	public GenericCatalogoBean getEstatusIDC() {
		return estatusIDC;
	}
	public void setEstatusIDC(GenericCatalogoBean estatusIDC) {
		this.estatusIDC = estatusIDC;
	}
	public Short getRegConciliado() {
		return regConciliado;
	}
	public void setRegConciliado(Short regConciliado) {
		this.regConciliado = regConciliado;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	public Date getFechaDeposito() {
		return fechaDeposito;
	}
	public void setFechaDeposito(Date fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}
	public GenericCatalogoBean getOldOrigenAportacion() {
		if(this.oldOrigenAportacion==null){
			this.oldOrigenAportacion=this.origenAportacion;
		}
		return oldOrigenAportacion;
	}
	
}