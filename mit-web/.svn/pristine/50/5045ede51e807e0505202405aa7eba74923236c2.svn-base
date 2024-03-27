package mx.profuturo.nci.web.beans;

import java.util.Date;
import java.util.List;

import mx.profuturo.nci.business.vo.cif.Profuturo_CIFVo;

public class MovimientosCIFParamBean {

	private NCI_CIFDataFilter filtros;
	private Date maxDate;
	private Date minDate;
	private List<NCI_CIFDataBean> resultados;
	private List<NCI_CIFDataBean> cuentasAenviar;
	private List<Profuturo_CIFVo> reporte;
	private NCI_CIFDataBean movDetalle;
	private String mensaje;
	
	public NCI_CIFDataFilter getFiltros() {
		if(filtros == null) {
			filtros = new NCI_CIFDataFilter();
		}
		return filtros;
	}
	
	public void setFiltros(NCI_CIFDataFilter filtros) {
		this.filtros = filtros;
	}
	public List<NCI_CIFDataBean> getResultados() {
		return resultados;
	}
	public void setResultados(List<NCI_CIFDataBean> resultados) {
		this.resultados = resultados;
	}

	public List<NCI_CIFDataBean> getCuentasAenviar() {
		return cuentasAenviar;
	}
	public void setCuentasAenviar(List<NCI_CIFDataBean> cuentasAenviar) {
		this.cuentasAenviar = cuentasAenviar;
	}

	public NCI_CIFDataBean getMovDetalle() {
		return movDetalle;
	}
	public void setMovDetalle(NCI_CIFDataBean movDetalle) {
		this.movDetalle = movDetalle;
	}

	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public Date getMinDate() {
		return minDate;
	}
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public List<Profuturo_CIFVo> getReporte() {
		return reporte;
	}
	public void setReporte(List<Profuturo_CIFVo> reporte) {
		this.reporte = reporte;
	}
	
}
