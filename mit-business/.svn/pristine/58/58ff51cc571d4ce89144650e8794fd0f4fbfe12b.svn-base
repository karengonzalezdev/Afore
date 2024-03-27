package mx.profuturo.nci.business.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"cveBancoIncluir", "archivoGenenerar","tiposCuenta", "archivoUnico"})
public class PeticionDomiciliacionVO {
	private String cveBancoIncluir;
	private String archivoGenenerar;
	private List <TiposCuentaDomiVO> tiposCuenta;
	private boolean archivoUnico;
	
	public PeticionDomiciliacionVO () {
		
	}
	
	public PeticionDomiciliacionVO ( String cveBancoIncluir
								   , String archivoGenenerar
								   , List <TiposCuentaDomiVO> tiposCuenta
								   , boolean archivoUnico) {
		this.cveBancoIncluir = cveBancoIncluir;
		this.archivoGenenerar = archivoGenenerar;
		this.tiposCuenta = tiposCuenta;
		this.archivoUnico = archivoUnico;
	}

	public String getCveBancoIncluir() {
		return cveBancoIncluir;
	}

	public void setCveBancoIncluir(String cveBancoIncluir) {
		this.cveBancoIncluir = cveBancoIncluir;
	}

	public String getArchivoGenenerar() {
		return archivoGenenerar;
	}

	public void setArchivoGenenerar(String archivoGenenerar) {
		this.archivoGenenerar = archivoGenenerar;
	}

	@XmlElementWrapper(name = "tiposCuenta")
	@XmlElement(name = "tipoCuenta")
	public List<TiposCuentaDomiVO> getTiposCuenta() {
		return tiposCuenta;
	}

	public void setTiposCuenta(List<TiposCuentaDomiVO> tiposCuenta) {
		this.tiposCuenta = tiposCuenta;
	}

	public boolean isArchivoUnico() {
		return archivoUnico;
	}

	public void setArchivoUnico(boolean archivoUnico) {
		this.archivoUnico = archivoUnico;
	}
	
	
}
