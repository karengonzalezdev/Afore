package mx.profuturo.nci.persistence;

import java.util.List;

import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.DevolucionesCIFDetVO;
import mx.profuturo.nci.business.vo.DevolucionesCIFVO;
import mx.profuturo.nci.business.vo.DevolucionesVO;
import mx.profuturo.nci.business.vo.LoteControlAluxVO;
import mx.profuturo.nci.business.vo.LoteControlNciVO;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface ProfuturoNCIPersistence {

	List<LoteControlNciVO> getListaLotes(LoteControlAluxVO param);
	List<LoteControlNciVO> getListaLotesByEstatus(LoteControlAluxVO param);
	List<DevolucionesVO> getListaTablaDevoluciones(DevolucionesVO param);
	List<DevolucionesCIFVO> getListaDevolucionesByCIF(List<String> param);
	List<DevolucionesCIFDetVO> getListaDevolucionesByCIFDet(List<String> param);
	int actualizarDevoluciones(DevolucionesVO param);
	List<CatalogoVO> getCatalogoEstatus();
}
