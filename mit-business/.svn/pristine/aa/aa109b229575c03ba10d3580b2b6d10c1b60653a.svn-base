package mx.profuturo.nci.persistence;

import java.util.List;

import mx.profuturo.nci.business.exception.MitBusinessException;
import mx.profuturo.nci.business.vo.SumConciliacionVO;
import mx.profuturo.nci.business.wrapped.SumConciliacionFilter;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface SumConciliacionPersistence {
	public List<SumConciliacionVO> select(SumConciliacionFilter sumConciliacionFilter) throws MitBusinessException;
	public List<SumConciliacionVO> selectAconciliar(SumConciliacionFilter sumConciliacionFilter) throws MitBusinessException;
	public int actualizar(SumConciliacionVO SumConciliacionVO) throws MitBusinessException;
	public int actualizarConciliado(SumConciliacionVO SumConciliacionVO) throws MitBusinessException;
}
