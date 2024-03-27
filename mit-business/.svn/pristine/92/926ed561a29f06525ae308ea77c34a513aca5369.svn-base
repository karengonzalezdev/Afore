package mx.profuturo.nci.persistence;

import java.util.List;

import mx.profuturo.nci.business.vo.ArchivosGeneradosDomiVO;
import mx.profuturo.nci.business.vo.CatalogosDomiVO;
import mx.profuturo.nci.business.vo.CifrasTotalesDomiVO;
import mx.profuturo.nci.business.vo.ProcesamientoDomiVO;
import mx.profuturo.nci.business.vo.RegistroCifrasDomiVO;
import mx.profuturo.nci.business.vo.TotalesRegistroDomiVO;
import mx.profuturo.nci.business.wrapped.CifraDomiFilter;
import mx.profuturo.nci.business.wrapped.CifrasTotalesDomiFilter;
import mx.profuturo.nci.business.wrapped.UltimosArchivosGeneradosFilter;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface DomiciliacionesPersistence {
	
	public ProcesamientoDomiVO consultarProcesamientoDomi ()  throws Exception;
	
	public List <CatalogosDomiVO> getCatalogoOrigenAportacionDomi()  throws Exception;
	
	public List <CatalogosDomiVO> getCatalogoOrigenBancosCuentasIncluir()  throws Exception;
	
	public List <CatalogosDomiVO> getCatalogoOrigenBancosArchivosGenerar()  throws Exception;
	
	public List <CatalogosDomiVO> getCatalogoTiposCuenta()  throws Exception;
	
	public List<CifrasTotalesDomiVO> getCifrasTotales(CifrasTotalesDomiFilter filter)  throws Exception;
	
	public List<CifrasTotalesDomiVO> getCifrasTotalesOtrosBancos(CifrasTotalesDomiFilter filter)  throws Exception;
	
	public RegistroCifrasDomiVO getCifraDomi(CifraDomiFilter filter)  throws Exception;
	
	public List<TotalesRegistroDomiVO> getImportesTotalesCifra(CifraDomiFilter filter) throws Exception;
	
	public List<TotalesRegistroDomiVO> getImportesTotalesCifraOtrosBancos(CifraDomiFilter filter) throws Exception;
	
	public List<ArchivosGeneradosDomiVO> ultimosArchivosGenerados(UltimosArchivosGeneradosFilter filter)throws Exception;
	
	public List<CatalogosDomiVO> getCatalogoContratoDomi() throws Exception;
}
