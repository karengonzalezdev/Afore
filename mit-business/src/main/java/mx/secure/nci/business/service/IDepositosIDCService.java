package mx.secure.nci.business.service;

import java.util.List;

import com.jeveris.core.exception.impl.BusinessException;

import mx.secure.nci.business.vo.DepositosIDCVO;
import mx.secure.nci.business.vo.ValidarCuentaVO;
import mx.secure.nci.business.wrapped.DepositosIDCFilter;

public interface IDepositosIDCService {
	
	public List<DepositosIDCVO>  consultar(DepositosIDCFilter DepositosFilter)throws BusinessException;
	
	public  ValidarCuentaVO validarCuenta (String cuenta)throws BusinessException;

}
