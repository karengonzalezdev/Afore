package mx.secure.nci.ws.service.devoluciones;

import mx.secure.nci.ws.beans.request.DevolucionesActualizaBeanRequest;
import mx.secure.nci.ws.beans.request.DevolucionesConsultaListaBeanRequest;
import mx.secure.nci.ws.beans.request.DevolucionesEnvioCIFBeanRequest;
import mx.secure.nci.ws.beans.request.DevolucionesListaEstatusBeanRequest;
import mx.secure.nci.ws.beans.response.DevolucionesActualizaBeanResponse;
import mx.secure.nci.ws.beans.response.DevolucionesConsultaListaBeanResponse;
import mx.secure.nci.ws.beans.response.DevolucionesEnvioCIFBeanResponse;
import mx.secure.nci.ws.beans.response.DevolucionesListaEstatusBeanResponse;

/*
 * FO998192
 * 12/11/2020
 * Servicio para las devoluciones de Alux
 */
public interface IDevolucionesSoapService {

	public DevolucionesListaEstatusBeanResponse consultaDevolucionesByEstatus(DevolucionesListaEstatusBeanRequest request);
	public DevolucionesConsultaListaBeanResponse consultaDevoluciones(DevolucionesConsultaListaBeanRequest request);
	public DevolucionesEnvioCIFBeanResponse envioDevolucionesCIF(DevolucionesEnvioCIFBeanRequest request);
	public DevolucionesActualizaBeanResponse actualizaDevoluciones(DevolucionesActualizaBeanRequest request);
	
}
