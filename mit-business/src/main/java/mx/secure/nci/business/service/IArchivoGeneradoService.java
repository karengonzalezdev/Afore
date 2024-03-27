package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.ArchivosGeneradosVO;
import mx.secure.nci.business.wrapped.ArchivoDomiciliacionfilter;

public interface IArchivoGeneradoService {
	public List<ArchivosGeneradosVO> lista(ArchivoDomiciliacionfilter archivoDomiciliacionfilter) throws MitBusinessException;	
}
