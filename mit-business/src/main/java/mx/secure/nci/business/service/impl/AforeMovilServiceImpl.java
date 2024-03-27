package mx.secure.nci.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.service.IAforeMovilService;
import mx.secure.nci.business.service.IArchivoGeneradoService;
import mx.secure.nci.business.vo.ArchivosGeneradosVO;
import mx.secure.nci.business.vo.ConciliacionVO;
import mx.secure.nci.business.wrapped.AforeMovilFilter;
import mx.secure.nci.business.wrapped.ArchivoDomiciliacionfilter;
import mx.secure.nci.persistence.AforeMovilPersistence;
import mx.secure.nci.persistence.ArchivoDomiPersistence;

@Service("AforeMovilService")
public class AforeMovilServiceImpl implements IAforeMovilService {

	@Autowired
	AforeMovilPersistence aforeMovilPersistence;

	public List<ConciliacionVO> consultar(AforeMovilFilter aforeMovilFilter){
		
		return aforeMovilPersistence.consultarAforeMovil(aforeMovilFilter);
	}
	
	
	public int rechazarAforeConciliacion(AforeMovilFilter aforeMovilFilter) {
		return aforeMovilPersistence.rechazarAforeMovil(aforeMovilFilter);
	}
}