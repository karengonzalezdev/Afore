package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.ParamCataConfigVO;
import mx.secure.nci.business.vo.ParamCatalogoVO;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface ParamCatConfigPersistence 
{
	public List<ParamCataConfigVO> select(ParamCatalogoVO paramVO);	
}