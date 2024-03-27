package mx.profuturo.nci.business.vo;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CifrasControlReporteVO {
	private String nombre;
	private Integer tipoReporte;
	private List secciones = new ArrayList();

	public JRDataSource getSeccionesDS() {
		return new JRBeanCollectionDataSource(secciones);
	}
	public JRDataSource getSeccionesSBC() {
		return new JRBeanCollectionDataSource(secciones);
	}
	public CifrasControlReporteVO() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List getSecciones() {
		return secciones;
	}
	public void setSecciones(List secciones) {
		this.secciones = secciones;
	}
	public void addSeccion(SeccionVO seccion)   
    {       
        this.secciones.add(seccion);   
    }
	public Integer getTipoReporte() {
		return tipoReporte;
	}
	public void setTipoReporte(Integer tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
}
