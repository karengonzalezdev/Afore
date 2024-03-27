package mx.secure.nci.ws.beans.response;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.business.util.UtilValidate;
import mx.secure.nci.business.vo.ObtenerCifrasControlVO;

public class ObtenerCifrasControlBeanResponse {

	private static final String CURRENCY_PATTERN = "$###,###,##0.00";
	private String codRet;
	private String msgRet;
	private String desRet;
	private List<ObtenerCifrasControlVO> lstCifrasMovimientosCif;
	private List<ObtenerCifrasControlVO> lstBancosyCuentasDestino;
	private List<ObtenerCifrasControlVO> lstTotalDevsecure;
	private BigDecimal importe;
	private BigDecimal monto;
	private BigDecimal total;
	private BigDecimal totalsecure;
	private BigDecimal totalDeTotales;
	private String importeFormat;
	private String montoFormat;
	private String totalFormat;
	private String totalsecureFormat;
	private String totalDeTotalesFormat;
	
	public ObtenerCifrasControlBeanResponse() {
	}

	public ObtenerCifrasControlBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}

	public ObtenerCifrasControlBeanResponse(String codRet, String msgRet, String desRet,
			List<ObtenerCifrasControlVO> lstCifrasMovimientosCif,
			List<ObtenerCifrasControlVO> lstBancosyCuentasDestino, List<ObtenerCifrasControlVO> lstTotalDevsecure) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.lstCifrasMovimientosCif = lstCifrasMovimientosCif;
		this.lstBancosyCuentasDestino = lstBancosyCuentasDestino;
		this.lstTotalDevsecure = lstTotalDevsecure;
	}

	public String getCodRet() {
		return codRet;
	}

	public void setCodRet(String codRet) {
		this.codRet = codRet;
	}

	public String getMsgRet() {
		return msgRet;
	}

	public void setMsgRet(String msgRet) {
		this.msgRet = msgRet;
	}

	public String getDesRet() {
		return desRet;
	}

	public void setDesRet(String desRet) {
		this.desRet = desRet;
	}
	
	@XmlElementWrapper(name = "lstcifrasMovimientosCif")
	@XmlElement(name = "cifrasMovimientosCif")
	public List<ObtenerCifrasControlVO> getLstCifrasMovimientosCif() {
		return lstCifrasMovimientosCif;
	}

	public void setLstCifrasMovimientosCif(List<ObtenerCifrasControlVO> lstCifrasMovimientosCif) {
		this.lstCifrasMovimientosCif = lstCifrasMovimientosCif;
	}

	@XmlElementWrapper(name = "lstBancosyCuentasDestino")
	@XmlElement(name = "bancosyCuentasDestino")
	public List<ObtenerCifrasControlVO> getLstBancosyCuentasDestino() {
		return lstBancosyCuentasDestino;
	}
	
	public void setLstBancosyCuentasDestino(List<ObtenerCifrasControlVO> lstBancosyCuentasDestino) {
		this.lstBancosyCuentasDestino = lstBancosyCuentasDestino;
	}

	@XmlElementWrapper(name = "lstTotalDevsecure")
	@XmlElement(name = "totalDevsecure")
	public List<ObtenerCifrasControlVO> getTotalDevsecure() {
		return lstTotalDevsecure;
	}

	public void setLstTotalDevsecure(List<ObtenerCifrasControlVO> lstTotalDevsecure) {
		this.lstTotalDevsecure = lstTotalDevsecure;
	}

	public String getImporteFormat() {
		return UtilValidate.parseString(CURRENCY_PATTERN, this.importe);
	}

	public void setImporteFormat(String importeFormat) {
		this.importeFormat = importeFormat;
	}
	
	public String getMontoFormat() {
		return UtilValidate.parseString(CURRENCY_PATTERN, this.monto);
	}

	public void setMontoFormat(String montoFormat) {
		this.montoFormat = montoFormat;
	}

	public String getTotalFormat() {
		return UtilValidate.parseString(CURRENCY_PATTERN, this.total);
	}

	public void setTotalFormat(String totalFormat) {
		this.totalFormat = totalFormat;
	}
	
	public String getTotalsecureFormat() {
		return UtilValidate.parseString(CURRENCY_PATTERN, this.totalsecure);
	}

	public void setTotalsecureFormat(String totalsecureFormat) {
		this.totalsecureFormat = totalsecureFormat;
	}
	
	public String getTotalDeTotalesFormat() {
		return UtilValidate.parseString(CURRENCY_PATTERN, this.totalDeTotales);
	}

	public void setTotalDeTotalesFormat(String totalDeTotalesFormat) {
		this.totalDeTotalesFormat = totalDeTotalesFormat;
	}
	
}
