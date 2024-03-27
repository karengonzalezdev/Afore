package mx.secure.nci.ws.beans.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import mx.secure.nci.business.bean.DefaultBeanResponse;



@XmlType(propOrder = { "cuenta","noCargo" })
public class CifrasLiquidacionExcluirBean implements Serializable {
		private static final long serialVersionUID = 1L;	
		
		private Long cuenta;
		private Integer noCargo;
		
		
		public CifrasLiquidacionExcluirBean(){
			super();
		}
	
		public CifrasLiquidacionExcluirBean(Long cuenta,Integer noCargo) {
			super();
			this.cuenta = cuenta;
			this.noCargo =noCargo;
		}

		public Long getCuenta() {
			return cuenta;
		}

		public void setCuenta(Long cuenta) {
			this.cuenta = cuenta;
		}

		public Integer getNoCargo() {
			return noCargo;
		}

		public void setNoCargo(Integer noCargo) {
			this.noCargo = noCargo;
		}	
		
		
			
}
