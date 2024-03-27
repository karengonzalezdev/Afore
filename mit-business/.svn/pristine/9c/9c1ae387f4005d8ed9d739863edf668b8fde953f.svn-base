package mx.profuturo.nci.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.service.IAforeMovilService;
import mx.profuturo.nci.business.service.IArchivoGeneradoService;
import mx.profuturo.nci.business.vo.ArchivosGeneradosVO;
import mx.profuturo.nci.business.vo.ConciliacionVO;
import mx.profuturo.nci.business.wrapped.AforeMovilFilter;
import mx.profuturo.nci.business.wrapped.ArchivoDomiciliacionfilter;
import mx.profuturo.nci.persistence.AforeMovilPersistence;
import mx.profuturo.nci.persistence.ArchivoDomiPersistence;

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