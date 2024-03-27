package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.ObtenerCifrasControlVO;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface ObtenerCifrasControlPersistence {

	List<ObtenerCifrasControlVO> getLstCifrasMovimientosCif(ObtenerCifrasControlVO param);
	List<ObtenerCifrasControlVO> getLstBancosYCuentasDestino(ObtenerCifrasControlVO param);
	List<ObtenerCifrasControlVO> getLstTotalDevsecure(ObtenerCifrasControlVO param);
	ObtenerCifrasControlVO getTotalDeTotales(ObtenerCifrasControlVO param);
}
