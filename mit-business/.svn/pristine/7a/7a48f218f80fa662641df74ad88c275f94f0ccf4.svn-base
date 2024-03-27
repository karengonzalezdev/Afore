package mx.profuturo.nci.business.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"archivoGenerar", "tipoCta", "bancoIncluir", "totales"})
public class RegistroCifrasDomiVO {
	private String archivoGenerar;
	private String tipoCta;
	private String bancoIncluir;
	private List <TotalesRegistroDomiVO> totales;
	
	public RegistroCifrasDomiVO () {
		
	}
	
	public RegistroCifrasDomiVO ( String archivoGenerar
								, String tipoCta
								, String bancoIncluir
								, List <TotalesRegistroDomiVO> totales) {
		this.archivoGenerar = archivoGenerar;
		this.tipoCta = tipoCta;
		this.bancoIncluir = bancoIncluir;
		this.totales = totales;
	}

	public String getArchivoGenerar() {
		return archivoGenerar;
	}

	public void setArchivoGenerar(String archivoGenerar) {
		this.archivoGenerar = archivoGenerar;
	}

	public String getTipoCta() {
		return tipoCta;
	}

	public void setTipoCta(String tipoCta) {
		this.tipoCta = tipoCta;
	}

	public String getBancoIncluir() {
		return bancoIncluir;
	}

	public void setBancoIncluir(String bancoIncluir) {
		this.bancoIncluir = bancoIncluir;
	}

	@XmlElementWrapper(name = "registrosTotales")
	@XmlElement(name = "registroTotal")
	public List<TotalesRegistroDomiVO> getTotales() {
		return totales;
	}

	public void setTotales(List<TotalesRegistroDomiVO> totales) {
		this.totales = totales;
	}

}
