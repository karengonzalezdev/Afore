package mx.secure.nci.business.vo;

import java.io.Serializable;


public class ProcesoVO implements Serializable {

  /**
   * Generated by Eclipse.
   */
  private static final long serialVersionUID = 54388792350303991L;

  private Short idProceso;
    private String descProceso;

    public ProcesoVO(){}
    
    

    public ProcesoVO(Short idProceso, String descProceso) {
		super();
		this.idProceso = idProceso;
		this.descProceso = descProceso;
	}



	public ProcesoVO(final Short idProceso) {
		this.idProceso = idProceso;
	}



	public Short getIdProceso() {
        return this.idProceso;
    }

    public void setIdProceso(final Short idProceso) {
        this.idProceso = idProceso;
    }

	public String getDescProceso() {
		return descProceso;
	}

	public void setDescProceso(final String descProceso) {
		this.descProceso = descProceso;
	}

	@Override
	public String toString() {
		return "ProcesoVO "+ "["+ "idProceso = " + idProceso + ", descProceso = "+ descProceso + "]";
	}




}