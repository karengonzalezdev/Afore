package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.BancosVO;
import mx.secure.nci.business.wrapped.BancosFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface BancoPersistence {
	List<BancosVO> select(BancosFilter bancosFilter);
	List<BancosVO> selectMovs(BancosFilter bancosFilter);
	int actualizar(BancosVO bancosVO);
	List<BancosVO> selectSumarizado(BancosFilter bancosFilter);
	int actualizarConciliado(BancosVO bancosVO);
	int insertAllFolioBanco(BancosVO bancosVO);
}
