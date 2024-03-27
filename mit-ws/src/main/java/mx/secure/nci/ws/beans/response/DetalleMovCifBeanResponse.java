package mx.secure.nci.ws.beans.response;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import mx.secure.nci.business.util.UtilValidate;
import mx.secure.nci.business.vo.DetalleMovimientosCifVO;

public class DetalleMovCifBeanResponse 
{
	private static final String CURRENCY_PATTERN = "$###,###,##0.00";
	private static final String COUNT_PATTERN = "###,###,##0";
	private String codRet;
	private String msgRet;
	private String desRet;
	private List<DetalleMovimientosCifVO> lstDetalleMovCif;
	private Integer totalCasosOperados;
	private BigDecimal totalImportePesos;
	private String totalCasosOperadosFormat;
	private String totalImportePesosFormat;
	
		
	public DetalleMovCifBeanResponse(){}
	
	public DetalleMovCifBeanResponse(String codRet, String msgRet, String desRet) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
	}
	
	/**
	 * @param reportBean
	 */
	public DetalleMovCifBeanResponse(String codRet, String msgRet, String desRet,
			List<DetalleMovimientosCifVO> lstDetalleMovCif) {
		this.codRet = codRet;
		this.msgRet = msgRet;
		this.desRet = desRet;
		this.lstDetalleMovCif = lstDetalleMovCif;
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
	
	@XmlElementWrapper(name = "listaDetalleMovCif")
	@XmlElement(name = "detalleMovCif")
	public List<DetalleMovimientosCifVO> getLstDetalleMovCif() {
		return lstDetalleMovCif;
	}

	public void setLstDetalleMovCif(List<DetalleMovimientosCifVO> lstDetalleMovCif) {
		this.lstDetalleMovCif = lstDetalleMovCif;
	}

	public Integer getTotalCasosOperados() {
		this.totalCasosOperados = new Integer(0);
		if(this.lstDetalleMovCif != null && this.lstDetalleMovCif.size() > 0) {
			for(DetalleMovimientosCifVO item : lstDetalleMovCif) {
				this.totalCasosOperados += item.getCasosOperados();
			}
		}
		return this.totalCasosOperados;
	}

	public void setTotalCasosOperados(Integer totalCasosOperados) {
		this.totalCasosOperados = totalCasosOperados;
	}

	public BigDecimal getTotalImportePesos() {
		this.totalImportePesos = new BigDecimal(0);
		if(this.lstDetalleMovCif != null && this.lstDetalleMovCif.size() > 0) {
			for(DetalleMovimientosCifVO item : lstDetalleMovCif) {
				this.totalImportePesos = this.totalImportePesos.add(item.getImportePesos());
			}
		}
		return this.totalImportePesos;
	}

	public void setTotalImportePesos(BigDecimal totalImportePesos) {
		this.totalImportePesos = totalImportePesos;
	}

	public String getTotalCasosOperadosFormat() {
		return UtilValidate.parseString(COUNT_PATTERN, this.totalCasosOperados);
	}

	public void setTotalCasosOperadosFormat(String totalCasosOperadosFormat) {
		this.totalCasosOperadosFormat = totalCasosOperadosFormat;
	}

	public String getTotalImportePesosFormat() {
		return UtilValidate.parseString(CURRENCY_PATTERN, this.totalImportePesos);
	}

	public void setTotalImportePesosFormat(String totalImportePesosFormat) {
		this.totalImportePesosFormat = totalImportePesosFormat;
	}
	
}