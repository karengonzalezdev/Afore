package mx.profuturo.nci.business.service;

import java.util.List;

import com.jeveris.core.exception.impl.BusinessException;

import mx.profuturo.nci.business.vo.CifrasGeneralesTipoVO;
import mx.profuturo.nci.business.vo.CifrasGeneralesVO;
import mx.profuturo.nci.business.vo.ReporteVO;
import mx.profuturo.nci.business.wrapped.CifrasGeneralesFilter;

public interface ICifrasGenerales {
	CifrasGeneralesTipoVO consultarPorTipo(CifrasGeneralesFilter cifrasGeneralesFilter) throws BusinessException;
	CifrasGeneralesTipoVO consultarPorTipoReporte(CifrasGeneralesFilter cifrasGeneralesFilter) throws BusinessException;
	List<CifrasGeneralesVO> consultarPorOrigen(CifrasGeneralesFilter cifrasGeneralesFilter) throws BusinessException;
	List<CifrasGeneralesVO> consultarPorBanco(CifrasGeneralesFilter cifrasGeneralesFilter) throws BusinessException;
	List<CifrasGeneralesVO> consultarPorSBC(CifrasGeneralesFilter cifrasGeneralesFilter) throws BusinessException;
	ReporteVO generarReport(CifrasGeneralesFilter cifrasGeneralesFilter) throws BusinessException;
}
