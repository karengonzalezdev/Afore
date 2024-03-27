package mx.profuturo.nci.business.service;

import java.util.List;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.vo.BancosVO;
import mx.profuturo.nci.business.wrapped.BancosFilter;

public interface IBancosService {
	
	public List<BancosVO>  consultar(BancosFilter bancosFilter)throws BusinessException;
	public List<BancosVO>  consultarMovs(BancosFilter bancosFilter)throws BusinessException;
	public int actualizar(BancosVO bancosVO)throws BusinessException;
	public int actualizarConciliado(List<BancosVO> bancosVO)throws BusinessException;
}
