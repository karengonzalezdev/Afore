package mx.secure.nci.business.report.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CifrasControlReclasecureDataReportBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<CifrasControlReclasecureDetalleReportBean> reporteRegistros = new ArrayList<CifrasControlReclasecureDetalleReportBean>();
	private List<CifrasControlReclasecureDetalleReportBean> totales = new ArrayList<CifrasControlReclasecureDetalleReportBean>();
	private List<CifrasControlReclasecureDetalleReportBean> cargos = new ArrayList<CifrasControlReclasecureDetalleReportBean>();
	private List<CifrasControlReclasecureDetalleReportBean> abonos = new ArrayList<CifrasControlReclasecureDetalleReportBean>();
	private List<CifrasControlReclasecureDetalleMixToReportBean> abonosCargos = new ArrayList<CifrasControlReclasecureDetalleMixToReportBean>();
	public List<CifrasControlReclasecureDetalleMixToReportBean> getAbonosCargos() {
		return abonosCargos;
	}

	public void setAbonosCargos(List<CifrasControlReclasecureDetalleMixToReportBean> abonosCargos) {
		this.abonosCargos = abonosCargos;
	}

	public List<CifrasControlReclasecureDetalleReportBean> getReporteRegistros() {
		return reporteRegistros;
	}

	public void setReporteRegistros(List<CifrasControlReclasecureDetalleReportBean> reporteRegistros) {
		this.reporteRegistros = reporteRegistros;
	}

	public List<CifrasControlReclasecureDetalleReportBean> getTotales() {
		return totales;
	}

	public void setTotales(List<CifrasControlReclasecureDetalleReportBean> totales) {
		this.totales = totales;
	}

	public List<CifrasControlReclasecureDetalleReportBean> getCargos() {
		return cargos;
	}

	public void setCargos(List<CifrasControlReclasecureDetalleReportBean> cargos) {
		this.cargos = cargos;
	}

	public List<CifrasControlReclasecureDetalleReportBean> getAbonos() {
		return abonos;
	}

	public void setAbonos(List<CifrasControlReclasecureDetalleReportBean> abonos) {
		this.abonos = abonos;
	}
	
	public void addReporteRegistros(CifrasControlReclasecureDetalleReportBean cifraControl) {
		this.reporteRegistros.add(cifraControl);
	}
	
	public JRDataSource getReporteRegistrosDS()     {       
	    return new JRBeanCollectionDataSource(reporteRegistros);   
	}
	
	public void addTotales(CifrasControlReclasecureDetalleReportBean cifraControl) {
		this.totales.add(cifraControl);
	}
	
	public JRDataSource getTotalesDS()     {       
	    return new JRBeanCollectionDataSource(totales);   
	}
	
	public void addCargos(CifrasControlReclasecureDetalleReportBean cifraControl) {
		this.cargos.add(cifraControl);
	}
	
	public JRDataSource getCargosDS()     {       
	    return new JRBeanCollectionDataSource(cargos);   
	}
	
	public void addAbonos(CifrasControlReclasecureDetalleReportBean cifraControl) {
		this.abonos.add(cifraControl);
	}
	
	public JRDataSource getAbonosDS()     {       
	    return new JRBeanCollectionDataSource(abonos);   
	}
	
	
	public void addAbonosCargos( CifrasControlReclasecureDetalleMixToReportBean abonosCargos  ) {
		this.abonosCargos.add( abonosCargos );
	}
	
	public JRDataSource getAbonosCargosDS() {
		return new JRBeanCollectionDataSource(abonosCargos);
	}
}
