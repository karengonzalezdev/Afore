package mx.secure.nci.business.service;

import java.util.List;

import mx.secure.nci.business.vo.RolVO;

public interface IConfiguracionPermisoSeccionService {
	
	
	List<String> getSecciones(Short idRol);

	List<String> getSecciones(List<RolVO> descripcionRoles);

}
