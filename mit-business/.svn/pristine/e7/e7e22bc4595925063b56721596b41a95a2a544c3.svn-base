package mx.profuturo.nci.persistence;

import java.util.List;

import mx.profuturo.nci.business.vo.ArchivoDomiVO;
import mx.profuturo.nci.business.wrapped.ArchivosDomiBitacoraFilter;
import mx.profuturo.nci.business.wrapped.OperacionDomiBitacoraFilter;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface DomisBitacoraPersistence {
	
	public List <ArchivoDomiVO> consultarArchivosDomiBitacora (ArchivosDomiBitacoraFilter filter) throws Exception;
	
	public Integer registrarArchivoDomiBitacora (OperacionDomiBitacoraFilter filter) throws Exception;
	
}
