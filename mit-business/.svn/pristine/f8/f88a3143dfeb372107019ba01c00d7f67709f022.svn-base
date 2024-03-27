package mx.profuturo.nci.business.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@XmlType(propOrder = { "idSiefore", "siefore", "detalleFondosVO" })
public class DetalleSieforesVO
{
	private Integer idSiefore;
	private String siefore;
	private List<DetalleFondosVO> detalleFondosVO;
	
	
	public DetalleSieforesVO() {
		super();
	}
	public DetalleSieforesVO(Integer idSiefore, String siefore, List<DetalleFondosVO> detalleFondosVO) {
		super();
		this.idSiefore = idSiefore;
		this.siefore = siefore;
		this.detalleFondosVO = detalleFondosVO;
	}
	public Integer getIdSiefore() {
		return idSiefore;
	}
	public void setIdSiefore(Integer idSiefore) {
		this.idSiefore = idSiefore;
	}
	public String getSiefore() {
		return siefore;
	}
	public void setSiefore(String siefore) {
		this.siefore = siefore;
	}
	
	@XmlElementWrapper(name = "fondos")
	@XmlElement(name = "fondo")
	public List<DetalleFondosVO> getDetalleFondosVO() {
		return detalleFondosVO;
	}
	public void setDetalleFondosVO(List<DetalleFondosVO> detalleFondosVO) {
		this.detalleFondosVO = detalleFondosVO;
	}
	
	
	
	
}