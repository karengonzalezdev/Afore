package mx.secure.nci.ws.beans;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class ConciliacionBean 
{
	
	private Integer idConciliacion;
	private String folio;
	private String claveSolicitud;
	private String numeroOrden;
	private GenericoCatalogoBean origenAportacion;	
	private String numeroCuentaIndividual;
	private Integer numeroPago;
	private String claveBanco;
	private String cuentaBanco;		
	private BigDecimal importe;
	private BigInteger referenciaSubcuentasecure;
	private Date fechaCargaArchivo;
	private String nombreCliente;
	private String apellidoClientePaterno;
	private String apellidoClienteMaterno;
	private String curp;	
	private String rfc;
	private String nss; 
	private String correoElectronico;
	private String celular;	
	private GenericoCatalogoBean empresa;	
	private GenericoCatalogoBean tipoNomina;	
	private Integer numeroEmpleado;	
	private Date fechaPagosecure;
	private Date fechaValorsecure;
	private String folioTransaccion;
	private String folioProcesar;
	private GenericoCatalogoBean claveRedComercial;
	private Long sucursal;
	private Short tipoAportacion;
	private Short registroConciliado;
	private Short movimientoGenerado;
	private Long movimiento;		
	private String usuario;
	private Date fechaCreacion;
	private Date fechaActualizacion;	
	private List<DiversificacionConciliacionBean> diversificaciones;
	private GenericoCatalogoBean estatusTramite;
	private String nombreOrdenante;
	private String rfcOrdenante;
	private String curpOrdenante;
	
	//AFORE MOVIL
	private String tipoAhorrador;
	private Date fechaConciliacion;
	private Date fechaActualizacionFondo;
	private String usuarioActualizacionFondo;
	private String folioConciliacion;


	@XmlElementWrapper(name="diversificaciones")
	@XmlElement(name="diversificacion")
	public List<DiversificacionConciliacionBean> getDiversificaciones() {
		return diversificaciones;
	}

	
	
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

	public String getClaveSolicitud() {
		return claveSolicitud;
	}

	public void setClaveSolicitud(String claveSolicitud) {
		this.claveSolicitud = claveSolicitud;
	}

	public String getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public GenericoCatalogoBean getOrigenAportacion() {
		return origenAportacion;
	}

	public void setOrigenAportacion(GenericoCatalogoBean origenAportacion) {
		this.origenAportacion = origenAportacion;
	}

	public String getNumeroCuentaIndividual() {
		return numeroCuentaIndividual;
	}

	public void setNumeroCuentaIndividual(String numeroCuentaIndividual) {
		this.numeroCuentaIndividual = numeroCuentaIndividual;
	}

	public Integer getNumeroPago() {
		return numeroPago;
	}

	public void setNumeroPago(Integer numeroPago) {
		this.numeroPago = numeroPago;
	}

	public String getClaveBanco() {
		return claveBanco;
	}

	public void setClaveBanco(String claveBanco) {
		this.claveBanco = claveBanco;
	}

	public String getCuentaBanco() {
		return cuentaBanco;
	}

	public void setCuentaBanco(String cuentaBanco) {
		this.cuentaBanco = cuentaBanco;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public BigInteger getReferenciaSubcuentasecure() {
		return referenciaSubcuentasecure;
	}

	public void setReferenciaSubcuentasecure(BigInteger referenciaSubcuentasecure) {
		this.referenciaSubcuentasecure = referenciaSubcuentasecure;
	}

	public Date getFechaCargaArchivo() {
		return fechaCargaArchivo;
	}

	public void setFechaCargaArchivo(Date fechaCargaArchivo) {
		this.fechaCargaArchivo = fechaCargaArchivo;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoClientePaterno() {
		return apellidoClientePaterno;
	}

	public void setApellidoClientePaterno(String apellidoClientePaterno) {
		this.apellidoClientePaterno = apellidoClientePaterno;
	}

	public String getApellidoClienteMaterno() {
		return apellidoClienteMaterno;
	}

	public void setApellidoClienteMaterno(String apellidoClienteMaterno) {
		this.apellidoClienteMaterno = apellidoClienteMaterno;
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

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
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

	public Integer getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public void setNumeroEmpleado(Integer numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
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

	public GenericoCatalogoBean getClaveRedComercial() {
		return claveRedComercial;
	}

	public void setClaveRedComercial(GenericoCatalogoBean claveRedComercial) {
		this.claveRedComercial = claveRedComercial;
	}

	public Long getSucursal() {
		return sucursal;
	}

	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}

	public Short getTipoAportacion() {
		return tipoAportacion;
	}

	public void setTipoAportacion(Short tipoAportacion) {
		this.tipoAportacion = tipoAportacion;
	}

	public Short getRegistroConciliado() {
		return registroConciliado;
	}

	public void setRegistroConciliado(Short registroConciliado) {
		this.registroConciliado = registroConciliado;
	}

	public Short getMovimientoGenerado() {
		return movimientoGenerado;
	}

	public void setMovimientoGenerado(Short movimientoGenerado) {
		this.movimientoGenerado = movimientoGenerado;
	}

	public Long getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Long movimiento) {
		this.movimiento = movimiento;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
	public void setDiversificaciones(List<DiversificacionConciliacionBean> diversificaciones) {
		this.diversificaciones = diversificaciones;
	}
	
	public GenericoCatalogoBean getEstatusTramite() {
		return estatusTramite;
	}
	
	public void setEstatusTramite(GenericoCatalogoBean estatusTramite) {
		this.estatusTramite = estatusTramite;
	}
	
	public String getNombreOrdenante() {
		return nombreOrdenante;
	}

	public void setNombreOrdenante(String nombreOrdenante) {
		this.nombreOrdenante = nombreOrdenante;
	}

	public String getRfcOrdenante() {
		return rfcOrdenante;
	}

	public void setRfcOrdenante(String rfcOrdenante) {
		this.rfcOrdenante = rfcOrdenante;
	}

	public String getCurpOrdenante() {
		return curpOrdenante;
	}

	public void setCurpOrdenante(String curpOrdenante) {
		this.curpOrdenante = curpOrdenante;
	}

	public String getTipoAhorrador() {
		return tipoAhorrador;
	}
	public void setTipoAhorrador(String tipoAhorrador) {
		this.tipoAhorrador = tipoAhorrador;
	}

	public Date getFechaConciliacion() {
		return fechaConciliacion;
	}
	public void setFechaConciliacion(Date fechaConciliacion) {
		this.fechaConciliacion = fechaConciliacion;
	}

	public Date getFechaActualizacionFondo() {
		return fechaActualizacionFondo;
	}
	public void setFechaActualizacionFondo(Date fechaActualizacionFondo) {
		this.fechaActualizacionFondo = fechaActualizacionFondo;
	}

	public String getUsuarioActualizacionFondo() {
		return usuarioActualizacionFondo;
	}
	public void setUsuarioActualizacionFondo(String usuarioActualizacionFondo) {
		this.usuarioActualizacionFondo = usuarioActualizacionFondo;
	}

	public String getFolioConciliacion() {
		return folioConciliacion;
	}
	public void setFolioConciliacion(String folioConciliacion) {
		this.folioConciliacion = folioConciliacion;
	}

}