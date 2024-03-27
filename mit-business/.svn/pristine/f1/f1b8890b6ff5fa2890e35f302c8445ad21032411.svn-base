package mx.profuturo.nci.business.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class SolicitudReclasificacionApoVolVO {
	private String cveSolicitud;
	private Long numCtaInvdual;
	private String folioFus;
	private String folio;
	private BigDecimal montoReclasificacion;
	private Short idFondoApp;
	private String usuCaptura;
	private Date fehCap;
	private Short idOrigenCaptura;
	private Short idMedioNotificacion;
	private String medioNotificacion;
	private String destinoNotificacion;
	private Short idEstatusSolicitud;
	private Short idMotivoRechazo;
	private Short idTipoReclasificacion;
	private Short idTipoSiefore;
	private String usuCre;
	private Date fehCre;
	private ClienteVO solicitante;
	private List<DiversificacionSolicitudReclasificacionVO> lstDiversificacionVO;
	private List<DiversificacionSolicitudReclasificacionVO> lstSaldosOriginal;
	private List<IndicadorSolicitudReclasificacionVO> lstIndicadores;

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
	
	public String getFolio() {
		return folio;
	}
	
	public void setFolio(String folio) {
		this.folio = folio;
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

	public Date getFehCap() {
		return fehCap;
	}

	public void setFehCap(Date fehCap) {
		this.fehCap = fehCap;
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

	public Short getIdTipoReclasificacion() {
		return idTipoReclasificacion;
	}

	public void setIdTipoReclasificacion(Short idTipoReclasificacion) {
		this.idTipoReclasificacion = idTipoReclasificacion;
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

	public Date getFehCre() {
		return fehCre;
	}

	public void setFehCre(Date fehCre) {
		this.fehCre = fehCre;
	}

	public ClienteVO getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(ClienteVO solicitante) {
		this.solicitante = solicitante;
	}
	
	@SuppressWarnings("restriction")
	@XmlElementWrapper(name = "listaReclasificacion")
	@XmlElement(name = "reclasificacion")
	public List<DiversificacionSolicitudReclasificacionVO> getLstDiversificacionVO() {
		return lstDiversificacionVO;
	}

	public void setLstDiversificacionVO(List<DiversificacionSolicitudReclasificacionVO> lstDiversificacionVO) {
		this.lstDiversificacionVO = lstDiversificacionVO;
	}
	
	@SuppressWarnings("restriction")
	@XmlElementWrapper(name = "saldosOriginales")
	@XmlElement(name = "saldo")
	public List<DiversificacionSolicitudReclasificacionVO> getLstSaldosOriginal() {
		return lstSaldosOriginal;
	}

	public void setLstSaldosOriginal(List<DiversificacionSolicitudReclasificacionVO> lstSaldosOriginal) {
		this.lstSaldosOriginal = lstSaldosOriginal;
	}
	
	@SuppressWarnings("restriction")
	@XmlElementWrapper(name = "listaIndicadores")
	@XmlElement(name = "indicador")
	public List<IndicadorSolicitudReclasificacionVO> getLstIndicadores() {
		return lstIndicadores;
	}

	public void setLstIndicadores(List<IndicadorSolicitudReclasificacionVO> lstIndicadores) {
		this.lstIndicadores = lstIndicadores;
	}

	public String getMedioNotificacion() {
		return medioNotificacion;
	}

	public void setMedioNotificacion(String medioNotificacion) {
		this.medioNotificacion = medioNotificacion;
	}

}
