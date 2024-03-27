package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.CifrasGeneralesTipoVO;
import mx.secure.nci.business.vo.CifrasGeneralesVO;
import mx.secure.nci.business.wrapped.CifrasGeneralesFilter;
import mx.secure.nci.stereotype.Mapper;

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
