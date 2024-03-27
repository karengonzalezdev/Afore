package mx.secure.nci.persistence;

import java.util.List;

import mx.secure.nci.business.vo.ConfigSubprocesoOrigenVO;
import mx.secure.nci.stereotype.Mapper;

@Mapper
public interface ConfigSubprocesoOrigenPersistence 
{

	public List<ConfigSubprocesoOrigenVO> selectAll();
}
