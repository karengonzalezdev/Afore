package mx.profuturo.nci.business.service;

import java.util.List;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.vo.ConciliacionVO;
import mx.profuturo.nci.business.wrapped.AforeMovilFilter;

public interface IAforeMovilService {

	public List<ConciliacionVO> consultar(AforeMovilFilter aforeMovilFilter) throws MitBusinessException;
	
	public int rechazarAforeConciliacion(AforeMovilFilter aforeMovilFilter) throws MitBusinessException;
}