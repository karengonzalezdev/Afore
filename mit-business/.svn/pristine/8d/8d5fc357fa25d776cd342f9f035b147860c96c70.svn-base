package mx.profuturo.nci.persistence;

import java.util.List;

import mx.profuturo.nci.business.vo.BancosVO;
import mx.profuturo.nci.business.wrapped.BancosFilter;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface BancoPersistence {
	List<BancosVO> select(BancosFilter bancosFilter);
	List<BancosVO> selectMovs(BancosFilter bancosFilter);
	int actualizar(BancosVO bancosVO);
	List<BancosVO> selectSumarizado(BancosFilter bancosFilter);
	int actualizarConciliado(BancosVO bancosVO);
	int insertAllFolioBanco(BancosVO bancosVO);
}
