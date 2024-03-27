package mx.profuturo.nci.business.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RolVO {
	private Long idRol;
	private String descRol;
	
	public RolVO() {}
	
	public RolVO(Long idRol) {
		this.idRol = idRol;
	}
	
	public RolVO(Long idRol,String descripcion) {
		this.idRol = idRol;
		this.descRol=descripcion;
	}
	
	public Long getIdRol() {
		return idRol;
	}
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	public String getDescRol() {
		return descRol;
	}
	public void setDescRol(String descRol) {
		this.descRol = descRol;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).appendSuper(super.toString())
				 .append("id",idRol).append("descRol",descRol).toString();
	}
	
}
