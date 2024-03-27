package mx.profuturo.nci.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.profuturo.nci.business.service.IArchivoGeneradoService;
import mx.profuturo.nci.business.vo.ArchivosGeneradosVO;
import mx.profuturo.nci.business.wrapped.ArchivoDomiciliacionfilter;
import mx.profuturo.nci.persistence.ArchivoDomiPersistence;

@Service("ArchivoGeneradoService")
public class ArchivoGeneradoServiceImpl implements IArchivoGeneradoService {

	@Autowired
	ArchivoDomiPersistence archivoDomiPersistence;

	public List<ArchivosGeneradosVO> lista(ArchivoDomiciliacionfilter archivoDomiciliacionfilter) {
		
		return archivoDomiPersistence.selectFechasArchivo(archivoDomiciliacionfilter);
	}

}
