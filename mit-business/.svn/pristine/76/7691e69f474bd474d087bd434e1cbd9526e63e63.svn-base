package mx.profuturo.nci.business.vo;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CifrasGeneralesReporteVO {
	private String reporte;
	private List listaCifras = new ArrayList();
	
	public JRDataSource getListaCifras() {
		return new JRBeanCollectionDataSource(listaCifras);
	}
	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
	public void setListaCifras(List listaCifras) {
		this.listaCifras = listaCifras;
	}
	
}
