package mx.secure.nci.ws.beans;

public class ReportBean 
{
	private byte[] archivo;
	private String nombre;
	
	
	
	public ReportBean(){}
	
	/**
	 * @param archivo
	 * @param nombre
	 */
	public ReportBean(byte[] archivo, String nombre) {
		this.archivo = archivo;
		this.nombre = nombre;
	}
	public byte[] getArchivo() {
		return archivo;
	}
	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	
	

}
