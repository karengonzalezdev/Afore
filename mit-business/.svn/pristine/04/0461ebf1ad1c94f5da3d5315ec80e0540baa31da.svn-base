package mx.profuturo.nci.persistence;

import java.util.List;

import mx.profuturo.nci.business.vo.CargoVO;
import mx.profuturo.nci.business.vo.DetalleCargoVO;
import mx.profuturo.nci.business.vo.DiversificacionCargoVO;
import mx.profuturo.nci.business.wrapped.ApoVolFilter;
import mx.profuturo.nci.business.wrapped.CargoFilter;
import mx.profuturo.nci.stereotype.Mapper;

@Mapper
public interface CargoPersistence {
	
	Integer insertCargo(CargoVO cargo);
	Integer insertDetalleCargo(CargoVO cargo);
	Integer insertDetalle(DetalleCargoVO cargo);
	Integer insertDiversificacion(DiversificacionCargoVO div);

	Integer actualizarCargo(ApoVolFilter filter);	
	List<CargoVO> selectCargoSumarizacion(CargoFilter cargoFilter);
}
