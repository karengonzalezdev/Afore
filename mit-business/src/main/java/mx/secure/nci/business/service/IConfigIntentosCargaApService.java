package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.CatalogoVO;
import mx.secure.nci.business.vo.ConfigIntentossecureVO;
import mx.secure.nci.business.vo.ReporteCifrasControlVO;
import mx.secure.nci.business.vo.SolicitudReclasificacionVO;
import mx.secure.nci.business.wrapped.*;
public interface IConfigIntentosCargasecureService {

	
	public List<CatalogoVO> consultaCatOrigenDomi() throws Exception;
	
	public List<CatalogoVO> consultaCatPeriodo() throws Exception;
	
	public List<ConfigIntentossecureVO> consultaConfigIntentossecure(DomiIntentosCargaFilter domiIntentoCargaFilter) throws Exception;
//	public List<ConfigIntentossecureVO> consultaConfigIntentossecure() throws Exception;
	
	public Integer insertConfIntentosCarga(List<ConfigIntentossecureVO> listConfIntentossecure) throws Exception;
	
	public Integer editaConfIntentosCarga(ConfigIntentossecureVO confIntentossecure) throws Exception;
}
