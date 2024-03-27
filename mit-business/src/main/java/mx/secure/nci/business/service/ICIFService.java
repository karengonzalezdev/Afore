package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.cif.DevolucionesEnvioCifCabeceraVO;
import mx.secure.nci.business.vo.cif.DevolucionesEnvioCifDetalleVO;
import mx.secure.nci.business.vo.cif.secure_CIFVo;
import mx.secure.nci.business.wrapped.NCI_CIFExtVo;
import mx.secure.nci.business.wrapped.NCI_CIFFilter;
import mx.secure.nci.business.wrapped.cif.secure_CIFFilter;

public interface ICIFService {
	List<NCI_CIFExtVo> consultarNCI(NCI_CIFFilter f) throws MitBusinessException;
	Integer actualizarNCI(NCI_CIFExtVo vo) throws MitBusinessException;
	Integer borrarNCI(NCI_CIFExtVo vo) throws MitBusinessException;
	
	List<secure_CIFVo> consultarsecure(secure_CIFFilter f) throws MitBusinessException;
	Integer insertarsecure(secure_CIFVo vo) throws MitBusinessException;
	
	List<DevolucionesEnvioCifDetalleVO> buscaListaEnvioCIFDetalle(String folio);
	List<DevolucionesEnvioCifDetalleVO> buscaListaEnvioCIFDetalle(DevolucionesEnvioCifDetalleVO filtro);
	DevolucionesEnvioCifCabeceraVO buscaListaEnvioCIFCabecera(String folio);
	Integer updateDetalleDevCif(DevolucionesEnvioCifDetalleVO data);
	Integer updateCabeceraDevCif(DevolucionesEnvioCifDetalleVO data);
}
