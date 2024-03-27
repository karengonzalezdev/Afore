package mx.secure.nci.ws.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "idSiefore", "siefore", "detalleFondosBean" })
public class DetalleSieforesBean extends AbstractAuditoriaBean {
	private Integer idSiefore;
	private String siefore;
	private List<DetalleFondosBean> detalleFondosBean;

	public DetalleSieforesBean() {
		super();
	}

	public DetalleSieforesBean(Integer idSiefore, String siefore, List<DetalleFondosBean> detalleFondosBean) {
		super();
		this.idSiefore = idSiefore;
		this.siefore = siefore;
		this.detalleFondosBean = detalleFondosBean;
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
	public List<DetalleFondosBean> getDetalleFondosBean() {
		return detalleFondosBean;
	}

	public void setDetalleFondosBean(List<DetalleFondosBean> detalleFondosBean) {
		this.detalleFondosBean = detalleFondosBean;
	}
	
	
	

}