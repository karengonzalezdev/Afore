package mx.secure.nci.ws.beans.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import mx.secure.nci.business.bean.DefaultBeanResponse;
import mx.secure.nci.ws.beans.DetalleSieforesBean;


@XmlType(propOrder = { "fechaInicio", "fechaFin", "detalleSieforeBean","usuario" })
public class CifrasLiquidacionBeanResponse extends DefaultBeanResponse implements Serializable {
		private static final long serialVersionUID = 1L;	
	
		private Date fechaInicio;
		private Date fechaFin;
		private List<DetalleSieforesBean> detalleSieforeBean;
		private String usuario;

	
		public CifrasLiquidacionBeanResponse(){
			super();
		}
	
		public CifrasLiquidacionBeanResponse(String codRet, String msgRet, String desRet) {
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
		public List<DetalleSieforesBean> getDetalleSieforeBean() {
			return detalleSieforeBean;
		}
		

		public void setDetalleSieforeBean(List<DetalleSieforesBean> detalleSieforeBean) {
			this.detalleSieforeBean = detalleSieforeBean;
		}

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
		
}
