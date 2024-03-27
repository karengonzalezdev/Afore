package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.vo.ObtenerCifrasControlVO;

public interface IObtenerCifrasControlService {


	public List<ObtenerCifrasControlVO> obtenerListaCifrasMovimientosCif(ObtenerCifrasControlVO param) throws Exception;
	public List<ObtenerCifrasControlVO> obtenerListaBancosyCuentasDestino(ObtenerCifrasControlVO param) throws Exception;
	public List<ObtenerCifrasControlVO> obtenerListaTotalDevsecure(ObtenerCifrasControlVO param) throws Exception;
	public ObtenerCifrasControlVO obtenerTotalDeTotales(ObtenerCifrasControlVO param) throws Exception;
}
