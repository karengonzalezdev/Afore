package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.ParamCataConfigVO;
import mx.secure.nci.business.vo.ParamCatalogoVO;

public interface IParamCatConfigService {

	List<ParamCataConfigVO> consultar(ParamCatalogoVO param) throws MitBusinessException;
}