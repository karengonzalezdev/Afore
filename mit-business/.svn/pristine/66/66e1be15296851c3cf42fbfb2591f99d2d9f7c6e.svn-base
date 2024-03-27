package mx.profuturo.nci.business.wrapped;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.profuturo.nci.business.vo.CargoVO;

public class CargoFilter extends CargoVO {
	private Boolean flagOtrosBancos=Boolean.FALSE;
	private Date fechaInicio;
	private Date fechaFin;
	private Short idTipoArchivo;
	private List<Long> excludeNumCuentaIndividual;
	private List<Short> subOrigen;
	private Integer idConcil;

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

	public Boolean getFlagOtrosBancos() {
		return flagOtrosBancos;
	}

	public void setFlagOtrosBancos(Boolean flagOtrosBancos) {
		this.flagOtrosBancos = flagOtrosBancos;
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
}