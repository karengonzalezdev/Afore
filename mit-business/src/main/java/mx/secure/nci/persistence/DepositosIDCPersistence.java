package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.DepositosIDCVO;
import mx.secure.nci.business.vo.ValidarCuentaVO;
import mx.secure.nci.business.wrapped.DepositosIDCFilter;
import mx.secure.nci.business.wrapped.ValidarCuentaFilter;
import mx.secure.nci.stereotype.Mapper;


@Mapper
public interface DepositosIDCPersistence {
	
	public List<DepositosIDCVO> select(DepositosIDCFilter depositosFilter);
	
	public ValidarCuentaVO selectValidarCuenta (ValidarCuentaFilter validarCuentaFilter);

}
