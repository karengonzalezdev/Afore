package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.vo.ActualizaEstatusIdcVO;
import mx.secure.nci.business.vo.DetalleExcepcionesIdcVO;
import mx.secure.nci.business.vo.ReporteDetalleIdcVO;
import mx.secure.nci.business.vo.ResultadoIdcVO;


public interface IServiciosIdcService {
	public Integer actualizaEstatusIdc(ActualizaEstatusIdcVO actualizaEstatusVO) throws Exception;
	public List<DetalleExcepcionesIdcVO> obtenerListaDetalleExcepcionesIdc(DetalleExcepcionesIdcVO param) throws Exception;
	public List<ReporteDetalleIdcVO> obtenerListaDetalleIdc(ReporteDetalleIdcVO param) throws Exception;
	public ResultadoIdcVO consultarResultadoIdc (ResultadoIdcVO param)throws Exception;
	
}
