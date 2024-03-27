package mx.profuturo.nci.business.events.report.transaccion;

import java.io.Serializable;
import java.util.List;

import mx.profuturo.nci.business.events.RequestReadEvent;
import mx.profuturo.nci.business.vo.cifctrlreclasificacion.CCOrigDestReclasificacionVO;
import mx.profuturo.nci.business.vo.cifctrlreclasificacion.CCTotalSubcuentaReclasificacionVO;

public class CifrasControlReclaApoVolReportRequestReadEvent extends RequestReadEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	private String folio;
	private String proceso;
	private String subProceso;
	private String fechaLiquidacion;
	private List<CCOrigDestReclasificacionVO> reporteRegistrosVO;
	private List<CCTotalSubcuentaReclasificacionVO> totalesVO;
	private List<CCTotalSubcuentaReclasificacionVO> cargosVO;
	private List<CCTotalSubcuentaReclasificacionVO> abonosVO;

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getSubProceso() {
		return subProceso;
	}

	public void setSubProceso(String subProceso) {
		this.subProceso = subProceso;
	}

	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	public List<CCOrigDestReclasificacionVO> getReporteRegistrosVO() {
		return reporteRegistrosVO;
	}

	public void setReporteRegistrosVO(List<CCOrigDestReclasificacionVO> reporteRegistrosVO) {
		this.reporteRegistrosVO = reporteRegistrosVO;
	}

	public List<CCTotalSubcuentaReclasificacionVO> getTotalesVO() {
		return totalesVO;
	}

	public void setTotalesVO(List<CCTotalSubcuentaReclasificacionVO> totalesVO) {
		this.totalesVO = totalesVO;
	}

	public List<CCTotalSubcuentaReclasificacionVO> getCargosVO() {
		return cargosVO;
	}

	public void setCargosVO(List<CCTotalSubcuentaReclasificacionVO> cargosVO) {
		this.cargosVO = cargosVO;
	}

	public List<CCTotalSubcuentaReclasificacionVO> getAbonosVO() {
		return abonosVO;
	}

	public void setAbonosVO(List<CCTotalSubcuentaReclasificacionVO> abonosVO) {
		this.abonosVO = abonosVO;
	}

}
