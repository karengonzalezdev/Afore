package mx.secure.nci.web.service;

import java.util.List;


import mx.secure.nci.business.vo.CatalogoVO;
import mx.secure.nci.business.vo.ConfigIntentossecureVO;
import mx.secure.nci.web.beans.DomiIntentosCargaFilterBean;


public interface IConfigIntentosCargasecureWebService {
	
	public List<CatalogoVO> consultaCatOrigenDomi() throws Exception;
	
	public List<CatalogoVO> consultaCatPeriodo() throws Exception;
	
	public List<ConfigIntentossecureVO> consultaConfigIntentossecure(DomiIntentosCargaFilterBean domiIntentoCargaFilter) throws Exception;
	
	public Integer insertConfIntentosCarga(List<ConfigIntentossecureVO> listConfIntentossecure) throws Exception;
	
	public Integer editaConfIntentosCarga(ConfigIntentossecureVO confIntentossecure) throws Exception;
}
