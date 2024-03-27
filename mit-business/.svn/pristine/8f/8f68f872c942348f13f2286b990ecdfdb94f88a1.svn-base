package mx.profuturo.nci.persistence;

import java.util.List;

import mx.profuturo.nci.business.vo.cif.DevolucionesEnvioCifCabeceraVO;
import mx.profuturo.nci.business.vo.cif.DevolucionesEnvioCifDetalleVO;
import mx.profuturo.nci.business.wrapped.NCI_CIFExtVo;
import mx.profuturo.nci.business.wrapped.NCI_CIFFilter;
import mx.profuturo.nci.stereotype.Mapper;

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
