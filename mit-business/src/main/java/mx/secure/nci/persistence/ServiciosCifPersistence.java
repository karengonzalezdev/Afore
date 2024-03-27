package mx.secure.nci.persistence;

import java.util.List;

import com.jeveris.core.exception.impl.BusinessException;

import mx.secure.nci.business.vo.ActualizaEstatusCifVO;
import mx.secure.nci.business.vo.ArchivoDetalleCifVO;
import mx.secure.nci.business.vo.DetalleMovimientosCifVO;
import mx.secure.nci.business.vo.DevolucionessecureCifVO;
import mx.secure.nci.business.vo.PendientesTimbradoCifVO;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface ServiciosCifPersistence {
	
	Integer actualizaEstatusCif(ActualizaEstatusCifVO actualizaEstatusCifVO) throws BusinessException;
	Integer actEstatCifData(ActualizaEstatusCifVO actualizaEstatusCifVO) throws BusinessException;
	List<PendientesTimbradoCifVO> getDevolucionesCif(PendientesTimbradoCifVO param);
	List<PendientesTimbradoCifVO> getDevolucionessecure(PendientesTimbradoCifVO param);
	List<ArchivoDetalleCifVO> getListaDetalleCif(ArchivoDetalleCifVO param);
	List<DevolucionessecureCifVO> getListaDevolucionessecure(DevolucionessecureCifVO param);
	List<DevolucionessecureCifVO> getListadoDevolucionessecure(DevolucionessecureCifVO param);
	List<DetalleMovimientosCifVO> getListaDetalleMovCif(DetalleMovimientosCifVO param);
}
