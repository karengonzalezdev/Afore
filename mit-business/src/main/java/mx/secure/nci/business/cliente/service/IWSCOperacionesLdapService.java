package mx.secure.nci.business.cliente.service;

import java.util.List;

import mx.secure.nci.business.vo.RolVO;

import com.jeveris.core.exception.impl.BusinessException;

public interface IWSCOperacionesLdapService {

	List<RolVO> getRoles(String userId) throws BusinessException;

}
