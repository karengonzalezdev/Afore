package mx.secure.nci.ws.beans.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import mx.secure.nci.business.bean.DefaultBeanResponse;



@XmlType(propOrder = { "detalleSolicitudes","usuario" })
public class CifrasLiquidacionConsultaSolBeanResponse extends DefaultBeanResponse implements Serializable {
		private static final long serialVersionUID = 1L;	
		
		private List<CifrasLiquidacionSolicitudBean> detalleSolicitudes;
		private String usuario;
		
		public CifrasLiquidacionConsultaSolBeanResponse(){
			super();
		}

		public CifrasLiquidacionConsultaSolBeanResponse(String codRet, String msgRet, String desRet) {
			super(codRet, msgRet, desRet);
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
