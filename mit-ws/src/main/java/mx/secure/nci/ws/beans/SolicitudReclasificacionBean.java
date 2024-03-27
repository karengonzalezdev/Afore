package mx.secure.nci.ws.beans;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class SolicitudReclasificacionBean {

	private String cveSolicitud;
	private Long numCtaInvdual;
	private String folioFus;
	private BigDecimal montoReclasificacion;
	private Short idFondoApp;
	private String usuCaptura;
	private Short idOrigenCaptura;
	private Short idMedioNotificacion;
	private String medioNotificacion;
	private String destinoNotificacion;
	private Short idEstatusSolicitud;
	private Short idMotivoRechazo;
	private Short idTipoSiefore;
	private String usuCre;
	private Short idTipoReclasificacion;
	private SolicitanteBean solicitante;
	private List<DiversificacionSolicitudReclasificacionBean> lstDiversificacionBean;
	private List<DiversificacionSolicitudReclasificacionBean> lstSaldosOriginal;
	private List<GenericoCatalogoBean> lstIndicadores;

	public String getCveSolicitud() {
		return cveSolicitud;
	}

	public void setCveSolicitud(String cveSolicitud) {
		this.cveSolicitud = cveSolicitud;
	}

	public Long getNumCtaInvdual() {
		return numCtaInvdual;
	}

	public void setNumCtaInvdual(Long numCtaInvdual) {
		this.numCtaInvdual = numCtaInvdual;
	}

	public String getFolioFus() {
		return folioFus;
	}

	public void setFolioFus(String folioFus) {
		this.folioFus = folioFus;
	}

	public BigDecimal getMontoReclasificacion() {
		return montoReclasificacion;
	}

	public void setMontoReclasificacion(BigDecimal montoReclasificacion) {
		this.montoReclasificacion = montoReclasificacion;
	}

	public Short getIdFondoApp() {
		return idFondoApp;
	}

	public void setIdFondoApp(Short idFondoApp) {
		this.idFondoApp = idFondoApp;
	}

	public String getUsuCaptura() {
		return usuCaptura;
	}

	public void setUsuCaptura(String usuCaptura) {
		this.usuCaptura = usuCaptura;
	}

	public Short getIdOrigenCaptura() {
		return idOrigenCaptura;
	}

	public void setIdOrigenCaptura(Short idOrigenCaptura) {
		this.idOrigenCaptura = idOrigenCaptura;
	}

	public Short getIdMedioNotificacion() {
		return idMedioNotificacion;
	}

	public void setIdMedioNotificacion(Short idMedioNotificacion) {
		this.idMedioNotificacion = idMedioNotificacion;
	}

	public String getDestinoNotificacion() {
		return destinoNotificacion;
	}

	public void setDestinoNotificacion(String destinoNotificacion) {
		this.destinoNotificacion = destinoNotificacion;
	}

	public Short getIdEstatusSolicitud() {
		return idEstatusSolicitud;
	}

	public void setIdEstatusSolicitud(Short idEstatusSolicitud) {
		this.idEstatusSolicitud = idEstatusSolicitud;
	}

	public Short getIdMotivoRechazo() {
		return idMotivoRechazo;
	}

	public void setIdMotivoRechazo(Short idMotivoRechazo) {
		this.idMotivoRechazo = idMotivoRechazo;
	}

	public Short getIdTipoSiefore() {
		return idTipoSiefore;
	}

	public void setIdTipoSiefore(Short idTipoSiefore) {
		this.idTipoSiefore = idTipoSiefore;
	}

	public String getUsuCre() {
		return usuCre;
	}

	public void setUsuCre(String usuCre) {
		this.usuCre = usuCre;
	}

	public Short getIdTipoReclasificacion() {
		return idTipoReclasificacion;
	}

	public void setIdTipoReclasificacion(Short idTipoReclasificacion) {
		this.idTipoReclasificacion = idTipoReclasificacion;
	}

	public SolicitanteBean getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(SolicitanteBean solicitante) {
		this.solicitante = solicitante;
	}

	@XmlElementWrapper(name = "listaReclasificacion")
	@XmlElement(name = "reclasificacion")
	public List<DiversificacionSolicitudReclasificacionBean> getLstDiversificacionBean() {
		return lstDiversificacionBean;
	}

	public void setLstDiversificacionBean(List<DiversificacionSolicitudReclasificacionBean> lstDiversificacionBean) {
		this.lstDiversificacionBean = lstDiversificacionBean;
	}

	@XmlElementWrapper(name = "saldosOriginales")
	@XmlElement(name = "saldo")
	public List<DiversificacionSolicitudReclasificacionBean> getLstSaldosOriginal() {
		return lstSaldosOriginal;
	}

	public void setLstSaldosOriginal(List<DiversificacionSolicitudReclasificacionBean> lstSaldosOriginal) {
		this.lstSaldosOriginal = lstSaldosOriginal;
	}

	@XmlElementWrapper(name = "listaIndicadores")
	@XmlElement(name = "indicador")
	public List<GenericoCatalogoBean> getLstIndicadores() {
		return lstIndicadores;
	}

	public void setLstIndicadores(List<GenericoCatalogoBean> lstIndicadores) {
		this.lstIndicadores = lstIndicadores;
	}

	public String getMedioNotificacion() {
		return medioNotificacion;
	}

	public void setMedioNotificacion(String medioNotificacion) {
		this.medioNotificacion = medioNotificacion;
	}

}
