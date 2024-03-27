package mx.secure.nci.ws.beans.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import mx.secure.nci.business.bean.DefaultBeanResponse;
import mx.secure.nci.business.vo.CifrasLiquidacionSolicitudVO;
import mx.secure.nci.ws.beans.DetalleSieforesBean;


@XmlType(propOrder = { "solicitudesExcluidas", "usuario" })
public class CifrasLiquidacionExcluirSolBeanResponce extends DefaultBeanResponse implements Serializable {
		private static final long serialVersionUID = 1L;	
		
		private boolean solicitudesExcluidas;
		private String usuario;
	
		public CifrasLiquidacionExcluirSolBeanResponce(){
			super();
		}

		public CifrasLiquidacionExcluirSolBeanResponce(String codRet, String msgRet, String desRet) {
			super(codRet, msgRet, desRet);
		}
		
		public CifrasLiquidacionExcluirSolBeanResponce(boolean solicitudesExcluidas) {
			super();
			this.solicitudesExcluidas = solicitudesExcluidas;
		}

		public boolean isSolicitudesExcluidas() {
			return solicitudesExcluidas;
		}

		public void setSolicitudesExcluidas(boolean solicitudesExcluidas) {
			this.solicitudesExcluidas = solicitudesExcluidas;
		}

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

	

		
}
