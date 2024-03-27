package mx.profuturo.nci.business.service;

import java.util.List;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ConfigIntentosApoVolVO;
import mx.profuturo.nci.business.vo.ReporteCifrasControlVO;
import mx.profuturo.nci.business.vo.SolicitudReclasificacionVO;
import mx.profuturo.nci.business.wrapped.*;
public interface IConfigIntentosCargaApoVolService {

	
	public List<CatalogoVO> consultaCatOrigenDomi() throws Exception;
	
	public List<CatalogoVO> consultaCatPeriodo() throws Exception;
	
	public List<ConfigIntentosApoVolVO> consultaConfigIntentosApoVol(DomiIntentosCargaFilter domiIntentoCargaFilter) throws Exception;
//	public List<ConfigIntentosApoVolVO> consultaConfigIntentosApoVol() throws Exception;
	
	public Integer insertConfIntentosCarga(List<ConfigIntentosApoVolVO> listConfIntentosApoVol) throws Exception;
	
	public Integer editaConfIntentosCarga(ConfigIntentosApoVolVO confIntentosApoVol) throws Exception;
}
