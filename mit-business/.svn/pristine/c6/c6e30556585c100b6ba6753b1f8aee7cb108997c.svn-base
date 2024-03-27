package mx.profuturo.nci.business.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import mx.profuturo.nci.business.bean.DefaultBeanResponse;


@XmlType(propOrder = { "detalleSolicitudes", "usuario"})
public class CifrasLiquidacionConsultaSolVO extends DefaultBeanResponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List<CifrasLiquidacionSolVO> detalleSolicitudes;
	private String usuario;
	
	@XmlElementWrapper(name = "solicitudes")
	@XmlElement(name = "solicitud")
	public List<CifrasLiquidacionSolVO> getDetalleSolicitudes() {
		return detalleSolicitudes;
	}
	
	public void setDetalleSolicitudes(List<CifrasLiquidacionSolVO> detalleSolicitudes) {
		this.detalleSolicitudes = detalleSolicitudes;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
		
}