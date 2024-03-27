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


@XmlType(propOrder = { "cuenta","nss","curp","impote","noCargo","fechaCarga","excluir" })
public class CifrasLiquidacionSolicitudBean extends DefaultBeanResponse implements Serializable {
		private static final long serialVersionUID = 1L;	
		
		private Long cuenta;
		private Long nss;
		private String curp;
		private BigDecimal impote; 
		private Integer noCargo;
		private Date fechaCarga;
		private Integer excluir;
	
		public CifrasLiquidacionSolicitudBean(){
			super();
		}

		public CifrasLiquidacionSolicitudBean(String codRet, String msgRet, String desRet) {
			super(codRet, msgRet, desRet);
		}
		
		public CifrasLiquidacionSolicitudBean(Long cuenta, Long nss, String curp, BigDecimal impote,
				Integer noCargo, Date fechaCarga, Integer excluir) {
			super();
			this.cuenta = cuenta;
			this.nss = nss;
			this.curp = curp;
			this.impote = impote;
			this.noCargo = noCargo;
			this.fechaCarga = fechaCarga;
			this.excluir = excluir;
		}
	
		public Long getCuenta() {
			return cuenta;
		}

		public void setCuenta(Long cuenta) {
			this.cuenta = cuenta;
		}

		public Long getNss() {
			return nss;
		}

		public void setNss(Long nss) {
			this.nss = nss;
		}

		public String getCurp() {
			return curp;
		}

		public void setCurp(String curp) {
			this.curp = curp;
		}

		public BigDecimal getImpote() {
			return impote;
		}

		public void setImpote(BigDecimal impote) {
			this.impote = impote;
		}

		public Integer getNoCargo() {
			return noCargo;
		}

		public void setNoCargo(Integer noCargo) {
			this.noCargo = noCargo;
		}

		public Date getFechaCarga() {
			return fechaCarga;
		}

		public void setFechaCarga(Date fechaCarga) {
			this.fechaCarga = fechaCarga;
		}

		public Integer getExcluir() {
			return excluir;
		}

		public void setExcluir(Integer excluir) {
			this.excluir = excluir;
		}
		
}
