package mx.secure.nci.ws.beans.response;

import javax.xml.bind.annotation.XmlElement;

import mx.secure.nci.ws.beans.ValidarCuentaBean;

public class ValidarCuentaBeanResponse {
	
	private ValidarCuentaBean cuenta;
	
	public ValidarCuentaBeanResponse () {
		
	}
	
	public ValidarCuentaBeanResponse (ValidarCuentaBean cuenta) {
		super();
		this.cuenta = cuenta;
	}

	
	@XmlElement(name = "cuenta")
	public ValidarCuentaBean getCuenta() {
		return cuenta;
	}

	public void setCuenta(ValidarCuentaBean cuenta) {
		this.cuenta = cuenta;
	}
	
	

}
