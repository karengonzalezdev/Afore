package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.CatalogoVO;
import mx.secure.nci.business.vo.ParamCatalogoVO;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface CatalogoPersistence 
{
	List<CatalogoVO> select(ParamCatalogoVO paramCatalogoVO);
}
