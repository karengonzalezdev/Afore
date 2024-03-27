package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.vo.ActualizaEstatusCifVO;
import mx.secure.nci.business.vo.ArchivoDetalleCifVO;
import mx.secure.nci.business.vo.DetalleMovimientosCifVO;
import mx.secure.nci.business.vo.DevolucionessecureCifVO;
import mx.secure.nci.business.vo.PendientesTimbradoCifVO;


public interface IServiciosCifService {

	public Integer actualizaEstatusCif(ActualizaEstatusCifVO actualizaEstatusVO) throws Exception;
	public Integer actualizaEstatusCifDatamarts(ActualizaEstatusCifVO actualizaEstatusVO) throws Exception;
	public List<PendientesTimbradoCifVO> obtenerDevolucionessecure(PendientesTimbradoCifVO param) throws Exception;
	public List<ArchivoDetalleCifVO> obtenerListaDetalleCif(ArchivoDetalleCifVO param) throws Exception;
	public List<DevolucionessecureCifVO>obtenerListaDevolucionessecureCif(DevolucionessecureCifVO param) throws Exception;
	public List<DetalleMovimientosCifVO> obtenerListaDetalleMovCif(DetalleMovimientosCifVO param) throws Exception;

}
