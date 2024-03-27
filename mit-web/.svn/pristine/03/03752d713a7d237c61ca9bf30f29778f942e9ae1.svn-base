package mx.profuturo.nci.web.beans;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.primefaces.model.UploadedFile;

public class DepositoArchivoBean implements Serializable , Comparable<DepositoArchivoBean>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String       identificador;
	private UploadedFile archivo;
	private String 		 descripcion;
	private Short 	 	 status;
	private String 	 	 descStatus;
	private String       imgStatus;	
	private Boolean      erroneo;
	
	private ConfigSubprocesoOrigenBean configSubprocesoOrigen;
	private Date 		fecha;
	
	
	public  DepositoArchivoBean(){}
	

	public void setArchivo(UploadedFile archivo) 
	{
		this.archivo = archivo;
		
	}
	
	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public UploadedFile getArchivo() {
		return archivo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getDescStatus() {
		return descStatus;
	}
	public void setDescStatus(String descStatus) {
		this.descStatus = descStatus;
	}
	public String getImgStatus() {
		return imgStatus;
	}
	public void setImgStatus(String imgStatus) {
		this.imgStatus = imgStatus;
	}

	




	public Boolean getErroneo() {
		return erroneo;
	}


	public void setErroneo(Boolean erroneo) {
		this.erroneo = erroneo;
	}
	
	

	
	public ConfigSubprocesoOrigenBean getConfigSubprocesoOrigen() {
		return configSubprocesoOrigen;
	}


	public void setConfigSubprocesoOrigen(
			ConfigSubprocesoOrigenBean configSubprocesoOrigen) {
		this.configSubprocesoOrigen = configSubprocesoOrigen;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identificador == null) ? 0 : identificador.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepositoArchivoBean other = (DepositoArchivoBean) obj;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		return true;
	}




	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.appendSuper(super.toString())				
				.append("identificador", identificador)
				.append("archivo", archivo != null ? archivo.getFileName() : null)
				.append("descripcion", descripcion)
				.append("status", status)
				.append("descStatus", descStatus)
				.append("imgStatus", imgStatus)
				.append("fecha", fecha).toString();
	}


	public int compareTo(DepositoArchivoBean obj) 
	{
		if(this.getFecha() == null)
		{
			return -1;
		}
		else if( obj == null || obj.getFecha() == null)
		{
			return 1;
		}
		else
		{
			return this.getFecha().compareTo(obj.getFecha());
		}
	}
	
	
	
	
	

	
	
	
}
