package mx.secure.nci.ws.beans.request;

/**
 * @author AR998040
 *
 */
public class CifrasControlReclasecureReportRequest {
	private String folio;
	private String nombreArchivo;
	private String nombreProceso;
	private String nombreSubproceso;
	private String fechaLiquidacion;

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	public String getNombreSubproceso() {
		return nombreSubproceso;
	}

	public void setNombreSubproceso(String nombreSubproceso) {
		this.nombreSubproceso = nombreSubproceso;
	}
	
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}
	
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	@Override
	public String toString() {
		return "CifrasControlReclasecureReportRequest [folio=" + folio + ", nombreArchivo=" + nombreArchivo
				+ ", nombreProceso=" + nombreProceso + ", nombreSubproceso=" + nombreSubproceso + ", fechaLiquidacion="
				+ fechaLiquidacion + "]";
	}
	
}
