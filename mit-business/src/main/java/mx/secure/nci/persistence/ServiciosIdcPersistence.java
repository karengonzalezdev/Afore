package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.ActualizaEstatusIdcVO;
import mx.secure.nci.business.vo.DetalleExcepcionesIdcVO;
import mx.secure.nci.business.vo.ReporteDetalleIdcVO;
import mx.secure.nci.business.vo.ResultadoIdcVO;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface ServiciosIdcPersistence {
	Integer actualizaEstatusIdc(ActualizaEstatusIdcVO actualizaEstatusIdcVO) throws Exception;
	ResultadoIdcVO getResultadoIdc (ResultadoIdcVO param);
	List<ReporteDetalleIdcVO> getListaDetalleIdc(ReporteDetalleIdcVO param);
	List<DetalleExcepcionesIdcVO> getListaExcepcionesIdc(DetalleExcepcionesIdcVO param);
}
