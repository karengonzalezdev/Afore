package mx.profuturo.nci.persistence;

import java.util.List;

import mx.profuturo.nci.business.vo.BancosVO;
import mx.profuturo.nci.business.vo.ConciliacionVO;
import mx.profuturo.nci.business.wrapped.AforeMovilFilter;
import mx.profuturo.nci.business.wrapped.BancosFilter;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface AforeMovilPersistence {
	List<ConciliacionVO> consultarAforeMovil(AforeMovilFilter aforeMovilFilter);
	
	int rechazarAforeMovil(AforeMovilFilter aforeMovilFilter);
}