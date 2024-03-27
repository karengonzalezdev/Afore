package mx.secure.nci.ws.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class SolicitudDomiciliacionBean extends AbstractAuditoriaBean {
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
	private Date fechaCargo;
	private Date fechaProximoCargo;
	private GenericoCatalogoBean estatusCargo;
	private GenericoCatalogoBean rechazoBanco;
	private Date fechaCargaResp;
	private Date fechaReactivacion;
	private Date fechaCaptura;
	private BigDecimal importe;
	private Short Incrementabilidad;
	private Short opcionIncrementabilidad;
	private GenericoCatalogoBean porcentajeIncremento;
	private BigDecimal montoIncremento;
	private GenericoCatalogoBean periodoIncrementabilidad;
	private Short intentos;
	private GenericoCatalogoBean moneda;
	private GenericoCatalogoBean medioNotificacion;
	private String usuario;
	private List<DiversificacionBean> diversificaciones;
	
	private Long celular;
	private Date fechaEstatuSolicitud;
	private GenericoCatalogoBean companiaCelular;
	private GenericoCatalogoBean estatusTraspaso;
	private GenericoCatalogoBean origenAportacion;
	private Integer numCargo;
	private Date fechaSuspension;

	private String origenSolAfom;
	private String vigenciaTc;
	
	private String folioProcesar;
	
	private String curpTutor;
	private String folioFamilia;
	private Long idArchivoDomi;
	//FOP Domi Registro 03/07/2018
	private String gerencia;
	private String promotor;
	private String regional;
	private Integer tipoRegistro;
	private String tutor;	
	private String paternoTutor;
	private String maternoTutor;
	

	@XmlElementWrapper(name = "diversificaciones")
	@XmlElement(name = "diversificacion")
	public List<DiversificacionBean> getDiversificaciones() {
		return diversificaciones;
	}

	public String getClaveSolicitud() {
		return claveSolicitud;
	}

	public void setClaveSolicitud(String claveSolicitud) {
		this.claveSolicitud = claveSolicitud;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public Long getNumCuentaIndividual() {
		return numCuentaIndividual;
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

	public String getCuentaBanco() {
		return cuentaBanco;
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

	public Date getFechaCargo() {
		return fechaCargo;
	}

	public void setFechaCargo(Date fechaCargo) {
		this.fechaCargo = fechaCargo;
	}

	public GenericoCatalogoBean getEstatusCargo() {
		return estatusCargo;
	}

	public void setEstatusCargo(GenericoCatalogoBean estatusCargo) {
		this.estatusCargo = estatusCargo;
	}

	public GenericoCatalogoBean getRechazoBanco() {
		return rechazoBanco;
	}

	public void setRechazoBanco(GenericoCatalogoBean rechazoBanco) {
		this.rechazoBanco = rechazoBanco;
	}

	public Date getFechaCargaResp() {
		return fechaCargaResp;
	}

	public void setFechaCargaResp(Date fechaCargaResp) {
		this.fechaCargaResp = fechaCargaResp;
	}

	public Date getFechaReactivacion() {
		return fechaReactivacion;
	}

	public void setFechaReactivacion(Date fechaReactivacion) {
		this.fechaReactivacion = fechaReactivacion;
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
		return Incrementabilidad;
	}

	public void setIncrementabilidad(Short incrementabilidad) {
		Incrementabilidad = incrementabilidad;
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

	public Short getIntentos() {
		return intentos;
	}

	public void setIntentos(Short intentos) {
		this.intentos = intentos;
	}

	public void setDiversificaciones(List<DiversificacionBean> diversificaciones) {
		this.diversificaciones = diversificaciones;
	}

	public GenericoCatalogoBean getMoneda() {
		return moneda;
	}

	public void setMoneda(GenericoCatalogoBean moneda) {
		this.moneda = moneda;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}

	public Date getFechaEstatuSolicitud() {
		return fechaEstatuSolicitud;
	}

	public void setFechaEstatuSolicitud(Date fechaEstatuSolicitud) {
		this.fechaEstatuSolicitud = fechaEstatuSolicitud;
	}

	public GenericoCatalogoBean getCompaniaCelular() {
		return companiaCelular;
	}

	public void setCompaniaCelular(GenericoCatalogoBean companiaCelular) {
		this.companiaCelular = companiaCelular;
	}

	public GenericoCatalogoBean getEstatusTraspaso() {
		return estatusTraspaso;
	}

	public void setEstatusTraspaso(GenericoCatalogoBean estatusTraspaso) {
		this.estatusTraspaso = estatusTraspaso;
	}

	public Date getFechaProximoCargo() {
		return fechaProximoCargo;
	}

	public void setFechaProximoCargo(Date fechaProximoCargo) {
		this.fechaProximoCargo = fechaProximoCargo;
	}

	public GenericoCatalogoBean getOrigenAportacion() {
		return origenAportacion;
	}

	public void setOrigenAportacion(GenericoCatalogoBean origenAportacion) {
		this.origenAportacion = origenAportacion;
	}

	public Integer getNumCargo() {
		return numCargo;
	}

	public void setNumCargo(Integer numCargo) {
		this.numCargo = numCargo;
	}

	public Date getFechaSuspension() {
		return fechaSuspension;
	}

	public void setFechaSuspension(Date fechaSuspension) {
		this.fechaSuspension = fechaSuspension;
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

	public Long getIdArchivoDomi() {
		return idArchivoDomi;
	}

	public void setIdArchivoDomi(Long idArchivoDomi) {
		this.idArchivoDomi = idArchivoDomi;
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