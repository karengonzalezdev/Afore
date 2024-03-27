package mx.profuturo.nci.business.wrapped;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Deprecated
public class GenArchivosDomiFilter{
	private Short idTipoArchivoBanco;
	private Short idTipoCuenta;
	private List<Short> idsUnSolArchivoTiposCuenta=new ArrayList<Short>();
	private List<Short> idsBancos=new ArrayList<Short>();
	private Date fechaInicio;
	private Date fechaFin;
	private String usuario;
	private Boolean unSoloArchivo=Boolean.FALSE;
	
	public Short getIdTipoArchivoBanco() {
		return idTipoArchivoBanco;
	}
	public void setIdTipoArchivoBanco(Short idTipoArchivoBanco) {
		this.idTipoArchivoBanco = idTipoArchivoBanco;
	}
	
	public Short getIdTipoCuenta() {
		return idTipoCuenta;
	}
	public void setIdTipoCuenta(Short idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}
	public List<Short> getIdsBancos() {
		return idsBancos;
	}
	public void setIdsBancos(List<Short> idsBancos) {
		this.idsBancos = idsBancos;
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
	
	
	public List<Short> getIdsUnSolArchivoTiposCuenta() {
		return idsUnSolArchivoTiposCuenta;
	}
	public void setIdsUnSolArchivoTiposCuenta(List<Short> idsUnSolArchivoTiposCuenta) {
		this.idsUnSolArchivoTiposCuenta = idsUnSolArchivoTiposCuenta;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
	public Boolean getUnSoloArchivo() {
		return unSoloArchivo;
	}
	public void setUnSoloArchivo(Boolean unSoloArchivo) {
		this.unSoloArchivo = unSoloArchivo;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj !=null && obj instanceof GenArchivosDomiFilter){
			GenArchivosDomiFilter dfp = (GenArchivosDomiFilter)obj;
			return 	idTipoArchivoBanco!=null && idTipoArchivoBanco.equals(dfp.idTipoArchivoBanco)
					&& idTipoCuenta!=null && idTipoCuenta.equals(dfp.getIdTipoCuenta());
		}else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return idTipoArchivoBanco*10000+idTipoCuenta;
	}
}