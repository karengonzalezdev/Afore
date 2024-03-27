package mx.secure.nci.ws.beans.request;

import java.io.Serializable;

import mx.secure.nci.business.util.UtilValidate;
import mx.secure.nci.business.vo.ValidacionesVO;

/**
 * Clase que contiene los parametros de entrada (request)
 * 
 * @author E0000548
 * @category MANTENIMIENTO
 * @date 28/03/2022
 */
public class TablaDinamicaArchDomiBeanRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String folio;
	
	/**
	 * Constructor
	 */
	public TablaDinamicaArchDomiBeanRequest() {
	}
	
	/**
	 * Constructor
	 * @param folio
	 */
	public TablaDinamicaArchDomiBeanRequest(String folio) {
		this.folio = folio;
	}
	
	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	/**
	 * Metodo que valida los parametros de entrada
	 * @return ValidacionesVO
	 */
	public ValidacionesVO validarRequest() {

		if (!UtilValidate.validaFormatoIntegerF4(this.folio, 20)) {
			return new ValidacionesVO(Boolean.FALSE, "Validar formato de folio, LONG MAX 20");
		}

		return new ValidacionesVO(Boolean.TRUE, "Validacion exitosa.");
	}
}
