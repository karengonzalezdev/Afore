package mx.secure.nci.ws.beans.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import mx.secure.nci.business.bean.DefaultBeanResponse;
import mx.secure.nci.business.vo.CifrasLiquidacionSolVO;
import mx.secure.nci.ws.beans.CifrasLiquidacionSolBean;
import mx.secure.nci.ws.beans.DetalleSieforesBean;


@XmlType(propOrder = { "fechaInicio", "fechaFin", "detalleSieforeBean","usuario","detalleSolicitudes" })
public class CifrasLiquidacionExportableBeanResponse extends DefaultBeanResponse implements Serializable {
		private static final long serialVersionUID = 1L;	
	
		private String fechaInicio;
		private String fechaFin;
		private List<DetalleSieforesBean> detalleSieforeBean;
		private String usuario;
		private List<CifrasLiquidacionSolicitudBean> detalleSolicitudes;
	
		public CifrasLiquidacionExportableBeanResponse(){
			super();
		}
	
		public CifrasLiquidacionExportableBeanResponse(String codRet, String msgRet, String desRet) {
			super(codRet, msgRet, desRet);
		}
		
		public String getFechaInicio() {
			return fechaInicio;
		}

		public void setFechaInicio(String fechaInicio) {
			this.fechaInicio = fechaInicio;
		}

		public String getFechaFin() {
			return fechaFin;
		}

		public void setFechaFin(String fechaFin) {
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

		@XmlElementWrapper(name = "solicitudes")
		@XmlElement(name = "solicitud")
		public List<CifrasLiquidacionSolicitudBean> getDetalleSolicitudes() {
			return detalleSolicitudes;
		}
		public void setDetalleSolicitudes(List<CifrasLiquidacionSolicitudBean> detalleSolicitudes) {
			this.detalleSolicitudes = detalleSolicitudes;
		}
		
		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}
			
}
