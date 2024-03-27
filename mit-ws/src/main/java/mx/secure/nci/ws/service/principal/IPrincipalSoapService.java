package mx.secure.nci.ws.service.principal;

import mx.secure.nci.ws.beans.request.PrincipalConsultarBeanRequest;
import mx.secure.nci.ws.beans.response.PrincipalConsultarBeanResponse;

public interface IPrincipalSoapService {
	public PrincipalConsultarBeanResponse consultar(PrincipalConsultarBeanRequest principalConsultarBeanRequest);	
}