package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.cif.DevolucionesEnvioCifCabeceraVO;
import mx.secure.nci.business.vo.cif.DevolucionesEnvioCifDetalleVO;
import mx.secure.nci.business.wrapped.NCI_CIFExtVo;
import mx.secure.nci.business.wrapped.NCI_CIFFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface NCICIFPersistence {
	List<NCI_CIFExtVo> select(NCI_CIFFilter f);
	Integer update(NCI_CIFExtVo vo);
	Integer delete(NCI_CIFExtVo vo);
	List<DevolucionesEnvioCifDetalleVO> buscaListaEnvioCIFDetalleByFolio(String folio);
	List<DevolucionesEnvioCifDetalleVO> buscaListaEnvioCIFDetalleByRango(DevolucionesEnvioCifDetalleVO filtro);
	DevolucionesEnvioCifCabeceraVO buscaListaEnvioCIFCabeceraByFolio(String folio);
	Integer actualizaDetalleDevCif(DevolucionesEnvioCifDetalleVO data);
	Integer actualizaCabeceraDevCif(DevolucionesEnvioCifDetalleVO data);
}
