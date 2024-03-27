package mx.secure.nci.ws.beans.request;

import java.math.BigDecimal;
import java.util.Date;


import mx.secure.nci.business.vo.ValidacionesVO;

public class CifrasLiquidacionConsultaSolBeanRequest {
	private Long cuenta;
	private Long nss;
	private String curp;
	private String usuario;

	public CifrasLiquidacionConsultaSolBeanRequest() {
		super();
	}
	
	public CifrasLiquidacionConsultaSolBeanRequest(Long cuenta, Long nss, String curp) {
		super();
		this.cuenta = cuenta;
		this.nss = nss;
		this.curp = curp;
	}

	public ValidacionesVO validaRequest() {
		if (cuenta == null && nss == null && curp == null) {
			return new ValidacionesVO(Boolean.FALSE, "Se debe recibir por lo menos algun parametro de consulta");
		}

		return new ValidacionesVO(Boolean.TRUE, "Validaciones correctas");
	}
	
	public Long getCuenta() {
		return cuenta;
	}

	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
	}

	public Long getNss() {
		return nss;
	}

	public void setNss(Long nss) {
		this.nss = nss;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
}
