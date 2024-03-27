package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.ConciliacionVO;
import mx.secure.nci.business.wrapped.AforeMovilFilter;

public interface IAforeMovilService {

	public List<ConciliacionVO> consultar(AforeMovilFilter aforeMovilFilter) throws MitBusinessException;
	
	public int rechazarAforeConciliacion(AforeMovilFilter aforeMovilFilter) throws MitBusinessException;
}