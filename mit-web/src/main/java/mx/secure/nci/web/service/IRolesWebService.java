package mx.secure.nci.web.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.RolVO;

public interface IRolesWebService {
	
	public List<RolVO> obtenerRoles(String username) throws MitBusinessException;
}