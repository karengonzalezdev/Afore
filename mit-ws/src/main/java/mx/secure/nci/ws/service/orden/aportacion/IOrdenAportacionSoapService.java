package mx.secure.nci.ws.service.orden.aportacion;

import mx.secure.nci.ws.beans.request.OrdenAportacionActualizarBeanRequest;
import mx.secure.nci.ws.beans.request.OrdenAportacionConsultarBeanRequest;
import mx.secure.nci.ws.beans.request.OrdenAportacionInsertarBeanRequest;
import mx.secure.nci.ws.beans.response.OrdenAportacionConsultarBeanResponse;

public interface IOrdenAportacionSoapService 
{
	
	public OrdenAportacionConsultarBeanResponse consultar(OrdenAportacionConsultarBeanRequest request);

	public String insertar(OrdenAportacionInsertarBeanRequest request);
	 
	public Boolean actualizar(OrdenAportacionActualizarBeanRequest request);
}
