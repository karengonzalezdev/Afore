package mx.secure.nci.ws.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

//@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
public class PreSolicitudDomiciliacionBean extends AbstractAuditoriaBean {
	private String claveSolicitud;

	private String folio;

	private Long numCuentaIndividual;
	private GenericoCatalogoBean lineaNegocio;
	private GenericoCatalogoBean origenSolicitud;
	private String cuentaBanco;
	private GenericoCatalogoBean tipoCuenta;
	private GenericoCatalogoBean claveBanco;
	private String clienteNombre;
	private String clientePaterno;
	private String clienteMaterno;
	private String correoElectronico;
	private String curp;
	private String nss;
	private String clienteRfc;
	private GenericoCatalogoBean periodo;
	private FrecuenciaBean frecuenciaInicial;
	private FrecuenciaBean frecuenciaFinal;
	private GenericoCatalogoBean estatusSolicitud;
	private GenericoCatalogoBean rechazoSolicitud;
	private Date fechaCaptura;
	private BigDecimal importe;
	private Short incrementabilidad;
	private Short opcionIncrementabilidad;
	private GenericoCatalogoBean porcentajeIncremento;
	private BigDecimal montoIncremento;
	private GenericoCatalogoBean periodoIncrementabilidad;
	private Short banderaDocumentos;
	private GenericoCatalogoBean moneda;
	private String usuario;
	private List<DiversificacionPreSolBean> diversificaciones;
	private GenericoCatalogoBean medioNotificacion;
	private GenericoCatalogoBean companiaCelular;
	private Long celular;
	private Date fechaCargo;
	private Date fechaEstatuSolicitud;

	private String origenSolAfom;
	private String vigenciaTc;
	
	private String folioProcesar;
	
	private String curpTutor;
	private String folioFamilia;
	
	//FOP Domi Registro 25/06/2018
	private String gerencia;
	private String promotor;
	private String regional;
	private Integer tipoRegistro;
	private String tutor;
	private String paternoTutor;
	private String maternoTutor;
	
	
	// @XmlElement(required=true)
	public String getClaveSolicitud() {
		return claveSolicitud;
	}

	// @XmlElement(required=true)
	// @XmlElement(required=true,nillable=false)
	public String getFolio() {
		return folio;
	}

	// @XmlElement(required=true)
	public Long getNumCuentaIndividual() {
		return numCuentaIndividual;
	}

	// @XmlElement(required=true)
	public String getCuentaBanco() {
		return cuentaBanco;
	}

	// @XmlElement(required=true)
	public String getUsuario() {
		return usuario;
	}

	@XmlElementWrapper(name = "diversificaciones")
	@XmlElement(name = "diversificacion")
	public List<DiversificacionPreSolBean> getDiversificaciones() {
		return diversificaciones;
	}

