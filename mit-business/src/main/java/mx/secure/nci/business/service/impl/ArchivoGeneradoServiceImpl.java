package mx.secure.nci.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.secure.nci.business.service.IArchivoGeneradoService;
import mx.secure.nci.business.vo.ArchivosGeneradosVO;
import mx.secure.nci.business.wrapped.ArchivoDomiciliacionfilter;
import mx.secure.nci.persistence.ArchivoDomiPersistence;

@Service("ArchivoGeneradoService")
public class ArchivoGeneradoServiceImpl implements IArchivoGeneradoService {

	@Autowired
	ArchivoDomiPersistence archivoDomiPersistence;

	public List<ArchivosGeneradosVO> lista(ArchivoDomiciliacionfilter archivoDomiciliacionfilter) {
		
		return archivoDomiPersistence.selectFechasArchivo(archivoDomiciliacionfilter);
	}

}
