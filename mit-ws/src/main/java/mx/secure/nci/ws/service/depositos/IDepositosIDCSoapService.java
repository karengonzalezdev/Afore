package mx.secure.nci.ws.service.depositos;

import mx.secure.nci.ws.beans.request.DepositosIDCConsultaBeanRequest;
import mx.secure.nci.ws.beans.response.DepositosIDCConsultaBeanResponse;

import mx.secure.nci.ws.beans.request.ValidarCuentaBeanRequest;
import mx.secure.nci.ws.beans.response.ValidarCuentaBeanResponse;

public interface IDepositosIDCSoapService {
	
	/*Metos disponibles en el servicio*/
	public DepositosIDCConsultaBeanResponse consultarDepositos (DepositosIDCConsultaBeanRequest request);
	
	public ValidarCuentaBeanResponse validarCuenta (ValidarCuentaBeanRequest request);
	
	
}
