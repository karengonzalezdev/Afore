package mx.secure.nci.business.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import mx.secure.nci.business.util.UtilValidate;

public class ServiciosContracargosVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final String CURRENCY_PATTERN = "$###,###,##0.00";
	
	private String folio;
	private String siefore;
	private String claveSubprcs;
	private String bancoOrigen;
	private String cuentaBancoOrigen;
	private String bancoDestino;
	private String cuentaBancoDestino;
	private BigDecimal monto;
	private String montoPesosFormat;
	
	public ServiciosContracargosVO() {
	}

	public ServiciosContracargosVO(String folio) {
		this.folio = folio;
	}
	
	public ServiciosContracargosVO(String bancoOrigen, String cuentaBancoOrigen, BigDecimal monto) {
		this.bancoOrigen = bancoOrigen;
		this.cuentaBancoOrigen = cuentaBancoOrigen;
		this.monto = monto;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getSiefore() {
		return siefore;
	}

	public void setSiefore(String siefore) {
		this.siefore = siefore;
	}

	public String getClaveSubprcs() {
		return claveSubprcs;
	}

	public void setClaveSubprcs(String claveSubprcs) {
		this.claveSubprcs = claveSubprcs;
	}

	public String getBancoOrigen() {
		return bancoOrigen;
	}

	public void setBancoOrigen(String bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}

	public String getCuentaBancoOrigen() {
		return cuentaBancoOrigen;
	}

	public void setCuentaBancoOrigen(String cuentaBancoOrigen) {
		this.cuentaBancoOrigen = cuentaBancoOrigen;
	}
	
	public String getBancoDestino() {
		return bancoDestino;
	}

	public void setBancoDestino(String bancoDestino) {
		this.bancoDestino = bancoDestino;
	}

	public String getCuentaBancoDestino() {
		return cuentaBancoDestino;
	}

	public void setCuentaBancoDestino(String cuentaBancoDestino) {
		this.cuentaBancoDestino = cuentaBancoDestino;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getMontoPesosFormat() {
		return UtilValidate.parseString(CURRENCY_PATTERN, this.montoPesosFormat);
	}

	public void setMontoPesosFormat(String montoPesosFormat) {
		this.montoPesosFormat = montoPesosFormat;
	}
	
}
