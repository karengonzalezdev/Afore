package mx.secure.nci.persistence;

import mx.secure.nci.business.vo.DetalleArchivoDomiVO;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface DetalleArchivoDomiPersistence {
		Integer insertDetalle(DetalleArchivoDomiVO detalle);
		Integer insertDetallePar(DetalleArchivoDomiVO detalle);
}
