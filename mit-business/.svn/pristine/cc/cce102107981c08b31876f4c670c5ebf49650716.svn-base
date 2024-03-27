package mx.profuturo.nci.cif.persistence;

import java.util.List;

import mx.profuturo.nci.business.vo.cif.DevolucionesCabeceraCifVO;
import mx.profuturo.nci.business.vo.cif.DevolucionesDetalleCifVO;
import mx.profuturo.nci.business.vo.cif.Profuturo_CIFVo;
import mx.profuturo.nci.business.wrapped.cif.Profuturo_CIFFilter;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface ProfuturoCIFPersistence {
	
	List<Profuturo_CIFVo> select(Profuturo_CIFFilter f);
	Integer insert(Profuturo_CIFVo vo);
	Integer creaDevolucionesDetalleCIF(DevolucionesDetalleCifVO data);
	Integer creaDevolucionesCIF(DevolucionesCabeceraCifVO data);
	List<DevolucionesCabeceraCifVO> selectReportDevCif(DevolucionesCabeceraCifVO data);
}
