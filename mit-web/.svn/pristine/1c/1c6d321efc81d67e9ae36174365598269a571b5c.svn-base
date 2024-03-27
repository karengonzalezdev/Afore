package mx.profuturo.nci.web.service;

import java.util.List;


import mx.profuturo.nci.business.vo.CatalogoVO;
import mx.profuturo.nci.business.vo.ConfigIntentosApoVolVO;
import mx.profuturo.nci.web.beans.DomiIntentosCargaFilterBean;


public interface IConfigIntentosCargaApoVolWebService {
	
	public List<CatalogoVO> consultaCatOrigenDomi() throws Exception;
	
	public List<CatalogoVO> consultaCatPeriodo() throws Exception;
	
	public List<ConfigIntentosApoVolVO> consultaConfigIntentosApoVol(DomiIntentosCargaFilterBean domiIntentoCargaFilter) throws Exception;
	
	public Integer insertConfIntentosCarga(List<ConfigIntentosApoVolVO> listConfIntentosApoVol) throws Exception;
	
	public Integer editaConfIntentosCarga(ConfigIntentosApoVolVO confIntentosApoVol) throws Exception;
}
