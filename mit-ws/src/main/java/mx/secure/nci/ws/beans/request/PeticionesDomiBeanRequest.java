package mx.secure.nci.ws.beans.request;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import mx.secure.nci.business.util.UtilValidate;
import mx.secure.nci.business.vo.PeticionDomiciliacionVO;
import mx.secure.nci.business.vo.ValidacionesVO;

@XmlType(propOrder={"origenAportacion", "fechaInicio","fechaFin", "usuario", "folio", "idTipoContrato", "peticiones"})
public class PeticionesDomiBeanRequest {
	private String origenAportacion;
	private String fechaInicio;
	private String fechaFin;
	private String usuario;
	private String folio;
	private String idTipoContrato;
	private List <PeticionDomiciliacionVO> peticiones;
	
	public PeticionesDomiBeanRequest () {
		
	}
	
	public PeticionesDomiBeanRequest ( String origenAportacion
								 	 , String fechaInicio
								 	 , String fechaFin
								 	 , String usuario
								 	 , List <PeticionDomiciliacionVO> peticiones) {
		this.origenAportacion = origenAportacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.usuario = usuario;
		this.peticiones = peticiones;
	}
	
	public PeticionesDomiBeanRequest ( String origenAportacion
								 	 , String fechaInicio
								 	 , String fechaFin
								 	 , String usuario
								 	 , List <PeticionDomiciliacionVO> peticiones
								 	 , String folio) {
		this.origenAportacion = origenAportacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.usuario = usuario;
		this.peticiones = peticiones;
		this.folio = folio;
	}

	public String getOrigenAportacion() {
		return origenAportacion;
	}

	public void setOrigenAportacion(String origenAportacion) {
		this.origenAportacion = origenAportacion;
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
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	public String getIdTipoContrato() {
		return idTipoContrato;
	}

	public void setIdTipoContrato(String idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}

	@XmlElementWrapper(name = "peticiones")
	@XmlElement(name = "peticion")
	public List<PeticionDomiciliacionVO> getPeticiones() {
		return peticiones;
	}

	public void setPeticiones(List<PeticionDomiciliacionVO> peticiones) {
		this.peticiones = peticiones;
	}
	
	public ValidacionesVO validarRequestCifras() {
		
		if( !UtilValidate.validaFormatoIntegerF4(this.origenAportacion, 7) ) {
			return new ValidacionesVO( Boolean.FALSE, "Validar formato origenAportacion, LONG MAX 7" );
		}
		
		if( !UtilValidate.validarFechaPorFormatoF4( this.fechaInicio , "dd/MM/yyyy") ) {
			return new ValidacionesVO( Boolean.FALSE, "fechaInicio, validar formato dd/MM/yyyy" );
		}
		
		if( !UtilValidate.validarFechaPorFormatoF4( this.fechaFin , "dd/MM/yyyy") ) {
			return new ValidacionesVO( Boolean.FALSE, "fechaFin, validar formato dd/MM/yyyy" );
		}
		
		if (this.peticiones == null || this.peticiones.isEmpty()) {
			return new ValidacionesVO( Boolean.FALSE, "peticiones, por favor ingrese 'peticiones' " );
		}
		
		if (this.usuario == null || this.usuario.isEmpty()) {
			return new ValidacionesVO( Boolean.FALSE, "peticiones, por favor ingrese 'usuario' " );
		}
		
		return new ValidacionesVO( Boolean.TRUE , "Validacion exitosa." );
	}
	
	public ValidacionesVO validarRequestArchivosDomi() {
		
		if( !UtilValidate.validaFormatoIntegerF4(this.origenAportacion, 7) ) {
			return new ValidacionesVO( Boolean.FALSE, "Validar formato origenAportacion, LONG MAX 7" );
		}
		
		if( !UtilValidate.validarFechaPorFormatoF4( this.fechaInicio , "dd/MM/yyyy") ) {
			return new ValidacionesVO( Boolean.FALSE, "fechaInicio, validar formato dd/MM/yyyy" );
		}
		
		if( !UtilValidate.validarFechaPorFormatoF4( this.fechaFin , "dd/MM/yyyy") ) {
			return new ValidacionesVO( Boolean.FALSE, "fechaFin, validar formato dd/MM/yyyy" );
		}
		
		if (this.peticiones == null || this.peticiones.isEmpty()) {
			return new ValidacionesVO( Boolean.FALSE, "peticiones, por favor ingrese 'peticiones' " );
		}
		
		if (this.usuario == null || this.usuario.isEmpty()) {
			return new ValidacionesVO( Boolean.FALSE, "peticiones, por favor ingrese 'usuario' " );
		}
		
		if (!UtilValidate.validaFormatoIntegerF4(this.folio, 20)) {
			return new ValidacionesVO( Boolean.FALSE, "Validar formato de folio, LONG MAX 20" );
		}
		
		if (!UtilValidate.validaFormatoIntegerF4(this.idTipoContrato, 7)) {
			return new ValidacionesVO( Boolean.FALSE, "Validar formato de tipo de contrato, LONG MAX 7" );
		}
		
		return new ValidacionesVO( Boolean.TRUE , "Validacion exitosa." );
	}
	

}
