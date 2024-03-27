package mx.profuturo.nci.business.vo;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AbstractAuditoriaVO {
	private Date fechaCreacion;
	private String usuarioCreacion;
	private Date fechaActualizacion;
	private String usuarioActualizacion;

	public AbstractAuditoriaVO() {
	};

	public AbstractAuditoriaVO(Date fechaCreacion, String usuarioCreacion, Date fechaActualizacion,
			String usuarioActualizacion) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getUsuarioActualizacion() {
		return usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("fechaCreacion", fechaCreacion)
				.append("usuarioCreacion", usuarioCreacion).append("fechaActualizacion", fechaActualizacion)
				.append("usuarioActualizacion", usuarioActualizacion).appendSuper(super.toString()).toString();
	}

}
