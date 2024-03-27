package mx.secure.nci.ws.beans.request;

import java.io.Serializable;

import mx.secure.nci.business.util.UtilValidate;
import mx.secure.nci.business.vo.ValidacionesVO;

/**
 * Clase que contiene los parametros de entrada (request)
 * 
 * @author E0000548 | Héctor Manuel Loza Cruz
 * @category MANTENIMIENTO
 * @date 20/04/2022
 */
public class TipoProcesamientoDomisBeanRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	private String valor;
	private String usuario;
	
	/**
	 * Constuctor
	 */
	public TipoProcesamientoDomisBeanRequest() {
	}
	
	/**
	 * Constuctor
	 * @param valor
	 * @param usuario
	 */
	public TipoProcesamientoDomisBeanRequest(String valor, String usuario) {
		this.valor = valor;
		this.usuario = usuario;
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Metodo que valida los parametros de entrada
	 * @return ValidacionesVO
	 */
	public ValidacionesVO validarRequest() {

		if (UtilValidate.validaFormatoIntegerF4(this.valor, 1)) {
			if(Integer.parseInt(this.valor) < 1 || Integer.parseInt(this.valor) > 2) {
				return new ValidacionesVO(Boolean.FALSE, "Validar formato de valor, el valor debe ser 1 o 2");
			}
		} else {
			return new ValidacionesVO(Boolean.FALSE, "Validar formato de valor, el valor debe ser 1 o 2");
		}

		return new ValidacionesVO(Boolean.TRUE, "Validacion exitosa.");
	}

}
