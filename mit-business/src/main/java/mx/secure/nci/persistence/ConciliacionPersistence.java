package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.ConciliacionVO;
import mx.secure.nci.business.wrapped.ConciliacionFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface  ConciliacionPersistence 
{
	public int insert(ConciliacionVO conciliacionVO);

	public int update(ConciliacionVO conciliacionVO);
	
	public List<ConciliacionVO> selectSelective(ConciliacionFilter conciliacionFilter);
	
	public List<ConciliacionVO> selectSelectivePantalla(ConciliacionFilter conciliacionFilter);
	
	public int actualizarConciliado(ConciliacionVO conciliacionVO) throws MitBusinessException;
	
	List<String> selectFolios(ConciliacionFilter conciliacionFilter);
	
	Integer selectCount(ConciliacionFilter conciliacionFilter);
	
	public List<String> consultarFolios(ConciliacionFilter conciliacionFilter);
	
	public List<String> consultarFoliosByConciliacion(ConciliacionFilter conciliacionFilter);
	
	public List<String> consultarFoliosAFinalizar(ConciliacionFilter conciliacionFilter);
	
}
