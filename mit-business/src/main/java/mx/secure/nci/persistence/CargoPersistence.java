package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.CargoVO;
import mx.secure.nci.business.vo.DetalleCargoVO;
import mx.secure.nci.business.vo.DiversificacionCargoVO;
import mx.secure.nci.business.wrapped.secureFilter;
import mx.secure.nci.business.wrapped.CargoFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface CargoPersistence {
	
	Integer insertCargo(CargoVO cargo);
	Integer insertDetalleCargo(CargoVO cargo);
	Integer insertDetalle(DetalleCargoVO cargo);
	Integer insertDiversificacion(DiversificacionCargoVO div);

	Integer actualizarCargo(secureFilter filter);	
	List<CargoVO> selectCargoSumarizacion(CargoFilter cargoFilter);
}
