package mx.profuturo.nci.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import mx.profuturo.nci.business.service.IConfiguracionPermisoSeccionService;
import mx.profuturo.nci.business.vo.ConfPermisosSeccionVO;
import mx.profuturo.nci.business.vo.RolVO;
import mx.profuturo.nci.persistence.ConfPermisoSeccionPersistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("configuracionPermisoSeccionService")
public class ConfiguracionPermisoSeccionServiceImpl implements IConfiguracionPermisoSeccionService{

	@Autowired ConfPermisoSeccionPersistence tfafogralConfigPermSeccionRepository;
	
	public List<String> getSecciones(Short idRol) {
		List<String> secciones = new ArrayList<String>();
		List<ConfPermisosSeccionVO> premisoSeccion = tfafogralConfigPermSeccionRepository.selectByIdRol(idRol);
		for(ConfPermisosSeccionVO cps: premisoSeccion){
			secciones.add(String.valueOf(cps.getIdSeccion()));
		}
		
		return secciones;
	}
	
	public List<String> getSecciones(List<RolVO> descripcionRoles) {
		List<String> secciones=new ArrayList<String>();
		for(RolVO rol:descripcionRoles){
			List<ConfPermisosSeccionVO> premisoSeccion = tfafogralConfigPermSeccionRepository.selectByDescRol(rol.getDescRol());
			for(ConfPermisosSeccionVO cps: premisoSeccion){
				String idSeccion = String.valueOf(cps.getIdSeccion());
				if(!secciones.contains(idSeccion)){
					secciones.add(idSeccion);
				}
			}
		}
		
		return secciones;
	}

}
