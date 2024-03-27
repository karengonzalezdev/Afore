package mx.secure.nci.business.wrapped;

import java.util.List;

import mx.secure.nci.business.vo.PrincipalVO;

public class PrincipalFilter extends PrincipalVO{
	private List<String> folios;

	public List<String> getFolios() {
		return folios;
	}

	public void setFolios(List<String> folios) {
		this.folios = folios;
	}
	
	
}