package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.PreSolicitudVO;
import mx.secure.nci.business.wrapped.PreSolicitudFilter;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface PreSolicitudPersistence 
{

	public int insert(PreSolicitudVO preSolicitudVO);
	
	public int actualizar(PreSolicitudVO preSolicitudVO);
	public int updateNewEstatusByFolioAndEstatus(PreSolicitudFilter preSolicitudFilter);
	
	public List<PreSolicitudVO> select(PreSolicitudFilter preSolicitudFilter);
	
}