	public void setClaveSolicitud(String claveSolicitud) {
		this.claveSolicitud = claveSolicitud;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public void setNumCuentaIndividual(Long numCuentaIndividual) {
		this.numCuentaIndividual = numCuentaIndividual;
	}

	public GenericoCatalogoBean getLineaNegocio() {
		return lineaNegocio;
	}

	public void setLineaNegocio(GenericoCatalogoBean lineaNegocio) {
		this.lineaNegocio = lineaNegocio;
	}

	public GenericoCatalogoBean getOrigenSolicitud() {
		return origenSolicitud;
	}

	public void setOrigenSolicitud(GenericoCatalogoBean origenSolicitud) {
		this.origenSolicitud = origenSolicitud;
	}

	public void setCuentaBanco(String cuentaBanco) {
		this.cuentaBanco = cuentaBanco;
	}

	public GenericoCatalogoBean getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(GenericoCatalogoBean tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public GenericoCatalogoBean getClaveBanco() {
		return claveBanco;
	}

	public void setClaveBanco(GenericoCatalogoBean claveBanco) {
		this.claveBanco = claveBanco;
	}

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public String getClientePaterno() {
		return clientePaterno;
	}

	public void setClientePaterno(String clientePaterno) {
		this.clientePaterno = clientePaterno;
	}

	public String getClienteMaterno() {
		return clienteMaterno;
	}

	public void setClienteMaterno(String clienteMaterno) {
		this.clienteMaterno = clienteMaterno;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getClienteRfc() {
		return clienteRfc;
	}

	public void setClienteRfc(String clienteRfc) {
		this.clienteRfc = clienteRfc;
	}

	public GenericoCatalogoBean getPeriodo() {
		return periodo;
	}

	public void setPeriodo(GenericoCatalogoBean periodo) {
		this.periodo = periodo;
	}

	public FrecuenciaBean getFrecuenciaInicial() {
		return frecuenciaInicial;
	}

	public void setFrecuenciaInicial(FrecuenciaBean frecuenciaInicial) {
		this.frecuenciaInicial = frecuenciaInicial;
	}

	public FrecuenciaBean getFrecuenciaFinal() {
		return frecuenciaFinal;
	}

	public void setFrecuenciaFinal(FrecuenciaBean frecuenciaFinal) {
		this.frecuenciaFinal = frecuenciaFinal;
	}

	public GenericoCatalogoBean getEstatusSolicitud() {
		return estatusSolicitud;
	}

	public void setEstatusSolicitud(GenericoCatalogoBean estatusSolicitud) {
		this.estatusSolicitud = estatusSolicitud;
	}

	public GenericoCatalogoBean getRechazoSolicitud() {
		return rechazoSolicitud;
	}

	public void setRechazoSolicitud(GenericoCatalogoBean rechazoSolicitud) {
		this.rechazoSolicitud = rechazoSolicitud;
	}

	public Date getFechaCaptura() {
		return fechaCaptura;
	}

	public void setFechaCaptura(Date fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public Short getIncrementabilidad() {
		return incrementabilidad;
	}

	public void setIncrementabilidad(Short incrementabilidad) {
		this.incrementabilidad = incrementabilidad;
	}

	public Short getOpcionIncrementabilidad() {
		return opcionIncrementabilidad;
	}

	public void setOpcionIncrementabilidad(Short opcionIncrementabilidad) {
		this.opcionIncrementabilidad = opcionIncrementabilidad;
	}

	public GenericoCatalogoBean getPorcentajeIncremento() {
		return porcentajeIncremento;
	}

	public void setPorcentajeIncremento(GenericoCatalogoBean porcentajeIncremento) {
		this.porcentajeIncremento = porcentajeIncremento;
	}

	public GenericoCatalogoBean getPeriodoIncrementabilidad() {
		return periodoIncrementabilidad;
	}

	public void setPeriodoIncrementabilidad(GenericoCatalogoBean periodoIncrementabilidad) {
		this.periodoIncrementabilidad = periodoIncrementabilidad;
	}

	public Short getBanderaDocumentos() {
		return banderaDocumentos;
	}

	public void setBanderaDocumentos(Short banderaDocumentos) {
		this.banderaDocumentos = banderaDocumentos;
	}

	public GenericoCatalogoBean getMoneda() {
		return moneda;
	}

	public void setMoneda(GenericoCatalogoBean moneda) {
		this.moneda = moneda;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setDiversificaciones(List<DiversificacionPreSolBean> diversificaciones) {
		this.diversificaciones = diversificaciones;
	}

	public GenericoCatalogoBean getMedioNotificacion() {
		return medioNotificacion;
	}

	public void setMedioNotificacion(GenericoCatalogoBean medioNotificacion) {
		this.medioNotificacion = medioNotificacion;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public BigDecimal getMontoIncremento() {
		return montoIncremento;
	}

	public void setMontoIncremento(BigDecimal montoIncremento) {
		this.montoIncremento = montoIncremento;
	}

	public GenericoCatalogoBean getCompaniaCelular() {
		return companiaCelular;
	}

	public void setCompaniaCelular(GenericoCatalogoBean companiaCelular) {
		this.companiaCelular = companiaCelular;
	}

	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}

	public Date getFechaCargo() {
		return fechaCargo;
	}

	public void setFechaCargo(Date fechaCargo) {
		this.fechaCargo = fechaCargo;
	}

	public Date getFechaEstatuSolicitud() {
		return fechaEstatuSolicitud;
	}

	public void setFechaEstatuSolicitud(Date fechaEstatuSolicitud) {
		this.fechaEstatuSolicitud = fechaEstatuSolicitud;
	}

	public String getOrigenSolAfom() {
		return origenSolAfom;
	}
	
	public void setOrigenSolAfom(String origenSolAfom) {
		this.origenSolAfom = origenSolAfom;
}
	public String getVigenciaTc() {
		return vigenciaTc;
	}
	
	public void setVigenciaTc(String vigenciaTc) {
		this.vigenciaTc = vigenciaTc;
	}

	public String getFolioProcesar() {
		return folioProcesar;
	}

	public void setFolioProcesar(String folioProcesar) {
		this.folioProcesar = folioProcesar;
	}

	public String getCurpTutor() {
		return curpTutor;
	}

	public void setCurpTutor(String curpTutor) {
		this.curpTutor = curpTutor;
	}

	public String getFolioFamilia() {
		return folioFamilia;
	}

	public void setFolioFamilia(String folioFamilia) {
		this.folioFamilia = folioFamilia;
	}

	public String getGerencia() {
		return gerencia;
	}

	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}

	public String getPromotor() {
		return promotor;
	}

	public void setPromotor(String promotor) {
		this.promotor = promotor;
	}

	public String getRegional() {
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}

	public Integer getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(Integer tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public String getPaternoTutor() {
		return paternoTutor;
	}

	public void setPaternoTutor(String paternoTutor) {
		this.paternoTutor = paternoTutor;
	}

	public String getMaternoTutor() {
		return maternoTutor;
	}

	public void setMaternoTutor(String maternoTutor) {
		this.maternoTutor = maternoTutor;
	}
	
	
	
}