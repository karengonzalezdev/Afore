package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.SumConciliacionVO;
import mx.secure.nci.business.wrapped.SumConciliacionFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface SumConciliacionPersistence {
	public List<SumConciliacionVO> select(SumConciliacionFilter sumConciliacionFilter) throws MitBusinessException;
	public List<SumConciliacionVO> selectAconciliar(SumConciliacionFilter sumConciliacionFilter) throws MitBusinessException;
	public int actualizar(SumConciliacionVO SumConciliacionVO) throws MitBusinessException;
	public int actualizarConciliado(SumConciliacionVO SumConciliacionVO) throws MitBusinessException;
}
