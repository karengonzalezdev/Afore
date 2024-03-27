package mx.secure.nci.business.vo;

public class FolioBancoVO extends AbstractAuditoriaVO {
	private String folio;
	
	public FolioBancoVO() {	}
	
	public FolioBancoVO(String folio) {
		this.folio=folio;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

}
