package mx.secure.nci.ws.service.cif;


import mx.secure.nci.ws.beans.request.ActualizaEstatusCifBeanRequest;
import mx.secure.nci.ws.beans.request.DetalleArchivoCifBeanRequest;
import mx.secure.nci.ws.beans.request.DetalleMovCifBeanRequest;
import mx.secure.nci.ws.beans.request.PendientesTimbradoCifBeanRequest;
import mx.secure.nci.ws.beans.request.ValidarResultLoteCifBeanRequest;
import mx.secure.nci.ws.beans.response.ActualizaEstatusCifBeanResponse;
import mx.secure.nci.ws.beans.response.DetalleArchivoCifBeanResponse;
import mx.secure.nci.ws.beans.response.DetalleMovCifBeanResponse;
import mx.secure.nci.ws.beans.response.PendientesTimbradoCifBeanResponse;
import mx.secure.nci.ws.beans.response.ValidarResultLoteCifBeanResponse;

public interface IServiciosCifSoapService {
	public ActualizaEstatusCifBeanResponse actualizaEstatusCif(ActualizaEstatusCifBeanRequest actualizaEstatusIdcBeanRequest);
	public DetalleArchivoCifBeanResponse obtenerDetalleArchivoCif(DetalleArchivoCifBeanRequest request);
	public DetalleMovCifBeanResponse getDetalleMovimientosCif(DetalleMovCifBeanRequest detalleMovCifBeanRequest);
	public PendientesTimbradoCifBeanResponse getListaPendientesTimbradoCif(PendientesTimbradoCifBeanRequest request);
	public ValidarResultLoteCifBeanResponse validarResultadoLoteCif(ValidarResultLoteCifBeanRequest request);
}

