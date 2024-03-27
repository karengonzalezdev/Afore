package mx.secure.nci.business.vo;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ConfigIntentossecureVO {

	private Integer idConfigIntentossecure;
	private Integer idOrigenDomiciliacion;
	private String descOrigenDomiciliacion;
	private Integer idPeriodo;
	private String descPeriodo;
	private Integer numIntentos;
	private Integer estatus;
	private Date fehCre;
	private String usuCre;
	private Date fehAct;
	private String usuAct;

	public ConfigIntentossecureVO() {

	}
	

	public ConfigIntentossecureVO(Integer idOrigenDomiciliacion, String descOrigenDomiciliacion, Integer idPeriodo,
			String descPeriodo, Integer numIntentos, Integer estatus, Date fehCre, String usuCre, Date fehAct,
			String usuAct) {
		this.idOrigenDomiciliacion = idOrigenDomiciliacion;
		this.descOrigenDomiciliacion = descOrigenDomiciliacion;
		this.idPeriodo = idPeriodo;
		this.descPeriodo = descPeriodo;
		this.numIntentos = numIntentos;
		this.estatus = estatus;
		this.fehCre = fehCre;
		this.usuCre = usuCre;
		this.fehAct = fehAct;
		this.usuAct = usuAct;
	}

	public ConfigIntentossecureVO(Integer idConfigIntentossecure, Integer idOrigenDomiciliacion,
			String descOrigenDomiciliacion, Integer idPeriodo, String descPeriodo, Integer numIntentos, Integer estatus,
			Date fehCre, String usuCre, Date fehAct, String usuAct) {
		this.idConfigIntentossecure = idConfigIntentossecure;
		this.idOrigenDomiciliacion = idOrigenDomiciliacion;
		this.descOrigenDomiciliacion = descOrigenDomiciliacion;
		this.idPeriodo = idPeriodo;
		this.descPeriodo = descPeriodo;
		this.numIntentos = numIntentos;
		this.estatus = estatus;
		this.fehCre = fehCre;
		this.usuCre = usuCre;
		this.fehAct = fehAct;
		this.usuAct = usuAct;
	}

	public Integer getIdConfigIntentossecure() {
		return idConfigIntentossecure;
	}

	public void setIdConfigIntentossecure(Integer idConfigIntentossecure) {
		this.idConfigIntentossecure = idConfigIntentossecure;
	}

	public Integer getIdOrigenDomiciliacion() {
		return idOrigenDomiciliacion;
	}

	public void setIdOrigenDomiciliacion(Integer idOrigenDomiciliacion) {
		this.idOrigenDomiciliacion = idOrigenDomiciliacion;
	}

	public String getDescOrigenDomiciliacion() {
		return descOrigenDomiciliacion;
	}

	public void setDescOrigenDomiciliacion(String descOrigenDomiciliacion) {
		this.descOrigenDomiciliacion = descOrigenDomiciliacion;
	}

	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public String getDescPeriodo() {
		return descPeriodo;
	}

	public void setDescPeriodo(String descPeriodo) {
		this.descPeriodo = descPeriodo;
	}

	public Integer getNumIntentos() {
		return numIntentos;
	}

	public void setNumIntentos(Integer numIntentos) {
		this.numIntentos = numIntentos;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Date getFehCre() {
		return fehCre;
	}

	public void setFehCre(Date fehCre) {
		this.fehCre = fehCre;
	}

	public String getUsuCre() {
		return usuCre;
	}

	public void setUsuCre(String usuCre) {
		this.usuCre = usuCre;
	}

	public Date getFehAct() {
		return fehAct;
	}

	public void setFehAct(Date fehAct) {
		this.fehAct = fehAct;
	}

	public String getUsuAct() {
		return usuAct;
	}

	public void setUsuAct(String usuAct) {
		this.usuAct = usuAct;
	}



}
