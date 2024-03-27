package mx.profuturo.nci.business.service;

import java.util.List;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.vo.cif.DevolucionesEnvioCifCabeceraVO;
import mx.profuturo.nci.business.vo.cif.DevolucionesEnvioCifDetalleVO;
import mx.profuturo.nci.business.vo.cif.Profuturo_CIFVo;
import mx.profuturo.nci.business.wrapped.NCI_CIFExtVo;
import mx.profuturo.nci.business.wrapped.NCI_CIFFilter;
import mx.profuturo.nci.business.wrapped.cif.Profuturo_CIFFilter;

public interface ICIFService {
	List<NCI_CIFExtVo> consultarNCI(NCI_CIFFilter f) throws MitBusinessException;
	Integer actualizarNCI(NCI_CIFExtVo vo) throws MitBusinessException;
	Integer borrarNCI(NCI_CIFExtVo vo) throws MitBusinessException;
	
	List<Profuturo_CIFVo> consultarProfuturo(Profuturo_CIFFilter f) throws MitBusinessException;
	Integer insertarProfuturo(Profuturo_CIFVo vo) throws MitBusinessException;
	
	List<DevolucionesEnvioCifDetalleVO> buscaListaEnvioCIFDetalle(String folio);
	List<DevolucionesEnvioCifDetalleVO> buscaListaEnvioCIFDetalle(DevolucionesEnvioCifDetalleVO filtro);
	DevolucionesEnvioCifCabeceraVO buscaListaEnvioCIFCabecera(String folio);
	Integer updateDetalleDevCif(DevolucionesEnvioCifDetalleVO data);
	Integer updateCabeceraDevCif(DevolucionesEnvioCifDetalleVO data);
}
