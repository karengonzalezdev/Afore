package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.CatalogoVO;
import mx.secure.nci.business.vo.ParamCatalogoVO;

public interface ICatalogosService {

 
	public List<CatalogoVO> consultarLista(ParamCatalogoVO paramCatalogoVO) throws MitBusinessException;
	
}
