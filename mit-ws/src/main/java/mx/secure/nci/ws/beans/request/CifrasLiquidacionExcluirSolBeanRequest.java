package mx.secure.nci.ws.beans.request;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import mx.secure.nci.business.vo.ValidacionesVO;
import mx.secure.nci.ws.beans.response.CifrasLiquidacionExcluirBean;

@XmlType(propOrder = { "listCuentas","usuario" })
public class CifrasLiquidacionExcluirSolBeanRequest {
	private List<CifrasLiquidacionExcluirBean> listCuentas;
	private String usuario;

	public CifrasLiquidacionExcluirSolBeanRequest() {
		super();
	}

	public ValidacionesVO validaRequest() {
		if (listCuentas.isEmpty()) {
			return new ValidacionesVO(Boolean.FALSE, "No se recibio ninguna solicitud a excluir");
		}

		return new ValidacionesVO(Boolean.TRUE, "Validaciones correctas");
	}
	
	public CifrasLiquidacionExcluirSolBeanRequest(List<CifrasLiquidacionExcluirBean> listCuentas) {
		super();
		this.listCuentas = listCuentas;
	}

	@XmlElementWrapper(name = "listCuentas")
	@XmlElement(name = "cuenta")
	public List<CifrasLiquidacionExcluirBean> getListCuentas() {
		return listCuentas;
	}

	public void setListCuentas(List<CifrasLiquidacionExcluirBean> listCuentas) {
		this.listCuentas = listCuentas;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
