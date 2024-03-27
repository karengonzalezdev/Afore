package mx.secure.nci.business.wrapped;

import mx.secure.nci.business.vo.MatrizConvivenciaVO;

public class MatrizConvivenciaFilter extends MatrizConvivenciaVO {
	
	private Short idSubproceso;
	private Boolean convive;
	
	public Short getIdSubproceso() {
		return idSubproceso;
	}
	public void setIdSubproceso(Short idSubproceso) {
		this.idSubproceso = idSubproceso;
	}

	public Boolean getConvive() {
		return convive;
	}
	public void setConvive(Boolean convive) {
		this.convive = convive;
	}

	
}
