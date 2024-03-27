package mx.secure.nci.ws.service.idc;


import mx.secure.nci.ws.beans.request.ActualizaEstatusIdcBeanRequest;
import mx.secure.nci.ws.beans.request.DetalleExcepcionesIdcBeanRequest;
import mx.secure.nci.ws.beans.request.DetalleIdcReporteBeanRequest;
import mx.secure.nci.ws.beans.request.IdcResultadoBeanRequest;
import mx.secure.nci.ws.beans.response.ActualizaEstatusIdcBeanResponse;
import mx.secure.nci.ws.beans.response.DetalleExcepcionesIdcBeanResponse;
import mx.secure.nci.ws.beans.response.DetalleIdcReporteBeanResponse;
import mx.secure.nci.ws.beans.response.IdcResultadoBeanResponse;

public interface IServiciosIdcSoapService {
	public ActualizaEstatusIdcBeanResponse marcarDescartadosIdc(ActualizaEstatusIdcBeanRequest actualizaEstatusIdcBeanRequest);
	public DetalleExcepcionesIdcBeanResponse getDetalleExcepcionesIdc(DetalleExcepcionesIdcBeanRequest request);
	public DetalleIdcReporteBeanResponse generaReporteDetalleIdc(DetalleIdcReporteBeanRequest request);
	public IdcResultadoBeanResponse getResultadoIdc(IdcResultadoBeanRequest request);
	
}

