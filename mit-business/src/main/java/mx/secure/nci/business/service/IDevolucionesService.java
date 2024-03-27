package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.vo.DevolucionesVO;
import mx.secure.nci.business.vo.LoteControlAluxVO;
import mx.secure.nci.business.vo.LoteControlNciVO;

public interface IDevolucionesService {

	public List<LoteControlAluxVO> obtenerLotesAlux(LoteControlAluxVO param) throws Exception;
	public List<LoteControlNciVO> obtenerLotesNCI(List<LoteControlAluxVO> ltsAlux, String estatus) throws Exception;
	public List<LoteControlNciVO> obtenerLotesNCI(LoteControlAluxVO ltsAlux) throws Exception;
	public List<DevolucionesVO> obtenerListaDevoluciones(DevolucionesVO param) throws Exception;
	public Integer enviaDevolucionesCIF(List<String> param) throws Exception;
	public Integer actualizaDevolucion(DevolucionesVO param) throws Exception;
	
}
