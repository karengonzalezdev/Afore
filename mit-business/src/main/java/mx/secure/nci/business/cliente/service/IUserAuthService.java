package mx.secure.nci.business.cliente.service;

import mx.secure.nci.business.vo.UserVO;

import com.jeveris.core.exception.impl.BusinessException;

public interface IUserAuthService {

	UserVO get(String user,String password) throws BusinessException;
	void loadUserData(UserVO user) throws BusinessException;
}
