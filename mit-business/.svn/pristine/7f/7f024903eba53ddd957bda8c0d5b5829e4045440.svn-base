package mx.profuturo.nci.business.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReporteCifrasControlVO implements Serializable{

	private static final long serialVersionUID = 4798229630774680449L;
	
	
	private String claveSolicitud;
	private String estatusSolicitud;
	private String fechaUltimoEstatus;
	private String usuarioActualizacion;
	private String numeroCuenta;
	private String curp;
	private String nss;
	private String rfc;
	private String nombre;

	private String origenCaptura;
	private String fechaCaptura;
	private String usuarioCaptura;
	private String tipoMonto;

	private String folio;
	private String fechaLiquidacion;
	private Integer numeracion;
	
	
	
	
	public Integer getNumeracion() {
		return numeracion;
	}
	public void setNumeracion(Integer numeracion) {
		this.numeracion = numeracion;
	}

	private List<ReporteCifrasControlCargoVO> listCargo;
	private List<ReporteCifrasControlAbonoVO> listabono;
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}
	public String getFechaCaptura() {
		return fechaCaptura;
	}
	public void setFechaCaptura(String fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
	}
	
	
	public List<ReporteCifrasControlCargoVO> getListCargo() {
		return listCargo;
	}
	public void setListCargo(List<ReporteCifrasControlCargoVO> listCargo) {
		this.listCargo = listCargo;
	}
	public JRDataSource getCargosDS(){       
	    return new JRBeanCollectionDataSource(listCargo);   
	}
	
	public String getClaveSolicitud() {
		return claveSolicitud;
	}
	public void setClaveSolicitud(String claveSolicitud) {
		this.claveSolicitud = claveSolicitud;
	}
	public String getEstatusSolicitud() {
		return estatusSolicitud;
	}
	public void setEstatusSolicitud(String estatusSolicitud) {
		this.estatusSolicitud = estatusSolicitud;
	}
	public String getFechaUltimoEstatus() {
		return fechaUltimoEstatus;
	}
	public void setFechaUltimoEstatus(String fechaUltimoEstatus) {
		this.fechaUltimoEstatus = fechaUltimoEstatus;
	}
	public String getUsuarioActualizacion() {
		return usuarioActualizacion;
	}
	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getNss() {
		return nss;
	}
	public void setNss(String nss) {
		this.nss = nss;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getOrigenCaptura() {
		return origenCaptura;
	}
	public void setOrigenCaptura(String origenCaptura) {
		this.origenCaptura = origenCaptura;
	}
	public String getUsuarioCaptura() {
		return usuarioCaptura;
	}
	public void setUsuarioCaptura(String usuarioCaptura) {
		this.usuarioCaptura = usuarioCaptura;
	}
	public String getTipoMonto() {
		return tipoMonto;
	}
	public void setTipoMonto(String tipoMonto) {
		this.tipoMonto = tipoMonto;
	}

	public List<ReporteCifrasControlAbonoVO> getListabono() {
		return listabono;
	}
	public void setListabono(List<ReporteCifrasControlAbonoVO> listabono) {
		this.listabono = listabono;
	}

	public JRDataSource getAbonosDS(){       
	    return new JRBeanCollectionDataSource(listabono);   
	}
	
	
	
}
