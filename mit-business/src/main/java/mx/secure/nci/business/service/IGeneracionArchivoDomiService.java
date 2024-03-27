package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.GeneracionArchivoDomiVO;
import mx.secure.nci.business.vo.SolicitudSumTotalPorBancoVO;
import mx.secure.nci.business.vo.SolicitudSumTotalVO;
import mx.secure.nci.business.vo.SolicitudVO;
import mx.secure.nci.business.wrapped.DomiParameterMapFilter;
import mx.secure.nci.business.wrapped.SolicitudFilter;

public interface IGeneracionArchivoDomiService {

	List<GeneracionArchivoDomiVO> generarArchivosDomi(DomiParameterMapFilter filters) throws MitBusinessException ;
	List<SolicitudSumTotalVO> consultarDomiResumen(DomiParameterMapFilter archivoDomiFilter)throws MitBusinessException;
	List<SolicitudSumTotalPorBancoVO> consultarDomiResumenPorBanco(DomiParameterMapFilter filter) throws MitBusinessException;
	List<SolicitudVO> getSolicitudesNOConviven(DomiParameterMapFilter filter) throws MitBusinessException;
	boolean cancelarCuentasNoConviven(List<SolicitudVO> noConviven) throws MitBusinessException;
	String generaFolioDB();
}
