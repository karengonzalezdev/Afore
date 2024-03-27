package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.ArchivoDomiVO;
import mx.secure.nci.business.wrapped.ArchivosDomiBitacoraFilter;
import mx.secure.nci.business.wrapped.OperacionDomiBitacoraFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface DomisBitacoraPersistence {
	
	public List <ArchivoDomiVO> consultarArchivosDomiBitacora (ArchivosDomiBitacoraFilter filter) throws Exception;
	
	public Integer registrarArchivoDomiBitacora (OperacionDomiBitacoraFilter filter) throws Exception;
	
}
