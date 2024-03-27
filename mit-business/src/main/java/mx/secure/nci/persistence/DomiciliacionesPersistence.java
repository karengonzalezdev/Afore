package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.ArchivosGeneradosDomiVO;
import mx.secure.nci.business.vo.CatalogosDomiVO;
import mx.secure.nci.business.vo.CifrasTotalesDomiVO;
import mx.secure.nci.business.vo.ProcesamientoDomiVO;
import mx.secure.nci.business.vo.RegistroCifrasDomiVO;
import mx.secure.nci.business.vo.TotalesRegistroDomiVO;
import mx.secure.nci.business.wrapped.CifraDomiFilter;
import mx.secure.nci.business.wrapped.CifrasTotalesDomiFilter;
import mx.secure.nci.business.wrapped.UltimosArchivosGeneradosFilter;
import mx.secure.nci.stereotype.Mapper;

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
