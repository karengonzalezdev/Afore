package mx.profuturo.nci.business.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ConfiguracionSubprocesoVO extends AbstractAuditoriaVO
{
	
	private Short idMovimiento;
	private Short idTipoMovimiento;
    private SubprocesoVO 	subproceso;
    private ProcesoVO 		proceso;
    private SaldoOperaVO 	saldoOpera;
    private String 			mascaraArchivo;
    private String 			rutaFilesystem;
    private List<Short> 	idsFamilia;
        
    public ConfiguracionSubprocesoVO()
    {
    	super();
    	this.proceso = new ProcesoVO();
    	this.subproceso = new SubprocesoVO();
    	this.idsFamilia = new ArrayList<Short>();
    }
    
    
	public ConfiguracionSubprocesoVO(SubprocesoVO subproceso,
									 ProcesoVO proceso, 
									 SaldoOperaVO saldoOpera, 
									 String mascaraArchivo,
									 String rutaFilesystem,
									 Date fechaCreacion,
									 String usuarioCreacion, 
									 Date fechaActualizacion,
									 String usuarioActualizacion									 
									) 
	{
		super(fechaCreacion, usuarioCreacion, fechaActualizacion,usuarioActualizacion);
		
		this.subproceso = subproceso;
		this.proceso = proceso;
		this.saldoOpera = saldoOpera;
		this.mascaraArchivo = mascaraArchivo;
		this.rutaFilesystem = rutaFilesystem;
		
	}

    public List<Short> getIdsFamilia() {
		return idsFamilia;
	}
	public void setIdsFamilia(List<Short> idsFamilia) {
		this.idsFamilia = idsFamilia;
	}
	public Short getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(Short idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public Short getIdTipoMovimiento() {
		return idTipoMovimiento;
	}
	public void setIdTipoMovimiento(Short idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}

	public SubprocesoVO getSubproceso() {
		return subproceso;
	}


	public void setSubproceso(SubprocesoVO subproceso) {
		this.subproceso = subproceso;
	}


	public ProcesoVO getProceso() {
		return proceso;
	}


	public void setProceso(ProcesoVO proceso) {
		this.proceso = proceso;
	}


	public SaldoOperaVO getSaldoOpera() {
		return saldoOpera;
	}


	public void setSaldoOpera(SaldoOperaVO saldoOpera) {
		this.saldoOpera = saldoOpera;
	}


	public String getMascaraArchivo() {
		return mascaraArchivo;
	}


	public void setMascaraArchivo(String mascaraArchivo) {
		this.mascaraArchivo = mascaraArchivo;
	}


	public String getRutaFilesystem() {
		return rutaFilesystem;
	}


	public void setRutaFilesystem(String rutaFilesystem) {
		this.rutaFilesystem = rutaFilesystem;
	}



	@Override
	  public String toString() 
	{
	    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)	    	
	    	.append("subproceso", subproceso)
	        .append("proceso", proceso)
	        .append("saldoOpera", saldoOpera)
	        .append("mascaraArchivo", mascaraArchivo)
	        .append("rutaFilesystem", rutaFilesystem)
	        .appendSuper(super.toString())	        	
	        .toString();
	  }
    
    
    
        
    

    
    
    
    

}
