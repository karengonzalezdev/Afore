package mx.profuturo.nci.persistence;

import java.util.List;

import mx.profuturo.nci.business.vo.CifrasGeneralesTipoVO;
import mx.profuturo.nci.business.vo.CifrasGeneralesVO;
import mx.profuturo.nci.business.wrapped.CifrasGeneralesFilter;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface CifrasGeneralesPersistence {
	public List<CifrasGeneralesVO> selectPorOrigen(CifrasGeneralesFilter cifrasGeneralesFilter);
	public CifrasGeneralesTipoVO selectPorTipoLP(CifrasGeneralesFilter cifrasGeneralesFilter);
	public CifrasGeneralesTipoVO selectPorTipoCP(CifrasGeneralesFilter cifrasGeneralesFilter);
	public CifrasGeneralesTipoVO selectPorTipoLPReporte(CifrasGeneralesFilter cifrasGeneralesFilter);
	public CifrasGeneralesTipoVO selectPorTipoCPReporte(CifrasGeneralesFilter cifrasGeneralesFilter);
	public List<CifrasGeneralesVO> selectPorBanco(CifrasGeneralesFilter cifrasGeneralesFilter);
	public List<CifrasGeneralesVO> selectPorSBC(CifrasGeneralesFilter cifrasGeneralesFilter);
	public CifrasGeneralesVO selectTotales(CifrasGeneralesFilter cifrasGeneralesFilter);
}
