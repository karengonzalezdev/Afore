package mx.secure.nci.ws.beans.request;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import mx.secure.nci.business.vo.ValidacionesVO;
import mx.secure.nci.business.vo.DevolucionessecureCifVO;

public class ValidarResultLoteCifBeanRequest {
 
	private String folio;
	private String tipoDevolucionsecure;
	private List<String> listaEstatusCIF;
	
		
public ValidacionesVO validarRequest() {
		
		if( this.folio == null || this.folio.isEmpty()) {
			return new ValidacionesVO( Boolean.FALSE ,  "Se debe recibir un folio.");
		}
		if( this.tipoDevolucionsecure == null || this.tipoDevolucionsecure.isEmpty()) {
			return new ValidacionesVO( Boolean.FALSE ,  "Se debe recibir la clave de devolucion.");
		}
		if( this.listaEstatusCIF == null || this.listaEstatusCIF.isEmpty()) {
			return new ValidacionesVO( Boolean.FALSE ,"Se debe recibir el estatus.");
		}
		return new ValidacionesVO( Boolean.TRUE , "Validacion exitosa", new DevolucionessecureCifVO(folio, tipoDevolucionsecure,listaEstatusCIF)); 
		}
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getTipoDevolucionsecure() {
		return tipoDevolucionsecure;
	}
	public void setTipoDevolucionsecure(String tipoDevolucionsecure) {
		this.tipoDevolucionsecure = tipoDevolucionsecure;
	}
	
	@XmlElementWrapper(name = "listaEstatusCif")
	@XmlElement(name = "listadoEstatusCif")
	public List<String> getListaEstatusCIF() {
		return listaEstatusCIF;
	}
	public void setListaEstatusCIF(List<String> listaEstatusCIF) {
		this.listaEstatusCIF = listaEstatusCIF;
	}
	
	
	
}
