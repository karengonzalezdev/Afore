package mx.secure.nci.ws.beans.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import mx.secure.nci.business.util.UtilValidate;
import mx.secure.nci.business.vo.ValidacionesVO;

/**
 * Clase que contiene los parametros de entrada (request)
 * @author Manuel Loza
 * @category MANTENIMIENTO
 * @date 01/02/2022
 */
@XmlType(propOrder={"fechaInicio", "fechaFin"})
public class FiltroArchivosDomiciliacionBeanRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	private String fechaInicio;
	private String fechaFin;
	
	/**
	 * Constructor vacio.
	 */
	public FiltroArchivosDomiciliacionBeanRequest() {
	}

	/**
	 * Constructo lleno.
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public FiltroArchivosDomiciliacionBeanRequest(String fechaInicio, String fechaFin) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	/**
	 * Metodo que valida los parametros de entrada.
	 * @return boolean, true = exitoso, false = algun campo vacio
	 */
	public ValidacionesVO validarRequest() {
		
		if(this.fechaInicio != null && !this.fechaInicio.isEmpty() && this.fechaInicio != "") {
			if (!UtilValidate.validarFechaPorFormato(this.fechaInicio, "dd-MM-yyyy")) {
				return new ValidacionesVO( Boolean.FALSE, "El formato correcto de fecha es: dd-mm-aaaa" );
			}
		} else {
			return new ValidacionesVO( Boolean.FALSE, "Favor de ingresar fecha inicial." );
		}
		
		if(this.fechaFin != null && !this.fechaFin.isEmpty() && this.fechaFin != "") {
			if (!UtilValidate.validarFechaPorFormato(this.fechaFin, "dd-MM-yyyy")) {
				return new ValidacionesVO( Boolean.FALSE, "El formato correcto de fecha es: dd-mm-aaaa" );
			}
		} else {
			return new ValidacionesVO( Boolean.FALSE, "Favor de ingresar fecha final." );
		}
		
		return new ValidacionesVO( Boolean.TRUE , "Validacion exitosa." );
	}
}
