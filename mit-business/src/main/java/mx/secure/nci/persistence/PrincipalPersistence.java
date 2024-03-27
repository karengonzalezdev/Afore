package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.PrincipalVO;
import mx.secure.nci.business.wrapped.PrincipalFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface PrincipalPersistence 
{
	public List<PrincipalVO> select(PrincipalFilter preSolicitudFilter);
}