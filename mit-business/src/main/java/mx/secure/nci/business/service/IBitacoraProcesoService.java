package mx.secure.nci.business.service;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.BitacoraProcesoVO;

public interface IBitacoraProcesoService {

	Boolean insertar(BitacoraProcesoVO vo) throws MitBusinessException;
	
}
