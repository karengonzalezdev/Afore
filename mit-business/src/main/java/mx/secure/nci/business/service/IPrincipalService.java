package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.PrincipalVO;
import mx.secure.nci.business.wrapped.PrincipalFilter;

public interface IPrincipalService {
	List<PrincipalVO> consultar(PrincipalFilter filter) throws MitBusinessException;
}
