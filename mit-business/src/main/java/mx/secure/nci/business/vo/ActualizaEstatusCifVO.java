package mx.secure.nci.business.vo;

import java.io.Serializable;
import java.util.Date;

public class ActualizaEstatusCifVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String folio;
	private String proceso;
	private String subproceso;
	private String tipoDevolucionsecure;
	private String usuario;
	private Date fechaLiquidacion;	


	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public String getSubproceso() {
		return subproceso;
	}
	public void setSubproceso(String subproceso) {
		this.subproceso = subproceso;
	}
	public String getTipoDevolucionsecure() {
		return tipoDevolucionsecure;
	}
	public void setTipoDevolucionsecure(String tipoDevolucionsecure) {
		this.tipoDevolucionsecure = tipoDevolucionsecure;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFechaLiquidacion() {
		return fechaLiquidacion;
	}
	public void setFechaLiquidacion(Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}
	public ActualizaEstatusCifVO(String folio, String proceso, String subproceso, String tipoDevolucionsecure,
			String usuario, Date fechaLiquidacion) {
		
		this.folio=folio;
		this.proceso = proceso;
		this.subproceso = subproceso;
		this.tipoDevolucionsecure = tipoDevolucionsecure;
		this.usuario = usuario;
		this.fechaLiquidacion = fechaLiquidacion;
	}
}
