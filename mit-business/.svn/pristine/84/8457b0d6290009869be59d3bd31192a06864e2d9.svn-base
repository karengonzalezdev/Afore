package mx.profuturo.nci.business.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import mx.profuturo.nci.business.bean.DefaultBeanResponse;


@XmlType(propOrder = { "fechaInicio", "fechaFin", "detalleSieforeVO","usuario"})
public class CifrasLiquidacionVO extends DefaultBeanResponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Date fechaInicio;
	private Date fechaFin;
	private List<DetalleSieforesVO> detalleSieforeVO;
	private String usuario;

	public CifrasLiquidacionVO(){
		super();
	}

	public CifrasLiquidacionVO(String codRet, String msgRet, String desRet) {
		super(codRet, msgRet, desRet);
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	@XmlElementWrapper(name = "siefores")
	@XmlElement(name = "siefore")
	public List<DetalleSieforesVO> getDetalleSieforeVO() {
		return detalleSieforeVO;
	}
	

	public void setDetalleSieforeVO(List<DetalleSieforesVO> detalleSieforeVO) {
		this.detalleSieforeVO = detalleSieforeVO;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}