package mx.profuturo.nci.business.wrapped;

import java.math.BigDecimal;
import java.util.Date;

import mx.profuturo.nci.business.vo.FrecuenciaVO;
import mx.profuturo.nci.business.vo.GenericCatalogoVO;
import mx.profuturo.nci.business.vo.SolicitudVO;

public class ApoVolFilter {
	private String cveSol;
	private Short periodicidad;
	private Short frecIni;
	private Short frecFin;
	private Short fondoApovol;
	private Short idEstatus;
	private BigDecimal monto;
	private String usuario;
	private Integer vigencia;
	private Integer fondo;
	private Integer porcentaje;
	private Date fechaProximoCargo;
	
	public String getCveSol() {
		return cveSol;
	}
	
	public void setCveSol(String cveSol) {
		this.cveSol = cveSol;
	}
	
	public Short getPeriodicidad() {
		return periodicidad;
	}
	
	public void setPeriodicidad(Short periodicidad) {
		this.periodicidad = periodicidad;
	}
	
	public Short getFrecIni() {
		return frecIni;
	}
	
	public void setFrecIni(Short frecIni) {
		this.frecIni = frecIni;
	}
	
	public Short getFrecFin() {
		return frecFin;
	}
	
	public void setFrecFin(Short frecFin) {
		this.frecFin = frecFin;
	}
	
	public Short getFondoApovol() {
		return fondoApovol;
	}
	
	public void setFondoApovol(Short fondoApovol) {
		this.fondoApovol = fondoApovol;
	}
	
	public BigDecimal getMonto() {
		return monto;
	}
	
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public Integer getVigencia() {
		return vigencia;
	}
	
	public void setVigencia(Integer vigencia) {
		this.vigencia = vigencia;
	}

	public Integer getFondo() {
		return fondo;
	}

	public void setFondo(Integer fondo) {
		this.fondo = fondo;
	}

	public Integer getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Short getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Short idEstatus) {
		this.idEstatus = idEstatus;
	}

	public Date getFechaProximoCargo() {
		return fechaProximoCargo;
	}

	public void setFechaProximoCargo(Date fechaProximoCargo) {
		this.fechaProximoCargo = fechaProximoCargo;
	}
	
	public SolicitudVO getSolicitud() {
		SolicitudVO vo = new SolicitudVO();
		vo.setPeriodo(new GenericCatalogoVO(periodicidad));
		vo.setFrecuenciaInicial(new FrecuenciaVO(new GenericCatalogoVO(frecIni), frecIni));
		vo.setFrecuenciaFinal(new FrecuenciaVO(new GenericCatalogoVO(frecFin), frecFin));
		return vo;
	}
	
}