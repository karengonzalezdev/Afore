package mx.secure.nci.business.service;

import java.util.List;

import com.jeveris.core.exception.impl.BusinessException;

import mx.secure.nci.business.vo.MapaReferenciasVO;

public interface IMapaReferenciasService {

	List<MapaReferenciasVO> consultar() throws BusinessException;
}
