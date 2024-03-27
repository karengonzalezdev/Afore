package mx.secure.nci.persistence;

import java.util.List;

import com.jeveris.core.exception.impl.BusinessException;

import mx.secure.nci.business.vo.ArchivoVO;
import mx.secure.nci.business.wrapped.ArchivoFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface ArchivoPersistence {


	List<ArchivoVO> select(ArchivoFilter ArchivoFilter)
			throws BusinessException;

}
