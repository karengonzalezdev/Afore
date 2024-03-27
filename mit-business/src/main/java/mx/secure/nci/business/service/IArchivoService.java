package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.vo.ArchivoVO;
import mx.secure.nci.business.wrapped.ArchivoFilter;

import com.jeveris.core.exception.impl.BusinessException;

public interface IArchivoService {
	
	List<ArchivoVO>  consultar(ArchivoFilter filter)throws BusinessException;	
}
