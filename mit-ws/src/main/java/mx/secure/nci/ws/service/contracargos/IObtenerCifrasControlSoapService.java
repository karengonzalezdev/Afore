package mx.secure.nci.ws.service.contracargos;

import mx.secure.nci.ws.beans.request.ObtenerCifrasControlBeanRequest;
import mx.secure.nci.ws.beans.response.ObtenerCifrasControlBeanResponse;

public interface IObtenerCifrasControlSoapService {

	public ObtenerCifrasControlBeanResponse getListaCifrasControl(ObtenerCifrasControlBeanRequest request);
	
}
