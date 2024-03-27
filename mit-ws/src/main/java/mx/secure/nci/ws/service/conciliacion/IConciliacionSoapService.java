package mx.secure.nci.ws.service.conciliacion;

import com.jeveris.core.ws.service.exception.WebServiceException;

import mx.secure.nci.ws.beans.request.ConciliacionActualizarBeanRequest;
import mx.secure.nci.ws.beans.request.ConciliacionConsultaFolioBeanRequest;
import mx.secure.nci.ws.beans.request.ConciliacionConsultarBeanRequest;
import mx.secure.nci.ws.beans.request.ConciliacionConsultarTotalesBeanRequest;
import mx.secure.nci.ws.beans.request.ConciliacionInsertarBeanRequest;
import mx.secure.nci.ws.beans.response.ConciliacionConsultaFolioBeanResponse;
import mx.secure.nci.ws.beans.response.ConciliacionConsultarBeanResponse;
import mx.secure.nci.ws.beans.response.ConciliacionConsultarCuantosBeanResponse;
import mx.secure.nci.ws.beans.response.ConciliacionConsultarIdPagoBeanResponse;

public interface IConciliacionSoapService {
	public ConciliacionConsultarBeanResponse consultar(ConciliacionConsultarBeanRequest conciliacionConsultarBeanRequest);
	public ConciliacionConsultarCuantosBeanResponse consultarCuantosRegistros(ConciliacionConsultarBeanRequest conciliacionConsultarBeanRequest);
	public Boolean insertar(ConciliacionInsertarBeanRequest conciliacionInsertarBeanRequest);
	public Boolean actualizar(ConciliacionActualizarBeanRequest conciliacionActualizarBeanRequest);
	ConciliacionConsultaFolioBeanResponse consultarFolios(ConciliacionConsultaFolioBeanRequest request);
	public boolean conciliarTotales(ConciliacionConsultarTotalesBeanRequest conciliacionConsultarTotalesBeanRequest); 
	public ConciliacionConsultarIdPagoBeanResponse consultaIdPagosecure();
}
