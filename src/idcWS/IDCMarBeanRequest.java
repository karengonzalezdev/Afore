package idcWS;

import java.util.List;

import idcBusiness.ValidacionesVO;

public class IDCMarBeanRequest {

	private String folio;
	private List<String> estatusIdc;
	
	public ValidacionesVO validarRequest() {
		if(this.folio == null || this.folio.isEmpty()) {
			return new ValidacionesVO(Boolean.FALSE, "Por lo menos debe ingresar un folio.");
		}
		return new ValidacionesVO(Boolean.TRUE, "Validacion exitosa."); 
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@XmlElementWrapper(name="listaEstatusIdc")
	@XmlElement(name="estatusIdc")
	public List<String> getEstatusIdc() {
		return estatusIdc;
	}

	public void setEstatusIdc(List<String> estatusIdc) {
		this.estatusIdc = estatusIdc;
	}	
	
}
