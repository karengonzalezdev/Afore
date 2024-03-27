package mx.profuturo.nci.business.report.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CifrasControlReclaApoVolDataReportBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<CifrasControlReclaApoVolDetalleReportBean> reporteRegistros = new ArrayList<CifrasControlReclaApoVolDetalleReportBean>();
	private List<CifrasControlReclaApoVolDetalleReportBean> totales = new ArrayList<CifrasControlReclaApoVolDetalleReportBean>();
	private List<CifrasControlReclaApoVolDetalleReportBean> cargos = new ArrayList<CifrasControlReclaApoVolDetalleReportBean>();
	private List<CifrasControlReclaApoVolDetalleReportBean> abonos = new ArrayList<CifrasControlReclaApoVolDetalleReportBean>();
	private List<CifrasControlReclaApoVolDetalleMixToReportBean> abonosCargos = new ArrayList<CifrasControlReclaApoVolDetalleMixToReportBean>();
	public List<CifrasControlReclaApoVolDetalleMixToReportBean> getAbonosCargos() {
		return abonosCargos;
	}

	public void setAbonosCargos(List<CifrasControlReclaApoVolDetalleMixToReportBean> abonosCargos) {
		this.abonosCargos = abonosCargos;
	}

	public List<CifrasControlReclaApoVolDetalleReportBean> getReporteRegistros() {
		return reporteRegistros;
	}

	public void setReporteRegistros(List<CifrasControlReclaApoVolDetalleReportBean> reporteRegistros) {
		this.reporteRegistros = reporteRegistros;
	}

	public List<CifrasControlReclaApoVolDetalleReportBean> getTotales() {
		return totales;
	}

	public void setTotales(List<CifrasControlReclaApoVolDetalleReportBean> totales) {
		this.totales = totales;
	}

	public List<CifrasControlReclaApoVolDetalleReportBean> getCargos() {
		return cargos;
	}

	public void setCargos(List<CifrasControlReclaApoVolDetalleReportBean> cargos) {
		this.cargos = cargos;
	}

	public List<CifrasControlReclaApoVolDetalleReportBean> getAbonos() {
		return abonos;
	}

	public void setAbonos(List<CifrasControlReclaApoVolDetalleReportBean> abonos) {
		this.abonos = abonos;
	}
	
	public void addReporteRegistros(CifrasControlReclaApoVolDetalleReportBean cifraControl) {
		this.reporteRegistros.add(cifraControl);
	}
	
	public JRDataSource getReporteRegistrosDS()     {       
	    return new JRBeanCollectionDataSource(reporteRegistros);   
	}
	
	public void addTotales(CifrasControlReclaApoVolDetalleReportBean cifraControl) {
		this.totales.add(cifraControl);
	}
	
	public JRDataSource getTotalesDS()     {       
	    return new JRBeanCollectionDataSource(totales);   
	}
	
	public void addCargos(CifrasControlReclaApoVolDetalleReportBean cifraControl) {
		this.cargos.add(cifraControl);
	}
	
	public JRDataSource getCargosDS()     {       
	    return new JRBeanCollectionDataSource(cargos);   
	}
	
	public void addAbonos(CifrasControlReclaApoVolDetalleReportBean cifraControl) {
		this.abonos.add(cifraControl);
	}
	
	public JRDataSource getAbonosDS()     {       
	    return new JRBeanCollectionDataSource(abonos);   
	}
	
	
	public void addAbonosCargos( CifrasControlReclaApoVolDetalleMixToReportBean abonosCargos  ) {
		this.abonosCargos.add( abonosCargos );
	}
	
	public JRDataSource getAbonosCargosDS() {
		return new JRBeanCollectionDataSource(abonosCargos);
	}
}
