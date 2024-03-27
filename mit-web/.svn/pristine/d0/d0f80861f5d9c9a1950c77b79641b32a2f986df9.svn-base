package mx.profuturo.nci.web.beans;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class ProcesoBean implements Serializable
{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Short idProceso;
    private String descProceso;
        
    public ProcesoBean(){}
    
    public ProcesoBean(Short idProceso) {
		this.idProceso = idProceso;
	}

	public Short getIdProceso() {
        return this.idProceso;
    }

    public void setIdProceso(Short idProceso) {
        this.idProceso = idProceso;
    }

	public String getDescProceso() {
		return descProceso;
	}

	public void setDescProceso(String descProceso) {
		this.descProceso = descProceso;
	}


	
	
	  @Override
	  public String toString() {
	    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).appendSuper(super.toString())
	        .append("idProceso", idProceso).append("descProceso", descProceso).toString();
	  }
        
}