package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.ConfPermisosSeccionVO;
import mx.secure.nci.stereotype.Mapper;


@Mapper
public interface ConfPermisoSeccionPersistence {

	List<ConfPermisosSeccionVO> selectByIdRol(Short idRol);
	List<ConfPermisosSeccionVO> selectByDescRol(String descRol);
}
