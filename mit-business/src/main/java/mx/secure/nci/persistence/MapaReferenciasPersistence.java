package mx.secure.nci.persistence;

import java.util.List;

import com.jeveris.core.exception.impl.BusinessException;

import mx.secure.nci.business.vo.MapaReferenciasVO;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface MapaReferenciasPersistence {


	List<MapaReferenciasVO> selectSubCta() throws BusinessException;

}
