package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.exception.MitBusinessException;
import mx.secure.nci.business.vo.SumConciliacionVO;
import mx.secure.nci.business.wrapped.SumConciliacionFilter;

public interface ISumConciliacionService {
	public List<SumConciliacionVO> consultarSumConciliacion(SumConciliacionFilter sumConciliacionFilter) throws MitBusinessException;
	public List<SumConciliacionVO> consultarSumConciliacionAConciliar(SumConciliacionFilter sumConciliacionFilter) throws MitBusinessException;
	public int actualizarSumConciliacion(SumConciliacionVO sumConciliacionVO) throws MitBusinessException;
	public int actualizarSumConciliacionConciliado(List<SumConciliacionVO> sumConciliacionVO) throws MitBusinessException;
}
