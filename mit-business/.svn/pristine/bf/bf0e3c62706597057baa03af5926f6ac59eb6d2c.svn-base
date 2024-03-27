package mx.profuturo.nci.business.wrapped;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.profuturo.nci.business.vo.SolicitudVO;

public class SolicitudFilter extends SolicitudVO{
	private List<Short> estatusSolicitudes;
	private List<Short> idsBancos;
	private List<Short> idsTiposCuenta;
	private Boolean flagOtrosBancos=Boolean.FALSE;
	private Date fechaInicio;
	private Date fechaFin;
	private Short idTipoArchivo;
	private List<Long> excludeNumCuentaIndividual;
	private List<Short> subOrigen;
	private Integer idConcil;
	private Boolean archivoUnico;
	
	{
		idsBancos= new ArrayList<Short>();
		idsTiposCuenta= new ArrayList<Short>();
	}
	
	public List<Short> getEstatusSolicitudes() {
		return estatusSolicitudes;
	}

	public void setEstatusSolicitudes(List<Short> estatusSolicitudes) {
		this.estatusSolicitudes = estatusSolicitudes;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<Short> getIdsBancos() {
		return idsBancos;
	}

	public void setIdsBancos(List<Short> idsBancos) {
		this.idsBancos = idsBancos;
	}

	public Boolean getFlagOtrosBancos() {
		return flagOtrosBancos;
	}

	public void setFlagOtrosBancos(Boolean flagOtrosBancos) {
		this.flagOtrosBancos = flagOtrosBancos;
	}

	public List<Short> getIdsTiposCuenta() {
		return idsTiposCuenta;
	}

	public void setIdsTiposCuenta(List<Short> idsTiposCuenta) {
		this.idsTiposCuenta = idsTiposCuenta;
	}

	public Short getIdTipoArchivo() {
		return idTipoArchivo;
	}

	public void setIdTipoArchivo(Short idTipoArchivo) {
		this.idTipoArchivo = idTipoArchivo;
	}
	
	public List<Long> getExcludeNumCuentaIndividual() {
		if(excludeNumCuentaIndividual == null){
			excludeNumCuentaIndividual = new ArrayList<Long>();
		}
		return excludeNumCuentaIndividual;
	}
	
	public List<Short> getSubOrigen() {
		return subOrigen;
	}
	
	public void setSubOrigen(List<Short> subOrigen) {
		this.subOrigen = subOrigen;
	}
	
	public Integer getIdConcil() {
		return idConcil;
	}
	
	public void setIdConcil(Integer idConcil) {
		this.idConcil = idConcil;
	}

	public Boolean getArchivoUnico() {
		return archivoUnico;
	}

	public void setArchivoUnico(Boolean archivoUnico) {
		this.archivoUnico = archivoUnico;
	}	
	
	
	
}