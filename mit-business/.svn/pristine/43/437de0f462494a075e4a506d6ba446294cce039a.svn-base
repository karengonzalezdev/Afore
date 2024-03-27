package mx.profuturo.nci.persistence;

import java.util.List;

import mx.profuturo.nci.business.vo.ArchivosDomiciliacionBitacoraVO;
import mx.profuturo.nci.business.vo.ArchivosDomiciliacionVO;
import mx.profuturo.nci.business.vo.ArchivosGeneradosVO;
import mx.profuturo.nci.business.vo.CatPrioridadesDiversificacionesVO;
import mx.profuturo.nci.business.vo.ConsecutivosDomiVO;
import mx.profuturo.nci.business.vo.FechaArchivoDomiVO;
import mx.profuturo.nci.business.vo.RegistrosArchivoVO;
import mx.profuturo.nci.business.vo.RegistrosMaxArchVO;
import mx.profuturo.nci.business.wrapped.ArchivoDomiciliacionfilter;
import mx.profuturo.nci.business.wrapped.FiltroArchivosDomiciliacionFilter;
import mx.profuturo.nci.business.wrapped.TablaDinamicaArchDomiFilter;
import mx.profuturo.nci.business.wrapped.TipoProcesamientoDomisFilter;
import mx.profuturo.nci.business.wrapped.TipoRegistrosMaxFilter;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface ArchivoDomiPersistence {
	List<ArchivosGeneradosVO> selectFechasArchivo(ArchivoDomiciliacionfilter archivoDomiciliacionfilter);
	Integer selectConsecutivoHoy(ArchivoDomiciliacionfilter filter);
	Integer insertArchivoDomiGenerado(ArchivosGeneradosVO archivoGen);
	Integer insert(ArchivosGeneradosVO archivoGen);
	Integer insertPar(ArchivosGeneradosVO archivoGen);
	Integer update(ArchivosGeneradosVO archivoGen);
	Integer updatePar(ArchivosGeneradosVO archivoGen);
	//FOP 2229 obtener Prioridad de Diversificaciones
	List<CatPrioridadesDiversificacionesVO> selectCatPrioridadDiversificaciones();
	
	public FechaArchivoDomiVO consultarFechaFormatoDomiBancomer ();
	public FechaArchivoDomiVO consultarFechaFormatoDomiBanamex ();
	
	public ConsecutivosDomiVO consultarConsecutivoBanamexOrdinario();
	public ConsecutivosDomiVO consultarConsecutivoBanamexTraspasos();
	public ConsecutivosDomiVO consultarConsecutivoBancomerOrdinario();
	public ConsecutivosDomiVO consultarConsecutivoBancomerTraspasos();
	
	List<ArchivosDomiciliacionVO> consultarArchivosDomiciliacion(FiltroArchivosDomiciliacionFilter filter) throws Exception;
	List<ArchivosDomiciliacionBitacoraVO> consultarArchivosDomiciliacionBitacora(FiltroArchivosDomiciliacionFilter filter) throws Exception;
	
	RegistrosMaxArchVO obtenerRegistrosMaximosArchivo(TipoRegistrosMaxFilter filter);
	
	List<ArchivosDomiciliacionBitacoraVO> consultarArchivosDomiBitacora(TablaDinamicaArchDomiFilter filter) throws Exception;
	
	Integer actValorProcesamientoDomi(TipoProcesamientoDomisFilter filter) throws Exception;
	
	List<RegistrosArchivoVO> consultarRegistrosArchivo(TablaDinamicaArchDomiFilter filter) throws Exception;
}
