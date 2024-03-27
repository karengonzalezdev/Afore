package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.BancosVO;
import mx.secure.nci.business.vo.ConciliacionVO;
import mx.secure.nci.business.wrapped.AforeMovilFilter;
import mx.secure.nci.business.wrapped.BancosFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface AforeMovilPersistence {
	List<ConciliacionVO> consultarAforeMovil(AforeMovilFilter aforeMovilFilter);
	
	int rechazarAforeMovil(AforeMovilFilter aforeMovilFilter);
}