package mx.secure.nci.ws.beans;

import java.math.BigDecimal;
import java.util.Date;

public class PrincipalBean {
	
	private Integer idConciliacion;
	private String folio;
	private String cveSolicitud;
	private String noOrden;
	private GenericoCatalogoBean origenAportacion;
	private Long numCtaInvdual;
	private Integer noPago;
	private GenericoCatalogoBean banco;
	private String ctaBanco;
	private BigDecimal importe;
	private GenericoCatalogoBean refSubctasecure;
	private Date fechaCargaArchivo;
	private String nombre;
	private String apPaterno;
	private String apMaterno;
	private String curp;
	private String rfc;
	private String nss;
	private String correo;
	private Long celular;
	private GenericoCatalogoBean empresa;
	private GenericoCatalogoBean tipoNomina;
	private Integer noEmpleado;
	private Date fechaPagosecure;
	private Date fechaValorsecure;
	private String folioTransaccion;
	private String folioProcesar;
	private GenericoCatalogoBean cveRedComercial;
	private Long idSucursal;
	private Short tipoAportacion;
	private Short regAcreditado;
	private Long identificadorMov;
	private String idInstancia;
	
	private Date fechaCreacion;
	private String usuarioCreacion;
	private Date fechaActualizacion;
	private String usuarioActualizacion;
	
	public Integer getIdConciliacion() {
		return idConciliacion;
	}
	public void setIdConciliacion(Integer idConciliacion) {
		this.idConciliacion = idConciliacion;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getCveSolicitud() {
		return cveSolicitud;
	}
	public void setCveSolicitud(String cveSolicitud) {
		this.cveSolicitud = cveSolicitud;
	}
	public String getNoOrden() {
		return noOrden;
	}
	public void setNoOrden(String noOrden) {
		this.noOrden = noOrden;
	}
	public GenericoCatalogoBean getOrigenAportacion() {
		return origenAportacion;
	}
	public void setOrigenAportacion(GenericoCatalogoBean origenAportacion) {
		this.origenAportacion = origenAportacion;
	}
	public Long getNumCtaInvdual() {
		return numCtaInvdual;
	}
	public void setNumCtaInvdual(Long numCtaInvdual) {
		this.numCtaInvdual = numCtaInvdual;
	}
	public Integer getNoPago() {
		return noPago;
	}
	public void setNoPago(Integer noPago) {
		this.noPago = noPago;
	}
	public GenericoCatalogoBean getBanco() {
		return banco;
	}
	public void setBanco(GenericoCatalogoBean banco) {
		this.banco = banco;
	}
	public String getCtaBanco() {
		return ctaBanco;
	}
	public void setCtaBanco(String ctaBanco) {
		this.ctaBanco = ctaBanco;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public GenericoCatalogoBean getRefSubctasecure() {
		return refSubctasecure;
	}
	public void setRefSubctasecure(GenericoCatalogoBean refSubctasecure) {
		this.refSubctasecure = refSubctasecure;
	}
	public Date getFechaCargaArchivo() {
		return fechaCargaArchivo;
	}
	public void setFechaCargaArchivo(Date fechaCargaArchivo) {
		this.fechaCargaArchivo = fechaCargaArchivo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApPaterno() {
		return apPaterno;
	}
	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}
	public String getApMaterno() {
		return apMaterno;
	}
	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getNss() {
		return nss;
	}
	public void setNss(String nss) {
		this.nss = nss;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Long getCelular() {
		return celular;
	}
	public void setCelular(Long celular) {
		this.celular = celular;
	}
	public GenericoCatalogoBean getEmpresa() {
		return empresa;
	}
	public void setEmpresa(GenericoCatalogoBean empresa) {
		this.empresa = empresa;
	}
	public GenericoCatalogoBean getTipoNomina() {
		return tipoNomina;
	}
	public void setTipoNomina(GenericoCatalogoBean tipoNomina) {
		this.tipoNomina = tipoNomina;
	}
	public Integer getNoEmpleado() {
		return noEmpleado;
	}
	public void setNoEmpleado(Integer noEmpleado) {
		this.noEmpleado = noEmpleado;
	}
	public Date getFechaPagosecure() {
		return fechaPagosecure;
	}
	public void setFechaPagosecure(Date fechaPagosecure) {
		this.fechaPagosecure = fechaPagosecure;
	}
	public Date getFechaValorsecure() {
		return fechaValorsecure;
	}
	public void setFechaValorsecure(Date fechaValorsecure) {
		this.fechaValorsecure = fechaValorsecure;
	}
	public String getFolioTransaccion() {
		return folioTransaccion;
	}
	public void setFolioTransaccion(String folioTransaccion) {
		this.folioTransaccion = folioTransaccion;
	}
	public String getFolioProcesar() {
		return folioProcesar;
	}
	public void setFolioProcesar(String folioProcesar) {
		this.folioProcesar = folioProcesar;
	}
	public GenericoCatalogoBean getCveRedComercial() {
		return cveRedComercial;
	}
	public void setCveRedComercial(GenericoCatalogoBean cveRedComercial) {
		this.cveRedComercial = cveRedComercial;
	}
	public Long getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}
	public Short getTipoAportacion() {
		return tipoAportacion;
	}
	public void setTipoAportacion(Short tipoAportacion) {
		this.tipoAportacion = tipoAportacion;
	}
	public Short getRegAcreditado() {
		return regAcreditado;
	}
	public void setRegAcreditado(Short regAcreditado) {
		this.regAcreditado = regAcreditado;
	}
	public Long getIdentificadorMov() {
		return identificadorMov;
	}
	public void setIdentificadorMov(Long identificadorMov) {
		this.identificadorMov = identificadorMov;
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
	public String getIdInstancia() {
		return idInstancia;
	}
	public void setIdInstancia(String idInstancia) {
		this.idInstancia = idInstancia;
	}	
	
}