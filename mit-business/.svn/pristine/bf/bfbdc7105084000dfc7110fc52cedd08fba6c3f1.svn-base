package mx.profuturo.nci.business.wrapped;

public class DomiParameterKeyFilter {
	private Short idTipoArchivo;
	private Short key;
	public Short getIdTipoArchivo() {
		return idTipoArchivo;
	}
	public void setIdTipoArchivo(Short idTipoArchivo) {
		this.idTipoArchivo = idTipoArchivo;
	}
	public Short getKey() {
		return key;
	}
	public void setKey(Short key) {
		this.key = key;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof DomiParameterKeyFilter){
			DomiParameterKeyFilter ob = (DomiParameterKeyFilter) obj;
			return key!=null && key.equals(ob.getKey()); 
		}
		return false;
	}

}
